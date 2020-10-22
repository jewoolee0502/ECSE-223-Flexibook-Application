package ca.mcgill.ecse.flexibook.features;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import ca.mcgill.ecse.flexibook.Controller.FlexibookController;
import ca.mcgill.ecse.flexibook.Controller.InvalidInputException;
import ca.mcgill.ecse.flexibook.application.FlexiBookApplication;
import ca.mcgill.ecse.flexibook.model.Appointment;
import ca.mcgill.ecse.flexibook.model.Customer;
import ca.mcgill.ecse.flexibook.model.FlexiBook;
import io.cucumber.java.After;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class DeleteCustomerAccountStepDefinition {

	private String error;
	private FlexiBook flexibook = FlexiBookApplication.getflexibook();
	private List<Appointment> oldAppointments;
	
	private int errorCount = 0;
 
	@Given("the account with username {string} has pending appointments")
	public void the_account_with_username_has_pending_appointments(String username) {

		/*if(getCustomer(username).getAppointments().size() == 0) {
			Appointment appointment = new Appointment(getCustomer(username), null, null, flexibook);
		}*/
	}

	@When("the user tries to delete account with the username {string}")
	public void the_user_tries_to_delete_account_with_the_username(String username) throws InvalidInputException {

		if(!(username.equals("owner"))) {
			oldAppointments = getCustomer(username).getAppointments();
		}
		
		try {
			String target = FlexiBookApplication.getCurrentuser().getUsername();
			FlexibookController.DeleteCustomerAccount(target, username);
		} catch (InvalidInputException e) {
			error = e.getMessage();
			FlexiBookApplication.setmessage(error);
		}
		errorCount ++;
	}

	@Then("the account with the username {string} does not exist")
	public void the_account_with_the_username_does_not_exist(String username) {

		if(username.equals("owner")) {
			assertNull(getCustomer(username));
		}
		else {
			assertFalse(flexibook.getCustomers().contains(getCustomer(username)));
		}
	}

	@Then("all associated appointments of the account with the username {string} shall not exist")
	public void all_associated_appointments_of_the_account_with_the_username_shall_not_exist(String username) {

		boolean exists = false;
		for(Appointment appointment : oldAppointments) {
			if(flexibook.getAppointments().contains(appointment)) {
				exists = true;
			}
		}
		assertFalse(exists);

	}


	@Then("the account with the username {string} exists")
	public void the_account_with_the_username_exists(String username) {
		
		boolean exists = false;
		if(getCustomer(username) != null) {
			exists = true;
		}
		assertTrue(exists);
	}
	
	
	private static Customer getCustomer(String username) {
		Customer foundCustomer = null;
		for(Customer customer : FlexiBookApplication.getflexibook().getCustomers()) {
			if(customer.getUsername().equals(username)) {
				return customer;
			}
		}
		return foundCustomer;
	}

}
