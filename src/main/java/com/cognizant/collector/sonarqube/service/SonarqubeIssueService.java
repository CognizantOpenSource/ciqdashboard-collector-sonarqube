
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

/*
 * SonarqubeIssueService
 *
 * @author Cognizant
 */

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
