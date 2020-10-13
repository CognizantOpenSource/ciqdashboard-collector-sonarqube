package com.cognizant.collector.sonarqube.scheduler;

import com.cognizant.collector.sonarqube.component.SonarqubeIssueComponent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class JobSchedulerImpl implements BatchEvents {

    @Autowired
    SonarqubeIssueComponent issueComponent;

    @Override
    public void beforeJob() {
        log.info("Before Job process........!");
        issueComponent.getIssues();
    }

    @Override
    public void beforeTask() {
        log.info("Before Task process........!");
    }

    @Override
    public void afterTask() {
        log.info("After Task process........!");
    }

    @Override
    public void afterJob() {
        log.info("After Job process........!");
    }
}