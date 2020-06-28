package com.cayan.relationservice.service.impl;


import com.cayan.relationservice.dto.RelationDTO;
import com.cayan.relationservice.dto.mapper.RelationMapper;
import com.cayan.relationservice.repository.RelationRepository;
import com.cayan.relationservice.service.RelationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RelationServiceImpl implements RelationService {

    @Autowired
    RelationRepository relationRepository;

    @Autowired
    RelationMapper relationMapper;

    @Override
    public Optional<RelationDTO> getRelationByUserId(Long userId) {
        return relationMapper.convertToDto(relationRepository.findByUserId(userId));
    }
}