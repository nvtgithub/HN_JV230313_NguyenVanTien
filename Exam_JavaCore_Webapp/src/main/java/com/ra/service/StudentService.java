package com.ra.service;

import com.ra.model.entity.Student;
import org.springframework.stereotype.Service;

import java.util.List;
public interface StudentService {
    List<Student> getAll();

    Boolean saveOrUpdate(Student student);

    Student findById(Integer id);

    void delete(Integer id);
    List<Student> search(String name);

}
