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
import ca.mcgill.ecse.flexibook.model.Business;

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
	private static JComboBox<String> existingHour;
	private static JComboBox<String> existingMin;
	private static JLabel newh;
	private static JLabel existingTime;
	private static JComboBox<String> newhour;
	private static JComboBox<String> newmin;
	private static JButton update;
	private static JButton cancel;
	private static JButton add;
	private static JButton set;
	private static JButton updateHr;
	private static JButton delete;
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

	public static void refreshEditBusiness() {
		newName.setText("");
		newAddress.setText("");
		newPhoneNumber.setText("");
		newEmail.setText("");
	}
	
	public static void refreshEditBusinessComboBox() {
		day.setSelectedIndex(0);
		existingHour.setSelectedIndex(0);
		existingMin.setSelectedIndex(0);
		newStartHour.setSelectedIndex(0);
		newStartMin.setSelectedIndex(0);
		newhour.setSelectedIndex(0);
		newmin.setSelectedIndex(0);
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

		existingTime = new JLabel("Existing Start Time (hh:mm):");
		existingTime.setBounds(280, 180, 220, 25);
		panel.add(existingTime);
		
		existingHour = new JComboBox<String>();
		existingHour.addItem("");
		existingHour.addItem("00");
		existingHour.addItem("01");
		existingHour.addItem("02");
		existingHour.addItem("03");
		existingHour.addItem("04");
		existingHour.addItem("05");
		existingHour.addItem("06");
		existingHour.addItem("07");
		existingHour.addItem("08");
		existingHour.addItem("09");
		existingHour.addItem("10");
		existingHour.addItem("11");
		existingHour.addItem("12");
		existingHour.addItem("13");
		existingHour.addItem("14");
		existingHour.addItem("15");
		existingHour.addItem("16");
		existingHour.addItem("17");
		existingHour.addItem("18");
		existingHour.addItem("19");
		existingHour.addItem("20");
		existingHour.addItem("21");
		existingHour.addItem("22");
		existingHour.addItem("23");

		existingHour.setEditable(true);
		System.out.println("#items = " + existingHour.getItemCount());
		existingHour.setBounds(470, 180, 70, 25);
		panel.add(existingHour);
		
		existingMin = new JComboBox<String>();
		existingMin.addItem("");
		existingMin.addItem("00");
		existingMin.addItem("01");
		existingMin.addItem("02");
		existingMin.addItem("03");
		existingMin.addItem("04");
		existingMin.addItem("05");
		existingMin.addItem("06");
		existingMin.addItem("07");
		existingMin.addItem("08");
		existingMin.addItem("09");
		existingMin.addItem("10");
		existingMin.addItem("11");
		existingMin.addItem("12");
		existingMin.addItem("13");
		existingMin.addItem("14");
		existingMin.addItem("15");
		existingMin.addItem("16");
		existingMin.addItem("17");
		existingMin.addItem("18");
		existingMin.addItem("19");
		existingMin.addItem("20");
		existingMin.addItem("21");
		existingMin.addItem("22");
		existingMin.addItem("23");
		existingMin.addItem("24");
		existingMin.addItem("25");
		existingMin.addItem("26");
		existingMin.addItem("27");
		existingMin.addItem("28");
		existingMin.addItem("29");
		existingMin.addItem("30");
		existingMin.addItem("31");
		existingMin.addItem("32");
		existingMin.addItem("33");
		existingMin.addItem("34");
		existingMin.addItem("35");
		existingMin.addItem("36");
		existingMin.addItem("37");
		existingMin.addItem("38");
		existingMin.addItem("39");
		existingMin.addItem("40");
		existingMin.addItem("41");
		existingMin.addItem("42");
		existingMin.addItem("43");
		existingMin.addItem("44");
		existingMin.addItem("45");
		existingMin.addItem("46");
		existingMin.addItem("47");
		existingMin.addItem("48");
		existingMin.addItem("49");
		existingMin.addItem("50");
		existingMin.addItem("51");
		existingMin.addItem("52");
		existingMin.addItem("53");
		existingMin.addItem("54");
		existingMin.addItem("55");
		existingMin.addItem("56");
		existingMin.addItem("57");
		existingMin.addItem("58");
		existingMin.addItem("59");

		existingMin.setEditable(true);
		System.out.println("#items = " + existingMin.getItemCount());
		existingMin.setBounds(540, 180, 70, 25);
		panel.add(existingMin);
		
		newStart = new JLabel("New Start Time (hh:mm):");
		newStart.setBounds(310, 230, 180, 25);
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
		newStartHour.setBounds(470, 230, 70, 25);
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
		newStartMin.setBounds(540, 230, 70, 25);
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

		update = new JButton("Update Business Info");
		update.setBounds(300, 500, 180, 25);
		update.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				updateActionPerformed(evt);
			}
		});
		panel.add(update);
		
		updateHr = new JButton("Update Business Hour");
		updateHr.setBounds(300, 370, 180, 25);
		updateHr.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				updateHourActionPerformed(evt);
			}
		});
		panel.add(updateHr);
		
		set = new JButton("Set-Up Business Info");
		set.setBounds(50, 500, 180, 25);
		set.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				setActionPerformed(evt);
			}
		});
		panel.add(set);

		add = new JButton("Add Business Hour");
		add.setBounds(520, 370, 150, 25);
		add.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				addActionPerformed(evt);
			}
		});
		panel.add(add);

		delete = new JButton("Delete Business Hour");
		delete.setBounds(400, 410, 180, 25);
		delete.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				deleteActionPerformed(evt);
			}
		});
		panel.add(delete);

		cancel = new JButton("Back");
		cancel.setBounds(580, 500, 80, 25);
		cancel.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				cancelActionPerformed(evt);
			}
		});
		panel.add(cancel);

		errorMessage = new JLabel();
		errorMessage.setBounds(100, 550, 600, 25);
		errorMessage.setForeground(Color.red);
		panel.add(errorMessage);

		frame.setVisible(true);
	}

	private static void updateActionPerformed(ActionEvent evt) {
		try {
			FlexibookController.setBusinessInformation(newName.getText(), newAddress.getText(), newPhoneNumber.getText(),
					newEmail.getText());
			errorMessage.setText("Successfully updated the business information!");
			refreshEditBusiness();
		} catch (InvalidInputException e) {
			errorMessage.setText(e.getMessage());
		}

	}
	
	private static void updateHourActionPerformed(ActionEvent evt) {
		try {
			if(FlexiBookApplication.getflexibook() != null) {
				if(FlexiBookApplication.getflexibook().getBusiness() != null) {
					String newstartTime = newStartHour.getSelectedItem().toString() + ":" + newStartMin.getSelectedItem().toString();
					String newEndTime = newhour.getSelectedItem().toString() + ":" + newmin.getSelectedItem().toString();
					String ExistingStartTime = existingHour.getSelectedItem().toString() + ":" + existingMin.getSelectedItem().toString();
					String ExistingDay = day.getSelectedItem().toString();
					String newDay = day.getSelectedItem().toString();
					FlexibookController.UpdateExistingBusinessHour(ExistingDay, ExistingStartTime, newDay, newstartTime, newEndTime);
					errorMessage.setText("Successfully updated the business hour!");
					refreshEditBusiness();
				}
			}
			
		} catch(InvalidInputException e) {
			errorMessage.setText(e.getMessage());
		}
	}
	
	private static void setActionPerformed(ActionEvent evt) {
		try {
			FlexibookController.setBusinessInformation(newName.getText(), newAddress.getText(), newPhoneNumber.getText(), newEmail.getText());
			errorMessage.setText("Successfully setted the business information!");
			refreshEditBusiness();
		} catch (InvalidInputException e) {
			errorMessage.setText(e.getMessage());
		}
	}

	private static void addActionPerformed(ActionEvent evt) {
		//this action adds more business hour for the same day
		try {
			if(FlexiBookApplication.getflexibook() != null) {
				if(FlexiBookApplication.getflexibook().getBusiness() != null) {
					String string2 = newStartHour.getSelectedItem().toString() + ":" + newStartMin.getSelectedItem().toString();
					String string3 = newhour.getSelectedItem().toString() + ":" + newmin.getSelectedItem().toString();
					String string1 = day.getSelectedItem().toString();
					FlexibookController.addNewBusinessHour(string1, string2, string3);
					errorMessage.setText("Successfully added a business hour!");
					refreshEditBusinessComboBox();
				}
			}
		} catch(InvalidInputException e) {
			errorMessage.setText(e.getMessage());
		}
	}
	
	private static void deleteActionPerformed(ActionEvent evt) {
//		try {
//			FlexibookController.removerBusinessHour(Day, starTime);
//		} catch(InvalidInputException e) {
//			
//		}
	}

	private static void cancelActionPerformed(java.awt.event.ActionEvent evt) {
		FlexiBookApplication.editbusinessinfocancel();
	}

	public static void main(String[] args) {
		init();
	}

	public static void refresh() {
		errorMessage.setText(" ");
	}

}

