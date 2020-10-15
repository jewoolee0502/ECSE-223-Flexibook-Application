package ca.mcgill.ecse.flexibook.features;

import java.sql.Date;
import java.sql.Time;
import java.util.List;
import java.util.Map;

import ca.mcgill.ecse.flexibook.Controller.FlexibookController;
import ca.mcgill.ecse.flexibook.application.FlexiBookApplication;
import ca.mcgill.ecse.flexibook.model.Business;
import ca.mcgill.ecse.flexibook.model.Customer;
import ca.mcgill.ecse.flexibook.model.FlexiBook;
import ca.mcgill.ecse.flexibook.model.Owner;
import ca.mcgill.ecse.flexibook.model.Service;
import io.cucumber.java.After;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class MakeAppointmentStepDefinitions {

	private FlexiBook flexibook = FlexiBookApplication.getflexibook();







	@Given("the business has the following opening hours")
	public void the_business_has_the_following_opening_hours(io.cucumber.datatable.DataTable dataTable) {
		List<Map<String,String>> list = dataTable.asMaps();
		for(Map<String,String> map : list) {
			String day = map.get("day");
			Time startTime = Time.valueOf(map.get("startTime")+":00");//why not in correct format!!!
			Time endTime = Time.valueOf(map.get("endTime")+"00");
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
			Time startTime = Time.valueOf(map.get("startTime"));
			Time endTime = Time.valueOf(map.get("endTime"));
			Business business = this.flexibook.getBusiness();
			//TODO
		}
	}


	@Given("{string} is logged in to their account")
	public void is_logged_in_to_their_account(String customer1) {
		//TODO Depend on Logged_in
	}

	@When("{string} schedules an appointment on {string} for {string} at {string}")
	public void schedules_an_appointment_on_for_at(String customer, String date, String serviceName, String startTime) {
	}
	@Then("{string} shall have a {string} appointment on {string} from {string} to {string}")
	public void shall_have_a_appointment_on_from_to(String customer, String serviceName, String date, String startTime, String endTime) {
		FlexibookController.MakeAppointment(customer,date, serviceName,startTime);

	}
	@Then("there shall be {int} more appointment in the system")
	public void there_shall_be_more_appointment_in_the_system(Integer int1) {
		//TODO
	}

	@After
	public void tearDown() {
		flexibook=FlexiBookApplication.getflexibook();
		flexibook.delete();
	}
}
