package com.unimelb.swen90007.studentclub.model;

public class RSVP {

    private int id;
    private int studentId;
    private int eventId;
    private int tickets;

    public RSVP(int studentId, int eventId, int tickets) {
        this.studentId = studentId;
        this.eventId = eventId;
        this.tickets = tickets;
    }

    public RSVP(int id, int studentId, int eventId, int tickets) {
        this.id = id;
        this.studentId = studentId;
        this.eventId = eventId;
        this.tickets = tickets;
    }

    // Getters and Setters
    public int getId() { return id; }

    public void setId(int id) { this.id = id; }

    public int getStudentId() { return studentId; }

    public void setStudentId(int studentId) { this.studentId = studentId; }

    public int getEventId() { return eventId; }

    public void setEventId(int eventId) { this.eventId = eventId; }

    public int getTickets() { return tickets; }

    public void setTickets(int tickets) { this.tickets = tickets; }
}
