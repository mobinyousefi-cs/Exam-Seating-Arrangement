package com.mobinyousefi.cs.examseating.model;

public class Student {

    private int id;
    private String rollNumber;
    private String name;
    private String program;
    private int semester;

    public Student() {
    }

    public Student(int id, String rollNumber, String name, String program, int semester) {
        this.id = id;
        this.rollNumber = rollNumber;
        this.name = name;
        this.program = program;
        this.semester = semester;
    }

    public Student(String rollNumber, String name, String program, int semester) {
        this(0, rollNumber, name, program, semester);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRollNumber() {
        return rollNumber;
    }

    public void setRollNumber(String rollNumber) {
        this.rollNumber = rollNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProgram() {
        return program;
    }

    public void setProgram(String program) {
        this.program = program;
    }

    public int getSemester() {
        return semester;
    }

    public void setSemester(int semester) {
        this.semester = semester;
    }
}
