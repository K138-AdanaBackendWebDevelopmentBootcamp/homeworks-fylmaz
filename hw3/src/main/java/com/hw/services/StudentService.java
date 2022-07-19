package com.hw.services;

import com.hw.models.Student;
import com.hw.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService implements IStudentService<Student> {

    @Autowired
    private StudentRepository studentRepository;

    @Override
    public List<Student> findAll() {
        return studentRepository.findAll();
    }

    @Override
    public Student findById(int id) {
        return studentRepository.findById(id).get();
    }

    @Override
    public void saveToDatabase(Student student) {
        studentRepository.save(student);
    }

    @Override
    public void deleteFromDatabase(Student student) {
        studentRepository.delete(student);
    }

    @Override
    public void deleteFromDatabase(int id) {
        studentRepository.deleteById(id);
    }

    @Override
    public void updateOnDatabase(Student student, int id) {
        Student existingStudent = studentRepository.findById(id).get();

        if(existingStudent != null){
            existingStudent.setAddress(student.getAddress());
            existingStudent.setBirthDay(student.getBirthDay());
            existingStudent.setCourses(student.getCourses());
            existingStudent.setGender(student.getGender());
            existingStudent.setName(student.getName());
            studentRepository.save(existingStudent);
        }
    }

}
