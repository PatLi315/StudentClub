package com.unimelb.swen90007.studentclub.model;

public class RSVP {
    private int studentId;
    private int eventId;

    // Constructor
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
}
