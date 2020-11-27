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


public class MainPage {

	private static int Width = 700;
	private static int Length = 700;
	public static JFrame frame = new JFrame("FlexiBook Application");
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
	private static JLabel message = new JLabel("");
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
	
	public static void refreshText1() {
		usr_inputLogIn.setText("");
		passwrd_inputLogIn.setText("");
	}
	
	public static void refreshE() {
		errorMessageLogIn.setText("");
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
			Boolean yes = FlexibookController.AttemptLogIn(usr_inputLogIn.getText(), password);
			if(yes) {
				//errorMessageLogIn.setText("Succesfully Logged In"); //should we show the error message, because it should just directly make a transition to the customer main page
				if(FlexiBookApplication.getCurrentuser().getUsername().equals(FlexiBookApplication.getflexibook().getOwner().getUsername())&&
						FlexiBookApplication.getCurrentuser().getPassword().equals(FlexiBookApplication.getflexibook().getOwner().getPassword())) {
					FlexiBookApplication.toowner();
				}else
					FlexiBookApplication.tocustomer();
			}
			refreshText1();
			refreshE();
		}
		catch(InvalidInputException e){
			refreshText1();
			refreshE();
			error=e.getMessage();
			errorMessageLogIn.setText("**" + error);
		}
	}

	private static void logInSignUpActionPerformed(java.awt.event.ActionEvent evt) {	
		FlexiBookApplication.toSignUp();
	}

	private static void quitActionPerformed(java.awt.event.ActionEvent evt) {
		System.exit(0);
	}

	public static void main(String[] args) {
		init_component_LogIn();
	}
}
