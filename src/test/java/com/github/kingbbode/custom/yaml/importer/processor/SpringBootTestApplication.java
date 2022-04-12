package com.github.kingbbode.custom.yaml.importer.processor;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@EnableConfigurationProperties({
    Test1Properties.class,
    Test2Properties.class,
    Test3Properties.class,
    Test4Properties.class,
    Test5Properties.class,
    Test6Properties.class,
    Test7Properties.class,
    Test8Properties.class
})
@SpringBootApplication
public class SpringBootTestApplication {
    @Configuration
    public static class TestConfig {
        @Bean
        public CustomYamlPropertiesEnvironmentPostProcessor thirdPropertiesEnvironmentPostProcessor() {
            return new CustomYamlPropertiesEnvironmentPostProcessor();
        }
    }
}
