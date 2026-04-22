package com.tvisha.studentapp.testcontroller;

import com.tvisha.studentapp.model.Student;
import com.tvisha.studentapp.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
public class TestController {

    @Autowired
    private StudentService service;

    @GetMapping
    public List<Student> getStudents() {
        return service.getStudents();
    }

    @PostMapping
    public Student addStudent(@RequestBody Student student) {
        return service.addStudent(student);
    }

    @GetMapping("/{name}")
    public Student getStudent(@PathVariable String name) {
        return service.getStudentByName(name);
    }

    @DeleteMapping("/{name}")
    public String deleteStudent(@PathVariable String name) {
        return service.deleteStudent(name);
    }

    @PutMapping("/{name}")
    public String updateStudent(@PathVariable String name,
                                @RequestBody Student student) {
        return service.updateStudent(name, student);
    }
}