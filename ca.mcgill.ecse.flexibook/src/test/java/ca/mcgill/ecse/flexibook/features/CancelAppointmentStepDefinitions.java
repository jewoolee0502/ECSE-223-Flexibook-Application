package ca.mcgill.ecse.flexibook.features;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class CancelAppointmentStepDefinitions {
	
  @When("{string} attempts to cancel their {string} appointment on {string} at {string}")
	public void attempts_to_cancel_their_appointment_on_at(String string, String string2, String string3, String string4) {
	    
	}
	
	@When("{string} attempts to cancel {string}'s {string} appointment on {string} at {string}")
	public void attempts_to_cancel_s_appointment_on_at(String string, String string2, String string3, String string4, String string5) {
	    // Write code here that turns the phrase above into concrete actions
	   
	}

	@Then("{string}'s {string} appointment on {string} at {string} shall be removed from the system")
	public void s_appointment_on_at_shall_be_removed_from_the_system(String string, String string2, String string3, String string4) {
	    // Write code here that turns the phrase above into concrete actions
	    
	}
	@Then("there shall be {int} less appointment in the system")
	public void there_shall_be_less_appointment_in_the_system(Integer int1) {

		
	}

	
}
