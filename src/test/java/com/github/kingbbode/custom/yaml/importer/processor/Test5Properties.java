package com.github.kingbbode.custom.yaml.importer.processor;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@NoArgsConstructor
@ConfigurationProperties(prefix = "test5")
public class Test5Properties {
    private String test;
}
