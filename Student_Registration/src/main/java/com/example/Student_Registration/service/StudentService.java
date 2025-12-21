package com.example.Student_Registration.service;
import com.example.Student_Registration.model.Student;

import java.util.ArrayList;
import java.util.List;

public class StudentService {
    private List<Student> Students = new ArrayList<>();

    //get
    public  List<Student> getAllStudents(){
        return Students;
    }
    public Student getStudentByID(int id){
        return Students.stream()
            .filter(s->s.getID()==id)
            .findFirst()
            .orElse(null);
    }

    //adding a student to Students list
    public Student addStudent(Student s){
        Students.add(s);
        return s;
    }

    //deleting a student
    public boolean deleteStudent(int id){
        return Students.removeIf(e->e.getID()==id);
    }

    //Checking if exists
    public boolean existsById(int id){
        return Students.stream()
            .anyMatch(s->s.getID()==id);
    }

    
}
