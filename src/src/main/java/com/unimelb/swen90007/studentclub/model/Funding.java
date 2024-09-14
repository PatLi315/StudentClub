package com.unimelb.swen90007.studentclub.model;

public class Funding {

    private int id;
    private int clubId;
    private String description;
    private int amount;
    private String status;

    public Funding(int id, int clubId, String description, int amount, String status) {
        this.id = id;
        this.clubId = clubId;
        this.description = description;
        this.amount = amount;
        this.status = status;
    }

    public Funding(int clubId, String description, int amount, String status) {
        this.clubId = clubId;
        this.description = description;
        this.amount = amount;
        this.status = status;
    }

    // Getters and Setters
    public int getId() { return id; }

    public void setId(int id) { this.id = id; }

    public int getClubId() { return clubId; }

    public void setClubId(int clubId) { this.clubId = clubId; }

    public String getDescription() { return description; }

    public void setDescription(String description) { this.description = description; }

    public int getAmount() { return amount; }

    public void setAmount(int amount) { this.amount = amount; }

    public String getStatus() { return status; }

    public void setStatus(String status) { this.status = status; }
}
