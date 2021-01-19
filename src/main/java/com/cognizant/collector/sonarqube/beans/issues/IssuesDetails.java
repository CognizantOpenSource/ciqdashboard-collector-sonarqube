package com.cognizant.collector.sonarqube.beans.issues;

import com.cognizant.collector.sonarqube.beans.Paging;
import com.fasterxml.jackson.annotation.*;
import lombok.Data;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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