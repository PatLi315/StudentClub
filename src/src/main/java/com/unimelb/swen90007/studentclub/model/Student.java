package com.unimelb.swen90007.studentclub.model;

public class Student {

    private int id;
    private String name;
    private String email;
    private String password;
    private String role;

    public Student(int id, String name, String email, String password, String role) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public Student(String name, String email, String password, String role) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public Student(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.role = "student";
    }

    public Student(int studentId, String name, String email, String role) {
        this.id = studentId;
        this.name = name;
        this.email = email;
        this.role = role;
    }

    // Getters and Setters
    public int getId() { return id; }

    public void setId(int id) { this.id = id; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }

    public void setEmail(String email) { this.email = email; }

    public String getPassword() { return password; }

    public void setPassword(String password) { this.password = password; }

    public String getRole() { return role; }

    public void setRole(String role) { this.role = role; }
}
