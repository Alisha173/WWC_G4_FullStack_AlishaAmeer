package com.example.Student_Registration.controller;
import com.example.Student_Registration.model.Student;
import com.example.Student_Registration.service.StudentService;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Students")
public class StudentController {
    
    private final StudentService sService;

    public StudentController(StudentService studentservice){
        this.sService= studentservice;
    }

    @GetMapping
    public ResponseEntity<List<Student>> getAllStudents(){
        return ResponseEntity.ok(sService.getAllStudents());
    }

    @GetMapping("/{id}")
    //using question mark ttp
    public ResponseEntity<?> getStudentByID(@PathVariable("id") int id){
        Student student = sService.getStudentByID(id);
        if (student == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Student not found");
        }
        return ResponseEntity.ok(student);
    }

    @PostMapping("/addStudent")
    public ResponseEntity<?> addStudent(@RequestBody Student s){
        return new ResponseEntity<>(sService.addStudent(s),HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteStudent(@PathVariable("id") int id){
        return ResponseEntity.ok(sService.deleteStudent(id));
    }



}
