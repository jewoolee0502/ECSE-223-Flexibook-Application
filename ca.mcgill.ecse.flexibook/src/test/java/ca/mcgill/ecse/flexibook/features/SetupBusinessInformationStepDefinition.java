package ca.mcgill.ecse.flexibook.features;

import ca.mcgill.ecse.flexibook.Controller.InvalidInputException;
import ca.mcgill.ecse.flexibook.application.FlexiBookApplication;
import ca.mcgill.ecse.flexibook.model.FlexiBook;
import ca.mcgill.ecse.flexibook.model.Owner;
import io.cucumber.java.en.Given;

public class SetupBusinessInformationStepDefinition {
	  private  FlexiBook flexibook;
	  private  InvalidInputException thise=null;

//	@Given("an owner account exists in the system with username {string} and password {string}")
//	public void an_owner_account_exists_in_the_system_with_username_and_password(String string, String string2) throws Exception{
//		flexibook=FlexiBookApplication.getflexibook();
//	if(!flexibook.hasOwner()) {
//		
//		   Owner owner =new Owner(string, string2, flexibook);
//		   flexibook.setOwner(owner);
//	}
//		  
//	   
//	}
	@Given("no business exists")
	public void no_business_exists() {
	  if(flexibook.getBusiness()!=null) {
		  flexibook.setBusiness(null);
	  }
	}
	/*@Given("the system's time and date is {string}")
	public void the_system_s_time_and_date_is(String string) {
		String[] arrOfStr = string.split("+", 2);
		
}*/



}
