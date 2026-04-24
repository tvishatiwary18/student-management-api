package com.tvisha.studentapp.testcontroller;
import jakarta.validation.Valid;
import com.tvisha.studentapp.dto.StudentRequestDTO;
import com.tvisha.studentapp.dto.StudentResponseDTO;
import com.tvisha.studentapp.model.Student;
import com.tvisha.studentapp.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.tvisha.studentapp.service.StudentService;
import java.util.List;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/students")
public class TestController {
    @RestControllerAdvice
    public class GlobalExceptionHandler {

        @ExceptionHandler(MethodArgumentNotValidException.class)
        public ResponseEntity<?> handleValidation(MethodArgumentNotValidException ex) {

            Map<String, String> errors = new HashMap<>();

            ex.getBindingResult().getFieldErrors().forEach(error ->
                    errors.put(error.getField(), error.getDefaultMessage())
            );

            return ResponseEntity.badRequest().body(errors);
        }
    }
    private final StudentService studentService;
    public TestController(StudentService studentService) {
        this.studentService = studentService;
    }
    @Autowired
    private StudentService service;

    @GetMapping
    public List<StudentResponseDTO> getStudents() {
        return service.getStudents();
    }

    @GetMapping("/{id}")
    public Student getStudent(@PathVariable Integer id) {
        return service.getStudentById(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        studentService.delete(id);
        return ResponseEntity.ok("Student deleted successfully");
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Integer id,
                                    @Valid @RequestBody StudentRequestDTO dto) {

        return ResponseEntity.ok(studentService.update(id, dto));
    }
    @PostMapping
    public ResponseEntity<?> save(@Valid @RequestBody StudentRequestDTO dto) {
        return ResponseEntity.ok(studentService.save(dto));
    }
}