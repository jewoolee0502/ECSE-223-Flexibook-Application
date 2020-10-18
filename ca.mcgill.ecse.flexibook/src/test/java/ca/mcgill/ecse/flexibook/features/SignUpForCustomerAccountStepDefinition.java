package ca.mcgill.ecse.flexibook.features;

import java.util.List;
import java.util.Map;

import ca.mcgill.ecse.flexibook.Controller.FlexibookController;
import ca.mcgill.ecse.flexibook.Controller.InvalidInputException;
import ca.mcgill.ecse.flexibook.application.FlexiBookApplication;
import ca.mcgill.ecse.flexibook.model.Customer;
import ca.mcgill.ecse.flexibook.model.FlexiBook;
import ca.mcgill.ecse.flexibook.model.User;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class SignUpForCustomerAccountStepDefinition {
	
	private FlexiBook flexibook;
	private String error;
	private int errorCntr =0;
	
	@Given("there is no existing username {string}")
	public void there_is_no_existing_username(String username, io.cucumber.datatable.DataTable dataTable) {
		FlexiBook fb = FlexiBookApplication.getflexibook();
		
		List<Map<String, String>> valueMaps = dataTable.asMaps();
	    for(Map<String,String> map : valueMaps) {
	    	String name = map.get("username");
	    	String passcode = map.get("password");
	    	Customer customer = null;
	    	if(User.getWithUsername(username) != fb.getCustomers()) {
	    		Customer customerAccount = new Customer("name", passcode, fb);
	    	}
	    }
		// Write code here that turns the phrase above into concrete actions
	    //throw new io.cucumber.java.PendingException();
	}

	@Given("there is an existing username {string}")
	public void there_is_an_existing_username(String username, String password) throws InvalidInputException {
		FlexiBook fb = FlexiBookApplication.getflexibook();
		if(username==null || username=="") {
	    	throw new InvalidInputException("The username cannot be empty.");
	    }
		else if(User.getWithUsername(username) == fb.getCustomers()) {
			fb.getCustomers().contains(username);
			throw new InvalidInputException("The username already exists.");
		}
		Customer thise=new Customer(username, username, fb);
		
		// Write code here that turns the phrase above into concrete actions
		//throw new io.cucumber.java.PendingException();
	}

	@Given("the user is logged in to an account with username {string}")
	public void the_user_is_logged_in_to_an_account_with_username(String string) {
	    if(flexibook.getCustomers().size() != 0) {
	    	Customer currentUser = (Customer) flexibook.getCustomer(0).getWithUsername(string);
	    	FlexiBookApplication.setCurrentuser(currentUser);
	    }
		
		// Write code here that turns the phrase above into concrete actions
	    //throw new io.cucumber.java.PendingException();
	}

	@When("the user provides a new username {string} and a password {string}")
	public void the_user_provides_a_new_username_and_a_password(String username, String password) throws InvalidInputException {
	    try {
	    	FlexibookController.CreateUser(username, password);
	    } catch (InvalidInputException e) {
	    	error += e.getMessage();
	    	throw new InvalidInputException("Error.");
	    }
		
		// Write code here that turns the phrase above into concrete actions
	    //throw new io.cucumber.java.PendingException();
	}
	
	@Then("a new customer account shall be created")
	public void a_new_customer_account_shall_be_created() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}
	
	@Then("the account shall have username {string} and password {string}")
	public void the_account_shall_have_username_and_password(String string, String string2) {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}


	@Then("no new account shall be created")
	public void no_new_account_shall_be_created() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}
	@Then("an error message {string} shall be raised")
	public void an_error_message_shall_be_raised(String string) {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}

	
}

