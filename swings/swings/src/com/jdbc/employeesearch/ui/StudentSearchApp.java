package com.jdbc.employeesearch.ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.FlowLayout;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JOptionPane;
import java.util.List;

import com.jdbc.employeesearch.core.Student;
import com.jdbc.employeesearch.dao.StudentDAO;

public class StudentSearchApp extends JFrame {

	/**
	 * 
	 */
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField dept_nameTextField;
	private JTextField tot_credTextField;
	private JButton btnSearch;
	private JScrollPane scrollPane;
	private JTable table;

	private StudentDAO studentDAO;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StudentSearchApp frame = new StudentSearchApp();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public StudentSearchApp() {
		
		// create the DAO
		try {
			studentDAO = new StudentDAO();
		} catch (Exception exc) {
			JOptionPane.showMessageDialog(this, "Error: " + exc, "Error", JOptionPane.ERROR_MESSAGE); 
		}
		
		setTitle("Student Search App");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		contentPane.add(panel, BorderLayout.NORTH);
		
	   JLabel lblEnterdept_name = new JLabel("Enter Department name");
		panel.add(lblEnterdept_name);
		
		 dept_nameTextField = new JTextField();
		panel.add(dept_nameTextField);
		dept_nameTextField.setColumns(10);
		
		JLabel lblEntertot_cred = new JLabel("Total credits");
		panel.add(lblEntertot_cred);
		
		tot_credTextField = new JTextField();
		panel.add(tot_credTextField);
	   tot_credTextField.setColumns(10);
		
		btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Get last name from the text field

				// Call DAO and get employees for the last name

				// If last name is empty, then get all employees

				// Print out employees				
				
				try {
					String dept_name = dept_nameTextField.getText();
					String tot_cred = tot_credTextField.getText();
					List<Student> students = null;
					if ((dept_name != null && dept_name.trim().length() > 0) && (tot_cred != null && tot_cred.trim().length() > 0)) {

						students = studentDAO.searchStudent(tot_cred,dept_name);
						/*students = studentDAO.searchDepartment(dept_name);*/
					} 
					
					
					// create the model and update the "table"
					StudentTableModel model = new StudentTableModel(students);
					
					table.setModel(model);
					
					/*
					for (Employee temp : employees) {
						System.out.println(temp);
					}
					*/
				} catch (Exception exc) {
					JOptionPane.showMessageDialog(StudentSearchApp.this, "Error: " + exc, "Error", JOptionPane.ERROR_MESSAGE); 
				}
				
			}
		});
		panel.add(btnSearch);
		
		scrollPane = new JScrollPane();
		contentPane.add(scrollPane, BorderLayout.CENTER);
		
		table = new JTable();
		scrollPane.setViewportView(table);
	}

}
