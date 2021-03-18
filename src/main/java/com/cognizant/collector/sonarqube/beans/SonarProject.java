
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

package com.cognizant.collector.sonarqube.beans;


import com.fasterxml.jackson.annotation.*;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/*
 * SonarProject
 *
 * @author Cognizant
 */

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "id",
        "k",
        "nm",
        "sc",
        "qu"
})
@Data
public class SonarProject {

    @JsonProperty("id")
    private Integer id;
    @JsonProperty("k")
    private String projectKey;
    @JsonProperty("nm")
    private String projectName;
    @JsonProperty("sc")
    private String sc;
    @JsonProperty("qu")
    private String qualifier;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<>();
}

