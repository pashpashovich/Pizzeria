package com.example.ssopfa.entities;


import java.io.Serializable;

public class User implements Serializable {
    private String login;
    private String password;
    private boolean hasAccess;


    public User(String login, String password, boolean hasAccess) {
        this.login = login;
        this.password = password;
        this.hasAccess = hasAccess;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public boolean isHasAccess() {
        return hasAccess;
    }

    public void setHasAccess(boolean hasAccess) {
        this.hasAccess = hasAccess;
    }
}

