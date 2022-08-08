package com.example.finalproject.repositories;

import com.example.finalproject.models.CreditScore;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CreditScoreRepository  extends JpaRepository<CreditScore, Integer> {
}
