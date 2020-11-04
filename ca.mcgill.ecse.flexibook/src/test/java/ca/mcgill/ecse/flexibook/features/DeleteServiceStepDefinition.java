//package ca.mcgill.ecse.flexibook.features;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import java.util.List;
//import ca.mcgill.ecse.flexibook.Controller.FlexibookController;
//import ca.mcgill.ecse.flexibook.Controller.InvalidInputException;
//import ca.mcgill.ecse.flexibook.application.FlexiBookApplication;
//import ca.mcgill.ecse.flexibook.model.Appointment;
//import ca.mcgill.ecse.flexibook.model.FlexiBook;
//import ca.mcgill.ecse.flexibook.util.SystemTime;
//import io.cucumber.java.After;
//import io.cucumber.java.en.Given;
//import io.cucumber.java.en.Then;
//import io.cucumber.java.en.When;
//
//public class DeleteServiceStepDefinition {
//	private FlexiBook flexibook=FlexiBookApplication.getflexibook();
//
//	@When("{string} initiates the deletion of service {string}")
//	public void initiates_the_deletion_of_service(String string, String string2) throws InvalidInputException {
//		try{
//			FlexibookController.deleteService(string, string2);
//		}catch (InvalidInputException e) {
//			FlexiBookApplication.setmessage(e.getMessage());
//		}
//	}
//
//	@After
//	public void tearDown() {
//		flexibook=FlexiBookApplication.getflexibook();
//		flexibook.delete();
//	}
//}
