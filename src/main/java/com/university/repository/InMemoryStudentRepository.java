package com.university.repository;

import com.university.model.Student;
import java.util.ArrayList;
import java.util.List;

public class InMemoryStudentRepository implements StudentRepository {

    private final List<Student> students = new ArrayList<>();

    @Override
    public void add(Student student) {
        students.add(student);
    }

    @Override
    public List<Student> findAll() {
        return students;
    }

    @Override
    public boolean existsById(int id) {
        return students.stream().anyMatch(s -> s.getId() == id);
    }
}
