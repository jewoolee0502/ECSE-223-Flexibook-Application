package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.sql.Date;
import java.sql.Time;
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
import ca.mcgill.ecse.flexibook.model.Service;
import ca.mcgill.ecse.flexibook.model.TimeSlot;
import ca.mcgill.ecse.flexibook.util.SystemTime;

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
	private static JButton viewService = new JButton();
	private static JButton businessInfo = new JButton();
	private static JButton logOut = new JButton("");
	private static JTable table;
	private static JPanel panelOwnerMainPage = new JPanel();
	private static String error;
	private static JComboBox startComboBox = new JComboBox();
	private static JComboBox endComboBox = new JComboBox();
	private static JComboBox noShowComboBox = new JComboBox();
	private static JDatePickerImpl overviewDatePicker;
	private static JLabel overviewDateLabel; 
	private static JTable overviewTable;
	private static JScrollPane overviewScrollPane;
	private static DefaultTableModel overviewDtm;
	private static String overviewColumnNames[] = {"Number","Customer", "Service","Start time"};
	private static final int HEIGHT_OVERVIEW_TABLE = 300;
	private static Customer currentUser = null;
	private static JLabel NumberLbl = new JLabel();
	private static JLabel ServiceLabel = new JLabel();
	private static JLabel HourLbl = new JLabel();
	private static JLabel CustomerLbl = new JLabel();
	private static JLabel StatusLbl = new JLabel();
	private static JLabel NoShowLbl = new JLabel();
	private static JLabel errorMessage = new JLabel();

	public OwnerView() throws InvalidInputException {
		init_component_ownerMainPage();
	}

	public static void refreshCombo() {
		startComboBox.setSelectedIndex(0);
		endComboBox.setSelectedIndex(0);
		noShowComboBox.setSelectedIndex(0);
	}

	public static void refreshError() {
		errorMessage.setText(" ");
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

		viewService.setText("View Service");
		viewService.setBounds(300, 600, 150, 30);

		businessInfo.setText("View Business Info");
		businessInfo.setBounds(50, 600, 150, 30);

		logOut.setText("Log Out");
		logOut.setBounds(550, 600, 100, 25);

		errorMessage.setBounds(20, 120, 500, 25);
		errorMessage.setForeground(Color.red);
		panelOwnerMainPage.add(errorMessage);

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
		viewService.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				viewServiceActionPerformed(evt);
			}});
		logOut.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				logOutActionPerformed(evt);
			}});
		startComboBox = new JComboBox();
		if(FlexiBookApplication.getflexibook().getAppointments().size()>0) {
			int count = 0;
			for(BookableService a: FlexiBookApplication.getflexibook().getBookableServices()) {
				count=count+1;
				startComboBox.addItem(count);
			}
		}

		startComboBox.setBounds(150, 80, 60, 30); 

		endComboBox = new JComboBox();       
		if(FlexiBookApplication.getflexibook().getAppointments().size()>0) {
			int count = 0;
			for(BookableService a: FlexiBookApplication.getflexibook().getBookableServices()) {
				count=count+1;
				endComboBox.addItem(count);
			}
		}    
		endComboBox.setBounds(350, 80, 60, 30);

		noShowComboBox = new JComboBox();      
		if(FlexiBookApplication.getflexibook().getAppointments().size()>0) {
			int count = 0;
			for(BookableService a: FlexiBookApplication.getflexibook().getBookableServices()) {
				count=count+1;
				noShowComboBox.addItem(count);
			}
		}

		noShowComboBox.setBounds(500, 80, 60, 30);

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
		overviewDatePicker.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				try {
					refresh();
				} catch (InvalidInputException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		overviewDateLabel = new JLabel();
		overviewDateLabel.setText("Date for Overview:");
		overviewDateLabel.setBounds(50, 150, 150, 20);
		panelOwnerMainPage.add(overviewDateLabel);

		if(FlexiBookApplication.getflexibook().getAppointments().size()>0) {
			overviewTable = new JTable();
			overviewDtm = new DefaultTableModel(0,0);
			overviewTable.setModel(overviewDtm);
			overviewDtm.addColumn("#");
			overviewDtm.addColumn("Customer");
			overviewDtm.addColumn("Service");
			overviewDtm.addColumn("Start Time");
			overviewDtm.addColumn("Status");
			overviewDtm.addColumn("No show");
			int index =0;

			for(Appointment a : FlexiBookApplication.getflexibook().getAppointments()) {
				if(a.getTimeSlot().getStartDate().toString().equals(overviewDatePicker.getModel().getValue().toString())) {
					index++;
					String[] obj = {String.valueOf(index),a.getCustomer().getUsername(), a.getBookableService().getName(), a.getTimeSlot().getStartTime().toString(),String.valueOf(a.getAppointmentInProgress()),String.valueOf(a.getNoShowCheck())};
					overviewDtm.addRow(obj); 
				}
			}
			overviewTable.setBounds(50, 250, 600, HEIGHT_OVERVIEW_TABLE);
			panelOwnerMainPage.add(overviewTable);
		}
		else {
			overviewTable = new JTable();
			overviewDtm = new DefaultTableModel(0,0);
			overviewTable.setModel(overviewDtm);
			overviewDtm.addColumn("#");
			overviewDtm.addColumn("Customer");
			overviewDtm.addColumn("Service");
			overviewDtm.addColumn("Start Time");
			overviewDtm.addColumn("Status");
			overviewDtm.addColumn("No show");
			String[] info2 = {"none","none","none","none","none","none"};
			overviewDtm.addRow(info2);
			overviewTable.setBounds(50, 250, 600, HEIGHT_OVERVIEW_TABLE);
			panelOwnerMainPage.add(overviewTable);
		}

		NumberLbl.setBounds(50, 210, 100, 50);
		NumberLbl.setText("#");
		CustomerLbl.setText("Customer Name");
		CustomerLbl.setBounds(150, 210, 100, 50);
		ServiceLabel.setText("Service");
		HourLbl.setText("Start Time");
		ServiceLabel.setBounds(250, 210, 100, 50);
		HourLbl.setBounds(350, 210, 100, 50);
		StatusLbl.setText("Status");
		StatusLbl.setBounds(450, 210, 100, 50);
		NoShowLbl.setText("No show");
		NoShowLbl.setBounds(550,210,100,50);
		panelOwnerMainPage.add(StatusLbl);
		panelOwnerMainPage.add(NoShowLbl);
		panelOwnerMainPage.add(NumberLbl);
		panelOwnerMainPage.add(CustomerLbl);
		panelOwnerMainPage.add(ServiceLabel);
		panelOwnerMainPage.add(HourLbl);
		panelOwnerMainPage.add(startAppointment);
		panelOwnerMainPage.add(endAppointment);
		panelOwnerMainPage.add(noShow);
		panelOwnerMainPage.add(editAccount);
		panelOwnerMainPage.add(viewService);
		panelOwnerMainPage.add(businessInfo);
		panelOwnerMainPage.add(logOut);
		panelOwnerMainPage.add(startComboBox);
		panelOwnerMainPage.add(endComboBox);
		panelOwnerMainPage.add(noShowComboBox);
		refresh();
		frame.setVisible(true);
	}

	public static void refresh() throws InvalidInputException {
		if(FlexiBookApplication.getflexibook().getAppointments().size()>0) {
			String b = overviewDatePicker.getModel().getValue().toString();
			overviewDtm = new DefaultTableModel(0,0);
			overviewTable.setModel(overviewDtm);
			overviewDtm.addColumn("#");
			overviewDtm.addColumn("Customer");
			overviewDtm.addColumn("Service");
			overviewDtm.addColumn("Start Time");
			overviewDtm.addColumn("Status");
			overviewDtm.addColumn("No show");
			int index =0;

			for(Appointment a : FlexiBookApplication.getflexibook().getAppointments()) {
				if(a.getTimeSlot().getStartDate().toString().equals(overviewDatePicker.getModel().getValue().toString())) {
					index++;
					String[] obj = {String.valueOf(index),a.getCustomer().getUsername(), a.getBookableService().getName(), a.getTimeSlot().getStartTime().toString(),String.valueOf(a.getAppointmentInProgress()),String.valueOf(a.getNoShowCheck())};
					overviewDtm.addRow(obj); 
				}
			}
			overviewTable.setBounds(50, 250, 600, HEIGHT_OVERVIEW_TABLE);
			panelOwnerMainPage.add(overviewTable);
		}
		else {
			overviewDtm = new DefaultTableModel(0,0);
			overviewTable.setModel(overviewDtm);
			overviewDtm.addColumn("#");
			overviewDtm.addColumn("Customer");
			overviewDtm.addColumn("Service");
			overviewDtm.addColumn("Start Time");
			overviewDtm.addColumn("Status");
			overviewDtm.addColumn("No show");
			String[] info2 = {"none","none","none","none","none","none"};
			//			overviewTable = new JTable(info2, column);
			overviewDtm.addRow(info2);
			overviewTable.setBounds(50, 250, 600, HEIGHT_OVERVIEW_TABLE);
			panelOwnerMainPage.add(overviewTable);
		}

		startComboBox.removeAllItems();
		if(FlexiBookApplication.getflexibook().getAppointments().size()>0) {
			int count = 0;
			for(BookableService a: FlexiBookApplication.getflexibook().getBookableServices()) {
				count=count+1;
				startComboBox.addItem(count);
			}
		}

		startComboBox.setBounds(150, 80, 60, 30); 

		endComboBox.removeAllItems();
		if(FlexiBookApplication.getflexibook().getAppointments().size()>0) {
			int count = 0;
			for(BookableService a: FlexiBookApplication.getflexibook().getBookableServices()) {
				count=count+1;
				endComboBox.addItem(count);
			}
		}    
		endComboBox.setBounds(350, 80, 60, 30);

		noShowComboBox.removeAllItems();
		if(FlexiBookApplication.getflexibook().getAppointments().size()>0) {
			int count = 0;
			for(BookableService a: FlexiBookApplication.getflexibook().getBookableServices()) {
				count=count+1;
				noShowComboBox.addItem(count);
			}
		} 
		noShowComboBox.setBounds(500, 80, 60, 30);

		panelOwnerMainPage.add(startComboBox);
		panelOwnerMainPage.add(endComboBox);
		panelOwnerMainPage.add(noShowComboBox);
	}

	private static void startAppointmentActionPerformed(java.awt.event.ActionEvent evt) {
		SystemTime.SystemTime(null, false);
		try {
			FlexibookController.startAppointment(FlexiBookApplication.getflexibook().getOwner().getUsername(), 
					FlexiBookApplication.getflexibook().getAppointment(Integer.valueOf(startComboBox.getSelectedItem().toString()) - 1));
			errorMessage.setText("Started the appointment!");
			refresh();
		} catch(InvalidInputException e) {
			errorMessage.setText(" ");
			errorMessage.setText(e.getMessage());
		}
	}

	private static void endAppointmentActionPerformed(java.awt.event.ActionEvent evt) {
		SystemTime.SystemTime(null, false);
		try {
			FlexibookController.endAppointment(FlexiBookApplication.getflexibook().getOwner().getUsername(), 
					FlexiBookApplication.getflexibook().getAppointment(Integer.valueOf(endComboBox.getSelectedItem().toString()) - 1));
			errorMessage.setText("Ended the appointment!");
			refresh();
		} catch(InvalidInputException e) {
			errorMessage.setText(" ");
			errorMessage.setText(e.getMessage());
		}
	}

	private static void noShowActionPerformed(java.awt.event.ActionEvent evt) {
		SystemTime.SystemTime(null, false);
		try {
			Appointment a = FlexiBookApplication.getflexibook().getAppointment(Integer.valueOf(noShowComboBox.getSelectedItem().toString()) - 1);
			String startTime = a.getTimeSlot().getStartTime().toString().substring(0,a.getTimeSlot().getStartTime().toString().length()-3);
			FlexibookController.noShowCheck(a.getCustomer().getUsername(), FlexiBookApplication.getflexibook().getOwner().getUsername(), 
					a.getBookableService().getName(), a.getTimeSlot().getStartDate().toString(), startTime);
			errorMessage.setText("The customer did not show up!");
			refresh();
		} catch(InvalidInputException e) {
			errorMessage.setText(" ");
			errorMessage.setText(e.getMessage());
		}
	}

	private static void editAccountActionPerformed(java.awt.event.ActionEvent evt) {
		refreshError();
		FlexiBookApplication.ownertoedit();
	}


	private static void viewServiceActionPerformed(java.awt.event.ActionEvent evt) {
		refreshError();
		FlexiBookApplication.ownertoservice();
	}

	private static void businessinfo(ActionEvent evt) {
		refreshError();
		FlexiBookApplication.ownertobusiness();
	}

	private static void logOutActionPerformed(java.awt.event.ActionEvent evt) {
		try {
			FlexibookController.LogOut();
			FlexiBookApplication.logout();
			refreshError();
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
