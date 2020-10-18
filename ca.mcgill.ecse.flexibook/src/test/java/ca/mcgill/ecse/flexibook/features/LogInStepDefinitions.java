package ca.mcgill.ecse.flexibook.features;

import static org.junit.Assert.assertEquals;

import java.util.List;
import java.util.Map;

import org.junit.After;

import ca.mcgill.ecse.flexibook.Controller.FlexibookController;
import ca.mcgill.ecse.flexibook.Controller.InvalidInputException;
import ca.mcgill.ecse.flexibook.application.FlexiBookApplication;
import ca.mcgill.ecse.flexibook.model.Customer;
import ca.mcgill.ecse.flexibook.model.FlexiBook;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class LogInStepDefinitions {
	private static FlexiBook flexibook; 
	@Given("the following customers exist in the system:")
	public void the_following_customers_exist_in_the_system(io.cucumber.datatable.DataTable dataTable) {
		flexibook=FlexiBookApplication.getflexibook();  
	  List<Map<String, String>> valueMaps = dataTable.asMaps();
		    for (Map<String, String> map : valueMaps) {
		     String name = map.get("username");
		     String passcode = map.get("password");
		     Customer customer = new Customer(name,passcode, flexibook);

		    }
		    }




	@When("the user tries to log in with username {string} and password {string}")
	public void the_user_tries_to_log_in_with_username_and_password(String string, String string2) throws InvalidInputException {
	   FlexibookController.AttemptLogIn(string, string2);
	}
	@Then("the user should be successfully logged in")
	public void the_user_should_be_successfully_logged_in() {
	    assertEquals(FlexiBookApplication.getCurrentuser(),flexibook.getCustomer(0));
	}
	@After
	public void tearDown() {
	   flexibook=FlexiBookApplication.getflexibook();
	    flexibook.delete();
	}

	}

