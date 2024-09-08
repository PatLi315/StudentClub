package com.unimelb.swen90007.studentclub.model;

/**
 * Author: Zeqian Li
 */

public class Club {
    private int id;
    private String name;

    // Constructor
    public Club(int id, String name) {
        this.id = id;
        this.name = name;
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
}
