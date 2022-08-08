package com.example.finalproject.models;


import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;
import javax.persistence.*;


@Entity
@Data
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private int id;

    @Column(name="first_name")
    private String firstName;
    @Column(name="last_name")
    private String lastName;
    @Column(name="identity_number")
    private String identityNumber;
    @Column(name="phone_number")
    private String phoneNumber;
    @Column(name="monthly_income")
    private double monthlyIncome;

    @OneToOne(mappedBy = "user",cascade = CascadeType.ALL)
    private CreditScore creditScore;

    @OneToOne(cascade = CascadeType.ALL)  //yeni müşteri ekleme işlemi gibi bir durumda credit eklememek gibi durumlar sıkıntı olur mu
    @Autowired
    private Credit credit;



    public User(String firstName, String lastName, String identityNumber, String phoneNumber, double monthlyIncome) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.identityNumber = identityNumber;
        this.phoneNumber = phoneNumber;
        this.monthlyIncome = monthlyIncome;
    }


}
