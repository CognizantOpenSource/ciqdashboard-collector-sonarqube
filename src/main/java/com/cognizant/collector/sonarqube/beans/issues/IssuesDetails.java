
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

package com.cognizant.collector.sonarqube.beans.issues;

import com.cognizant.collector.sonarqube.beans.Paging;
import com.fasterxml.jackson.annotation.*;
import lombok.Data;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 * IssuesDetails
 *
 * @author Cognizant
 */

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
"total",
"p",
"ps",
"paging",
"effortTotal",
"debtTotal",
"issues",
"components",
"facets"
})
public class IssuesDetails {

@JsonProperty("total")
private Integer total;
@JsonProperty("p")
private Integer p;
@JsonProperty("ps")
private Integer ps;
@JsonProperty("paging")
private Paging paging;
@JsonProperty("effortTotal")
private Integer effortTotal;
@JsonProperty("debtTotal")
private Integer debtTotal;
@JsonProperty("issues")
private List<Issue> issues = null;
@JsonProperty("components")
private List<Object> components = null;
@JsonProperty("facets")
private List<Object> facets = null;
@JsonIgnore
private Map<String, Object> additionalProperties = new HashMap<>();

}