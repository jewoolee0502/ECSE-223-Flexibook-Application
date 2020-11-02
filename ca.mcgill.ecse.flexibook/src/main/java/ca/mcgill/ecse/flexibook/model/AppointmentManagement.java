/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.30.1.5099.60569f335 modeling language!*/

package ca.mcgill.ecse.flexibook.model;
import java.util.*;

// line 3 "../../../../../FlexiBookStates.ump"
public class AppointmentManagement
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //AppointmentManagement State Machines
  public enum AppointmentStatus { Booking, ChangeAppointment, CancelAppointment, CheckArrival, AppointmentStart, ChangeAppointmentDuring, AppointmentEnded, AppointmentVanish }
  private AppointmentStatus appointmentStatus;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public AppointmentManagement()
  {
    setAppointmentStatus(AppointmentStatus.Booking);
  }

  //------------------------
  // INTERFACE
  //------------------------

  public String getAppointmentStatusFullName()
  {
    String answer = appointmentStatus.toString();
    return answer;
  }

  public AppointmentStatus getAppointmentStatus()
  {
    return appointmentStatus;
  }

  public boolean MakeAppointment(Customer customer,BookableService service,TimeSlot timeslot)
  {
    boolean wasEventProcessed = false;
    
    AppointmentStatus aAppointmentStatus = appointmentStatus;
    switch (aAppointmentStatus)
    {
      case Booking:
        // line 10 "../../../../../FlexiBookStates.ump"
        doBookAppointment(customer, service, timeslot);
        setAppointmentStatus(AppointmentStatus.Booking);
        wasEventProcessed = true;
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  public boolean MakeAppointmentFinish()
  {
    boolean wasEventProcessed = false;
    
    AppointmentStatus aAppointmentStatus = appointmentStatus;
    switch (aAppointmentStatus)
    {
      case Booking:
        setAppointmentStatus(AppointmentStatus.CheckArrival);
        wasEventProcessed = true;
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  public boolean CheckCancel()
  {
    boolean wasEventProcessed = false;
    
    AppointmentStatus aAppointmentStatus = appointmentStatus;
    switch (aAppointmentStatus)
    {
      case Booking:
        setAppointmentStatus(AppointmentStatus.CancelAppointment);
        wasEventProcessed = true;
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  public boolean CheckChange()
  {
    boolean wasEventProcessed = false;
    
    AppointmentStatus aAppointmentStatus = appointmentStatus;
    switch (aAppointmentStatus)
    {
      case Booking:
        setAppointmentStatus(AppointmentStatus.ChangeAppointment);
        wasEventProcessed = true;
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  public boolean ChangeDatetime()
  {
    boolean wasEventProcessed = false;
    
    AppointmentStatus aAppointmentStatus = appointmentStatus;
    switch (aAppointmentStatus)
    {
      case ChangeAppointment:
        setAppointmentStatus(AppointmentStatus.CheckArrival);
        wasEventProcessed = true;
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  public boolean ReChangeDatetime()
  {
    boolean wasEventProcessed = false;
    
    AppointmentStatus aAppointmentStatus = appointmentStatus;
    switch (aAppointmentStatus)
    {
      case ChangeAppointment:
        setAppointmentStatus(AppointmentStatus.ChangeAppointment);
        wasEventProcessed = true;
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  public boolean ChangeCombo()
  {
    boolean wasEventProcessed = false;
    
    AppointmentStatus aAppointmentStatus = appointmentStatus;
    switch (aAppointmentStatus)
    {
      case ChangeAppointment:
        setAppointmentStatus(AppointmentStatus.CheckArrival);
        wasEventProcessed = true;
        break;
      case ChangeAppointmentDuring:
        setAppointmentStatus(AppointmentStatus.AppointmentEnded);
        wasEventProcessed = true;
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  public boolean ReChangeCombo()
  {
    boolean wasEventProcessed = false;
    
    AppointmentStatus aAppointmentStatus = appointmentStatus;
    switch (aAppointmentStatus)
    {
      case ChangeAppointment:
        setAppointmentStatus(AppointmentStatus.ChangeAppointment);
        wasEventProcessed = true;
        break;
      case ChangeAppointmentDuring:
        setAppointmentStatus(AppointmentStatus.ChangeAppointmentDuring);
        wasEventProcessed = true;
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  public boolean Cancel()
  {
    boolean wasEventProcessed = false;
    
    AppointmentStatus aAppointmentStatus = appointmentStatus;
    switch (aAppointmentStatus)
    {
      case CancelAppointment:
        setAppointmentStatus(AppointmentStatus.AppointmentVanish);
        wasEventProcessed = true;
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  public boolean ReBook()
  {
    boolean wasEventProcessed = false;
    
    AppointmentStatus aAppointmentStatus = appointmentStatus;
    switch (aAppointmentStatus)
    {
      case CancelAppointment:
        setAppointmentStatus(AppointmentStatus.Booking);
        wasEventProcessed = true;
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  public boolean OwnerCheckArrival()
  {
    boolean wasEventProcessed = false;
    
    AppointmentStatus aAppointmentStatus = appointmentStatus;
    switch (aAppointmentStatus)
    {
      case CheckArrival:
        setAppointmentStatus(AppointmentStatus.AppointmentStart);
        wasEventProcessed = true;
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  public boolean OwnerCheckNoArrival()
  {
    boolean wasEventProcessed = false;
    
    AppointmentStatus aAppointmentStatus = appointmentStatus;
    switch (aAppointmentStatus)
    {
      case CheckArrival:
        setAppointmentStatus(AppointmentStatus.AppointmentVanish);
        wasEventProcessed = true;
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  public boolean ChangeNeeded()
  {
    boolean wasEventProcessed = false;
    
    AppointmentStatus aAppointmentStatus = appointmentStatus;
    switch (aAppointmentStatus)
    {
      case AppointmentStart:
        setAppointmentStatus(AppointmentStatus.ChangeAppointmentDuring);
        wasEventProcessed = true;
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  public boolean NoChangeNeeded()
  {
    boolean wasEventProcessed = false;
    
    AppointmentStatus aAppointmentStatus = appointmentStatus;
    switch (aAppointmentStatus)
    {
      case AppointmentStart:
        setAppointmentStatus(AppointmentStatus.AppointmentEnded);
        wasEventProcessed = true;
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  public boolean OwnerEnd()
  {
    boolean wasEventProcessed = false;
    
    AppointmentStatus aAppointmentStatus = appointmentStatus;
    switch (aAppointmentStatus)
    {
      case AppointmentEnded:
        setAppointmentStatus(AppointmentStatus.AppointmentVanish);
        wasEventProcessed = true;
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  private void setAppointmentStatus(AppointmentStatus aAppointmentStatus)
  {
    appointmentStatus = aAppointmentStatus;
  }

  public void delete()
  {}


  /**
   * 
   * @author
   * 
   * @Param customer - 
   * @Param BookableService - 
   * @Param timeslot -
   * @Param flexibook - 
   * 
   */
  // line 72 "../../../../../FlexiBookStates.ump"
   private void doBookAppointment(Customer customer, BookableService service, TimeSlot timeslot){
    flexibook.addAppointment(customer, service, timeslot);
  }

}