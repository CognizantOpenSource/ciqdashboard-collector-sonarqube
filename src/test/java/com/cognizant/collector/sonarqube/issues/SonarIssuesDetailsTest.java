package com.cognizant.collector.sonarqube.issues;

import com.cognizant.collector.sonarqube.beans.SonarProject;
import com.cognizant.collector.sonarqube.component.SonarqubeIssueComponent;
import com.cognizant.collector.sonarqube.service.SonarqubeIssueService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.List;

@SpringBootTest
class SonarIssuesDetailsTest {

    @Autowired
    SonarqubeIssueComponent issueComponent;
//    @Autowired
//    SonarqubeIssueService issueService;

    /* Uncomment the bellow lines for validation */
    @Test
    void getStatusNotIn(){
        List<SonarProject> projects = issueComponent.getSonarProjects();
//        projects.forEach(sonarProject -> {
//            String commaSeparatedKeys = issueComponent.getCommaSeparatedKeys(sonarProject.getProjectName());
//            System.out.println(commaSeparatedKeys);
//        });
        Assertions.assertNotNull(projects);
    }

    @Test
    void getMaxDateTest(){
        List<SonarProject> projects = issueComponent.getSonarProjects();
//        projects.forEach(sonarProject -> {
//            Date maxCreatedDate = issueService.getMaxCreatedDate(sonarProject.getProjectName());
//            String dateToString = issueComponent.parseDateToString(maxCreatedDate);
//            System.out.println(dateToString);
//
//        });
        Assertions.assertNotNull(projects);
    }

    @Test
    void getProjectTest() {
        List<SonarProject> sonarProjects = issueComponent.getSonarProjects();
//        System.out.println(sonarProjects);
        Assertions.assertNotNull(sonarProjects);
    }


}
