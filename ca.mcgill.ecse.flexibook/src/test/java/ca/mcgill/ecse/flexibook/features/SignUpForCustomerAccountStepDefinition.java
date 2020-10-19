package ca.mcgill.ecse.flexibook.features;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

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

	private FlexiBook flexibook = FlexiBookApplication.getflexibook();

	private String error;
	private int errorCntr = 0;
	private int userCount = 0;

	private int userCntrBeforeCreation;

	@Given("there is no existing username {string}")
	public void there_is_no_existing_username(String username) {

		if(User.getWithUsername(username) != null) {
			User.getWithUsername(username).delete();
		}
	}

	@Given("there is an existing username {string}")
	public void there_is_an_existing_username(String username) throws InvalidInputException {

		flexibook = FlexiBookApplication.getflexibook();

		if(User.getWithUsername(username) == null) { 
			flexibook.addCustomer(username, "password");
		}
	}

	@Given("the user is logged in to an account with username {string}")
	public void the_user_is_logged_in_to_an_account_with_username(String username) {
		User user = null;
		if(flexibook.getCustomers().size()!= 0) {
			for(Customer c:flexibook.getCustomers()) {
				if (c.getUsername().equals(username)) user = c;
			}
		}
		if(flexibook.getOwner().getUsername().equals(username)) user=flexibook.getOwner();
		FlexiBookApplication.setCurrentuser(user);
	}

	@When("the user provides a new username {string} and a password {string}")
	public void the_user_provides_a_new_username_and_a_password(String username, String password) throws InvalidInputException {
		userCntrBeforeCreation = flexibook.getCustomers().size();
		try {
			FlexibookController.SignUpForCustomerAccount(username, password);
		} catch (InvalidInputException e) {
			error += e.getMessage();
			throw new InvalidInputException("Error.");
		}
	}

	@Then("a new customer account shall be created")
	public void a_new_customer_account_shall_be_created() {
		assertEquals(userCount + userCntrBeforeCreation, flexibook.getCustomers().size());

	}

	@Then("the account shall have username {string} and password {string}")
	public void the_account_shall_have_username_and_password(String username, String password) {
		assertEquals(username, flexibook.getCustomers().get(0).getUsername());
		assertEquals(password, flexibook.getCustomers().get(0).getPassword());

	}

	@Then("no new account shall be created")
	public void no_new_account_shall_be_created() throws Throwable {
		assertEquals(0, flexibook.getCustomers().size());

	}

	@Then("an error message {string} shall be raised")
	public void an_error_message_shall_be_raised(String errorMsg) {
		assertTrue(error.contains(errorMsg));

	}


}





//@Given("there is no existing username {string}")
//public void there_is_no_existing_username(String username) {
//
//	flexibook = FlexiBookApplication.getflexibook();
//
//	String name = username;
//	if(flexibook.getCustomers().size() != 0) {
//		if(flexibook.getCustomer(0).getWithUsername(username) != null) {
//			flexibook.getCustomer(0).getWithUsername(username).delete();
//		}
//	}
//}
//
//	@Given("there is an existing username {string}")
//	public void there_is_an_existing_username(String username) throws InvalidInputException {
//
//		flexibook = FlexiBookApplication.getflexibook();
//		if(username == null || username == "") {
//			throw new InvalidInputException("The username cannot be empty.");
//		}
//		else if(User.getWithUsername(username) == flexibook.getCustomers()) {
//			if(flexibook.getCustomer(0).getWithUsername(username) == null) {
//				flexibook.getCustomer(0).getWithUsername(username).delete();
//			}
//			Customer currentCustomer = new Customer(username, username, flexibook);
//		}
//	}
//
//	@Given("the user is logged in to an account with username {string}")
//	public void the_user_is_logged_in_to_an_account_with_username(String username) {
//		if(flexibook.getCustomers().size() != 0) {
//			Customer currentUser = (Customer) flexibook.getCustomer(0).getWithUsername(username);
//			FlexiBookApplication.setCurrentuser(currentUser);
//		}
//		FlexiBookApplication.setCurrentuser(flexibook.getCustomer(0).getWithUsername(username));
//	}
//
//	@When("the user provides a new username {string} and a password {string}")
//	public void the_user_provides_a_new_username_and_a_password(String username, String password) throws InvalidInputException {
//		try {
//			FlexibookController.SignUpForCustomerAccount(username, password);
//		} catch (InvalidInputException e) {
//			error += e.getMessage();
//			throw new InvalidInputException("Error.");
//		}
//	}
//
//	@Then("a new customer account shall be created")
//	public void a_new_customer_account_shall_be_created() {
//		assertEquals(userCount + userCntrBeforeCreation, flexibook.getCustomers().size()); //check this code again with the size of the existing customer account
//
//	}
//
//	@Then("the account shall have username {string} and password {string}")
//	public void the_account_shall_have_username_and_password(String username, String password) {
//		assertEquals(username, flexibook.getCustomer(0).getWithUsername(username).getUsername());
//		assertEquals(password, flexibook.getCustomer(0).getWithUsername(username).getPassword());
//
//	}
//
//	@Then("no new account shall be created")
//	public void no_new_account_shall_be_created() throws Throwable {
//		assertEquals(0, flexibook.getCustomers().size());
//
//	}
//
//	@Then("an error message {string} shall be raised")
//	public void an_error_message_shall_be_raised(String errorMsg) {
//		assertTrue(error.contains(errorMsg));
//
//	}
//
//
//}
