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
	  private String removeResult="not";
	  private String typeofNewslot=" ";
	    @Given("the business has a business hour on {string} with start time {string} and end time {string}")
	    public void the_business_has_a_business_hour_on_with_start_time_and_end_time(String string,String string2,String string3) {
	    	flexibook=FlexiBookApplication.getflexibook();
	    	Business business= flexibook.getBusiness();
	    	boolean hasSuchHour=false;
	    	if(business.getBusinessHours().size()!=0) {
				 for(BusinessHour hour:business.getBusinessHours()) {
					 if(hour.getDayOfWeek().equals(DayOfWeek.valueOf(string))&&hour.getStartTime().equals(Time.valueOf(string2+":00"))&&hour.getEndTime().equals(Time.valueOf(string3+":00"))) {
						 hasSuchHour=true;
						  return;
						 }
					 }
				 }
	    	if(hasSuchHour==false) {
	    		DayOfWeek dayOfWeek=DayOfWeek.valueOf(string);
	    		Time startTime=Time.valueOf(string2+":00");
	    		Time endTime=Time.valueOf(string3+":00");
	    		BusinessHour nHour=new BusinessHour(dayOfWeek, startTime, endTime, flexibook);
	    		business.addBusinessHour(nHour);
			  }
	    }
	@When("the user tries to update the business information with new {string} and {string} and {string} and {string}")
	public void the_user_tries_to_update_the_business_information_with_new_and_and_and(String string, String string2, String string3, String string4) {
		try {  
	        FlexibookController.UpdateBusinessInformation(string, string2, string3, string4);
		 SetupBusinessInformationStepDefinition.resultString="be";
		 SetupBusinessInformationStepDefinition.resultError="not be";
		 FlexiBookApplication.setmessage("");
       }catch (Exception e) {
       
    	   SetupBusinessInformationStepDefinition.resultString="not be";
    	   SetupBusinessInformationStepDefinition.resultError="be";
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
		 SetupBusinessInformationStepDefinition.resultError="not be";
		 FlexiBookApplication.setmessage("");
       }catch (Exception e) {
    	   SetupBusinessInformationStepDefinition.resultError="be";
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
	        
		try{FlexibookController.removerBusinessHour(string, string2);
		 removeResult="not";
	
		 FlexiBookApplication.setmessage("");
       }catch (Exception e) {
          removeResult="";

   		FlexiBookApplication.setmessage(e.getMessage());	
   		
       }
	}



	@Then("the business hour starting {string} at {string} shall {string} exist")
	public void the_business_hour_starting_at_shall_exist(String string, String string2, String string3) {
	
	}
	@Then("an error message {string} shall {string} be raised")
	public void an_error_message_shall_be_raised(String string, String string2) {
		String e = FlexiBookApplication.returnmessage();
		
		assertEquals(string2,removeResult);
		if(string2.equals("not")==false) {
		assertEquals(string, e);
		}
	}
	@When("the user tries to change the {string} on {string} at {string} to be with start date {string} at {string} and end date {string} at {string}")
	public void the_user_tries_to_change_the_on_at_to_be_with_start_date_at_and_end_date_at(String string, String string2, String string3, String string4, String string5, String string6, String string7) {
		try {
     		typeofNewslot=string;
    		 FlexibookController.updateHolidayOrVacation(string, string2, string3, string4, string5, string6, string7);
    		 SetupBusinessInformationStepDefinition.resultError="not be";
    		 SetupBusinessInformationStepDefinition.resultString="be";
    		 FlexiBookApplication.setmessage("");
	        }catch (Exception e) {
	        	SetupBusinessInformationStepDefinition.resultString="not be";
	        	SetupBusinessInformationStepDefinition.resultError="be";
	    		FlexiBookApplication.setmessage(e.getMessage());	    		
	    	}
	}



	@Then("the {string} shall {string} updated with start date {string} at {string} and end date {string} at {string}")
	public void the_shall_updated_with_start_date_at_and_end_date_at(String string, String string2, String string3, String string4, String string5, String string6) {

	}
	
	@When("the user tries to remove an existing {string} with start date {string} at {string} and end date {string} at {string}")
	public void the_user_tries_to_remove_an_existing_with_start_date_at_and_end_date_at(String string, String string2, String string3, String string4, String string5) {
		try{FlexibookController.removeExistingTimeSlot(string, string2,string3,string4,string5);
		 removeResult="not";
	
		 FlexiBookApplication.setmessage("");
      }catch (Exception e) {
         removeResult="";

  		FlexiBookApplication.setmessage(e.getMessage());	
  		
      }
	}


		@Then("the {string} with start date {string} at {string} shall {string} exist")
		public void the_with_start_date_at_shall_exist(String string, String string2, String string3, String string4) {
	
		}


}
