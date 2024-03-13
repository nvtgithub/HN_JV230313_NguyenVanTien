package com.ra.controller;

import com.ra.model.entity.Student;
import com.ra.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@PropertySource("classpath:application.properties")
public class StudentController {
    @Value("${path}")
    private String path;
    @Autowired
    private StudentService studentService;

    @GetMapping("/student")
    public String index(Model model,@RequestParam(value = "keyword",required = false) String keyword) {
        List<Student> studentList = new ArrayList<>();
        if(keyword!= null){
            studentList = studentService.search(keyword);
            model.addAttribute("keyword",keyword);
            return "student/index";
        } else {
            studentList = studentService.getAll();
            model.addAttribute("studentList", studentList);
            return "student/index";
        }
    }

    @GetMapping("/add-student")
    public String add(Model model, Student student) {
        model.addAttribute("student", student);
        return "student/add";
    }

    // new
    @PostMapping("/add-student")
    public String create(@ModelAttribute("student") Student student,
                         @RequestParam(value = "fileImage", required = false) MultipartFile file) {
        String fileName = file.getOriginalFilename();
        File destination = new File(path + "/" + fileName);
        try {
            Files.write(destination.toPath(), file.getBytes(), StandardOpenOption.CREATE);
            student.setImage_url(fileName);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        studentService.saveOrUpdate(student);
        return "redirect:/student";
    }


    // edit
    @GetMapping("edit-student/{id}")
    public String edit(@PathVariable("id") Integer id, Model model) {
        Student student = studentService.findById(id);
        model.addAttribute("student", student);
        return "student/edit";
    }

    @PostMapping("/edit-student/{id}")
    public String update(@PathVariable("id") Integer id,
                         @ModelAttribute("student") Student student,
                         @RequestParam(value = "fileImage", required = false) MultipartFile file
    ) {
        String fileName = file.getOriginalFilename();
        if (!fileName.isEmpty()) {
            File destination = new File(path + "/" + fileName);
            try {
                Files.write(destination.toPath(), file.getBytes(), StandardOpenOption.CREATE);
                student.setImage_url(fileName);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        if (studentService.saveOrUpdate(student)) {
            return "redirect:/student";
        } else
            return "redirect:/edit-student/" + id;
    }

    //delete
    @GetMapping("/delete-student/{id}")
    public String delete(@PathVariable("id") Integer id){
        studentService.delete(id);
        return "redirect:/student";
    }

//    @InitBinder
//    public void initBinder(WebDataBinder binder){
//        binder.registerCustomEditor(       Date.class,
//                new CustomDateEditor(new SimpleDateFormat("dd/MM/yyyy"), true, 10));
//    }
}
