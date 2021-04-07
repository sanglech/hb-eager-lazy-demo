package com.christian.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import com.christian.hibernate.demo.entity.Course;
import com.christian.hibernate.demo.entity.Instructor;
import com.christian.hibernate.demo.entity.InstructorDetail;
import com.christian.hibernate.demo.entity.Student;

public class FetchJoinDemo {

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
			
			//option 2: Hibernate QUery with HQL
			Query<Instructor> query = session.createQuery("select i from Instructor i "
					+ "JOIN FETCH i.courses "
					+ "where i.id=:theInstructorId",Instructor.class);
			
			query.setParameter("theInstructorId", theId);
			
			
			//execute the query
			
			Instructor tempInstructor=query.getSingleResult();
			
			System.out.println("Chrsitian Instructor: "+ tempInstructor);
			
			
			//comit txn
			session.getTransaction().commit();
			
			//close session
			session.close();
			
			System.out.println("Session Closed Courses: "+ tempInstructor.getCourses());
			
			System.out.println("Christian Complete");
			
		}finally {
			session.close();
			factory.close();
		}
	}

}
