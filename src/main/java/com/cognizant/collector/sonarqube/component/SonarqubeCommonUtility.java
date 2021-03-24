
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

/*
 * SonarqubeCommonUtility
 *
 * @author Cognizant
 */

@Component
public class SonarqubeCommonUtility {
//    private final JacksonDecoder decoder = new JacksonDecoder();
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
