package com.cayan.contentservice.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.neo4j.ogm.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RelationshipEntity(type = "LIKED_BY")
public class LikedBy {
    @Id
    @GeneratedValue
    private Long id;

    private List<String> roles = new ArrayList<>();

    @StartNode
    @JsonIgnoreProperties({"authorByList", "likedList"})
    private Person person;

    @EndNode
    @JsonIgnore
    private ScienceContent scienceContext;

    public LikedBy() {
    }

    public LikedBy(Person person, ScienceContent scienceContext) {
        this.person = person;
        this.scienceContext = scienceContext;
    }

    public Long getId() {
        return id;
    }

    public List<String> getRoles() { return roles; }

    public Person getPerson() {
        return person;
    }

    public ScienceContent getScienceContext() {
        return scienceContext;
    }

    public void setScienceContext(ScienceContent scienceContext) {
        this.scienceContext = scienceContext;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }
}