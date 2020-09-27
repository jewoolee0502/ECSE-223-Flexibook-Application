/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.30.1.5099.60569f335 modeling language!*/

package ca.mcgill.ecse.flexibooksystem;
import java.sql.Time;
import java.sql.Date;
import java.util.*;

// line 33 "../../../../flexibook.ump"
public class Appointment
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Appointment Attributes
  private Time begintime;
  private Time endtime;
  private Date date;
  private Date cancellationdate;
  private boolean isCancelled;

  //Appointment Associations
  private List<ServiceCombo> choosecombo;
  private List<Service> services;
  private CustomerAccount customerAccount;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Appointment(Time aBegintime, Time aEndtime, Date aDate, Date aCancellationdate, boolean aIsCancelled, CustomerAccount aCustomerAccount)
  {
    begintime = aBegintime;
    endtime = aEndtime;
    date = aDate;
    cancellationdate = aCancellationdate;
    isCancelled = aIsCancelled;
    choosecombo = new ArrayList<ServiceCombo>();
    services = new ArrayList<Service>();
    boolean didAddCustomerAccount = setCustomerAccount(aCustomerAccount);
    if (!didAddCustomerAccount)
    {
      throw new RuntimeException("Unable to create appointment due to customerAccount. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setBegintime(Time aBegintime)
  {
    boolean wasSet = false;
    begintime = aBegintime;
    wasSet = true;
    return wasSet;
  }

  public boolean setEndtime(Time aEndtime)
  {
    boolean wasSet = false;
    endtime = aEndtime;
    wasSet = true;
    return wasSet;
  }

  public boolean setDate(Date aDate)
  {
    boolean wasSet = false;
    date = aDate;
    wasSet = true;
    return wasSet;
  }

  public boolean setCancellationdate(Date aCancellationdate)
  {
    boolean wasSet = false;
    cancellationdate = aCancellationdate;
    wasSet = true;
    return wasSet;
  }

  public boolean setIsCancelled(boolean aIsCancelled)
  {
    boolean wasSet = false;
    isCancelled = aIsCancelled;
    wasSet = true;
    return wasSet;
  }

  public Time getBegintime()
  {
    return begintime;
  }

  public Time getEndtime()
  {
    return endtime;
  }

  public Date getDate()
  {
    return date;
  }

  public Date getCancellationdate()
  {
    return cancellationdate;
  }

  public boolean getIsCancelled()
  {
    return isCancelled;
  }
  /* Code from template attribute_IsBoolean */
  public boolean isIsCancelled()
  {
    return isCancelled;
  }
  /* Code from template association_GetMany */
  public ServiceCombo getChoosecombo(int index)
  {
    ServiceCombo aChoosecombo = choosecombo.get(index);
    return aChoosecombo;
  }

  public List<ServiceCombo> getChoosecombo()
  {
    List<ServiceCombo> newChoosecombo = Collections.unmodifiableList(choosecombo);
    return newChoosecombo;
  }

  public int numberOfChoosecombo()
  {
    int number = choosecombo.size();
    return number;
  }

  public boolean hasChoosecombo()
  {
    boolean has = choosecombo.size() > 0;
    return has;
  }

  public int indexOfChoosecombo(ServiceCombo aChoosecombo)
  {
    int index = choosecombo.indexOf(aChoosecombo);
    return index;
  }
  /* Code from template association_GetMany */
  public Service getService(int index)
  {
    Service aService = services.get(index);
    return aService;
  }

  public List<Service> getServices()
  {
    List<Service> newServices = Collections.unmodifiableList(services);
    return newServices;
  }

  public int numberOfServices()
  {
    int number = services.size();
    return number;
  }

  public boolean hasServices()
  {
    boolean has = services.size() > 0;
    return has;
  }

  public int indexOfService(Service aService)
  {
    int index = services.indexOf(aService);
    return index;
  }
  /* Code from template association_GetOne */
  public CustomerAccount getCustomerAccount()
  {
    return customerAccount;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfChoosecombo()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public ServiceCombo addChoosecombo(int aMaxnumberOfServices, boolean aHasSubServices, MainService aMain, OwnerAccount aOwnerAccount, Business aBusiness)
  {
    return new ServiceCombo(aMaxnumberOfServices, aHasSubServices, aMain, aOwnerAccount, this, aBusiness);
  }

  public boolean addChoosecombo(ServiceCombo aChoosecombo)
  {
    boolean wasAdded = false;
    if (choosecombo.contains(aChoosecombo)) { return false; }
    Appointment existingAppointment = aChoosecombo.getAppointment();
    boolean isNewAppointment = existingAppointment != null && !this.equals(existingAppointment);
    if (isNewAppointment)
    {
      aChoosecombo.setAppointment(this);
    }
    else
    {
      choosecombo.add(aChoosecombo);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeChoosecombo(ServiceCombo aChoosecombo)
  {
    boolean wasRemoved = false;
    //Unable to remove aChoosecombo, as it must always have a appointment
    if (!this.equals(aChoosecombo.getAppointment()))
    {
      choosecombo.remove(aChoosecombo);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addChoosecomboAt(ServiceCombo aChoosecombo, int index)
  {  
    boolean wasAdded = false;
    if(addChoosecombo(aChoosecombo))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfChoosecombo()) { index = numberOfChoosecombo() - 1; }
      choosecombo.remove(aChoosecombo);
      choosecombo.add(index, aChoosecombo);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveChoosecomboAt(ServiceCombo aChoosecombo, int index)
  {
    boolean wasAdded = false;
    if(choosecombo.contains(aChoosecombo))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfChoosecombo()) { index = numberOfChoosecombo() - 1; }
      choosecombo.remove(aChoosecombo);
      choosecombo.add(index, aChoosecombo);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addChoosecomboAt(aChoosecombo, index);
    }
    return wasAdded;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfServices()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public Service addService(String aName, float aPrice, Time aDownTimeStart, Time aDownTimeEnd, String aType, OwnerAccount aOwnerAccount, MainService aMainService, SubService aSubService, Business aBusiness)
  {
    return new Service(aName, aPrice, aDownTimeStart, aDownTimeEnd, aType, aOwnerAccount, this, aMainService, aSubService, aBusiness);
  }

  public boolean addService(Service aService)
  {
    boolean wasAdded = false;
    if (services.contains(aService)) { return false; }
    Appointment existingAppointment = aService.getAppointment();
    boolean isNewAppointment = existingAppointment != null && !this.equals(existingAppointment);
    if (isNewAppointment)
    {
      aService.setAppointment(this);
    }
    else
    {
      services.add(aService);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeService(Service aService)
  {
    boolean wasRemoved = false;
    //Unable to remove aService, as it must always have a appointment
    if (!this.equals(aService.getAppointment()))
    {
      services.remove(aService);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addServiceAt(Service aService, int index)
  {  
    boolean wasAdded = false;
    if(addService(aService))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfServices()) { index = numberOfServices() - 1; }
      services.remove(aService);
      services.add(index, aService);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveServiceAt(Service aService, int index)
  {
    boolean wasAdded = false;
    if(services.contains(aService))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfServices()) { index = numberOfServices() - 1; }
      services.remove(aService);
      services.add(index, aService);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addServiceAt(aService, index);
    }
    return wasAdded;
  }
  /* Code from template association_SetOneToMany */
  public boolean setCustomerAccount(CustomerAccount aCustomerAccount)
  {
    boolean wasSet = false;
    if (aCustomerAccount == null)
    {
      return wasSet;
    }

    CustomerAccount existingCustomerAccount = customerAccount;
    customerAccount = aCustomerAccount;
    if (existingCustomerAccount != null && !existingCustomerAccount.equals(aCustomerAccount))
    {
      existingCustomerAccount.removeAppointment(this);
    }
    customerAccount.addAppointment(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    for(int i=choosecombo.size(); i > 0; i--)
    {
      ServiceCombo aChoosecombo = choosecombo.get(i - 1);
      aChoosecombo.delete();
    }
    for(int i=services.size(); i > 0; i--)
    {
      Service aService = services.get(i - 1);
      aService.delete();
    }
    CustomerAccount placeholderCustomerAccount = customerAccount;
    this.customerAccount = null;
    if(placeholderCustomerAccount != null)
    {
      placeholderCustomerAccount.removeAppointment(this);
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "isCancelled" + ":" + getIsCancelled()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "begintime" + "=" + (getBegintime() != null ? !getBegintime().equals(this)  ? getBegintime().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "endtime" + "=" + (getEndtime() != null ? !getEndtime().equals(this)  ? getEndtime().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "date" + "=" + (getDate() != null ? !getDate().equals(this)  ? getDate().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "cancellationdate" + "=" + (getCancellationdate() != null ? !getCancellationdate().equals(this)  ? getCancellationdate().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "customerAccount = "+(getCustomerAccount()!=null?Integer.toHexString(System.identityHashCode(getCustomerAccount())):"null");
  }
}