package com.marcos.demo;

import entity.Course;
import entity.Instructor;
import entity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

public class FetchJoinDemo
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
            session.beginTransaction();

            int theID = 3;

            Query<Instructor> query = session.createQuery("select i from Instructor i " + "JOIN FETCH i.courses " + "where i.id=:theInstructorId", Instructor.class);
            query.setParameter("theInstructorId", theID);
            Instructor tempInstructor = query.getSingleResult();
            System.out.println("TempInstructor here: " + tempInstructor);

            session.getTransaction().commit();
            factory.close();
//            Saving query data in a local variable can stop the lazy data exception to be thrown...
            System.out.println("All courses for Susan here: " + tempInstructor.getCourses());
        }
        finally
        {
            System.out.println("All tasks completed...");
            //            factory.close();
        }
    }
}