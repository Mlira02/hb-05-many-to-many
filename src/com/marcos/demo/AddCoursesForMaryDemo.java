package com.marcos.demo;

import entity.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class AddCoursesForMaryDemo
{
    public static void main(String[] args)
    {
        SessionFactory factory = new Configuration()
                                    .configure("hibernate.cfg.xml")
                                    .addAnnotatedClass(Instructor.class)
                                    .addAnnotatedClass(InstructorDetail.class)
                                    .addAnnotatedClass(Course.class)
                                    .addAnnotatedClass(Review.class)
                                    .addAnnotatedClass(Student.class)
                                    .buildSessionFactory();

        Session session = factory.getCurrentSession();

        try
        {
            session.beginTransaction();

            int id = 2;
            Student tempStudent  = session.get(Student.class, id);
            System.out.println("Should be Mary: " + tempStudent);
            System.out.println("All of Mary's courses here: " + tempStudent.getCourses());

            Course tempCourse1 = new Course("Accounting 204");
            Course tempCourse2 = new Course("Math 203");

            tempCourse1.addStudents(tempStudent);
            tempCourse2.addStudents(tempStudent);

            session.save(tempCourse1);
            session.save(tempCourse2);

            session.getTransaction().commit();
        }
        finally
        {
            System.out.println("All tasks completed...");
            factory.close();
        }
    }
}
