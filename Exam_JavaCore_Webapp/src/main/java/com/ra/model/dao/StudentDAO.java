package com.ra.model.dao;

import com.ra.model.entity.Student;

import java.util.List;

public interface StudentDAO {
    List<Student> getAll();

    Boolean saveOrUpdate(Student student);

    Student findById(Integer id);

    void delete(Integer id);

    List<Student> search(String name);

}
