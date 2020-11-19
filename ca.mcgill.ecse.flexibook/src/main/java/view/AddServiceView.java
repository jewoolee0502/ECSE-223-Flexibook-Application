package view;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import ca.mcgill.ecse.flexibook.Controller.FlexibookController;
import ca.mcgill.ecse.flexibook.Controller.InvalidInputException;

public class AddServiceView {
	
	private static JFrame frame = new JFrame();
	private static JPanel panel;
	private static JLabel service;
	private static JLabel duration;
	private static JLabel downtimestart;
	private static JComboBox<String> downstart;
	private static JLabel downtimeduration;
	private static JComboBox<String> downduration;
	private static JTextField newservice;
	private static JButton create;
	private static JButton cancel;
	private static JLabel errorMessage = new JLabel("");
	private static JLabel success;
	private static JComboBox<String> timeduration;
	
	public static void main(String[] args) {
		JPanel panel = new JPanel();
		JFrame frame = new JFrame();
		JLabel Title = new JLabel();
	frame.setSize(550, 550);
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	frame.add(panel);
	
	
	panel.setLayout(null);
		
	service = new JLabel("Service Name");
	service.setBounds(70, 70, 80, 25);
	panel.add(service);
	
	newservice = new JTextField(70);
	newservice.setBounds(70,110,165,25);
	panel.add(newservice);
	
	duration = new JLabel("Service duration");
	duration.setBounds(70,150,120,25);
	panel.add(duration);
	
	timeduration = new JComboBox<String>();
	timeduration.addItem("");
	timeduration.addItem("5");
	timeduration.addItem("10");
	timeduration.addItem("15");
	timeduration.addItem("20");
	timeduration.addItem("25");
	timeduration.addItem("30");
	timeduration.addItem("35");
	timeduration.addItem("40");
	timeduration.addItem("45");
	timeduration.addItem("50");
	timeduration.addItem("55");
	timeduration.addItem("60");
	timeduration.addItem("65");
	timeduration.addItem("70");
	timeduration.addItem("75");
	timeduration.addItem("80");
	timeduration.addItem("85");
	timeduration.addItem("90");
	timeduration.addItem("95");
	timeduration.addItem("100");
	timeduration.setEditable(true);
    System.out.println("#items=" + timeduration.getItemCount());
	timeduration.setBounds(70,190,120,25);
	panel.add(timeduration);
	
	downtimestart = new JLabel("DowntimeStart");
	downtimestart.setBounds(70,230,120,25);
	panel.add(downtimestart);
	
	downstart = new JComboBox<String>();
	downstart.addItem("");
	downstart.addItem("0");
	downstart.addItem("5");
	downstart.addItem("10");
	downstart.addItem("15");
	downstart.addItem("20");
	downstart.addItem("25");
	downstart.addItem("30");
	downstart.addItem("35");
	downstart.addItem("40");
	downstart.addItem("45");
	downstart.addItem("50");
	downstart.setEditable(true);
    System.out.println("#items=" + downstart.getItemCount());
    downstart.setBounds(70, 270, 120, 25);
    panel.add(downstart);
    
    downtimeduration = new JLabel("Downtime Duration");
    downtimeduration.setBounds(300, 230, 120, 25);
    panel.add(downtimeduration);
    
    downduration = new JComboBox<String>();
    downduration.addItem("");
    downduration.addItem("0");
    downduration.addItem("5");
    downduration.addItem("10");
    downduration.addItem("15");
    downduration.addItem("20");
    downduration.addItem("25");
    downduration.addItem("30");
    downduration.addItem("35");
    downduration.addItem("40");
    downduration.addItem("45");
    downduration.addItem("50");
    downduration.setEditable(true);
    System.out.println("#items=" + downduration.getItemCount());
    downduration.setBounds(300,270,120,25);
    panel.add(downduration);
	
    create = new JButton("Create new Service");
	create.setBounds(70, 350, 150, 25);
	create.addActionListener(new java.awt.event.ActionListener() {
		public void actionPerformed(java.awt.event.ActionEvent evt) {
			createActionPerformed(evt);
		}});
	panel.add(create);
	
	cancel = new JButton("Cancel");
	cancel.setBounds(330, 350, 80, 25);
	cancel.addActionListener(new java.awt.event.ActionListener() {
		public void actionPerformed(java.awt.event.ActionEvent evt) {
			cancelActionPerformed(evt);
		}});
	panel.add(cancel);
	
	
	success = new JLabel("");
	success.setBounds(70, 380, 200, 25);
	panel.add(success);
	
	errorMessage.setBounds(330, 380, 300, 25);
	errorMessage.setForeground(Color.red);
	panel.add(errorMessage);
	
	frame.setVisible(true);
	
	}
	private static void createActionPerformed(java.awt.event.ActionEvent evt) {
		String s = service.getText();
		String d = duration.getText();
		if(s.equals(null) || d.equals(null) ) {
			success.setText("Service name/duration can not be empty");
		}else {
			success.setText("Successfully add a new service!");
		}
	}
	private static void cancelActionPerformed(java.awt.event.ActionEvent evt) {
	   //add code to go back to main page
	}
}
