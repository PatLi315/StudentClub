package com.unimelb.swen90007.studentclub.enums;
/**
 * person role enum
 */
public enum PersonEnum {

    STUDENT("student"),
    ADMIN("admin");

    private final String roleName;


    PersonEnum(String student) {
        this.roleName = student;
    }


    public String getRoleName() {
        return roleName;
    }
}
