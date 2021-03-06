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

public class CreateServiceView {

	private static int Width = 700;
	private static int Length = 700;
	public static JFrame frame = new JFrame();
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
	
	public static void refreshCreateS() {
		newService.setText(" ");
		downstart.setSelectedIndex(0);
		downDuration.setSelectedIndex(0);
		timeDuration.setSelectedIndex(0);
	}
	
	public static void refreshError() {
		success.setText(" ");
		errorMessage.setText(" ");
	}

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

		String downStartComboBox[] = {"", "0", "5", "10", "15", "20", "25", "30", "35", "40",
				"45", "50", "55", "60", "65", "70", "75", "80",
				"85", "90", "95", "100"};
		downstart = new JComboBox(downStartComboBox);
		downstart.setBounds(180, 270, 120, 25);
		panelCreateService.add(downstart);
		
		downtimeDuration.setText("Downtime Duration:");
		downtimeDuration.setBounds(370, 250, 200, 25);
		panelCreateService.add(downtimeDuration);
		
		String downDurationComboBox[] = {"", "0", "5", "10", "15", "20", "25", "30", "35", "40",
				"45", "50", "55", "60", "65", "70", "75", "80",
				"85", "90", "95", "100"};
		downDuration = new JComboBox(downDurationComboBox);
		downDuration.setBounds(370,270,120,25);
		panelCreateService.add(downDuration);


		create.setText("Create New Service");
		create.setBounds(180, 350, 150, 25);
		create.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				createActionPerformed(evt);
			}});
		panelCreateService.add(create);

		cancel.setText("Back");
		cancel.setBounds(390, 350, 80, 25);
		cancel.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				try {
					cancelActionPerformed(evt);
				} catch (InvalidInputException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}});
		panelCreateService.add(cancel);


		success = new JLabel(""); 
		success.setBounds(100, 380, 500, 25);
		success.setForeground(Color.red);
		panelCreateService.add(success);

		errorMessage.setBounds(100, 380, 500, 25);
		errorMessage.setForeground(Color.red);
		panelCreateService.add(errorMessage);

		frame.setVisible(true);
		System.out.println(FlexiBookApplication.getflexibook().getBookableServices());

	}

	private static void createActionPerformed(java.awt.event.ActionEvent evt) {
		String s = service.getText();
		String d = timeDuration.getSelectedItem().toString();
		if(s.equals(null) || d.equals(null) ) {
			success.setText("Service name/duration can not be empty");
		}else {
			try {
				String downtime = downDuration.getSelectedItem().toString();
				String downStart = downstart.getSelectedItem().toString();
				
				FlexibookController.addService(FlexiBookApplication.getCurrentuser().getUsername(), newService.getText(), timeDuration.getSelectedItem().toString(),downStart, 
						downtime); 
				refreshCreateS();
				errorMessage.setText(" ");
				success.setText("Successfully created a new service!");
			}catch (InvalidInputException e) {
				success.setText(" ");
				errorMessage.setText(e.getMessage());
			}
		}
	}

	private static void cancelActionPerformed(java.awt.event.ActionEvent evt) throws InvalidInputException {
		FlexiBookApplication.addservicecancel();
		refreshCreateS();
	}
}
