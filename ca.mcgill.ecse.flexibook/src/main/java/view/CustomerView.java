package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ItemListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import ca.mcgill.ecse.flexibook.application.FlexiBookApplication;
import ca.mcgill.ecse.flexibook.model.Appointment;
import ca.mcgill.ecse.flexibook.model.BookableService;

public class CustomerView extends JFrame {
	
	private static int Width = 700;
	private static int Length = 700;
	private static JFrame frame = new JFrame();
	private static JLabel Title = new JLabel();
	private static JLabel errorMessageMakeAppointment = new JLabel("");
	private static JLabel errorMessageUpdateAppointment = new JLabel("");
	private static JLabel errorMessageEditAccount = new JLabel("");
//	private static JLabel comboBoxMessage = new JLabel("Select Appointment #: ");
//	private static JLabel comboBoxMessage2 = new JLabel("# Selected");
	private static JButton makeAppointment = new JButton();
	private static JButton updateAppointment = new JButton();
	private static JButton editAccount = new JButton();
	private static JButton logOut = new JButton("");
	private static JTable table;
	private static JPanel panelCustomerMainPage = new JPanel();
	private static JPanel panelLogOut = new JPanel();
	private static String error;
	private static JComboBox comboBox = new JComboBox();
	
	public CustomerView() {
	  init_component_customerMainPage(); 
	}
	
	
	private static void init_component_customerMainPage() {
		Font font1 = new Font("Times New Romans", Font.BOLD, 20);
//		Font font2 = new Font("Times New Romans", Font.PLAIN, 1);
		
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
		makeAppointment.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				makeAppointmentActionPerformed(evt);
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
	    
	    String[] columnName = {"#", "Appointment Type", "Date & Time"};
	    String[][] data = {
	    		{"1", "Cut", "2020/12/20 - 10:30"},
	    		{"2", "Wash", "2020/12/28 - 14:20"}
	    };
	    table = new JTable(data, columnName);
	    table.setBounds(50, 180, 600, 420);

		panelCustomerMainPage.add(makeAppointment);
		panelCustomerMainPage.add(updateAppointment);
		panelCustomerMainPage.add(editAccount);
		panelCustomerMainPage.add(logOut);
//		panelCustomerMainPage.add(comboBoxMessage);
		panelCustomerMainPage.add(comboBox);
		panelCustomerMainPage.add(table);
		frame.setVisible(true);
	}
	
	private static void makeAppointmentActionPerformed(java.awt.event.ActionEvent evt) {
		
	}
	
	private static void updateAppointmentActionPerformed(java.awt.event.ActionEvent evt) {
	 int k=Integer.parseInt(comboBox.getSelectedItem().toString());
	if(FlexiBookApplication.getflexibook().getAppointments().size()>0) {
	 FlexiBookApplication.setcurap(FlexiBookApplication.getflexibook().getAppointment(k-1));
	}
	}
	
	private static void editAccountActionPerformed(java.awt.event.ActionEvent evt) {
		
	}
	
	private static void logOutActionPerformed(java.awt.event.ActionEvent evt) {
		//make transition back to the main page
	}
	
	public static void main(String[] args) {
		init_component_customerMainPage();
	}
	
}
