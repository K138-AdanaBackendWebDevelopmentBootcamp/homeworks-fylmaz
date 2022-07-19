package com.hw.services;

import com.hw.models.Course;
import com.hw.repositories.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService implements ICourseService<Course> {

    @Autowired
    private CourseRepository courseRepository;

    @Override
    public List<Course> findAll() {
        return courseRepository.findAll();
    }

    @Override
    public Course findById(int id) {
        return courseRepository.findById(id).get();
    }

    @Override
    public void saveToDatabase(Course course) {
        courseRepository.save(course);
    }

    @Override
    public void deleteFromDatabase(Course course) {
        courseRepository.delete(course);
    }

    @Override
    public void deleteFromDatabase(int id) {
        courseRepository.deleteById(id);
    }

    @Override
    public void updateOnDatabase(Course course, int id) {
        Course existingCourse = courseRepository.findById(id).get();

        if(existingCourse != null){
            existingCourse.setCourseCode(course.getCourseCode());
            existingCourse.setCourseName(course.getCourseName());
            existingCourse.setCreditScore(course.getCreditScore());
            existingCourse.setInstructor(course.getInstructor());

            courseRepository.save(existingCourse);
        }
    }

}
