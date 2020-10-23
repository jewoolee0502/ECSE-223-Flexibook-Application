package ca.mcgill.ecse.flexibook.features;

import java.sql.Date;
import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;
import java.util.Calendar;

import ca.mcgill.ecse.flexibook.application.FlexiBookApplication;
import ca.mcgill.ecse.flexibook.model.BusinessHour.DayOfWeek;
import ca.mcgill.ecse.flexibook.model.Business;
import ca.mcgill.ecse.flexibook.model.BusinessHour;
import ca.mcgill.ecse.flexibook.model.Customer;
import ca.mcgill.ecse.flexibook.model.FlexiBook;
import ca.mcgill.ecse.flexibook.model.TimeSlot;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class ViewAppointmentCalendarStepDefinition {

	private FlexiBook flexibook=FlexiBookApplication.getflexibook();
	private static String error;
	@Given("the business has the following opening hours:")
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


	@Given("the business has the following holidays:")
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
	private static Date dateChecker(String date){
		DateFormat formatDate = new SimpleDateFormat("yyyy/mm/dd");
		try {
			formatDate.parse(date);
		}
		catch (ParseException parseError) {
			error=date+" is not a valid date.";
			FlexiBookApplication.setmessage(error);
		}
		return Date.valueOf(date);
	}
	@When("{string} requests the appointment calendar for the week starting on {string}")
	public void requests_the_appointment_calendar_for_the_week_starting_on(String string, String string2) {
	    Date date=dateChecker(string2);
	    throw new io.cucumber.java.PendingException();
	}
	@Then("the following slots shall be unavailable:")
	public void the_following_slots_shall_be_unavailable(io.cucumber.datatable.DataTable dataTable) {
	    // Write code here that turns the phrase above into concrete actions
	    // For automatic transformation, change DataTable to one of
	    // E, List<E>, List<List<E>>, List<Map<K,V>>, Map<K,V> or
	    // Map<K, List<V>>. E,K,V must be a String, Integer, Float,
	    // Double, Byte, Short, Long, BigInteger or BigDecimal.
	    //
	    // For other transformations you can register a DataTableType.
	    throw new io.cucumber.java.PendingException();
	}
	@Then("the following slots shall be available:")
	public void the_following_slots_shall_be_available(io.cucumber.datatable.DataTable dataTable) {
	    // Write code here that turns the phrase above into concrete actions
	    // For automatic transformation, change DataTable to one of
	    // E, List<E>, List<List<E>>, List<Map<K,V>>, Map<K,V> or
	    // Map<K, List<V>>. E,K,V must be a String, Integer, Float,
	    // Double, Byte, Short, Long, BigInteger or BigDecimal.
	    //
	    // For other transformations you can register a DataTableType.
	    throw new io.cucumber.java.PendingException();
	}

}
