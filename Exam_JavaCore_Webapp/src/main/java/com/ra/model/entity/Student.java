package com.ra.model.entity;

import org.hibernate.annotations.Type;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "students")
public class Student {
    @Id
    @Column(name = "student_id")
    private Integer student_id;

    @Column(name = "student_name")
    private String student_name;

    @Column(name = "address")
    private String address;

    @Column(name = "birthday")
    private LocalDate birthday;

    @Column(name = "image_url")
    private String image_url;

    @Column(name = "phone_number")
    private String phone_number;

    @Column(name = "sex")
    private Boolean sex;

    public Student() {
    }

    public Student(Integer student_id, String student_name, String address, LocalDate birthday, String image_url, String phone_number, Boolean sex) {
        this.student_id = student_id;
        this.student_name = student_name;
        this.address = address;
        this.birthday = birthday;
        this.image_url = image_url;
        this.phone_number = phone_number;
        this.sex = sex;
    }

    public Integer getStudent_id() {
        return student_id;
    }

    public void setStudent_id(Integer student_id) {
        this.student_id = student_id;
    }

    public String getStudent_name() {
        return student_name;
    }

    public void setStudent_name(String student_name) {
        this.student_name = student_name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public Boolean getSex() {
        return sex;
    }

    public void setSex(Boolean sex) {
        this.sex = sex;
    }
}
