package com.christian.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;

public class TestJdbc {

	public static void main(String[] args) {
		String jdbcURL="jdbc:mysql://localhost:3306/hb-01-one-to-one-uni?useSSL=false&serverTimezone=UTC";
		String user="hbstudent";
		String pass ="hbstudent";
		try {
			System.out.println("Connecting to database: "+jdbcURL );
			Connection myConn = DriverManager.getConnection(jdbcURL,user,pass);
			
			System.out.println("Successfully Connected to database");
			
		}catch (Exception e) {
			System.out.println(e);
		}

	}

}
