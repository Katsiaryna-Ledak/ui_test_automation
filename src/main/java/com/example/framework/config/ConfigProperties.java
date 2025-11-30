package com.example.framework.config;

import org.aeonbits.owner.Config;
import org.aeonbits.owner.ConfigFactory;

@Config.Sources("classpath:config.properties")
public interface ConfigProperties extends Config {

    @Key("base_url")
    String baseUrl();

    @Key("email")
    String email();

    @Key("password")
    String password();

    @Key("name")
    String name();

    @Key("timeout")
    int timeout();

    @Key("headless")
    boolean headless();

    static ConfigProperties get() {
        return ConfigFactory.create(ConfigProperties.class);
    }
}
