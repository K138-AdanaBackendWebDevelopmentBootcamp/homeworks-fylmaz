
package com.hw.models;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;

@Entity
@Inheritance(
        strategy = InheritanceType.JOINED
)
public abstract class Instructor {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private int id;
    private String name;
    private String address;
    private String phoneNumber;
    @OneToMany
    private List<Course> courses;

    public Instructor(String name, String address, String phoneNumber) {
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.courses = new ArrayList();
    }

    public Instructor() {
    }

    public Instructor(String name, String address, String phoneNumber, List<Course> courses) {
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.courses = courses;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public List<Course> getCourses() {
        return this.courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    public int getId() {
        return this.id;
    }

    public String toString() {
        return "Models.Instructor{name='" + this.name + "', address='" + this.address + "', phoneNumber='" + this.phoneNumber + "', courses=" + this.courses + "}";
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (o != null && this.getClass() == o.getClass()) {
            Instructor that = (Instructor)o;
            return this.phoneNumber.equals(that.phoneNumber);
        } else {
            return false;
        }
    }

    public int hashCode() {
        return Objects.hash(new Object[]{this.phoneNumber});
    }

    public void assignCourse(Course course) {
        if (course != null) {
            this.courses.add(course);
        }

    }

    public void assignCourseList(List<Course> courses) {
        if (this.courses != null && courses != null) {
            this.courses.addAll(courses);
        }

    }
}
