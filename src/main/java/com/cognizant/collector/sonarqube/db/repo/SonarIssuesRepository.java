package com.cognizant.collector.sonarqube.db.repo;

import com.cognizant.collector.sonarqube.beans.issues.Issue;
import com.cognizant.collector.sonarqube.beans.issues.Types;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface SonarIssuesRepository extends MongoRepository<Issue, String> {
    Issue findFirstByProjectNameOrderByCreationDateDesc(String projectName);

    @Query(fields = "{ '_id' : 1}", value = "{'projectName' : ?0, 'status' : {'$nin' : ?1}}")
    List<Issue> findByProjectNameAndStatusNotIn(String projectName, List<Types.Status> statuses);
}
