package com.example.finalproject.services;


import com.example.finalproject.models.Credit;
import com.example.finalproject.repositories.CreditRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class CreditService implements ICreditService {

    private CreditRepository creditRepository;

    @Autowired
    public CreditService(CreditRepository creditRepository) {
        this.creditRepository = creditRepository;
    }

    public List<Credit> findAll() {
        return creditRepository.findAll();
    }

    public Credit findById(int id) {
        return creditRepository.findById(id).get();
    }

    public void saveToDatabase(Credit credit) {
        creditRepository.save(credit);
    }

    public void deleteFromDatabase(Credit credit) {
        creditRepository.delete(credit);
    }

    public void deleteFromDatabase(int id) {
        creditRepository.deleteById(id);
    }

    public void updateOnDatabase(Credit credit, int id) {
        Credit existingCredit = creditRepository.findById(id).get();

        if (existingCredit != null) {
            existingCredit.setCreditLimit(credit.getCreditLimit());
            creditRepository.save(existingCredit);
        }
    }



}
