package Main;
import Controllers.StudentController;
import Models.*;
import Utils.EntityManagerUtils;

import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.time.Month;
import java.util.Iterator;
import java.util.List;



public class App {
    public static void main(String[] args) {

        StudentController studentController = new StudentController();

        if (checkTestData() == 0) {

            Student tempStudent = new Student("fatih","april","ankara","e");
            studentController.saveStudent(tempStudent);
            saveTestData();
        }

        List<Student> tempList = studentController.findAllStudents();

        Student foundStudent = tempList.get(0);
        foundStudent.setAddress("istanbul");
        studentController.updateStudent(foundStudent,1);

        System.out.println(foundStudent);

        studentController.deleteStudent(foundStudent);







    }

    private static int checkTestData() {
        EntityManager em = EntityManagerUtils.getEntityManager("mysqlPU");
        return  em.createQuery("From Student", Student.class).getResultList().size();
    }

    private static void saveTestData() {

        EntityManager em = EntityManagerUtils.getEntityManager("mysqlPU");


        Student student1 = new Student("fatih","january","izmir","e");
        Student student2 = new Student("alptug","january","izmir","e");
        Student student3 = new Student("kemal","may","ankara","e");
        Student student4 = new Student("sabiha","","istanbul","k");
        Student student5 = new Student("kagan","november","corum","e");
        Student student6 = new Student("tomris","august","aydin","k");

        Instructor instructor1 = new PermanentResearcher("koray","istanbul","10",20);
        Instructor instructor2 = new PermanentResearcher("mete","ankara","11",18);
        Instructor instructor3 = new VisitingResearcher("mustafa","","12",1);
        Instructor instructor4 = new VisitingResearcher("aybuke","ankara","13",1.2);

        Course course1 = new Course("geometry","geo101",4,instructor3);
        Course course2 = new Course("music","132",2,instructor4);
        Course course3 = new Course("physics","phy1",4,instructor2);
        Course course4 = new Course("liberal arts","5",2,instructor3);
        Course course5 = new Course("web programming","web",5,instructor1);

        try {
            em.getTransaction().begin();

            em.persist(student1);
            em.persist(student2);
            em.persist(student3);
            em.persist(student4);
            em.persist(student5);
            em.persist(student6);

            em.persist(instructor1);
            em.persist(instructor2);
            em.persist(instructor3);
            em.persist(instructor4);

            em.persist(course1);
            em.persist(course2);
            em.persist(course3);
            em.persist(course4);
            em.persist(course5);

            em.getTransaction().commit();

            System.out.println("All data persisted...");
        } catch (Exception e) {
            em.getTransaction().rollback();
        } finally {
            EntityManagerUtils.closeEntityManager(em);
        }

    }
}