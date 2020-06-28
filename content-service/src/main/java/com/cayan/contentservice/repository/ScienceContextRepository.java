package com.cayan.contentservice.repository;

import com.cayan.contentservice.model.ScienceContent;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ScienceContextRepository extends Neo4jRepository<ScienceContent, Long> {

    Optional<ScienceContent> findByIdAndUserId(Long id, Long userId);


    @Query("MATCH (scienceContent:ScienceContent) WITH scienceContent ORDER BY scienceContent.likeCount DESC LIMIT 3 MATCH (scienceContent:ScienceContent)<-[rel]-(person:Person)   RETURN scienceContent,rel,person")
    List<ScienceContent>  findOrderByLikeCount();

}
