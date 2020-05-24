package com.cayan.contentservice.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import java.util.ArrayList;
import java.util.List;

import static org.neo4j.ogm.annotation.Relationship.INCOMING;
import static org.neo4j.ogm.annotation.Relationship.OUTGOING;

@NodeEntity
public class Person {

    @Id
    @GeneratedValue
    private Long id;

    private Long userId;

    @Relationship(type = "AUTHOR_BY")
    @JsonIgnoreProperties("person")
    private List<AuthorBy>  authorByList =  new ArrayList<>();

    @Relationship(type = "LIKED_BY")
    @JsonIgnoreProperties({"authorBy", "likedByList"})
    private List<ScienceContent> likedList = new ArrayList<>();

    public Person(Long userId) {
        this.userId = userId;
    }

    public Long getId() {
        return id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public List<AuthorBy> getAuthorByList() {
        return authorByList;
    }

    public void setAuthorByList(List<AuthorBy> authorByList) {
        this.authorByList = authorByList;
    }

    public List<ScienceContent> getLikedList() {
        return likedList;
    }

    public void setLikedList(List<ScienceContent> likedList) {
        this.likedList = likedList;
    }
}
