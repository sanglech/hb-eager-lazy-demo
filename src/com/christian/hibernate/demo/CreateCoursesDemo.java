package com.christian.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.christian.hibernate.demo.entity.Course;
import com.christian.hibernate.demo.entity.Student;

import src.com.christian.hibernate.demo.entity.Instructor;
import src.com.christian.hibernate.demo.entity.InstructorDetail;

public class CreateCoursesDemo {

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
			
			// create some courses 
			
			Course tempCourse1= new Course("SWAG ON EM");
			Course tempCourse2= new Course("Micro and Macro");
			
			// addcourses
			tempInstructor.add(tempCourse1);
			tempInstructor.add(tempCourse2);
			
			//save courses
			session.save(tempCourse1);
			session.save(tempCourse2);
			
			
			//comit txn
			session.getTransaction().commit();
			
			System.out.println("Complete");
			
		}finally {
			session.close();
			factory.close();
		}
	}

}
