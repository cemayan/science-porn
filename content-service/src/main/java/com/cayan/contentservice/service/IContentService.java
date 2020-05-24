package com.cayan.contentservice.service;


import com.cayan.contentservice.model.AuthorBy;
import com.cayan.contentservice.model.ScienceContent;

import java.util.List;

public interface IContentService {

    void save(ScienceContent scienceContent);
    List<AuthorBy> getAll();
}
