package ca.mcgill.ecse.flexibook.features;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class DeleteServiceComboStepDefiniton {
 /* @Given("the system's time and date is {string}")
  public void the_system_s_time_and_date_is(String string) {
      // Write code here that turns the phrase above into concrete actions
      throw new io.cucumber.java.PendingException();
  }
*/

 
  @Given("the following appointments exist in the system:")
  public void the_following_appointments_exist_in_the_system(io.cucumber.datatable.DataTable dataTable) {
      // Write code here that turns the phrase above into concrete actions
      // For automatic transformation, change DataTable to one of
      // E, List<E>, List<List<E>>, List<Map<K,V>>, Map<K,V> or
      // Map<K, List<V>>. E,K,V must be a String, Integer, Float,
      // Double, Byte, Short, Long, BigInteger or BigDecimal.
      //
      // For other transformations you can register a DataTableType.
      throw new io.cucumber.java.PendingException();
  }
  @When("{string} initiates the deletion of service combo {string}")
  public void initiates_the_deletion_of_service_combo(String string, String string2) {
      // Write code here that turns the phrase above into concrete actions
      throw new io.cucumber.java.PendingException();
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
}
