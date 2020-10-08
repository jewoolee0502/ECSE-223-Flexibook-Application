package ca.mcgill.ecse.flexibook.features;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class AddServiceStepDefiniton {
  @When("{string} initiates the addition of the service {string} with duration {string}, start of down time {string} and down time duration {string}")
  public void initiates_the_addition_of_the_service_with_duration_start_of_down_time_and_down_time_duration(String string, String string2, String string3, String string4, String string5) {
      // Write code here that turns the phrase above into concrete actions
      throw new io.cucumber.java.PendingException();
  }


  

  @Then("the service {string} shall exist in the system")
  public void the_service_shall_exist_in_the_system(String string) {
      // Write code here that turns the phrase above into concrete actions
      throw new io.cucumber.java.PendingException();
  }
  @Then("the service {string} shall have duration {string}, start of down time {string} and down time duration {string}")
  public void the_service_shall_have_duration_start_of_down_time_and_down_time_duration(String string, String string2, String string3, String string4) {
      // Write code here that turns the phrase above into concrete actions
      throw new io.cucumber.java.PendingException();
  }
  @Then("the number of services in the system shall be {string}")
  public void the_number_of_services_in_the_system_shall_be(String string) {
      // Write code here that turns the phrase above into concrete actions
      throw new io.cucumber.java.PendingException();
  }

}
