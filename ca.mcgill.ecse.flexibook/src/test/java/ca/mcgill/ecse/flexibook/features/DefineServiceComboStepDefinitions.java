package ca.mcgill.ecse.flexibook.features;

import io.cucumber.java.After;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertThrows;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Executable;
import java.util.List;
import java.util.Map;
import ca.mcgill.ecse.flexibook.Controller.FlexibookController;
import ca.mcgill.ecse.flexibook.Controller.InvalidInputException;
import ca.mcgill.ecse.flexibook.application.FlexiBookApplication;
import ca.mcgill.ecse.flexibook.model.BookableService;
import ca.mcgill.ecse.flexibook.model.Business;
import ca.mcgill.ecse.flexibook.model.ComboItem;
import ca.mcgill.ecse.flexibook.model.Customer;
import ca.mcgill.ecse.flexibook.model.FlexiBook;
import ca.mcgill.ecse.flexibook.model.Owner;
import ca.mcgill.ecse.flexibook.model.Service;
import ca.mcgill.ecse.flexibook.model.ServiceCombo;

public class DefineServiceComboStepDefinitions {
  private  FlexiBook flexibook;
  private String error;
  @Given("a Flexibook system exists")
  public void a_flexibook_system_exists() {
  flexibook=new FlexiBook();
    FlexiBookApplication.setflexibook(flexibook);
  }

  @Given("an owner account exists in the system")
  public void an_owner_account_exists_in_the_system() {
if(!flexibook.hasOwner()) {
    Owner owner = new Owner("a", "123", flexibook); 
}
  }
 
  @Given("Customer with username {string} is logged in")
  public void customer_with_username_is_logged_in(String string) {
  if(flexibook.getCustomers().size()!=0) {
  Customer thisc=(Customer) flexibook.getCustomer(0).getWithUsername(string);
  FlexiBookApplication.setCurrentuser(thisc);
  }
  }
  @Given("a business exists in the system")
  public void a_business_exists_in_the_system() {
    if(!flexibook.hasBusiness()) {
    new Business("b", "ab", "1111111111", "123@mail.com", flexibook);
  }
    }
  @Given("the following services exist in the system:")
  public void the_following_services_exist_in_the_system(io.cucumber.datatable.DataTable dataTable) {

    List<Map<String, String>> valueMaps = dataTable.asMaps();
    for (Map<String, String> map : valueMaps) {
     String name = map.get("name");
     Integer duration = Integer.decode(map.get("duration"));
     Integer downtimeStart=Integer.decode(map.get("downtimeStart"));
     Integer downtimeDuration=Integer.decode(map.get("downtimeDuration")); 
     Service service = null;
     int count =0;
     FlexiBook thisf=flexibook;
     service=new Service(name, FlexiBookApplication.getflexibook(), duration, downtimeDuration, downtimeStart);
     
     }
  }
  @Given("the following service combos exist in the system:")
  public void the_following_service_combos_exist_in_the_system(io.cucumber.datatable.DataTable dataTable) throws InvalidInputException {
    String owner=flexibook.getOwner().getUsername();
    List<Map<String, String>> valueMaps = dataTable.asMaps();
    for (Map<String, String> map : valueMaps) {
     String name = map.get("name");
     String mainservice = "Item"+map.get("mainService");
     String services = map.get("services");
     String mandatory = map.get("mandatory"); 
     FlexibookController.makecombo(owner, name, mainservice, services, mandatory);
    }
  }
  
  @Given("the Owner with username {string} is logged in")
  public void the_owner_with_username_is_logged_in(String string) {
     flexibook.getOwner().setUsername(string);
  }
  @When("{string} initiates the definition of a service combo {string} with main service {string}, services {string} and mandatory setting {string}")
  public void initiates_the_definition_of_a_service_combo_with_main_service_services_and_mandatory_setting(String string, String string2, String string3, String string4, String string5) throws InvalidInputException {
    try {  
    FlexibookController.makecombo(string, string2, string3, string4, string5);
    }catch (InvalidInputException e) {
      error=e.getMessage();
      FlexiBookApplication.setmessage(error);
    }
    }
  @Then("the service combo {string} shall exist in the system")
  public void the_service_combo_shall_exist_in_the_system(String string) {
String comboname = null;
for (int i =0;i<flexibook.getBookableServices().size();i++) {
  if(flexibook.getBookableService(i).getName().equals(string)) {
    comboname=flexibook.getBookableService(i).getName();
  }}
    assertEquals(string,comboname);
  }
  @Then("the service combo {string} shall contain the services {string} with mandatory setting {string}")
  public void the_service_combo_shall_contain_the_services_with_mandatory_setting(String string, String string2, String string3) {
    String comboname=null;
    for (int i=0;i<flexibook.getBookableServices().size();i++) {
      if(flexibook.getBookableService(i).getName().equals(string)) {
        comboname=flexibook.getBookableService(i).getName();
        ServiceCombo thiscombo=(ServiceCombo) flexibook.getBookableService(i);
       List<ComboItem> services =thiscombo.getServices();
           String[] parts = string2.split(",");
         String[] setting = string3.split(",");
         for (int k=0;k<services.size();k++) {
         assertEquals(services.get(k).getService().getName(),parts[k]);
         assertEquals(Boolean.toString(services.get(k).getMandatory()),setting[k]);
       }
      }}
    assertEquals(comboname,string);
  }
  @Then("the main service of the service combo {string} shall be {string}")
  public void the_main_service_of_the_service_combo_shall_be(String string, String string2) {
    for (int i =0;i<flexibook.getBookableServices().size();i++) {
      if(flexibook.getBookableService(i).getName().equals(string)) {
       ServiceCombo thiscombo=(ServiceCombo) flexibook.getBookableService(i);
      String name=  thiscombo.getMainService().getService().getName();
      assertEquals(name,string2);
      }}
  }
  @Then("the service {string} in service combo {string} shall be mandatory")
  public void the_service_in_service_combo_shall_be_mandatory(String string, String string2) {
    String comboname=null;
    for (int i =0;i<flexibook.getBookableServices().size();i++) {
      if(flexibook.getBookableService(i).getName().equals(string2)) {
        ServiceCombo thiscombo=(ServiceCombo) flexibook.getBookableService(i);
        List<ComboItem> services =thiscombo.getServices();
        for (int k=0;k<services.size();k++) {
          if(services.get(k).getService().getName().equals(string)) {
          assertEquals(Boolean.toString(services.get(k).getMandatory()),"true" );
          }
        }
      }}
  }
  @Then("the number of service combos in the system shall be {string}")
  public void the_number_of_service_combos_in_the_system_shall_be(String string) {
int count = 0;
 for (int i =0;i<flexibook.getBookableServices().size();i++) {
     if(flexibook.getBookableService(i).getName().contains("-")) {
      
      String name=flexibook.getBookableService(i).getName();
       count =count+1;
       }    
    }
 assertEquals(string,Integer.toString(count));
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
  @Then("an error message with content {string} shall be raised")
  public void an_error_message_with_content_shall_be_raised(String string) {
 String e = FlexiBookApplication.returnmessage();
   assertEquals(string,e);
   FlexiBookApplication.setmessage(null);
  }
  @Then("the service combo {string} shall not exist in the system")
  public void the_service_combo_shall_not_exist_in_the_system(String string) throws InvalidInputException {
    if(flexibook.getBookableServices()!=null) {
      BookableService thisb=flexibook.getBookableService(1).getWithName(string);
      assertEquals(null,thisb);
    }
    
  }

  @After
  public void tearDown() {
     flexibook=FlexiBookApplication.getflexibook();
      flexibook.delete();
  }
}
