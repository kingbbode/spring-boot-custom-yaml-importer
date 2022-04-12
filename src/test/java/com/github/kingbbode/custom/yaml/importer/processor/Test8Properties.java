package com.github.kingbbode.custom.yaml.importer.processor;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@NoArgsConstructor
@ConfigurationProperties(prefix = "test8")
public class Test8Properties {
    private String test;
}
