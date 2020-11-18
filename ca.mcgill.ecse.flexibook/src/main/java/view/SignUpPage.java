package view;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import ca.mcgill.ecse.flexibook.Controller.FlexibookController;
import ca.mcgill.ecse.flexibook.Controller.InvalidInputException;

public class SignUpPage {
	private static int Width = 300;
	private static int Length = 300;
	private static JFrame frame = new JFrame();
	private static JLabel User = new JLabel();
	private static JLabel Password = new JLabel();
	private static JLabel confirmPass = new JLabel();
	private static JTextField usr_input = new JTextField();
	private static JTextField passwrd_input = new JTextField();
	private static JTextField confirm_input = new JTextField();
	private static JButton signUp = new JButton();
	private static String error;
	private static JPanel panel = new JPanel();
public static void main(String[] args) {

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
	confirmPass.setText("Confirm password");
	confirmPass.setBounds(10, 80, 80, 25);
	panel.add(confirmPass);
	confirm_input.setBounds(100,80,165,25);
	panel.add(confirm_input);
	signUp.setText("Sign up");
	signUp.setBounds(90, 110, 80, 25);
	signUp.addActionListener(new java.awt.event.ActionListener() {
		public void actionPerformed(java.awt.event.ActionEvent evt) {
			signUpActionPerformed(evt);
		}});
	panel.add(signUp);
	frame.setVisible(true);
}
private static void signUpActionPerformed(java.awt.event.ActionEvent evt) {
	if(passwrd_input.getText().equals(confirm_input.getText())) {
		try {
		FlexibookController.SignUpForCustomerAccount(usr_input.getText(), passwrd_input.getText());
	}
		catch(InvalidInputException e) {
			error = e.getMessage();
		}
		}
}

}
