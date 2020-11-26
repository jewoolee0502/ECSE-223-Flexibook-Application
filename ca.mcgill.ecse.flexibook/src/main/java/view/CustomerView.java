package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ItemListener;
import java.time.LocalDate;
import java.util.Properties;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.table.DefaultTableModel;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.SqlDateModel;

import ca.mcgill.ecse.flexibook.Controller.FlexibookController;
import ca.mcgill.ecse.flexibook.Controller.InvalidInputException;
import ca.mcgill.ecse.flexibook.Controller.TOTimeSlot;
import ca.mcgill.ecse.flexibook.application.FlexiBookApplication;
import ca.mcgill.ecse.flexibook.model.Appointment;
import ca.mcgill.ecse.flexibook.model.BookableService;
import ca.mcgill.ecse.flexibook.model.Customer;


public class CustomerView  {
	private static int Width = 700;
	private static int Length = 700;
	public static JFrame frame = new JFrame();
	private static JLabel Title = new JLabel();
	private static JLabel errorMessageMakeAppointment = new JLabel("");
	private static JLabel errorMessageUpdateAppointment = new JLabel("");
	private static JLabel errorMessageEditAccount = new JLabel("");
	//	private static JLabel comboBoxMessage = new JLabel("Select Appointment #: ");
	//	private static JLabel comboBoxMessage2 = new JLabel("# Selected");
	private static JButton makeAppointment = new JButton();
	private static JButton updateAppointment = new JButton();
	private static JButton editAccount = new JButton();
	private static JButton logOut = new JButton();
	private static JButton businessInfo = new JButton();
	private static JButton viewService = new JButton();
	private static JTable table;
	private static JPanel panelCustomerMainPage = new JPanel();
	private static JPanel panelLogOut = new JPanel();
	private static String error;
	private static JComboBox comboBox = new JComboBox();
	private static JDatePickerImpl overviewDatePicker;
	private static JLabel overviewDateLabel; 
	private static JTable overviewTable;
	private static JScrollPane overviewScrollPane;
	private static DefaultTableModel overviewDtm;
	private static String overviewColumnNames[] = {"Number", "Service","Start time"};
	private static final int HEIGHT_OVERVIEW_TABLE = 350;
	private static Customer currentUser = null;
	private static JLabel NumberLbl = new JLabel();
	private static JLabel ServiceLabel = new JLabel();
	private static JLabel HourLbl = new JLabel();


	public CustomerView() throws InvalidInputException {
		init_component_customerMainPage(); 
	}


	private static void init_component_customerMainPage() throws InvalidInputException {
		Font font1 = new Font("Times New Romans", Font.BOLD, 20);
		//		Font font2 = new Font("Times New Romans", Font.PLAIN, 1);
		for(Customer user : FlexiBookApplication.getflexibook().getCustomers()) {
			if(user.getUsername().equals(FlexiBookApplication.getCurrentuser().getUsername())) {
				currentUser = user;
			}}
		panelCustomerMainPage.setLayout(null);
		frame.add(panelCustomerMainPage);
		frame.setSize(Width, Length);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Title.setText("Main Customer Page:");
		Title.setBounds(225,  10,  400,  75);
		Title.setFont(font1);
		panelCustomerMainPage.add(Title);
		makeAppointment.setText("Make Appointment");
		makeAppointment.setBounds(10, 80, 150, 30);
		updateAppointment.setText("Update Appointment");
		updateAppointment.setBounds(170, 80, 170, 30);
		editAccount.setText("Edit Account");
		editAccount.setBounds(550, 80, 120, 30);
		logOut.setText("Log Out");
		logOut.setBounds(300, 630, 100, 25);
		businessInfo.setText("View Business Info");
		businessInfo.setBounds(520, 120, 150, 25);
		viewService.setText("View Service");
		viewService.setBounds(520, 160, 150, 25);
		viewService.addActionListener(new java.awt.event.ActionListener() {
	          public void actionPerformed(java.awt.event.ActionEvent evt) {
					FlexiBookApplication.ctoservice();
	        }});
		businessInfo.addActionListener(new java.awt.event.ActionListener() {
          public void actionPerformed(java.awt.event.ActionEvent evt) {
            FlexiBookApplication.customertobusiness();
        }});
		makeAppointment.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				FlexiBookApplication.gotomakeappointment();
			}});
		updateAppointment.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				updateAppointmentActionPerformed(evt);
			}});
		editAccount.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				editAccountActionPerformed(evt);
			}});		//should I add an error message? What kind of error message though?
		logOut.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				logOutActionPerformed(evt);
			}});

		//		comboBoxMessage.setBounds(170, 70, 100, 10);
		//		comboBoxMessage.setForeground(Color.red);
		String number[] = { "#", "1", "2", "3", "4", "5" };        
		if(FlexiBookApplication.getflexibook().getAppointments().size()>0) {
			int count = 0;
			for(BookableService a: FlexiBookApplication.getflexibook().getBookableServices()) {
				count=count+1;
				comboBox.addItem(count);
			}
		}
		comboBox = new JComboBox(number);    
		comboBox.setBounds(350, 80, 70, 30); 

		SqlDateModel overviewModel = new SqlDateModel();
		LocalDate now = LocalDate.now();
		overviewModel.setDate(now.getYear(), now.getMonthValue() - 1, now.getDayOfMonth());
		overviewModel.setSelected(true);
		Properties pO = new Properties();
		pO.put("text.today", "Today");
		pO.put("text.month", "Month");
		pO.put("text.year", "Year");
		JDatePanelImpl overviewDatePanel = new JDatePanelImpl(overviewModel, pO);
		overviewDatePicker = new JDatePickerImpl(overviewDatePanel, new DateLabelFormatter());
		overviewDatePicker.setBounds(210, 150, 210, 30);
		panelCustomerMainPage.add(overviewDatePicker);
		overviewDateLabel = new JLabel();
		overviewDateLabel.setText("Date for Overview:");
		overviewDateLabel.setBounds(50, 150, 150, 20);
		panelCustomerMainPage.add(overviewDateLabel);
		
		overviewTable = new JTable();
		overviewScrollPane = new JScrollPane(overviewTable);
		Dimension d = overviewTable.getPreferredSize();
		overviewScrollPane.setPreferredSize(new Dimension(d.width, HEIGHT_OVERVIEW_TABLE));
		overviewScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		overviewTable.setBounds(50, 250, 600, HEIGHT_OVERVIEW_TABLE);
		panelCustomerMainPage.add(overviewTable);
		NumberLbl.setBounds(50, 210, 200, 50);
		NumberLbl.setText("#");
		ServiceLabel.setText("Service");
		HourLbl.setText("Start Time");
		ServiceLabel.setBounds(250, 210, 200, 50);
		HourLbl.setBounds(450, 210, 200, 50);
		panelCustomerMainPage.add(NumberLbl);
		panelCustomerMainPage.add(ServiceLabel);
		panelCustomerMainPage.add(HourLbl);
		panelCustomerMainPage.add(overviewScrollPane);
		panelCustomerMainPage.add(makeAppointment);
		panelCustomerMainPage.add(updateAppointment);
		panelCustomerMainPage.add(editAccount);
		panelCustomerMainPage.add(logOut);
		panelCustomerMainPage.add(businessInfo);
		panelCustomerMainPage.add(viewService);
		//		panelCustomerMainPage.add(comboBoxMessage);
		panelCustomerMainPage.add(comboBox);
		//refreshDailyOverview();
		frame.setVisible(true);

	}

//	private static void refreshDailyOverview() throws InvalidInputException {
//		overviewDtm = new DefaultTableModel(0, 0);
//		overviewDtm.setColumnIdentifiers(overviewColumnNames);
//		overviewTable.setModel(overviewDtm);
//		if (overviewDatePicker.getModel().getValue() != null) {
//			int index=0;
//			for (TOTimeSlot item : FlexibookController.getUnavailableTimeSlots(FlexiBookApplication.getCurrentuser().getUsername(),overviewDatePicker.getModel().getValue().toString())) {
//				index++;
//				String number = String.valueOf(index);
//				String service="---";
//				String StartTime = "---";
//						
//				for(Appointment appointment:currentUser.getAppointments()) {
//					if(appointment.getTimeSlot().getStartTime().equals(item.getStartTime())){
//						 StartTime=appointment.getTimeSlot().getStartTime().toString();
//						 service=appointment.getBookableService().getName();
//					}
//				}
//
//				
//				Object[] obj = {index, service, StartTime};
//				overviewDtm.addRow(obj);
//			}
//		}
//		Dimension d = overviewTable.getPreferredSize();
//		overviewScrollPane.setPreferredSize(new Dimension(d.width, HEIGHT_OVERVIEW_TABLE));
//	}



	private static void updateAppointmentActionPerformed(java.awt.event.ActionEvent evt) {
		//		int k=Integer.parseInt(comboBox.getSelectedItem().toString());
		//		if(FlexiBookApplication.getflexibook().getAppointments().size()>0) {
		//			FlexiBookApplication.setcurap(FlexiBookApplication.getflexibook().getAppointment(k-1));
		//		}
		FlexiBookApplication.updateapp();
	}

	private static void editAccountActionPerformed(java.awt.event.ActionEvent evt) {
		FlexiBookApplication.customertoedit();
	}

	private static void logOutActionPerformed(java.awt.event.ActionEvent evt) {
		try {
			FlexiBookApplication.clogout();
			FlexibookController.LogOut();
		} catch(InvalidInputException e) {
			
		}
	}

	public static void main(String[] args) throws InvalidInputException {
		init_component_customerMainPage();
	}

}
