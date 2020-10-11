package ca.mcgill.ecse.flexibook.Controller;
import java.awt.*;
import java.util.List;
import com.google.common.base.CharMatcher;
import java.io.*;
import java.util.*;
import ca.mcgill.ecse.flexibook.application.FlexiBookApplication;
import ca.mcgill.ecse.flexibook.model.*;
public class FlexibookController {

  public static void makecombo(String string, String string2, String string3, String string4, String string5) {
  Service mainservice = null;
  
  FlexiBook fb = FlexiBookApplication.getflexibook();
  String mainname = null;
  for(int j =0;j<string3.length();j++) {
    if(j>3) {
     mainname+= string3.charAt(j);
    }
  }
char[] array=new char[mainname.length()-4];
mainname.getChars(4, mainname.length(), array, 0);
String nameofmain=new String(array);
  if(fb.getOwner().getUsername().equals(string)) {
    for (int i =0;i<fb.getBookableServices().size();i++) {
      
      if(fb.getBookableService(i).getName().equals(nameofmain)) {
        mainservice = (Service) fb.getBookableService(i);
      }
    }
    
    ComboItem main=new ComboItem(true, mainservice);
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
           thisubservice=main;
         }
        }
      }
     
    }
   
    ComboItem[] comboitems=items.toArray(new ComboItem[items.size()]);
ServiceCombo thiscombo=new ServiceCombo(string2, fb,main , comboitems);
thiscombo.setFlexiBook(fb);
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
