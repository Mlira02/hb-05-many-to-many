package com.marcos.demo;

import entity.Course;
import entity.Instructor;
import entity.InstructorDetail;
import entity.Review;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class DeleteCourseAndReviewsDemo
{
    public static void main(String[] args)
    {
        SessionFactory factory = new Configuration()
                                    .configure("hibernate.cfg.xml")
                                    .addAnnotatedClass(Instructor.class)
                                    .addAnnotatedClass(InstructorDetail.class)
                                    .addAnnotatedClass(Course.class)
                                    .addAnnotatedClass(Review.class)
                                    .buildSessionFactory();

        Session session = factory.getCurrentSession();

        try
        {
            session.beginTransaction();

            int theId = 10;
            Course tempCourse = session.get(Course.class, theId);
            tempCourse.getReviews();
            session.delete(tempCourse);

            session.getTransaction().commit();
        }
        finally
        {
            System.out.println("All tasks completed...");
            factory.close();
        }
    }
}
