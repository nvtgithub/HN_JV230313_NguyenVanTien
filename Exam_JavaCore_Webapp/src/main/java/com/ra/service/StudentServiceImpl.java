package com.ra.service;

import com.ra.model.dao.StudentDAO;
import com.ra.model.entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class StudentServiceImpl implements StudentService{
    @Autowired
    StudentDAO studentDAO;
    @Override
    public List<Student> getAll() {
        return studentDAO.getAll();
    }

    @Override
    public Boolean saveOrUpdate(Student student) {
        return studentDAO.saveOrUpdate(student);
    }

    @Override
    public Student findById(Integer id) {
        return studentDAO.findById(id);
    }

    @Override
    public void delete(Integer id) {
        studentDAO.delete(id);
    }

    @Override
    public List<Student> search(String name) {
        return studentDAO.search(name);
    }
}
