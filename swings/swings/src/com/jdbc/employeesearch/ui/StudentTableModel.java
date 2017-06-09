package com.jdbc.employeesearch.ui;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import com.jdbc.employeesearch.core.Student;

class StudentTableModel extends AbstractTableModel {

	private static final long serialVersionUID = 1L;
	private static final int ID_COL = 0;
	private static final int NAME_COL = 1;
	private static final int DEPT_NAME_COL = 2;
	private static final int TOT_CRED_COL = 3;

	private String[] columnNames = { "id","name", "dept_name", "tot_cred" };
	private List<Student> students;

	public StudentTableModel(List<Student> theStudents) {
		students = theStudents;
	}

	@Override
	public int getColumnCount() {
		return columnNames.length;
	}

	@Override
	public int getRowCount() {
		return students.size();
	}

	@Override
	public String getColumnName(int col) {
		return columnNames[col];
	}

	@Override
	public Object getValueAt(int row, int col) {

		Student tempStudent = students.get(row);

		switch (col) {
		case ID_COL:
			return tempStudent.getId();
		case NAME_COL:
			return tempStudent.getname();
		case DEPT_NAME_COL:
			return tempStudent.getdept_name();
		case TOT_CRED_COL:
			return tempStudent.gettot_cred();
		default:
			return tempStudent.getname();
		}
	}

	@Override
	public Class<? extends Object> getColumnClass(int c) {
		return getValueAt(0, c).getClass();
	}
}