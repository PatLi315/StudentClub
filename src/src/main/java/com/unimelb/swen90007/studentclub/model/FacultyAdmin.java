package com.unimelb.swen90007.studentclub.model;

public class FacultyAdmin extends Person{
    public static final String ADMIN = "admin";

    public FacultyAdmin(int id, String name, String email, String password) {
        super(id, name, email, password);
    }

    public FacultyAdmin(String name, String email, String password) {
        super(name, email, password);
    }

    public FacultyAdmin(int id, String name, String email) {
        super(id, name, email);
    }

    @Override
    public String getRole() {
        return ADMIN;
    }
}
