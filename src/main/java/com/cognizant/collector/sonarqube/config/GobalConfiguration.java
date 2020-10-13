package com.cognizant.collector.sonarqube.config;


import com.cognizant.collector.sonarqube.beans.ConfigProperties;
import com.cognizant.collector.sonarqube.client.SonarClient;
import feign.Feign;
import feign.Logger;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import feign.slf4j.Slf4jLogger;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.support.SpringMvcContract;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
@Slf4j
public class GobalConfiguration {

    @Autowired
    ConfigProperties properties;

    @Bean
    public SonarClient sonarClient() {

        return Feign.builder()
                .contract(new SpringMvcContract())
                .decoder(new JacksonDecoder())
                .encoder(new JacksonEncoder())
                .logger(new Slf4jLogger(SonarClient.class))
                .logLevel(Logger.Level.FULL)
                .target(SonarClient.class,properties.getUrl());
    }

}


