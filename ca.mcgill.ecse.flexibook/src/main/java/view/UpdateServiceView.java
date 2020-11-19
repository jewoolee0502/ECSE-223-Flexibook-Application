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

public class UpdateServiceView {
	private static JLabel Title;
	private static JFrame frame;
	private static JPanel panel;
	private static String error;
	private static JLabel errorMessage = new JLabel("");
	private static JLabel newlabel; 
	private static JTextField serviceTest;
	private static JLabel durationlabel;
	private static JComboBox<String> timeduration;
	private static JButton delete;
	private static JButton update;
	private static JButton cancel;
	
	public static void main(String[] args) {
		JPanel panel = new JPanel();
		JFrame frame = new JFrame();
		JLabel Title = new JLabel();
		frame.setSize(550, 400);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(panel);

		panel.setLayout(null);
		
		Title.setText("Update Service");
		Title.setBounds(220, 15, 400, 75);
		panel.add(Title);
		
		
		newlabel = new JLabel("New Name");
		newlabel.setBounds(70, 70, 80, 25);
		panel.add(newlabel);
		
		serviceTest = new JTextField(70);
		serviceTest.setBounds(70,110,165,25);
		panel.add(serviceTest);
		
		durationlabel = new JLabel("New duration");
		durationlabel.setBounds(70,150,120,25);
		panel.add(durationlabel);
		
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
		
		update = new JButton("Update Service");
		update.setBounds(70, 250, 130, 25);
		update.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				updateActionPerformed(evt);
			}});
		panel.add(update);
		
		delete = new JButton("Delete Service");
		delete.setBounds(230, 250, 130, 25);
		delete.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				deleteActionPerformed(evt);
			}});
		panel.add(delete);
		
	    cancel = new JButton("Cancel");
		cancel.setBounds(390, 250, 80, 25);
		cancel.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				cancelActionPerformed(evt);
			}});
		panel.add(cancel);
		
		
		errorMessage.setBounds(200, 350, 300, 25);
		errorMessage.setForeground(Color.red);
		panel.add(errorMessage);
		
		
		frame.setVisible(true);
	}

		private static void updateActionPerformed(java.awt.event.ActionEvent evt) {
			
		}
		
		private static void deleteActionPerformed(java.awt.event.ActionEvent evt) {
		
		}
	    
		private static void cancelActionPerformed(java.awt.event.ActionEvent evt) {
			
		}
}
