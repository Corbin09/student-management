package com.university.service;

import com.university.exception.InvalidStudentDataException;
import com.university.model.Student;
import com.university.repository.StudentRepository;

import java.util.List;
import java.util.regex.Pattern;

public class StudentService {

    private final StudentRepository repository;

    private static final Pattern EMAIL_PATTERN =
            Pattern.compile("^[A-Za-z0-9+_.-]+@(.+)$");

    public StudentService(StudentRepository repository) {
        this.repository = repository;
    }

    public void addStudent(String id, String name, String email, double gpa) {

        if (repository.existsById(id)) {
            throw new InvalidStudentDataException("Student ID already exists.");
        }

        if (!EMAIL_PATTERN.matcher(email).matches()) {
            throw new InvalidStudentDataException("Invalid email format.");
        }

        if (gpa < 0 || gpa > 10) {
            throw new InvalidStudentDataException("GPA must be between 0 and 10.");
        }

        repository.save(new Student(id, name, email, gpa));
    }

    // 🔹 Stream: filter
    public List<Student> searchStudents(String name) {
        return repository.findAll()
                .stream()
                .filter(s -> s.getName().toLowerCase().contains(name.toLowerCase()))
                .toList();
    }

    // 🔹 Stream: sorted
    public List<Student> getTopStudents(int n) {
        return repository.findAll()
                .stream()
                .sorted((a, b) -> Double.compare(b.getGpa(), a.getGpa()))
                .limit(n)
                .toList();
    }

    // 🔹 Stream: mapToDouble
    public double getAverageGPA() {
        return repository.findAll()
                .stream()
                .mapToDouble(Student::getGpa)
                .average()
                .orElse(0.0);
    }
}
