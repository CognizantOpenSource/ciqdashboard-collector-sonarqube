package com.cognizant.collector.sonarqube.beans;

import com.fasterxml.jackson.annotation.*;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "pageIndex",
        "pageSize",
        "total"
})
@Data
public class Paging {

    @JsonProperty("pageIndex")
    public Integer pageIndex;
    @JsonProperty("pageSize")
    public Integer pageSize;
    @JsonProperty("total")
    public Integer total;
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
