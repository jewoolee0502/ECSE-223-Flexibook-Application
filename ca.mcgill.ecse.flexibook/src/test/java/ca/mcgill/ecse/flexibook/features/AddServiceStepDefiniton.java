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
import ca.mcgill.ecse.flexibook.model.FlexiBook;
import ca.mcgill.ecse.flexibook.model.Owner;
import ca.mcgill.ecse.flexibook.model.Service;

public class AddServiceStepDefiniton {
	private  FlexiBook flexibook;
	  private  InvalidInputException thise=null;
  @When("{string} initiates the addition of the service {string} with duration {string}, start of down time {string} and down time duration {string}")
  public void initiates_the_addition_of_the_service_with_duration_start_of_down_time_and_down_time_duration(String string, String string2, String string3, String string4, String string5) {
      // Write code here that turns the phrase above into concrete actions
      throw new io.cucumber.java.PendingException();
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
  public void the_service_shall_have_duration_start_of_down_time_and_down_time_duration(String string, String string2, String string3, String string4,io.cucumber.datatable.DataTable dataTable) {
	  List<Map<String, String>> valueMaps = dataTable.asMaps();
	    for (Map<String, String> map : valueMaps) {
	     String name = map.get("name");
	     Integer duration = Integer.decode(map.get("duration"));
	     Integer downtimeStart=Integer.decode(map.get("downtimeStart"));
	     Integer downtimeDuration=Integer.decode(map.get("downtimeDuration")); 
	     Service service = null;
	     int count =0;
	     FlexiBook thisf=flexibook;
	     service=new Service(name, FlexiBookApplication.getflexibook(), duration, downtimeStart, downtimeDuration);
	     
	     }
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
