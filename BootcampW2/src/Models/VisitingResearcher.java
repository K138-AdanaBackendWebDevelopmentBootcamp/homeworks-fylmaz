package Models;

import javax.persistence.Entity;
import java.util.List;


@Entity
public class VisitingResearcher extends Instructor {


    private double hourlySalary;

    public VisitingResearcher(String name, String address, String phoneNumber, List<Course> courses, double hourlySalary) {
        super(name, address, phoneNumber, courses);
        this.hourlySalary=hourlySalary;
    }
    public VisitingResearcher(String name, String address, String phoneNumber, double hourlySalary) {
        super(name, address, phoneNumber);
        this.hourlySalary=hourlySalary;
    }

}
