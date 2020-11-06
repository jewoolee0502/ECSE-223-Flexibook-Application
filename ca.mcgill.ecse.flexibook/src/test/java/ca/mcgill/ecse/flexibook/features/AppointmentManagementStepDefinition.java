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
import ca.mcgill.ecse.flexibook.model.AppointmentManagement.AppointmentStatus;
import ca.mcgill.ecse.flexibook.model.BookableService;
import ca.mcgill.ecse.flexibook.model.ComboItem;
import ca.mcgill.ecse.flexibook.model.Customer;
import ca.mcgill.ecse.flexibook.model.FlexiBook;
import ca.mcgill.ecse.flexibook.model.Service;
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
			FlexibookController.MakeAppointment(string, string3, string2, null, string4);}
		catch(InvalidInputException e) {
			error = e.getMessage();
			FlexiBookApplication.setmessage(error);
		}
		appointment = flexibook.getAppointment(1);
	}
	@When("{string} attempts to change the service in the appointment to {string} at {string}")
	public void attempts_to_change_the_service_in_the_appointment_to_at(String string, String string2, String string3) {
		SystemTime.SystemTime(string3, true);

		for (BookableService s: flexibook.getBookableServices())
		{
			if (s.getName().equals(string2)) {
				break;
			}
			index ++;
		}
		service= (Service) flexibook.getBookableService(index);
		appointment.updateAppointment(appointment.getTimeSlot(), service, list);
	}

	@When("{string} attempts to update the date to {string} and time to {string} at {string}")
	public void attempts_to_update_the_date_to_and_time_to_at(String string, String string2, String string3, String string4) {
		SystemTime.SystemTime(string4, true);
		timeslot = appointment.getTimeSlot();
		Date date = Date.valueOf(string2);
		Time time = Time.valueOf(string3);
		timeslot.setStartDate(date);
		timeslot.setStartTime(time);
		appointment.updateAppointment(timeslot, null, null);
	}

	@When("{string} attempts to cancel the appointment at {string}")
	public void attempts_to_cancel_the_appointment_at(String string, String string2) throws InvalidInputException {
		try {
			appointment.cancelAppointment();
		}
		catch(RuntimeException e) {
			throw new InvalidInputException(e.getMessage());
		}
	}
	@When("{string} makes a {string} appointment without choosing optional services for the date {string} and time {string} at {string}")
	public void makes_a_appointment_without_choosing_optional_services_for_the_date_and_time_at(String string, String string2, String string3, String string4, String string5) {
		// Write code here that turns the phrase above into concrete actions
		throw new io.cucumber.java.PendingException();
	}
	@When("{string} attempts to add the optional service {string} to the service combo in the appointment at {string}")
	public void attempts_to_add_the_optional_service_to_the_service_combo_in_the_appointment_at(String string, String string2, String string3) {
		// Write code here that turns the phrase above into concrete actions
		throw new io.cucumber.java.PendingException();
	}

	@When("the owner starts the appointment at {string}")
	public void the_owner_starts_the_appointment_at(String string) {
		// Write code here that turns the phrase above into concrete actions
		throw new io.cucumber.java.PendingException();
	}

	@When("the owner ends the appointment at {string}")
	public void the_owner_ends_the_appointment_at(String string) {
		// Write code here that turns the phrase above into concrete actions
		throw new io.cucumber.java.PendingException();
	}
	@When("the owner attempts to end the appointment at {string}")
	public void the_owner_attempts_to_end_the_appointment_at(String string) {
		// Write code here that turns the phrase above into concrete actions
		throw new io.cucumber.java.PendingException();
	}

	@Then("the service combo in the appointment shall be {string}")
	public void the_service_combo_in_the_appointment_shall_be(String string) {
		// Write code here that turns the phrase above into concrete actions
		throw new io.cucumber.java.PendingException();
	}
	@Then("the service combo shall have {string} selected services")
	public void the_service_combo_shall_have_selected_services(String string) {
		// Write code here that turns the phrase above into concrete actions
		throw new io.cucumber.java.PendingException();
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
		assertEquals(int1,noShowCount);
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
