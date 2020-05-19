package com.cayan.contentservice.service.impl;

import com.cayan.contentservice.model.Person;
import com.cayan.contentservice.model.ScienceContent;
import com.cayan.contentservice.repository.PersonRepository;
import com.cayan.contentservice.repository.ScienceContextRepository;
import com.cayan.contentservice.service.IContentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ContentService implements IContentService {

    @Autowired
    ScienceContextRepository scienceContextRepository;

    @Autowired
    PersonRepository personRepository;

    private static Logger LOGGER = LoggerFactory.getLogger(ContentService.class);

    @Override
    public void save(ScienceContent scienceContext) {
        scienceContextRepository.save(scienceContext);
    }

    @Override
    public Iterable<ScienceContent> getAll() {
        return null;
    }

    public Optional<ScienceContent> getContent(Long id) {
        return scienceContextRepository.findById(id);
    }

    public Optional<Person> getPersonByUserId(Long userId) {
        return personRepository.findByUserId(userId);
    }
}
