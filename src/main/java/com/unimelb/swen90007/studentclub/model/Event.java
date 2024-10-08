package com.unimelb.swen90007.studentclub.model;

import java.sql.Date;

public class Event {
    private int id;
    private String title;
    private String description;
    private Date eventDate;
    private int clubId;

    // Constructor with all fields
    public Event(int id, String title, String description, Date eventDate, int clubId) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.eventDate = eventDate;
        this.clubId = clubId;
    }

    // Constructor without ID (for inserting new events)
    public Event(String title, String description, Date eventDate, int clubId) {
        this.title = title;
        this.description = description;
        this.eventDate = eventDate;
        this.clubId = clubId;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getEventDate() {
        return eventDate;
    }

    public void setEventDate(Date eventDate) {
        this.eventDate = eventDate;
    }

    public int getClubId() {
        return clubId;
    }

    public void setClubId(int clubId) {
        this.clubId = clubId;
    }
}
