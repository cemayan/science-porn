package com.cayan.contentservice.model;

import org.neo4j.ogm.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RelationshipEntity(type = "SHARED_BY")
public class SharedBy {
    @Id
    @GeneratedValue
    private Long id;

    private List<String> roles = new ArrayList<>();

    @StartNode
    private Person person;

    @EndNode
    private ScienceContent scienceContext;

    public SharedBy() {
    }

    public SharedBy(Person person, ScienceContent scienceContext) {
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

    public ScienceContent getMovie() {
        return scienceContext;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }
}