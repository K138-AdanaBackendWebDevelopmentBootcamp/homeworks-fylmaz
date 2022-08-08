package com.example.finalproject.services;

import com.example.finalproject.models.CreditScore;
import com.example.finalproject.repositories.CreditScoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class CreditScoreService implements ICreditScoreService{

    private CreditScoreRepository creditScoreRepository;

    @Autowired
    public CreditScoreService(CreditScoreRepository creditScoreRepository) {
        this.creditScoreRepository = creditScoreRepository;
    }

    public List<CreditScore> findAll() {
        return creditScoreRepository.findAll();
    }

    public CreditScore findById(int id) {
        return creditScoreRepository.findById(id).get();
    }

    public void saveToDatabase(CreditScore credit) {
        creditScoreRepository.save(credit);
    }

    public void deleteFromDatabase(CreditScore credit) {
        creditScoreRepository.delete(credit);
    }

    public void deleteFromDatabase(int id) {
        creditScoreRepository.deleteById(id);
    }

    public void updateOnDatabase(CreditScore creditScore, int id) {
        CreditScore existingCreditScore = creditScoreRepository.findById(id).get();

        if (existingCreditScore != null) {
            existingCreditScore.setCreditScore(creditScore.getCreditScore());
            existingCreditScore.setUser(creditScore.getUser());
            creditScoreRepository.save(existingCreditScore);
        }
    }



}
