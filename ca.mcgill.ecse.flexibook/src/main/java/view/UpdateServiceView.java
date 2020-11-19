package view;

import java.awt.Color;
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

public class UpdateServiceView {
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
		
		
		JLabel newlabel = new JLabel("New Name");
		newlabel.setBounds(70, 70, 80, 25);
		panel.add(newlabel);
		
		JTextField sTest = new JTextField(70);
		sTest.setBounds(70,110,165,25);
		panel.add(sTest);
		
		JLabel dlabel = new JLabel("New duration");
		dlabel.setBounds(70,150,120,25);
		panel.add(dlabel);
		
		JButton updatebutton = new JButton("Update Service");
		updatebutton.setBounds(70, 250, 130, 25);
		panel.add(updatebutton);
		
		JButton deletebutton = new JButton("Delete Service");
		deletebutton.setBounds(230, 250, 130, 25);
		panel.add(deletebutton);
		
		JButton button = new JButton("Cancel");
		button.setBounds(390, 250, 80, 25);
		panel.add(button);
		frame.setVisible(true);
		
	}
}
