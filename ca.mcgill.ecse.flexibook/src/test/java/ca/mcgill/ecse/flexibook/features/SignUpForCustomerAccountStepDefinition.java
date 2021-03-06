//package ca.mcgill.ecse.flexibook.features;
//
//import static org.junit.Assert.assertEquals;
//
//import java.util.List;
//import java.util.Map;
//
//import org.junit.Before;
//
//import ca.mcgill.ecse.flexibook.Controller.FlexibookController;
//import ca.mcgill.ecse.flexibook.Controller.InvalidInputException;
//import ca.mcgill.ecse.flexibook.application.FlexiBookApplication;
//import ca.mcgill.ecse.flexibook.model.Customer;
//import ca.mcgill.ecse.flexibook.model.FlexiBook;
//import ca.mcgill.ecse.flexibook.model.Owner;
//import ca.mcgill.ecse.flexibook.model.User;
//import io.cucumber.java.en.Given;
//import io.cucumber.java.en.Then;
//import io.cucumber.java.en.When;
//
//public class SignUpForCustomerAccountStepDefinition {
//
//	private FlexiBook flexibook = FlexiBookApplication.getflexibook();
//
//	private String error;
//	private int errorCntr = 0;
//	private int userCount = 0;
//
//	private User tempUser;
//
//	private static int userCntrBeforeCreation;
//
//	@Before
//	public static void setUp() {
//		FlexiBookApplication.setCurrentuser(null);
//		FlexiBookApplication.getflexibook().delete();
//		userCntrBeforeCreation = 0;
//	}
//
//	@Given("there is no existing username {string}")
//	public void there_is_no_existing_username(String username) {
//
//		if(User.getWithUsername(username) != null) {
//			User.getWithUsername(username).delete();
//		}
//	}
//
//	@Given("there is an existing username {string}")
//	public void there_is_an_existing_username(String username) throws InvalidInputException {
//
//		flexibook = FlexiBookApplication.getflexibook();
//
//		if(username.equals("owner")) {
//			Owner owner = new Owner(username, "password", flexibook);
//		}
//		else {
//			if(User.getWithUsername(username) == null) { 
//				flexibook.addCustomer(username, "password");
//			}
//		}
//
//	}
//
//	@Given("the user is logged in to an account with username {string}")
//	public void the_user_is_logged_in_to_an_account_with_username(String username) {
//
//		User user = null;
//
//		if(username.equals("owner")) {
//			if(flexibook.getOwner() == null) {
//				Owner owner = new Owner("owner", "owner", flexibook);
//			}
//			user = flexibook.getOwner();
//		}
//		else {
//			if(getCustomer(username) == null) {
//				user = new Customer(username, "password", flexibook);
//			}
//			user = getCustomer(username);
//		}
//
//		FlexiBookApplication.setCurrentuser(user);
//
//		tempUser = user;
//	}
//
//
//	@When("the user provides a new username {string} and a password {string}")
//	public void the_user_provides_a_new_username_and_a_password(String username, String password) throws InvalidInputException {
//		userCntrBeforeCreation = flexibook.getCustomers().size();
//		try {
//			FlexibookController.SignUpForCustomerAccount(username, password);
//		} catch (InvalidInputException e) {
//			error = e.getMessage();
//			FlexiBookApplication.setmessage(error);
//		}
//		userCount ++;
//		tempUser = getCustomer(username);
//	}
//
//	@Then("a new customer account shall be created")
//	public void a_new_customer_account_shall_be_created() {
//		flexibook.getCustomers().size();
//		assertEquals(userCount + userCntrBeforeCreation, flexibook.getCustomers().size());
//
//	}
//
//	@Then("the account shall have username {string} and password {string}")
//	public void the_account_shall_have_username_and_password(String username, String password) {
//		assertEquals(username, flexibook.getCustomer(0).getWithUsername(username).getUsername());
//		assertEquals(password, flexibook.getCustomer(0).getWithUsername(username).getPassword());
//
//	}
//
//	@Then("no new account shall be created")
//	public void no_new_account_shall_be_created() throws Throwable {
//		assertEquals(userCntrBeforeCreation, flexibook.getCustomers().size());
//
//	}
//
//	@Then("an error message {string} shall be raised")
//	public void an_error_message_shall_be_raised(String errorMsg) {
//		String e = FlexiBookApplication.returnmessage();
//		assertEquals(errorMsg, e);
//		FlexiBookApplication.setmessage(null);
//
//	}
//
//	private static Customer getCustomer(String username) {
//		Customer foundCustomer = null;
//		for(Customer customer : FlexiBookApplication.getflexibook().getCustomers()) {
//			if(customer.getUsername().equals(username)) {
//				return customer;
//			}
//		}
//		return foundCustomer;
//	}
//
//}
//
