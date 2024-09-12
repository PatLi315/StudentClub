package com.unimelb.swen90007.studentclub.model;

import java.sql.Timestamp;

public class Club {
    private int id;
    private String name;
    private Timestamp createdAt;  // Timestamp for when the club was created

    // Constructor with all fields
    public Club(int id, String name, Timestamp createdAt) {
        this.id = id;
        this.name = name;
        this.createdAt = createdAt;
    }

    // Constructor without ID (for inserting new clubs)
    public Club(String name) {
        this.name = name;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }
}
