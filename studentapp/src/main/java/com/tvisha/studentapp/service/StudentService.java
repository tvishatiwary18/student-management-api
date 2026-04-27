package com.tvisha.studentapp.service;

import com.tvisha.studentapp.dto.StudentRequestDTO;
import com.tvisha.studentapp.dto.StudentResponseDTO;
import com.tvisha.studentapp.exception.StudentNotFoundException;
import com.tvisha.studentapp.model.Student;
import com.tvisha.studentapp.repository.StudentRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentService {

    private static final Logger log =
            LoggerFactory.getLogger(StudentService.class);

    @Autowired
    private StudentRepository studentRepo;

    public List<StudentResponseDTO> getStudents() {
        log.info("Fetching all students");

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
        log.info("Adding student {}", student.getName());
        return studentRepo.save(student);
    }

   
    public boolean deleteStudent(Integer id) {
        log.info("Deleting student by id {}", id);

        if (studentRepo.existsById(id)) {
            studentRepo.deleteById(id);
            return true;
        }

        log.warn("Student not found with id {}", id);
        return false;
    }

    public StudentResponseDTO update(Integer id, StudentRequestDTO dto) {
        log.info("Updating student with id {}", id);

        Student existing = studentRepo.findById(id)
                .orElseThrow(() -> new StudentNotFoundException("Student not found"));

        existing.setName(dto.getName());
        existing.setAge(dto.getAge());

        Student updated = studentRepo.save(existing);

        StudentResponseDTO response = new StudentResponseDTO();
        response.setId(updated.getId());
        response.setName(updated.getName());
        response.setAge(updated.getAge());

        log.info("Student updated successfully {}", updated.getId());

        return response;
    }

    public StudentResponseDTO save(StudentRequestDTO dto) {
        log.info("Creating student {}", dto.getName());

        Student student = new Student();
        student.setName(dto.getName());
        student.setAge(dto.getAge());

        Student saved = studentRepo.save(student);

        StudentResponseDTO response = new StudentResponseDTO();
        response.setId(saved.getId());
        response.setName(saved.getName());
        response.setAge(saved.getAge());

        log.info("Student created successfully {}", saved.getId());

        return response;
    }

    public void delete(Integer id) {
        log.info("Deleting student with id {}", id);

        Student existing = studentRepo.findById(id)
                .orElseThrow(() -> new StudentNotFoundException("Student not found"));

        studentRepo.delete(existing);

        log.info("Student deleted successfully {}", id);
    }

    public Page<StudentResponseDTO> getAll(int page, int size) {
        log.info("Fetching students page {} size {}", page, size);

        Pageable pageable = PageRequest.of(page, size);
        Page<Student> students = studentRepo.findAll(pageable);

        return students.map(student -> {
            StudentResponseDTO dto = new StudentResponseDTO();
            dto.setId(student.getId());
            dto.setName(student.getName());
            dto.setAge(student.getAge());
            return dto;
        });
    }

    public List<StudentResponseDTO> searchByName(String name) {
        log.info("Searching student by name {}", name);

        List<Student> students = studentRepo.findByName(name);

        return students.stream().map(student -> {
            StudentResponseDTO dto = new StudentResponseDTO();
            dto.setId(student.getId());
            dto.setName(student.getName());
            dto.setAge(student.getAge());
            return dto;
        }).toList();
    }
    public StudentResponseDTO getById(Integer id) {

        log.info("Fetching student by id {}", id);

        Student student = studentRepo.findById(id)
                .orElseThrow(() -> new StudentNotFoundException("Student not found"));

        StudentResponseDTO dto = new StudentResponseDTO();
        dto.setId(student.getId());
        dto.setName(student.getName());
        dto.setAge(student.getAge());

        return dto;
    }
}