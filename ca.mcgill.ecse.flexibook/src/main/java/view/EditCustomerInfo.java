package view;

import java.awt.Color;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import ca.mcgill.ecse.flexibook.Controller.FlexibookController;
import ca.mcgill.ecse.flexibook.Controller.InvalidInputException;
import ca.mcgill.ecse.flexibook.application.FlexiBookApplication;

public class EditCustomerInfo {
	private static JLabel Title=new JLabel();
	public static JFrame frame=new JFrame();
	private static JPanel panel = new JPanel();
	private static String error;
	private static JLabel errorMessage = new JLabel("");
	private static JLabel newusr; 
	private static JTextField newuser;
	private static JLabel confirmpwd;
	private static JPasswordField cpwd;
	private static JLabel newpwd;
	private static JPasswordField np;
	private static JButton confirm;
	private static JButton cancel;
	private static JButton delete;
	private static JLabel success = new JLabel();

	public EditCustomerInfo() {
		init();
	}

	public static void main(String[] args) {
		init();   
	}

	private static void init() {
		Font font1 = new Font("Times New Romans", Font.BOLD, 20);
		frame.setSize(700, 700);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(panel);

		panel.setLayout(null);

		Title.setText("Editing Information:");
		Title.setBounds(250, 15, 400, 75);
		Title.setFont(font1);
		panel.add(Title);

		newusr = new JLabel("New Username:");
		newusr.setBounds(280, 100, 150, 25);
		panel.add(newusr);

		newuser = new JTextField(70);
		newuser.setBounds(280, 140, 165, 25);
		panel.add(newuser);

		newpwd = new JLabel("New Password:");
		newpwd.setBounds(280, 180, 150, 25);
		panel.add(newpwd);

		np = new JPasswordField(70);
		np.setBounds(280, 220, 165, 25);
		panel.add(np);

		confirmpwd = new JLabel("Confirm New Password: ");
		confirmpwd.setBounds(280,260, 200, 25);
		panel.add(confirmpwd);

		cpwd = new JPasswordField(70);
		cpwd.setBounds(280, 300, 165, 25);
		panel.add(cpwd);

		confirm = new JButton("Confirm");
		confirm.setBounds(250, 360, 80, 25);
		confirm.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				confirmActionPerformed(evt);
			}});
		panel.add(confirm);

		cancel = new JButton("Back");
		cancel.setBounds(520, 360, 80, 25);
		cancel.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				cancelActionPerformed(evt);
			}});
		panel.add(cancel);

		delete = new JButton("Delete Account");
		delete.setBounds(360, 360, 130, 25);
		delete.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				deleteActionPerformed(evt);
			}});
		panel.add(delete);

		errorMessage = new JLabel();
		errorMessage.setBounds(150, 400, 300, 25);
		errorMessage.setForeground(Color.red);
		panel.add(errorMessage);

		success = new JLabel(""); 
		success.setBounds(150, 400, 400, 25);
		success.setForeground(Color.BLUE);
		panel.add(success);

		frame.setVisible(true);


	}

	private static void confirmActionPerformed(ActionEvent evt) {
		// add code to save the change
		//check the confirm pwd same as pwd enter before
		//success update the new account, otherwise throw error
		// all input cannot be empty if it is throw error
		//		if(!(FlexiBookApplication.getCurrentuser().getUsername().equals(newuser))) {
		//			try {
		//				FlexibookController.UpdateAccount(FlexiBookApplication.getCurrentuser().getUsername().toString(), newuser.getText().toString(), np.getText().toString());
		//			} catch(InvalidInputException e) {
		//				success.setText("Successfully updated the account!");
		//			}
		//		}
		//		else {
		//			//errorMessage.setText(e.getMessage());
		//		}

		try {
			if(!(FlexiBookApplication.getCurrentuser().getUsername().equals(newuser)) && !(FlexiBookApplication.getCurrentuser().getPassword().equals(np))) {
				FlexibookController.UpdateAccount(FlexiBookApplication.getCurrentuser().getUsername().toString(), newuser.getText().toString(), np.getText().toString());
				success.setText("Successfully updated the account!");
			}
		} catch(InvalidInputException e) {
			errorMessage.setText("**" + e.getMessage());
		}

	}

	private static void cancelActionPerformed(ActionEvent evt) {
		FlexiBookApplication.editcustomercancel();
	}

	private static void deleteActionPerformed(ActionEvent evt) {
		try {
			FlexibookController.DeleteCustomerAccount(FlexiBookApplication.getCurrentuser().getUsername().toString(), FlexiBookApplication.getCurrentuser().getUsername().toString());
			FlexiBookApplication.clogout();
		} catch(InvalidInputException e) {

		}
	}
}
