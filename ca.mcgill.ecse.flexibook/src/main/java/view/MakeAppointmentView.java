package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import ca.mcgill.ecse.flexibook.Controller.FlexibookController;
import ca.mcgill.ecse.flexibook.Controller.InvalidInputException;
import ca.mcgill.ecse.flexibook.application.FlexiBookApplication;
import ca.mcgill.ecse.flexibook.model.*;
public class MakeAppointmentView extends JFrame {
 
  private static JFrame frame = new JFrame();
  private static JLabel Title = new JLabel();
  private static JLabel User = new JLabel();
  private static String error=null;
  private static JLabel errorMessage = new JLabel("");
  private static JLabel errorMessage2 = new JLabel("");
  private static JLabel date = new JLabel();
  private static JLabel starttime = new JLabel();
  private static JLabel Bookable = new JLabel();
  private static JTextField dateinput = new JTextField();
  private static JTextField usr_input = new JTextField();
  private static JTextField starttimeinput = new JTextField();
  private static JComboBox<String> BookableService = new JComboBox<String>();
  private static JButton make= new JButton();
  private static JButton back=new JButton();
  
  public static void main(String[] args) {JPanel panel = new JPanel();
  Font font1 = new Font("Times New Romans", Font.BOLD, 20);
  panel.setLayout(null);
  frame.add(panel);
  frame.setSize(700, 700);
  frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  
  Title.setText("Making an Appointment: ");
  Title.setBounds(200, 50, 400, 75);
  Title.setFont(font1);
  panel.add(Title);
  date.setText("Start Date(yyyy-mm-dd): ");
  date.setBounds(130, 200, 165, 25);
  panel.add(date);
  dateinput.setBounds(300, 200, 165, 25);
  panel.add(dateinput);
  starttime.setText("Start Time(hh:mm): ");
  starttime.setBounds(165, 250, 165, 25);
  panel.add(starttime);
  starttimeinput.setBounds(300, 250, 165, 25);
  panel.add(starttimeinput);
  Bookable.setText("Please choose a Service: ");
  Bookable.setBounds(135, 300, 165, 25);
  panel.add(Bookable);
  BookableService.setBounds(300,300,165,25);
  panel.add(BookableService);
  if(FlexiBookApplication.getflexibook().getBookableServices().size()>0) {
    for(BookableService a: FlexiBookApplication.getflexibook().getBookableServices()) {
      BookableService.addItem(a.getName());
      }
  }BookableService.addItem("cut");
  BookableService.addItem("wash");
  back.setText("back");
  back.setBounds(350, 350, 100, 25);
  panel.add(back);  
  make.setText("Make");
  make.setBounds(200, 350, 100, 25);
  make.addActionListener(new java.awt.event.ActionListener() {
    public void actionPerformed(java.awt.event.ActionEvent evt) {
     makeappointmentActionPerformed(evt);
  }});
  panel.add(make); 
  errorMessage.setBounds(200, 400, 500, 25);
  errorMessage.setForeground(Color.red);
  panel.add(errorMessage);
  errorMessage2.setBounds(200, 450, 300, 25);
  errorMessage2.setForeground(Color.red);
  panel.add(errorMessage2);
  
  frame.setVisible(true);  
 
  } 
  private static void makeappointmentActionPerformed(ActionEvent evt) {
    try {
      FlexibookController.MakeAppointment(FlexiBookApplication.getCurrentuser().getUsername(),
          date.getText(), BookableService.getSelectedItem().toString(), null, starttime.getText());
      errorMessage.setText("Successfully made an appointment");
    }catch (InvalidInputException e) {
      error = e.getMessage();
      errorMessage.setText(error);
      errorMessage2.setText("Sorry the appointment is not made");
    }
    }
}
