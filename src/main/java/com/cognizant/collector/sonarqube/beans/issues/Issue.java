
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

import com.fasterxml.jackson.annotation.*;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 * Issue
 *
 * @author Cognizant
 */

@Data
@Document(collection = "#{T(com.cognizant.collector.sonarqube.component.SonarqubeIssueComponent).getCollectionName()}")
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "key",
        "projectId",
        "projectKey",
        "projectName",
        "rule",
        "severity",
        "component",
        "project",
        "hash",
        "textRange",
        "flows",
        "resolution",
        "status",
        "message",
        "effort",
        "debt",
        "author",
        "tags",
        "creationDate",
        "updateDate",
        "closeDate",
        "type",
        "organization",
        "fromHotspot",
        "line"
})
public class Issue {
    @Id
    @Field("key")
    @JsonProperty("key")
    private String key;
    @JsonProperty("projectId")
    private int projectId;
    @JsonProperty("projectKey")
    private String projectKey;
    @JsonProperty("projectName")
    private String projectName;
    @JsonProperty("rule")
    private String rule;
    @JsonProperty("severity")
    private String severity;
    @JsonProperty("component")
    private String component;
    @JsonProperty("project")
    private String project;
    @JsonProperty("hash")
    private String hash;
    @JsonProperty("textRange")
    private TextRange textRange;
    @JsonProperty("flows")
    private List<Object> flows = null;
    @JsonProperty("resolution")
    private String resolution;
    @JsonProperty("status")
    private String status;
    @JsonProperty("message")
    private String message;
    @JsonProperty("effort")
    private String effort;
    @JsonProperty("debt")
    private String debt;
    @JsonProperty("author")
    private String author;
    @JsonProperty("tags")
    private List<String> tags = null;
    @JsonProperty("creationDate")
    private Date creationDate;
    @JsonProperty("updateDate")
    private Date updateDate;
    @JsonProperty("closeDate")
    private Date closeDate;
    @JsonProperty("type")
    private String type;
    @JsonProperty("organization")
    private String organization;
    @JsonProperty("fromHotspot")
    private Boolean fromHotspot;
    @JsonProperty("line")
    private Integer line;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<>();

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}