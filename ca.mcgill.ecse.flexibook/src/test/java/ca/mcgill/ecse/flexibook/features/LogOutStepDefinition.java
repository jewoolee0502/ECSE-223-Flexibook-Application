package ca.mcgill.ecse.flexibook.features;

import static org.junit.Assert.assertNull;

import ca.mcgill.ecse.flexibook.Controller.FlexibookController;
import ca.mcgill.ecse.flexibook.Controller.InvalidInputException;
import ca.mcgill.ecse.flexibook.application.FlexiBookApplication;
import ca.mcgill.ecse.flexibook.model.Customer;
import ca.mcgill.ecse.flexibook.model.FlexiBook;
import ca.mcgill.ecse.flexibook.model.Owner;
import ca.mcgill.ecse.flexibook.model.User;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class LogOutStepDefinition {
	private FlexiBook flexibook=FlexiBookApplication.getflexibook();
	private String error;
	@Given("the user is logged out")
	public void the_user_is_logged_out() {
	    FlexiBookApplication.setCurrentuser(null);
	}


	@When("the user tries to log out")
	public void the_user_tries_to_log_out() throws InvalidInputException {
		try { FlexibookController.LogOut();
	}
		catch(InvalidInputException e) {			
		error = e.getMessage();
		FlexiBookApplication.setmessage(error);}
		}
	

	@Then("the user shall be logged out")
	public void the_user_shall_be_logged_out() {
		assertNull(FlexiBookApplication.getCurrentuser());
	}
}
