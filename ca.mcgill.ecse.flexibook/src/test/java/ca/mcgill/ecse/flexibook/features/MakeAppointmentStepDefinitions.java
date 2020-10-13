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

	FlexiBook flexibook = FlexiBookApplication.getflexibook();

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
		//TODO
	}

	@Given("an owner account exists in the system")
	public void an_owner_account_exists_in_the_system() {
		if(!flexibook.hasOwner()) {
			new Owner("flexi_owner", "123456", flexibook);
		}
	}

	@Given("a business exists in the system")
	public void a_business_exists_in_the_system() {
		if(!flexibook.hasBusiness()) {
			new Business("business","address","1234567890","123@mail.com",flexibook);
		}
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

	@Given("the following services exist in the system:")
	public void the_following_services_exist_in_the_system(io.cucumber.datatable.DataTable dataTable) {
		List<Map<String,String>> list = dataTable.asMaps();
		for(Map<String,String> map : list) {
			String name = map.get("name");
			Integer duration = Integer.decode(map.get("duration"));
			Integer downtimeStart = Integer.decode(map.get("downtimeStart"));
			Integer downtimeDuration = Integer.decode(map.get("downtimeDuration"));
			new Service(name, flexibook,duration, downtimeStart, downtimeDuration);
		}
	}

	@Given("the following service combos exist in the system:")
	public void the_following_service_combos_exist_in_the_system(io.cucumber.datatable.DataTable dataTable) {
		List<Map<String,String>> list = dataTable.asMaps();
		for(Map<String,String> map : list) {
			String name = map.get("name");
			String mainService = map.get("mainService");
			String[] services = map.get("services").split(",");
			String[] mandatory = map.get("mandatory").split(",");
			//TODO

		}
	}

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
	@Given("the following appointments exist in the system:")
	public void the_following_appointments_exist_in_the_system(io.cucumber.datatable.DataTable dataTable) {
		List<Map<String,String>> list = dataTable.asMaps();
		for(Map<String,String> map : list) {
			String customer = map.get("customer");
			String serviceName = map.get("serviceName");
			String[] optServices = map.get("optServices").split(",");
			Date date = Date.valueOf(map.get("date"));
			Time startTime = Time.valueOf(map.get("startTime"));
			Time endTime = Time.valueOf(map.get("endTime"));
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
