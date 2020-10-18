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
	//Example I found online
	/*public static void main(String[] args) {  
	    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");  
	    Date date = new Date();  
	    System.out.println(formatter.format(date));  
	}  */
}
