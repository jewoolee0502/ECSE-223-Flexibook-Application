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

public class AddServiceView {
	
	private static JFrame frame = new JFrame();
	private static JPanel panel;
	private static JLabel service;
	private static JLabel duration;
	private static JTextField newservice;
	private static JButton create;
	private static JButton cancel;
	
	
	public static void main(String[] args) {
		JPanel panel = new JPanel();
		JFrame frame = new JFrame();
		JLabel Title = new JLabel();
	frame.setSize(550, 400);
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
	
    create = new JButton("Create new Service");
	create.setBounds(70, 250, 150, 25);
	create.addActionListener(new java.awt.event.ActionListener() {
		public void actionPerformed(java.awt.event.ActionEvent evt) {
			createActionPerformed(evt);
		}});
	panel.add(create);
	
	cancel = new JButton("Cancel");
	cancel.setBounds(330, 250, 80, 25);
	cancel.addActionListener(new java.awt.event.ActionListener() {
		public void actionPerformed(java.awt.event.ActionEvent evt) {
			cancelActionPerformed(evt);
		}});
	panel.add(cancel);
	
	frame.setVisible(true);
	
	}
	private static void createActionPerformed(java.awt.event.ActionEvent evt) {
		
	}
	private static void cancelActionPerformed(java.awt.event.ActionEvent evt) {
		
	}
}
