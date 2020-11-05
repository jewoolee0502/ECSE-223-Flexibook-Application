package ca.mcgill.ecse.flexibook.features;

import static org.junit.jupiter.api.Assertions.assertEquals;

import ca.mcgill.ecse.flexibook.Controller.FlexibookController;
import ca.mcgill.ecse.flexibook.Controller.InvalidInputException;
import ca.mcgill.ecse.flexibook.application.FlexiBookApplication;
import ca.mcgill.ecse.flexibook.model.Customer;
import ca.mcgill.ecse.flexibook.model.FlexiBook;
import ca.mcgill.ecse.flexibook.util.SystemTime;
import io.cucumber.java.After;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class AppointmentManagementStepDefinition {
	private FlexiBook flexibook = FlexiBookApplication.getflexibook();
	private String error;
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
	}
	@When("{string} attempts to change the service in the appointment to {string} at {string}")
	public void attempts_to_change_the_service_in_the_appointment_to_at(String string, String string2, String string3) {
		// Write code here that turns the phrase above into concrete actions
		throw new io.cucumber.java.PendingException();
	}
	@Then("the appointment shall be booked")
	public void the_appointment_shall_be_booked() {
		// Write code here that turns the phrase above into concrete actions
		throw new io.cucumber.java.PendingException();
	}
	@Then("the service in the appointment shall be {string}")
	public void the_service_in_the_appointment_shall_be(String string) {
		// Write code here that turns the phrase above into concrete actions
		throw new io.cucumber.java.PendingException();
	}
	@Then("the appointment shall be for the date {string} with start time {string} and end time {string}")
	public void the_appointment_shall_be_for_the_date_with_start_time_and_end_time(String string, String string2, String string3) {
		// Write code here that turns the phrase above into concrete actions
		throw new io.cucumber.java.PendingException();
	}
	@Then("the username associated with the appointment shall be {string}")
	public void the_username_associated_with_the_appointment_shall_be(String string) {
		// Write code here that turns the phrase above into concrete actions
		throw new io.cucumber.java.PendingException();
	}
	@Then("the user {string} shall have {int} no-show records")
	public void the_user_shall_have_no_show_records(String customer, Integer int1) {
		int cindex = -1;

		for(Customer c : flexibook.getCustomers()) {
			if(c.getUsername().equals(customer)) {
				cindex = flexibook.indexOfCustomer(c);
			}
		}
		flexibook.getCustomer(cindex).setNoShowCount(int1);
		assertEquals(int1,flexibook.getCustomer(cindex).setNoShowCount(int1));
	}

	@Then("the system shall have {int} appointments")
	public void the_system_shall_have_appointments(Integer int1) {
		assertEquals(int1,flexibook.getAppointments().size());
	}
	
	@After
	public void tearDown() {
		flexibook.delete();
	}
}
