package view;

import java.awt.Font;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import ca.mcgill.ecse.flexibook.Controller.InvalidInputException;
import ca.mcgill.ecse.flexibook.application.FlexiBookApplication;
import ca.mcgill.ecse.flexibook.model.Appointment;
import ca.mcgill.ecse.flexibook.model.BookableService;
import ca.mcgill.ecse.flexibook.model.Service;

public class ViewServicesOwner extends JFrame {

	private static int Width = 700;
	private static int Length = 700;
	public static JFrame frame = new JFrame();
	private static JLabel Title = new JLabel();
	private static JPanel panelViewServices = new JPanel();
	private static JLabel services = new JLabel();
	private static JTable overviewTable = new JTable();
	private static JButton back = new JButton();
	private static JButton create = new JButton();
	private static JButton edit = new JButton();
	private static DefaultTableModel overviewDtm;
	private static final int HEIGHT_OVERVIEW_TABLE = 400;
	private static JLabel name = new JLabel();
	private static JLabel duration = new JLabel();
	private static JLabel dstart = new JLabel();
	private static JLabel downdur = new JLabel();

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
		
		name.setText("Service Name:");
		name.setBounds(50, 150, 150, 25);
		panelViewServices.add(name);
		
		duration.setText("Duration:");
		duration.setBounds(200, 150, 150, 25);
		panelViewServices.add(duration);
		
		dstart.setText("Downtime Start:");
		dstart.setBounds(350, 150, 150, 25);
		panelViewServices.add(dstart);
		
		downdur.setText("Downtime Duration:");
		downdur.setBounds(500, 150, 150, 25);
		panelViewServices.add(downdur);
		
		//Service k = new Service("cut", FlexiBookApplication.getflexibook(), 10, 0, 0);
		
		if(FlexiBookApplication.getflexibook().getBookableServices().size()>0) {
			overviewTable = new JTable();
			overviewDtm = new DefaultTableModel(0,0);
			overviewTable.setModel(overviewDtm);
			overviewDtm.addColumn("Service Name");
			overviewDtm.addColumn("Duration");
			overviewDtm.addColumn("Downtime Start");
			overviewDtm.addColumn("Downtime Duration");
			for(BookableService s: FlexiBookApplication.getflexibook().getBookableServices()) {
				if(s instanceof Service) {
					Service thiss = (Service) s;
					String[] obj = {thiss.getName(), String.valueOf(thiss.getDuration()), String.valueOf(thiss.getDowntimeStart()), String.valueOf(thiss.getDowntimeDuration())};
					overviewDtm.addRow(obj);
				}
			}
			overviewTable.setBounds(50, 180, 600, HEIGHT_OVERVIEW_TABLE);
			panelViewServices.add(overviewTable);
		}
		else {
			overviewTable = new JTable();
			overviewDtm = new DefaultTableModel(0,0);
			overviewTable.setModel(overviewDtm);
			overviewDtm.addColumn("Service Name");
			overviewDtm.addColumn("Duration");
			overviewDtm.addColumn("Downtime Start");
			overviewDtm.addColumn("Downtime Duration");
			String[] info2 = {"none","none","none","none"};
			//			overviewTable = new JTable(info2, column);
			overviewDtm.addRow(info2);
			overviewTable.setBounds(50, 180, 600, HEIGHT_OVERVIEW_TABLE);
			panelViewServices.add(overviewTable);
		}

		back.setText("Back");
		back.setBounds(550, 620, 80, 25);
		back.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				try {
					backActionPerformed(evt);
				} catch (InvalidInputException e) {

				}
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

	public static void refreshService() {
		if(FlexiBookApplication.getflexibook().getBookableServices().size()>0) {
			overviewDtm = new DefaultTableModel(0,0);
			overviewTable.setModel(overviewDtm);
			overviewDtm.addColumn("Service Name");
			overviewDtm.addColumn("Duration");
			overviewDtm.addColumn("Downtime Start");
			overviewDtm.addColumn("Downtime Duration");
			for(BookableService s: FlexiBookApplication.getflexibook().getBookableServices()) {
				if(s instanceof Service) {
					Service thiss = (Service) s;
					String[] obj = {thiss.getName(), String.valueOf(thiss.getDuration()), String.valueOf(thiss.getDowntimeStart()), String.valueOf(thiss.getDowntimeDuration())};
					overviewDtm.addRow(obj);
				}
			}
			overviewTable.setBounds(50, 180, 600, HEIGHT_OVERVIEW_TABLE);
			panelViewServices.add(overviewTable);
		}
		else {
			overviewDtm = new DefaultTableModel(0,0);
			overviewTable.setModel(overviewDtm);
			overviewDtm.addColumn("Service Name");
			overviewDtm.addColumn("Duration");
			overviewDtm.addColumn("Downtime Start");
			overviewDtm.addColumn("Downtime Duration");
			String[] info2 = {"none","none","none","none"};
			overviewDtm.addRow(info2);
			overviewTable.setBounds(50, 180, 600, HEIGHT_OVERVIEW_TABLE);
			panelViewServices.add(overviewTable);
		}
	}

	public ViewServicesOwner() {
		init_component_ViewServicesOwner();
	}

	private static void backActionPerformed(ActionEvent evt) throws InvalidInputException {
		FlexiBookApplication.servicetoowner();
	}

	private static void createActionPerformed(ActionEvent evt) {
		FlexiBookApplication.viewtocreateservice();
	}

	private static void editActionPerformed(ActionEvent evt) {
		FlexiBookApplication.updateservice();
	}

	public static void main(String[] args) {
		init_component_ViewServicesOwner();
	}
}
