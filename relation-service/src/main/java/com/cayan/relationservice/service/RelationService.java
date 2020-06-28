package com.cayan.relationservice.service;

import com.cayan.relationservice.dto.RelationDTO;

import java.util.Optional;

public interface RelationService {

    Optional<RelationDTO> getRelationByUserId(Long userId);
}
