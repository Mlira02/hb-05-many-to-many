package com.marcos.demo;

import entity.Course;
import entity.Instructor;
import entity.InstructorDetail;
import entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class CreateInstructorDemo
{
    public static void main(String[] args)
    {
        SessionFactory factory = new Configuration()
                                    .configure("hibernate.cfg.xml")
                                    .addAnnotatedClass(Instructor.class)
                                    .addAnnotatedClass(InstructorDetail.class)
                                    .addAnnotatedClass(Course.class)
                                    .buildSessionFactory();

        Session session = factory.getCurrentSession();

        try
        {

            Instructor tempInstructor = new Instructor("Susan", "Strong", "susanS@gmail.com");
            InstructorDetail tempDetails = new InstructorDetail("youtube.com/susanStrong", "accounting");
            tempInstructor.setInstructorDetail(tempDetails);

            session.beginTransaction();

            session.save(tempInstructor);

            session.getTransaction().commit();
        }
        catch(Exception exc)
        {
            exc.printStackTrace();
        }
        finally
        {
            System.out.println("All tasks completed...");
            session.close();
            factory.close();
        }
    }
}
