package com.example.finalproject.services;

import com.example.finalproject.models.Credit;

import java.util.List;

public interface ICreditService {

    List<Credit> findAll();
    Credit findById(int id);
    void saveToDatabase(Credit object);
    void deleteFromDatabase(Credit object);
    void deleteFromDatabase(int id);
    void updateOnDatabase(Credit object, int id);
}
