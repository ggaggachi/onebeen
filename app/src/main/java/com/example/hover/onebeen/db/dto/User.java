package com.example.hover.onebeen.db.dto;

public class User {
    private String id;
    private String name;

    public User(String id, String fullName) {
        this.id = id;
        this.name = fullName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
