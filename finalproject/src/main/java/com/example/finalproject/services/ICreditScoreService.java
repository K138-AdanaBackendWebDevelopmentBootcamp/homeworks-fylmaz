package com.example.finalproject.services;

import com.example.finalproject.models.CreditScore;

import java.util.List;

public interface ICreditScoreService {
    List<CreditScore> findAll();
    CreditScore findById(int id);
    void saveToDatabase(CreditScore object);
    void deleteFromDatabase(CreditScore object);
    void deleteFromDatabase(int id);
    void updateOnDatabase(CreditScore object, int id);
}
