package ca.mcgill.ecse.flexibook.features;

import static org.junit.Assert.assertEquals;

import ca.mcgill.ecse.flexibook.Controller.FlexibookController;
import ca.mcgill.ecse.flexibook.Controller.InvalidInputException;
import ca.mcgill.ecse.flexibook.application.FlexiBookApplication;
import ca.mcgill.ecse.flexibook.model.*;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class UpdateAppointmentStepDefinition {

	private FlexiBook flexibook = FlexiBookApplication.getflexibook();


	@Given("{string} has a {string} appointment with optional sevices {string} on {string} at {string}")
	public void has_a_appointment_with_optional_sevices_on_at(String customer, String serviceName, String optService, String date, String startTime) throws InvalidInputException {
		FlexibookController.MakeAppointment(customer,date, serviceName,optService ,startTime);	
	}

	@When("{string} attempts to update their {string} appointment on {string} at {string} to {string} at {string}")
	public void attempts_to_update_their_appointment_on_at_to_at(String customer, String serviceName, String serviceDate, String startTime, String newDate, String newStartTime) {
		try {
			FlexibookController.UpdateAppointment(customer, null, null, null, serviceName, serviceDate, newDate, startTime, newStartTime);
		}catch(InvalidInputException e) {
			FlexiBookApplication.setmessage(e.getMessage());
		}

	}

	@When("{string} attempts to {string} {string} from their {string} appointment on {string} at {string}")
	public void attempts_to_from_their_appointment_on_at(String customer, String action, String comboItem, String serviceName, String serviceDate, String startTime) {
		try {
			FlexibookController.UpdateAppointment(customer, null, action, comboItem, serviceName, serviceDate, null, startTime, null);
		}catch(InvalidInputException e) {
			FlexiBookApplication.setmessage(e.getMessage());
		}
	}

	@When("{string} attempts to update {string}'s {string} appointment on {string} at {string} to {string} at {string}")
	public void attempts_to_update_s_appointment_on_at_to_at(String customer, String customer2, String serviceName, String serviceDate, String startTime, String newDate, String newStartTime) {
		try {
			FlexibookController.UpdateAppointment(customer, customer2, null, null, serviceName, serviceDate, newDate, startTime, newStartTime);
		}catch(InvalidInputException e) {
			FlexiBookApplication.setmessage(e.getMessage());
		}
	}

	@Then("the system shall report that the update was {string}")
	public void the_system_shall_report_that_the_update_was(String string) {
		String e = FlexiBookApplication.returnmessage();
		assertEquals(string, e);
		//assertEquals(true,true);
		FlexiBookApplication.setmessage(null);
	}


}
