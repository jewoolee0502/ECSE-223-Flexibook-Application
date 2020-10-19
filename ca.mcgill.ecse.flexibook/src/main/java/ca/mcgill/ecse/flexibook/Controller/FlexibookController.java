package ca.mcgill.ecse.flexibook.Controller;
import java.awt.*;
import java.util.List;
import com.google.common.base.CharMatcher;
import java.io.*;
import java.util.*;
import ca.mcgill.ecse.flexibook.application.FlexiBookApplication;
import ca.mcgill.ecse.flexibook.model.*;
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
							throw new InvalidInputException("A service Combo must contain at least 2 services");  
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

		else throw new InvalidInputException("Username/password not found");

		}
	public static boolean LogOut() throws InvalidInputException {
		FlexiBook flexi=FlexiBookApplication.getflexibook();
		if(FlexiBookApplication.getCurrentuser()==null) {
			throw new InvalidInputException("User is already logged out");
		}
		if(FlexiBookApplication.getCurrentuser()!=null) {
			FlexiBookApplication.setCurrentuser(null);
			return true;
		}
		throw new InvalidInputException("No user found with corresponding Username");
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
			if(customer.getUsername() == username) {
				break;
			}
		}
		return foundCustomer;
	}

	public static void SignUpForCustomerAccount(String username, String password) throws InvalidInputException {

		try {
			FlexiBook flexibook = FlexiBookApplication.getflexibook();

			if(FlexiBookApplication.getCurrentuser().equals(flexibook.getOwner())) {  
				throw new InvalidInputException("You must log out of the owner account before creating a customer account");
			}
			else {
				if(!(username.equals("") || username == null || password.equals("") || password == null)) {
					if(getCustomer(username) == null) {
						flexibook.addCustomer(username, password);
					}
					else {
						throw new InvalidInputException("The username already exists");
					}
				}
				else if(username.equals("") || username == null) {
					throw new InvalidInputException("The username cannot be empty");
				}
				else if(password.equals("") || password == null) {
					throw new InvalidInputException("The password cannot be empty");
				}
			}
		} 
		catch(RuntimeException e) {
			throw new InvalidInputException(e.getMessage());
		}
	}

	public static void UpdateAccount(String oldUsername, String newUsername, String newPassword) throws InvalidInputException {

		FlexiBook flexibook = FlexiBookApplication.getflexibook();
		User user = getCustomer(oldUsername);

		if(oldUsername.equals("owner")) {
			user = flexibook.getOwner();
		}
		else {
			user = getCustomer(oldUsername);
		}
		if(getCustomer(newUsername) != null) {
			throw new InvalidInputException("Username not available");
		}
		else if(user != null) {
			if(oldUsername.equals("owner") && (!newUsername.equals("owner"))) {
				throw new InvalidInputException("Changing username of owner is not allowed");	
			}
		}
		else if(newUsername.equals("") || newUsername == null) {
			throw new InvalidInputException("The username cannot be empty");
		}
		else if(newPassword.equals("") || newPassword == null) {
			throw new InvalidInputException("The password cannot be empty");
		}
		else if(getCustomer(newUsername) == null) {
			user.setUsername(newUsername);
			user.setPassword(newPassword);
		}
	}

	public static void DeleteCustomerAccount(String username, String target) throws InvalidInputException {

		FlexiBook flexibook = FlexiBookApplication.getflexibook();
		Customer user = getCustomer(username);

		if(user != null) {
			if(username.equals(target) && !(username.equals("owner"))) {
				for(Appointment appointment : user.getAppointments()) {
					appointment.delete();
				}
				user.delete();
			}
			else {
				throw new InvalidInputException("You do not have permission to delete this account");
			}
		}

	}

}
