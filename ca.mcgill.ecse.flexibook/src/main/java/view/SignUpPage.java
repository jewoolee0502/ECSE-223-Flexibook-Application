package view;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import ca.mcgill.ecse.flexibook.Controller.FlexibookController;
import ca.mcgill.ecse.flexibook.Controller.InvalidInputException;

public class SignUpPage extends JFrame {
	private static int Width = 700;
	private static int Length = 700;
	private static JFrame frame = new JFrame();
	private static JLabel Title = new JLabel();
	private static JLabel User = new JLabel();
	private static JLabel Password = new JLabel();
	private static JLabel confirmPass = new JLabel();
	private static JLabel errorMessage = new JLabel("");
	private static JTextField usr_input = new JTextField();
	private static JPasswordField passwrd_input = new JPasswordField();
	private static JPasswordField confirm_input = new JPasswordField();
	private static JButton signUp = new JButton();
	private static JButton cancel = new JButton();
	private static String error;
	private static JPanel panel = new JPanel();

	public static void main(String[] args) {

		Font font1 = new Font("Times New Romans", Font.BOLD, 20);

		panel.setLayout(null); //change the layout!
		frame.add(panel);
		frame.setSize(Width, Length);
		Title.setText("Create your FlexiBook Account");
		Title.setBounds(180, 50, 400, 75);
		Title.setFont(font1);
		panel.add(Title);
		User.setText("Username: ");
		User.setBounds(200, 150, 80, 25);
		panel.add(User);
		usr_input.setBounds(300, 150, 165, 25);
		panel.add(usr_input);
		Password.setText("Password: ");
		Password.setBounds(200, 200, 80, 25);
		panel.add(Password);
		passwrd_input.setBounds(300, 200, 165, 25);
		panel.add(passwrd_input);
		confirmPass.setText("Confirm Password: ");
		confirmPass.setBounds(150, 250, 150, 25);
		panel.add(confirmPass);
		confirm_input.setBounds(300, 250, 165, 25);
		panel.add(confirm_input);
		errorMessage.setBounds(200, 350, 300, 15);
		errorMessage.setForeground(Color.red);
		panel.add(errorMessage);
		signUp.setText("Create Account");
		signUp.setBounds(200, 300, 120, 25);
		signUp.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				signUpActionPerformed(evt);
			}});
		panel.add(signUp);
		frame.setVisible(true);
		cancel.setText("Cancel");
		cancel.setBounds(350, 300, 100, 25);
		panel.add(cancel);
		frame.setVisible(true);
	}

	private static void signUpActionPerformed(java.awt.event.ActionEvent evt) {
		if(passwrd_input.getText().equals(confirm_input.getText())) {
			try {
				FlexibookController.SignUpForCustomerAccount(usr_input.getText(), passwrd_input.getText()); //the confirm password is not checking if the password is equal to the confirm password.
				errorMessage.setText("Successfully created an account"); //if successful, create a button, for log in and make the customer log in to the created account
			}
			catch(InvalidInputException e) {
				error = e.getMessage();
				errorMessage.setText("**" + error);

			}
		}
	}

}
