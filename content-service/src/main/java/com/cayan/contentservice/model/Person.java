package com.cayan.contentservice.model;

import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import java.util.ArrayList;
import java.util.List;

@NodeEntity
public class Person {

    @Id
    @GeneratedValue
    private Long id;

    private Long userId;

    @Relationship(type = "SHARED_BY")
    private ScienceContent  sharedBy;

    @Relationship(type = "LIKED_BY")
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

    public ScienceContent getSharedBy() {
        return sharedBy;
    }

    public void setSharedBy(ScienceContent sharedBy) {
        this.sharedBy = sharedBy;
    }

    public List<ScienceContent> getLikedList() {
        return likedList;
    }

    public void setLikedList(List<ScienceContent> likedList) {
        this.likedList = likedList;
    }
}
