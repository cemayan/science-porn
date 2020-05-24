package com.cayan.contentservice.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.neo4j.ogm.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RelationshipEntity(type = "AUTHOR_BY")
public class AuthorBy {
    @Id
    @GeneratedValue
    @JsonIgnore
    private Long id;

    @JsonIgnore
    private List<String> roles = new ArrayList<>();

    @StartNode
    @JsonIgnoreProperties({"authorByList", "likedList"})
    private Person person;

    @EndNode
    @JsonIgnoreProperties({"authorBy", "likedByList"})
    private ScienceContent scienceContent;

    public AuthorBy() {
    }

    public AuthorBy(Person person, ScienceContent scienceContent) {
        this.person = person;
        this.scienceContent = scienceContent;
    }

    public Long getId() {
        return id;
    }

    public List<String> getRoles() { return roles; }

    public Person getPerson() {
        return person;
    }


    public ScienceContent getScienceContent() {
        return scienceContent;
    }

    public void setScienceContent(ScienceContent scienceContent) {
        this.scienceContent = scienceContent;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }
}