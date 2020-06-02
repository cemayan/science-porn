package com.cayan.contentservice.service.impl;

import com.cayan.contentservice.model.AuthorBy;
import com.cayan.contentservice.model.LikedBy;
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

    private static Logger LOGGER = LoggerFactory.getLogger(ContentService.class);

    @Autowired
    ScienceContextRepository scienceContextRepository;

    @Autowired
    PersonRepository personRepository;

    @Autowired
    SecurityDetails securityDetails;


    @Override
    public void save(ScienceContent scienceContext) {
        scienceContextRepository.save(scienceContext);
    }

    @Override
    public List<AuthorBy> getAllMyContents() {

        try {
           Optional<Person> person =   personRepository.findByUserId(securityDetails.getAuthenticatedUser().getUserId());
           List<AuthorBy> scienceContents = person.get().getAuthorByList();
           return  scienceContents;
        } catch (JsonProcessingException e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }
    }

    @Override
    public List<ScienceContent> getTop5() {
        return scienceContextRepository.findTop5ByOrderByLikeCount();
    }

    @Override
    public Optional<ScienceContent> getContent(Long id) {
        return scienceContextRepository.findById(id);
    }


    @Override
    public Optional<Person> getPersonByUserId(Long userId) {
        return personRepository.findByUserId(userId);
    }

    @Override
    public Person addNewUser(Person person) {
      return personRepository.save(person);
    }
}
