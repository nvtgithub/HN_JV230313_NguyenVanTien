package com.ra.model.dao;

import com.ra.model.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

@Repository
public class StudentDAOImpl implements StudentDAO {
    @Autowired
    SessionFactory sessionFactory;

    @Override
    public List<Student> getAll() {
        List<Student> studentList = new ArrayList<>();
        Session session = sessionFactory.openSession();
        try {
            studentList = session.createQuery("from Student ", Student.class).list();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return studentList;
    }

    @Override
    public Boolean saveOrUpdate(Student student) {
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            session.saveOrUpdate(student);
            session.getTransaction().commit();
            return true;
        } catch (Exception e) {
            session.getTransaction().rollback();
        } finally {
            session.close();
        }

        return false;
    }

    @Override
    public Student findById(Integer id) {
        Session session = sessionFactory.openSession();
        try {
            return session.get(Student.class, id);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }

        return null;
    }

    @Override
    public void delete(Integer id) {
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            session.delete(findById(id));
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
    }

    @Override
    public List<Student> search(String name) {
        List<Student> studentList = new ArrayList<>();
        Session session = sessionFactory.openSession();
        try {
            String hql = "from Student where student_name like :keyword";
            Query query = session.createQuery(hql);
            query.setParameter("keyword", "%" + name + "%");

            studentList = query.getResultList();
        } catch (Exception e) {
            session.getTransaction().rollback();
        } finally {
            session.close();
        }
        return studentList;
    }
}
