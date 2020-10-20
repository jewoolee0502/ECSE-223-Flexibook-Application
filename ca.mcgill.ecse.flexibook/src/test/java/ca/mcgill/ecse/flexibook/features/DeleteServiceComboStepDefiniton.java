package ca.mcgill.ecse.flexibook.features;

import ca.mcgill.ecse.flexibook.Controller.FlexibookController;
import ca.mcgill.ecse.flexibook.Controller.InvalidInputException;
import ca.mcgill.ecse.flexibook.application.FlexiBookApplication;
import ca.mcgill.ecse.flexibook.model.FlexiBook;
import ca.mcgill.ecse.flexibook.util.SystemTime;
import io.cucumber.java.After;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class DeleteServiceComboStepDefiniton {
private FlexiBook flexibook=new FlexiBook();

 
 
  @When("{string} initiates the deletion of service combo {string}")
  public void initiates_the_deletion_of_service_combo(String string, String string2) throws InvalidInputException {
    FlexibookController.deletecombo(string, string2);
    System.out.println(SystemTime.comparetime("2020-10-01+14:00", "2020-10-01+14:01"));
  }
  @Then("the number of appointments in the system with service {string} shall be {string}")
  public void the_number_of_appointments_in_the_system_with_service_shall_be(String string, String string2) {
      // Write code here that turns the phrase above into concrete actions
      throw new io.cucumber.java.PendingException();
  }
  @Then("the number of appointments in the system shall be {string}")
  public void the_number_of_appointments_in_the_system_shall_be(String string) {
      // Write code here that turns the phrase above into concrete actions
      throw new io.cucumber.java.PendingException();
      
  }
  @After
  public void tearDown() {
      flexibook=FlexiBookApplication.getflexibook();
      flexibook.delete();
  }
}
