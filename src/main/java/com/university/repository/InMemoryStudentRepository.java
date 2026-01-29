package com.university.repository;

import com.university.model.Student;

import java.util.*;

public class InMemoryStudentRepository implements StudentRepository {

    private final Map<String, Student> studentMap =
            Collections.synchronizedMap(new HashMap<>());

    @Override
    public void save(Student student) {
        studentMap.put(student.getId(), student);
    }

    @Override
    public boolean existsById(String id) {
        return studentMap.containsKey(id);
    }

    @Override
    public List<Student> findAll() {
        synchronized (studentMap) {
            return new ArrayList<>(studentMap.values());
        }
    }


    @Override
    public void deleteById(String id) {
        studentMap.remove(id);
    }
}
