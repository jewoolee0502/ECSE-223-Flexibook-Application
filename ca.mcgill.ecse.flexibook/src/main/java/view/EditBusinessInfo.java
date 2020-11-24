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

public class EditBusinessInfo {

	private static JLabel Title = new JLabel();
	public static JFrame frame = new JFrame();
	private static JPanel panel = new JPanel();
	private static String error;
	private static JLabel errorMessage = new JLabel("");
	// private static JLabel newusr;
	// private static JTextField newuser;
	// private static JLabel newpwd;
	// private static JPasswordField np;
	// private static JLabel confirmpwd;
	// private static JPasswordField cpwd;
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
	private static JButton add;
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



		// newusr = new JLabel("New Username:"); //not allowed to change the username of the owner
		// newusr.setBounds(100, 100, 150, 25);
		// panel.add(newusr);
		//
		// newuser = new JTextField(70);
		// newuser.setBounds(100,140,165,25);
		// panel.add(newuser);
		//
		// newpwd = new JLabel("New Password:");
		// newpwd.setBounds(100, 180, 150, 25);
		// panel.add(newpwd);
		//
		// np = new JPasswordField(70);
		// np.setBounds(100,220,165,25);
		// panel.add(np);
		//
		// confirmpwd = new JLabel("Confirm New Password:");
		// confirmpwd.setBounds(100,260, 200, 25);
		// panel.add(confirmpwd);
		//
		// cpwd = new JPasswordField(70);
		// cpwd.setBounds(100,300,165,25);
		// panel.add(cpwd);

		change = new JLabel("Change Business Hour:");
		Font font2 = new Font("Times New Romans", Font.BOLD, 14);
		change.setBounds(420, 100, 170, 25);
		change.setFont(font2);
		panel.add(change);

		day = new JComboBox<String>();
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

		newStart = new JLabel("New Start Time (hh:mm):");
		newStart.setBounds(310, 200, 180, 25);
		panel.add(newStart);

		newStartHour = new JComboBox<String>();
		newStartHour.addItem("");
		newStartHour.addItem("00");
		newStartHour.addItem("01");
		newStartHour.addItem("02");
		newStartHour.addItem("03");
		newStartHour.addItem("04");
		newStartHour.addItem("05");
		newStartHour.addItem("06");
		newStartHour.addItem("07");
		newStartHour.addItem("08");
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
		newStartHour.addItem("23");

		newStartHour.setEditable(true);
		System.out.println("#items = " + newStartHour.getItemCount());
		newStartHour.setBounds(470, 200, 70, 25);
		panel.add(newStartHour);

		newStartMin = new JComboBox<String>();
		newStartMin.addItem("");
		newStartMin.addItem("00");
		newStartMin.addItem("01");
		newStartMin.addItem("02");
		newStartMin.addItem("03");
		newStartMin.addItem("04");
		newStartMin.addItem("05");
		newStartMin.addItem("06");
		newStartMin.addItem("07");
		newStartMin.addItem("08");
		newStartMin.addItem("09");
		newStartMin.addItem("10");
		newStartMin.addItem("11");
		newStartMin.addItem("12");
		newStartMin.addItem("13");
		newStartMin.addItem("14");
		newStartMin.addItem("15");
		newStartMin.addItem("16");
		newStartMin.addItem("17");
		newStartMin.addItem("18");
		newStartMin.addItem("19");
		newStartMin.addItem("20");
		newStartMin.addItem("21");
		newStartMin.addItem("22");
		newStartMin.addItem("23");
		newStartMin.addItem("24");
		newStartMin.addItem("25");
		newStartMin.addItem("26");
		newStartMin.addItem("27");
		newStartMin.addItem("28");
		newStartMin.addItem("29");
		newStartMin.addItem("30");
		newStartMin.addItem("31");
		newStartMin.addItem("32");
		newStartMin.addItem("33");
		newStartMin.addItem("34");
		newStartMin.addItem("35");
		newStartMin.addItem("36");
		newStartMin.addItem("37");
		newStartMin.addItem("38");
		newStartMin.addItem("39");
		newStartMin.addItem("40");
		newStartMin.addItem("41");
		newStartMin.addItem("42");
		newStartMin.addItem("43");
		newStartMin.addItem("44");
		newStartMin.addItem("45");
		newStartMin.addItem("46");
		newStartMin.addItem("47");
		newStartMin.addItem("48");
		newStartMin.addItem("49");
		newStartMin.addItem("50");
		newStartMin.addItem("51");
		newStartMin.addItem("52");
		newStartMin.addItem("53");
		newStartMin.addItem("54");
		newStartMin.addItem("55");
		newStartMin.addItem("56");
		newStartMin.addItem("57");
		newStartMin.addItem("58");
		newStartMin.addItem("59");

		newStartMin.setEditable(true);
		System.out.println("#items = " + newStartMin.getItemCount());
		newStartMin.setBounds(540, 200, 70, 25);
		panel.add(newStartMin);

		newh = new JLabel("New End Time (hh:mm):");
		newh.setBounds(320, 280, 160, 25);
		panel.add(newh);

		newhour = new JComboBox<String>();
		newhour.addItem("");
		newhour.addItem("00");
		newhour.addItem("01");
		newhour.addItem("02");
		newhour.addItem("03");
		newhour.addItem("04");
		newhour.addItem("05");
		newhour.addItem("06");
		newhour.addItem("07");
		newhour.addItem("08");
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
		newhour.addItem("23");

		newhour.setEditable(true);
		System.out.println("#items = " + newhour.getItemCount());
		newhour.setBounds(470, 280, 70, 25);
		panel.add(newhour);

		newmin = new JComboBox<String>();
		newmin.addItem("");
		newmin.addItem("00");
		newmin.addItem("01");
		newmin.addItem("02");
		newmin.addItem("03");
		newmin.addItem("04");
		newmin.addItem("05");
		newmin.addItem("06");
		newmin.addItem("07");
		newmin.addItem("08");
		newmin.addItem("09");
		newmin.addItem("10");
		newmin.addItem("11");
		newmin.addItem("12");
		newmin.addItem("13");
		newmin.addItem("14");
		newmin.addItem("15");
		newmin.addItem("16");
		newmin.addItem("17");
		newmin.addItem("18");
		newmin.addItem("19");
		newmin.addItem("20");
		newmin.addItem("21");
		newmin.addItem("22");
		newmin.addItem("23");
		newmin.addItem("24");
		newmin.addItem("25");
		newmin.addItem("26");
		newmin.addItem("27");
		newmin.addItem("28");
		newmin.addItem("29");
		newmin.addItem("30");
		newmin.addItem("31");
		newmin.addItem("32");
		newmin.addItem("33");
		newmin.addItem("34");
		newmin.addItem("35");
		newmin.addItem("36");
		newmin.addItem("37");
		newmin.addItem("38");
		newmin.addItem("39");
		newmin.addItem("40");
		newmin.addItem("41");
		newmin.addItem("42");
		newmin.addItem("43");
		newmin.addItem("44");
		newmin.addItem("45");
		newmin.addItem("46");
		newmin.addItem("47");
		newmin.addItem("48");
		newmin.addItem("49");
		newmin.addItem("50");
		newmin.addItem("51");
		newmin.addItem("52");
		newmin.addItem("53");
		newmin.addItem("54");
		newmin.addItem("55");
		newmin.addItem("56");
		newmin.addItem("57");
		newmin.addItem("58");
		newmin.addItem("59");

		newmin.setEditable(true);
		System.out.println("#items = " + newmin.getItemCount());
		newmin.setBounds(540, 280, 70, 25);
		panel.add(newmin);

		update = new JButton("Update");
		update.setBounds(185, 450, 80, 25);
		update.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				updateActionPerformed(evt);
			}
		});
		panel.add(update);

		add = new JButton("Add");
		add.setBounds(420, 450, 70, 25);
		add.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				addActionPerformed(evt);
			}
		});
		panel.add(add);


		cancel = new JButton("Back");
		cancel.setBounds(580, 450, 80, 25);
		cancel.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				cancelActionPerformed(evt);
			}
		});
		panel.add(cancel);

		errorMessage = new JLabel();
		errorMessage.setBounds(410, 380, 300, 25);
		errorMessage.setForeground(Color.red);
		panel.add(errorMessage);

		frame.setVisible(true);
	}

	private static void updateActionPerformed(ActionEvent evt) {
		try {
			FlexibookController.setBusinessInformation(newName.toString(), newAddress.toString(), newPhoneNumber.toString(),
					newEmail.toString());
		} catch (InvalidInputException e) {
			errorMessage.setText(e.getMessage());
		}

	}

	private static void addActionPerformed(ActionEvent evt) {
		//this action adds more business hour for the same day
	}

	private static void cancelActionPerformed(java.awt.event.ActionEvent evt) {
		FlexiBookApplication.editbusinessinfocancel();
	}

	public static void main(String[] args) {
		init();
	}

}

