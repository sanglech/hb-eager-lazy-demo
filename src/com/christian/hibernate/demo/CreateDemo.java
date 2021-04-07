package com.christian.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.christian.hibernate.demo.entity.Instructor;
import com.christian.hibernate.demo.entity.InstructorDetail;
import com.christian.hibernate.demo.entity.Student;

public class CreateDemo {

	public static void main(String[] args) {
		
		///create session factory
		SessionFactory factory = new Configuration()
									.configure("hibernate.cfg.xml")
									.addAnnotatedClass(Instructor.class)
									.addAnnotatedClass(InstructorDetail.class)
									.buildSessionFactory();
		
		//create session
		
		Session session= factory.getCurrentSession();
		
		try {
			
			//create  objs
			/*
			Instructor tempInstructor = new Instructor("Christian", "Sangle","christan@sangle.com");
			InstructorDetail tempInstructorDetail= new  InstructorDetail("http://christian.sangle","basketball");
			*/
			
			Instructor tempInstructor2 = new Instructor("meow", "cat","meow@cat.com");
			InstructorDetail tempInstructorDetail2= new  InstructorDetail("http://meow.cat","sleeping");
			
			//associate the objects
			tempInstructor2.setInstructorDetail(tempInstructorDetail2); 
			
			
			//start txn
			session.beginTransaction();
		
			
			//save the instructor
			//Note this will also save the details obj because of cascade type all
			
			session.save(tempInstructor2);
			
			System.out.println("Saving Instructor..."+ tempInstructor2);
			
			//comit txn
			session.getTransaction().commit();
			
			System.out.println("Complete");
			
		}finally {
			factory.close();
		}
	}

}
