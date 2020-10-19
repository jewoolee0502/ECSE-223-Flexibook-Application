package ca.mcgill.ecse.flexibook.features;

import static org.junit.Assert.assertEquals;

import java.sql.Date;
import java.sql.Time;
import java.util.List;
import java.util.Map;

import ca.mcgill.ecse.flexibook.Controller.FlexibookController;
import ca.mcgill.ecse.flexibook.Controller.InvalidInputException;
import ca.mcgill.ecse.flexibook.application.FlexiBookApplication;
import ca.mcgill.ecse.flexibook.model.*;
import ca.mcgill.ecse.flexibook.model.BusinessHour.DayOfWeek;
import ca.mcgill.ecse.flexibook.util.SystemTime;
import io.cucumber.java.After;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class MakeAppointmentStepDefinitions {

	private FlexiBook flexibook = FlexiBookApplication.getflexibook();

	//	@Given("a Flexibook system exists")
	//	public void a_flexibook_system_exists() {
	//		// Write code here that turns the phrase above into concrete actions
	//		if(flexibook!=null) {
	//			flexibook = new FlexiBook();
	//			FlexiBookApplication.setflexibook(flexibook);
	//		}
	//	}

	@Given("the system's time and date is {string}")
	public void the_system_s_time_and_date_is(String string) {
		SystemTime systime = new SystemTime(string, true);

	}

	//	@Given("an owner account exists in the system")
	//	public void an_owner_account_exists_in_the_system() {
	//		if(!flexibook.hasOwner()) {
	//			new Owner("owner", "123456", flexibook);
	//		}
	//	}

	//	@Given("a business exists in the system")
	//	public void a_business_exists_in_the_system() {
	//		if(!flexibook.hasBusiness()) {
	//			new Business("business","address","1234567890","123@mail.com",flexibook);
	//		}
	//	}

//		@Given("the following customers exist in the system:")
//		public void the_following_customers_exist_in_the_system(io.cucumber.datatable.DataTable dataTable) {
//			List<Map<String, String>> list = dataTable.asMaps();
//			for(Map<String,String> map : list) {
//				String username = map.get("username");
//				String password = map.get("password");
//				new Customer(username, password, flexibook);
//			}
//		}

	//	@Given("the following services exist in the system:")
	//	public void the_following_services_exist_in_the_system(io.cucumber.datatable.DataTable dataTable) {
	//		List<Map<String,String>> list = dataTable.asMaps();
	//		for(Map<String,String> map : list) {
	//			String name = map.get("name");
	//			Integer duration = Integer.decode(map.get("duration"));
	//			Integer downtimeStart = Integer.decode(map.get("downtimeStart"));
	//			Integer downtimeDuration = Integer.decode(map.get("downtimeDuration"));
	//			new Service(name, flexibook,duration, downtimeStart, downtimeDuration);
	//		}
	//	}

	//	@Given("the following service combos exist in the system:")
	//	public void the_following_service_combos_exist_in_the_system(io.cucumber.datatable.DataTable dataTable) throws InvalidInputException {
	//		List<Map<String,String>> list = dataTable.asMaps();
	//		String owner = flexibook.getOwner().getUsername();
	//		for(Map<String,String> map : list) {
	//			String name = map.get("name");
	//			String mainService = "Item"+map.get("mainService");
	//			String services = map.get("services");
	//			String mandatory = map.get("mandatory");
	//			FlexibookController.makecombo(owner, name, mainService, services, mandatory);
	//		}
	//	}

	@Given("the business has the following opening hours")
	public void the_business_has_the_following_opening_hours(io.cucumber.datatable.DataTable dataTable) {
		List<Map<String,String>> list = dataTable.asMaps();
		for(Map<String,String> map : list) {
			DayOfWeek dayofweek = DayOfWeek.valueOf(map.get("day"));
			Time startTime = Time.valueOf(map.get("startTime")+":00");
			Time endTime = Time.valueOf(map.get("endTime")+":00");
			if(flexibook.getBusiness() == null) {
				new Business("Salon", "123 street","1234567890","123@mail.com",flexibook);
			}
			BusinessHour aBusinessHour = new BusinessHour(dayofweek,startTime,endTime,flexibook);
			flexibook.getBusiness().addBusinessHour(aBusinessHour);
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
			TimeSlot timeslot = new TimeSlot(startDate,startTime,endDate,endTime,flexibook);
			flexibook.getBusiness().addHoliday(timeslot);
		}
	}
	
	@Given("the following appointments exist in the system:")
	public void the_following_appointments_exist_in_the_system(io.cucumber.datatable.DataTable dataTable) throws InvalidInputException {
		List<Map<String,String>> list = dataTable.asMaps();
		for(Map<String,String> map : list) {
			String customer = map.get("customer");
			String serviceName = map.get("serviceName");
			String optServices = map.get("optServices");
			String date = map.get("date");
			String startTime = map.get("startTime");
			FlexibookController.MakeAppointment(customer, date, serviceName, optServices, startTime);
		}
	}

	@Given("{string} is logged in to their account")
	public void is_logged_in_to_their_account(String customer1) {
		int cindex = -1;

		for(Customer c : flexibook.getCustomers()) {
			if(c.getUsername().equals(customer1)) {
				cindex = flexibook.indexOfCustomer(c);
			}
		}
		if(cindex == -1) {
			FlexiBookApplication.setCurrentuser(flexibook.getOwner());
		}else {
			FlexiBookApplication.setCurrentuser(flexibook.getCustomer(cindex));
		}

	}

	@When("{string} schedules an appointment on {string} for {string} at {string}")
	public void schedules_an_appointment_on_for_at(String customer, String date, String serviceName, String startTime) throws InvalidInputException{
		try {
			FlexibookController.MakeAppointment(customer,date, serviceName,null ,startTime);

		}catch(InvalidInputException e){
			FlexiBookApplication.setmessage(e.getMessage());
		}
	}

	@When("{string} schedules an appointment on {string} for {string} with {string} at {string}")
	public void schedules_an_appointment_on_for_with_at(String customer, String date, String serviceName, String optionalServices, String startTime) throws InvalidInputException{
		try {
			FlexibookController.MakeAppointment(customer, date, serviceName, optionalServices, startTime);

		}catch(InvalidInputException e) {
			FlexiBookApplication.setmessage(e.getMessage());
		}
	}

	@Then("{string} shall have a {string} appointment on {string} from {string} to {string}")
	public void shall_have_a_appointment_on_from_to(String customer, String serviceName, String date, String startTime, String endTime) {
		int cindex = -1;

		for(Customer c : flexibook.getCustomers()) {
			if(c.getUsername().equals(customer)) {
				cindex = flexibook.indexOfCustomer(c);
			}
		}
		
	}

	@Then("there shall be {int} more appointment in the system")
	public void there_shall_be_more_appointment_in_the_system(Integer int1) {
		int count = flexibook.numberOfAppointments();
		
	}

	@Then("the system shall report {string}")
	public void the_system_shall_report(String string) {
		String e = FlexiBookApplication.returnmessage();
		assertEquals(string, e);
		FlexiBookApplication.setmessage(null);
	}


	//owner schedule 
	@When("{string} schedules an appointment on on {string} for {string} at {string}")
	public void schedules_an_appointment_on_on_for_at(String owner, String date, String serviceName, String startTime) throws InvalidInputException{
		try {
			FlexibookController.MakeAppointment(owner, date, serviceName, null, startTime);
		}catch(InvalidInputException e) {
			FlexiBookApplication.setmessage(e.getMessage());
		}
	}


	@After
	public void tearDown() {
		flexibook=FlexiBookApplication.getflexibook();
		flexibook.delete();
	}
}

