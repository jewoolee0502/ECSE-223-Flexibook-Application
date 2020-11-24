package view;

import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import ca.mcgill.ecse.flexibook.application.FlexiBookApplication;

public class ViewBusinessInfoCustomer  {
	
	private static int Width = 700;
	private static int Length = 700;
	public static JFrame frame = new JFrame("FlexiBook Application");
	private static JLabel Title = new JLabel();
	private static JPanel panelViewBusiness = new JPanel();
	private static JButton back = new JButton();
	private static JLabel name = new JLabel();
	private static JLabel address = new JLabel();
	private static JLabel phoneNumber = new JLabel();
	private static JLabel email = new JLabel();
	private static JLabel businessHour = new JLabel();
	private static JTable table;
	private static JLabel nameCur;
	private static JLabel addressCur;
	private static JLabel phoneNumberCur;
	private static JLabel emailCur;
	
	
	private static void init_component_viewBusinessInfo() {
		panelViewBusiness.setLayout(null);
		frame.add(panelViewBusiness);
		frame.setSize(Width, Length);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Title.setText("View Business Information:");
		Font font1 = new Font("Times New Romans", Font.BOLD, 20);
		Title.setBounds(200, 15, 500, 75);
		Title.setFont(font1);
		panelViewBusiness.add(Title);
		
		back.setText("Back");
		back.setBounds(550, 550, 80, 25);
		back.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				backActionPerformed(evt);
			}});
		panelViewBusiness.add(back);
		
		name.setText("Business Name:");
		Font font2 = new Font("Times New Romans", Font.BOLD, 13);
		name.setBounds(50, 150, 150, 25);
		name.setFont(font2);
		panelViewBusiness.add(name);
		
		nameCur = new JLabel("Current Business Name.");
		nameCur.setBounds(200, 150, 150, 25);
		panelViewBusiness.add(nameCur);
		
		address.setText("Address:");
		Font font3 = new Font("Times New Romans", Font.BOLD, 13);
		address.setBounds(50, 200, 150, 25);
		address.setFont(font3);
		panelViewBusiness.add(address);
		
		addressCur = new JLabel("Current Address.");
		addressCur.setBounds(200, 200, 150, 25);
		panelViewBusiness.add(addressCur);
		
		phoneNumber.setText("Phone Number:");
		Font font4 = new Font("Times New Romans", Font.BOLD, 13);
		phoneNumber.setBounds(50, 250, 150, 25);
		phoneNumber.setFont(font4);
		panelViewBusiness.add(phoneNumber);
		
		phoneNumberCur = new JLabel("Phone Number:");
		phoneNumberCur.setBounds(200, 250, 150, 25);
		panelViewBusiness.add(phoneNumberCur);
		
		email.setText("Email:");
		Font font5 = new Font("Times New Romans", Font.BOLD, 13);
		email.setBounds(50, 300, 100, 25);
		email.setFont(font5);
		panelViewBusiness.add(email);
		
		emailCur = new JLabel("Current Email.");
		emailCur.setBounds(200, 300, 100, 25);
		panelViewBusiness.add(emailCur);
		
		businessHour.setText("Business Hour:");
		Font font6 = new Font("Times New Romans", Font.BOLD, 13);
		businessHour.setBounds(50, 350, 150, 25);
		businessHour.setFont(font6);
		panelViewBusiness.add(businessHour);
		
		String column[] = {"Days", "Business Hour"};
		String info[][] = {
				{"Monday", "09:00 - 13:00 & 14:00 - 18:00"},
				{"Tuesday", "09:00 - 13:00 & 14:00 - 18:00"},
				{"Wednesday", "09:00 - 13:00 & 14:00 - 18:00"},
				{"Thursday", "09:00 - 13:00 & 14:00 - 18:00"},
				{"Friday", "09:00 - 13:00 & 14:00 - 18:00"},
				{"Saturday", "09:00 - 13:00 & 14:00 - 18:00"},
				{"Sunday", "09:00 - 13:00 & 14:00 - 18:00"},
		};
		table = new JTable(info, column);
		table.setBounds(50, 380, 400, 150);
		panelViewBusiness.add(table);
		
		frame.setVisible(true);
		
	}
	
	private static void backActionPerformed(java.awt.event.ActionEvent evt) {
    FlexiBookApplication.businesstocustomer();
	}
	
	public ViewBusinessInfoCustomer() {
		init_component_viewBusinessInfo();
	}
	
	public static void main(String[] args) {
		init_component_viewBusinessInfo();
	}

}

