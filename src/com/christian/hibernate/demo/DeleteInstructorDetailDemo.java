package com.christian.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.christian.hibernate.demo.entity.Student;

import src.com.christian.hibernate.demo.entity.Instructor;
import src.com.christian.hibernate.demo.entity.InstructorDetail;

public class DeleteInstructorDetailDemo {

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
			
			//get instructor detail obj
			int theId=3;
			
			// print instruct detail
			
			InstructorDetail tempInstructorDetail= session.get(InstructorDetail.class, theId);
			
			
			System.out.println("tempInstructorDetail: "+ tempInstructorDetail);
		
			
			// print associated instuctor
			
			System.out.println("The associated Instructor is: "+ tempInstructorDetail.getInstructor());
			
			
			//remove the associated obj ref
			//break bi-directional link
			tempInstructorDetail.getInstructor().setInstructorDetail(null);
			
			
			//deleteing instructordetail with id 3
			
			session.delete(tempInstructorDetail);
			
		
			//comit txn
			session.getTransaction().commit();
			
			System.out.println("Complete");
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		
		finally {
			session.close();
			factory.close();
		}
	}

}
