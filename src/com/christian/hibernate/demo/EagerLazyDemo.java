package com.christian.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.christian.hibernate.demo.entity.Course;
import com.christian.hibernate.demo.entity.Instructor;
import com.christian.hibernate.demo.entity.InstructorDetail;
import com.christian.hibernate.demo.entity.Student;

public class EagerLazyDemo {

	public static void main(String[] args) {
		
		///create session factory
		SessionFactory factory = new Configuration()
									.configure("hibernate.cfg.xml")
									.addAnnotatedClass(Instructor.class)
									.addAnnotatedClass(InstructorDetail.class)
									.addAnnotatedClass(Course.class)
									.buildSessionFactory();
		
		//create session
		
		Session session= factory.getCurrentSession();
		
		try {
			
			//start txn
			session.beginTransaction();
			//get instructor from db
			int theId=1;
			
			Instructor tempInstructor= session.get(Instructor.class, theId);
			
			System.out.println("christain instructor: "+ tempInstructor);
			
			//option 1: call getter method while session is still open
			//loading lazy data while session is open
			System.out.println("Session Open Courses: "+ tempInstructor.getCourses());
			
			
			//comit txn
			session.getTransaction().commit();
			
			session.close();
			
			
			System.out.println("Session Closed Courses: "+ tempInstructor.getCourses());
			System.out.println("Christian Complete");
			
		}finally {
			session.close();
			factory.close();
		}
	}

}
