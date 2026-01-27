package com.university.model;

public class Student {
    private int id;
    private String name;
    private String email;
    private double gpa;

    public Student(int id, String name, String email, double gpa) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.gpa = gpa;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public double getGpa() {
        return gpa;
    }

    @Override
    public String toString() {
        return String.format(
                "ID: %d | Name: %s | Email: %s | GPA: %.2f",
                id, name, email, gpa
        );
    }
}