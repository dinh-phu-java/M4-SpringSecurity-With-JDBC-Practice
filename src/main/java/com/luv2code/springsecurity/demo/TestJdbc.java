package com.luv2code.springsecurity.demo;

import java.sql.Connection;
import java.sql.DriverManager;

public class TestJdbc {

	public static void main(String[] args) {

		String jdbcUrl = "jdbc:mysql://localhost:3306/spring_security_demo_plaintext?useSSL=false&serverTimezone=UTC";
//		String jdbcUrl = "jdbc:mysql://localhost:3306/hb_student_tracker";
		String user = "root";
		String pass = "qazWSX1@";
		
		try {
			System.out.println("Connecting to database: " + jdbcUrl);
			
			Connection myConn =
					DriverManager.getConnection(jdbcUrl, user, pass);
			
			System.out.println("Connection successful!!!");
			
		}
		catch (Exception exc) {
			exc.printStackTrace();
		}
		
	}

}



