package com.cayan.common.dto;


import com.cayan.common.enumaration.ScienceContentRelationship;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.time.LocalDateTime;

public class ScienceContentDTO {

    private Long id;
    private String title;
    private String content;
    private Long userId;
    private LocalDateTime createdAt;

    private String image;

    private ScienceContentRelationship relationship;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public ScienceContentRelationship getRelationship() {
        return relationship;
    }

    public void setRelationship(ScienceContentRelationship relationship) {
        this.relationship = relationship;
    }
}
