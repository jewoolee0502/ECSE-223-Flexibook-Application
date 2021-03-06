package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import ca.mcgill.ecse.flexibook.Controller.FlexibookController;
import ca.mcgill.ecse.flexibook.Controller.InvalidInputException;
import ca.mcgill.ecse.flexibook.application.FlexiBookApplication;

public class EditOwnerAccount {
	
	private static JLabel Title = new JLabel();
    public static JFrame frame = new JFrame();
	private static JPanel panel = new JPanel();
	private static String error;
	private static JLabel errorMessage = new JLabel("");
//	private static JLabel newusr; 
//	private static JTextField newuser;
	private static JLabel confirmpwd;
	private static JPasswordField cpwd;
	private static JLabel newpwd;
	private static JPasswordField np;
	private static JButton confirm;
	private static JButton cancel;
	private static JLabel success = new JLabel();

	private static void init_component_editOwnerAccount() {

		Font font1 = new Font("Times New Romans", Font.BOLD, 20);
		frame.setSize(700, 700);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(panel);

		panel.setLayout(null);

		Title.setText("Edit Owner Account:");
		Title.setBounds(250, 15, 400, 75);
		Title.setFont(font1);
		panel.add(Title);

//		newusr = new JLabel("New Username:");
//		newusr.setBounds(280, 100, 150, 25);
//		panel.add(newusr);
//
//		newuser = new JTextField(70);
//		newuser.setBounds(280,140,165,25);
//		panel.add(newuser);

		newpwd = new JLabel("New Password:");
		newpwd.setBounds(280, 150, 150, 25);
		panel.add(newpwd);

		np = new JPasswordField(70);
		np.setBounds(280, 190, 165, 25);
		panel.add(np);

		confirmpwd = new JLabel("Confirm New Password: ");
		confirmpwd.setBounds(280, 230, 200, 25);
		panel.add(confirmpwd);

		cpwd = new JPasswordField(70);
		cpwd.setBounds(280, 270, 165, 25);
		panel.add(cpwd);

		confirm = new JButton("Confirm");
		confirm.setBounds(250, 360, 80, 25);
		confirm.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				confirmActionPerformed(evt);
			}});
		panel.add(confirm);

		cancel = new JButton("Back");
		cancel.setBounds(390, 360, 80, 25);
		cancel.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				try {
					cancelActionPerformed(evt);
				} catch (InvalidInputException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}});
		panel.add(cancel);

		errorMessage = new JLabel();
		errorMessage.setBounds(410, 380, 300, 25);
		errorMessage.setForeground(Color.red);
		panel.add(errorMessage);
		
		success = new JLabel(""); 
		success.setBounds(150, 400, 400, 25);
		success.setForeground(Color.red);
		panel.add(success);

		frame.setVisible(true);
	}
	
	private static void confirmActionPerformed(ActionEvent evt) {
		// add code to save the change
		//check the confirm pwd same as pwd enter before
		//success update the new account, otherwise throw error
		// all input cannot be empty if it is throw error
		
		try {
			if(!(FlexiBookApplication.getCurrentuser().getPassword().equals(np))) {
				FlexibookController.UpdateAccount(FlexiBookApplication.getCurrentuser().getUsername(), FlexiBookApplication.getCurrentuser().getUsername(), np.getText());
				success.setText("Successfully updated the account!");
			}
		} catch(InvalidInputException e) {
			errorMessage.setText("**" + e.getMessage());
		}
	}
	private static void cancelActionPerformed(java.awt.event.ActionEvent evt) throws InvalidInputException {
		FlexiBookApplication.editownercancel();
	}
	
	public static void main(String[] args) {
		init_component_editOwnerAccount();
	}
	
	public EditOwnerAccount() {
		init_component_editOwnerAccount();
	}
	
}
