package com.unimelb.swen90007.studentclub.model;

import com.unimelb.swen90007.studentclub.enums.PersonEnum;

public class RegularStudent extends Person {

    public RegularStudent(int id, String name, String email, String password) {
        super (id, name, email, password);
    }

    public RegularStudent(String name, String email, String password) {
        super (name, email, password);
    }


    public RegularStudent(int studentId, String name, String email) {
        super (studentId, name, email, null);
    }

    @Override
    public String getRole() { return PersonEnum.STUDENT.getRoleName(); }
}
