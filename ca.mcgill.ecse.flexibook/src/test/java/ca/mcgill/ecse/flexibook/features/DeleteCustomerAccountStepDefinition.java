package ca.mcgill.ecse.flexibook.features;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class DeleteCustomerAccountStepDefinition {



	@Given("the account with username {string} has pending appointments")
	public void the_account_with_username_has_pending_appointments(String string) {



		// Write code here that turns the phrase above into concrete actions
		//throw new io.cucumber.java.PendingException();
	}

	@When("the user tries to delete account with the username {string}")
	public void the_user_tries_to_delete_account_with_the_username(String string) {



		// Write code here that turns the phrase above into concrete actions
		//throw new io.cucumber.java.PendingException();
	}

	@Then("the account with the username {string} does not exist")
	public void the_account_with_the_username_does_not_exist(String string) {



		// Write code here that turns the phrase above into concrete actions
		//throw new io.cucumber.java.PendingException();
	}

	@Then("all associated appointments of the account with the username {string} shall not exist")
	public void all_associated_appointments_of_the_account_with_the_username_shall_not_exist(String string) {



		// Write code here that turns the phrase above into concrete actions
		//throw new io.cucumber.java.PendingException();
	}








}
