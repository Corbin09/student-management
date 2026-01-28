package com.university.repository;

import com.university.model.Student;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class InMemoryStudentRepository implements StudentRepository {

    private final Map<String, Student> studentMap = new HashMap<>();

    @Override
    public void save(Student student) {
        studentMap.put(student.getId(), student);
    }

    @Override
    public boolean existsById(String id) {
        return studentMap.containsKey(id);
    }

    @Override
    public Collection<Student> findAll() {
        return studentMap.values();
    }
}
