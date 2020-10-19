package ca.mcgill.ecse.flexibook.features;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class UpdateAppointmentStepDefinition {

	
	@Given("{string} has a {string} appointment with optional sevices {string} on {string} at {string}")
	public void has_a_appointment_with_optional_sevices_on_at(String string, String string2, String string3, String string4, String string5) {
	    
	}
	
	@When("{string} attempts to update their {string} appointment on {string} at {string} to {string} at {string}")
	public void attempts_to_update_their_appointment_on_at_to_at(String string, String string2, String string3, String string4, String string5, String string6) {
	    
		
	}
	
	@When("{string} attempts to {string} {string} from their {string} appointment on {string} at {string}")
	public void attempts_to_from_their_appointment_on_at(String string, String string2, String string3, String string4, String string5, String string6) {
	    
	}
	
	@When("{string} attempts to update {string}'s {string} appointment on {string} at {string} to {string} at {string}")
	public void attempts_to_update_s_appointment_on_at_to_at(String string, String string2, String string3, String string4, String string5, String string6, String string7) {
	    
	}
	
	@Then("the system shall report that the update was {string}")
	public void the_system_shall_report_that_the_update_was(String string) {
	    
	}


}
