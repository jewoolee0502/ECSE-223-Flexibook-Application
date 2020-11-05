package ca.mcgill.ecse.flexibook.Controller;
import java.awt.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.sql.Date;
import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalTime;

import com.google.common.base.CharMatcher;
import java.io.*;

import ca.mcgill.ecse.flexibook.application.FlexiBookApplication;
import ca.mcgill.ecse.flexibook.model.*;
import ca.mcgill.ecse.flexibook.model.BusinessHour.DayOfWeek;
import ca.mcgill.ecse.flexibook.util.SystemTime;


public class FlexibookController {

	/**
	 * DefineService combo: This method takes in all the parameters to create a service combo in the flexibook system
	 * 
	 * @author Haipeng Yue
	 * 
	 * @param String owername is string.
	 * @paramString comboname is string2.
	 * @param String mainservicename is string3.
	 * @paramString Services is String4
	 * @paramString mandatorySetting is string5
	 * @throws InvalidInputException an error is encountered
	 * @return void
	 */
	public static Map<Integer ,DayOfWeek> mapforDayofWeekMap=new HashMap<>();
	public static void fill_the_DayOfWeek () {
		mapforDayofWeekMap.put(1, DayOfWeek.Monday);
		mapforDayofWeekMap.put(2,DayOfWeek.Tuesday);
		mapforDayofWeekMap.put(3,DayOfWeek.Wednesday);
		mapforDayofWeekMap.put(4,DayOfWeek.Thursday);
		mapforDayofWeekMap.put(5,DayOfWeek.Friday);
		mapforDayofWeekMap.put(6,DayOfWeek.Saturday);
		mapforDayofWeekMap.put(0,DayOfWeek.Sunday);
	}
	public static Map<BookableService, Boolean> gettheMap(FlexiBook fb){
		boolean occupied=false;
		boolean hasDowntime=false;
		boolean overLapExist=false;

		Map<BookableService, Boolean>DowntimeMap=new HashMap<>();
		for(BookableService service2:fb.getBookableServices()) {
			if(service2 instanceof Service) {
				if(service2.getName().equals("color")) {
					hasDowntime=true;
				}
			}else if (service2 instanceof ServiceCombo) {
				for(ComboItem item:((ServiceCombo) service2).getServices()) {
					if(item.getService().getName().equals("color")) {
						hasDowntime=true;
						break;
					}
				}
			}
			DowntimeMap.put(service2, hasDowntime);
			hasDowntime=false;
		}
		return DowntimeMap;
	}

	public static void makecombo(String string, String string2, String string3, String string4, String string5) throws InvalidInputException {
		Service mainservice = null;
		ComboItem main=null;

		FlexiBook fb = FlexiBookApplication.getflexibook();
		if(fb.getOwner().getUsername().equals(string)) {

			if(fb.getBookableServices().size()!=0) {
				if(fb.getBookableService(0).getWithName(string2)!=null)
				{
					throw new InvalidInputException("Service combo "+string2+ " already exists");
				}
			}
			ServiceCombo thiscombo=new ServiceCombo(string2, fb);
			String nameofmain=string3;
			if(fb.getBookableService(0).getWithName(nameofmain)==null) {
				thiscombo.delete();
				throw new InvalidInputException("Service "+nameofmain+" does not exist");
			}
			ArrayList <ComboItem> items=new ArrayList();
			String[] parts = string4.split(","); 
			String[] setting = string5.split(",");
			for (int k=0;k<parts.length;k++) {
				for (int i =0;i<fb.getBookableServices().size();i++) {
					if(fb.getBookableService(i).getName().equals(parts[k])) {
						Service thissub=(Service) fb.getBookableService(i);
						ComboItem thisubservice=new ComboItem(Boolean.parseBoolean(setting[k]),thissub,thiscombo);
						thiscombo.addService(thisubservice);
						items.add(thisubservice);
						if(thisubservice.getService().getName().equals(nameofmain)) {
							thiscombo.setMainService(thisubservice);
							main=thisubservice;
							if(main.getMandatory()!=true) {
								thiscombo.delete();
								throw new InvalidInputException("Main service must be mandatory");
							}
						}
					}else if(fb.getBookableService(i).getWithName(parts[k])==null) {
						thiscombo.delete();
						throw new InvalidInputException("Service "+ parts[k] +" does not exist");
					}
				}

			}
			if(items.contains(main)!=true) {
				thiscombo.delete();
				throw new InvalidInputException("Main service must be included in the services");
			}
			if(items.size()<2) {
				thiscombo.delete();
				throw new InvalidInputException("A service Combo must contain at least 2 services");  
			}
			ComboItem[] comboitems=items.toArray(new ComboItem[items.size()]);

			thiscombo.setFlexiBook(fb);

		} else {
			throw new InvalidInputException("You are not authorized to perform this operation");}
	}



	/**
	 * update: This method takes in all the parameters to update a service combo in the flexibook system
	 * 
	 * @author Haipeng Yue
	 * 
	 * @param String owername is string.
	 * @param String oldcomboname is string2
	 * @param String new comboname is string3.
	 * @param String mainservicename is string4.
	 * @paramString Services is String5
	 * @paramString mandatorySetting is string6
	 * @throws InvalidInputException an error is encountered
	 * @return void
	 */

	public static void updatecombo(String string, String string2, String string3, String string4, String string5, String string6) throws InvalidInputException {
		FlexiBook fb = FlexiBookApplication.getflexibook();

		ComboItem main=null;
		if(fb.getOwner().getUsername().equals(string)==true) {
			if(fb.getBookableServices().size()!=0) {
				if(fb.getBookableService(0).getWithName(string2)==null) {
					throw new InvalidInputException("Service combo does not exist");
				}else {
					if(string2.equals(string3)!=true) {
						makecombo(string,string3,string4,string5,string6);
						fb.getBookableService(0).getWithName(string2).delete();
					}else {
						ServiceCombo combo=(ServiceCombo) fb.getBookableService(0).getWithName(string2);

						String nameofmain=string4;
						if(fb.getBookableService(0).getWithName(nameofmain)==null) {
							throw new InvalidInputException("Service "+nameofmain+" does not exist");
						}
						ArrayList <ComboItem> items=new ArrayList();
						String[] parts = string5.split(","); 
						String[] setting = string6.split(",");
						for (int k=0;k<parts.length;k++) {
							if(fb.getBookableServices().size()!=0) {
								Service thissub=(Service) fb.getBookableService(0).getWithName(parts[k]);
								boolean man=Boolean.parseBoolean(setting[k]);
								if(thissub==null) {
									throw new InvalidInputException("Service "+ parts[k] +" does not exist"); 
								}
								if(thissub.getName().equals(nameofmain)) {
									if(man!=true) {
										throw new InvalidInputException("Main service must be mandatory");
									}
								}
								ComboItem thisubservice=new ComboItem(man,thissub,combo);
								items.add(thisubservice);
								if (thisubservice.getService().getName().equals(nameofmain)) {
									main=thisubservice;
								}
							}
						} 

						if(items.contains(main)!=true) {
							for(int i=0;i<items.size();i++) {
								items.get(i).delete();
							}
							throw new InvalidInputException("Main service must be included in the services");
						}
						if(items.size()<2) {
							for(int i=0;i<items.size();i++) {
								items.get(i).delete();
							}
							throw new InvalidInputException("A service Combo must have at least 2 services");  
						}
						int k=combo.getServices().size();
						for (int i=0;i<k-items.size();i++) {
							combo.getService(0).delete();
						}
						combo.setMainService(main);

					}

				}
			}
		}else {throw new InvalidInputException("You are not authorized to perform this operation");  
		}
	}



	/**
	 * AttemptLogIn: This method takes an input of username and a password. It will check if the person trying to log in is registered in the system with the right password
	 * 
	 * @author James Willems
	 * 
	 * @param String userID
	 * @param String passcode.
	 * @throws InvalidInputException an error is encountered
	 * @return boolean
	 */

	public static boolean AttemptLogIn(String userID,String passcode) throws InvalidInputException {
		FlexiBook flexi=FlexiBookApplication.getflexibook();
		try {
			for(Customer c:flexi.getCustomers()) {
				if(c.getUsername().equals(userID)&&c.getPassword().equals(passcode)) {
					FlexiBookApplication.setCurrentuser(c);
					return true;

				}}
			if(userID.equals("owner")&&passcode.equals("owner")) {
				Owner owner=new Owner(userID,passcode,flexi);
				flexi.setOwner(owner);
				FlexiBookApplication.setCurrentuser(flexi.getOwner());
				return true;
			}

			else FlexiBookApplication.setCurrentuser(null); 
			throw new InvalidInputException("Username/password not found");
		}
		catch(InvalidInputException e) {
			throw new InvalidInputException(e.getMessage());
		}
	}



	/**
	 * LogOut: This method takes no inputs. It will check if the user is already logged in or not, and log him out.
	 * 
	 * @author James Willems
	 * 
	 * @throws InvalidInputException an error is encountered
	 * @return void
	 */

	public static void LogOut() throws InvalidInputException {
		FlexiBook flexi=FlexiBookApplication.getflexibook();
		try {
			if(FlexiBookApplication.getCurrentuser()==null) {
				throw new InvalidInputException("The user is already logged out");
			}
			if(FlexiBookApplication.getCurrentuser()!=null) {
				FlexiBookApplication.setCurrentuser(null);
			}
			throw new InvalidInputException("No user found with corresponding Username");
		}
		catch(InvalidInputException e) {
			throw new InvalidInputException(e.getMessage());
		}
	}



	/**
	 * deletecombo: This method takes an input of username and a combo name. The method will decide whether to initiate the deleting method
	 * 
	 * @author Haipeng Yue
	 * 
	 * @param String username
	 * @param String name of the combo to be deleted.
	 * @throws InvalidInputException an error is encountered
	 * @return void
	 */

	public static void deletecombo(String name,String comboname) throws InvalidInputException {
		FlexiBook fb =FlexiBookApplication.getflexibook();
		String time=SystemTime.gettime(SystemTime.getSysTime());
		String date=SystemTime.getdate(SystemTime.getSysTime());
		if(name.equals(fb.getOwner().getUsername())==true) {
			if(fb.getBookableServices().size()!=0) {
				if(fb.getBookableService(0).getWithName(comboname)!=null) {
					if(fb.getBookableService(0).getWithName(comboname).getAppointments().size()>0) {
						for(int i=0;i<fb.getAppointments().size();i++) {
							String startdate=fb.getBookableService(0).getWithName(comboname).getAppointment(i).getTimeSlot().getStartDate().toString();
							if(SystemTime.comparedate(date,startdate)==2) {
								throw new InvalidInputException("Service combo "+comboname+ " has future appointments"); 
							}else if(SystemTime.comparedate(date,startdate)==1) {
								fb.getBookableService(0).getWithName(comboname).delete();
								break;
							}else if(SystemTime.comparedate(date,startdate)==0) {
								String starttime=fb.getBookableService(0).getWithName(comboname).getAppointment(i).getTimeSlot().getStartTime().toString();
								if(SystemTime.comparetime(time,starttime)==1) {
									fb.getBookableService(0).getWithName(comboname).delete();
									break;
								}else {
									throw new InvalidInputException("Service combo "+comboname+ " has future appointments");
								}
							}
						}
					}else{fb.getBookableService(0).getWithName(comboname).delete();}

				}
			}
		}else {
			throw new InvalidInputException("You are not authorized to perform this operation"); 
		}


	}



	/**
	 * This method takes in all the parameters and creates a new user.
	 * 
	 * @author Jewoo Lee
	 * 
	 * @param username - username of the new user 
	 * @param password - password of the new user
	 * 
	 * @throws InvalidInputException
	 */

	public static void CreateUser(String username, String password) throws InvalidInputException { 
		FlexiBook fb = FlexiBookApplication.getflexibook();

		if(username == null || username == "         ") {
			throw new InvalidInputException("username");

		} else if(fb.getCustomers().size() > 0) {

			if(fb.getCustomer(0).getWithUsername(username) != null) {
			}
		}
		Customer thisc = new Customer(username, password, fb);
	}


	/**
	 * This method takes in all the parameters and looks for a customer account that has the same username.
	 * 
	 * @author Jewoo Lee
	 * 
	 * @param username - username of the customer account
	 * 
	 * @throws InvalidInputException
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


	/**
	 * This method takes in all parameters to sign-up for customer account
	 * 
	 * @author Jewoo Lee
	 * 
	 * @param username
	 * @param password
	 * 
	 * @throws InvalidInputException
	 */

	public static void SignUpForCustomerAccount(String username, String password) throws InvalidInputException {

		try {
			FlexiBook flexibook = FlexiBookApplication.getflexibook();

			if(flexibook.getOwner() != null && FlexiBookApplication.getCurrentuser() == flexibook.getOwner()) {  
				throw new InvalidInputException("You must log out of the owner account before creating a customer account");
			}
			else {
				if(username.equals("") || username == null) {
					throw new InvalidInputException("The user name cannot be empty");
				}
				else if(password.equals("") || password == null) {
					throw new InvalidInputException("The password cannot be empty");
				}
				else {
					if(flexibook.getCustomers().size() == 0) {
						Customer c = new Customer(username, password, flexibook);
						flexibook.addCustomer(c);
					}				
					if(flexibook.getCustomer(0).getWithUsername(username) == null) {
						Customer cstmr = new Customer(username, password, flexibook);
						flexibook.addCustomer(cstmr);
					}
					else {
						throw new InvalidInputException("The username already exists");
					}
				}
			}
		} 

		catch(InvalidInputException e) {
			throw new InvalidInputException(e.getMessage());
		}
	}


	/**
	 * This method takes in all parameters to update either or both owner and customer account
	 * 
	 * @author Jewoo Lee
	 * 
	 * @param oldUsername - the old username before the account was updated
	 * @param newUsername - the new updated username of the customer or owner account
	 * @param newPassword - the new updated password of the customer or owner account
	 * 
	 * @throws InvalidInputException
	 */

	public static void UpdateAccount(String oldUsername, String newUsername, String newPassword) throws InvalidInputException {

		try {

			FlexiBook flexibook = FlexiBookApplication.getflexibook();
			User user;

			if(oldUsername.equals("owner")) {
				user = flexibook.getOwner();
			}
			else {
				user = getCustomer(oldUsername);
			}
			if(user != null) {
				if(oldUsername.equals("owner") && (!newUsername.equals("owner"))) {
					throw new InvalidInputException("Changing username of owner is not allowed");	
				}
				else if(newUsername.equals("") || newUsername == null) {
					throw new InvalidInputException("The user name cannot be empty");
				}
				else if(newPassword.equals("") || newPassword == null) {
					throw new InvalidInputException("The password cannot be empty");
				}
				else if(getCustomer(newUsername) != null) {   
					throw new InvalidInputException("Username not available");
				}
				else {
					user.setUsername(newUsername);
					user.setPassword(newPassword);
				}
			}

		} catch (InvalidInputException e) {
			throw new InvalidInputException(e.getMessage());
		}

	}


	/**
	 * This method takes in all parameters to delete customer account
	 * 
	 * @author Jewoo Lee
	 * 
	 * @param username - the customer or owner's account username
	 * @param target - the target username that wants to be deleted
	 * 
	 * @throws InvalidInputException
	 */

	public static void DeleteCustomerAccount(String username, String target) throws InvalidInputException {

		try {

			Customer user = getCustomer(username);

			if((!(username.equals(target))) || username.equals("owner")) {
				throw new InvalidInputException("You do not have permission to delete this account");
			}
			else {

				for(Appointment appointment : user.getAppointments()) {
					appointment.delete();

				}
				user.delete();
				FlexiBookApplication.setCurrentuser(null);
			}


		} catch (InvalidInputException e) {
			throw new InvalidInputException(e.getMessage());
		}


	}



	/**
	 * This method takes all parameters to make a new appointment in the system.
	 * 
	 * @author Zhixin Xiong
	 * @author Yujing Yan
	 * 
	 * @param customer -- can be customer or owner. If it's owner, throw exception.
	 * @param date
	 * @param serviceName
	 * @param optionalServices
	 * @param startTime
	 * @throws InvalidInputException
	 */

	public static void MakeAppointment(String customer, String date, String serviceName, String optionalServices, String startTime) throws InvalidInputException{
		FlexiBook fb = FlexiBookApplication.getflexibook();
		BookableService service=BookableService.getWithName(serviceName);
		fill_the_DayOfWeek();

		if(fb.getBusiness()==null) {
			throw new InvalidInputException("The business should exist for making an appointment.");
		}
		//a owner tries to make an appointment
		if(customer.equals("owner")) {
			throw new InvalidInputException("An owner cannot make an appointment");
		}
		//a customer tries to make an appointment
		int cindex = -1;
		for(Customer c : fb.getCustomers()) {
			if(c.getUsername().equals(customer)) {
				cindex = fb.indexOfCustomer(c);
			}
		}
		//try to figure out it is service combo or not
		int duration = 0;
		if(service instanceof Service) {
			duration=((Service) service).getDuration();
		}else if(service  instanceof ServiceCombo) {
			String[] arrOfStr = optionalServices.split(",", -1);
			for(int i=0;i<arrOfStr.length;i++) {
				BookableService aService=BookableService.getWithName(arrOfStr[i]);
				if(aService instanceof Service) {
					duration+=((Service) aService).getDuration();
				}
			}
			duration+=((ServiceCombo) service).getMainService().getService().getDuration();

		}

		Date servicedate = Date.valueOf(date);
		Time starttime = Time.valueOf(startTime+":00");
		Time endtime = null;
		LocalTime localtime = starttime.toLocalTime();

		endtime = Time.valueOf(localtime.plusMinutes(duration));
		String a=starttime.toString();
		String endtimet=endtime.toString();
		int day=servicedate.getDay();


		//ensure that the time slot is in business hour
		DayOfWeek inputDayOfWeek=mapforDayofWeekMap.get(day);

		Business business= fb.getBusiness();

		List<BusinessHour> aHour=fb.getBusiness().getBusinessHours();
		List<TimeSlot> allTimeSlots=fb.getTimeSlots();
		List<DayOfWeek> aDayOfWeeks=new ArrayList<>();
		boolean inBusinessHour=false;
		for(BusinessHour ahour:aHour) {
			DayOfWeek dayOfWeek=ahour.getDayOfWeek();
			if(dayOfWeek.equals(inputDayOfWeek)) {
				if(endtime.after(ahour.getEndTime())==false&&starttime.before(ahour.getStartTime())==false) {
					inBusinessHour=true;
				}
			}}
		//make sure that this timeslot does not overslap with other time slots
		List<TimeSlot> newList = new ArrayList<>();
		newList.addAll(business.getHolidays());
		newList.addAll(business.getVacation());

		//make sure that it does not overlap with the holidays and vacation
		Boolean overslapBoolean=false;
		for(TimeSlot Slota:newList) {
			if((Slota.getEndDate().before(servicedate)==false)&&(Slota.getStartDate().after(servicedate)==false)){
				overslapBoolean=true;	
			}
		}
		//make sure that it doesn't happen in the past
		Date currenDate=Date.valueOf(SystemTime.getdate(SystemTime.getSysTime()));

		//make sure that is not occupied by existing appointment
		boolean occupied=false;
		boolean hasDowntime=false;
		boolean overLapExist=false;
		Map<BookableService, Boolean>DowntimeMap=gettheMap(fb);	
		for(Appointment appointment : fb.getAppointments()) {
			TimeSlot slot = appointment.getTimeSlot();
			if(slot!=null) {
				if(slot.getStartDate().after(servicedate)==false&&slot.getStartDate().before(servicedate)==false) {
					if((starttime.before(slot.getEndTime()))&&(starttime.before(slot.getStartTime())==false)) {
						occupied=true;			
					}
				}
			}
			if(occupied==true) {
				if((DowntimeMap.get(appointment.getBookableService())==false)) {
					overLapExist=true;
					break;
				}else {
					Time starttime2 = slot.getStartTime();
					LocalTime localstarttime2 = starttime2.toLocalTime();

					starttime2=Time.valueOf(localstarttime2.plusMinutes(30));
					Time endtime2 = slot.getEndTime();
					LocalTime localedndtime2 = endtime.toLocalTime();

					endtime2 = Time.valueOf(localedndtime2.minusMinutes(30));
					// slot corresponds to down time that is not long enough
					if(duration>30) {
						overLapExist=true;
						if(overLapExist)
							break;
					}

					occupied=false;
				}
			}
		}
		if((!inBusinessHour)||overslapBoolean||currenDate.before(servicedate)==false||overLapExist) {
			throw new InvalidInputException("There are no available slots for "+serviceName+" on "+date+" at "+startTime);
		}

		TimeSlot timeslot = new TimeSlot(servicedate,starttime,servicedate,endtime, fb);

		Appointment appointment = new Appointment(fb.getCustomer(cindex), service, timeslot, fb);


	}


	/**
	 * This method checks whether the time slot overlaps or not.
	 * 
	 * @author Jewoo Lee
	 * 
	 * @param t1 - the first time slot 
	 * @param t2 - the second time slot 
	 * 
	 */

	public static boolean isNoOverlap(TimeSlot t1, TimeSlot t2) {
		if(t1.getStartDate().equals(t2.getStartDate())) {
			if(t1.getEndTime().before(t2.getStartTime()) || 
					t2.getEndTime().before(t1.getStartTime())) {
				//is not overlap
				return true;
			}else {
				return false;
			}
		}
		return true;
	}



	/**
	 * This method checks whether one of the time slot is fully covered by the other time slot or not.
	 * 
	 * @author Jewoo Lee
	 * @author Haipeng Yue
	 * 
	 * @param t1 - the first time slot 
	 * @param t2 - the second time slot 
	 * 
	 */

	public static boolean isFullyCovered(TimeSlot t1, TimeSlot t2) {
		if(t1.getStartDate().equals(t2.getStartDate())) {
			if(t1.getEndTime().before(t2.getEndTime()) && 
					t1.getStartTime().after(t2.getStartTime())) {
				return true;
			}
			else {
				return false;
			}
		}
		else {
			return false;
		}

	}



	/**The method updates an appointment of a customer.
	 * 
	 * @author Jewoo Lee
	 * @author Haipeng Yue
	 * 
	 * @param customer -- the customer to update the appointment 
	 * @param customer2 -- the customer whose appointment to be updated
	 * @param action -- add/remove a comboitem in service combo appointment
	 * @param comboItem -- the combo item to be updated 
	 * @param serviceName -- the service to be updated
	 * @param serviceDate -- the old date of service
	 * @param newDate -- the updated service date
	 * @param startTime -- the old start time of appointment
	 * @param newStartTime -- the new start time of appointment 
	 * @throws InvalidInputException
	 */
	public static void UpdateAppointment(String customer, String customer2, String action, String comboItem, String serviceName, 
			String serviceDate, String newDate, String startTime, String newStartTime) throws InvalidInputException {
		FlexiBook fb = FlexiBookApplication.getflexibook();
		if(customer2!=null) {
			if(customer.equals("owner")) {
				throw new InvalidInputException("Error: An owner cannot update a customer's appointment");
			}else {
				throw new InvalidInputException("Error: A customer can only update their own appointments");
			}
		}
/*/
 * 
 */
		int cindex = -1;
		for(Customer c : fb.getCustomers()) {
			if(c.getUsername().equals(customer)) {
				cindex = fb.indexOfCustomer(c);
			}
		}

		int aindex = -1;
		for(Appointment a : fb.getCustomer(cindex).getAppointments()) {
			if(a.getTimeSlot().getStartDate().equals(Date.valueOf(serviceDate))) {
				if(a.getTimeSlot().getStartTime().equals(Time.valueOf(startTime+":00"))) {
					aindex = fb.getCustomer(cindex).indexOfAppointment(a);
				}
			}
		}
		
		
		
		if(action == null && comboItem == null) {
			Service service = (Service)fb.getCustomer(cindex).getAppointment(aindex).getBookableService();
			Time newstarttime = Time.valueOf(newStartTime+":00");
			TimeSlot oldslot = fb.getCustomer(cindex).getAppointment(aindex).getTimeSlot();
			LocalTime localstart = newstarttime.toLocalTime();
			int duration = localstart.compareTo(oldslot.getEndTime().toLocalTime());
			int d = service.getDuration();
			Time endtime = Time.valueOf(localstart.plusMinutes(d));
			int day = Date.valueOf(newDate).getDay();
			if(day == 0 || day == 6) {
				throw new InvalidInputException("unsuccessful");
			}else if(day == 5) {
				if(endtime.after(new Time(15,00,00))) {
					throw new InvalidInputException("unsuccessful");
				}
			}else {
				if(endtime.after(new Time(16,50,00))) {
					throw new InvalidInputException("unsuccessful");
				}
			}
			if(fb.getBusiness().getBusinessHour(0).getEndTime().before(endtime)) {
			}
			FlexiBook fb2 = new FlexiBook();
			TimeSlot newslot = new TimeSlot(Date.valueOf(newDate), newstarttime, Date.valueOf(newDate), endtime, fb);
			try {
				fb.getCustomer(cindex).getAppointment(aindex).updateAppointment(newslot, 
						fb.getCustomer(cindex).getAppointment(aindex).getBookableService(), fb.getCustomer(cindex).getAppointment(aindex).getChosenItems());
			}
			catch (RuntimeException e) {
				if(e != null) {
					throw new InvalidInputException(e.getMessage());
				}
			}
			FlexiBookApplication.setmessage("successful");

		}

		else {
			ServiceCombo combo = (ServiceCombo)fb.getCustomer(cindex).getAppointment(aindex).getBookableService();
			if(action.equals("remove")) {
				if(combo.getMainService().getService().getName().equals(comboItem)) {
					throw new InvalidInputException("unsuccessful");
				}
				for(ComboItem item :combo.getServices()) {
					if(item.getMandatory()) {
						if(item.getService().getName().equals(comboItem)) {
							throw new InvalidInputException("unsuccessful");
						}
					}
					if(item.getService().getName().equals(comboItem)) {
						fb.getCustomer(cindex).getAppointment(aindex).removeChosenItem(item);
						List<ComboItem> newChosenItems = fb.getCustomer(cindex).getAppointment(aindex).getChosenItems();
						TimeSlot ts = fb.getCustomer(cindex).getAppointment(aindex).getTimeSlot();
						LocalTime endTime = ts.getEndTime().toLocalTime();
						endTime = endTime.minusMinutes(item.getService().getDuration());
						ts.setEndTime(Time.valueOf(endTime));
						try {
							fb.getCustomer(cindex).getAppointment(aindex).updateAppointment(ts, fb.getCustomer(cindex).getAppointment(aindex).getBookableService(), newChosenItems);
						}
						catch (RuntimeException e) {
							if(e != null) {
								throw new InvalidInputException(e.getMessage());
							}
						}
						FlexiBookApplication.setmessage("successful");
					}
				}
			}
			if(action.equals("add")) {
				Service svc = (Service) fb.getBookableService(0).getWithName(comboItem);
				ComboItem CI = new ComboItem(false, svc, combo);
				Appointment ap = fb.getCustomer(cindex).getAppointment(aindex);
				int d = svc.getDuration();
				TimeSlot ts = ap.getTimeSlot();
				String sts = ts.getStartTime().toString();
				String ets = ts.getEndTime().toString();
				LocalTime EndTime = ts.getEndTime().toLocalTime();
				LocalTime newEndtime = EndTime.plusMinutes(d);
				ts.setEndTime(Time.valueOf(newEndtime));
				String endts = ts.getEndTime().toString();

				for(Appointment appointment : fb.getAppointments()) {
					if(appointment != fb.getCustomer(cindex).getAppointment(aindex)) {
						TimeSlot slot = appointment.getTimeSlot();

						if(!isNoOverlap(ts,slot)) {
							CI.delete();
							ts.setEndTime(Time.valueOf(EndTime));
							throw new InvalidInputException("unsuccessful");
						}
					}
				}
				fb.getCustomer(cindex).getAppointment(aindex).addChosenItem(CI);
				try {
					fb.getCustomer(cindex).getAppointment(aindex).updateAppointment(ts, 
							fb.getCustomer(cindex).getAppointment(aindex).getBookableService(), fb.getCustomer(cindex).getAppointment(aindex).getChosenItems());
				}
				catch (RuntimeException e) {
					if(e != null) {
						throw new RuntimeException(e.getMessage());
					}
				}
				FlexiBookApplication.setmessage("successful");
			}

		}
	}



	/**
	 * The CancelAppointment method can cancel an appointment in the flexibook system.
	 * 
	 * @author Jewoo Lee
	 * @author Haipeng Yue
	 * 
	 * @param customer -- the customer who want to cancel his/her appointment.
	 * @param customer2 -- the customer whose appointment to be cancelled.
	 * @param name -- the service name in appointment
	 * @param serviceDate -- the service date in appointment
	 * @param startTime -- the start time of the appointment
	 * @throws InvalidInputException
	 */

	public static void CancelAppointment(String customer, String customer2, String name, String serviceDate, String startTime) throws InvalidInputException {
		FlexiBook fb = FlexiBookApplication.getflexibook();
		if(!(customer2==null)) {
			if(customer.equals("owner")) {
				throw new InvalidInputException("An owner cannot cancel an appointment");
			}else {
				throw new InvalidInputException("A customer can only cancel their own appointments");
			}
		}

		String sysTime = SystemTime.getSysTime();
		String[] sys = sysTime.split("\\+");
		Date localDate = Date.valueOf(sys[0]);
		Time localTime = Time.valueOf(sys[1]+":00");

		Date servicedate = Date.valueOf(serviceDate);
		Time starttime = Time.valueOf(startTime+":00");
		if(servicedate.equals(localDate)) {
			throw new InvalidInputException("Cannot cancel an appointment on the appointment date");
		}

		int cindex = -1;
		for(Customer c : fb.getCustomers()) {
			if(c.getUsername().equals(customer)) {
				cindex = fb.indexOfCustomer(c);
			}
		}
		int aindex = -1;
		for(Appointment a : fb.getCustomer(cindex).getAppointments()) {
			if(a.getTimeSlot().getStartDate().equals(servicedate)) {
				if(a.getTimeSlot().getStartTime().equals(starttime)) {
					aindex = fb.getCustomer(cindex).indexOfAppointment(a);
				}
			}
		}
		fb.getCustomer(cindex).getAppointment(aindex).cancelAppointment();
	}



	/**
	 * The OwnerCancelAppointment method can cancel an appointment in the flexibook system.
	 * 
	 * @author Jewoo Lee
	 * 
	 * @param customer -- the customer who want to cancel his/her appointment.
	 * @param owner -- the owner whose appointment to be cancelled.
	 * @param name -- the service name in appointment
	 * @param serviceDate -- the service date in appointment
	 * @param startTime -- the start time of the appointment
	 * @throws InvalidInputException
	 */

	public static void OwnerCancelAppointment(String customer, String owner, String name, String serviceDate, String startTime) throws InvalidInputException {
		FlexiBook fb = FlexiBookApplication.getflexibook();
		if(owner.equals("owner")) {

			String sysTime = SystemTime.getSysTime();
			String[] sys = sysTime.split("\\+");
			Date localDate = Date.valueOf(sys[0]);
			Time localTime = Time.valueOf(sys[1]+":00");

			Date servicedate = Date.valueOf(serviceDate);
			Time starttime = Time.valueOf(startTime+":00");
			if(servicedate.equals(localDate)) {
				throw new InvalidInputException("Cannot cancel an appointment on the appointment date");
			}

			int cindex = -1;
			for(Customer c : fb.getCustomers()) {
				if(c.getUsername().equals(customer)) {
					cindex = fb.indexOfCustomer(c);
				}
			}
			int aindex = -1;
			for(Appointment a : fb.getCustomer(cindex).getAppointments()) {
				if(a.getTimeSlot().getStartDate().equals(servicedate)) {
					if(a.getTimeSlot().getStartTime().equals(starttime)) {
						aindex = fb.getCustomer(cindex).indexOfAppointment(a);
					}
				}
			}
			if(fb.getCustomer(cindex).getAppointment(aindex).getAppointmentInProgress() == false) {
				throw new InvalidInputException("The owner cannot delete the appointment before the start of the appointment");
			}
			fb.getCustomer(cindex).getAppointment(aindex).ownerCancelAppointment();

		}
		else {
			throw new InvalidInputException("You are not the owner");
		}

	}



	/**
	 * This method takes all parameters to update a service in the system.
	 * 
	 * @author Tianyu Zhao
	 * 
	 * @param ownername --- the name of current owner
	 * @paramString --- ownername is string
	 * @param color     
	 * @paramString --- color is string2
	 * @param servicename  --- a string of services, separated with ",".
	 * @paramString --- servicename is string3
	 * @param duration     --- the total amount of time needed for a service
	 * @paramString --- duration is string4
	 * @param downtimeStart
	 * @paramString --- downtimeStart is string5
	 * @param downtimeDuration  
	 * @paramString --- downtimeDuration is string6
	 * @throws InvalidInputException --- if the input is invalid
	 * @return void
	 */

	public static void updateservice(String string, String string2, String string3, String string4, String string5, String string6) throws InvalidInputException {
		FlexiBook fb = FlexiBookApplication.getflexibook();
		Service thiss=(Service) fb.getBookableService(0).getWithName(string2);
		int duration=Integer.parseInt(string4);
		int downstart=Integer.parseInt(string5);
		int downduration=Integer.parseInt(string6);
		if(fb.getOwner().getUsername().equals(string)) {
			if(fb.getBookableService(0).getWithName(string3)==null) {
				if(duration<0||duration==0) {

					throw new InvalidInputException("Duration must be positive"); 
				}else if(downstart>0 && downduration<=0) {

					throw new InvalidInputException("Downtime duration must be positive"); 
				}else if (downstart==0&& downduration<0) {

					throw new InvalidInputException("Downtime duration must be "+downstart); 
				}else if (downstart==0&& downduration>0) {
					throw new InvalidInputException("Downtime must not start at the beginning of the service"); 
				}else if (downstart<0) {
					throw new InvalidInputException("Downtime must not start before the beginning of the service"); 
				}else if (downstart>duration) {
					throw new InvalidInputException("Downtime must not start after the end of the service"); 
				}else if (downduration>duration-downstart) {
					throw new InvalidInputException("Downtime must not end after the service"); 
				}else {
					thiss.setName(string3); 
					thiss.setDowntimeStart(downstart);
					thiss.setDowntimeDuration(downduration);
					thiss.setDuration(duration); 
				}
			}else if(string2.equals(string3)) {
				if(duration<0||duration==0) {

					throw new InvalidInputException("Duration must be positive"); 
				}else if(downstart>0 && downduration<=0) {

					throw new InvalidInputException("Downtime duration must be positive"); 
				}else if (downstart==0&& downduration<0) {

					throw new InvalidInputException("Downtime duration must be "+downstart); 
				}else if (downstart==0&& downduration>0) {
					throw new InvalidInputException("Downtime must not start at the beginning of the service"); 
				}else if (downstart<0) {
					throw new InvalidInputException("Downtime must not start before the beginning of the service"); 
				}else if (downstart>duration) {
					throw new InvalidInputException("Downtime must not start after the end of the service"); 
				}else if (downduration>duration-downstart) {
					throw new InvalidInputException("Downtime must not end after the service"); 
				}else {
					thiss.setDowntimeStart(downstart);
					thiss.setDowntimeDuration(downduration);
					thiss.setDuration(duration);
				}}else if(fb.getBookableService(0).getWithName(string3)!=null) {
					throw new InvalidInputException("Service "+string3+" already exists");
				}

		} else {

			throw new InvalidInputException("You are not authorized to perform this operation");}
	}



	/**
	 * deleteService: This method takes an input of servicenames. The method will decide whether to initiate the deleting method
	 * 
	 * @author Zhixin Xiong
	 * 
	 * @param owner
	 * @param servicename
	 * @throws InvalidInputException
	 */
	public static void deleteService(String owner, String servicename) throws InvalidInputException {
		FlexiBook fb =FlexiBookApplication.getflexibook();
		String time=SystemTime.gettime(SystemTime.getSysTime());
		String date=SystemTime.getdate(SystemTime.getSysTime());
		Service thiss;
		String userString=fb.getOwner().getUsername();


		if(owner.equals(fb.getOwner().getUsername())!=true) {
			throw new InvalidInputException("You are not authorized to perform this operation"); 
		}
		thiss=(Service) BookableService.getWithName(servicename);

		List<Appointment> allAppointments=fb.getAppointments();
		String bString;
		List<BookableService> allServices=fb.getBookableServices();
		List<String> TobedeletedBookableServices = new ArrayList<>();

		//throw exception if have future appointment
		Date currenDate=Date.valueOf(SystemTime.getdate(SystemTime.getSysTime()));

		if(BookableService.getWithName(servicename).getAppointments().size()>0) {
			for(Appointment appointment:thiss.getAppointments()) {
				Date staDate=appointment.getTimeSlot().getStartDate();
				if(staDate.after(currenDate)) {
					throw new InvalidInputException("The service contains future appointments");
				}

			}
		}
		//delete a service included in a serviceCombo

		for(BookableService serviceCom:allServices) {
			if(serviceCom instanceof ServiceCombo) {
				serviceCom=(ServiceCombo)serviceCom;
				String seString=serviceCom.getName();
				seString=serviceCom.getName();
				ComboItem thisComboItem=null;
				List<ComboItem>CombosItems=((ServiceCombo) serviceCom).getServices();

				boolean isMainservice=((ServiceCombo) serviceCom).getMainService().getService().getName().equals(thiss.getName());
				if(isMainservice){
					TobedeletedBookableServices.add(serviceCom.getName());
				}else {
					for(ComboItem item:((ServiceCombo) serviceCom).getServices()) {
						if(item.getService().getName().equals(servicename)) {
							item.delete();
							break;}

					}

				}
			}
		}

		for(int i=0 ;i<TobedeletedBookableServices.size();i++) {
			BookableService.getWithName(TobedeletedBookableServices.get(i)).delete();
		}

		thiss.delete();
	}



	/**
	 * This method takes all parameters to add a new service in the system.
	 * 
	 * @author Tianyu Zhao
	 * @param  owner  --- the owner name of current owner
	 * @param  name   --- name of the services, A string of services
	 * @param  duration  --- the total amount of time needed for a service
	 * @paramString  ---   duration is string3
	 * @param  downtimeStart  --- the start of the downtime
	 * @paramString    downtieStart is string4
	 * @param  downtimeDuration  --- the duration of downtime
	 * @paramString --- downtimeDuration is string5
	 * @throws InvalidInputException  if input is invalid
	 * @return void
	 */

	public static void addService(String owner, String name, String string3, String string4,String string5) throws InvalidInputException {
		Service service = null;

		FlexiBook fb = FlexiBookApplication.getflexibook();
		String servicename = null;
		//convert integer to string
		int duration=Integer.parseInt(string3);
		int downstart=Integer.parseInt(string4);
		int downduration=Integer.parseInt(string5);
		if(fb.getOwner().getUsername().equals(owner)) {
			if(fb.getBookableServices().size()==0) {

				if(duration<0||duration==0) {

					throw new InvalidInputException("Duration must be positive"); 
				}else if(downstart>0 && downduration<=0) {

					throw new InvalidInputException("Downtime duration must be positive"); 
				}else if (downstart==0&& downduration<0) {

					throw new InvalidInputException("Downtime duration must be "+downstart); 
				}else if (downstart==0&& downduration>0) {
					throw new InvalidInputException("Downtime must not start at the beginning of the service"); 
				}else if (downstart<0) {
					throw new InvalidInputException("Downtime must not start before the beginning of the service"); 
				}else if (downstart>duration) {
					throw new InvalidInputException("Downtime must not start after the end of the service"); 
				}else if (downduration>duration-downstart) {
					throw new InvalidInputException("Downtime must not end after the service"); 
				}else {

					Service thisservice=new Service(name, fb, duration,downduration , downstart);
				}
			}else if(fb.getBookableService(0).getWithName(name)!=null) {
				throw new InvalidInputException("Service "+name+ " already exists");
			}else if(fb.getBookableService(0).getWithName(name)==null) {
				if(duration<0||duration==0) {

					throw new InvalidInputException("Duration must be positive"); 
				}else if(downstart>0 && downduration<=0) {

					throw new InvalidInputException("Downtime duration must be positive"); 
				}else if (downstart==0&& downduration<0) {

					throw new InvalidInputException("Downtime duration must be "+downstart); 
				}else if (downstart==0&& downduration>0) {
					throw new InvalidInputException("Downtime must not start at the beginning of the service"); 
				}else if (downstart<0) {
					throw new InvalidInputException("Downtime must not start before the beginning of the service"); 
				}else if (downstart>duration) {
					throw new InvalidInputException("Downtime must not start after the end of the service"); 
				}else if (downduration>duration-downstart) {
					throw new InvalidInputException("Downtime must not end after the service"); 
				}else {
					Service thisservice=new Service(name, fb, duration, downduration, downstart);
				}
			}

		} else {

			throw new InvalidInputException("You are not authorized to perform this operation");}
	}



	/**
	 * getUnavailableTimeSlots: This method takes as input a date and a username and returns the unavailable time slots for a day. 	 * 
	 * @author James Willems
	 * @param String username
	 * @param String date
	 * @throws InvalidInputException an error is encountered
	 * @return ArrayList<TimeSlot>
	 */

	public static ArrayList<TimeSlot> getUnavailableTimeSlots (String username, String date) throws InvalidInputException{
		String error;
		FlexiBook flexibook=FlexiBookApplication.getflexibook();
		ArrayList<TimeSlot> list = new ArrayList<TimeSlot>();
		Date newDate = null;
		try {
			newDate=dateChecker(date);}
		catch(ParseException e) {
			throw new InvalidInputException(date+" is not a valid date");
		}
		catch(IllegalArgumentException f) {
			throw new InvalidInputException(date+" is not a valid date");
		}

		for(Appointment appointment:flexibook.getAppointments()) {
			if(appointment.getTimeSlot().getStartDate().equals(newDate)) {
				list.add(appointment.getTimeSlot());

			}
		}
		return list;
	}




	/**
	 * getAvailableTimeSlots: This method takes as input a date and a username and returns the available time slots for a day. 	 * 
	 * @author James Willems
	 * @param String username
	 * @param String date
	 * @throws InvalidInputException an error is encountered
	 * @return ArrayList<TimeSlot>
	 */

	public static ArrayList<TimeSlot> getAvailableTimeSlots(String username, String date) throws InvalidInputException {
		String error;
		int count=0;
		FlexiBook flexibook=FlexiBookApplication.getflexibook();
		ArrayList<TimeSlot> list = new ArrayList<TimeSlot>();
		Date newDate = null;
		try {
			newDate=dateChecker(date);}
		catch(ParseException e) {
			throw new InvalidInputException(date+" is not a valid date");
		}
		catch(IllegalArgumentException f) {
			throw new InvalidInputException(date+" is not a valid date");
		}
		for(TimeSlot ts:flexibook.getTimeSlots()) {
			for (Appointment a:flexibook.getAppointments()) {
				if(!(a.getTimeSlot().equals(ts))&&a.getTimeSlot().getStartDate().equals(newDate)) {
					count++;
				}
			}
			if (count==flexibook.getAppointments().size()) {
				list.add(ts);
			}
		}		
		return list;
	}



	/**
	 * date Checker: This method takes as input a date as a string and returns it as a Date object. 	 * 
	 * @author James Willems
	 * @param String date
	 * @throws InvalidInputException an error is encountered
	 * @throws Illegal Argument Exception
	 * @return Date date
	 */
	private static Date dateChecker(String date)throws ParseException , IllegalArgumentException{
		DateFormat formatDate = new SimpleDateFormat("yyyy-mm-dd");
		formatDate.parse(date);
		return Date.valueOf(date);
	}



	/**
	 * getDaysOfWeek: This method takes date as inputs and returns all the remaining days of the week.
	 *  	  
	 * @author James Willems
	 * @param String date
	 * @throws InvalidInputException an error is encountered
	 * @return ArrayList<Date>
	 */

	public static ArrayList<Date> getDaysofWeek(String date) throws InvalidInputException{
		String error;
		int day;
		FlexiBook flexibook=FlexiBookApplication.getflexibook();
		ArrayList<Date> list = new ArrayList<Date>();
		Date newDate = null;

		try {
			newDate=dateChecker(date);}
		catch(ParseException e) {
			throw new InvalidInputException(date+" is not a valid date");
		}
		catch(IllegalArgumentException f) {
			throw new InvalidInputException(date+" is not a valid date");
		}	
		Calendar c = Calendar.getInstance();
		c.setTime(newDate);
		day=newDate.getDay();
		switch(day){
		case 0: for(int i=0;i<6;i++) {
			c.add(Calendar.DATE,1);
			java.util.Date intDate=c.getTime();
			Date intermediate=  new Date(intDate.getTime());
			list.add(intermediate);

		}break;
		case 1: for(int i=0;i<5;i++) {
			c.add(Calendar.DATE,1);
			java.sql.Date intermediate=  (java.sql.Date) c.getTime();
			list.add(intermediate);
		}break;
		case 2: for(int i=0;i<4;i++) {
			c.add(Calendar.DATE,1);
			java.sql.Date intermediate=  (java.sql.Date) c.getTime();
			list.add(intermediate);
		}break;
		case 3: for(int i=0;i<3;i++) {
			c.add(Calendar.DATE,1);
			java.sql.Date intermediate=  (java.sql.Date) c.getTime();
			list.add(intermediate);
		}break;
		case 4: for(int i=0;i<2;i++) {
			c.add(Calendar.DATE,1);
			java.sql.Date intermediate=  (java.sql.Date) c.getTime();
			list.add(intermediate);
		}break;
		case 5: for(int i=0;i<1;i++) {
			c.add(Calendar.DATE,1);
			java.sql.Date intermediate=  (java.sql.Date) c.getTime();
			list.add(intermediate);
		}break;

		}
		return list;
	}


	/**
	 * addTimeSlot: This method adds new time slot.
	 *  	  
	 * @author James Willems
	 * 
	 * @param String type
	 * @param String startDate
	 * @param String startTime
	 * @param String endDate
	 * @param String endTime
	 * 
	 * @throws InvalidInputException an error is encountered
	 * @return ArrayList<Date>
	 */

	public static void addTimeSlot(String type, String startDate, String startTime, String endDate, String endTime) throws InvalidInputException{
		FlexiBook flexibook = FlexiBookApplication.getflexibook();
		String mString = "";
		FlexiBookApplication.setmessage(mString);
		FlexiBookApplication.setmessage("");
		FlexiBook fb = FlexiBookApplication.getflexibook();
		String user = fb.getOwner().getUsername();
		String currentUserString=FlexiBookApplication.getCurrentuser().getUsername();
		String tString="Vacation";

		Date currenDate=Date.valueOf(SystemTime.getdate(SystemTime.getSysTime()));
		Business business=flexibook.getBusiness();
		List<TimeSlot> timeSlots;
		List<TimeSlot> anotherTimeSlots;
		if(type.equals("vacation")){
			timeSlots=business.getVacation();
			anotherTimeSlots=business.getHolidays();
		}else {
			tString="Holiday";
			timeSlots=business.getHolidays();
			anotherTimeSlots=business.getVacation();
		}
		Date staDate=Date.valueOf(startDate);
		Time staTime=Time.valueOf(startTime+":00");
		Date enDate=Date.valueOf(endDate);
		Time enTime=Time.valueOf(endTime+":00");

		if(user.equals(currentUserString)==false) {
			throw new InvalidInputException("No permission to update business information");
		}
		if(currenDate.before(staDate)==false) {
			throw new InvalidInputException(tString+" cannot start in the past");
		}
		if(staDate.before(enDate)==false) {
			if((staDate.after(enDate))||(staDate.after(enDate)==false&&staTime.before(enTime)==false))
				throw new InvalidInputException("Start time must be before end time");
		}

		//check whether this slot overlap other time slots
		Boolean overlapDifferBoolean=false;
		for(TimeSlot Slota:anotherTimeSlots) {
			if(!((Slota.getEndDate().after(staDate)==false)||(Slota.getStartDate().before(enDate)==false))){
				overlapDifferBoolean=true;	
			}

		}
		if(overlapDifferBoolean==true) {
			throw new InvalidInputException("Holiday and vacation times cannot overlap");
		}
		Boolean overlapSameBoolean=false;
		for(TimeSlot Slota:timeSlots) {
			System.out.println(Slota);
			if(!((Slota.getEndDate().after(staDate)==false)||(Slota.getStartDate().before(enDate)==false))){
				overlapSameBoolean=true;	
			}

		}
		if(overlapSameBoolean==true) {
			throw new InvalidInputException(tString+" times cannot overlap");
		}

		TimeSlot slot=new TimeSlot(staDate,staTime,enDate,enTime,flexibook);
		if(type.equals("vacation")) {
			business.addVacation(slot);
		}
		else {
			business.addHoliday(slot);
		}


	}



	/**
	 * This method takes all parameters to set the business information in the system.
	 * 
	 * @author Zhixin Xiong
	 * @param name
	 * 
	 * @param address
	 * @param phone number
	 * @param email

	 */

	public static void UpdateBusinessInformation(String name,String address,String phoneNumber,String email)throws InvalidInputException {
		FlexiBook fb = FlexiBookApplication.getflexibook();
		String user = fb.getOwner().getUsername();
		String currentUserString=FlexiBookApplication.getCurrentuser().getUsername();
		if(user.equals(currentUserString)==false) {
			throw new InvalidInputException("No permission to update business information");
		}
		String aString="@gmail.com" ;
		int begin=email.length()-aString.length();
		int end=email.length();
		if(begin<=0) {
			throw new InvalidInputException("Invalid email");
		}else {
			String subString =email.substring(begin,end);
			if(subString.equals(aString)==false) {
				throw new InvalidInputException("Invalid email");
			}
		}
		fb.getBusiness().setEmail(email);
		fb.getBusiness().setName(name);
		fb.getBusiness().setPhoneNumber(phoneNumber);
		fb.getBusiness().setAddress(address);
		FlexiBookApplication.setmessage("");

	}
	/**
	 * This method takes all parameters to set the business information in the system.
	 * 
	 * @author Zhixin Xiong
	 * @param ExistingDay
	 * @param ExistingStartTime
	 * @param newDay
	 * @param newstartTime
	 * @param newEndTime
	 * @throws InvalidInputException
	 */
	public static void UpdateExistingBusinessHour(String ExistingDay, String ExistingStartTime, String newDay, String newstartTime, String newEndTime)throws InvalidInputException {
		FlexiBook fb = FlexiBookApplication.getflexibook();
		String user = fb.getOwner().getUsername();
		String currentUserString=FlexiBookApplication.getCurrentuser().getUsername();
		if(user.equals(currentUserString)==false) {
			throw new InvalidInputException("No permission to update business information");
		}
		DayOfWeek inputDayOfWeek=DayOfWeek.valueOf(ExistingDay);
		Time exiStTime=Time.valueOf(ExistingStartTime+":00");
		DayOfWeek InputDay=DayOfWeek.valueOf(newDay);
		Time inputStTime=Time.valueOf(newstartTime+":00");
		Time inputEdTime=Time.valueOf(newEndTime+":00");
		if(inputStTime.before(inputEdTime)==false) {
			throw new InvalidInputException("Start time must be before end time");
		}
		Business business= fb.getBusiness();
		List<BusinessHour> aHours=business.getBusinessHours();
		BusinessHour thisBusinessHour=null;Boolean existBoolean=false;
		for(BusinessHour aHour:aHours) {
			DayOfWeek dayOfWeek=aHour.getDayOfWeek();
			Time startTime=aHour.getStartTime();
			Time endTime=aHour.getEndTime();
			if(dayOfWeek.equals(inputDayOfWeek)) {
				if(exiStTime.after(startTime)==false&&exiStTime.before(startTime)==false) {
					existBoolean=true;
					thisBusinessHour=aHour;

				}else {
					existBoolean=false;
				}
			}
			if(InputDay.equals(dayOfWeek)) {
				if(existBoolean==false) {
					if(!(inputEdTime.before(startTime)||inputStTime.after(endTime))) {
						throw new InvalidInputException("The business hours cannot overlap");
					}}

			}
		}
		thisBusinessHour.setStartTime(inputStTime);
		thisBusinessHour.setEndTime(inputEdTime);
		FlexiBookApplication.setmessage("");	

	}
	/**
	 * This method takes all parameters to set the business information in the system.
	 * 
	 * @author Zhixin Xiong
	 * 
	 *
	 **
	 * @param Day
	 * @param starTime
	 * @throws InvalidInputException
	 */
	public static void removerBusinessHour(String Day,String starTime) throws InvalidInputException{
		FlexiBook fb = FlexiBookApplication.getflexibook();
		String user = fb.getOwner().getUsername();
		String currentUserString=FlexiBookApplication.getCurrentuser().getUsername();
		if(user.equals(currentUserString)==false) {
			throw new InvalidInputException("No permission to update business information");
		}
		DayOfWeek inputDayOfWeek=DayOfWeek.valueOf(Day);
		Time exiStTime=Time.valueOf(starTime+":00");
		BusinessHour toBeRemovedBusinessHour=null;
		Business business= fb.getBusiness();
		Boolean exiBoolean=false;
		List<BusinessHour> aHours=business.getBusinessHours();

		for(BusinessHour aHour:aHours) {
			DayOfWeek dayOfWeek=aHour.getDayOfWeek();
			Time startTime=aHour.getStartTime();
			if(inputDayOfWeek.equals(dayOfWeek)) {
				if(startTime.after(exiStTime)==false&&startTime.after(exiStTime)==false) {
					toBeRemovedBusinessHour=aHour;
					exiBoolean=true;
					break;
				}
			}
		}
		if(exiBoolean) {
			business.removeBusinessHour(toBeRemovedBusinessHour);
		}
	}
	/**
	 * This method takes all parameters to set the business information in the system.
	 * 
	 * @author Zhixin Xiong
	 * 
	 */
	/**
	 * @param type
	 * @param ExistingDate
	 * @param ExistingStartTime
	 * @param startDate
	 * @param startTime
	 * @param endDate
	 * @param endTime
	 * @throws InvalidInputException
	 */
	public static void updateHolidayOrVacation(String type, String ExistingDate, String ExistingStartTime, String startDate, String startTime, String endDate,String endTime)throws InvalidInputException {
		FlexiBook flexibook=FlexiBookApplication.getflexibook();
		String mString="";
		FlexiBookApplication.setmessage(mString);
		FlexiBookApplication.setmessage("");
		FlexiBook fb = FlexiBookApplication.getflexibook();
		String user = fb.getOwner().getUsername();
		String currentUserString=FlexiBookApplication.getCurrentuser().getUsername();
		String tString="Vacation";

		Date currenDate=Date.valueOf(SystemTime.getdate(SystemTime.getSysTime()));
		Business business=flexibook.getBusiness();
		List<TimeSlot> timeSlots;
		List<TimeSlot> anotherTimeSlots;
		TimeSlot thiSlot=null;
		if(type.equals("vacation")){
			timeSlots=business.getVacation();
			anotherTimeSlots=business.getHolidays();
		}else {
			tString="Holiday";
			timeSlots=business.getHolidays();
			anotherTimeSlots=business.getVacation();
		}
		Date staDate=Date.valueOf(startDate);
		Time staTime=Time.valueOf(startTime+":00");
		Date enDate=Date.valueOf(endDate);
		Time enTime=Time.valueOf(endTime+":00");
		Date existDate=Date.valueOf(ExistingDate);
		Time exisTime=Time.valueOf(ExistingStartTime+":00");

		if(user.equals(currentUserString)==false) {
			throw new InvalidInputException("No permission to update business information");
		}
		if(currenDate.before(staDate)==false) {
			if(tString.equals("Holiday")) {
				throw new InvalidInputException(tString+" cannot be in the past");
			}else {
				throw new InvalidInputException(tString+" cannot start in the past");
			}
		}
		if(staDate.before(enDate)==false) {
			if((staDate.after(enDate))||(staDate.after(enDate)==false&&staTime.before(enTime)==false)) 

				throw new InvalidInputException("Start time must be before end time");
		}

		//check whether this slot overlap other time slots
		Boolean overlapDifferBoolean=false;
		for(TimeSlot Slota:anotherTimeSlots) {
			if(!((Slota.getEndDate().after(staDate)==false)||(Slota.getStartDate().before(enDate)==false))){
				overlapDifferBoolean=true;	
			}

		}
		if(overlapDifferBoolean==true) {
			throw new InvalidInputException("Holiday and vacation times cannot overlap");
		}
		Boolean overlapSameBoolean=false;
		for(TimeSlot Slota:timeSlots) {

			if(!((Slota.getEndDate().after(staDate)==false)||(Slota.getStartDate().before(enDate)==false))){
				overlapSameBoolean=true;	
			}
			if(Slota.getStartDate().before(existDate)==false&&Slota.getStartDate().after(existDate)==false) {
				if(Slota.getStartTime().before(exisTime)==false&&Slota.getStartTime().after(exisTime)==false) {
					overlapSameBoolean=false;
					thiSlot=Slota;
				}
			}

		}
		if(overlapSameBoolean==true) {
			throw new InvalidInputException(tString+" times cannot overlap");
		}
		thiSlot.setStartDate(staDate);
		thiSlot.setStartTime(staTime);
		thiSlot.setEndDate(enDate);
		thiSlot.setEndTime(enTime);
		FlexiBookApplication.setmessage("");
	}   

	/**
	 * This method takes all parameters to set the business information in the system.
	 * 
	 * @author Zhixin Xiong
	 * @param name
	 * @param address
	 * @param phoneNumber
	 * @param email
	 * @throws InvalidInputException
	 */
	public static void setBusinessInformation(String name, String address, String phoneNumber, String email)throws InvalidInputException {
		FlexiBook fb = FlexiBookApplication.getflexibook();
		String user = fb.getOwner().getUsername();
		String currentUserString=FlexiBookApplication.getCurrentuser().getUsername();
		if(user.equals(currentUserString)==false) {
			throw new InvalidInputException("No permission to set up business information");
		}
		String aString="@gmail.com" ;
		int begin=email.length()-aString.length();
		int end=email.length();
		if(begin<=0) {
			throw new InvalidInputException("Invalid email");
		}else {
			String subString =email.substring(begin,end);
			if(subString.equals(aString)==false) {
				throw new InvalidInputException("Invalid email");
			}
		}

		Business newBusiness=new Business(name, address, phoneNumber, email,fb );
		fb.setBusiness(newBusiness);	
		FlexiBookApplication.setmessage("");


	}
	/**
	 * This method takes all parameters to set the business information in the system.
	 * 
	 * @author Zhixin Xiong
	 * 
	 */

	/**
	 * @param string
	 * @param string2
	 * @param string3
	 * @throws InvalidInputException
	 */
	public static void addNewBusinessHour(String string, String string2, String string3)throws InvalidInputException {
		FlexiBook fb = FlexiBookApplication.getflexibook();
		String user = fb.getOwner().getUsername();
		String currentUserString=FlexiBookApplication.getCurrentuser().getUsername();
		if(user.equals(currentUserString)==false) {
			throw new InvalidInputException("No permission to update business information");
		}
		DayOfWeek inputDayOfWeek=DayOfWeek.valueOf(string);
		Time inputStTime=Time.valueOf(string2+":00");
		Time inputEdTime=Time.valueOf(string3+":00");
		if(inputStTime.before(inputEdTime)==false) {
			throw new InvalidInputException("Start time must be before end time");
		}
		Business business= fb.getBusiness();
		BusinessHour aHour=business.getBusinessHour(0);
		DayOfWeek dayOfWeek=aHour.getDayOfWeek();
		Time startTime=aHour.getStartTime();
		Time endTime=aHour.getEndTime();
		if(dayOfWeek.equals(inputDayOfWeek)) {
			if(inputEdTime.before(endTime)&&inputEdTime.after(startTime)) {
				throw new InvalidInputException("The business hours cannot overlap");
			}
		}
		BusinessHour newHour=new BusinessHour(inputDayOfWeek, inputStTime, inputEdTime, fb);
		business.addBusinessHour(newHour);
		fb.addHour(newHour);
		FlexiBookApplication.setmessage("");	

	}
	/**
	 * This method takes all parameters to set the business information in the system.
	 * 
	 * @author Zhixin Xiong
	 * 
	 */
	/**
	 * @param type
	 * @param startDate
	 * @param startTime
	 * @param endDate
	 * @param endTime
	 * @throws InvalidInputException
	 */
	/**
	 * @param type
	 * @param startDate
	 * @param startTime
	 * @param endDate
	 * @param endTime
	 * @throws InvalidInputException
	 */
	public static void removeExistingTimeSlot(String type, String startDate, String startTime, String endDate, String endTime) throws InvalidInputException {
		FlexiBook fb = FlexiBookApplication.getflexibook();
		String user = fb.getOwner().getUsername();
		String currentUserString=FlexiBookApplication.getCurrentuser().getUsername();
		if(user.equals(currentUserString)==false) {
			throw new InvalidInputException("No permission to update business information");
		}

		FlexiBook flexibook=FlexiBookApplication.getflexibook();

		Business business=flexibook.getBusiness();
		List<TimeSlot> timeSlots;
		Boolean isvaBoolean=true;
		if(type.equals("vacation")){
			timeSlots=business.getVacation();
		}else {
			isvaBoolean=false;
			timeSlots=business.getHolidays();
		}
		Date staDate=Date.valueOf(startDate);
		Time staTime=Time.valueOf(startTime+":00");
		Date enDate=Date.valueOf(endDate);
		Time enTime=Time.valueOf(endTime+":00");


		Boolean exiBoolean=false;
		TimeSlot thisTimeSlot=null;
		for(TimeSlot Slota:timeSlots) {

			if((Slota.getEndDate().after(enDate)==false)&&(Slota.getEndDate().before(enDate)==false)&&(Slota.getStartDate().before(staDate)==false)&&(Slota.getStartDate().after(staDate)==false)){
				if(Slota.getStartTime().before(staTime)==false&&Slota.getStartTime().after(staTime)==false&&Slota.getEndTime().before(enTime)==false&&Slota.getEndTime().after(enTime)==false) {
					thisTimeSlot=Slota;
					exiBoolean=true;
				}
			}
		}
		if(exiBoolean) {
			if(isvaBoolean) {
				business.removeVacation(thisTimeSlot);
			}else {
				business.removeHoliday(thisTimeSlot);
			}
		}
	}
	/**
	 * This method takes all parameters to set the business information in the system.
	 * 
	 * 
	 * @author Zhixin Xiong
	 * @return
	 */
	public static String[] viewBusinessInfor () {
		FlexiBook flexiBook=FlexiBookApplication.getflexibook();
		Business business=flexiBook.getBusiness();
		String[]busiStrings=new String[4];
		busiStrings[0]=business.getName();
		busiStrings[1]=business.getAddress();
		busiStrings[2]=business.getPhoneNumber();
		busiStrings[3]=business.getEmail();
		return busiStrings;

	}


}


