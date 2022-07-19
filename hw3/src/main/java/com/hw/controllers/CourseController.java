package com.hw.controllers;


import com.hw.models.Course;
import com.hw.services.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/course")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @GetMapping("/all")
    public List<Course> findAllCourses(){
        return courseService.findAll();
    }

    @GetMapping("/{id}")
    public Course findCourseById(@PathVariable int id) {
        return courseService.findById(id);
    }

    @PostMapping("/save")
    public void saveCourse(@RequestBody Course course) {
        courseService.saveToDatabase(course);
    }

    @DeleteMapping("/deleteCourse")
    public void deleteCourse(@RequestBody Course course) {
        courseService.deleteFromDatabase(course);
    }

    @DeleteMapping("/deleteCourseById/{id}")
    public void deleteCourse(@PathVariable int id) {
        courseService.deleteFromDatabase(id);
    }

    @PutMapping("/updateCourse/{id}")
    public ResponseEntity<Course> updateSCourse(@RequestBody Course course, @PathVariable int id) {
        courseService.updateOnDatabase(course, id);
        //Return 200 response code if successful
        return ResponseEntity.status(HttpStatus.OK).body(course);
    }
}