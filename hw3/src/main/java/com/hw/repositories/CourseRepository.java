package com.hw.repositories;

import com.hw.models.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository  extends JpaRepository<Course,Integer> {
}
