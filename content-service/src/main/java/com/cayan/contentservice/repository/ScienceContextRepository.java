package com.cayan.contentservice.repository;

import com.cayan.contentservice.model.ScienceContent;
import org.springframework.data.neo4j.repository.Neo4jRepository;

import java.util.List;
import java.util.Optional;

public interface ScienceContextRepository extends Neo4jRepository<ScienceContent, Long> {

    Optional<ScienceContent> findByIdAndUserId(Long id, Long userId);
    List<ScienceContent>  findTop5ByOrderByLikeCount();

}
