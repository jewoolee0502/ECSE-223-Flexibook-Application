package view;

import java.awt.Font;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;

public class ViewServicesOwner extends JFrame {
	
	private static int Width = 700;
	private static int Length = 700;
	public static JFrame frame = new JFrame();
	private static JLabel Title = new JLabel();
	private static JPanel panelViewServices = new JPanel();
	private static JLabel services = new JLabel();
	private static JTable table = new JTable();
	private static JButton back = new JButton();
	private static JButton create = new JButton();
	private static JButton edit = new JButton();
	
	private static void init_component_ViewServicesOwner() {
		panelViewServices.setLayout(null);
		frame.add(panelViewServices);
		frame.setSize(Width, Length);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Font font1 = new Font("Times New Romans", Font.BOLD, 20);
		Title.setText("View Services:");
		Title.setBounds(250, 10, 400, 75);
		Title.setFont(font1);
		panelViewServices.add(Title);
		
		Font font2 = new Font("Times New Romans", Font.BOLD, 13);
		services.setText("Types of Existing Services:");
		services.setBounds(50, 80, 200, 25);
		services.setFont(font2);
		panelViewServices.add(services);
		
		String column[] = {"Service Name:", "Duration:", "Downtime Start:", "Downtime Duration:"};
		String data[][] = {
				{"         Service Name:", "             Duration:", "       Downtime Start:", "    Downtime Duration:"},
				{"cut", "20", "10", "50"},
				{"wash", "10", "5", "10"}
		};
		table = new JTable(data, column);
		table.setBounds(50, 110, 600, 500);
		panelViewServices.add(table);
		
		back.setText("Back");
		back.setBounds(550, 620, 80, 25);
		back.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				backActionPerformed(evt);
			}});
		panelViewServices.add(back);
		
		create.setText("Create Service");
		create.setBounds(500, 80, 150, 25);
		create.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				createActionPerformed(evt);
			}});
		panelViewServices.add(create);
		
		edit.setText("Edit Service");
		edit.setBounds(100, 620, 150, 25);
		edit.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				editActionPerformed(evt);
			}});
		panelViewServices.add(edit);
		
		frame.setVisible(true);
		
	}
	
	private static void backActionPerformed(ActionEvent evt) {
		
	}
	
	private static void createActionPerformed(ActionEvent evt) {
		
	}
	
	private static void editActionPerformed(ActionEvent evt) {
		
	}
	
	public static void main(String[] args) {
		init_component_ViewServicesOwner();
	}
}
