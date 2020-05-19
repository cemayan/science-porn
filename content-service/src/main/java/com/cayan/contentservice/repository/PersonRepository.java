package com.cayan.contentservice.repository;

import com.cayan.contentservice.model.Person;
import com.cayan.contentservice.model.ScienceContent;
import org.springframework.data.neo4j.repository.Neo4jRepository;

import java.util.Optional;

public interface PersonRepository extends Neo4jRepository<Person, Long> {

    Optional<Person> findByUserId(Long userId);
}
