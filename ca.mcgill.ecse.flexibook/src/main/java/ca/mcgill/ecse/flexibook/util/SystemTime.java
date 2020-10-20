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
	  String[]parts=systime.split("+");
	  
	  return parts[0];
	}
	   public static String gettime(String systime) {
	      String[]parts=systime.split("+");
	      return parts[1];
	    }
	   public static int comparedate(String time1,String time2) {
	    int check=0;	     
	    String date2=time2.split("\\+")[0];
	    String date1=time1.split("\\+")[0];
	       String[] split1=date1.split("-"); 
	      String[] split2=date2.split("-");
	       if(Integer.decode(split1[0])>Integer.decode(split2[0])) {
	         check=1;
	       }else if(Integer.decode(split1[0])<Integer.decode(split2[0])) {
	         check=2;
	       }else {
	         if(Integer.decode(split1[1])>Integer.decode(split2[1])) {
	           check=1;
	         }else if(Integer.decode(split1[1])<Integer.decode(split2[1])) {
	           check=2;
	         }else {
	           if(Integer.decode(split1[2])>Integer.decode(split2[2])) {
	             check=1;
	           }else if(Integer.decode(split1[2])<Integer.decode(split2[2])) {
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
	        String t2=time2.split("\\+")[1];
	        String t1=time1.split("\\+")[1];
	          String[] split1=t1.split(":"); 
	          String[] split2=t2.split(":");
	          if(Integer.decode(split1[0])>Integer.decode(split2[0])) {
	             check=1;
	           }else if(Integer.decode(split1[0])<Integer.decode(split2[0])) {
	             check=2;
	           }else {
	             if(Integer.decode(split1[1])>Integer.decode(split2[1])) {
	               
	               check=1;
	             }else if(Integer.decode(split1[1])<Integer.decode(split2[1])) {
	               check=2;
	             }else {
	               check=0;
	             }
	   }return check;
}
}