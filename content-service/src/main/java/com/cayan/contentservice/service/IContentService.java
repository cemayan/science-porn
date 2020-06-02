package com.cayan.contentservice.service;


import com.cayan.contentservice.model.AuthorBy;
import com.cayan.contentservice.model.LikedBy;
import com.cayan.contentservice.model.Person;
import com.cayan.contentservice.model.ScienceContent;

import java.util.List;
import java.util.Optional;

public interface IContentService {

    void save(ScienceContent scienceContent);
    List<AuthorBy> getAllMyContents();
    List<ScienceContent> getTop5();
    Optional<ScienceContent> getContent(Long id);
    Optional<Person> getPersonByUserId(Long userId);
    Person addNewUser(Person person);

}
