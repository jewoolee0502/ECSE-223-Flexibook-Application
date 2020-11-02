//package ca.mcgill.ecse.flexibook.features;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertTrue;
//import static org.junit.jupiter.api.Assertions.fail;
//
//import java.io.File;
//
//import ca.mcgill.ecse.flexibook.Controller.FlexibookController;
//import ca.mcgill.ecse.flexibook.Controller.InvalidInputException;
//import ca.mcgill.ecse.flexibook.application.FlexiBookApplication;
//import ca.mcgill.ecse.flexibook.model.FlexiBook;
//import ca.mcgill.ecse.flexibook.model.Service;
//import io.cucumber.java.After;
//import io.cucumber.java.Before;
//import io.cucumber.java.en.Given;
//import io.cucumber.java.en.Then;
//import io.cucumber.java.en.When;
//
//public class CucumberStepDefinitions {
//
//	private  FlexiBook flexibook=FlexiBookApplication.getflexibook();
//	private  InvalidInputException thise=null;
//	@When("{string} initiates the addition of the service {string} with duration {string}, start of down time {string} and down time duration {string}")
//	public void initiates_the_addition_of_the_service_with_duration_start_of_down_time_and_down_time_duration(String string, String string2, String string3, String string4, String string5) {
//		try{
//			FlexibookController.addService(string, string2, string3,string4,string5);
//		}catch (InvalidInputException e) {
//			FlexiBookApplication.setmessage(e.getMessage());
//		}
//	}
//	@Then("the service {string} shall exist in the system")
//	public void the_service_shall_exist_in_the_system(String string) {
//		assertEquals(string,flexibook.getBookableService(0).getWithName(string).getName());
//	}
//	@Then("the service {string} shall have duration {string}, start of down time {string} and down time duration {string}")
//	public void the_service_shall_have_duration_start_of_down_time_and_down_time_duration(String string, String string2, String string3, String string4) {
//		Service thiss=(Service) flexibook.getBookableService(0).getWithName(string);
//		assertEquals(string2,Integer.toString(thiss.getDuration()) );
//		assertEquals(string3,Integer.toString(thiss.getDowntimeStart()));
//		assertEquals(string4,Integer.toString(thiss.getDowntimeDuration()));
//
//	}
//	@Then("the number of services in the system shall be {string}")
//	public void the_number_of_services_in_the_system_shall_be(String string) {
//		assertEquals(string,Integer.toString(flexibook.getBookableServices().size()));
//	}
//	@Then("the service {string} shall not exist in the system")
//	public void the_service_shall_not_exist_in_the_system(String string) {
//		if (flexibook.getBookableServices().size()!=0) {
//			assertEquals(null,flexibook.getBookableService(0).getWithName(string));   
//		}else {
//			assertEquals(true,true);
//		}
//	}
//	@After
//	public void tearDown() {
//		flexibook=FlexiBookApplication.getflexibook();
//		flexibook.delete();
//	}
//
//}
//
