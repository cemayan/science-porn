package com.cayan.contentservice.repository;

import com.cayan.contentservice.model.ScienceContent;
import org.springframework.data.neo4j.repository.Neo4jRepository;

public interface ScienceContextRepository extends Neo4jRepository<ScienceContent, Long> {

}
