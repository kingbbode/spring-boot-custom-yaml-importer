package com.github.kingbbode.custom.yaml.importer.processor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.context.properties.bind.Bindable;
import org.springframework.boot.context.properties.bind.Binder;
import org.springframework.boot.context.properties.bind.PropertySourcesPlaceholdersResolver;
import org.springframework.boot.context.properties.source.ConfigurationPropertySources;
import org.springframework.boot.env.EnvironmentPostProcessor;
import org.springframework.boot.env.YamlPropertySourceLoader;
import org.springframework.boot.origin.OriginTrackedValue;
import org.springframework.core.Ordered;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.Profiles;
import org.springframework.core.env.PropertySource;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Slf4j
public class CustomYamlPropertiesEnvironmentPostProcessor implements EnvironmentPostProcessor, Ordered {

    private static final int DEFAULT_ORDER = Ordered.LOWEST_PRECEDENCE;
    private static final String SPRING_PROFILES = "spring.profiles";

    private final YamlPropertySourceLoader loader = new YamlPropertySourceLoader();

    @Override
    public void postProcessEnvironment(ConfigurableEnvironment environment,
                                       SpringApplication application) {
        log.info("** START CustomYamlPropertiesEnvironmentPostProcessor **");
        ResourceLoader resourceLoader = Optional
                .ofNullable(application.getResourceLoader())
                .orElseGet(DefaultResourceLoader::new);

        ResourcePatternResolver resourcePatternResolver = new PathMatchingResourcePatternResolver(resourceLoader);

        Resource[] resources = new Resource[]{};
        try{
            resources = resourcePatternResolver.getResources("classpath*:config/custom-*.yml");
        }catch(IOException e){
            log.error("{}", e.getMessage(), e);
        }
        for(Resource resource : resources) {
            loadYaml(resource, environment)
                    .forEach(propertySource -> {
                        log.info("[Yaml Third Properties Load] file: {}", resource.getFilename());
                        environment.getPropertySources().addLast(propertySource);
                    });
        }
    }

    private List<PropertySource<?>> loadYaml(Resource path, ConfigurableEnvironment environment) {
        if (!path.exists()) {
            throw new IllegalArgumentException("Resource " + path + " does not exist");
        }

        try {
            List<PropertySource<?>> defaultPropertySource = new ArrayList<>();

            List<PropertySource<?>> propertySourceList = this.loader.load(path.getFilename(), path).stream().filter(
                    propertySource -> {
                        Binder binder = new Binder(
                                ConfigurationPropertySources.from(propertySource),
                                new PropertySourcesPlaceholdersResolver(environment));
                        String[] profiles = binder.bind(SPRING_PROFILES, Bindable.of(String[].class))
                                .orElse(new String[0]);

                        if (profiles.length == 0) {
                            defaultPropertySource.add(propertySource);
                            return false;
                        }
                        return environment.acceptsProfiles(Profiles.of(profiles));
                    }).collect(Collectors.toList());

            propertySourceList = sortProfilePriority(environment, propertySourceList);
            propertySourceList.addAll(defaultPropertySource);

            return propertySourceList;
        }
        catch (IOException ex) {
            throw new IllegalStateException(
                    "Failed to load yaml configuration from " + path, ex);
        }
    }

    /**
     * 사용자 입력 순서 보장
     */
    private List<PropertySource<?>> sortProfilePriority(ConfigurableEnvironment environment, List<PropertySource<?>> propertySourceList) {
        String[] activeProfiles = environment.getActiveProfiles();

        return IntStream.range(0, activeProfiles.length)
                .map(i -> activeProfiles.length - i - 1)
                .mapToObj(i -> activeProfiles[i])
                .filter(Objects::nonNull)
                .map(profile -> propertySourceList.stream().filter(propertySource -> matchProfiles(profile, propertySource)).findAny().orElse(null))
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    @SuppressWarnings("unchecked")
    private boolean matchProfiles(String profile, PropertySource<?> propertySource) {
        Object property = propertySource.getProperty(SPRING_PROFILES);
        if(property == null) {
            return ((Map<String, OriginTrackedValue>) propertySource.getSource())
                    .entrySet()
                    .stream()
                    .filter(entry -> entry.getKey().contains(SPRING_PROFILES))
                    .map(trackedValueEntry -> trackedValueEntry.getValue().toString())
                    .anyMatch(profile::equals);
        }

        return Arrays.stream(((String) property).split(","))
                .map(String::trim)
                .anyMatch(s -> s.equals(profile));
    }

    @Override
    public int getOrder() {
        return DEFAULT_ORDER;
    }
}
