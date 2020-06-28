package com.cayan.relationservice.dto.mapper;

import com.cayan.common.dto.UserDTO;
import com.cayan.relationservice.dto.RelationDTO;
import com.cayan.relationservice.entity.Relation;
import com.cayan.relationservice.service.RelationService;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.Optional;

@Service
public class RelationMapper {

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    RelationService relationService;


    public Optional<RelationDTO> convertToDto(Optional<Relation> relation) {
        if(relation.isPresent()) {
            return Optional.of(modelMapper.map(relation.get(), RelationDTO.class));
        }
        return  Optional.empty();
    }

    public Relation convertToEntity(RelationDTO relationDTO) throws ParseException {
        Relation relation = modelMapper.map(relationDTO, Relation.class);
        return relation;
    }

}
