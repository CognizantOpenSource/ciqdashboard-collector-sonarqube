
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

import com.cognizant.collector.sonarqube.beans.SonarProject;
import com.cognizant.collector.sonarqube.beans.issues.Issue;
import com.cognizant.collector.sonarqube.beans.issues.IssuesDetails;
import com.cognizant.collector.sonarqube.beans.Paging;
import com.cognizant.collector.sonarqube.client.SonarClient;
import com.cognizant.collector.sonarqube.service.SonarqubeIssueService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.annotation.PostConstruct;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.cognizant.collector.sonarqube.constant.Constant.*;

/*
 * SonarqubeIssueComponent
 *
 * @author Cognizant
 */

@Component
@Slf4j
public class SonarqubeIssueComponent {

    public static String collectionName;
    
    @Autowired
    SonarClient client;

    @Autowired
    SonarqubeIssueService sonarqubeIssueService;
    @Autowired
    SonarqubeCommonUtility sonarqubeCommonUtility;
    private HttpHeaders requestHeader = new HttpHeaders();

    @PostConstruct
    private void init() {
        requestHeader = sonarqubeCommonUtility.getHeaders();
    }

    public void getIssues() {
        getSonarIssues();
    }

    public void getSonarIssues() {
        List<SonarProject> projects = getSonarProjects();
        projects.forEach(sonarProject -> {
            updateOpenIssues(sonarProject);
            getAllIssues(sonarProject);
        });
    }

    public List<SonarProject> getSonarProjects() {
        return client.getProject(requestHeader);
    }

    public IssuesDetails getIssuesDetails(Map<String, Object> requestParamMap) {
        return client.getIssuesDetails(requestParamMap, requestHeader);
    }

    public IssuesDetails getIssuesDetailsByIds(Map<String, Object> requestParamMap) {
        return client.getIssuesDetailsByIds(requestParamMap, requestHeader);
    }

    private void updateOpenIssues(SonarProject project) {
        String commaSeparatedKeys = getCommaSeparatedKeys(project.getProjectName());
        if (!StringUtils.isEmpty(commaSeparatedKeys)) {
            getAndUpdateExistingIssues(project, commaSeparatedKeys);
        }
    }

    private void getAndUpdateExistingIssues(SonarProject project, String commaSeparatedKeys) {
        log.info("********** update existing issues which is not in (CLOSED, RESOLVED) status, Project Name: {} ", project.getProjectName());
        int pageIndex = PAGE_INDEX;
        int pageSize = MAX_RESULT_PER_PAGE;
        int noOfPages = 0;
        Map<String, Object> requestParamMap = new HashMap<>();
        requestParamMap.put(REQ_PARAM_PS, pageSize);
        requestParamMap.put(PROJECT_KEYS, project.getProjectKey());
        requestParamMap.put(ISSUES, commaSeparatedKeys);
        do {
            requestParamMap.put(REQ_PARAM_P, pageIndex);
            log.info("PageNumber:{} , PageSize:{}, ProjectKey:{} ", pageIndex, pageSize, project.getProjectKey());
            IssuesDetails issuesDetails = getIssuesDetailsByIds(requestParamMap);
            saveAllIssuesInDB(project, issuesDetails);
            if (pageIndex == 1) {
                Paging paging = issuesDetails.getPaging();
                noOfPages = (int) Math.ceil((double) paging.getTotal() / paging.getPageSize());
            }
            pageIndex++;
        } while (noOfPages >= pageIndex);
    }

    private void getAllIssues(SonarProject project) {
        log.info("********** Getting all issues with creationAfter, Project Name: {} ", project.getProjectName());
        int pageIndex = PAGE_INDEX;
        int pageSize = MAX_RESULT_PER_PAGE;
        int noOfPages = 0;
        Map<String, Object> requestParamMap = new HashMap<>();
        Date maxCreatedDate = sonarqubeIssueService.getMaxCreatedDate(project.getProjectName());
        String creationAfter = parseDateToString(maxCreatedDate);
        requestParamMap.put(REQ_PARAM_PS, pageSize);
        requestParamMap.put(PROJECT_KEYS, project.getProjectKey());
        requestParamMap.put(ASC, true);
        requestParamMap.put(SORT_S, CREATION_DATE);
        if (null != maxCreatedDate) {
            requestParamMap.put(CREATED_AFTER, creationAfter);
        }
        do {
            requestParamMap.put(REQ_PARAM_P, pageIndex);
            log.info("PageNumber:{} , PageSize:{}, CreationAfter:{}, ProjectKey:{} ", pageIndex, pageSize, creationAfter, project.getProjectKey());
            IssuesDetails issuesDetails = getIssuesDetails(requestParamMap);
            saveAllIssuesInDB(project, issuesDetails);
            if (pageIndex == 1) {
                Paging paging = issuesDetails.getPaging();
                noOfPages = (int) Math.ceil((double) paging.getTotal() / paging.getPageSize());
            }
            pageIndex++;
        } while (noOfPages >= pageIndex);
    }

    private List<Issue> saveAllIssuesInDB(SonarProject project, IssuesDetails issuesDetails) {
        List<Issue> issues = issuesDetails.getIssues();
        issues.forEach(issue -> {
            issue.setProjectId(project.getId());
            issue.setProjectKey(project.getProjectKey());
            issue.setProjectName(project.getProjectName());
        });
        return sonarqubeIssueService.addAll(issues);
    }

    public String parseDateToString(Date date) {
        return sonarqubeCommonUtility.parseDateToString(date);
    }

    public String getCommaSeparatedKeys(String projectName) {
        List<String> keys = sonarqubeIssueService.getIssuesNotInClosedOrResolvedStatus(projectName);
        return keys.stream().collect(Collectors.joining(","));
    }

    @Value("${spring.data.mongodb.collection}")
    public void setCollectionName(String collectionName) {
        this.collectionName = SOURCE+collectionName;
    }

    public static String getCollectionName(){
        return collectionName;
    }
}
