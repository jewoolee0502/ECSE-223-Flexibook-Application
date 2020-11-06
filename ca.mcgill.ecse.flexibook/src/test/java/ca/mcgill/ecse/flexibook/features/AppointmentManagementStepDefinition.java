package ca.mcgill.ecse.flexibook.features;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.text.SimpleDateFormat;
import java.util.List;
import java.sql.Date;
import java.sql.Time;

import ca.mcgill.ecse.flexibook.Controller.FlexibookController;
import ca.mcgill.ecse.flexibook.Controller.InvalidInputException;
import ca.mcgill.ecse.flexibook.application.FlexiBookApplication;
import ca.mcgill.ecse.flexibook.model.Appointment;
import ca.mcgill.ecse.flexibook.model.BookableService;
import ca.mcgill.ecse.flexibook.model.ComboItem;
import ca.mcgill.ecse.flexibook.model.Customer;
import ca.mcgill.ecse.flexibook.model.FlexiBook;
import ca.mcgill.ecse.flexibook.model.Owner;
import ca.mcgill.ecse.flexibook.model.Service;
import ca.mcgill.ecse.flexibook.model.ServiceCombo;
import ca.mcgill.ecse.flexibook.model.TimeSlot;
import ca.mcgill.ecse.flexibook.util.SystemTime;
import io.cucumber.java.After;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class AppointmentManagementStepDefinition {

	private FlexiBook flexibook = FlexiBookApplication.getflexibook();
	private String error;
	private Appointment appointment;
	private Service service;
	private TimeSlot timeslot;
	private List<ComboItem> list;
	private int index;

	@Given("{string} has {int} no-show records")
	public void has_no_show_records(String string, Integer int1) {
		int cindex = -1;

		for(Customer c : flexibook.getCustomers()) {
			if(c.getUsername().equals(string)) {
				cindex = flexibook.indexOfCustomer(c);
			}
		}
		flexibook.getCustomer(cindex).setNoShowCount(int1);
	}

	@When("{string} makes a {string} appointment for the date {string} and time {string} at {string}")
	public void makes_a_appointment_for_the_date_and_time_at(String string, String string2, String string3, String string4, String string5) {
		SystemTime.SystemTime(string5, true);
		try {
			FlexibookController.MakeAppointment(string, string3, string2, null, string4);
			appointment = flexibook.getAppointment(flexibook.getAppointments().size() - 1);
		}
		catch(InvalidInputException e) {
			error = e.getMessage();
			FlexiBookApplication.setmessage(error);
		}
	}

	@When("{string} attempts to change the service in the appointment to {string} at {string}")
	public void attempts_to_change_the_service_in_the_appointment_to_at(String string, String string2, String string3) throws InvalidInputException {

		SystemTime.setSysTime(string3);
		
		for (BookableService s: flexibook.getBookableServices()) {
			if (s.getName().equals(string2)) {
				break;
			}
			index ++;
		}
		service = (Service) flexibook.getBookableService(index);
		String startTime = appointment.getTimeSlot().getStartTime().toString();
		String startDate = appointment.getTimeSlot().getStartDate().toString();
		String newStartTime = startTime.substring(0, startTime.length()-3);
		FlexibookController.CancelAppointment(string, null, 
				appointment.getTimeSlot().getStartDate().toString(), appointment.getTimeSlot().getStartTime().toString());
		if(appointment.getAppointmentStatus().toString().equals("Final")) {
			FlexibookController.MakeAppointment(string, startDate, string2, null, newStartTime);
		}
		appointment = flexibook.getAppointment(1);
	}

	@When("{string} attempts to update the date to {string} and time to {string} at {string}")
	public void attempts_to_update_the_date_to_and_time_to_at(String string, String string2, String string3, String string4) {
		SystemTime.setSysTime(string4);;
		appointment = flexibook.getAppointment(1);
		timeslot = appointment.getTimeSlot();
		Date date = timeslot.getStartDate();
		Time start = timeslot.getStartTime();
		String optionalServices=null;
		BookableService thiService=appointment.getBookableService();
		String serviceDate=date.toString();
		String startTimeFomat=start.toString();
		String startTime=startTimeFomat.substring(0,startTimeFomat.length()-3);
		String newStartDateString=string2;
		String newStartTime=string3;
		String serviceName=appointment.getBookableService().getName();
		if(thiService instanceof ServiceCombo) {

			List<ComboItem>items= ((ServiceCombo) thiService).getServices();
			if(items.size()>1) {
				for(int i=1;i<items.size();i++) {
					optionalServices+=items.get(i).getService().getName();
					if(i<items.size()-1) {
						optionalServices+=",";
					}
				}
			}

		}
		try {
			//String customer, String customer2, String name, String serviceDate, String startTime
			FlexibookController.UpdateAppointment(string, null, null, null, serviceName, serviceDate, newStartDateString, startTime, newStartTime);
		} catch (Exception e)
		{
			error = e.getMessage();
			FlexiBookApplication.setmessage(error);
		}
	}

	@When("{string} attempts to cancel the appointment at {string}")
	public void attempts_to_cancel_the_appointment_at(String string, String string2) throws InvalidInputException {
		SystemTime.setSysTime(string2);
		String a = appointment.getTimeSlot().getStartTime().toString();
		try {
			FlexibookController.CancelAppointment(string, null, appointment.getTimeSlot().getStartDate().toString(), appointment.getTimeSlot().getStartTime().toString());
			//appointment.cancelAppointment();
		}
		catch(RuntimeException e) {

		}
	}

	@When("{string} makes a {string} appointment without choosing optional services for the date {string} and time {string} at {string}")
	public void makes_a_appointment_without_choosing_optional_services_for_the_date_and_time_at(String string, 
			String string2, String string3, String string4, String string5) {

		SystemTime.setSysTime(string5);
		try {
			
			FlexibookController.MakeAppointment(string, string3, string2, null, string4);
			appointment = flexibook.getAppointment(flexibook.getAppointments().size() - 1);
		}
		catch(InvalidInputException e) {
			error = e.getMessage();
			FlexiBookApplication.setmessage(error);
		}
	}

	@When("{string} attempts to add the optional service {string} to the service combo in the appointment at {string}")
	public void attempts_to_add_the_optional_service_to_the_service_combo_in_the_appointment_at(String string, String string2, String string3) {

		SystemTime.SystemTime(string3, true);
		timeslot = appointment.getTimeSlot();
		Date date = timeslot.getStartDate();
		Time start = timeslot.getStartTime();
		String serviceDate=date.toString();
		String newDate=serviceDate;
		String startTimeFomat=start.toString();
		String startTime=startTimeFomat.substring(0,startTimeFomat.length()-3);
		String newStartTime=startTime;
		String serviceName=appointment.getBookableService().getName();

		try {
			FlexibookController.UpdateAppointment(string, null, "add", string2, serviceName, serviceDate, newDate, startTime, newStartTime);
			appointment = flexibook.getAppointment(1);
		}
		catch(InvalidInputException e) {
			error = e.getMessage();
			FlexiBookApplication.setmessage(error);
		}
	}

	@When("the owner starts the appointment at {string}")
	public void the_owner_starts_the_appointment_at(String string) throws InvalidInputException {
		SystemTime.setSysTime(string);
		Owner owner = flexibook.getOwner();
		Appointment a = appointment;
		FlexiBook f = flexibook;
		FlexibookController.startAppointment(flexibook.getOwner().getUsername(), appointment);
	}

	@When("the owner ends the appointment at {string}")
	public void the_owner_ends_the_appointment_at(String string) throws InvalidInputException {
		SystemTime.setSysTime(string);
		Owner owner = flexibook.getOwner();
		FlexibookController.endAppointment(flexibook.getOwner().getUsername(), appointment);
	}
	@When("the owner attempts to end the appointment at {string}")
	public void the_owner_attempts_to_end_the_appointment_at(String string) throws InvalidInputException {
		Owner owner = flexibook.getOwner();
		appointment.endAppointment(); //FlexibookController
		SystemTime.SystemTime(string, true);
		try {
			appointment.endAppointment(); //FlexibookController
		}
		catch(RuntimeException e) {
			throw new InvalidInputException(e.getMessage());
		}
	}

	@When("the owner attempts to register a no-show for the appointment at {string}")
	public void the_owner_attempts_to_register_a_no_show_for_the_appointment_at(String string) throws InvalidInputException {
		SystemTime.setSysTime(string);
		FlexibookController.noShowCheck(appointment.getCustomer().getUsername(), appointment.getFlexiBook().getOwner().getUsername(), 
				null, appointment.getTimeSlot().getStartDate().toString(), appointment.getTimeSlot().getStartTime().toString());
	}

	@Then("the appointment shall be in progress")
	public void the_appointment_shall_be_in_progress() {
		assertEquals(appointment.getAppointmentInProgress(), true);
	}

	@Then("the service combo in the appointment shall be {string}")
	public void the_service_combo_in_the_appointment_shall_be(String string) {
		String service = appointment.getBookableService().getName();
		assertEquals(string, service);
	}

	@Then("the service combo shall have {string} selected services")
	public void the_service_combo_shall_have_selected_services(String string) {
		BookableService serviceCombo=appointment.getBookableService();
		String allServiceString="";

		if(serviceCombo instanceof ServiceCombo) {
			List<ComboItem>items=((ServiceCombo) serviceCombo).getServices();
			for(int i=0;i<items.size();i++) {
				allServiceString+=items.get(i).getService().getName();
				if(i<items.size()-1) {
					allServiceString+=",";
				}
			}
		}
		assertEquals(string, allServiceString);
	}

	@Then("the system shall have {int} appointment")
	public void the_system_shall_have_appointment(Integer int1) {
		Integer size = new Integer(flexibook.getAppointments().size());
		assertEquals(int1, size);
	}

	@Then("the appointment shall be booked")
	public void the_appointment_shall_be_booked() {
		String appointmentStatus = appointment.getAppointmentStatus().toString();
		assertEquals("Booked", appointmentStatus);
	}
	
	@Then("the service in the appointment shall be {string}")
	public void the_service_in_the_appointment_shall_be(String string) {
		String serviceIn = appointment.getBookableService().getName();
		assertEquals(string, serviceIn);
	}
	
	@Then("the appointment shall be for the date {string} with start time {string} and end time {string}")
	public void the_appointment_shall_be_for_the_date_with_start_time_and_end_time(String string, String string2, String string3) {
		TimeSlot currentTimeSlot = appointment.getTimeSlot();
		String date = currentTimeSlot.getStartDate().toString();
		String start = currentTimeSlot.getStartTime().toString();
		String end = currentTimeSlot.getEndTime().toString();
		assertEquals(string, date);
		assertEquals(string2+":00", start);
		assertEquals(string3+":00", end);
	}
	
	@Then("the username associated with the appointment shall be {string}")
	public void the_username_associated_with_the_appointment_shall_be(String string) {
		String username = appointment.getCustomer().getUsername();
		assertEquals(string, username);
	}
	
	@Then("the user {string} shall have {int} no-show records")
	public void the_user_shall_have_no_show_records(String customer, Integer int1) {
		int cindex = -1;
		int noShowCount;
		for(Customer c : flexibook.getCustomers()) {
			if(c.getUsername().equals(customer)) {
				cindex = flexibook.indexOfCustomer(c);
			}
		}
		noShowCount=flexibook.getCustomer(cindex).getNoShowCount();
		assertEquals(int1, noShowCount);
	}

	@Then("the system shall have {int} appointments")
	public void the_system_shall_have_appointments(Integer int1) {
		assertEquals(int1, flexibook.getAppointments().size());
	}

	@After
	public void tearDown() {
		flexibook.delete();
	}
}
