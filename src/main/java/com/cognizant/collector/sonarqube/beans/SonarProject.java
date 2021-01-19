package com.cognizant.collector.sonarqube.beans;


import com.fasterxml.jackson.annotation.*;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

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

