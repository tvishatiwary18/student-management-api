package com.tvisha.studentapp.service;

import com.tvisha.studentapp.model.Student;
import com.tvisha.studentapp.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepo;

    public List<Student> getStudents() {
        return studentRepo.findAll();
    }

    public Student addStudent(Student student) {
        return studentRepo.save(student);
    }

    public Student getStudentByName(String name) {
        List<Student> students = studentRepo.findAll();

        for (Student s : students) {
            if (s.getName().equalsIgnoreCase(name)) {
                return s;
            }
        }
        return null;
    }

    public String deleteStudent(String name) {
        List<Student> students = studentRepo.findAll();

        for (Student s : students) {
            if (s.getName().equalsIgnoreCase(name)) {
                studentRepo.delete(s);
                return "Deleted";
            }
        }
        return "Not found";
    }

    public String updateStudent(String name, Student updatedStudent) {
        List<Student> students = studentRepo.findAll();

        for (Student s : students) {
            if (s.getName().equalsIgnoreCase(name)) {
                s.setName(updatedStudent.getName());
                s.setAge(updatedStudent.getAge());
                studentRepo.save(s);
                return "Updated";
            }
        }
        return "Not found";
    }
}