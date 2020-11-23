package view;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
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
import javax.swing.JPasswordField;
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
import ca.mcgill.ecse.flexibook.application.FlexiBookApplication;
import ca.mcgill.ecse.flexibook.model.FlexiBook;


public class MainPage extends JFrame {

	private static int Width = 700;
	private static int Length = 700;
	private static JFrame frame = new JFrame("FlexiBook Application");
	private static JLabel User = new JLabel();
	private static JLabel Password = new JLabel();
	private static JTextField usr_inputLogIn = new JTextField();
	private static JTextField usr_inputSignUp = new JTextField();
	private static JPasswordField passwrd_inputLogIn = new JPasswordField();
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
	private static JPasswordField confirm_input = new JPasswordField();
	private static JButton cancel = new JButton();
	private static JPanel panelLogIn = new JPanel();
	private static JPanel panelSignUp = new JPanel();
	private static FlexiBook flexi = new FlexiBook();
	private static FlexiBookApplication flex = new FlexiBookApplication();
	private static String password = new String();


	public MainPage() {
		init_component_LogIn();
	}
	private static void init_component_LogIn() {
		Font font1 = new Font("Times New Romans", Font.BOLD, 30);

		panelLogIn.setLayout(null);
		frame.add(panelLogIn);
		frame.setSize(Width, Length);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Title.setText("Welcome to FlexiBook!");
		Title.setBounds(175, 50, 400, 75);
		Title.setFont(font1);
		panelLogIn.add(Title);
		User.setText("Username: ");
		User.setBounds(200,150, 80, 25);
		panelLogIn.add(User);
		usr_inputLogIn.setBounds(300, 150, 165, 25);
		panelLogIn.add(usr_inputLogIn);
		Password.setText("Password: ");
		Password.setBounds(200,200, 80, 25);
		panelLogIn.add(Password);
		passwrd_inputLogIn.setBounds(300, 200, 165, 25);
		panelLogIn.add(passwrd_inputLogIn);
		logInBut.setText("Log In");
		logInBut.setBounds(350, 300, 120, 25);
		signUpLogIn.setText("Sign up");
		signUpLogIn.setBounds(200, 300, 120, 25);
		quit.setText("Quit");
		quit.setBounds(280, 500, 100, 25);
		logInBut.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				logInActionPerformed(evt);
			}
		});
		signUpLogIn.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				logInSignUpActionPerformed(evt);
			}
		});
		quit.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				quitActionPerformed(evt);
			}
		});
		errorMessageLogIn.setBounds(200, 350, 300, 15);
		errorMessageLogIn.setForeground(Color.red);
		panelLogIn.add(errorMessageLogIn);
		panelLogIn.add(logInBut);
		panelLogIn.add(signUpLogIn);
		panelLogIn.add(quit);
		frame.setVisible(true);
	}

	private static void logInActionPerformed(java.awt.event.ActionEvent evt) {
		error=null;
		try {
			String password = new String(passwrd_inputLogIn.getPassword());
			Boolean yes=FlexibookController.AttemptLogIn(usr_inputLogIn.getText(), password);
			if(yes) {
				errorMessageLogIn.setText("Succesfully Logged In"); //should we show the error message, because it should just directly make a transition to the customer main page
			}
		}
		catch(InvalidInputException e){
			error=e.getMessage();
			errorMessageLogIn.setText("**" + error);
		}
	}

	private static void logInSignUpActionPerformed(java.awt.event.ActionEvent evt) {	
		frame.remove(panelLogIn);
		errorMessageLogIn.setText(null);
		panelLogIn = new JPanel();
		init_component_signUp();
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
		errorMessageSignUp.setBounds(100, 350, 550, 15);
		errorMessageSignUp.setForeground(Color.red);
		panelSignUp.add(errorMessageSignUp);
		signUp.setText("Create Account");
		signUp.setBounds(200, 300, 120, 25);
		signUp.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				signUpActionPerformed(evt);
			}});
		panelSignUp.add(signUp);
		frame.setVisible(true);
		cancel.setText("Cancel");
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
			}
			FlexibookController.SignUpForCustomerAccount(usr_inputSignUp.getText(), password); //the confirm password is not checking if the password is equal to the confirm password.
			errorMessageSignUp.setText("Successfully created an account");
			System.out.print(flexi.getCustomers());
		}
		catch(InvalidInputException e) {
			error = e.getMessage();
			errorMessageSignUp.setText("**" + error);

		}

	}

	private static void cancelActionPerformed(java.awt.event.ActionEvent evt) {
		frame.remove(panelSignUp);
		panelSignUp = new JPanel();
		errorMessageSignUp.setText(null);
		init_component_LogIn();
	}

	private static void quitActionPerformed(java.awt.event.ActionEvent evt) {
		System.exit(0);
	}

	public static void main(String[] args) {
		flex.setflexibook(flexi);
		flexi.addCustomer("James", "Bonsoir");  //what is this line of code?
		init_component_LogIn();
		System.out.print(flexi.getCustomers());
	}
}
