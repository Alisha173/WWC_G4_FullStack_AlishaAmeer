package com.example.Student_Registration.model;

public class Student {
    private int id;
    private String name;
    private String course;

    //Constructors
    public Student(){}
    public Student(int id, String name, String course){
        this.id=id;
        this.name=name;
        this.course=course;
    }

    public int getID(){
        return id;
    }

    public void setID(int id){
        this.id= id;
    }

    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name=name;
    }

    public String getCourse(){
        return course;
    }

    public void setCourse(String course){
        this.course=course;
    }

}
