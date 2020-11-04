//package ca.mcgill.ecse.flexibook.features;
//
//import ca.mcgill.ecse.flexibook.Controller.FlexibookController;
//import ca.mcgill.ecse.flexibook.Controller.InvalidInputException;
//import ca.mcgill.ecse.flexibook.application.FlexiBookApplication;
//import ca.mcgill.ecse.flexibook.model.*;
//import ca.mcgill.ecse.flexibook.model.Customer;
//import ca.mcgill.ecse.flexibook.model.FlexiBook;
//import io.cucumber.java.en.Then;
//import io.cucumber.java.en.When;
//
//public class CancelAppointmentStepDefinitions {
//	
//	private FlexiBook flexibook = FlexiBookApplication.getflexibook();
//	@When("{string} attempts to cancel their {string} appointment on {string} at {string}")
//	public void attempts_to_cancel_their_appointment_on_at(String customer, String name, String serviceDate, String startTime) {
//	    try {
//			FlexibookController.CancelAppointment(customer, null, name, serviceDate, startTime);
//		} catch (InvalidInputException e) {
//			FlexiBookApplication.setmessage(e.getMessage());
//		}
//	}
//	
//	@When("{string} attempts to cancel {string}'s {string} appointment on {string} at {string}")
//	public void attempts_to_cancel_s_appointment_on_at(String customer, String customer2, String name, String serviceDate, String startTime) {
//		try {
//			FlexibookController.CancelAppointment(customer, customer2, name, serviceDate, startTime);
//		} catch (InvalidInputException e) {
//			FlexiBookApplication.setmessage(e.getMessage());
//
//		}
//	}
//
//	@Then("{string}'s {string} appointment on {string} at {string} shall be removed from the system")
//	public void s_appointment_on_at_shall_be_removed_from_the_system(String string, String string2, String string3, String string4) {
//	    
//	}
//	
//	@Then("there shall be {int} less appointment in the system")
//	public void there_shall_be_less_appointment_in_the_system(Integer int1) {
//		
//		
//	}
//
//	
//}
