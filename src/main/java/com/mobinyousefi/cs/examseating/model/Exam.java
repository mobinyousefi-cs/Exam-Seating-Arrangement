package com.mobinyousefi.cs.examseating.model;

import java.time.LocalDate;
import java.time.LocalTime;

public class Exam {

    private int id;
    private String courseCode;
    private String courseName;
    private LocalDate examDate;
    private LocalTime startTime;

    public Exam() {
    }

    public Exam(int id, String courseCode, String courseName, LocalDate examDate, LocalTime startTime) {
        this.id = id;
        this.courseCode = courseCode;
        this.courseName = courseName;
        this.examDate = examDate;
        this.startTime = startTime;
    }

    public Exam(String courseCode, String courseName, LocalDate examDate, LocalTime startTime) {
        this(0, courseCode, courseName, examDate, startTime);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public LocalDate getExamDate() {
        return examDate;
    }

    public void setExamDate(LocalDate examDate) {
        this.examDate = examDate;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }
}
