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
    compile 'com.github.kingbbode:spring-boot-custom-yaml-importer:0.1.0'
}
```

## Usage

load the yaml file that is matched with `resources/config/custom-*.yml`.

write the settings you need for each of your modules here.

### Single Module

```
|_src
|  |_main
|     |_resources
|           |_config
|           |    |_ custom-a.yml
|           |    |_ custom-b.yml
|           |    |_ custom-c.yml
|           |
|           |_application.yml  
```

### Multi Module

```
|_app
|  |_src
|     |_main
|        |_resources
|              |_config
|              |   |_custom-a.yml
|              |
|              |_application.yml
|                   
|_module-b
|   |_src
|      |_main
|         |_resources
|               |_config
|                    |_custom-b.yml
|_module-c
|   |_src
|      |_main
|         |_resources
|               |_config
|                    |_custom-c.yml
```