package com.cayan.contentservice.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.neo4j.ogm.annotation.Relationship.INCOMING;

@NodeEntity
public class ScienceContent {

    @Id
    @GeneratedValue
    private Long id;

    private String title;
    private String content;
    private Long userId;

    private LocalDateTime createdAt;

    private String image;

    @Relationship(type = "AUTHOR_BY", direction = INCOMING)
    @JsonIgnoreProperties("scienceContent")
    private AuthorBy authorBy;

    @Relationship(type = "LIKED_BY", direction = INCOMING)
    @JsonIgnoreProperties({"authorByList", "likedList"})
    private List<LikedBy> likedByList = new ArrayList<>();

    public ScienceContent( String title,String content, String image, Long userId) {
        this.title = title;
        this.content = content;
        this.image = image;
        this.userId = userId;
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public List<LikedBy> getLikedByList() {
        return likedByList;
    }

    public void setLikedByList(List<LikedBy> likedByList) {
        this.likedByList = likedByList;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public AuthorBy getAuthorBy() {
        return authorBy;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setAuthorBy(AuthorBy authorBy) {
        this.authorBy = authorBy;
    }
}
