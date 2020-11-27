package view;

import java.awt.Color;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import ca.mcgill.ecse.flexibook.Controller.FlexibookController;
import ca.mcgill.ecse.flexibook.Controller.InvalidInputException;
import ca.mcgill.ecse.flexibook.application.FlexiBookApplication;
import ca.mcgill.ecse.flexibook.model.BookableService;
import ca.mcgill.ecse.flexibook.model.Service;
import ca.mcgill.ecse.flexibook.util.SystemTime;

public class UpdateServiceView {
	private static int Width = 700;
	private static int Length = 700;
	public static JFrame frame = new JFrame();
	private static JLabel Title = new JLabel();
	private static JPanel panel = new JPanel();
	private static JLabel service = new JLabel();
	private static JLabel duration = new JLabel();
	private static JLabel downtimeStart = new JLabel();
	private static JLabel changeService = new JLabel();
	private static JComboBox<String> downstart = new JComboBox();
	private static JLabel downtimeDuration = new JLabel();
	private static JComboBox<String> downDuration = new JComboBox();
	private static JTextField newService = new JTextField();
	private static JButton create = new JButton();
	private static JButton cancel = new JButton();
	private static JLabel errorMessage = new JLabel("");
	private static JComboBox<String> timeDuration;
	private static JComboBox<String> existingService;
	private static String error=null;
	private static JButton delete = new JButton();
	private static JButton update = new JButton();

	public static void refreshUpdateS() {
		newService.setText(" ");
		downstart.setSelectedIndex(0);
		downDuration.setSelectedIndex(0);
		timeDuration.setSelectedIndex(0);
		existingService.setSelectedIndex(0);
	}
	
	public static void refreshError() {
		errorMessage.setText(" ");
	}
	
	public static void refreshCombo() {
		existingService.removeAllItems();
		if(FlexiBookApplication.getflexibook().getBookableServices().size() > 0) {
			for(BookableService b: FlexiBookApplication.getflexibook().getBookableServices()) {
				if(b instanceof Service) {
					Service s = (Service) b;
					existingService.addItem(s.getName());
				}
			}
		}
		existingService.setBounds(270, 150, 150, 25);
		panel.add(existingService);
	}

	public UpdateServiceView() {
		init_component_updateService();
	}

	public static void main(String[] args) {
		init_component_updateService();
	}

	private static void init_component_updateService() {
		Font font1 = new Font("Times New Romans", Font.BOLD, 20);

		panel.setLayout(null);

		frame.add(panel);
		frame.setSize(Width, Length);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		Title.setText("Update Service:");
		Title.setBounds(260, 15, 400, 75);
		Title.setFont(font1);
		panel.add(Title);

		changeService.setText("Existing Service Name:");
		changeService.setBounds(270, 120, 200, 25);
		panel.add(changeService);

		existingService = new JComboBox<String>();
		if(FlexiBookApplication.getflexibook().getBookableServices().size() > 0) {
			for(BookableService b: FlexiBookApplication.getflexibook().getBookableServices()) {
				if(b instanceof Service) {
					Service s = (Service) b;
					existingService.addItem(s.getName());
				}
			}
		}
		existingService.setBounds(270, 150, 150, 25);
		panel.add(existingService);

		service.setText("New Service Name:");
		service.setBounds(280, 200, 150, 25);
		panel.add(service);

		newService.setBounds(240, 220, 200, 35);
		panel.add(newService);

		duration.setText("New Service Duration:");
		duration.setBounds(270, 280, 200, 25);
		panel.add(duration);

		String duration[] = {"", "5", "10", "15", "20", "25", "30", "35", "40",
				"45", "50", "55", "60", "65", "70", "75", "80",
				"85", "90", "95", "100"};
		timeDuration = new JComboBox(duration);
		timeDuration.setBounds(270, 310, 150, 25);
		panel.add(timeDuration);

		downtimeStart.setText("Downtime Start:");
		downtimeStart.setBounds(180, 360, 120, 25);
		panel.add(downtimeStart);

		String downStartComboBox[] = {"","0", "5", "10", "15", "20", "25", "30", "35", "40",
				"45", "50", "55", "60", "65", "70", "75", "80",
				"85", "90", "95", "100"};
		downstart = new JComboBox(downStartComboBox);
		downstart.setBounds(180, 390, 120, 25);
		panel.add(downstart);

		downtimeDuration.setText("Downtime Duration:");
		downtimeDuration.setBounds(370, 360, 200, 25);
		panel.add(downtimeDuration);

		String downDurationComboBox[] = {"","0", "5", "10", "15", "20", "25", "30", "35", "40",
				"45", "50", "55", "60", "65", "70", "75", "80",
				"85", "90", "95", "100"};
		downDuration = new JComboBox(downDurationComboBox);
		downDuration.setBounds(370, 390, 120, 25);
		panel.add(downDuration);

		cancel.setText("Back");
		cancel.setBounds(480, 460, 80, 25);
		cancel.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				try {
					cancelActionPerformed(evt);
				} catch (InvalidInputException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}});
		panel.add(cancel);

		update.setText("Update Service");
		update.setBounds(130, 460, 130, 25);
		update.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				updateActionPerformed(evt);
			}});
		panel.add(update);

		delete.setText("Delete Service");
		delete.setBounds(300, 460, 130, 25);
		delete.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				deleteActionPerformed(evt);
			}});
		panel.add(delete);

		errorMessage.setBounds(200, 500, 500, 25);
		errorMessage.setForeground(Color.red);
		panel.add(errorMessage);

		frame.setVisible(true);
	}

	private static void updateActionPerformed(java.awt.event.ActionEvent evt) {
		error=null;
		try {
			//we need to find a way to get old service name and the owner username/name.
			String string1=FlexiBookApplication.getflexibook().getOwner().getUsername();
			FlexibookController.updateservice(string1, existingService.getSelectedItem().toString(), newService.getText(), timeDuration.getSelectedItem().toString(), 
					downstart.getSelectedItem().toString(), downDuration.getSelectedItem().toString());
			errorMessage.setText("Succesfully updated the service");
		}
		catch(InvalidInputException e){
			errorMessage.setText(" ");
			error=e.getMessage();
			errorMessage.setText(error);
		}
		refreshCombo();
		refreshUpdateS();
	}

	private static void deleteActionPerformed(java.awt.event.ActionEvent evt) {
		SystemTime.SystemTime(null, false);
		try {
			String string1=FlexiBookApplication.getflexibook().getOwner().getUsername();
			FlexibookController.deleteService(string1, existingService.getSelectedItem().toString());
			errorMessage.setText("Successfully deleted service");
		}
		catch(InvalidInputException e) {
			errorMessage.setText(" ");
			error = e.getMessage();
			errorMessage.setText(error);

		}
		refreshCombo();
		refreshUpdateS();
	}

	private static void cancelActionPerformed(java.awt.event.ActionEvent evt) throws InvalidInputException {
		refreshCombo();
		refreshUpdateS();
		refreshError();
		FlexiBookApplication.updateservicecancel();
	}
}
