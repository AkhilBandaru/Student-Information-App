package com.jdbc.employeesearch.core
public class Student {

	private int id;
	private String name;
	private String dept_name;
	private String tot_cred;
	
	
	public Student(int id, String name, String dept_name, String tot_cred) {
		super();
		this.id = id;
		this.name = name;
		this.dept_name = dept_name;
		this.tot_cred = tot_cred;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getname() {
		return name;
	}

	public void setname(String name) {
		this.name = name;
	}

	public String getdept_name() {
		return dept_name;
	}

	public void setdept_name(String dept_name) {
		this.dept_name = dept_name;
	}

	public String gettot_cred() {
		return tot_cred;
	}

	public void settot_cred(String tot_cred) {
		this.tot_cred = tot_cred;
	}


	@Override
	public String toString() {
		return String
				.format("Student [id=%s, name=%s, dept_name=%s, tot_cred=%s]",
						id, name, dept_name, tot_cred);
	}

	

	
	
	
		
}