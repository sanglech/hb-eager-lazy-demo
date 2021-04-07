package com.christian.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.christian.hibernate.demo.entity.Student;

import src.com.christian.hibernate.demo.entity.Instructor;
import src.com.christian.hibernate.demo.entity.InstructorDetail;

public class DeleteDemo {

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
			
			//start txn
			session.beginTransaction();
			
			//get instructor by primary id/key
			
			int theId=2;
			
			Instructor tempInstructor= session.get(Instructor.class, theId);
			
			System.out.println("Found the instructor: "+tempInstructor);
			
			//delete the instructor
			
			if(tempInstructor!=null) {
				System.out.println("Deleting instructor: "+ tempInstructor);
				//Will also delete detail obj because of cascade.ALL
				session.delete(tempInstructor);
			
			}
			
			//comit txn
			session.getTransaction().commit();
			
			System.out.println("Complete");
			
		}finally {
			factory.close();
		}
	}

}
