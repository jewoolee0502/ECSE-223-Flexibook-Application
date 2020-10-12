package ca.mcgill.ecse.flexibook.features;

import static org.junit.Assert.assertEquals;
import java.util.List;
import java.util.Map;

import ca.mcgill.ecse.flexibook.Controller.FlexibookController;
import ca.mcgill.ecse.flexibook.application.FlexiBookApplication;
import ca.mcgill.ecse.flexibook.model.Customer;
import ca.mcgill.ecse.flexibook.model.FlexiBook;
import ca.mcgill.ecse.flexibook.model.Service;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class LogInStepDefinitions {
	private static FlexiBook flexibook = new FlexiBook();
	@Given("the following customers exist in the system:")
	public void the_following_customers_exist_in_the_system(io.cucumber.datatable.DataTable dataTable) {
		  List<Map<String, String>> valueMaps = dataTable.asMaps();
		    for (Map<String, String> map : valueMaps) {
		     String name = map.get("username");
		     String passcode = map.get("password");
		     Customer customer = null;
		     int count =0;
		     if (flexibook.numberOfCustomers()!=0) {
		      for (Customer c: flexibook.getCustomers()) {
		        if(!(c.getUsername().equals(name))) {
		         count=count+1;
		       }
		      }
		      if(count==flexibook.getBookableServices().size()) {
		        customer=new Customer(name,passcode, flexibook);
		      }
		     }
		     else{
		      customer=new Customer(name,passcode, flexibook);
		    }
		     if(customer!=null) {
		     flexibook.addCustomer(customer);}
		    }
	}



	@When("the user tries to log in with username {string} and password {string}")
	public void the_user_tries_to_log_in_with_username_and_password(String string, String string2) {
	   FlexibookController.AttemptLogIn(string, string2);
	}
	@Then("the user should be successfully logged in")
	public void the_user_should_be_successfully_logged_in() {
	    FlexiBookApplication.getflexibook();
	}
}
