package ca.mcgill.ecse.flexibook.features;

import java.sql.Date;
import java.sql.Time;
import java.util.List;
import java.util.Map;

import ca.mcgill.ecse.flexibook.Controller.FlexibookController;
import ca.mcgill.ecse.flexibook.Controller.InvalidInputException;
import ca.mcgill.ecse.flexibook.application.FlexiBookApplication;
import ca.mcgill.ecse.flexibook.model.Business;
import ca.mcgill.ecse.flexibook.model.Customer;
import ca.mcgill.ecse.flexibook.model.FlexiBook;
import ca.mcgill.ecse.flexibook.model.Owner;
import ca.mcgill.ecse.flexibook.model.Service;
import ca.mcgill.ecse.flexibook.util.SystemTime;
import io.cucumber.java.After;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class MakeAppointmentStepDefinitions {
/*
	private FlexiBook flexibook = FlexiBookApplication.getflexibook();


	@Given("a Flexibook system exists")
	public void a_flexibook_system_exists() {
		// Write code here that turns the phrase above into concrete actions
		if(flexibook!=null) {
			flexibook = new FlexiBook();
			FlexiBookApplication.setflexibook(flexibook);
		}
	}

	@Given("the system's time and date is {string}")
	public void the_system_s_time_and_date_is(String string) {
		SystemTime systime = new SystemTime(string, true);
		
	}




	@Given("the following customers exist in the system:")
	public void the_following_customers_exist_in_the_system(io.cucumber.datatable.DataTable dataTable) {
		List<Map<String, String>> list = dataTable.asMaps();
		for(Map<String,String> map : list) {
			String username = map.get("username");
			String password = map.get("password");
			new Customer(username, password, flexibook);

		}
	}

	@Given("the following service combos exist in the system:")
	public void the_following_service_combos_exist_in_the_system(io.cucumber.datatable.DataTable dataTable) throws InvalidInputException {
		List<Map<String,String>> list = dataTable.asMaps();
		String owner = flexibook.getOwner().getUsername();
		for(Map<String,String> map : list) {
			String name = map.get("name");
			String mainService = "Item"+map.get("mainService");
			String services = map.get("services");
			String mandatory = map.get("mandatory");
			FlexibookController.makecombo(owner, name, mainService, services, mandatory);
		}
	}


	@Given("the business has the following opening hours")
	public void the_business_has_the_following_opening_hours(io.cucumber.datatable.DataTable dataTable) {
		List<Map<String,String>> list = dataTable.asMaps();
		for(Map<String,String> map : list) {
			String day = map.get("day");
			Time startTime = Time.valueOf(map.get("startTime")+":00");//why not in correct format!!!
			Time endTime = Time.valueOf(map.get("endTime")+":00");
			Business business = this.flexibook.getBusiness();
			//TODO
		}
	}

	@Given("the business has the following holidays")
	public void the_business_has_the_following_holidays(io.cucumber.datatable.DataTable dataTable) {
		List<Map<String,String>> list = dataTable.asMaps();
		for(Map<String,String> map : list) {
			Date startDate = Date.valueOf(map.get("startDate"));
			Date endDate = Date.valueOf(map.get("endDate"));
			Time startTime = Time.valueOf(map.get("startTime")+":00");
			Time endTime = Time.valueOf(map.get("endTime")+":00");
			Business business = this.flexibook.getBusiness();
			//TODO
		}
	}

	@Given("the following appointments exist in the system:")
	public void the_following_appointments_exist_in_the_system(io.cucumber.datatable.DataTable dataTable) {
		List<Map<String,String>> list = dataTable.asMaps();
		for(Map<String,String> map : list) {
			String customer = map.get("customer");
			String serviceName = map.get("serviceName");
			String optServices = map.get("optServices");
			Date date = Date.valueOf(map.get("date"));
			Time startTime = Time.valueOf(map.get("startTime")+":00");
			Time endTime = Time.valueOf(map.get("endTime")+":00");
			//TODO
		}
	}


	@Given("{string} is logged in to their account")
	public void is_logged_in_to_their_account(String customer1) {
		//TODO Depend on Logged_in
	}

	@When("{string} schedules an appointment on {string} for {string} at {string}")
	public void schedules_an_appointment_on_for_at(String customer, String date, String serviceName, String startTime) {
		try {
			FlexibookController.MakeAppointment(customer,date, serviceName,startTime);
		}catch(Exception e){
			throw e;
		}

	}
	
	@Then("{string} shall have a {string} appointment on {string} from {string} to {string}")
	public void shall_have_a_appointment_on_from_to(String customer, String serviceName, String date, String startTime, String endTime) {

	}
	@Then("there shall be {int} more appointment in the system")
	public void there_shall_be_more_appointment_in_the_system(Integer int1) {

	}
	
	@Then("the system shall report {string}")
	public void the_system_shall_report(String string) {
	    // Write code here that turns the phrase above into concrete actions
	}
	
	@When("{string} selects {string} for the service combo")
	public void selects_for_the_service_combo(String string, String string2) {
	    // Write code here that turns the phrase above into concrete actions
	    
	}
	
	//owner schedule 
	@When("{string} schedules an appointment on on {string} for {string} at {string}")
	public void schedules_an_appointment_on_on_for_at(String string, String string2, String string3, String string4) {
	    // Write code here that turns the phrase above into concrete actions
	    
	}
	
	
	@After
	public void tearDown() {
		flexibook=FlexiBookApplication.getflexibook();
		flexibook.delete();
	}*/
}
