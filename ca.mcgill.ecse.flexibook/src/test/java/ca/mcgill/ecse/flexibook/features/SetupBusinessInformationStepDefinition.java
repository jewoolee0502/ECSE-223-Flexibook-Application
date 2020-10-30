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

public class SetupBusinessInformationStepDefinition {
	  private  FlexiBook flexibook=FlexiBookApplication.getflexibook();
	  private  InvalidInputException thise=null;
	  public static String resultString="";
	  private  String[] businessInfor;
	  private TimeSlot newTimeSlot=null;
	  private String typeofNewslot=" ";
	  public static String resultError="not be";
	  
	  



	@Given("no business exists")
	public void no_business_exists() {
	  if(flexibook.getBusiness()!=null) {
		  flexibook.setBusiness(null);
	  }
	}
	@When("the user tries to set up the business information with new {string} and {string} and {string} and {string}")
		public void the_user_tries_to_set_up_the_business_information_with_new_and_and_and(String string, String string2, String string3, String string4)throws InvalidInputException {
	    try {  
	    	resultString="be";
	        FlexibookController.setBusinessInformation(string, string2, string3, string4);
	        resultError="not be";
	        }catch (InvalidInputException e) {
	        	resultString="not be";
	    		FlexiBookApplication.setmessage(e.getMessage());
	    		resultError="be";
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
	    }
	}

	    @When("the user tries to add a new business hour on {string} with start time {string} and end time {string}")
	    public void the_user_tries_to_add_a_new_business_hour_on_with_start_time_and_end_time(String string, String string2, String string3) throws InvalidInputException{
	    	try {
	    		resultString="be";
	    		
	    		 FlexibookController.addNewBusinessHour(string,string2,string3);
	    		 resultError="not be";
		        }catch (Exception e) {
		    		// TODO: handle exception
		        	resultString="not be";
		    		FlexiBookApplication.setmessage(e.getMessage());
		    		resultError="be";
		    	}
	    }
	    @Then("a new business hour shall {string} created")
	    public void a_new_business_hour_shall_created(String string) {
	    	
	    	assertEquals(string, resultString);
	    }
	    @When("the user tries to access the business information")
	    public void the_user_tries_to_access_the_business_information() {
	    	businessInfor=FlexibookController.viewBusinessInfor();
	    }


	    @Then("the {string} and {string} and {string} and {string} shall be provided to the user")
	    public void the_and_and_and_shall_be_provided_to_the_user(String string, String string2, String string3, String string4) {
	      assertEquals(businessInfor[0], string);
	      assertEquals(businessInfor[1], string2);
	      assertEquals(businessInfor[2], string3);
	      assertEquals(businessInfor[3], string4);
	    }
	    @Given("a {string} time slot exists with start time {string} at {string} and end time {string} at {string}")
	    public void a_time_slot_exists_with_start_time_at_and_end_time_at(String string, String string2, String string3, String string4, String string5) {
	    	Business business=flexibook.getBusiness();
	    	List<TimeSlot> timeSlots;
	    	Date staDate=Date.valueOf(string2);
	    	Time staTime=Time.valueOf(string3+":00");
	    	Date enDate=Date.valueOf(string4);
	    	Time enTime=Time.valueOf(string5+":00");
	    	
	    	if(string.equals("vacation")){
	    		
	    		timeSlots=business.getVacation();
	    	}else {
				timeSlots=business.getHolidays();
			}
	    	if(timeSlots==null) {
	    		TimeSlot slot=new TimeSlot(staDate,staTime,enDate,enTime,flexibook);
	    		timeSlots.add(slot);
	    		if(string.equals("vacation")) {
	    			business.addVacation(slot);
	    			}
	    		else {
	    			business.addHoliday(slot);
	    		}
	    		return;
	    	}
	    	boolean exist=false; 	
	    	for(TimeSlot slot:timeSlots) {
	    		if(slot.getStartDate().equals(staDate)&&slot.getStartTime().equals(staTime)&&slot.getEndDate().equals(enDate)&&slot.getEndTime().equals(enTime)) {
	    			exist=true;
	    		}
	    	}
	    	if(exist==false) {
	    	TimeSlot slot=new TimeSlot(staDate,staTime,enDate,enTime,flexibook);
    		if(string.equals("vacation")) {
    			business.addVacation(slot);
    			}
    		else {
    			business.addHoliday(slot);
    		}
	    	}
	    }


	  

	    @When("the user tries to add a new {string} with start date {string} at {string} and end date {string} at {string}")
	    public void the_user_tries_to_add_a_new_with_start_date_at_and_end_date_at(String string, String string2, String string3, String string4, String string5) {
	     	try {
	     		typeofNewslot=string;
	    		 FlexibookController.addTimeSlot( string,  string2, string3,  string4,  string5);
	    		 resultError="not be";
	    		 resultString="be";
	    		 FlexiBookApplication.setmessage("");
		        }catch (Exception e) {
		        	resultString="not be";
		        	resultError="be";
		    		FlexiBookApplication.setmessage(e.getMessage());	    		
		    	}
	    }

	    @Then("a new {string} shall {string} be added with start date {string} at {string} and end date {string} at {string}")
	    public void a_new_shall_be_added_with_start_date_at_and_end_date_at(String string, String string2, String string3, String string4, String string5, String string6) {
	    	Business business=flexibook.getBusiness();
	    	List<TimeSlot> timeSlots;
	    	Date staDate=Date.valueOf(string3);
	    	Time staTime=Time.valueOf(string4+":00");
	    	Date enDate=Date.valueOf(string5);
	    	Time enTime=Time.valueOf(string6+":00");
	    	if(string.equals("vacation")){
	    		
	    		timeSlots=business.getVacation();
	    	}else {
				timeSlots=business.getHolidays();
			}
	    	boolean exist=false; 	
	    	for(TimeSlot slot:timeSlots) {
	    		if(slot.getStartDate().equals(staDate)&&slot.getStartTime().equals(staTime)&&slot.getEndDate().equals(enDate)&&slot.getEndTime().equals(enTime)) {
	    			exist=true;
	    			newTimeSlot=slot;
	    			break;
	    		}
	    	}
	    	if(exist==true) {
	    	assertEquals(string, typeofNewslot);
	    	assertEquals(Date.valueOf(string3), newTimeSlot.getStartDate());
	    	assertEquals(Time.valueOf(string4+":00"), newTimeSlot.getStartTime());
	    	assertEquals(Date.valueOf(string5), newTimeSlot.getEndDate());
	    	assertEquals(Time.valueOf(string6+":00"), newTimeSlot.getEndTime());

	    	}
	    }



}
