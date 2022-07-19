
package com.hw.models;

import java.util.List;
import javax.persistence.Entity;

@Entity
public class PermanentResearcher extends Instructor {
    private double fixedSalary;

    public PermanentResearcher(String name, String address, String phoneNumber, List<Course> courses, double fixedSalary) {
        super(name, address, phoneNumber, courses);
        this.fixedSalary = fixedSalary;
    }

    public PermanentResearcher(String name, String address, String phoneNumber, double fixedSalary) {
        super(name, address, phoneNumber);
        this.fixedSalary = fixedSalary;
    }

    public PermanentResearcher() {

    }
}
