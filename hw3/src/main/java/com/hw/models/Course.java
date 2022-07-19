package com.hw.models;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.persistence.*;

@Entity
public class Course {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private int id;
    private String courseName;
    private String courseCode;
    private int creditScore;
    @ManyToOne
    private Instructor instructor;
    @ManyToMany
    private List<Student> studentList;

    public Course(String courseName, String courseCode, int creditScore, Instructor instructor) {
        this.courseName = courseName;
        this.courseCode = courseCode;
        this.creditScore = creditScore;
        this.instructor = instructor;
        this.studentList = new ArrayList();
    }

    public Course(String courseName, String courseCode, int creditScore, Instructor instructor, List<Student> studentList) {
        this.courseName = courseName;
        this.courseCode = courseCode;
        this.creditScore = creditScore;
        this.instructor = instructor;
        this.studentList = studentList;
    }

    public Course() {
    }

    public String getCourseName() {
        return this.courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getCourseCode() {
        return this.courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public int getCreditScore() {
        return this.creditScore;
    }

    public void setCreditScore(int creditScore) {
        this.creditScore = creditScore;
    }

    public Instructor getInstructor() {
        return this.instructor;
    }

    public void setInstructor(Instructor instructor) {
        this.instructor = instructor;
    }

    public int getId() {
        return this.id;
    }

    public String toString() {
        return "Models.Course{courseName='" + this.courseName + "', courseCode='" + this.courseCode + "', creditScore=" + this.creditScore + ", instructor=" + this.instructor + "}";
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (o != null && this.getClass() == o.getClass()) {
            Course course = (Course)o;
            return this.courseCode.equals(course.courseCode);
        } else {
            return false;
        }
    }

    public int hashCode() {
        return Objects.hash(new Object[]{this.courseCode});
    }
}
