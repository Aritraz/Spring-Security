package com.tutorial.springsecurity.model;

public class Student {

    private String id;
    private String name ;
    private String marks;
    private String rollNo;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMarks() {
        return marks;
    }

    public void setMarks(String marks) {
        this.marks = marks;
    }

    public String getRollNo() {
        return rollNo;
    }

    public Student(String id, String name, String marks, String rollNo) {
        this.id = id;
        this.name = name;
        this.marks = marks;
        this.rollNo = rollNo;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", marks='" + marks + '\'' +
                ", rollNo='" + rollNo + '\'' +
                '}';
    }

    public Student ()
    {

    }

    public void setRollNo(String rollNo) {
        this.rollNo = rollNo;
    }







}
