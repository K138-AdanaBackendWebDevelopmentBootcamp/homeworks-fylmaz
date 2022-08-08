package com.example.finalproject.repositories;

import com.example.finalproject.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface UserRepository extends JpaRepository<User, Integer> {

    @Query("select u from User u where u.identityNumber = ?1")
    User findByIdentityNumber(String identityNumber);
}


