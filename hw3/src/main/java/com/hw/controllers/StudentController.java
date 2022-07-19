package com.hw.controllers;
import com.hw.models.Student;
import com.hw.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping("/all")
    public List<Student> findAllStudents(){
        return studentService.findAll();
    }

    @GetMapping("/{id}")
    public Student findStudentById(@PathVariable int id) {
        return studentService.findById(id);
    }

    @PostMapping("/save")
    public void saveStudent(@RequestBody Student student) {
        studentService.saveToDatabase(student);
    }

    @DeleteMapping("/deleteStudent")
    public void deleteStudent(@RequestBody Student student) {
        studentService.deleteFromDatabase(student);
    }

    @DeleteMapping("/deleteStudentById/{id}")
    public void deleteStudent(@PathVariable int id) {
        studentService.deleteFromDatabase(id);
    }

    @PutMapping("/updateStudent/{id}")
    public ResponseEntity<Student> updateStudent(@RequestBody Student student, @PathVariable int id) {
        studentService.updateOnDatabase(student, id);
        //Return 200 response code if successful
        return ResponseEntity.status(HttpStatus.OK).body(student);
    }
}