# spring-boot-custom-yaml-importer

[![Build Status](https://travis-ci.org/kingbbode/spring-boot-custom-yaml-importer.svg?branch=master)](https://travis-ci.org/kingbbode/spring-boot-custom-yaml-importer)
[![](https://jitpack.io/v/kingbbode/spring-boot-custom-yaml-importer.svg)](https://jitpack.io/#kingbbode/spring-boot-custom-yaml-importer)
[![Coverage Status](https://coveralls.io/repos/github/kingbbode/spring-boot-custom-yaml-importer/badge.svg?branch=master)](https://coveralls.io/github/kingbbode/spring-boot-custom-yaml-importer?branch=master)

spring boot support for automatically loading custom yaml file.

---

Spring Boot offers three ways to load custom settings.

- spring.config.location, spring.config.name
- SpringApplicationBuilder
- EnvironmentPostProcessor

**but** this is cumbersome. when a lot of multi-modules and settings are created, you have to work on them every time.

so i made it. 

**so that you can load the settings just by having dependencies.**

[[blog : spring-boot-properties-environment](http://blog.kingbbode.com/posts/spring-boot-properties-environment)]


## Install

```
repositories {
    maven { url 'https://jitpack.io' }
}

dependencies {
    compile 'com.github.kingbbode:spring-boot-custom-yaml-importer:{version}'
}
```

## Usage

load the yaml file that is matched with `resources/config/custom-*.yml`.

write the settings you need for each of your modules here.