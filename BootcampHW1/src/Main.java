import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        Instructor instructor = new PermanentResearcher("a","b","c",300);

        Course course = new Course("a","b",4,instructor);
        Course course1 = new Course("c","d",5,instructor);
        Course course2 = new Course("e","f",6,instructor);

        instructor.assignCourse(course);//Adding 1by1

        List<Course> courses = List.of(new Course("e", "f", 6, instructor),
                                        new Course("c", "d", 5, instructor));

        instructor.assignCourseList(courses);//Adding multiple courses

        Instructor instructor2 = new VisitingResearcher("a","b","c",courses,20);//Setting multiple courses in constructor

        //--------------------------------------

        Student student = new Student("aa","11.03.1998","ddd","m");//construct with zero course

        student.assignCourse(course); //Adding 1by1

        student.assignCourseList(courses); //Adding multiple courses

        Student student2 = new Student("bb","13.12.1997","eee","f",courses);////Setting multiple courses in constructor

    }
}
