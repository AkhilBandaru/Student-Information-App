package com.jdbc.employeesearch.dao;

import java.util.*;
import java.sql.*;
import java.io.*;


import com.jdbc.employeesearch.core.Student;

public class StudentDAO {

	private java.sql.Connection myConn;
	
	public StudentDAO() throws Exception {
		String catalog = null;
		String schemaPattern = null;
		String tableNamePattern = null;
		String columnNamePattern = null;
		String[] types = null;
		
		ResultSet myRs = null;
		// get db properties
		Properties props = new Properties();
		props.load(new FileInputStream("demo.properties"));
		
		String user = props.getProperty("user");
		String password = props.getProperty("password");
		String dburl = props.getProperty("dburl");
		
		// connect to database
		myConn = DriverManager.getConnection(dburl, user, password);
		
		System.out.println("DB connection successful to: " + dburl);
		// 2. Get metadata
					DatabaseMetaData databaseMetaData = myConn.getMetaData();

					// 3. Get list of tables
					

					myRs = databaseMetaData.getTables(catalog, schemaPattern, tableNamePattern,
							types);

					while (myRs.next()) {
						System.out.println("\n\nTABLE_NAME");
						System.out.println("--------------");
						System.out.println(myRs.getString("TABLE_NAME"));
					String table = myRs.getString("TABLE_NAME");

					// 4. Get list of columns
					System.out.println("\nList of Columns");
					System.out.println("--------------");

					ResultSet myRt = databaseMetaData.getColumns(catalog, schemaPattern,table, columnNamePattern);

					while (myRt.next()) {
						System.out.println(myRt.getString("COLUMN_NAME"));
					}
					}
		
	}
	
	
	public List<Student> getAllStudents() throws Exception {
		List<Student> list = new ArrayList<>();
		
		Statement myStmt = null;
		ResultSet myRs = null;
		
		try {
			myStmt = myConn.createStatement();
			myRs = myStmt.executeQuery("select * from student");
			
			while (myRs.next()) {
				Student tempStudent = convertRowToStudent(myRs);
				list.add(tempStudent);
			}

			return list;		
		}
		finally {
			close(myStmt, myRs);
		}
	}
	
	
	
	private Student convertRowToStudent(ResultSet myRs) throws SQLException {
		
		int id = myRs.getInt("id");
		String name = myRs.getString("name");
		String dept_name = myRs.getString("dept_name");
		String tot_cred = myRs.getString("tot_cred");
		Student tempStudent = new Student(id, name, dept_name, tot_cred);
		
		return tempStudent;
	}

	
	private static void close(Connection myConn, Statement myStmt, ResultSet myRs)
			throws SQLException {

		if (myRs != null) {
			myRs.close();
		}

		if (myStmt != null) {
			
		}
		
		if (myConn != null) {
			myConn.close();
		}
	}

	private void close(Statement myStmt, ResultSet myRs) throws SQLException {
		close(null, myStmt, myRs);		
	}

	public static void main(String[] args) throws Exception {
		
		StudentDAO dao = new StudentDAO();

		System.out.println(dao.getAllStudents());
	}

	public List<Student> searchStudent(String tot_cred, String dept_name)throws Exception {
		List<Student> list = new ArrayList<>();
		

		PreparedStatement myStmt = null;
		ResultSet myRs = null;

		try {
			tot_cred += "%";
		dept_name += "%";
			myStmt = myConn.prepareStatement("select * from student where dept_name like ? and tot_cred like ?");
			myStmt.setString(2, tot_cred);
			myStmt.setString(1, dept_name);
			
			
			myRs = myStmt.executeQuery();
			
			while (myRs.next()) {
				Student tempStudent = convertRowToStudent(myRs);
				list.add(tempStudent);
			}
			
			return list;
		}
		finally {
			close(myStmt, myRs);
		}
	}

	

	
}
