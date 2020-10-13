package com.cognizant.collector.sonarqube.component;

import com.cognizant.collector.sonarqube.beans.ConfigProperties;
import com.cognizant.collector.sonarqube.config.CustomBasicAuthentication;
import feign.jackson.JacksonDecoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

@Component
public class SonarqubeCommonUtility {
    private final JacksonDecoder decoder = new JacksonDecoder();
    private final HttpHeaders headers = new HttpHeaders();
    @Autowired
    ConfigProperties properties;
    @Autowired
    CustomBasicAuthentication customBasicAuthentication;

    @PostConstruct
    private void postConstructMethod() {
        headers.setAll(customBasicAuthentication.getBasicAuthentication(properties.getUsername(), properties.getToken()));
    }

    HttpHeaders getHeaders() {
        return headers;
    }

    public String parseDateToString(Date date){
        if (null == date) return "";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ");
        sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
        return sdf.format(date);
    }
}
