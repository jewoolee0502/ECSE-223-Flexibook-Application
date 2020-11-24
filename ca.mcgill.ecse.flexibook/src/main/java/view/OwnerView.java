package view;

import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;

import ca.mcgill.ecse.flexibook.application.FlexiBookApplication;
import ca.mcgill.ecse.flexibook.model.BookableService;

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

	public OwnerView() {
		init_component_ownerMainPage();
	}

	private static void init_component_ownerMainPage() {
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

		String[] columnName = {"#", "Customer", "Appointment Type", "Date & Time", "Status"};
		String[][] data = {
				{"1", "Customer 1", "Cut", "2020/12/20 - 10:30", "Started"},
				{"2", "Customer 2", "Wash", "2020/12/28 - 14:20", "In Progress"}
		};
		table = new JTable(data, columnName);
		table.setBounds(50, 150, 600, 420);

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
		panelOwnerMainPage.add(table);
		frame.setVisible(true);
	}

	private static void startAppointmentActionPerformed(java.awt.event.ActionEvent evt) {

	}

	private static void endAppointmentActionPerformed(java.awt.event.ActionEvent evt) {

	}

	private static void noShowActionPerformed(java.awt.event.ActionEvent evt) {

	}

	private static void editAccountActionPerformed(java.awt.event.ActionEvent evt) {
    FlexiBookApplication.ownertedit();
	}

	private static void createServiceActionPerformed(java.awt.event.ActionEvent evt) {

	}

	private static void editServiceActionPerformed(java.awt.event.ActionEvent evt) {

	}

	private static void logOutActionPerformed(java.awt.event.ActionEvent evt) {

	}

	public static void main(String[] args) {
		init_component_ownerMainPage();
	}

}
