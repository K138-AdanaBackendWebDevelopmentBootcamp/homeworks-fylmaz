package com.hw.models;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;

@Entity
public class Student {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private int id;
    private String name;
    @Column(name="birthday")
    private String birthDay;
    private String address;
    private String gender;
    @ManyToMany
    private List<Course> courses;

    public Student(String name, String birthDay, String address, String gender, List<Course> courses) {
        this.name = name;
        this.birthDay = birthDay;
        this.address = address;
        this.gender = gender;
        this.courses = courses;
    }

    public Student(String name, String birthDay, String address, String gender) {
        this.name = name;
        this.birthDay = birthDay;
        this.address = address;
        this.gender = gender;
        this.courses = new ArrayList();
    }

    public Student() {
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBirthDay(String birthDay) {
        this.birthDay = birthDay;
    }

    public String getBirthDay() {
        return this.birthDay;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getGender() {
        return this.gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public List<Course> getCourses() {
        return this.courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    public int getId() {
        return this.id;
    }

    public void assignCourseList(List<Course> courses) {
        if (this.courses != null && courses != null) {
            this.courses.addAll(courses);
        }

    }

    public void assignCourse(Course course) {
        if (course != null) {
            this.courses.add(course);
        }

    }
}
