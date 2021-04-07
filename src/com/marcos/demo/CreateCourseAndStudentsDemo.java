package com.marcos.demo;

import entity.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class CreateCourseAndStudentsDemo
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

            Course tempCourse = new Course("Business 101");

            System.out.println("Saving Course and all reviews as well");
            session.save(tempCourse);

            Student tempStudent1 = new Student("John", "Doe", "johnD@gmail.com");
            Student tempStudent2 = new Student("Mary", "Public", "MaryP@gmail.com");
            Student tempStudent3 = new Student("Marcos","Lira", "liram@gmail.com");

            tempCourse.addStudents(tempStudent1);
            tempCourse.addStudents(tempStudent2);
            tempCourse.addStudents(tempStudent3);

            session.save(tempStudent1);
            session.save(tempStudent2);
            session.save(tempStudent3);

            session.getTransaction().commit();
        }
        finally
        {
            System.out.println("All tasks completed...");
            factory.close();
        }
    }
}
