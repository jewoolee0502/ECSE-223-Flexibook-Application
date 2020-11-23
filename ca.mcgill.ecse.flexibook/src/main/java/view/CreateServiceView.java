package view;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import ca.mcgill.ecse.flexibook.Controller.FlexibookController;
import ca.mcgill.ecse.flexibook.Controller.InvalidInputException;
import ca.mcgill.ecse.flexibook.application.FlexiBookApplication;

public class CreateServiceView extends JFrame {

	private static int Width = 700;
	private static int Length = 700;
	private static JFrame frame = new JFrame();
	private static JLabel Title = new JLabel();
	private static JPanel panelCreateService = new JPanel();
	private static JLabel service = new JLabel();
	private static JLabel duration = new JLabel();
	private static JLabel downtimeStart = new JLabel();
	private static JComboBox<String> downstart = new JComboBox();
	private static JLabel downtimeDuration = new JLabel();
	private static JComboBox<String> downDuration = new JComboBox();
	private static JTextField newService = new JTextField();
	private static JButton create = new JButton();
	private static JButton cancel = new JButton();
	private static JLabel errorMessage = new JLabel("");
	private static JLabel success = new JLabel();
	private static JComboBox<String> timeDuration;

	public static void main(String[] args) {
		init_component_createService();
	}

	public CreateServiceView() {
		init_component_createService();
	}

	private static void init_component_createService() {
		Font font1 = new Font("Times New Romans", Font.BOLD, 20);

		panelCreateService.setLayout(null);

		frame.add(panelCreateService);
		frame.setSize(Width, Length);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		Title.setText("Create a New Service:");
		Title.setBounds(225,  10,  400,  75);
		Title.setFont(font1);
		panelCreateService.add(Title);

		service.setText("New Service Name:");
		service.setBounds(280, 100, 150, 25);
		panelCreateService.add(service);

		newService.setBounds(240, 120, 200, 35);
		panelCreateService.add(newService);

		duration.setText("New Service Duration:");
		duration.setBounds(270, 180, 200, 25);
		panelCreateService.add(duration);

		String duration[] = {"", "5", "10", "15", "20", "25", "30", "35", "40",
								"45", "50", "55", "60", "65", "70", "75", "80",
								"85", "90", "95", "100"};
		timeDuration = new JComboBox(duration);
		timeDuration.setBounds(270, 200, 150, 25);
		panelCreateService.add(timeDuration);

		downtimeStart.setText("Downtime Start:");
		downtimeStart.setBounds(180, 250, 120, 25);
		panelCreateService.add(downtimeStart);

		String downStartComboBox[] = {"", "5", "10", "15", "20", "25", "30", 
										"35", "40", "45", "50"};
		downstart = new JComboBox(downStartComboBox);
		downstart.setBounds(180, 270, 120, 25);
		panelCreateService.add(downstart);
		
		downtimeDuration.setText("Downtime Duration:");
		downtimeDuration.setBounds(370, 250, 200, 25);
		panelCreateService.add(downtimeDuration);
		
		String downDurationComboBox[] = {"", "5", "10", "15", "20", "25", "30", 
											"35", "40", "45", "50"};
		downDuration = new JComboBox(downDurationComboBox);
		downDuration.setBounds(370,270,120,25);
		panelCreateService.add(downDuration);


		create.setText("Create New Service");
		create.setBounds(180, 350, 150, 35);
		create.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				createActionPerformed(evt);
			}});
		panelCreateService.add(create);

		cancel.setText("Cancel");
		cancel.setBounds(390, 350, 80, 35);
		cancel.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				cancelActionPerformed(evt);
			}});
		panelCreateService.add(cancel);


		success = new JLabel(""); //what is this?
		success.setBounds(70, 380, 200, 25);
		success.setForeground(Color.BLUE);
		panelCreateService.add(success);

		errorMessage.setBounds(330, 380, 300, 25);
		errorMessage.setForeground(Color.red);
		panelCreateService.add(errorMessage);

		frame.setVisible(true);





		//		JPanel panel = new JPanel();
		//		JFrame frame = new JFrame();
		//		JLabel Title = new JLabel();
		//		frame.setSize(700, 700);
		//		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//		frame.add(panel);
		//
		//
		//		panel.setLayout(null);
		//
		//		service = new JLabel("Service Name");
		//		service.setBounds(70, 70, 80, 25);
		//		panel.add(service);
		//
		//		newservice = new JTextField(70);
		//		newservice.setBounds(70,110,165,25);
		//		panel.add(newservice);
		//
		//		duration = new JLabel("Service duration");
		//		duration.setBounds(70,150,120,25);
		//		panel.add(duration);
		//
		//		timeduration = new JComboBox<String>();
		//		timeduration.addItem("");
		//		timeduration.addItem("5");
		//		timeduration.addItem("10");
		//		timeduration.addItem("15");
		//		timeduration.addItem("20");
		//		timeduration.addItem("25");
		//		timeduration.addItem("30");
		//		timeduration.addItem("35");
		//		timeduration.addItem("40");
		//		timeduration.addItem("45");
		//		timeduration.addItem("50");
		//		timeduration.addItem("55");
		//		timeduration.addItem("60");
		//		timeduration.addItem("65");
		//		timeduration.addItem("70");
		//		timeduration.addItem("75");
		//		timeduration.addItem("80");
		//		timeduration.addItem("85");
		//		timeduration.addItem("90");
		//		timeduration.addItem("95");
		//		timeduration.addItem("100");
		//		timeduration.setEditable(true);
		//		System.out.println("#items=" + timeduration.getItemCount());
		//		timeduration.setBounds(70,190,120,25);
		//		panel.add(timeduration);
		//
		//		downtimestart = new JLabel("DowntimeStart");
		//		downtimestart.setBounds(70,230,120,25);
		//		panel.add(downtimestart);
		//
		//		downstart = new JComboBox<String>();
		//		downstart.addItem("");
		//		downstart.addItem("0");
		//		downstart.addItem("5");
		//		downstart.addItem("10");
		//		downstart.addItem("15");
		//		downstart.addItem("20");
		//		downstart.addItem("25");
		//		downstart.addItem("30");
		//		downstart.addItem("35");
		//		downstart.addItem("40");
		//		downstart.addItem("45");
		//		downstart.addItem("50");
		//		downstart.setEditable(true);
		//		System.out.println("#items=" + downstart.getItemCount());
		//		downstart.setBounds(70, 270, 120, 25);
		//		panel.add(downstart);
		//
		//		downtimeduration = new JLabel("Downtime Duration");
		//		downtimeduration.setBounds(300, 230, 120, 25);
		//		panel.add(downtimeduration);
		//
		//		downduration = new JComboBox<String>();
		//		downduration.addItem("");
		//		downduration.addItem("0");
		//		downduration.addItem("5");
		//		downduration.addItem("10");
		//		downduration.addItem("15");
		//		downduration.addItem("20");
		//		downduration.addItem("25");
		//		downduration.addItem("30");
		//		downduration.addItem("35");
		//		downduration.addItem("40");
		//		downduration.addItem("45");
		//		downduration.addItem("50");
		//		downduration.setEditable(true);
		//		System.out.println("#items=" + downduration.getItemCount());
		//		downduration.setBounds(300,270,120,25);
		//		panel.add(downduration);
		//
		//		create = new JButton("Create new Service");
		//		create.setBounds(70, 350, 150, 25);
		//		create.addActionListener(new java.awt.event.ActionListener() {
		//			public void actionPerformed(java.awt.event.ActionEvent evt) {
		//				createActionPerformed(evt);
		//			}});
		//		panel.add(create);
		//
		//		cancel = new JButton("Cancel");
		//		cancel.setBounds(330, 350, 80, 25);
		//		cancel.addActionListener(new java.awt.event.ActionListener() {
		//			public void actionPerformed(java.awt.event.ActionEvent evt) {
		//				cancelActionPerformed(evt);
		//			}});
		//		panel.add(cancel);
		//
		//
		//		success = new JLabel("");
		//		success.setBounds(70, 380, 200, 25);
		//		success.setForeground(Color.BLUE);
		//		panel.add(success);
		//
		//		errorMessage = new JLabel("");
		//		errorMessage.setBounds(330, 380, 300, 25);
		//		errorMessage.setForeground(Color.red);
		//		panel.add(errorMessage);
		//
		//		frame.setVisible(true);
	}

	private static void createActionPerformed(java.awt.event.ActionEvent evt) {
		String s = service.getText();
		String d = duration.getText();
		if(s.equals(null) || d.equals(null) ) {
			success.setText("Service name/duration can not be empty");
		}else {
			try {
				FlexibookController.addService(FlexiBookApplication.getCurrentuser().getUsername(), service.getText(), duration.getText(), downstart.getSelectedItem().toString(), 
						downDuration.getSelectedItem().toString()); 
			}catch (InvalidInputException e) {
				errorMessage.setText(e.getMessage());
			}
			success.setText("Successfully add a new service!");
		}
	}

	private static void cancelActionPerformed(java.awt.event.ActionEvent evt) {
		//add code to go back to ownerview page
	}
}
