package com.cayan.relationservice.repository;

import com.cayan.relationservice.entity.Relation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@Transactional
public interface RelationRepository extends JpaRepository<Relation,Long> {

    Optional<Relation> findByUserId(Long userId);

}
