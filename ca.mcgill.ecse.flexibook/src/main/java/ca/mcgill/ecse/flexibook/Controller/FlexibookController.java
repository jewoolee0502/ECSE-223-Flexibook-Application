package ca.mcgill.ecse.flexibook.Controller;
import java.awt.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.sql.Date;
import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
	public static void makecombo(String string, String string2, String string3, String string4, String string5) throws InvalidInputException {
		Service mainservice = null;
		ComboItem main=null;

		FlexiBook fb = FlexiBookApplication.getflexibook();
		String mainname = null;
		if(fb.getOwner().getUsername().equals(string)) {
			for(int j =0;j<string3.length();j++) {
				if(j>3) {
					mainname+= string3.charAt(j);
				}
			}    if(fb.getBookableServices().size()!=0) {
				if(fb.getBookableService(0).getWithName(string2)!=null)
				{
					throw new InvalidInputException("Service combo "+string2+ " already exists");
				}
			}
			ServiceCombo thiscombo=new ServiceCombo(string2, fb);


			char[] array=new char[mainname.length()-4];
			mainname.getChars(4, mainname.length(), array, 0);
			String nameofmain=new String(array);
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
		String mainname=null;
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
						for(int j =0;j<string4.length();j++) {
							if(j>3) {
								mainname+= string4.charAt(j);
							}
						} 
						char[] array=new char[mainname.length()-4];
						mainname.getChars(4, mainname.length(), array, 0);
						String nameofmain=new String(array);
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

	public static void CreateUser(String a, String b) throws InvalidInputException {
		FlexiBook fb=FlexiBookApplication.getflexibook();
		if(a==null|| a=="         "){
			throw new InvalidInputException("username");
		}else if(fb.getCustomers().size()>0) {
			if(fb.getCustomer(0).getWithUsername(a)!=null) {


			}
		}
		Customer thisc=new Customer(a, b, fb);
	}


	/**
	 * deletecombo: This method takes an input of username and a combo name. The method will decide whether to initiate the deleting method
	 * 
	 * @author Haipeng Yue
	 * 
	 * @param String username
	 * @param
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
	 * Customer: This method takes in all the parameters and looks for a customer account that has the same username.
	 * 
	 * @author Jewoo Lee
	 * 
	 * @param String username is the username of the customer account
	 * @throws InvalidInputException an error is encountered
	 * @return foundCustomer
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
				//				else if(getCustomer(username) != null) {
				//					throw new InvalidInputException("The username already exists");
				//				}
				//				else {
				//					flexibook.addCustomer(username, password);
				//				}
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

		if(fb.getBusiness()==null) {
			throw new InvalidInputException("The business should exist for making an appointment.");
		}
		if(customer.equals(fb.getOwner().getUsername())) {
			throw new InvalidInputException("An owner cannot make an appointment");
		}

		int cindex = -1;
		for(Customer c : fb.getCustomers()) {
			if(c.getUsername().equals(customer)) {
				cindex = fb.indexOfCustomer(c);
			}
		}
		if(cindex == -1) {
			throw new InvalidInputException("There is no customer ["+customer+"] exists.");
		}

		int sindex = -1;
		for(BookableService s : fb.getBookableServices()) {
			if(s.getName().equals(serviceName)) {
				sindex = fb.indexOfBookableService(s);
			}
		}
		if(sindex == -1) {
			throw new InvalidInputException("There is no bookable service["+serviceName+"] exists.");
		}


		Date servicedate = Date.valueOf(date);
		Time starttime = Time.valueOf(startTime+":00");
		Time endtime = null;
		int duration = 0;
		if(fb.getBookableService(sindex) instanceof Service) {
			Service service = (Service)fb.getBookableService(sindex);
			duration = service.getDuration();

		}else if(fb.getBookableService(sindex) instanceof ServiceCombo){
			ServiceCombo combo = (ServiceCombo)fb.getBookableService(sindex);

			duration = combo.getMainService().getService().getDuration();
			String[] optService = optionalServices.split(",");
			for(ComboItem item : combo.getServices()) {
				for(String str : optService) {
					if(str.equals(item.getService().getName())) {
						duration+=item.getService().getDuration();
					}
				}
			}
		}

		LocalTime localtime = starttime.toLocalTime();
		localtime.plusMinutes(duration);
		endtime = Time.valueOf(localtime);
		TimeSlot timeslot = new TimeSlot(servicedate,starttime,servicedate,endtime, fb);

		for(Appointment appointment : fb.getAppointments()) {
			TimeSlot slot = appointment.getTimeSlot();
			if(!isNoOverlap(timeslot,slot)) {
				throw new InvalidInputException("There are no available slots for "+serviceName+" on "+date+" at "+startTime);
			}
		}

		for(TimeSlot slot : fb.getBusiness().getHolidays()) {
			if(!isNoOverlap(timeslot,slot)) {
				throw new InvalidInputException("There are no available slots for "+serviceName+" on "+date+" at "+startTime);
			}
		}
		for(TimeSlot slot : fb.getBusiness().getVacation()) {
			if(!isNoOverlap(timeslot,slot)) {
				throw new InvalidInputException("There are no available slots for "+serviceName+" on "+date+" at "+startTime);
			}
		}

		//TODO kind of GOP
		/*int day = servicedate.getDay();
		if(day == 0 || day == 6) {
			throw new InvalidInputException("There are no available slots for "+serviceName+" on "+date+" at "+startTime);
		}*/

		Appointment appointment = new Appointment(fb.getCustomer(cindex), fb.getBookableService(sindex), timeslot, fb);

	}

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
	 * This method takes all parameters to update a service in the system.
	 * 
	 * @author Tianyu Zhao
	 * 
	 * @param ownername
	 * @param oldservicename
	 * @param newserviceName
	 * @param service
	 * @throws InvalidInputException
	 */
	public static void updateservice(String string, String string2, String string3, String string4, String string5,
			String string6) throws InvalidInputException {
		FlexiBook fb = FlexiBookApplication.getflexibook();
		String servicename = null;

		if(fb.getOwner().getUsername().equals(string)==true) {
			if(fb.getBookableServices().size()!=0) {
				if(fb.getBookableService(0).getWithName(string2)==null) {
					throw new InvalidInputException("Service does not exist");
				}else {
					if(string2.equals(string3)!=true) {
						fb.getBookableService(0).getWithName(string2).delete();
					}
					}

			}else {throw new InvalidInputException("You are not authorized to perform this operation");  
			}
		}
	}
	
	/**
	 * deleteService: This method takes an input of servicename. The method will decide whether to initiate the deleting method
	 * 
	 * @author Tianyu Zhao
	 * 
	 * @param String servicename
	 * @param ownername
	 * @throws InvalidInputException an error is encountered
	 * @return void
	 */

	public static void deleteService(String servicename) throws InvalidInputException {
		FlexiBook fb =FlexiBookApplication.getflexibook();
		String time=SystemTime.gettime(SystemTime.getSysTime());
		String date=SystemTime.getdate(SystemTime.getSysTime());
		if(servicename.equals(fb.getOwner().getUsername())==true) {
			if(fb.getBookableServices().size()!=0) {
				if(fb.getBookableService(0).getWithName(servicename)!=null) {
					if(fb.getBookableService(0).getWithName(servicename).getAppointments().size()>0) {
						for(int i=0;i<fb.getAppointments().size();i++) {
							String startdate=fb.getBookableService(0).getWithName(servicename).getAppointment(i).getTimeSlot().getStartDate().toString();
							if(SystemTime.comparedate(date,startdate)==2) {
								throw new InvalidInputException("Service "+servicename+ " has future appointments"); 
							}else if(SystemTime.comparedate(date,startdate)==1) {
								fb.getBookableService(0).getWithName(servicename).delete();
								break;
							}else if(SystemTime.comparedate(date,startdate)==0) {
								String starttime=fb.getBookableService(0).getWithName(servicename).getAppointment(i).getTimeSlot().getStartTime().toString();
								if(SystemTime.comparetime(time,starttime)==1) {
									fb.getBookableService(0).getWithName(servicename).delete();
									break;
								}else {
									throw new InvalidInputException("Service "+servicename+ " has future appointments");
								}
							}
						}
					}else{fb.getBookableService(0).getWithName(servicename).delete();}

				}
			}
		}else {
			throw new InvalidInputException("You are not authorized to perform this operation"); 
		}


	}
	
	/**
	 * This method takes all parameters to add a new service in the system.
	 * 
	 * @author Tianyu Zhao
	 * @param  ownername 
	 * @param  servicename
	 * @param  services
	 */
	public static void addService(String string, String string2, String string3) throws InvalidInputException {
		Service service = null;
		

		FlexiBook fb = FlexiBookApplication.getflexibook();
		String servicename = null;
		if(fb.getOwner().getUsername().equals(string)) {
			for(int j =0;j<string3.length();j++) {
				if(j>3) {
					servicename+= string3.charAt(j);
				}
			}    if(fb.getBookableServices().size()!=0) {
				if(fb.getBookableService(0).getWithName(string2)!=null)
				{
					throw new InvalidInputException("Service "+string2+ " already exists");
				}
			}
			Service thisservice=new Service(string2, fb, 0, 0, 0);


			char[] array=new char[servicename.length()-4];
			servicename.getChars(4,servicename.length(), array, 0);
			String nameofmain=new String(array);
			if(fb.getBookableService(0).getWithName(servicename)==null) {
				thisservice.delete();
				throw new InvalidInputException("Service "+servicename+" does not exist");
			}
			
		} else {
			throw new InvalidInputException("You are not authorized to perform this operation");}
	}
	
	/**
	 * This method takes all parameters to set the business information in the system.
	 * 
	 * @author Zhixin Xiong
	 * @param name 
	 * @param  address
	 * @param phone number
	 * @param email
	 */
	public static void setBusinessInformation(String string, String string2, String string3, String string4)throws InvalidInputException {
		FlexiBook fb = FlexiBookApplication.getflexibook();
		try {
			String user = fb.getOwner().getUsername();
			String currentUserString=FlexiBookApplication.getCurrentuser().getUsername();
		if(user.equals(currentUserString)==false) {
			throw new InvalidInputException("No permission to set up business information");
		}
		String aString="@gmail.com" ;
		int begin=string4.length()-aString.length();
		int end=string4.length();
		if(begin<=0) {
			throw new InvalidInputException("Invalid email");
		}else {
			String subString =string4.substring(begin,end);
			if(subString.equals(aString)==false) {
				throw new InvalidInputException("Invalid email");
		}
		}
		
		Business newBusiness=new Business(string, string2, string3, string4,fb );
		fb.setBusiness(newBusiness);	
		FlexiBookApplication.setmessage("");
	} catch (Exception e) {
		// TODO: handle exception
		String ebString=e.getMessage();
		FlexiBookApplication.setmessage(e.getMessage());
		String ab=FlexiBookApplication.returnmessage();
	}
	}
	public static void addNewBusinessHour(String string, String string2, String string3) {
		FlexiBook fb = FlexiBookApplication.getflexibook();
		try {
			Business business= fb.getBusiness();
	    	boolean hasSuchHour=false;
	    	if(business.getBusinessHours().size()!=0) {
				 for(BusinessHour hour:business.getBusinessHours()) {
					 if(hour.getDayOfWeek().equals(DayOfWeek.valueOf(string))&&hour.getStartTime().equals(Time.valueOf(string2+":00"))&&hour.getEndTime().equals(Time.valueOf(string3+":00"))) {
						 hasSuchHour=true;
						  return;
						 }
					 }
				 }
	    	if(hasSuchHour==false) {
	    		DayOfWeek dayOfWeek=DayOfWeek.valueOf(string);
	    		Time startTime=Time.valueOf(string2+":00");
	    		Time endTime=Time.valueOf(string3+":00");
	    		BusinessHour nHour=new BusinessHour(dayOfWeek, startTime, endTime,fb);
	    		business.addBusinessHour(nHour);
			  }
	} catch (Exception e) {
		// TODO: handle exception
		String ebString=e.getMessage();
		FlexiBookApplication.setmessage(e.getMessage());
		String ab=FlexiBookApplication.returnmessage();
	}
	}
		
	//	public static ArrayList<TimeSlot> getUnavailableTimeSlots (String username, String date)throws InvalidInputException{
	//		String error;
	//		FlexiBook flexibook=FlexiBookApplication.getflexibook();
	//		ArrayList<TimeSlot> list = new ArrayList<TimeSlot>();
	//		Date newDate = null;
	//		try {
	//		newDate=dateChecker(date);}
	//		catch(ParseException e) {
	//			throw new InvalidInputException(date+" is not a valid date");
	//		}
	//<<<<<<< HEAD
	//	}
	//
	//=======
	//		for(Appointment appointment:flexibook.getAppointments()) {
	//			if(appointment.getTimeSlot().getStartDate().equals(newDate)) {
	//				list.add(appointment.getTimeSlot());
	//				
	//			}
	//		}
	//		return list;
	//		}

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

	private static Date dateChecker(String date)throws ParseException , IllegalArgumentException{
		DateFormat formatDate = new SimpleDateFormat("yyyy-mm-dd");
		formatDate.parse(date);
		return Date.valueOf(date);
	}

}
