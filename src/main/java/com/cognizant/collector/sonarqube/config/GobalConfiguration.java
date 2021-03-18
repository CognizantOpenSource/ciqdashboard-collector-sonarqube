
/*
 * © [2021] Cognizant. All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * 	http:www.apache.orglicensesLICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

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

/*
 * GobalConfiguration
 *
 * @author Cognizant
 */

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


