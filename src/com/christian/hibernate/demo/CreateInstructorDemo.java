package com.christian.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.christian.hibernate.demo.entity.Course;
import com.christian.hibernate.demo.entity.Instructor;
import com.christian.hibernate.demo.entity.InstructorDetail;
import com.christian.hibernate.demo.entity.Student;

public class CreateInstructorDemo {

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
			
			//create  objs
			Instructor tempInstructor = new Instructor("Christian", "Sangle","christan@sangle.com");
			InstructorDetail tempInstructorDetail= new  InstructorDetail("http://christian.sangle","basketball");
			
			
			
			//associate the objects
			tempInstructor.setInstructorDetail(tempInstructorDetail); 
			
			
			//start txn
			session.beginTransaction();
		
			
			//save the instructor
			//Note this will also save the details obj because of cascade type all
			
			session.save(tempInstructor);
			
			System.out.println("Saving Instructor..."+ tempInstructor);
			
			//comit txn
			session.getTransaction().commit();
			
			System.out.println("Complete");
			
		}finally {
			session.close();
			factory.close();
		}
	}

}
