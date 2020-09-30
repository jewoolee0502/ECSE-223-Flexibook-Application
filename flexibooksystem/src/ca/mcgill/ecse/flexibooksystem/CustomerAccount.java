/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.30.1.5099.60569f335 modeling language!*/

package ca.mcgill.ecse.flexibooksystem;
import java.util.*;
import java.sql.Time;
import java.sql.Date;

// line 17 "../../../../flexibook.ump"
public class CustomerAccount extends Account
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //CustomerAccount Attributes
  private boolean isDeleted;

  //CustomerAccount Associations
  private List<Appointment> appointments;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public CustomerAccount(String aUsername, String aPassword, boolean aIsLoggedIn, FlexiBook aFlexiBook, boolean aIsDeleted)
  {
    super(aUsername, aPassword, aIsLoggedIn, aFlexiBook);
    isDeleted = aIsDeleted;
    appointments = new ArrayList<Appointment>();
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setIsDeleted(boolean aIsDeleted)
  {
    boolean wasSet = false;
    isDeleted = aIsDeleted;
    wasSet = true;
    return wasSet;
  }

  public boolean getIsDeleted()
  {
    return isDeleted;
  }
  /* Code from template attribute_IsBoolean */
  public boolean isIsDeleted()
  {
    return isDeleted;
  }
  /* Code from template association_GetMany */
  public Appointment getAppointment(int index)
  {
    Appointment aAppointment = appointments.get(index);
    return aAppointment;
  }

  public List<Appointment> getAppointments()
  {
    List<Appointment> newAppointments = Collections.unmodifiableList(appointments);
    return newAppointments;
  }

  public int numberOfAppointments()
  {
    int number = appointments.size();
    return number;
  }

  public boolean hasAppointments()
  {
    boolean has = appointments.size() > 0;
    return has;
  }

  public int indexOfAppointment(Appointment aAppointment)
  {
    int index = appointments.indexOf(aAppointment);
    return index;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfAppointments()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public Appointment addAppointment(Time aBegintime, Time aEndtime, Date aDate, Date aCancellationdate, boolean aIsCancelled)
  {
    return new Appointment(aBegintime, aEndtime, aDate, aCancellationdate, aIsCancelled, this);
  }

  public boolean addAppointment(Appointment aAppointment)
  {
    boolean wasAdded = false;
    if (appointments.contains(aAppointment)) { return false; }
    CustomerAccount existingCustomerAccount = aAppointment.getCustomerAccount();
    boolean isNewCustomerAccount = existingCustomerAccount != null && !this.equals(existingCustomerAccount);
    if (isNewCustomerAccount)
    {
      aAppointment.setCustomerAccount(this);
    }
    else
    {
      appointments.add(aAppointment);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeAppointment(Appointment aAppointment)
  {
    boolean wasRemoved = false;
    //Unable to remove aAppointment, as it must always have a customerAccount
    if (!this.equals(aAppointment.getCustomerAccount()))
    {
      appointments.remove(aAppointment);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addAppointmentAt(Appointment aAppointment, int index)
  {  
    boolean wasAdded = false;
    if(addAppointment(aAppointment))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfAppointments()) { index = numberOfAppointments() - 1; }
      appointments.remove(aAppointment);
      appointments.add(index, aAppointment);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveAppointmentAt(Appointment aAppointment, int index)
  {
    boolean wasAdded = false;
    if(appointments.contains(aAppointment))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfAppointments()) { index = numberOfAppointments() - 1; }
      appointments.remove(aAppointment);
      appointments.add(index, aAppointment);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addAppointmentAt(aAppointment, index);
    }
    return wasAdded;
  }

  public void delete()
  {
    while (appointments.size() > 0)
    {
      Appointment aAppointment = appointments.get(appointments.size() - 1);
      aAppointment.delete();
      appointments.remove(aAppointment);
    }
    
    super.delete();
  }


  public String toString()
  {
    return super.toString() + "["+
            "isDeleted" + ":" + getIsDeleted()+ "]";
  }
}