package com.unimelb.swen90007.studentclub.model;

import java.sql.Timestamp;

public class RSVP {
    private int studentId;
    private int eventId;
    private Timestamp createdAt;  // Timestamp for when the RSVP was created

    // Constructor with all fields
    public RSVP(int studentId, int eventId, Timestamp createdAt) {
        this.studentId = studentId;
        this.eventId = eventId;
        this.createdAt = createdAt;
    }

    // Constructor without createdAt (for new RSVPs)
    public RSVP(int studentId, int eventId) {
        this.studentId = studentId;
        this.eventId = eventId;
    }

    // Getters and Setters
    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public int getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }
}
