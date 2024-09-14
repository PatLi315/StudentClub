package com.unimelb.swen90007.studentclub.model;

import java.util.Date;

public class Event {

    private int id;
    private String title;
    private String description;
    private Date eventDate;
    private int clubId;
    private int capacity;  // Added capacity
    private String venue;  // Added venue

    public Event(String title, String description, Date eventDate, int clubId, int capacity, String venue) {
        this.title = title;
        this.description = description;
        this.eventDate = eventDate;
        this.clubId = clubId;
        this.capacity = capacity;
        this.venue = venue;
    }

    public Event(int id, String title, String description, Date eventDate, int clubId, int capacity, String venue) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.eventDate = eventDate;
        this.clubId = clubId;
        this.capacity = capacity;
        this.venue = venue;
    }

    // Getters and Setters
    public int getId() { return id; }

    public void setId(int id) { this.id = id; }

    public String getTitle() { return title; }

    public void setTitle(String title) { this.title = title; }

    public String getDescription() { return description; }

    public void setDescription(String description) { this.description = description; }

    public java.sql.Date getEventDate() { return (java.sql.Date) eventDate; }

    public void setEventDate(Date eventDate) { this.eventDate = eventDate; }

    public int getClubId() { return clubId; }

    public void setClubId(int clubId) { this.clubId = clubId; }

    public int getCapacity() { return capacity; }

    public void setCapacity(int capacity) { this.capacity = capacity; }

    public String getVenue() { return venue; }

    public void setVenue(String venue) { this.venue = venue; }

}
