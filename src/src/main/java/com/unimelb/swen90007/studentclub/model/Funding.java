package com.unimelb.swen90007.studentclub.model;

public class Funding {

    private int id;
    private String description;
    private Integer amountRequested;
    private String status;  // Draft, Submitted, In Review, Approved, Rejected
    private int clubId;

    public Funding(int id, String description, Integer amountRequested, String status, int clubId) {
        this.id = id;
        this.description = description;
        this.amountRequested = amountRequested;
        this.status = status;
        this.clubId = clubId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getAmountRequested() {
        return amountRequested;
    }

    public void setAmountRequested(Integer amountRequested) {
        this.amountRequested = amountRequested;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getClubId() {
        return clubId;
    }

    public void setClubId(int clubId) {
        this.clubId = clubId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
