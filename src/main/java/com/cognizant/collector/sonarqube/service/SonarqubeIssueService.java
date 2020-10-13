package com.cognizant.collector.sonarqube.service;

import com.cognizant.collector.sonarqube.beans.issues.Issue;
import com.cognizant.collector.sonarqube.beans.issues.Types;
import com.cognizant.collector.sonarqube.db.repo.SonarIssuesRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class SonarqubeIssueService {

    @Autowired
    SonarIssuesRepository issuesRepository;

    public List<Issue> getAll(){
        return issuesRepository.findAll();
    }

    public Optional<Issue> get(String id){
        return issuesRepository.findById(id);
    }

    public Issue add(Issue issue){
        return issuesRepository.save(issue);
    }

    public List<Issue> addAll(List<Issue> issues){
        return issuesRepository.saveAll(issues);
    }

    public Date getMaxCreatedDate(String projectName){
        Issue first = issuesRepository.findFirstByProjectNameOrderByCreationDateDesc(projectName);
        return first == null ? null : first.getCreationDate();
    }

    public List<String> getIssuesNotInClosedOrResolvedStatus(String projectName){
        List<Issue> issues = issuesRepository.findByProjectNameAndStatusNotIn(projectName, Arrays.asList(Types.Status.CLOSED, Types.Status.REOPENED));
        return issues.stream().map(Issue::getKey).collect(Collectors.toList());
    }
}
