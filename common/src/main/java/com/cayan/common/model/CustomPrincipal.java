package com.cayan.common.model;

import java.io.Serializable;

public class CustomPrincipal implements Serializable {

    private static final long serialVersionUID = 1L;

    private String username;
    private String email;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}