package ca.mcgill.ecse.flexibook.features;
import static org.junit.Assert.assertEquals;

import java.sql.Date;
import java.sql.Time;
import java.util.List;
import java.util.Map;

import ca.mcgill.ecse.flexibook.Controller.FlexibookController;
import ca.mcgill.ecse.flexibook.Controller.InvalidInputException;
import ca.mcgill.ecse.flexibook.application.FlexiBookApplication;
import ca.mcgill.ecse.flexibook.model.Business;
import ca.mcgill.ecse.flexibook.model.BusinessHour;
import ca.mcgill.ecse.flexibook.model.BusinessHour.DayOfWeek;
import ca.mcgill.ecse.flexibook.model.FlexiBook;
import ca.mcgill.ecse.flexibook.model.TimeSlot;
import io.cucumber.java.After;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
public class UpdateBusinessInformation {
	  private  FlexiBook flexibook=FlexiBookApplication.getflexibook();
	  private  InvalidInputException thise=null;
	  
	  private  String[] businessInfor;
	  private TimeSlot newTimeSlot=null;
	  private String typeofNewslot=" ";
	  private String resultError="not be";
	@When("the user tries to update the business information with new {string} and {string} and {string} and {string}")
	public void the_user_tries_to_update_the_business_information_with_new_and_and_and(String string, String string2, String string3, String string4) {
		try {  
	        FlexibookController.UpdateBusinessInformation(string, string2, string3, string4);
		 SetupBusinessInformationStepDefinition.resultString="be";
		 FlexiBookApplication.setmessage("");
       }catch (Exception e) {
       
    	   SetupBusinessInformationStepDefinition.resultString="not be";

   		FlexiBookApplication.setmessage(e.getMessage());	
   		
       }
		
	}
	@Then("the business information shall {string} updated with new {string} and {string} and {string} and {string}")
	public void the_business_information_shall_updated_with_new_and_and_and(String string, String string2, String string3, String string4, String string5) {
		if(SetupBusinessInformationStepDefinition.resultString.equals("be")) {
			Business aBusiness=flexibook.getBusiness();
			
			assertEquals(aBusiness.getName(), string2);
			assertEquals(aBusiness.getAddress(), string3);
			assertEquals(aBusiness.getPhoneNumber(), string4);
			assertEquals(aBusiness.getEmail(), string5);
		}
	}
	@When("the user tries to change the business hour {string} at {string} to be on {string} starting at {string} and ending at {string}")
	public void the_user_tries_to_change_the_business_hour_at_to_be_on_starting_at_and_ending_at(String string, String string2, String string3, String string4, String string5) {
		try {  
	        FlexibookController.UpdateExistingBusinessHour(string, string2, string3, string4,string5);
		 SetupBusinessInformationStepDefinition.resultString="be";
		 FlexiBookApplication.setmessage("");
       }catch (Exception e) {
       
    	   SetupBusinessInformationStepDefinition.resultString="not be";

   		FlexiBookApplication.setmessage(e.getMessage());	
   		
       }
	}

	@Then("the business hour shall {string} be updated")
	public void the_business_hour_shall_be_updated(String string) {
	    // Write code here that turns the phrase above into concrete actions
	  
	}
	@When("the user tries to remove the business hour starting {string} at {string}")
	public void the_user_tries_to_remove_the_business_hour_starting_at(String string, String string2)  {  
	        FlexibookController.UpdateExistingBusinessHour(string, string2);
		 SetupBusinessInformationStepDefinition.resultString="be";
		 FlexiBookApplication.setmessage("");
       }catch (Exception e) {
       
    	   SetupBusinessInformationStepDefinition.resultString="not be";

   		FlexiBookApplication.setmessage(e.getMessage());	
   		
       }
	}



	@Then("the business hour starting {string} at {string} shall {string} exist")
	public void the_business_hour_starting_at_shall_exist(String string, String string2, String string3) {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}
	@Then("an error message {string} shall {string} be raised")
	public void an_error_message_shall_be_raised(String string, String string2) {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}


}
