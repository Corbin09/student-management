package com.university.repository;

import com.university.model.Student;
import java.util.List;

public interface StudentRepository {

    void save(Student student);

    boolean existsById(String id);

    List<Student> findAll();

    void deleteById(String id);
}
