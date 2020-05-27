package com.cayan.contentservice.service.impl;

import com.cayan.contentservice.model.AuthorBy;
import com.cayan.contentservice.model.Person;
import com.cayan.contentservice.model.ScienceContent;
import com.cayan.contentservice.repository.PersonRepository;
import com.cayan.contentservice.repository.ScienceContextRepository;
import com.cayan.contentservice.service.IContentService;
import com.cayan.contentservice.util.SecurityDetails;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ContentService implements IContentService {

    @Autowired
    ScienceContextRepository scienceContextRepository;

    @Autowired
    PersonRepository personRepository;

    @Autowired
    SecurityDetails securityDetails;

    private static Logger LOGGER = LoggerFactory.getLogger(ContentService.class);

    @Override
    public void save(ScienceContent scienceContext) {
        scienceContextRepository.save(scienceContext);
    }

    @Override
    public List<AuthorBy> getAll() {

        try {
           Optional<Person> person =   personRepository.findByUserId(securityDetails.getAuthenticatedUser().getUserId());
           List<AuthorBy> scienceContents = person.get().getAuthorByList();
           return  scienceContents;
        } catch (JsonProcessingException e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }
    }


    public Optional<ScienceContent> getContent(Long id, Long userId) {
        return scienceContextRepository.findByIdAndUserId(id, userId);
    }

    public Optional<Person> getPersonByUserId(Long userId) {
        return personRepository.findByUserId(userId);
    }

    public Person addNewUser(Person person) {
      return personRepository.save(person);
    }
}
