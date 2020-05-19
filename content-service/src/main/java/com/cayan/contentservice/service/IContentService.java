package com.cayan.contentservice.service;


import com.cayan.contentservice.model.ScienceContent;

public interface IContentService {

    void save(ScienceContent scienceContent);
    Iterable<ScienceContent> getAll();
}
