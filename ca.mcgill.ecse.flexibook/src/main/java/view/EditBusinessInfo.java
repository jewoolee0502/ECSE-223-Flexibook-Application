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

public class EditBusinessInfo {
	
	private static JLabel Title;
	private static JFrame frame;
	private static JPanel panel;
	private static String error;
	private static JLabel errorMessage = new JLabel("");
	private static JLabel newusr; 
	private static JTextField newuser;
	private static JLabel newpwd;
	private static JPasswordField np;
	private static JLabel confirmpwd;
	private static JPasswordField cpwd;
	private static JLabel change;
	private static JComboBox<String> day;
	private static JLabel oldh;
	private static JComboBox<String> oldhour;
	private static JComboBox<String> oldmin;
	private static JLabel newh;
	private static JComboBox<String> newhour;
	private static JComboBox<String> newmin;
	private static JButton update;
	private static JButton cancel;
	public EditBusinessInfo() {
	  init();
	}
	public static void main(String[] args) {
	  init();
	  }private static void init() {
		JPanel panel = new JPanel();
		JFrame frame = new JFrame();
		JLabel Title = new JLabel();
		Font font1 = new Font("Times New Romans", Font.BOLD, 20);
		frame.setSize(700, 700);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(panel);
		
		panel.setLayout(null);
		
		Title.setText("Editing Business Information:");
		Title.setBounds(170, 15, 400, 75);
		Title.setFont(font1);
		panel.add(Title);
		
		newusr = new JLabel("New Username:");
		newusr.setBounds(100, 100, 150, 25);
		panel.add(newusr);
		
		newuser = new JTextField(70);
		newuser.setBounds(100,140,165,25);
		panel.add(newuser);
		
		newpwd = new JLabel("New Password:");
		newpwd.setBounds(100, 180, 150, 25);
		panel.add(newpwd);
		
		np = new JPasswordField(70);
		np.setBounds(100,220,165,25);
		panel.add(np);
		
		confirmpwd = new JLabel("Confirm New Password:");
		confirmpwd.setBounds(100,260, 200, 25);
		panel.add(confirmpwd);
		
		cpwd = new JPasswordField(70);
		cpwd.setBounds(100,300,165,25);
		panel.add(cpwd);
		
		change = new JLabel("Change Business Hour:");
		change.setBounds(320, 100, 150, 25);
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
		System.out.println("#items=" + day.getItemCount());
		day.setBounds(320,140,150,25);
		panel.add(day);
		
		oldh = new JLabel("Old-Time:");
		oldh.setBounds(320,200, 150, 25);
		panel.add(oldh);
		
		oldhour = new JComboBox<String>();
		oldhour.addItem("");
		oldhour.addItem("09");
		oldhour.addItem("10");
		oldhour.addItem("11");
		oldhour.addItem("12");
		oldhour.addItem("13");
		oldhour.addItem("14");
		oldhour.addItem("15");
		oldhour.addItem("16");
		oldhour.addItem("17");
		oldhour.addItem("18");
		oldhour.addItem("19");
		oldhour.addItem("20");
		oldhour.addItem("21");
		oldhour.addItem("22");
		oldhour.setEditable(true);
		System.out.println("#items=" + oldhour.getItemCount());
		oldhour.setBounds(400,200,70,25);
		panel.add(oldhour);
		
		oldmin = new JComboBox<String>();
		oldmin.addItem("");
		oldmin.addItem("00");
		oldmin.addItem("10");
		oldmin.addItem("20");
		oldmin.addItem("30");
		oldmin.addItem("40");
		oldmin.addItem("50");
	
		oldmin.setEditable(true);
		System.out.println("#items=" + oldmin.getItemCount());
		oldmin.setBounds(470,200,70,25);
		panel.add(oldmin);
		
		newh = new JLabel("New-Time:");
		newh.setBounds(320, 280, 150, 25);
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
		System.out.println("#items=" + newhour.getItemCount());
		newhour.setBounds(400, 280,70,25);
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
		System.out.println("#items=" + newmin.getItemCount());
		newmin.setBounds(470,280,70,25);
		panel.add(newmin);
		
		update = new JButton("Confirm");
		update.setBounds(185, 380, 80, 25);
		update.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				updateActionPerformed(evt);
			}});
		panel.add(update);
		
		
		cancel = new JButton("Cancel");
		cancel.setBounds(435, 380, 80, 25);
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
		// add code to save the change
		//check the confirm pwd same as pwd enter before
		//success update the new business info, otherwise throw error
		//at least a input shouldn''t be empty
		//the old time can be the same as the new one settting
	}
	private static void cancelActionPerformed(java.awt.event.ActionEvent evt) {
		//add code to go back to the ownerview page
	}

}
