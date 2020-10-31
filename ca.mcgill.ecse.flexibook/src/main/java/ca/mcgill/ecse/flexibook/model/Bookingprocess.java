/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.30.1.5099.60569f335 modeling language!*/

package ca.mcgill.ecse.flexibook.model;
import ca.mcgill.ecse.flexibook.application;
import ca.mcgill.ecse.flexibook.Controller;
import ca.mcgill.ecse.flexibook.model;
import ca.mcgill.ecse.flexibook.util;
import java.util.*;

// line 2 "../../../../../booingprocess.ump"
public class Bookingprocess
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Bookingprocess State Machines
  public enum Appointstatus { booking, appointmentstart, appointmentvanish }
  public enum AppointstatusBooking { Null, makeappointment, changeappointment, cancelappointment }
  public enum AppointstatusAppointmentstart { Null, checkarrival, changeappointmentduring, appointmentended }
  private Appointstatus appointstatus;
  private AppointstatusBooking appointstatusBooking;
  private AppointstatusAppointmentstart appointstatusAppointmentstart;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Bookingprocess()
  {
    setAppointstatusBooking(AppointstatusBooking.Null);
    setAppointstatusAppointmentstart(AppointstatusAppointmentstart.Null);
    setAppointstatus(Appointstatus.booking);
  }

  //------------------------
  // INTERFACE
  //------------------------

  public String getAppointstatusFullName()
  {
    String answer = appointstatus.toString();
    if (appointstatusBooking != AppointstatusBooking.Null) { answer += "." + appointstatusBooking.toString(); }
    if (appointstatusAppointmentstart != AppointstatusAppointmentstart.Null) { answer += "." + appointstatusAppointmentstart.toString(); }
    return answer;
  }

  public Appointstatus getAppointstatus()
  {
    return appointstatus;
  }

  public AppointstatusBooking getAppointstatusBooking()
  {
    return appointstatusBooking;
  }

  public AppointstatusAppointmentstart getAppointstatusAppointmentstart()
  {
    return appointstatusAppointmentstart;
  }

  public boolean makeappoitment()
  {
    boolean wasEventProcessed = false;
    
    AppointstatusBooking aAppointstatusBooking = appointstatusBooking;
    switch (aAppointstatusBooking)
    {
      case makeappointment:
        exitAppointstatusBooking();
        setAppointstatusBooking(AppointstatusBooking.makeappointment);
        wasEventProcessed = true;
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  public boolean checkcancel()
  {
    boolean wasEventProcessed = false;
    
    AppointstatusBooking aAppointstatusBooking = appointstatusBooking;
    switch (aAppointstatusBooking)
    {
      case makeappointment:
        exitAppointstatusBooking();
        setAppointstatusBooking(AppointstatusBooking.cancelappointment);
        wasEventProcessed = true;
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  public boolean checkchange()
  {
    boolean wasEventProcessed = false;
    
    AppointstatusBooking aAppointstatusBooking = appointstatusBooking;
    switch (aAppointstatusBooking)
    {
      case makeappointment:
        exitAppointstatusBooking();
        setAppointstatusBooking(AppointstatusBooking.changeappointment);
        wasEventProcessed = true;
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  public boolean changeservce()
  {
    boolean wasEventProcessed = false;
    
    AppointstatusBooking aAppointstatusBooking = appointstatusBooking;
    switch (aAppointstatusBooking)
    {
      case changeappointment:
        exitAppointstatus();
        setAppointstatusAppointmentstart(AppointstatusAppointmentstart.checkarrival);
        wasEventProcessed = true;
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  public boolean changedatetime()
  {
    boolean wasEventProcessed = false;
    
    AppointstatusBooking aAppointstatusBooking = appointstatusBooking;
    switch (aAppointstatusBooking)
    {
      case changeappointment:
        exitAppointstatus();
        setAppointstatusAppointmentstart(AppointstatusAppointmentstart.checkarrival);
        wasEventProcessed = true;
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  public boolean changecombo()
  {
    boolean wasEventProcessed = false;
    
    AppointstatusBooking aAppointstatusBooking = appointstatusBooking;
    switch (aAppointstatusBooking)
    {
      case changeappointment:
        exitAppointstatus();
        setAppointstatusAppointmentstart(AppointstatusAppointmentstart.checkarrival);
        wasEventProcessed = true;
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  public boolean cancel()
  {
    boolean wasEventProcessed = false;
    
    AppointstatusBooking aAppointstatusBooking = appointstatusBooking;
    switch (aAppointstatusBooking)
    {
      case cancelappointment:
        exitAppointstatus();
        setAppointstatus(Appointstatus.appointmentvanish);
        wasEventProcessed = true;
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  public boolean ownercheck()
  {
    boolean wasEventProcessed = false;
    
    AppointstatusAppointmentstart aAppointstatusAppointmentstart = appointstatusAppointmentstart;
    switch (aAppointstatusAppointmentstart)
    {
      case checkarrival:
        exitAppointstatusAppointmentstart();
        setAppointstatusAppointmentstart(AppointstatusAppointmentstart.changeappointmentduring);
        wasEventProcessed = true;
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  public boolean change()
  {
    boolean wasEventProcessed = false;
    
    AppointstatusAppointmentstart aAppointstatusAppointmentstart = appointstatusAppointmentstart;
    switch (aAppointstatusAppointmentstart)
    {
      case changeappointmentduring:
        exitAppointstatusAppointmentstart();
        setAppointstatusAppointmentstart(AppointstatusAppointmentstart.appointmentended);
        wasEventProcessed = true;
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  public boolean end()
  {
    boolean wasEventProcessed = false;
    
    AppointstatusAppointmentstart aAppointstatusAppointmentstart = appointstatusAppointmentstart;
    switch (aAppointstatusAppointmentstart)
    {
      case appointmentended:
        exitAppointstatus();
        setAppointstatus(Appointstatus.appointmentvanish);
        wasEventProcessed = true;
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  private void exitAppointstatus()
  {
    switch(appointstatus)
    {
      case booking:
        exitAppointstatusBooking();
        break;
      case appointmentstart:
        exitAppointstatusAppointmentstart();
        break;
    }
  }

  private void setAppointstatus(Appointstatus aAppointstatus)
  {
    appointstatus = aAppointstatus;

    // entry actions and do activities
    switch(appointstatus)
    {
      case booking:
        if (appointstatusBooking == AppointstatusBooking.Null) { setAppointstatusBooking(AppointstatusBooking.makeappointment); }
        break;
      case appointmentstart:
        if (appointstatusAppointmentstart == AppointstatusAppointmentstart.Null) { setAppointstatusAppointmentstart(AppointstatusAppointmentstart.checkarrival); }
        break;
    }
  }

  private void exitAppointstatusBooking()
  {
    switch(appointstatusBooking)
    {
      case makeappointment:
        setAppointstatusBooking(AppointstatusBooking.Null);
        break;
      case changeappointment:
        setAppointstatusBooking(AppointstatusBooking.Null);
        break;
      case cancelappointment:
        setAppointstatusBooking(AppointstatusBooking.Null);
        break;
    }
  }

  private void setAppointstatusBooking(AppointstatusBooking aAppointstatusBooking)
  {
    appointstatusBooking = aAppointstatusBooking;
    if (appointstatus != Appointstatus.booking && aAppointstatusBooking != AppointstatusBooking.Null) { setAppointstatus(Appointstatus.booking); }
  }

  private void exitAppointstatusAppointmentstart()
  {
    switch(appointstatusAppointmentstart)
    {
      case checkarrival:
        setAppointstatusAppointmentstart(AppointstatusAppointmentstart.Null);
        break;
      case changeappointmentduring:
        setAppointstatusAppointmentstart(AppointstatusAppointmentstart.Null);
        break;
      case appointmentended:
        setAppointstatusAppointmentstart(AppointstatusAppointmentstart.Null);
        break;
    }
  }

  private void setAppointstatusAppointmentstart(AppointstatusAppointmentstart aAppointstatusAppointmentstart)
  {
    appointstatusAppointmentstart = aAppointstatusAppointmentstart;
    if (appointstatus != Appointstatus.appointmentstart && aAppointstatusAppointmentstart != AppointstatusAppointmentstart.Null) { setAppointstatus(Appointstatus.appointmentstart); }
  }

  public void delete()
  {}

}