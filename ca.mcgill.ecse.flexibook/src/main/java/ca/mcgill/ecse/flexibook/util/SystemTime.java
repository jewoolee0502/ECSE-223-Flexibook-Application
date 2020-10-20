package ca.mcgill.ecse.flexibook.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class SystemTime {

	private static String SysTime;

	//this class is only used for define the system time and date
	//in clause: Given the system's time and date is ""
	
	//please be free to edit this file if you have a better idea
	//features related: ViewAppointmentCalendar; SetUpBusinessInfo; MakeAppointment
	public static void SystemTime(String systime, boolean istest) {
		if(istest) {
			SysTime = systime;
			
		}else {
			SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd+HH:mm");
			Date date = new Date();
			SysTime = dateFormatter.format(date);
		}
	}
	
	public static  String getSysTime() {
		return SysTime;
	}
	
	public static void setSysTime(String systime) {
		SysTime = systime;
	}
	public static String getdate(String systime) {
	  String[] parts1=systime.split("\\+");
	  
	  return parts1[0];
	}
	   public static String gettime(String systime) {
	      String[] parts2=systime.split("\\+");
	      return parts2[1];
	    }
	   public static int comparedate(String time1,String time2) {
	    int check=0;	     

	       String[] split1=time1.split("-"); 
	      String[] split2=time2.split("-");
	       if(Integer.parseInt(split1[0])>Integer.parseInt(split2[0])) {
	         check=1;
	       }else if(Integer.parseInt(split1[0])<Integer.parseInt(split2[0])) {
	         check=2;
	       }else {
	         if(Integer.parseInt(split1[1])>Integer.parseInt(split2[1])) {
	           check=1;
	         }else if(Integer.parseInt(split1[1])<Integer.parseInt(split2[1])) {
	           check=2;
	         }else {
	           if(Integer.parseInt(split1[2])>Integer.parseInt(split2[2])) {
	             check=1;
	           }else if(Integer.parseInt(split1[2])<Integer.parseInt(split2[2])) {
	             check=2;
	           }else {
	             check=0;
	           }
	         }
	       }
	     return check;
	     }
	   public static int comparetime(String time1,String time2) {
	     int check=0;       
	          String[] split1=time1.split(":"); 
	          String[] split2=time2.split(":");
	          if(Integer.parseInt(split1[0])>Integer.parseInt(split2[0])) {
	             check=1;
	           }else if(Integer.parseInt(split1[0])<Integer.parseInt(split2[0])) {
	             check=2;
	           }else {
	             if(Integer.parseInt(split1[1])>Integer.parseInt(split2[1])) {
	               
	               check=1;
	             }else if(Integer.parseInt(split1[1])<Integer.parseInt(split2[1])) {
	               check=2;
	             }else {
	               check=0;
	             }
	   }return check;
}
}