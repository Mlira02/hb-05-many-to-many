package com.marcos.demo;

import entity.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class DeleteCourseDemo
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

            int id = 10;
            Course tempCourse  = session.get(Course.class, id);
            session.delete(tempCourse);
            System.out.println("Deleting course, all students associated with this course should be safe...");

            session.getTransaction().commit();
        }
        finally
        {
            System.out.println("All tasks completed...");
            factory.close();
        }
    }
}
