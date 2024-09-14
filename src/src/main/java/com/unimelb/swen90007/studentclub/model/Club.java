package com.unimelb.swen90007.studentclub.model;

public class Club {

    private int id;
    private String name;

    public Club(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Club(String name) {
        this.name = name;
    }

    // Getters and Setters
    public int getId() { return id; }

    public void setId(int id) { this.id = id; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }
}
