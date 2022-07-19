package com.hw.repositories;

import com.hw.models.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ICRUDRepository<T>  extends JpaRepository<Student,Integer> {

//    List<T> findAll();
//    T findById(int id);
//    void saveToDatabase(T object);
//    void deleteFromDatabase(T object);
//    void deleteFromDatabase(int id);
//    void updateOnDatabase(T object, int id);

}

