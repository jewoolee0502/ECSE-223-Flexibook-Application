package ca.mcgill.ecse.flexibook.features;

import io.cucumber.java.After;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import static org.junit.Assert.assertEquals;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import ca.mcgill.ecse.flexibook.Controller.FlexibookController;
import ca.mcgill.ecse.flexibook.Controller.InvalidInputException;
import ca.mcgill.ecse.flexibook.application.FlexiBookApplication;
import ca.mcgill.ecse.flexibook.model.Business;
import ca.mcgill.ecse.flexibook.model.ComboItem;
import ca.mcgill.ecse.flexibook.model.FlexiBook;
import ca.mcgill.ecse.flexibook.model.Owner;
import ca.mcgill.ecse.flexibook.model.Service;
import ca.mcgill.ecse.flexibook.model.ServiceCombo;

public class AddServiceStepDefiniton {
	private  FlexiBook flexibook=FlexiBookApplication.getflexibook();
	  private  InvalidInputException thise=null;
  @When("{string} initiates the addition of the service {string} with duration {string}, start of down time {string} and down time duration {string}")
  public void initiates_the_addition_of_the_service_with_duration_start_of_down_time_and_down_time_duration(String string, String string2, String string3, String string4, String string5) {
	  try{
	      FlexibookController.addService(string, string2, string3, string4, string5);
	    }catch (InvalidInputException e) {
	      FlexiBookApplication.setmessage(e.getMessage());
	    }
  }
  @Then("the service {string} shall exist in the system")
  public void the_service_shall_exist_in_the_system(String string) {
	 	  String servicename = null;
		  for (int i =0;i<flexibook.getBookableServices().size();i++) {
		    if(flexibook.getBookableService(i).getName().equals(string)) {
		      servicename=flexibook.getBookableService(i).getName();
		    }}
		      assertEquals(string,servicename);
      }
  @Then("the service {string} shall have duration {string}, start of down time {string} and down time duration {string}")
  public void the_service_shall_have_duration_start_of_down_time_and_down_time_duration(String string, String string2, String string3, String string4) {
	  String servicename=null;
	    for (int i=0;i<flexibook.getBookableServices().size();i++) {
	      if(flexibook.getBookableService(i).getName().equals(string)) {
	        servicename=flexibook.getBookableService(i).getName();
	        ServiceCombo thiscombo=(ServiceCombo) flexibook.getBookableService(i);
	       
	         
	      }}
	    assertEquals(servicename,string);
	  }
	   
 
  @Then("the number of services in the system shall be {string}")
  public void the_number_of_services_in_the_system_shall_be(String string) {
	  int count = 0;
	  for (int i =0;i<flexibook.getBookableServices().size();i++) {
	      if(flexibook.getBookableService(i).getName().contains("-")) {
	       
	       String name=flexibook.getBookableService(i).getName();
	        count =count+1;
	        }    
	     }
	  assertEquals(string,Integer.toString(count));
  }
  @After
  public void tearDown() {
     flexibook=FlexiBookApplication.getflexibook();
      flexibook.delete();
  }
 

}
