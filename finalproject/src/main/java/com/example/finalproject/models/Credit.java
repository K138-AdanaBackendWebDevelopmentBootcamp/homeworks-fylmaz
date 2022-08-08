package com.example.finalproject.models;


import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;


@Entity
@Data
@NoArgsConstructor
public class Credit {

    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private int id;

    @Column(name="credit_limit")
    private double creditLimit;

    @OneToOne
    @JsonBackReference
    private User user;

    public Credit(double creditLimit) {
        this.creditLimit = creditLimit;
    }

}







