package com.rana.juyelrana.sqlitedatabasetest;

/**
 * Created by JuyelRana on 5/22/2016.
 */
public class StudentLists {

    String name,phone,email,faculty;

    public StudentLists() {
    }

    public StudentLists(String name, String phone, String email, String faculty) {
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.faculty = faculty;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFaculty() {
        return faculty;
    }

    public void setFaculty(String faculty) {
        this.faculty = faculty;
    }
}
