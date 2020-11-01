/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.30.1.5099.60569f335 modeling language!*/

package ca.mcgill.ecse.flexibook.model;
import ca.mcgill.ecse.flexibook.application;
import ca.mcgill.ecse.flexibook.Controller;
import ca.mcgill.ecse.flexibook.model;
import ca.mcgill.ecse.flexibook.util;
import java.util.*;

// line 3 "../../../../../BookingProcess.ump"
public class Bookingprocess
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Bookingprocess State Machines
  public enum Appointmentvanish {  }
  private Appointmentvanish appointmentvanish;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Bookingprocess()
  {}

  //------------------------
  // INTERFACE
  //------------------------

  public String getAppointmentvanishFullName()
  {
    String answer = appointmentvanish.toString();
    return answer;
  }

  public Appointmentvanish getAppointmentvanish()
  {
    return appointmentvanish;
  }

  public void delete()
  {}
  
  //------------------------
  // DEVELOPER CODE - PROVIDED AS-IS
  //------------------------
  
  // line 12 "../../../../../BookingProcess.ump"
  appointmentStatus 
  {
    booking {
     		makeappointment {
    			makeappoitment->booking.makeappointment;
     			makeappoitment->appointmentstart.checkarrival;
     			checkcancel->booking.cancelappointment;
     			checkcancel->booking.makeappointment;
     			checkchange->booking.changeappointment;
     			checkchange->booking.makeappointment;
     		}
     
      
     		changeappointment {
     			changeservce->appointmentstart.checkarrival;
    			changeservce->changeappointment;
     			changedatetime->appointmentstart.checkarrival;
     			changedatetime->changeappointment;
     			changecombo->appointmentstart.checkarrival;
     			changecombo->changeappointment;
     		}
     
     
     		cancelappointment {
     			cancel->appointmentvanish;
     		}
         
		}
     
		appointmentStart {
     		// checkArrival {
     			// ownerCheck -> checkArrival;
     			// ownerCheck -> appointmentVanish; 
     			// cutomerCheck -> changeAppointmentDuring;
     			// cutomerCheck -> appointmentEnded;
     			
     			Arrived {
     				toggleArrivalStatus() -> NotArrived;
     				schedule(Service service, Date date, Time time) / {
     					doSchedule(service, date, time);
     				} -> Arrived;
     				
     			}
     			
     			NotArrived {
     				toggleArrivalStatus() -> Arrived;
     				schedule(Service service, Date date, Time time) / {
     					rejectSchedule(service, date, time);
     				} -> NotArrived;
     			
     			}
     		}
     		
     		private void doSchedule(Service service, Date date, Time time) {
     			//I don't know what to write here.
     		}
     		
     		private void rejectSchedule(Service service, Date date, Time time) {
     			throw new RuntimeException("The customer did not arrive.");
     		}
     
     
     		changeappointmentduring {
     			changecombo->appointmentended;
     			changecomboop->changeappointmentduring;
    		}
     
     
     		appointmentended {
     			end->appointmentvanish;
     		}
  }

  
}