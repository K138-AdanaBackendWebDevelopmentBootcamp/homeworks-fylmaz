package com.example.finalproject.services;

import com.example.finalproject.models.Credit;
import com.example.finalproject.models.User;

import java.util.List;

public interface IUserService<T> {

    List<User> findAll();
    User findById(int id);
    void saveToDatabase(User object);
    void deleteFromDatabase(User object);
    void deleteFromDatabase(int id);
    void updateOnDatabase(User object, int id);
    String applyCredit(int id) throws Exception;
    String checkCreditStatus(String identityNumber);
}
