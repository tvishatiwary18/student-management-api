package com.tvisha.studentapp.service;
import com.tvisha.studentapp.dto.StudentRequestDTO;
import com.tvisha.studentapp.dto.StudentResponseDTO;
import com.tvisha.studentapp.model.Student;
import com.tvisha.studentapp.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepo;

    public List<StudentResponseDTO> getStudents() {
        List<Student> students = studentRepo.findAll();

        List<StudentResponseDTO> response = new ArrayList<>();

        for (Student s : students) {
            StudentResponseDTO dto = new StudentResponseDTO();
            dto.setId(s.getId());
            dto.setName(s.getName());
            dto.setAge(s.getAge());

            response.add(dto);
        }

        return response;
    }

    public Student addStudent(Student student) {
        return studentRepo.save(student);
    }

    public Student getStudentById(Integer id) {
            return studentRepo.findById(id).orElse(null);
    }

    public boolean deleteStudent(Integer id) {
        if (studentRepo.existsById(id)) {
            studentRepo.deleteById(id);
            return true;
        }
        return false;
    }

    public StudentResponseDTO update(Integer id, StudentRequestDTO dto) {

        Student existing = studentRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found"));

        existing.setName(dto.getName());
        existing.setAge(dto.getAge());

        Student updated = studentRepo.save(existing);

        StudentResponseDTO response = new StudentResponseDTO();
        response.setId(updated.getId());
        response.setName(updated.getName());
        response.setAge(updated.getAge());

        return response;
    }
    public StudentResponseDTO save(StudentRequestDTO dto) {

        Student student = new Student();
        student.setName(dto.getName());
        student.setAge(dto.getAge());

        Student saved = studentRepo.save(student);

        StudentResponseDTO response = new StudentResponseDTO();
        response.setId(saved.getId());
        response.setName(saved.getName());
        response.setAge(saved.getAge());

        return response;
    }
    public void delete(Integer id) {

        Student existing = studentRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found"));

        studentRepo.delete(existing);
    }

}