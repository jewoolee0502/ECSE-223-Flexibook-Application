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

public class EditBusinessInfo extends JFrame {

	private static JLabel Title;
	private static JFrame frame;
	private static JPanel panel;
	private static String error;
	private static JLabel errorMessage = new JLabel("");
	//	private static JLabel newusr; 
	//	private static JTextField newuser;
	//	private static JLabel newpwd;
	//	private static JPasswordField np;
	//	private static JLabel confirmpwd;
	//	private static JPasswordField cpwd;
	private static JLabel change;
	private static JComboBox<String> day;
	private static JLabel newStart;
	private static JComboBox<String> newStartHour;
	private static JComboBox<String> newStartMin;
	private static JLabel newh;
	private static JComboBox<String> newhour;
	private static JComboBox<String> newmin;
	private static JButton update;
	private static JButton cancel;
	private static JLabel name;
	private static JTextField newName;
	private static JLabel address;
	private static JTextField newAddress;
	private static JLabel phoneNumber;
	private static JTextField newPhoneNumber;
	private static JLabel email;
	private static JTextField newEmail;

	public EditBusinessInfo() {
		init();
	}

	private static void init() {
		JPanel panel = new JPanel();
		JFrame frame = new JFrame();
		JLabel Title = new JLabel();
		Font font1 = new Font("Times New Romans", Font.BOLD, 20);
		frame.setSize(700, 700);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(panel);

		panel.setLayout(null);

		Title.setText("Edit Business Information:");
		Title.setBounds(200, 15, 500, 75);
		Title.setFont(font1);
		panel.add(Title);
		
		name = new JLabel("New Business Name:");
		name.setBounds(120, 100, 150, 25);
		panel.add(name);
		
		newName = new JTextField(70);
		newName.setBounds(120, 130, 150, 25);
		panel.add(newName);
		
		address = new JLabel("New Address:");
		address.setBounds(120, 180, 150, 25);
		panel.add(address);
		
		newAddress = new JTextField(70);
		newAddress.setBounds(120, 210, 150, 25);
		panel.add(newAddress);
		
		phoneNumber = new JLabel("New Phone Number:");
		phoneNumber.setBounds(120, 260, 150, 25);
		panel.add(phoneNumber);
		
		newPhoneNumber = new JTextField(70);
		newPhoneNumber.setBounds(120, 290, 150, 25);
		panel.add(newPhoneNumber);
		
		email = new JLabel("New Email:");
		email.setBounds(120, 340, 150, 25);
		panel.add(email);
		
		newEmail = new JTextField(70);
		newEmail.setBounds(120, 370, 150, 25);
		panel.add(newEmail);
		
		

		//		newusr = new JLabel("New Username:"); //not allowed to change the username of the owner
		//		newusr.setBounds(100, 100, 150, 25);
		//		panel.add(newusr);
		//
		//		newuser = new JTextField(70);
		//		newuser.setBounds(100,140,165,25);
		//		panel.add(newuser);
		//
		//		newpwd = new JLabel("New Password:");
		//		newpwd.setBounds(100, 180, 150, 25);
		//		panel.add(newpwd);
		//
		//		np = new JPasswordField(70);
		//		np.setBounds(100,220,165,25);
		//		panel.add(np);
		//
		//		confirmpwd = new JLabel("Confirm New Password:");
		//		confirmpwd.setBounds(100,260, 200, 25);
		//		panel.add(confirmpwd);
		//
		//		cpwd = new JPasswordField(70);
		//		cpwd.setBounds(100,300,165,25);
		//		panel.add(cpwd);

		change = new JLabel("Change Business Hour:");
		Font font2 = new Font("Times New Romans", Font.BOLD, 14);
		change.setBounds(420, 100, 170, 25);
		change.setFont(font2);
		panel.add(change);
		
		day= new JComboBox<String>();
		day.addItem("Day");
		day.addItem("Monday");
		day.addItem("Tuesday");
		day.addItem("Wednesday");
		day.addItem("Thursday");
		day.addItem("Friday");
		day.addItem("Saturday");
		day.addItem("Sunday");
		System.out.println("#items = " + day.getItemCount());
		day.setBounds(430, 140, 150, 25);
		panel.add(day);

		newStart = new JLabel("New Start Time:");
		newStart.setBounds(360, 200, 150, 25);
		panel.add(newStart);

		newStartHour = new JComboBox<String>();
		newStartHour.addItem("");
		newStartHour.addItem("09");
		newStartHour.addItem("10");
		newStartHour.addItem("11");
		newStartHour.addItem("12");
		newStartHour.addItem("13");
		newStartHour.addItem("14");
		newStartHour.addItem("15");
		newStartHour.addItem("16");
		newStartHour.addItem("17");
		newStartHour.addItem("18");
		newStartHour.addItem("19");
		newStartHour.addItem("20");
		newStartHour.addItem("21");
		newStartHour.addItem("22");
		
		newStartHour.setEditable(true);
		System.out.println("#items = " + newStartHour.getItemCount());
		newStartHour.setBounds(470, 200, 70, 25);
		panel.add(newStartHour);

		newStartMin = new JComboBox<String>();
		newStartMin.addItem("");
		newStartMin.addItem("00");
		newStartMin.addItem("10");
		newStartMin.addItem("20");
		newStartMin.addItem("30");
		newStartMin.addItem("40");
		newStartMin.addItem("50");

		newStartMin.setEditable(true);
		System.out.println("#items = " + newStartMin.getItemCount());
		newStartMin.setBounds(540, 200, 70, 25);
		panel.add(newStartMin);

		newh = new JLabel("New End Time:");
		newh.setBounds(360, 280, 150, 25);
		panel.add(newh);

		newhour = new JComboBox<String>();
		newhour.addItem("");
		newhour.addItem("09");
		newhour.addItem("10");
		newhour.addItem("11");
		newhour.addItem("12");
		newhour.addItem("13");
		newhour.addItem("14");
		newhour.addItem("15");
		newhour.addItem("16");
		newhour.addItem("17");
		newhour.addItem("18");
		newhour.addItem("19");
		newhour.addItem("20");
		newhour.addItem("21");
		newhour.addItem("22");
		
		newhour.setEditable(true);
		System.out.println("#items = " + newhour.getItemCount());
		newhour.setBounds(470, 280, 70, 25);
		panel.add(newhour);

		newmin = new JComboBox<String>();
		newmin.addItem("");
		newmin.addItem("00");
		newmin.addItem("10");
		newmin.addItem("20");
		newmin.addItem("30");
		newmin.addItem("40");
		newmin.addItem("50");

		newmin.setEditable(true);
		System.out.println("#items = " + newmin.getItemCount());
		newmin.setBounds(540, 280, 70, 25);
		panel.add(newmin);

		update = new JButton("Confirm");
		update.setBounds(185, 450, 80, 25);
		update.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				updateActionPerformed(evt);
			}});
		panel.add(update);


		cancel = new JButton("Cancel");
		cancel.setBounds(435, 450, 80, 25);
		cancel.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				cancelActionPerformed(evt);
			}});
		panel.add(cancel);

		errorMessage = new JLabel();
		errorMessage.setBounds(410, 380, 300, 25);
		errorMessage.setForeground(Color.red);
		panel.add(errorMessage);

		frame.setVisible(true);	
	}

	private static void updateActionPerformed(ActionEvent evt) {
		try {
			FlexibookController.setBusinessInformation(newName.toString(), 
					newAddress.toString(), newPhoneNumber.toString(), newEmail.toString());
		}catch (InvalidInputException e) {
			errorMessage.setText(e.getMessage());
		}

	}
	private static void cancelActionPerformed(java.awt.event.ActionEvent evt) {
		//add code to go back to the ownerview page
	}

	public static void main(String[] args) {
		init();
	}
	
}

