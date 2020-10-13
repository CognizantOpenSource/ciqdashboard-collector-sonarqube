package com.cognizant.collector.sonarqube.client;

import com.cognizant.collector.sonarqube.beans.SonarProject;
import com.cognizant.collector.sonarqube.beans.issues.IssuesDetails;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

public interface SonarClient {

    /**
     * Getting List of projects from sonar
     * @param headers RequestHeaders for authentication
     * @return list of projects
     */
    @GetMapping("/api/projects/index")
    public List<SonarProject> getProject(@RequestHeader HttpHeaders headers);

    /*Issues details*/

    /*http://localhost:9000/api/issues/search?projectKeys=pl.piomin.services:organization-service&p=1&ps=100&asc=true&s=CREATION_DATE&createdAfter=2020-07-22T10:35:28%2B0000*/

    /**
     * Getting list of issues using creation after
     * @param requestParam Request params to get issues
     * @param headers RequestHeaders for authentication
     * @return list of issuse
     */
    @GetMapping("/api/issues/search")
    IssuesDetails getIssuesDetails(@RequestParam Map<String, Object> requestParam,
                                   @RequestHeader HttpHeaders headers);

    /*http://localhost:9000/api/issues/search?issues=AXPXfX4BxPPb_RQblnTY,AXPXfX4BxPPb_RQblnTX&projectKeys=pl.piomin.services:organization-service*/

    /**
     * Getting list of existing issues based on issues id with comma separated
     * @param requestParam Request params to get issues
     * @param headers RequestHeaders for authentication
     * @return list of issuse
     */
    @GetMapping("/api/issues/search")
    IssuesDetails getIssuesDetailsByIds(@RequestParam Map<String, Object> requestParam,
                                        @RequestHeader HttpHeaders headers);

    /*Issues details*/

}
