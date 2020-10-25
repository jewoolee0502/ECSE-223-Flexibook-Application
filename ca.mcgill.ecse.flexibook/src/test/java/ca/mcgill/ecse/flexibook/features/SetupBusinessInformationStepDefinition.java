package ca.mcgill.ecse.flexibook.features;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.sql.Time;
import java.util.List;
import java.util.Map;

import org.omg.CORBA.UserException;

import ca.mcgill.ecse.flexibook.Controller.FlexibookController;
import ca.mcgill.ecse.flexibook.Controller.InvalidInputException;
import ca.mcgill.ecse.flexibook.application.FlexiBookApplication;
import ca.mcgill.ecse.flexibook.model.Business;
import ca.mcgill.ecse.flexibook.model.BusinessHour;
import ca.mcgill.ecse.flexibook.model.BusinessHour.DayOfWeek;
import ca.mcgill.ecse.flexibook.model.Customer;
import ca.mcgill.ecse.flexibook.model.FlexiBook;
import ca.mcgill.ecse.flexibook.model.Owner;
import ca.mcgill.ecse.flexibook.model.User;
import io.cucumber.java.After;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;

public class SetupBusinessInformationStepDefinition {
	  private  FlexiBook flexibook=FlexiBookApplication.getflexibook();
	  private  InvalidInputException thise=null;
	  private String resultString="";


	@Given("no business exists")
	public void no_business_exists() {
	  if(flexibook.getBusiness()!=null) {
		  flexibook.setBusiness(null);
	  }
	}
	@When("the user tries to set up the business information with new {string} and {string} and {string} and {string}")
		public void the_user_tries_to_set_up_the_business_information_with_new_and_and_and(String string, String string2, String string3, String string4)throws InvalidInputException {
	    try {  
	        FlexibookController.setBusinessInformation(string, string2, string3, string4);
	        }catch (InvalidInputException e) {
	        	FlexiBookApplication.setmessage(e.getMessage());
	        }
	        
		}
	@Then("a new business with new {string} and {string} and {string} and {string} shall {string} created")
	public void a_new_business_with_new_and_and_and_shall_created(String string, String string2, String string3, String string4, String string5) {
		Business cuBusiness=FlexiBookApplication.getflexibook().getBusiness();
		String name="",address="",phoneNumber="",email="";resultString="not be";
		if(cuBusiness!=null) {
		 name=cuBusiness.getName();
		address=cuBusiness.getAddress();
		 phoneNumber=cuBusiness.getPhoneNumber();
		email=cuBusiness.getEmail();
		resultString="be";
		}
		if(resultString.equals("be")) {
		assertEquals(string,name);
		assertEquals(string2,address);
		assertEquals( string3,phoneNumber);
		assertEquals( string4,email);
		assertEquals(string5, resultString);
		}
	}	


	@Then("an error message {string} shall {string} raised")
	public void an_error_message_shall_raised(String string, String string2) {
		String e = FlexiBookApplication.returnmessage();
		
		System.out.println(resultString);
		String resultError="not be";
		if(resultString.equals("not be")) {
			resultError="be";
		}
		   assertEquals(string,e);
		   assertEquals(string2, resultError);
		   FlexiBookApplication.setmessage(null);
		   
	}
	@After
	  public void tearDown() {
	     flexibook=FlexiBookApplication.getflexibook();
	      flexibook.delete();
	} 
	
	@Given("a business exists with the following information:")
	public void a_business_exists_with_the_following_information(io.cucumber.datatable.DataTable dataTable) throws InvalidInputException {
	    // Write code here that turns the phrase above into concrete actions
	    // For automatic transformation, change DataTable to one of
	    // E, List<E>, List<List<E>>, List<Map<K,V>>, Map<K,V> or
	    // Map<K, List<V>>. E,K,V must be a String, Integer, Float,
	    // Double, Byte, Short, Long, BigInteger or BigDecimal.
	    //
	    // For other transformations you can register a DataTableType.
		// name        | address          | phone number  | email   
		flexibook=FlexiBookApplication.getflexibook();
	    List<Map<String, String>> valueMaps = dataTable.asMaps();
	    for (Map<String, String> map : valueMaps) {
	     String name = map.get("name");
	     String address = map.get("address");
	     String phonenumber = map.get("phone number");
	     String email = map.get("email"); 
	     Business aNewBusiness=new Business(name, address, phonenumber, email, flexibook);
	     flexibook.setBusiness(aNewBusiness);
	     FlexibookController.setBusinessInformation(name, address, phonenumber, email);
	    }
	}
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
	    @When("the user tries to add a new business hour on {string} with start time {string} and end time {string}")
	    public void the_user_tries_to_add_a_new_business_hour_on_with_start_time_and_end_time(String string, String string2, String string3) throws InvalidInputException{
	    	try {
	    		resultString="be";
	    		 FlexibookController.addNewBusinessHour(string,string2,string3);
		        }catch (Exception e) {
		    		// TODO: handle exception
		        	resultString="not be";
		    		String ebString=e.getMessage();
		    		FlexiBookApplication.setmessage(e.getMessage());
		    		String ab=FlexiBookApplication.returnmessage();
		    	}
	    }
	    @Then("a new business hour shall {string} created")
	    public void a_new_business_hour_shall_created(String string) {
	    	
	    	assertEquals(string, resultString);
	    }
	    /*@When("the user tries to access the business information")
	    public void the_user_tries_to_access_the_business_information() {

	    }


	    @Then("the {string} and {string} and {string} and {string} shall be provided to the user")
	    public void the_and_and_and_shall_be_provided_to_the_user(String string, String string2, String string3, String string4) {
	      
	    }
*/




}
