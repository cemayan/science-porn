package com.cayan.contentservice.model;

import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import java.util.ArrayList;
import java.util.List;

import static org.neo4j.ogm.annotation.Relationship.INCOMING;

@NodeEntity
public class ScienceContent {

    @Id
    @GeneratedValue
    private Long id;

    private String content;
    private String title;

    @Relationship(type = "SHARED_BY", direction = INCOMING)
    private SharedBy sharedBy;

    @Relationship(type = "LIKED_BY", direction = INCOMING)
    private List<LikedBy> likedByList = new ArrayList<>();

    public ScienceContent(String content, String title) {
        this.content = content;
        this.title = title;
    }

    public Long getId() {
        return id;
    }


    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public SharedBy getSharedBy() {
        return sharedBy;
    }

    public void setSharedBy(SharedBy sharedBy) {
        this.sharedBy = sharedBy;
    }

    public List<LikedBy> getLikedByList() {
        return likedByList;
    }

    public void setLikedByList(List<LikedBy> likedByList) {
        this.likedByList = likedByList;
    }
}
