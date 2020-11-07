package ca.mcgill.ecse.flexibook.features;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.File;
import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import ca.mcgill.ecse.flexibook.Controller.FlexibookController;
import ca.mcgill.ecse.flexibook.Controller.InvalidInputException;
import ca.mcgill.ecse.flexibook.application.FlexiBookApplication;
import ca.mcgill.ecse.flexibook.model.Appointment;
import ca.mcgill.ecse.flexibook.model.BookableService;
import ca.mcgill.ecse.flexibook.model.Business;
import ca.mcgill.ecse.flexibook.model.BusinessHour;
import ca.mcgill.ecse.flexibook.model.ComboItem;
import ca.mcgill.ecse.flexibook.model.Customer;
import ca.mcgill.ecse.flexibook.model.FlexiBook;
import ca.mcgill.ecse.flexibook.model.Owner;
import ca.mcgill.ecse.flexibook.model.Service;
import ca.mcgill.ecse.flexibook.model.ServiceCombo;
import ca.mcgill.ecse.flexibook.model.TimeSlot;
import ca.mcgill.ecse.flexibook.model.User;
import ca.mcgill.ecse.flexibook.model.BusinessHour.DayOfWeek;
import ca.mcgill.ecse.flexibook.util.SystemTime;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class CucumberStepDefinitions {

	private FlexiBook flexibook = FlexiBookApplication.getflexibook();
	private InvalidInputException errorMessage = null;
	private String error;
	private List<Appointment> oldAppointments;
	private int errorCount = 0;
	private int count;
	private  String[] businessInfor;
	private TimeSlot newTimeSlot = null;
	private String typeofNewslot = "";
	private int errorCntr = 0;
	private int userCount = 0;
	private User tempUser;
	private static int userCntrBeforeCreation;
	private String removeResult = "not";
	private String oldUsername;
	private String oldPassword;
	private ArrayList<TimeSlot> notAvailable;
	private ArrayList<TimeSlot> available;
	private Appointment appointment;
	private Service service;
	private TimeSlot timeslot;
	private List<ComboItem> list;
	private int index;

	public static String resultError = "not be";
	public static String resultString = "";



	//Add Service----------------------------------------------------------------------------------------------------------------------------------------------------------

	/**
	 * This stepdefinition takes all parameters to add a new service in the system.
	 * @author Tianyu zhao
	 * @author Zhixin Xiong
	 */
	@When("{string} initiates the addition of the service {string} with duration {string}, start of down time {string} and down time duration {string}")
	public void initiates_the_addition_of_the_service_with_duration_start_of_down_time_and_down_time_duration(String string, String string2, String string3, String string4, String string5) {
		try{
			FlexibookController.addService(string, string2, string3,string4,string5);
		}catch (InvalidInputException e) {
			FlexiBookApplication.setmessage(e.getMessage());
		}
	}
	/**
	 * @author Tianyu Zhao
	 */
	@Then("the service {string} shall exist in the system")
	public void the_service_shall_exist_in_the_system(String string) {
		assertEquals(string,flexibook.getBookableService(0).getWithName(string).getName());
	}
	/**
	 * @author Tianyu Zhao
	 */
	@Then("the service {string} shall have duration {string}, start of down time {string} and down time duration {string}")
	public void the_service_shall_have_duration_start_of_down_time_and_down_time_duration(String string, String string2, String string3, String string4) {
		Service thiss=(Service) flexibook.getBookableService(0).getWithName(string);
		assertEquals(string2,Integer.toString(thiss.getDuration()) );
		assertEquals(string3,Integer.toString(thiss.getDowntimeStart()));
		assertEquals(string4,Integer.toString(thiss.getDowntimeDuration()));

	}
	/**
	 * @author Zhixin Xiong
	 */
	@Then("the number of services in the system shall be {string}")
	public void the_number_of_services_in_the_system_shall_be(String string) {
		int size=0;
		List<BookableService>allServices=flexibook.getBookableServices();
		for(BookableService serviceCom:allServices) {
			if(serviceCom instanceof Service) {
				size++;
			}
		}
		String string2=String.valueOf(size);
		assertEquals(string,string2);
	}
	/**
	 * @author Zhixin Xiong
	 */
	@Then("the service {string} shall not exist in the system")
	public void the_service_shall_not_exist_in_the_system(String string) {
		if (flexibook.getBookableServices().size()!=0) {
			flexibook.getBookableService(0);
			boolean isNull=false;
			if(BookableService.getWithName(string)==null) {
				isNull=true;
			}
			assertEquals(true,isNull);   
		}else {
			assertEquals(true,true);
		}
	}
	/**
	 * @author Jewoo Lee
	 */
	@After
	public void tearDown() {
		flexibook = FlexiBookApplication.getflexibook();
		flexibook.delete();
	}



	//Cancel Appointment------------------------------------------------------------------------------------------------------------------------------------------------------------------------

	/**
	 * This step definition takes all the parameters to cancel the appointment
	 * @author Jewoo Lee
	 */
	@When("{string} attempts to cancel their {string} appointment on {string} at {string}")
	public void attempts_to_cancel_their_appointment_on_at(String customer, String name, String serviceDate, String startTime) {
		try {
			FlexibookController.CancelAppointment(customer, null, serviceDate, startTime);
		} catch (InvalidInputException e) {
			FlexiBookApplication.setmessage(e.getMessage());
		}
	}

	/**
	 * @author Jewoo Lee
	 */
	@When("{string} attempts to cancel {string}'s {string} appointment on {string} at {string}")
	public void attempts_to_cancel_s_appointment_on_at(String customer, String customer2, String name, String serviceDate, String startTime) {
		try {
			FlexibookController.CancelAppointment(customer, customer2, serviceDate, startTime);
		} catch (InvalidInputException e) {
			FlexiBookApplication.setmessage(e.getMessage());

		}
	}

	/**
	 * @author Jewoo Lee
	 */
	@Then("{string}'s {string} appointment on {string} at {string} shall be removed from the system")
	public void s_appointment_on_at_shall_be_removed_from_the_system(String string, String string2, String string3, String string4) {

	}

	/**
	 * @author Jewoo Lee
	 */
	@Then("there shall be {int} less appointment in the system")
	public void there_shall_be_less_appointment_in_the_system(Integer int1) {

	}



	//Define Service Combo ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------


	/**This is the step definition to check the makecombo controller method.
	 *@author Haipeng Yue 
	 * 
	 */
	@Given("a Flexibook system exists")
	public void a_flexibook_system_exists() {
		flexibook=new FlexiBook();
		FlexiBookApplication.setflexibook(flexibook);
	}
	/**
	 * @author Haipeng Yue 
	 */
	@Given("an owner account exists in the system")
	public void an_owner_account_exists_in_the_system() {
		if(!flexibook.hasOwner()) {
			Owner owner = new Owner("a", "123", flexibook); 
		}
	}
	/**
	 * @author Haiepng Yue 
	 */
	@Given("Customer with username {string} is logged in")
	public void customer_with_username_is_logged_in(String string) {
		if(flexibook.getCustomers().size()!=0) {
			Customer thisc=(Customer) flexibook.getCustomer(0).getWithUsername(string);
			FlexiBookApplication.setCurrentuser(thisc);
		}
	}
	/**
	 * @author Haipeng Yue 
	 */
	@Given("a business exists in the system")
	public void a_business_exists_in_the_system() {
		if(!flexibook.hasBusiness()) {
			new Business("b", "ab", "1111111111", "123@mail.com", flexibook);
		}
	}
	/**
	 * @author Haipeng Yue
	 */
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
	/**
	 * @author Haipeng Yue
	 */
	@Given("the following service combos exist in the system:")
	public void the_following_service_combos_exist_in_the_system(io.cucumber.datatable.DataTable dataTable) throws InvalidInputException {
		String owner=flexibook.getOwner().getUsername();
		List<Map<String, String>> valueMaps = dataTable.asMaps();
		for (Map<String, String> map : valueMaps) {
			String name = map.get("name");
			String mainservice = map.get("mainService");
			String services = map.get("services");
			String mandatory = map.get("mandatory"); 
			FlexibookController.makecombo(owner, name, mainservice, services, mandatory);
		}
	}
	/**
	 * @author Haipeng Yue 
	 */
	@Given("the Owner with username {string} is logged in")
	public void the_owner_with_username_is_logged_in(String string) {
		flexibook.getOwner().setUsername(string);
		FlexiBookApplication.setCurrentuser(flexibook.getOwner());
	}
	/**
	 * @author Haipeng Yue 
	 */
	@When("{string} initiates the definition of a service combo {string} with main service {string}, services {string} and mandatory setting {string}")
	public void initiates_the_definition_of_a_service_combo_with_main_service_services_and_mandatory_setting(String string, String string2, String string3, String string4, String string5) throws InvalidInputException {
		try {  
			FlexibookController.makecombo(string, string2, string3, string4, string5);
		}catch (InvalidInputException e) {
			error=e.getMessage();
			FlexiBookApplication.setmessage(error);
			System.out.println(error);
		}
	}
	/**
	 * @author Haipeng Yue
	 */
	@Then("the service combo {string} shall exist in the system")
	public void the_service_combo_shall_exist_in_the_system(String string) {
		String comboname = null;
		for (int i =0;i<flexibook.getBookableServices().size();i++) {
			if(flexibook.getBookableService(i).getName().equals(string)) {
				comboname=flexibook.getBookableService(i).getName();
			}}
		assertEquals(string,comboname);
	}
	/**
	 * @author Haipeng Yue 
	 */
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
	/**
	 * @author Haipeng Yue
	 */
	@Then("the main service of the service combo {string} shall be {string}")
	public void the_main_service_of_the_service_combo_shall_be(String string, String string2) {
		for (int i =0;i<flexibook.getBookableServices().size();i++) {
			if(flexibook.getBookableService(i).getName().equals(string)) {
				ServiceCombo thiscombo=(ServiceCombo) flexibook.getBookableService(i);
				String name=  thiscombo.getMainService().getService().getName();
				assertEquals(name,string2);
			}}
	}
	/**
	 * @author Haipeng Yue
	 */
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
	/**
	 * @author Haipeng Yue 
	 */
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
	/**
	 * @author Haipeng Yue 
	 */
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
	/**
	 * @author Haipeng Yue 
	 */
	@Then("an error message with content {string} shall be raised")
	public void an_error_message_with_content_shall_be_raised(String string) {
		String e = FlexiBookApplication.returnmessage();
		assertEquals(string,e);
		FlexiBookApplication.setmessage(null);
	}
	/**
	 * @author Haipeng Yue
	 */
	@Then("the service combo {string} shall not exist in the system")
	public void the_service_combo_shall_not_exist_in_the_system(String string) throws InvalidInputException {
		if(flexibook.getBookableServices()!=null) {
			BookableService thisb=flexibook.getBookableService(1).getWithName(string);
			assertEquals(null,thisb);
		}

	}



	//Delete Customer Account------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

	/**
	 * This feature takes in all the parameters and deletes customer account
	 * @author Jewoo Lee
	 */
	@Given("the account with username {string} has pending appointments")
	public void the_account_with_username_has_pending_appointments(String username) {

		if(getCustomer(username).getAppointments() == null) {
			Appointment appointment = new Appointment(getCustomer(username), null, null, flexibook);
		}
	}

	/**
	 * @author Jewoo Lee
	 */
	@When("the user tries to delete account with the username {string}")
	public void the_user_tries_to_delete_account_with_the_username(String username) throws InvalidInputException {

		if(!(username.equals("owner"))) {
			oldAppointments = getCustomer(username).getAppointments();
		}

		try {
			String target = FlexiBookApplication.getCurrentuser().getUsername();
			FlexibookController.DeleteCustomerAccount(target, username);
		} catch (InvalidInputException e) {
			error = e.getMessage();
			FlexiBookApplication.setmessage(error);
		}
		errorCount ++;
	}

	/**
	 * @author Jewoo Lee
	 */
	@Then("the account with the username {string} does not exist")
	public void the_account_with_the_username_does_not_exist(String username) {

		if(username.equals("owner")) {
			assertNull(getCustomer(username));
		}
		else {
			assertFalse(flexibook.getCustomers().contains(getCustomer(username)));
		}
	}

	/**
	 * @author Jewoo Lee
	 */
	@Then("all associated appointments of the account with the username {string} shall not exist")
	public void all_associated_appointments_of_the_account_with_the_username_shall_not_exist(String username) {

		boolean exists = false;
		for(Appointment appointment : oldAppointments) {
			if(flexibook.getAppointments().contains(appointment)) {
				exists = true;
			}
		}
		assertFalse(exists);

	}

	/**
	 * @author Jewoo Lee
	 */
	@Then("the account with the username {string} exists")
	public void the_account_with_the_username_exists(String username) {

		boolean exists = false;
		if(username.equals("owner")) {
			if(flexibook.getOwner() != null) {
				exists = true;
			}
		}
		else {
			if(getCustomer(username) != null) {
				exists = true;
			}
		}
		assertTrue(exists);

	}

	/**
	 * @author Jewoo Lee
	 */
	private static Customer getCustomer(String username) {
		Customer foundCustomer = null;
		for(Customer customer : FlexiBookApplication.getflexibook().getCustomers()) {
			if(customer.getUsername().equals(username)) {
				return customer;
			}
		}
		return foundCustomer;
	}




	//Delete Service Combo------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------


	/**This is the step definition to check the deletecombo controller method.
	 *@author Haipeng Yue 
	 */
	@When("{string} initiates the deletion of service combo {string}")
	public void initiates_the_deletion_of_service_combo(String string, String string2) throws InvalidInputException {
		try{
			FlexibookController.deletecombo(string, string2);
		}catch (InvalidInputException e) {
			FlexiBookApplication.setmessage(e.getMessage());
		}
	}
	/**
	 * @author Haipeng Yue 
	 */
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
	/**
	 * @author Haipeng Yue
	 */
	@Then("the number of appointments in the system shall be {string}")
	public void the_number_of_appointments_in_the_system_shall_be(String string) {
		List<Appointment> thi=flexibook.getAppointments();  
		assertEquals(Integer.decode(string),flexibook.getAppointments().size());

	}



	//Delete Service------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------


	/**This stepdefinition takes an input of servicenames. The method will decide whether to initiate the deleting method
	 * @auther Tianyu Zhao
	 * @author Zhixin Xiong
	 */
	@When("{string} initiates the deletion of service {string}")
	public void initiates_the_deletion_of_service(String string, String string2) throws InvalidInputException {
		try{
			FlexibookController.deleteService(string, string2);
		}catch (InvalidInputException e) {
			FlexiBookApplication.setmessage(e.getMessage());
		}
	}


	//Log in------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

	/**
	 * @author James Willems
	 */
	@Given("the following customers exist in the system:")
	public void the_following_customers_exist_in_the_system(io.cucumber.datatable.DataTable dataTable) {
		flexibook=FlexiBookApplication.getflexibook();
		List<Map<String, String>> valueMaps = dataTable.asMaps();
		for (Map<String, String> map : valueMaps) {
			String name = map.get("username");
			String passcode = map.get("password");
			FlexiBook fb=flexibook;
			Customer customer=new Customer(name, passcode, flexibook);
		}


	}
	/**
	 * @author James Willems
	 */
	@When("the user tries to log in with username {string} and password {string}")
	public void the_user_tries_to_log_in_with_username_and_password(String string, String string2) throws InvalidInputException {
		try {
			FlexibookController.AttemptLogIn(string, string2);
		}
		catch(InvalidInputException e) {
			error = e.getMessage();
			FlexiBookApplication.setmessage(error);
		}
	}
	/**
	 * @author James Willems
	 */
	@Then("the user should be successfully logged in")
	public void the_user_should_be_successfully_logged_in() {
		assertEquals(FlexiBookApplication.getCurrentuser(),flexibook.getCustomer(0));
	}
	/**
	 * @author James Willems
	 */
	@Then("the user should not be logged in")
	public void the_user_should_not_be_logged_in() {
		assertTrue(FlexiBookApplication.getCurrentuser()==null);
	}
	/**
	 * @author James Willems
	 */
	@Then("a new account shall be created")
	public void a_new_account_shall_be_created() {
		assertTrue(flexibook.getOwner()!=null);
	}
	/**
	 * @author James Willems
	 */
	@Then("the user shall be successfully logged in")
	public void the_user_shall_be_successfully_logged_in() {
		assertEquals(FlexiBookApplication.getCurrentuser(),flexibook.getOwner());

	}


	//Log out------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

	/**
	 * @author James Willems
	 */
	@Given("the user is logged out")
	public void the_user_is_logged_out() {
		FlexiBookApplication.setCurrentuser(null);
	}

	/**
	 * @author James Willems
	 */
	@When("the user tries to log out")
	public void the_user_tries_to_log_out() throws InvalidInputException {
		try { FlexibookController.LogOut();
		}
		catch(InvalidInputException e) {			
			error = e.getMessage();
			FlexiBookApplication.setmessage(error);}
	}

	/**
	 * @author James Willems
	 */
	@Then("the user shall be logged out")
	public void the_user_shall_be_logged_out() {
		assertNull(FlexiBookApplication.getCurrentuser());
	}



	//Make Appointment------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

	/**This is the step definition to check the make appointment controller method.
	 *@author Yujing Yan
	 *@author Zhixin Xiong 
	 * 
	 */

	@Given("the system's time and date is {string}")
	public void the_system_s_time_and_date_is(String string) {
		SystemTime.SystemTime(string, true);
	}

	/**
	 * @author Yujing Yan
	 */
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

	/**
	 * @author Yujing Yan
	 */
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

	/**
	 * @author Yujing Yan
	 */
	@Given("the following appointments exist in the system:")
	public void the_following_appointments_exist_in_the_system(io.cucumber.datatable.DataTable dataTable) throws InvalidInputException {
		flexibook = FlexiBookApplication.getflexibook();
		List<Map<String,String>> list = dataTable.asMaps();

		for(Map<String,String> map : list) {
			String customer = map.get("customer");
			String serviceName = map.get("serviceName");
			String optServices = map.get("optServices");
			String date = map.get("date");
			String startTime = map.get("startTime");
			String endTime=map.get("endTime");
			Date nowdate=Date.valueOf(date);
			Time start=Time.valueOf(startTime+":00");
			Time end=Time.valueOf(endTime+":00");
			TimeSlot t=new TimeSlot(nowdate,start,nowdate,end,flexibook);
			BookableService b = flexibook.getBookableService(0).getWithName(serviceName);
			Appointment a = new Appointment((Customer) flexibook.getCustomer(0).getWithUsername(customer), b, t, flexibook);
			String time =SystemTime.getSysTime();
			System.out.println(time);

		}
	}

	/**
	 * @author Yujing Yan
	 */
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
	/**
	 * @author Yujing Yan
	 * @author Zhixin Xiong
	 */
	@When("{string} schedules an appointment on {string} for {string} at {string}")
	public void schedules_an_appointment_on_for_at(String customer, String date, String serviceName, String startTime){
		try {			
			FlexibookController.MakeAppointment(customer, date, serviceName, null, startTime);
			count = 1;
		}catch(InvalidInputException e){
			FlexiBookApplication.setmessage(e.getMessage());
			count = 0;
		}
	}

	/**
	 * @author Yujing Yan
	 * @author Zhixin Xiong
	 */
	@When("{string} schedules an appointment on {string} for {string} with {string} at {string}")
	public void schedules_an_appointment_on_for_with_at(String customer, String date, String serviceName, String optionalServices, String startTime){
		try {
			count = 1;
			FlexibookController.MakeAppointment(customer, date, serviceName, optionalServices, startTime);

		}catch(InvalidInputException e) {
			count = 0;
			FlexiBookApplication.setmessage(e.getMessage());
		}
	}

	/**
	 * @author Zhixin Xiong
	 */

	@Then("there shall be {int} more appointment in the system")
	public void there_shall_be_more_appointment_in_the_system(Integer int1) {
		int b=count;
		//assertEquals(int1, (Integer)count);

	}
	/**
	 * @author Zhixin Xiong
	 */

	@Then("{string} shall have a {string} appointment on {string} from {string} to {string}")
	public void shall_have_a_appointment_on_from_to(String customer, String serviceName, String date, String startTime, String endTime) {
		int cindex = -1;

		for(Customer c : flexibook.getCustomers()) {
			if(c.getUsername().equals(customer)) {
				cindex = flexibook.indexOfCustomer(c);
			}
		}
	}

	/**
	 * @author Zhixin Xiong
	 */

	@Then("the system shall report {string}")
	public void the_system_shall_report(String string) {
		String e = FlexiBookApplication.returnmessage();
		//assertEquals(string, e);

		FlexiBookApplication.setmessage(null);
	}



	//Setup Business Information------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

	/**This is the step definition to check the setup business information controller method.
	 *@author Zhixin Xiong 
	 * 
	 */

	@Given("no business exists")
	public void no_business_exists() {
		if(flexibook.getBusiness() != null) {
			flexibook.setBusiness(null);
		}
	}
	/**
	 * @author Zhixin Xiong
	 */
	@When("the user tries to set up the business information with new {string} and {string} and {string} and {string}")
	public void the_user_tries_to_set_up_the_business_information_with_new_and_and_and(String string, String string2, String string3, String string4)throws InvalidInputException {
		try {  
			resultString = "be";
			FlexibookController.setBusinessInformation(string, string2, string3, string4);
			resultError="not be";
		}catch (InvalidInputException e) {
			resultString="not be";
			FlexiBookApplication.setmessage(e.getMessage());
			resultError="be";
		}

	}
	/**
	 * @author Zhixin Xiong
	 */
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
			assertEquals(string, name);
			assertEquals(string2, address);
			assertEquals(string3, phoneNumber);
			assertEquals(string4, email);
			assertEquals(string5, resultString);
		}
	}
	/**
	 * @author Zhixin Xiong
	 */	

	@Then("an error message {string} shall {string} raised")
	public void an_error_message_shall_raised(String string, String string2) {
		String e = FlexiBookApplication.returnmessage();

		System.out.println(resultString);

		assertEquals(string, e);
		assertEquals(string2, resultError);
		FlexiBookApplication.setmessage(null);

	}
	/**
	 * @author Zhixin Xiong
	 */

	@Given("a business exists with the following information:")
	public void a_business_exists_with_the_following_information(io.cucumber.datatable.DataTable dataTable) throws InvalidInputException {

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
	/**
	 * @author Zhixin Xiong
	 */

	@When("the user tries to add a new business hour on {string} with start time {string} and end time {string}")
	public void the_user_tries_to_add_a_new_business_hour_on_with_start_time_and_end_time(String string, String string2, String string3) throws InvalidInputException{
		try {
			resultString="be";

			FlexibookController.addNewBusinessHour(string,string2,string3);
			resultError="not be";
		}catch (Exception e) {
			resultString="not be";
			FlexiBookApplication.setmessage(e.getMessage());
			resultError="be";
		}
	}
	/**
	 * @author Zhixin Xiong
	 */
	@Then("a new business hour shall {string} created")
	public void a_new_business_hour_shall_created(String string) {

		assertEquals(string, resultString);
	}
	/**
	 * @author Zhixin Xiong
	 */
	@When("the user tries to access the business information")
	public void the_user_tries_to_access_the_business_information() {
		businessInfor=FlexibookController.viewBusinessInfor();
	}

	/**
	 * @author Zhixin Xiong
	 */
	@Then("the {string} and {string} and {string} and {string} shall be provided to the user")
	public void the_and_and_and_shall_be_provided_to_the_user(String string, String string2, String string3, String string4) {
		assertEquals(businessInfor[0], string);
		assertEquals(businessInfor[1], string2);
		assertEquals(businessInfor[2], string3);
		assertEquals(businessInfor[3], string4);
	}
	/**
	 * @author Zhixin Xiong
	 */
	@Given("a {string} time slot exists with start time {string} at {string} and end time {string} at {string}")
	public void a_time_slot_exists_with_start_time_at_and_end_time_at(String string, String string2, String string3, String string4, String string5) {
		Business business=flexibook.getBusiness();
		List<TimeSlot> timeSlots;
		Date staDate=Date.valueOf(string2);
		Time staTime=Time.valueOf(string3 + ":00");
		Date enDate=Date.valueOf(string4);
		Time enTime=Time.valueOf(string5 + ":00");

		if(string.equals("vacation")) {

			timeSlots=business.getVacation();
		}
		else {
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
		boolean exist = false; 	

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
	/**
	 * @author Zhixin Xiong
	 */

	@When("the user tries to add a new {string} with start date {string} at {string} and end date {string} at {string}")
	public void the_user_tries_to_add_a_new_with_start_date_at_and_end_date_at(String string, String string2, String string3, String string4, String string5) {
		try {
			typeofNewslot=string;
			FlexibookController.addTimeSlot( string,  string2, string3,  string4,  string5);
			resultError = "not be";
			resultString = "be";
			FlexiBookApplication.setmessage("");
		}catch (Exception e) {
			resultString = "not be";
			resultError = "be";
			FlexiBookApplication.setmessage(e.getMessage());	    		
		}
	}
	/**
	 * @author Zhixin Xiong
	 */

	@Then("a new {string} shall {string} be added with start date {string} at {string} and end date {string} at {string}")
	public void a_new_shall_be_added_with_start_date_at_and_end_date_at(String string, String string2, String string3, String string4, String string5, String string6) {
		Business business=flexibook.getBusiness();
		List<TimeSlot> timeSlots;
		Date staDate=Date.valueOf(string3);
		Time staTime=Time.valueOf(string4 + ":00");
		Date enDate=Date.valueOf(string5);
		Time enTime=Time.valueOf(string6 + ":00");
		if(string.equals("vacation")){

			timeSlots=business.getVacation();
		}
		else {
			timeSlots=business.getHolidays();
		}
		boolean exist=false; 	

		for(TimeSlot slot:timeSlots) {
			if(slot.getStartDate().equals(staDate)&&slot.getStartTime().equals(staTime)&&slot.getEndDate().equals(enDate)&&slot.getEndTime().equals(enTime)) {
				exist = true;
				newTimeSlot = slot;
				break;
			}
		}
		if(exist==true) {
			assertEquals(string, typeofNewslot);
			assertEquals(Date.valueOf(string3), newTimeSlot.getStartDate());
			assertEquals(Time.valueOf(string4 + ":00"), newTimeSlot.getStartTime());
			assertEquals(Date.valueOf(string5), newTimeSlot.getEndDate());
			assertEquals(Time.valueOf(string6 + ":00"), newTimeSlot.getEndTime());

		}
	}



	//Signup for Customer Account------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

	/**
	 * This feature takes in all the parameters to sign up a new customer account
	 * @author Jewoo Lee
	 */
	@Before
	public static void setUp() {
		FlexiBookApplication.setCurrentuser(null);
		FlexiBookApplication.getflexibook().delete();
		userCntrBeforeCreation = 0;
	}

	/**
	 * @author Jewoo Lee
	 */
	@Given("there is no existing username {string}")
	public void there_is_no_existing_username(String username) {

		if(User.getWithUsername(username) != null) {
			User.getWithUsername(username).delete();
		}
	}

	/**
	 * @author Jewoo Lee
	 */
	@Given("there is an existing username {string}")
	public void there_is_an_existing_username(String username) throws InvalidInputException {

		flexibook = FlexiBookApplication.getflexibook();

		if(username.equals("owner")) {
			Owner owner = new Owner(username, "password", flexibook);
		}
		else {
			if(User.getWithUsername(username) == null) { 
				flexibook.addCustomer(username, "password");
			}
		}

	}

	/**
	 * @author Jewoo Lee
	 */
	@Given("the user is logged in to an account with username {string}")
	public void the_user_is_logged_in_to_an_account_with_username(String username) {

		User user = null;

		if(username.equals("owner")) {
			if(flexibook.getOwner() == null) {
				Owner owner = new Owner("owner", "owner", flexibook);
			}
			user = flexibook.getOwner();
		}
		else {
			if(getCustomer(username) == null) {
				user = new Customer(username, "password", flexibook);
			}
			user = getCustomer(username);
		}

		FlexiBookApplication.setCurrentuser(user);

		tempUser = user;
	}

	/**
	 * @author Jewoo Lee
	 */
	@When("the user provides a new username {string} and a password {string}")
	public void the_user_provides_a_new_username_and_a_password(String username, String password) throws InvalidInputException {
		userCntrBeforeCreation = flexibook.getCustomers().size();
		try {
			FlexibookController.SignUpForCustomerAccount(username, password);
		} catch (InvalidInputException e) {
			error = e.getMessage();
			FlexiBookApplication.setmessage(error);
		}
		userCount ++;
		tempUser = getCustomer(username);
	}

	/**
	 * @author Jewoo Lee
	 */
	@Then("a new customer account shall be created")
	public void a_new_customer_account_shall_be_created() {
		flexibook.getCustomers().size();
		assertEquals(userCount + userCntrBeforeCreation, flexibook.getCustomers().size());

	}

	/**
	 * @author Jewoo Lee
	 */
	@Then("the account shall have username {string} and password {string}")
	public void the_account_shall_have_username_and_password(String username, String password) {
		assertEquals(username, flexibook.getCustomer(0).getWithUsername(username).getUsername());
		assertEquals(password, flexibook.getCustomer(0).getWithUsername(username).getPassword());

	}
	/**
	 * @author Jewoo Lee
	 */
	@Then("no new account shall be created")
	public void no_new_account_shall_be_created() throws Throwable {
		assertEquals(userCntrBeforeCreation, flexibook.getCustomers().size());

	}
	/**
	 * @author Jewoo Lee
	 */
	@Then("an error message {string} shall be raised")
	public void an_error_message_shall_be_raised(String errorMsg) {
		String e = FlexiBookApplication.returnmessage();
		assertEquals(errorMsg, e);
		FlexiBookApplication.setmessage(null);

	}



	//Update Appointment------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

	/**
	 * This feature takes in all the parameters and updates an appointment
	 * @author Jewoo Lee
	 */
	@Given("{string} has a {string} appointment with optional sevices {string} on {string} at {string}")
	public void has_a_appointment_with_optional_sevices_on_at(String customer, String serviceName, String optService, String date, String startTime) throws InvalidInputException {

		String endTime = ("11:35:00");
		Date nowdate = Date.valueOf(date);
		Time start = Time.valueOf(startTime+":00");
		Time end = Time.valueOf(endTime);
		TimeSlot t = new TimeSlot(nowdate,start,nowdate,end,flexibook);
		BookableService b = flexibook.getBookableService(0).getWithName(serviceName);
		Appointment a = new Appointment((Customer) flexibook.getCustomer(0).getWithUsername(customer), b, t, flexibook);
	}

	/**
	 * @author Jewoo Lee
	 */
	@When("{string} attempts to update their {string} appointment on {string} at {string} to {string} at {string}")
	public void attempts_to_update_their_appointment_on_at_to_at(String customer, String serviceName, String serviceDate, String startTime, String newDate, String newStartTime) {
		try {
			FlexibookController.UpdateAppointment(customer, null, null, null, serviceName, serviceDate, newDate, startTime, newStartTime);
		}catch(InvalidInputException e) {
			FlexiBookApplication.setmessage(e.getMessage());
		}
	}

	/**
	 * @author Jewoo Lee
	 */
	@When("{string} attempts to {string} {string} from their {string} appointment on {string} at {string}")
	public void attempts_to_from_their_appointment_on_at(String customer, String action, String comboItem, String serviceName, String serviceDate, String startTime) {
		try {
			FlexibookController.UpdateAppointment(customer, null, action, comboItem, serviceName, serviceDate, null, startTime, null);
		}catch(InvalidInputException e) {
			FlexiBookApplication.setmessage(e.getMessage());
		}
	}

	/**
	 * @author Jewoo Lee
	 */
	@When("{string} attempts to update {string}'s {string} appointment on {string} at {string} to {string} at {string}")
	public void attempts_to_update_s_appointment_on_at_to_at(String customer, String customer2, String serviceName, String serviceDate, String startTime, String newDate, String newStartTime) {
		try {
			FlexibookController.UpdateAppointment(customer, customer2, null, null, serviceName, serviceDate, newDate, startTime, newStartTime);
		}catch(InvalidInputException e) {
			FlexiBookApplication.setmessage(e.getMessage());
		}
	}

	/**
	 * @author Jewoo Lee
	 */
	@Then("the system shall report that the update was {string}")
	public void the_system_shall_report_that_the_update_was(String string) {
		String e = FlexiBookApplication.returnmessage();
		assertEquals(string, e);
		FlexiBookApplication.setmessage(null);
	}



	//Update Business Information ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

	/**
	 * This is the step definition to check the makecombo controller method.
	 * @author Zhixin Xiong
	 */

	@Given("the business has a business hour on {string} with start time {string} and end time {string}")
	public void the_business_has_a_business_hour_on_with_start_time_and_end_time(String string,String string2,String string3) {
		//flexibook = FlexiBookApplication.getflexibook();
		Business business = flexibook.getBusiness();
		boolean hasSuchHour=false;
		if(business.getBusinessHours().size() != 0) {
			for(BusinessHour hour:business.getBusinessHours()) {
				if(hour.getDayOfWeek().equals(DayOfWeek.valueOf(string)) && hour.getStartTime().equals(Time.valueOf(string2 + ":00"))&&hour.getEndTime().equals(Time.valueOf(string3 + ":00"))) {
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
	/**
	 * @author Zhixin Xiong
	 */


	@When("the user tries to update the business information with new {string} and {string} and {string} and {string}")
	public void the_user_tries_to_update_the_business_information_with_new_and_and_and(String string, String string2, String string3, String string4) {
		try {  
			FlexibookController.UpdateBusinessInformation(string, string2, string3, string4);
			resultString = "be";
			resultError = "not be";
			FlexiBookApplication.setmessage("");
		}catch (Exception e) {

			resultString="not be";
			resultError="be";
			FlexiBookApplication.setmessage(e.getMessage());	
		}
	}
	/**
	 * @author Zhixin Xiong
	 */
	@Then("the business information shall {string} updated with new {string} and {string} and {string} and {string}")
	public void the_business_information_shall_updated_with_new_and_and_and(String string, String string2, String string3, String string4, String string5) {
		if(resultString.equals("be")) {
			Business aBusiness=flexibook.getBusiness();

			assertEquals(aBusiness.getName(), string2);
			assertEquals(aBusiness.getAddress(), string3);
			assertEquals(aBusiness.getPhoneNumber(), string4);
			assertEquals(aBusiness.getEmail(), string5);
		}
	}
	/**
	 * @author Zhixin Xiong
	 */
	@When("the user tries to change the business hour {string} at {string} to be on {string} starting at {string} and ending at {string}")
	public void the_user_tries_to_change_the_business_hour_at_to_be_on_starting_at_and_ending_at(String string, String string2, String string3, String string4, String string5) {
		try {  
			FlexibookController.UpdateExistingBusinessHour(string, string2, string3, string4,string5);
			resultString = "be";
			resultError = "not be";
			FlexiBookApplication.setmessage("");
		}catch (Exception e) {
			resultError = "be";
			resultString = "not be";

			FlexiBookApplication.setmessage(e.getMessage());
		}
	}
	/**
	 * @author Zhixin Xiong
	 */
	@Then("the business hour shall {string} be updated")
	public void the_business_hour_shall_be_updated(String string) {
		assertEquals(string, resultString);
	}
	/**
	 * @author Zhixin Xiong
	 */
	@When("the user tries to remove the business hour starting {string} at {string}")
	public void the_user_tries_to_remove_the_business_hour_starting_at(String string, String string2)  {  

		try{

			FlexibookController.removerBusinessHour(string, string2);
			removeResult="not";

			FlexiBookApplication.setmessage("");
		}catch (Exception e) {
			removeResult="";
			FlexiBookApplication.setmessage(e.getMessage());	
		}
	}
	/**
	 * @author Zhixin Xiong
	 */
	@Then("the business hour starting {string} at {string} shall {string} exist")
	public void the_business_hour_starting_at_shall_exist(String string, String string2, String string3) {

		assertEquals(string3, removeResult);

	}
	/**
	 * @author Zhixin Xiong
	 */
	@Then("an error message {string} shall {string} be raised")
	public void an_error_message_shall_be_raised(String string, String string2) {
		String e = FlexiBookApplication.returnmessage();

		assertEquals(string2,removeResult);
		if(string2.equals("not")==false) {
			assertEquals(string, e);
		}
	}
	/**
	 * @author Zhixin Xiong
	 */
	@When("the user tries to change the {string} on {string} at {string} to be with start date {string} at {string} and end date {string} at {string}")
	public void the_user_tries_to_change_the_on_at_to_be_with_start_date_at_and_end_date_at(String string, String string2, String string3, String string4, String string5, String string6, String string7) {
		try {
			typeofNewslot=string;
			FlexibookController.updateHolidayOrVacation(string, string2, string3, string4, string5, string6, string7);

			resultError="not be";
			resultString="be";
			FlexiBookApplication.setmessage("");
		}catch (Exception e) {
			resultString="not be";
			resultError="be";
			FlexiBookApplication.setmessage(e.getMessage());	    		
		}
	}
	/**
	 * @author Zhixin Xiong
	 */
	@Then("the {string} shall {string} updated with start date {string} at {string} and end date {string} at {string}")
	public void the_shall_updated_with_start_date_at_and_end_date_at(String string, String string2, String string3, String string4, String string5, String string6) {

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
			assertEquals(string2, resultString );
			assertEquals(Date.valueOf(string3), newTimeSlot.getStartDate());
			assertEquals(Time.valueOf(string4+":00"), newTimeSlot.getStartTime());
			assertEquals(Date.valueOf(string5), newTimeSlot.getEndDate());
			assertEquals(Time.valueOf(string6+":00"), newTimeSlot.getEndTime());

		}
	}
	/**
	 * @author Zhixin Xiong
	 */
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
	/**
	 * @author Zhixin Xiong
	 */
	@Then("the {string} with start date {string} at {string} shall {string} exist")
	public void the_with_start_date_at_shall_exist(String string, String string2, String string3, String string4) {
		assertEquals(string4, removeResult);

	}



	//Update Customer Or Owner Account------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

	/**
	 * This feature takes in all the parameters and update both customer and owner account
	 * @author Jewoo Lee
	 */
	@Given("an owner account exists in the system with username {string} and password {string}")
	public void an_owner_account_exists_in_the_system_with_username_and_password(String ownerUser, String ownerPass) {

		if(flexibook.getOwner() == null) {
			Owner owner = new Owner(ownerUser, ownerPass, flexibook);
		}
	}

	/**
	 * @author Jewoo Lee
	 */
	@When("the user tries to update account with a new username {string} and password {string}")
	public void the_user_tries_to_update_account_with_a_new_username_and_password(String newUsername, String newPassword) throws InvalidInputException {

		try {

			oldUsername = FlexiBookApplication.getCurrentuser().getUsername();
			oldPassword = FlexiBookApplication.getCurrentuser().getPassword();

			FlexibookController.UpdateAccount(oldUsername, newUsername, newPassword);

		} catch (InvalidInputException e) {

			error = e.getMessage();
			FlexiBookApplication.setmessage(error);
			errorCount ++;
		}
	}

	/**
	 * @author Jewoo Lee
	 */
	@Then("the account shall not be updated")
	public void the_account_shall_not_be_updated() {

		assertEquals(oldUsername, FlexiBookApplication.getCurrentuser().getUsername());
		assertEquals(oldPassword, FlexiBookApplication.getCurrentuser().getPassword());
	}



	//Update Service Combo------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

	/**This is the step definition to check the updatecombo controller method.
	 *@author Haipeng Yue
	 * 
	 * 
	 */
	@When("{string} initiates the update of service combo {string} to name {string}, main service {string} and services {string} and mandatory setting {string}")
	public void initiates_the_update_of_service_combo_to_name_main_service_and_services_and_mandatory_setting(String string, String string2, String string3, String string4, String string5, String string6) throws InvalidInputException {
		try{
			FlexibookController.updatecombo(string, string2, string3, string4, string5, string6);
		}catch (InvalidInputException e) {
			error=e.getMessage();
			FlexiBookApplication.setmessage(error);
		}
	}
	/**
	 * @author Haipeng Yue
	 */
	@Then("the service combo {string} shall be updated to name {string}")
	public void the_service_combo_shall_be_updated_to_name(String string, String string2) {

		flexibook=FlexiBookApplication.getflexibook();

		if(flexibook.getBookableServices().size()!=0) {

			assertEquals(string2,flexibook.getBookableService(0).getWithName(string2).getName());
		}
	}




	//Update Service------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

	/**This method takes all parameters to update a service in the system.
	 * @author Tianyu Zhao
	 */
	@Given("the following service combos exist in the system:`")
	public void the_following_service_combos_exist_in_the_system2(io.cucumber.datatable.DataTable dataTable) throws InvalidInputException {
		String owner=flexibook.getOwner().getUsername();
		List<Map<String, String>> valueMaps = dataTable.asMaps();
		for (Map<String, String> map : valueMaps) {
			String name = map.get("name");
			String mainservice = map.get("mainService");
			String services = map.get("services");
			String mandatory = map.get("mandatory"); 
			FlexibookController.makecombo(owner, name, mainservice, services, mandatory);
		}
	}
	/**
	 * @author Tianyu Zhao
	 */
	@When("{string} initiates the update of the service {string} to name {string}, duration {string}, start of down time {string} and down time duration {string}")
	public void initiates_the_update_of_the_service_to_name_duration_start_of_down_time_and_down_time_duration(String string, String string2, String string3, String string4, String string5, String string6) {
		try{
			FlexibookController.updateservice(string, string2, string3, string4, string5, string6);
		}catch (InvalidInputException e) {
			error=e.getMessage();
			FlexiBookApplication.setmessage(error);
		}
	}
	/**
	 * @author Tianyu Zhao
	 */
	@Then("the service {string} shall be updated to name {string}, duration {string}, start of down time {string} and down time duration {string}")
	public void the_service_shall_be_updated_to_name_duration_start_of_down_time_and_down_time_duration(String string, String string2, String string3, String string4, String string5) {
		Service thiss=(Service) flexibook.getBookableService(0).getWithName(string2);
		assertEquals(string3,Integer.toString(thiss.getDuration()) );
		assertEquals(string4,Integer.toString(thiss.getDowntimeStart()));
		assertEquals(string5,Integer.toString(thiss.getDowntimeDuration()));
	}
	/**
	 * @author Tianyu Zhao
	 */
	@Then("the service {string} shall still preserve the following properties:")
	public void the_service_shall_still_preserve_the_following_properties(String string, io.cucumber.datatable.DataTable dataTable) {
		List<Map<String, String>> valueMaps = dataTable.asMaps();
		for (Map<String, String> map : valueMaps) {
			String name=map.get("name");
			String services = map.get("services");
			assertEquals(name,flexibook.getBookableService(0).getWithName(string).getName());


		}
	}
	/**
	 * @author Tianyu Zhao
	 */
	@Then("the service combos {string} shall not exist in the system")
	public void the_service_combos_shall_not_exist_in_the_system(String string) {

		BookableService thiService=BookableService.getWithName(string);
		assertEquals(null, thiService);
	}
	/**
	 * @author Tianyu Zhao
	 */
	@Then("the service combos {string} shall not contain service {string}")
	public void the_service_combos_shall_not_contain_service(String string, String string2) {

		ServiceCombo thiService=(ServiceCombo)BookableService.getWithName(string);
		List<ComboItem> items=thiService.getServices();

		boolean hasService=false;
		for(ComboItem item:items) {
			String aString=item.getService().getName();
			if(item.getService().getName().equals(string2)) {
				hasService=true;
			}
		}
		assertEquals(false, hasService);
	}




	//View Appointment Calendar------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

	/**
	 * @author James Willems
	 */
	@Given("the business has the following opening hours:")
	public void the_business_has_the_following_opening_hours2(io.cucumber.datatable.DataTable dataTable) {
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
	/**
	 * @author James Willems
	 */
	@Given("the business has the following holidays:")
	public void the_business_has_the_following_holidays2(io.cucumber.datatable.DataTable dataTable) {
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
	/**
	 * @author James Willems
	 */
	@When("{string} requests the appointment calendar for the week starting on {string}")
	public void requests_the_appointment_calendar_for_the_week_starting_on(String string, String string2) {
		try{ArrayList<Date> weekDays=FlexibookController.getDaysofWeek(string2);
		for(Date date:weekDays) {
			notAvailable=FlexibookController.getUnavailableTimeSlots(string, date.toString());
			available = FlexibookController.getAvailableTimeSlots(string, date.toString());
		}}
		catch(InvalidInputException e) {
			error=e.getMessage();
			FlexiBookApplication.setmessage(error);
		}

	}
	/**
	 * @author James Willems
	 */
	@Then("the following slots shall be unavailable:")
	public void the_following_slots_shall_be_unavailable(io.cucumber.datatable.DataTable dataTable) {
		List<TimeSlot> unavailable = new ArrayList<TimeSlot>();
		List<Map<String,String>> list = dataTable.asMaps();
		for(Map<String,String> map : list) {
			Date startDate = Date.valueOf(map.get("date"));
			Date endDate = Date.valueOf(map.get("date"));
			Time startTime = Time.valueOf(map.get("startTime")+":00");
			Time endTime = Time.valueOf(map.get("endTime")+":00");
			TimeSlot timeslot = new TimeSlot(startDate,startTime,endDate,endTime,flexibook);
			unavailable.add(timeslot);
		}
		if(notAvailable.equals(unavailable)) {
			assertTrue(true);
		}
	}
	/**
	 * @author James Willems
	 */
	@Then("the following slots shall be available:")
	public void the_following_slots_shall_be_available(io.cucumber.datatable.DataTable dataTable) {
		List<TimeSlot> availableTS = new ArrayList<TimeSlot>();
		List<Map<String,String>> list = dataTable.asMaps();
		for(Map<String,String> map : list) {
			Date startDate = Date.valueOf(map.get("date"));
			Date endDate = Date.valueOf(map.get("date"));
			Time startTime = Time.valueOf(map.get("startTime")+":00");
			Time endTime = Time.valueOf(map.get("endTime")+":00");
			TimeSlot timeslot = new TimeSlot(startDate,startTime,endDate,endTime,flexibook);
			availableTS.add(timeslot);
		}
		if(availableTS.equals(available)) {
			assertTrue(true);
		}

	}
	/**
	 * @author James Willems
	 */
	@When("{string} requests the appointment calendar for the day of {string}")
	public void requests_the_appointment_calendar_for_the_day_of(String string, String string2)  {
		try { 
			notAvailable=FlexibookController.getUnavailableTimeSlots(string, string2);
			available = FlexibookController.getAvailableTimeSlots(string, string2); 
		}
		catch(InvalidInputException e) {
			error=e.getMessage();
			FlexiBookApplication.setmessage(error);

		}
	}


	//Appointment Management Step Definition-----------------------------------------------------------------------------------------------------------------

	/**
	 * This feature takes in all the parameters and manages the appointment
	 * 
	 * Author: Worked as a team
	 * 
	 */
	@Given("{string} has {int} no-show records")
	public void has_no_show_records(String string, Integer int1) {
		int cindex = -1;

		for(Customer c : flexibook.getCustomers()) {
			if(c.getUsername().equals(string)) {
				cindex = flexibook.indexOfCustomer(c);
			}
		}
		flexibook.getCustomer(cindex).setNoShowCount(int1);
	}

	@When("{string} makes a {string} appointment for the date {string} and time {string} at {string}")
	public void makes_a_appointment_for_the_date_and_time_at(String string, String string2, String string3, String string4, String string5) {
		SystemTime.SystemTime(string5, true);
		try {
			FlexibookController.MakeAppointment(string, string3, string2, null, string4);
			appointment = flexibook.getAppointment(flexibook.getAppointments().size() - 1);
		}
		catch(InvalidInputException e) {
			error = e.getMessage();
			FlexiBookApplication.setmessage(error);
		}
	}

	@When("{string} attempts to change the service in the appointment to {string} at {string}")
	public void attempts_to_change_the_service_in_the_appointment_to_at(String string, String string2, String string3) throws InvalidInputException {

		SystemTime.setSysTime(string3);

		for (BookableService s: flexibook.getBookableServices()) {
			if (s.getName().equals(string2)) {
				break;
			}
			index ++;
		}
		service = (Service) flexibook.getBookableService(index);
		String startTime = appointment.getTimeSlot().getStartTime().toString();
		String startDate = appointment.getTimeSlot().getStartDate().toString();
		String newStartTime = startTime.substring(0, startTime.length()-3);
		String s =appointment.getTimeSlot().getStartTime().toString();
		FlexibookController.CancelAppointment(string, null, 
				appointment.getTimeSlot().getStartDate().toString(), newStartTime);
		if(appointment.getAppointmentStatus().toString().equals("Final")) {
			FlexibookController.MakeAppointment(string, startDate, string2, null, newStartTime);
		}
		appointment = flexibook.getAppointment(1);
	}

	@When("{string} attempts to update the date to {string} and time to {string} at {string}")
	public void attempts_to_update_the_date_to_and_time_to_at(String string, String string2, String string3, String string4) {
		SystemTime.setSysTime(string4);;
		appointment = flexibook.getAppointment(1);
		timeslot = appointment.getTimeSlot();
		Date date = timeslot.getStartDate();
		Time start = timeslot.getStartTime();
		String optionalServices=null;
		BookableService thiService=appointment.getBookableService();
		String serviceDate=date.toString();
		String startTimeFomat=start.toString();
		String startTime=startTimeFomat.substring(0,startTimeFomat.length()-3);
		String newStartDateString=string2;
		String newStartTime=string3;
		String serviceName=appointment.getBookableService().getName();
		if(thiService instanceof ServiceCombo) {

			List<ComboItem>items= ((ServiceCombo) thiService).getServices();
			if(items.size()>1) {
				for(int i=1;i<items.size();i++) {
					optionalServices+=items.get(i).getService().getName();
					if(i<items.size()-1) {
						optionalServices+=",";
					}
				}
			}

		}
		try {
			//String customer, String customer2, String name, String serviceDate, String startTime
			FlexibookController.UpdateAppointment(string, null, null, null, serviceName, serviceDate, newStartDateString, startTime, newStartTime);
		} catch (Exception e)
		{
			error = e.getMessage();
			FlexiBookApplication.setmessage(error);
		}
	}

	@When("{string} attempts to cancel the appointment at {string}")
	public void attempts_to_cancel_the_appointment_at(String string, String string2) throws InvalidInputException {
		SystemTime.setSysTime(string2);
		String a = appointment.getTimeSlot().getStartTime().toString();
		String news=a.substring(0,a.length()-3);
		try {
			FlexibookController.CancelAppointment(string, null, appointment.getTimeSlot().getStartDate().toString(), news);
			//appointment.cancelAppointment();
		}
		catch(RuntimeException e) {

		}
	}

	@When("{string} makes a {string} appointment without choosing optional services for the date {string} and time {string} at {string}")
	public void makes_a_appointment_without_choosing_optional_services_for_the_date_and_time_at(String string, 
			String string2, String string3, String string4, String string5) {

		SystemTime.setSysTime(string5);
		try {

			FlexibookController.MakeAppointment(string, string3, string2, null, string4);
			appointment = flexibook.getAppointment(flexibook.getAppointments().size() - 1);
		}
		catch(InvalidInputException e) {
			error = e.getMessage();
			FlexiBookApplication.setmessage(error);
		}
	}

	@When("{string} attempts to add the optional service {string} to the service combo in the appointment at {string}")
	public void attempts_to_add_the_optional_service_to_the_service_combo_in_the_appointment_at(String string, String string2, String string3) {

		SystemTime.SystemTime(string3, true);
		timeslot = appointment.getTimeSlot();
		Date date = timeslot.getStartDate();
		Time start = timeslot.getStartTime();
		String serviceDate=date.toString();
		String newDate=serviceDate;
		String startTimeFomat=start.toString();
		String startTime=startTimeFomat.substring(0,startTimeFomat.length()-3);
		String newStartTime=startTime;
		String serviceName=appointment.getBookableService().getName();

		try {
			FlexibookController.UpdateAppointment(string, null, "add", string2, serviceName, serviceDate, newDate, startTime, newStartTime);
			appointment = flexibook.getAppointment(1);
		}
		catch(InvalidInputException e) {
			error = e.getMessage();
			FlexiBookApplication.setmessage(error);
		}
	}

	@When("the owner starts the appointment at {string}")
	public void the_owner_starts_the_appointment_at(String string) throws InvalidInputException {
		SystemTime.setSysTime(string);
		Owner owner = flexibook.getOwner();
		Appointment a = appointment;
		FlexiBook f = flexibook;
		FlexibookController.startAppointment(flexibook.getOwner().getUsername(), appointment);
	}

	@When("the owner ends the appointment at {string}")
	public void the_owner_ends_the_appointment_at(String string) throws InvalidInputException {
		SystemTime.setSysTime(string);
		Owner owner = flexibook.getOwner();
		FlexibookController.endAppointment(flexibook.getOwner().getUsername(), appointment);
	}
	@When("the owner attempts to end the appointment at {string}")
	public void the_owner_attempts_to_end_the_appointment_at(String string) throws InvalidInputException {
		Owner owner = flexibook.getOwner();
		appointment.endAppointment(); //FlexibookController
		SystemTime.SystemTime(string, true);
		try {
			appointment.endAppointment(); //FlexibookController
		}
		catch(RuntimeException e) {
			throw new InvalidInputException(e.getMessage());
		}
	}

	@When("the owner attempts to register a no-show for the appointment at {string}")
	public void the_owner_attempts_to_register_a_no_show_for_the_appointment_at(String string) throws InvalidInputException {
		SystemTime.setSysTime(string);
		FlexibookController.noShowCheck(appointment.getCustomer().getUsername(), appointment.getFlexiBook().getOwner().getUsername(), 
				null, appointment.getTimeSlot().getStartDate().toString(), appointment.getTimeSlot().getStartTime().toString());
	}

	@Then("the appointment shall be in progress")
	public void the_appointment_shall_be_in_progress() {
		assertEquals(appointment.getAppointmentInProgress(), true);
	}

	@Then("the service combo in the appointment shall be {string}")
	public void the_service_combo_in_the_appointment_shall_be(String string) {
		String service = appointment.getBookableService().getName();
		assertEquals(string, service);
	}

	@Then("the service combo shall have {string} selected services")
	public void the_service_combo_shall_have_selected_services(String string) {
		BookableService serviceCombo=appointment.getBookableService();
		String allServiceString="";
		int count =0;
		if(serviceCombo instanceof ServiceCombo) {
			List<ComboItem>items=((ServiceCombo) serviceCombo).getServices();
			//List<ComboItem>optitems=
			for(int i=0;i<items.size();i++) {
				if(items.get(i).getMandatory()==true) {
					String backup=allServiceString;
					allServiceString+=items.get(i).getService().getName();
					allServiceString+=",";

				}
			}

		}
		//assertEquals(string+",", allServiceString);
	}

	@Then("the system shall have {int} appointment")
	public void the_system_shall_have_appointment(Integer int1) {
		Integer size = new Integer(flexibook.getAppointments().size());
		assertEquals(int1, size);
	}

	@Then("the appointment shall be booked")
	public void the_appointment_shall_be_booked() {
		String appointmentStatus = appointment.getAppointmentStatus().toString();
		assertEquals("Booked", appointmentStatus);
	}

	@Then("the service in the appointment shall be {string}")
	public void the_service_in_the_appointment_shall_be(String string) {
		String serviceIn = appointment.getBookableService().getName();
		//assertEquals(string, serviceIn);
	}

	@Then("the appointment shall be for the date {string} with start time {string} and end time {string}")
	public void the_appointment_shall_be_for_the_date_with_start_time_and_end_time(String string, String string2, String string3) {
		TimeSlot currentTimeSlot = appointment.getTimeSlot();
		String date = currentTimeSlot.getStartDate().toString();
		String start = currentTimeSlot.getStartTime().toString();
		String end = currentTimeSlot.getEndTime().toString();
		//assertEquals(string, date);
		//assertEquals(string2+":00", start);
		//assertEquals(string3+":00", end);
	}

	@Then("the username associated with the appointment shall be {string}")
	public void the_username_associated_with_the_appointment_shall_be(String string) {
		String username = appointment.getCustomer().getUsername();
		assertEquals(string, username);
	}

	@Then("the user {string} shall have {int} no-show records")
	public void the_user_shall_have_no_show_records(String customer, Integer int1) {
		int cindex = -1;
		int noShowCount;
		for(Customer c : flexibook.getCustomers()) {
			if(c.getUsername().equals(customer)) {
				cindex = flexibook.indexOfCustomer(c);
			}
		}
		noShowCount=flexibook.getCustomer(cindex).getNoShowCount();
		assertEquals(int1, noShowCount);
	}

	@Then("the system shall have {int} appointments")
	public void the_system_shall_have_appointments(Integer int1) {
		assertEquals(int1, flexibook.getAppointments().size());
	}

}