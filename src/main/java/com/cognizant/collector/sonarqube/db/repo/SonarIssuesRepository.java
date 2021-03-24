
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

package com.cognizant.collector.sonarqube.db.repo;

import com.cognizant.collector.sonarqube.beans.issues.Issue;
import com.cognizant.collector.sonarqube.beans.issues.Types;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

/*
 * SonarIssuesRepository
 *
 * @author Cognizant
 */

public interface SonarIssuesRepository extends MongoRepository<Issue, String> {
    Issue findFirstByProjectNameOrderByCreationDateDesc(String projectName);

    @Query(fields = "{ '_id' : 1}", value = "{'projectName' : ?0, 'status' : {'$nin' : ?1}}")
    List<Issue> findByProjectNameAndStatusNotIn(String projectName, List<Types.Status> statuses);
}
