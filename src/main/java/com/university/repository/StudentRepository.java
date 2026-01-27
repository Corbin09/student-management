package com.university.repository;

import com.university.model.Student;
import java.util.List;

public interface StudentRepository {

    void add(Student student);

    List<Student> findAll();

    boolean existsById(int id);
}
