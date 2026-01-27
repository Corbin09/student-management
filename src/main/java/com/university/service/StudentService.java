package com.university.service;

import com.university.model.Student;
import com.university.repository.StudentRepository;

import java.util.List;

public class StudentService {

    private final StudentRepository repository;

    public StudentService(StudentRepository repository) {
        this.repository = repository;
    }

    public void addStudent(int id, String name, String email, double gpa) {
        if (repository.existsById(id)) {
            throw new IllegalArgumentException("Student ID must be unique.");
        }

        if (gpa < 0 || gpa > 10) {
            throw new IllegalArgumentException("GPA must be between 0 and 10.");
        }

        Student student = new Student(id, name, email, gpa);
        repository.add(student);
    }

    public List<Student> listStudents() {
        return repository.findAll();
    }
}
