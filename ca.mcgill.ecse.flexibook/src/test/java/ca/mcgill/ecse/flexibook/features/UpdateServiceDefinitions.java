package ca.mcgill.ecse.flexibook.features;

import io.cucumber.java.After;
import io.cucumber.java.en.Given;
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
import ca.mcgill.ecse.flexibook.model.ServiceCombo;

public class UpdateServiceDefinitions {
  private FlexiBook flexibook=FlexiBookApplication.getflexibook(); 
  private String error;
	  
 @Given("the following service combos exist in the system:`")
  public void the_following_service_combos_exist_in_the_system(io.cucumber.datatable.DataTable dataTable) throws InvalidInputException {
   String owner=flexibook.getOwner().getUsername();
   List<Map<String, String>> valueMaps = dataTable.asMaps();
   for (Map<String, String> map : valueMaps) {
    String name = map.get("name");
    String mainservice = map.get("mainService");
    String services = map.get("services");
    String mandatory = map.get("mandatory"); 
    FlexibookController.makecombo(owner, name, mainservice, services, mandatory);
   }
  }

  @When("{string} initiates the update of the service {string} to name {string}, duration {string}, start of down time {string} and down time duration {string}")
	  public void initiates_the_update_of_the_service_to_name_duration_start_of_down_time_and_down_time_duration(String string, String string2, String string3, String string4, String string5, String string6) {
		  try{
		        FlexibookController.updateservice(string, string2, string3, string4, string5, string6);
		      }catch (InvalidInputException e) {
		        error=e.getMessage();
		        FlexiBookApplication.setmessage(error);
		      }
	  }



	  @Then("the service {string} shall be updated to name {string}, duration {string}, start of down time {string} and down time duration {string}")
	  public void the_service_shall_be_updated_to_name_duration_start_of_down_time_and_down_time_duration(String string, String string2, String string3, String string4, String string5) {
	    Service thiss=(Service) flexibook.getBookableService(0).getWithName(string2);
	    assertEquals(string3,Integer.toString(thiss.getDuration()) );
	    assertEquals(string4,Integer.toString(thiss.getDowntimeStart()));
	    assertEquals(string5,Integer.toString(thiss.getDowntimeDuration()));
	  }
	  
	  @Then("the service {string} shall still preserve the following properties:")
	  public void the_service_shall_still_preserve_the_following_properties(String string, io.cucumber.datatable.DataTable dataTable) {
		  List<Map<String, String>> valueMaps = dataTable.asMaps();
		    for (Map<String, String> map : valueMaps) {
		      String name=map.get("name");
		      String services = map.get("services");
		      assertEquals(name,flexibook.getBookableService(0).getWithName(string).getName());
		      
		      
		    }
	  }
	  @Then("the service combos {string} shall not exist in the system")
	  public void the_service_combos_shall_not_exist_in_the_system(String string) {
	      // Write code here that turns the phrase above into concrete actions
	      throw new io.cucumber.java.PendingException();
	  }
	  @Then("the service combos {string} shall not contain service {string}")
	  public void the_service_combos_shall_not_contain_service(String string, String string2) {
	      // Write code here that turns the phrase above into concrete actions
	      throw new io.cucumber.java.PendingException();
	  }
	  @After
	  public void tearDown() {
	     flexibook=FlexiBookApplication.getflexibook();
	      flexibook.delete();
	  }
}
