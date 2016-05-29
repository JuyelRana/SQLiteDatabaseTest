package com.rana.juyelrana.sqlitedatabasetest;

/**
 * Created by JuyelRana on 5/22/2016.
 */
public class Students {

    private int id;
    private String name, phone, email, faculty;

    public Students() {
    }

    public Students(String name, String phone, String email, String faculty) {
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.faculty = faculty;
    }

    public Students(int id,String name, String phone, String email, String faculty) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.faculty = faculty;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    @Override
    public String toString() {

        return "Students{" +
                "name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", faculty='" + faculty + '\'' +
                '}';
    }
}
