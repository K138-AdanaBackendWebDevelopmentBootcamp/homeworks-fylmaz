package com.example.finalproject.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
public class CreditScore {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private int id;

    @Column(name="credit_score")
    private double creditScore;


    @OneToOne
    @JsonBackReference
    private User user;

    @Autowired
    public CreditScore(double creditScore) {
        this.creditScore = creditScore;
    }
}
