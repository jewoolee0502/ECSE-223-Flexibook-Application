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
   * @param String owername, String comboname, String mainservicename, String Services, String mandatorySetting
   * @throws InvalidInputException an error is encountered
   * @return void
   */
  public static void makecombo(String string, String string2, String string3, String string4, String string5) throws InvalidInputException {
  Service mainservice = null;
  ComboItem main;
  FlexiBook fb = FlexiBookApplication.getflexibook();
  String mainname = null;
  if(fb.getOwner().getUsername().equals(string)) {
    for(int j =0;j<string3.length();j++) {
    if(j>3) {
     mainname+= string3.charAt(j);
    }
  }
  
char[] array=new char[mainname.length()-4];
mainname.getChars(4, mainname.length(), array, 0);
String nameofmain=new String(array);
 
   for (int i =0;i<fb.getBookableServices().size();i++) {
      
      if(fb.getBookableService(i).getName().equals(nameofmain)) {
        mainservice = (Service) fb.getBookableService(i);
      }
    }
if(mainservice!=null) {
 main =new ComboItem(true, mainservice); }
else {
  System.out.println(nameofmain);
  throw new InvalidInputException("Service "+nameofmain+" does not exist");
}
 ArrayList <ComboItem> items=new ArrayList();
    String[] parts = string4.split(","); 
    String[] setting = string5.split(",");
    for (int k=0;k<parts.length;k++) {
      for (int i =0;i<fb.getBookableServices().size();i++) {
        if(fb.getBookableService(i).getName().equals(parts[k])) {
          Service thissub=(Service) fb.getBookableService(i);
          ComboItem thisubservice=new ComboItem(Boolean.parseBoolean(setting[k]),thissub);
         items.add(thisubservice);
         if(thisubservice.getService().getName().equals(nameofmain)) {
           main=thisubservice;
           if(main.getMandatory()!=true) {
             throw new InvalidInputException("Main service must be mandatory");
           }
         }
        }else if(fb.getBookableService(i).getWithName(parts[k])==null) {
          throw new InvalidInputException("Service "+ parts[k] +" does not exist");
        }
      }
     
    }
    if(items.contains(main)!=true) {
      throw new InvalidInputException("Main service must be included in the services");
    }
    if(items.size()<2) {
      throw new InvalidInputException("A service Combo must contain at least 2 services");  
    }
    ComboItem[] comboitems=items.toArray(new ComboItem[items.size()]);

   if(fb.getBookableServices()!=null) {
     if(fb.getBookableService(0).getWithName(string2)!=null)
     {String thiss=string2;
       fb.getBookableService(0).getWithName(string2).setName(thiss+"1");
       throw new InvalidInputException("Service combo "+string2+ " already exists");
     }
   }
    ServiceCombo thiscombo=new ServiceCombo(string2, fb,main , comboitems);
    int checkmain =0;

thiscombo.setFlexiBook(fb);
 } else {throw new InvalidInputException("You are not authorized to perform this operation");}
}
  public static void updatecombo(String string, String string2, String string3, String string4, String string5, String string6) throws InvalidInputException {
    FlexiBook fb = FlexiBookApplication.getflexibook();
if(fb.getBookableServices().size()!=0) {
  if(fb.getBookableService(0).getWithName(string2)!=null) {
    fb.getBookableService(0).getWithName(string2).delete();
    makecombo(string,string3,string4,string5,string6);
  }else {
    makecombo(string,string3,string4,string5,string6);
  }
}
    
  }
  
  public static Boolean AttemptLogIn(String userID,String passcode) {
	  FlexiBook flexi=FlexiBookApplication.getflexibook();
	  for(Customer c:flexi.getCustomers()) {
		  if(c.getUsername().equals(userID)||c.getPassword().equals(passcode)) {
			 
		    return true;
		  }
	  }
	  return false;
  }
}
