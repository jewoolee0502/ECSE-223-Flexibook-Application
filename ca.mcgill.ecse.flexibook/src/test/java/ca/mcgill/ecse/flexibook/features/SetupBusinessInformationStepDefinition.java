package ca.mcgill.ecse.flexibook.features;

import ca.mcgill.ecse.flexibook.Controller.InvalidInputException;
import ca.mcgill.ecse.flexibook.application.FlexiBookApplication;
import ca.mcgill.ecse.flexibook.model.FlexiBook;
import ca.mcgill.ecse.flexibook.model.Owner;
import ca.mcgill.ecse.flexibook.model.User;
import io.cucumber.java.en.Given;

public class SetupBusinessInformationStepDefinition {
	  private  FlexiBook flexibook;
	  private  InvalidInputException thise=null;

//	@Given("an owner account exists in the system with username {string} and password {string}")
//	public void an_owner_account_exists_in_the_system_with_username_and_password(String string, String string2) throws Exception{
//		flexibook=FlexiBookApplication.getflexibook();
//	if(!flexibook.hasOwner()) {
//		
//		   Owner owner =new Owner(string, string2, flexibook);
//		   flexibook.setOwner(owner);
//	}
//		  
//	   
//	}
	@Given("no business exists")
	public void no_business_exists() {
	  if(flexibook.getBusiness()!=null) {
		  flexibook.setBusiness(null);
	  }
	}
	/*@Given("the system's time and date is {string}")
	public void the_system_s_time_and_date_is(String string) {
		String[] arrOfStr = string.split("+", 2);
		
}*/

		@Given("the user is logged in to an account with username {string}")
		public void the_user_is_logged_in_to_an_account_with_username(String string) {
			User aUser=FlexiBookApplication.getCurrentuser();
			if(!aUser.getUsername().equals(string)) {
				aUser.setUsername(string);
			}
			FlexiBookApplication.
		}


		Some other steps were also undefined:

		@When("the user tries to set up the business information with new {string} and {string} and {string} and {string}")
		public void the_user_tries_to_set_up_the_business_information_with_new_and_and_and(String string, String string2, String string3, String string4) {
		    // Write code here that turns the phrase above into concrete actions
		    throw new io.cucumber.java.PendingException();
		}
		@Then("a new business with new {string} and {string} and {string} and {string} shall {string} created")
		public void a_new_business_with_new_and_and_and_shall_created(String string, String string2, String string3, String string4, String string5) {
		    // Write code here that turns the phrase above into concrete actions
		    throw new io.cucumber.java.PendingException();
		}
		@Then("an error message {string} shall {string} raised")
		public void an_error_message_shall_raised(String string, String string2) {
		    // Write code here that turns the phrase above into concrete actions
		    throw new io.cucumber.java.PendingException();
		}




}
