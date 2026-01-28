package com.university.repository;

import com.university.model.Student;
import java.util.Collection;

public interface StudentRepository {

    void save(Student student);

    boolean existsById(String id);

    Collection<Student> findAll();
}
