package view;

import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;

import ca.mcgill.ecse.flexibook.Controller.InvalidInputException;
import ca.mcgill.ecse.flexibook.application.FlexiBookApplication;
import ca.mcgill.ecse.flexibook.model.Business;
import ca.mcgill.ecse.flexibook.model.BusinessHour;

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
	
	public static void refresh() {
		panelViewBusiness.remove(table);
		String column[] = {"Days:", "Business Hour:"};
		if(FlexiBookApplication.getflexibook().getBusiness().getBusinessHours().size() > 0) {
			String Monday = "|";
			String Tuesday = "|";
			String Wednesday = "|";
			String Thursday = "|";
			String Friday = "|";
			String Saturday = "|";
			String Sunday = "|";
			for(BusinessHour b: FlexiBookApplication.getflexibook().getBusiness().getBusinessHours()) {
				switch(b.getDayOfWeek().toString()) {
				case "Monday":
					Monday = Monday + b.getStartTime().toString() + " - " + b.getEndTime().toString() + "|";
					break;
				case "Tuesday":
					Tuesday = Tuesday + b.getStartTime().toString() + " - " + b.getEndTime().toString() + "|";
					break;
				case "Wednesday":
					Wednesday = Wednesday + b.getStartTime().toString() + " - " + b.getEndTime().toString() + "|";
					break;
				case "Thursday":
					Thursday = Thursday + b.getStartTime().toString() + " - " + b.getEndTime().toString() + "|";
					break;
				case "Friday":
					Friday = Friday + b.getStartTime().toString() + " - " + b.getEndTime().toString() + "|";
					break;
				case "Saturday":
					Saturday = Saturday + b.getStartTime().toString() + " - " + b.getEndTime().toString() + "|";
					break;
				case "Sunday":
					Sunday = Sunday + b.getStartTime().toString() + " - " + b.getEndTime().toString() + "|";
					break;
				}
			}

			String info1[][] = {{"                    Days:", "              Business Hour:"},
					{"Monday", Monday}, {"Tuesday", Tuesday},
					{"Wednesday", Wednesday}, {"Thursday", Thursday},
					{"Friday", Friday}, {"Saturday", Saturday},
					{"Sunday", Sunday}};
			table = new JTable(info1, column);
			table.setBounds(50, 380, 600, 150);
			panelViewBusiness.add(table);
		}
		else {
			String info2[][] = {{"                    Days:", "              Business Hour:"},
					{"Monday", " "}, {"Tuesday", " "},
					{"Wednesday", " "}, {"Thursday", " "},
					{"Friday", " "}, {"Saturday", " "},
					{"Sunday", " "}};
			table = new JTable(info2, column);
			table.setBounds(50, 380, 600, 150);
			panelViewBusiness.add(table);
		}
	}
	
	private static void init_component_viewBusinessInfo() {
		
		if(FlexiBookApplication.getflexibook() != null) {
			if(FlexiBookApplication.getflexibook().getBusiness() == null) {
				Business b = new Business("The name of the business is not set-up yet.", "The address of the business is not set-up yet.", 
						"The phone number of the business is not set-up yet.", "The email of the business is not set-up yet.", FlexiBookApplication.getflexibook());
			}
		}
		
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
				try {
					backActionPerformed(evt);
				} catch (InvalidInputException e) {

				}
			}});
		panelViewBusiness.add(back);
		
		name.setText("Business Name:");
		Font font2 = new Font("Times New Romans", Font.BOLD, 13);
		name.setBounds(50, 150, 150, 25);
		name.setFont(font2);
		panelViewBusiness.add(name);
		
		nameCur = new JLabel(FlexiBookApplication.getflexibook().getBusiness().getName());
		nameCur.setBounds(200, 150, 400, 25);
		panelViewBusiness.add(nameCur);
		
		address.setText("Address:");
		Font font3 = new Font("Times New Romans", Font.BOLD, 13);
		address.setBounds(50, 200, 150, 25);
		address.setFont(font3);
		panelViewBusiness.add(address);
		
		addressCur = new JLabel(FlexiBookApplication.getflexibook().getBusiness().getAddress());
		addressCur.setBounds(200, 200, 400, 25);
		panelViewBusiness.add(addressCur);
		
		phoneNumber.setText("Phone Number:");
		Font font4 = new Font("Times New Romans", Font.BOLD, 13);
		phoneNumber.setBounds(50, 250, 150, 25);
		phoneNumber.setFont(font4);
		panelViewBusiness.add(phoneNumber);
		
		phoneNumberCur = new JLabel(FlexiBookApplication.getflexibook().getBusiness().getPhoneNumber());
		phoneNumberCur.setBounds(200, 250, 400, 25);
		panelViewBusiness.add(phoneNumberCur);
		
		email.setText("Email:");
		Font font5 = new Font("Times New Romans", Font.BOLD, 13);
		email.setBounds(50, 300, 100, 25);
		email.setFont(font5);
		panelViewBusiness.add(email);
		
		emailCur = new JLabel(FlexiBookApplication.getflexibook().getBusiness().getEmail());
		emailCur.setBounds(200, 300, 400, 25);
		panelViewBusiness.add(emailCur);
		
		businessHour.setText("Business Hour:");
		Font font6 = new Font("Times New Romans", Font.BOLD, 13);
		businessHour.setBounds(50, 350, 150, 25);
		businessHour.setFont(font6);
		panelViewBusiness.add(businessHour);
		
		String column[] = {"Days:", "Business Hour:"};
		if(FlexiBookApplication.getflexibook().getBusiness().getBusinessHours().size() > 0) {
			String Monday = "|";
			String Tuesday = "|";
			String Wednesday = "|";
			String Thursday = "|";
			String Friday = "|";
			String Saturday = "|";
			String Sunday = "|";
			for(BusinessHour b: FlexiBookApplication.getflexibook().getBusiness().getBusinessHours()) {
				switch(b.getDayOfWeek().toString()) {
				case "Monday":
					Monday = Monday + b.getStartTime().toString() + " - " + b.getEndTime().toString() + "|";
					break;
				case "Tuesday":
					Tuesday = Tuesday + b.getStartTime().toString() + " - " + b.getEndTime().toString() + "|";
					break;
				case "Wednesday":
					Wednesday = Wednesday + b.getStartTime().toString() + " - " + b.getEndTime().toString() + "|";
					break;
				case "Thursday":
					Thursday = Thursday + b.getStartTime().toString() + " - " + b.getEndTime().toString() + "|";
					break;
				case "Friday":
					Friday = Friday + b.getStartTime().toString() + " - " + b.getEndTime().toString() + "|";
					break;
				case "Saturday":
					Saturday = Saturday + b.getStartTime().toString() + " - " + b.getEndTime().toString() + "|";
					break;
				case "Sunday":
					Sunday = Sunday + b.getStartTime().toString() + " - " + b.getEndTime().toString() + "|";
					break;
				}
			}

			String info1[][] = {{"                    Days:", "              Business Hour:"},
					{"Monday", Monday}, {"Tuesday", Tuesday},
					{"Wednesday", Wednesday}, {"Thursday", Thursday},
					{"Friday", Friday}, {"Saturday", Saturday},
					{"Sunday", Sunday}};
			table = new JTable(info1, column);
			table.setBounds(50, 380, 600, 150);
			panelViewBusiness.add(table);
		}
		else {
			String info2[][] = {{"                    Days:", "              Business Hour:"},
					{"Monday", " "}, {"Tuesday", " "},
					{"Wednesday", " "}, {"Thursday", " "},
					{"Friday", " "}, {"Saturday", " "},
					{"Sunday", " "}};
			table = new JTable(info2, column);
			table.setBounds(50, 380, 600, 150);
			panelViewBusiness.add(table);
		}
		
		frame.setVisible(true);
		
	}
	
	private static void backActionPerformed(java.awt.event.ActionEvent evt) throws InvalidInputException {
    FlexiBookApplication.businesstocustomer();
	}
	
	public ViewBusinessInfoCustomer() {
		init_component_viewBusinessInfo();
	}
	
	public static void main(String[] args) {
		init_component_viewBusinessInfo();
	}

}

