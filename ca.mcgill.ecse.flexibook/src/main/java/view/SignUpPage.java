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
import ca.mcgill.ecse.flexibook.application.FlexiBookApplication;
import ca.mcgill.ecse.flexibook.model.FlexiBook;

public class SignUpPage{
	private static int Width = 700;
	private static int Length = 700;
	public static JFrame frame = new JFrame("FlexiBook Application");
	private static JLabel User = new JLabel();
	private static JLabel Password = new JLabel();
	private static JTextField usr_inputSignUp = new JTextField();
	private static JPasswordField passwrd_inputSignUp = new JPasswordField();
	private static JButton logInBut = new JButton();
	private static JButton signUpLogIn = new JButton();
	private static JButton signUp = new JButton();
	private static JButton quit = new JButton();
	private static String error;
	private static JLabel Title = new JLabel();
	private static JLabel confirmPass = new JLabel();
	private static JLabel errorMessageLogIn = new JLabel("");
	private static JLabel errorMessageSignUp = new JLabel("");
	private static JLabel message = new JLabel("");
	private static JPasswordField confirm_input = new JPasswordField();
	private static JButton cancel = new JButton();
	private static JPanel panelLogIn = new JPanel();
	private static JPanel panelSignUp = new JPanel();
	private static FlexiBook flexi = new FlexiBook();
	private static FlexiBookApplication flex = new FlexiBookApplication();
	private static String password = new String();

	public static void main(String[] args) {
		init_component_signUp();
	}

	public SignUpPage() {
		init_component_signUp();
	}

	public static void refreshE() {
		errorMessageSignUp.setText("");
	}

	public static void refreshText2() {
		usr_inputSignUp.setText("");
		passwrd_inputSignUp.setText("");
		confirm_input.setText("");
	}

	private static void init_component_signUp() {
		Font font1 = new Font("Times New Romans", Font.BOLD, 20);

		panelSignUp.setLayout(null); //change the layout!
		frame.add(panelSignUp);
		frame.setSize(Width, Length);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Title.setText("Create your FlexiBook Account");
		Title.setBounds(180, 50, 400, 75);
		Title.setFont(font1);
		panelSignUp.add(Title);
		User.setText("Username: ");
		User.setBounds(200, 150, 80, 25);
		panelSignUp.add(User);
		usr_inputSignUp.setBounds(300, 150, 165, 25);
		panelSignUp.add(usr_inputSignUp);
		Password.setText("Password: ");
		Password.setBounds(200, 200, 80, 25);
		panelSignUp.add(Password);
		passwrd_inputSignUp.setBounds(300, 200, 165, 25);
		panelSignUp.add(passwrd_inputSignUp);
		confirmPass.setText("Confirm Password: ");
		confirmPass.setBounds(150, 250, 150, 25);
		panelSignUp.add(confirmPass);
		confirm_input.setBounds(300, 250, 165, 25);
		panelSignUp.add(confirm_input);
		errorMessageSignUp.setBounds(100, 350, 550, 25);
		errorMessageSignUp.setForeground(Color.red);
		panelSignUp.add(errorMessageSignUp);
		panelSignUp.add(message);
		signUp.setText("Create Account");
		signUp.setBounds(200, 300, 120, 25);
		signUp.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				signUpActionPerformed(evt);
			}});
		panelSignUp.add(signUp);
		frame.setVisible(true);
		cancel.setText("Back");
		cancel.setBounds(350, 300, 100, 25);
		cancel.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				cancelActionPerformed(evt);
			}});
		panelSignUp.add(cancel);
		quit.setText("Quit");
		quit.setBounds(280, 500, 100, 25);
		quit.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				quitActionPerformed(evt);
			}
		});
		panelSignUp.add(quit);
		frame.setVisible(true);
	}

	private static void signUpActionPerformed(java.awt.event.ActionEvent evt) {
		String first_pw = new String(String.valueOf(passwrd_inputSignUp.getPassword()));
		String second_pw = new String(String.valueOf(confirm_input.getPassword()));
		try {
			if(first_pw.equals(second_pw)) {
				char[] character=passwrd_inputSignUp.getPassword();
				password = String.valueOf(character);

				FlexibookController.SignUpForCustomerAccount(usr_inputSignUp.getText(), password); //the confirm password is not checking if the password is equal to the confirm password.
				refreshE();
				errorMessageSignUp.setText("Successfully created an account! \n Now Log In with the account you have just created!");
				System.out.print(flexi.getCustomers());
				refreshText2();
			}
			else {
				refreshE();
				errorMessageSignUp.setText("Password Confirmation Failed!");
			}
		}
		catch(InvalidInputException e) {
			refreshText2();
			refreshE();
			error = e.getMessage();
			errorMessageSignUp.setText("**" + error);
		}
	}

	private static void cancelActionPerformed(java.awt.event.ActionEvent evt) {
		FlexiBookApplication.toMainpage();
	}

	private static void quitActionPerformed(java.awt.event.ActionEvent evt) {
		System.exit(0);
	}
}
