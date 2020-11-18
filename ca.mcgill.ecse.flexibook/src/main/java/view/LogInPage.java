package view;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.sql.Date;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Properties;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import ca.mcgill.ecse.flexibook.Controller.FlexibookController;
import ca.mcgill.ecse.flexibook.Controller.InvalidInputException;


public class LogInPage extends JFrame {

	
	public LogInPage() {
		init_component();
	}
	private void init_component() {
		frame.setSize(Width, Length);
		User.setText("Username");
		User.setBounds(5,10, 5, 5);
		frame.add(User);
		Password.setText("Password");
		Password.setBounds(5,30, 5, 5);
		frame.add(Password);
		logInBut.setText("Log In");
		logInBut.setBounds(20, 10, 5, 5);
		signUp.setText("Sign up for new account");
		signUp.setBounds(20, 30, 5, 5);
		logInBut.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				logInActionPerformed(evt);
			}
		});
		signUp.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				signUpActionPerformed(evt);
			}
		});
		frame.add(logInBut);
		frame.add(signUp);
		frame.setVisible(true);
	}
	private static void logInActionPerformed(java.awt.event.ActionEvent evt) {
		error=null;
		try {
			FlexibookController.AttemptLogIn(usr_input.getText(), passwrd_input.getText());
		}
		catch(InvalidInputException e){
			error=e.getMessage();
		}
	}
	private static void signUpActionPerformed(java.awt.event.ActionEvent evt) {
		
	}
	public static void main(String[] args) {
		int Width = 300;
		int Length = 300;
		JFrame frame = new JFrame();
		JLabel User = new JLabel();
		JLabel Password = new JLabel();
		JTextField usr_input = new JTextField();
		JTextField passwrd_input = new JTextField();
		JButton logInBut = new JButton();
		JButton signUp = new JButton();
		String error;
		JPanel panel = new JPanel();
		panel.setLayout(null);
		frame.add(panel);
		frame.setSize(Width, Length);
		User.setText("Username");
		User.setBounds(10,20, 80, 25);
		panel.add(User);
		usr_input.setBounds(100, 20, 165, 25);
		panel.add(usr_input);
		Password.setText("Password");
		Password.setBounds(10,50, 80, 25);
		panel.add(Password);
		passwrd_input.setBounds(100, 50, 165, 25);
		panel.add(passwrd_input);
		logInBut.setText("Log In");
		logInBut.setBounds(140, 100, 80, 25);
		signUp.setText("Sign up");
		signUp.setBounds(50, 100, 80, 25);
		logInBut.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				logInActionPerformed(evt);
			}
		});
		signUp.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				signUpActionPerformed(evt);
			}
		});
		panel.add(logInBut);
		panel.add(signUp);
		frame.setVisible(true);
	}
}
