package ca.mcgill.ecse.flexibook.features;


import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.List;
import java.util.Map;
import ca.mcgill.ecse.flexibook.Controller.FlexibookController;
import ca.mcgill.ecse.flexibook.Controller.InvalidInputException;
import ca.mcgill.ecse.flexibook.application.FlexiBookApplication;
import ca.mcgill.ecse.flexibook.model.FlexiBook;
import ca.mcgill.ecse.flexibook.model.ServiceCombo;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class UpdateServiceComboStepDefinitions {
  private  FlexiBook flexibook;
  private  InvalidInputException thise=null;
  @When("{string} initiates the update of service combo {string} to name {string}, main service {string} and services {string} and mandatory setting {string}")
  public void initiates_the_update_of_service_combo_to_name_main_service_and_services_and_mandatory_setting(String string, String string2, String string3, String string4, String string5, String string6) throws InvalidInputException {
      try{
        FlexibookController.updatecombo(string, string2, string3, string4, string5, string6);
      }catch (InvalidInputException e) {
        thise=e;
      }
  }



  @Then("the service combo {string} shall preserve the following properties:")
  public void the_service_combo_shall_preserve_the_following_properties(String string, io.cucumber.datatable.DataTable dataTable) {
    List<Map<String, String>> valueMaps = dataTable.asMaps();
    for (Map<String, String> map : valueMaps) {
      String name=map.get("name");
      String mainservice = map.get("mainService");
      String services = map.get("services");
      String mandatory = map.get("mandatory"); 
      assertEquals(name,flexibook.getBookableService(0).getWithName(string).getName());
      ServiceCombo thiscombo=(ServiceCombo)flexibook.getBookableService(0).getWithName(string);
      assertEquals(thiscombo.getMainService().getService().getName(),mainservice);
      
      
    }
  } 
  @Then("the service combo {string} shall be updated to name {string}")
  public void the_service_combo_shall_be_updated_to_name(String string, String string2) {

   flexibook=FlexiBookApplication.getflexibook();
   
    if(flexibook.getBookableServices().size()!=0) {

 assertEquals(string2,flexibook.getBookableService(0).getWithName(string2).getName());
}
  }
}
