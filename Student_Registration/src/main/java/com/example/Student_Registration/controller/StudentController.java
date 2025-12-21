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
        //400 BAD Request [validation fails]
        if(s.getName()==null || s.getName().isEmpty() ||
           s.getCourse()==null || s.getCourse().isEmpty()){
            //did not check id cause- since it is primitive it will always have value
            //java will convert it to 0, cause int type cannot have Null value

            return ResponseEntity
                        .status(HttpStatus.BAD_REQUEST)
                        .body("Name and Course cannot be empty");
        }

        //409 Conflict [Student with id already exists]
        if(sService.existsById(s.getID())){
            return ResponseEntity
                        .status(HttpStatus.CONFLICT)
                        .body("Student with this ID already exists");
        }

        //201 Created
        return new ResponseEntity<>(sService.addStudent(s),HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteStudent(@PathVariable("id") int id){
        //since in service delete returns true or false we check that
        boolean deleted = sService.deleteStudent(id);

        if(!deleted){
            return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body("Student not found");
        }
        
        return ResponseEntity.status(HttpStatus.OK).body("Student deleted");
    }



}
