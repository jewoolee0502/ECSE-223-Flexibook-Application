package ca.mcgill.ecse.flexibook.features;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.List;
import ca.mcgill.ecse.flexibook.Controller.FlexibookController;
import ca.mcgill.ecse.flexibook.Controller.InvalidInputException;
import ca.mcgill.ecse.flexibook.application.FlexiBookApplication;
import ca.mcgill.ecse.flexibook.model.Appointment;
import ca.mcgill.ecse.flexibook.model.FlexiBook;
import ca.mcgill.ecse.flexibook.util.SystemTime;
import io.cucumber.java.After;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class DeleteServiceStepDefinition {
	private FlexiBook flexibook=FlexiBookApplication.getflexibook();
	
	@When("{string} initiates the deletion of service {string}")
	  public void initiates_the_deletion_of_service(String string, String string2) throws InvalidInputException {
	    try{
	      FlexibookController.deleteService(string, string2);
	    }catch (InvalidInputException e) {
	      FlexiBookApplication.setmessage(e.getMessage());
	    }
	  }
	  @Then("the number of appointments in the system with service {string} shall be {string}")
	  public void the_number_of_appointments_in_the_system_with_service_shall_be(String string, String string2) {
	int count =0;
	    for(int i =0;i<flexibook.getAppointments().size();i++) {
	 if(flexibook.getAppointment(i).getBookableService().getName().equals(string)) {
	  count++;
	}
	}
	    assertEquals(Integer.decode(string2),count);
	  }
	  @Then("the number of appointments in the system shall be {string}")
	  public void the_number_of_appointments_in_the_system_shall_be(String string) {
	    List<Appointment> thi=flexibook.getAppointments();  
	    assertEquals(Integer.decode(string),flexibook.getAppointments().size());
	      
	  }
	  @After
	  public void tearDown() {
	      flexibook=FlexiBookApplication.getflexibook();
	      flexibook.delete();
	  }
}
