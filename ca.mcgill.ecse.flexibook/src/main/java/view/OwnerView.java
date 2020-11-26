package view;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.time.LocalDate;
import java.util.Properties;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
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

public class OwnerView {

	private static int Width = 700;
	private static int Length = 700;
	public static JFrame frame = new JFrame();
	private static JLabel Title = new JLabel();
	private static JLabel errorMessageStartAppointment = new JLabel("");
	private static JLabel errorMessageEndAppointment = new JLabel("");
	private static JLabel errorMessageNoShow = new JLabel("");
	private static JLabel errorMessageEditAccount = new JLabel("");
	private static JLabel errorMessageCreateService = new JLabel("");
	private static JLabel errorMessageEditService = new JLabel("");
	private static JLabel errorMessageLogOut = new JLabel("");
	private static JButton startAppointment = new JButton();
	private static JButton endAppointment = new JButton();
	private static JButton noShow = new JButton();
	private static JButton editAccount = new JButton();
	private static JButton createService = new JButton();
	private static JButton editService = new JButton();
	private static JButton businessInfo = new JButton();
	private static JButton logOut = new JButton("");
	private static JTable table;
	private static JPanel panelOwnerMainPage = new JPanel();
	private static JPanel panelLogOut = new JPanel();
	private static String error;
	private static JComboBox startComboBox = new JComboBox();
	private static JComboBox endComboBox = new JComboBox();
	private static JComboBox noShowComboBox = new JComboBox();
	private static JComboBox editServiceComboBox = new JComboBox();
	private static JDatePickerImpl overviewDatePicker;
	private static JLabel overviewDateLabel; 
	private static JTable overviewTable;
	private static JScrollPane overviewScrollPane;
	private static DefaultTableModel overviewDtm;
	private static String overviewColumnNames[] = {"Number","Customer", "Service","Start time"};
	private static final int HEIGHT_OVERVIEW_TABLE = 200;
	private static Customer currentUser = null;
	private static JLabel NumberLbl = new JLabel();
	private static JLabel ServiceLabel = new JLabel();
	private static JLabel HourLbl = new JLabel();
	private static JLabel CustomerLbl = new JLabel();

	public OwnerView() throws InvalidInputException {
		init_component_ownerMainPage();
	}

	private static void init_component_ownerMainPage() throws InvalidInputException {
		Font font1 = new Font("Times New Romans", Font.BOLD, 20);

		panelOwnerMainPage.setLayout(null);
		frame.add(panelOwnerMainPage);
		frame.setSize(Width, Length);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		Title.setText("Main Owner Page:");
		Title.setBounds(250,  10,  400,  75);
		Title.setFont(font1);
		panelOwnerMainPage.add(Title);

		startAppointment.setText("Start Appointment");
		startAppointment.setBounds(10, 80, 150, 30);

		endAppointment.setText("End Appointment");
		endAppointment.setBounds(220, 80, 140, 30);

		noShow.setText("No-Show");
		noShow.setBounds(420, 80, 90, 30);

		editAccount.setText("Edit Account");
		editAccount.setBounds(580, 80, 120, 30);

		createService.setText("Create Service");
		createService.setBounds(10, 600, 120, 30);

		editService.setText("Edit Service");
		editService.setBounds(300, 600, 100, 30);

		businessInfo.setText("Business Info");
		businessInfo.setBounds(150, 600, 110, 30);

		logOut.setText("Log Out");
		logOut.setBounds(550, 600, 100, 25);
		businessInfo.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				businessinfo(evt);
			}

		});
		startAppointment.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				startAppointmentActionPerformed(evt);
			}});
		endAppointment.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				endAppointmentActionPerformed(evt);
			}});
		noShow.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				noShowActionPerformed(evt);
			}});
		editAccount.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				editAccountActionPerformed(evt);
			}});		//should I add an error message? What kind of error message though?
		createService.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				createServiceActionPerformed(evt);
			}});
		editService.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				editServiceActionPerformed(evt);
			}});
		logOut.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				logOutActionPerformed(evt);
			}});

		String startAppNum[] = { "#", "1", "2", "3", "4", "5" };        
		if(FlexiBookApplication.getflexibook().getAppointments().size()>0) {
			int count = 0;
			for(BookableService a: FlexiBookApplication.getflexibook().getBookableServices()) {
				count=count+1;
				startComboBox.addItem(count);
			}
		}
		startComboBox = new JComboBox(startAppNum);    
		startComboBox.setBounds(150, 80, 60, 30); 

		String endAppNum[] = { "#", "1", "2", "3", "4", "5" };        
		if(FlexiBookApplication.getflexibook().getAppointments().size()>0) {
			int count = 0;
			for(BookableService a: FlexiBookApplication.getflexibook().getBookableServices()) {
				count=count+1;
				endComboBox.addItem(count);
			}
		}
		endComboBox = new JComboBox(endAppNum);    
		endComboBox.setBounds(350, 80, 60, 30);

		String noShowNum[] = { "#", "1", "2", "3", "4", "5" };        
		if(FlexiBookApplication.getflexibook().getAppointments().size()>0) {
			int count = 0;
			for(BookableService a: FlexiBookApplication.getflexibook().getBookableServices()) {
				count=count+1;
				noShowComboBox.addItem(count);
			}
		}
		noShowComboBox = new JComboBox(noShowNum);    
		noShowComboBox.setBounds(500, 80, 60, 30);

		String editServiceChoice[] = { "Services", "Cut", "Wash", "Color", "Perm"};        
		if(FlexiBookApplication.getflexibook().getAppointments().size()>0) {
			int count = 0;
			for(BookableService a: FlexiBookApplication.getflexibook().getBookableServices()) {
				count=count+1;
				editServiceComboBox.addItem(count);
			}
		}
		editServiceComboBox = new JComboBox(editServiceChoice);    
		editServiceComboBox.setBounds(390, 600, 105, 30);


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
		panelOwnerMainPage.add(overviewDatePicker);
		overviewDateLabel = new JLabel();
		overviewDateLabel.setText("Date for Overview:");
		overviewDateLabel.setBounds(50, 150, 150, 20);
		panelOwnerMainPage.add(overviewDateLabel);
		
		overviewTable = new JTable();
		overviewScrollPane = new JScrollPane(overviewTable);
		Dimension d = overviewTable.getPreferredSize();
		overviewScrollPane.setPreferredSize(new Dimension(d.width, HEIGHT_OVERVIEW_TABLE));
		overviewScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		overviewTable.setBounds(50, 250, 600, HEIGHT_OVERVIEW_TABLE);
		panelOwnerMainPage.add(overviewTable);
		NumberLbl.setBounds(50, 210, 150, 50);
		NumberLbl.setText("#");
		CustomerLbl.setText("Customer Name");
		CustomerLbl.setBounds(200, 210, 150, 50);
		ServiceLabel.setText("Service");
		HourLbl.setText("Start Time");
		ServiceLabel.setBounds(350, 210, 150, 50);
		HourLbl.setBounds(500, 210, 150, 50);
		panelOwnerMainPage.add(NumberLbl);
		panelOwnerMainPage.add(CustomerLbl);
		panelOwnerMainPage.add(ServiceLabel);
		panelOwnerMainPage.add(HourLbl);
		panelOwnerMainPage.add(overviewTable);
		panelOwnerMainPage.add(startAppointment);
		panelOwnerMainPage.add(endAppointment);
		panelOwnerMainPage.add(noShow);
		panelOwnerMainPage.add(editAccount);
		panelOwnerMainPage.add(createService);
		panelOwnerMainPage.add(editService);
		panelOwnerMainPage.add(businessInfo);
		panelOwnerMainPage.add(logOut);
		panelOwnerMainPage.add(startComboBox);
		panelOwnerMainPage.add(endComboBox);
		panelOwnerMainPage.add(noShowComboBox);
		panelOwnerMainPage.add(editServiceComboBox);
		refreshDailyOverview();
		frame.setVisible(true);
	}
	
	private static void refreshDailyOverview() throws InvalidInputException {
		overviewDtm = new DefaultTableModel(0, 0);
		overviewDtm.setColumnIdentifiers(overviewColumnNames);
		overviewTable.setModel(overviewDtm);
		if (overviewDatePicker.getModel().getValue() != null) {
			for (Customer cust : FlexiBookApplication.getflexibook().getCustomers()) {
			int index=0;
			String customer = cust.getUsername();
			for (TOTimeSlot item : FlexibookController.getUnavailableTimeSlots(cust.getUsername(),overviewDatePicker.getModel().getValue().toString())) {
				index++;
				String number = String.valueOf(index);
				String service="---";
				String StartTime = "---";
						
				for(Appointment appointment:currentUser.getAppointments()) {
					if(appointment.getTimeSlot().getStartTime().equals(item.getStartTime())){
						 StartTime=appointment.getTimeSlot().getStartTime().toString();
						 service=appointment.getBookableService().getName();
					}
				}

				
				Object[] obj = {index,customer, service, StartTime};
				overviewDtm.addRow(obj);
			}
		}
		}
		Dimension d = overviewTable.getPreferredSize();
		overviewScrollPane.setPreferredSize(new Dimension(d.width, HEIGHT_OVERVIEW_TABLE));
	}
	private static void startAppointmentActionPerformed(java.awt.event.ActionEvent evt) {
//		try {
//			FlexibookController.startAppointment(owner, appointment);
//		} catch(InvalidInputException e) {
//			
//		}
	}

	private static void endAppointmentActionPerformed(java.awt.event.ActionEvent evt) {
//		try {
//			FlexibookController.endAppointment(owner, appointment);
//		} catch(InvalidInputException e) {
//			
//		}
	}

	private static void noShowActionPerformed(java.awt.event.ActionEvent evt) {
//		try {
//			FlexibookController.noShowCheck(customer, owner, name, serviceDate, startTime);
//		} catch(InvalidInputException e) {
//			
//		}
	}

	private static void editAccountActionPerformed(java.awt.event.ActionEvent evt) {
			FlexiBookApplication.ownertoedit();
	}

	private static void createServiceActionPerformed(java.awt.event.ActionEvent evt) {
		FlexiBookApplication.ownertoaddservice();
	}

	private static void editServiceActionPerformed(java.awt.event.ActionEvent evt) {
//		try {
//			FlexibookController.updateservice(string, string2, string3, string4, string5, string6);
//			FlexiBookApplication.updateservice();
//		} catch(InvalidInputException e) {
//			
//		}
	}
	
	private static void businessinfo(ActionEvent evt) {
		FlexiBookApplication.ownertobusiness();
	}
	
	private static void logOutActionPerformed(java.awt.event.ActionEvent evt) {
		try {
			FlexibookController.LogOut();
			FlexiBookApplication.logout();
		} catch (InvalidInputException e) {
			
		}
	}

	public static void main(String[] args) {
		try {
			init_component_ownerMainPage();
		} catch(InvalidInputException e) {
			
		}
	}

}
