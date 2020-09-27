/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.30.1.5099.60569f335 modeling language!*/

package ca.mcgill.ecse.flexibooksystem;
import java.util.*;
import java.sql.Time;

// line 55 "../../../../flexibook.ump"
public class MainService
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //MainService Attributes
  private int numberofServices;

  //MainService Associations
  private List<Service> services;
  private ServiceCombo serviceCombo;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public MainService(int aNumberofServices, ServiceCombo aServiceCombo)
  {
    numberofServices = aNumberofServices;
    services = new ArrayList<Service>();
    if (aServiceCombo == null || aServiceCombo.getMain() != null)
    {
      throw new RuntimeException("Unable to create MainService due to aServiceCombo. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    serviceCombo = aServiceCombo;
  }

  public MainService(int aNumberofServices, int aMaxnumberOfServicesForServiceCombo, boolean aHasSubServicesForServiceCombo, OwnerAccount aOwnerAccountForServiceCombo, Appointment aAppointmentForServiceCombo, Business aBusinessForServiceCombo)
  {
    numberofServices = aNumberofServices;
    services = new ArrayList<Service>();
    serviceCombo = new ServiceCombo(aMaxnumberOfServicesForServiceCombo, aHasSubServicesForServiceCombo, this, aOwnerAccountForServiceCombo, aAppointmentForServiceCombo, aBusinessForServiceCombo);
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setNumberofServices(int aNumberofServices)
  {
    boolean wasSet = false;
    numberofServices = aNumberofServices;
    wasSet = true;
    return wasSet;
  }

  public int getNumberofServices()
  {
    return numberofServices;
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
  public ServiceCombo getServiceCombo()
  {
    return serviceCombo;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfServices()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public Service addService(String aName, float aPrice, Time aDownTimeStart, Time aDownTimeEnd, String aType, OwnerAccount aOwnerAccount, Appointment aAppointment, SubService aSubService, Business aBusiness)
  {
    return new Service(aName, aPrice, aDownTimeStart, aDownTimeEnd, aType, aOwnerAccount, aAppointment, this, aSubService, aBusiness);
  }

  public boolean addService(Service aService)
  {
    boolean wasAdded = false;
    if (services.contains(aService)) { return false; }
    MainService existingMainService = aService.getMainService();
    boolean isNewMainService = existingMainService != null && !this.equals(existingMainService);
    if (isNewMainService)
    {
      aService.setMainService(this);
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
    //Unable to remove aService, as it must always have a mainService
    if (!this.equals(aService.getMainService()))
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

  public void delete()
  {
    for(int i=services.size(); i > 0; i--)
    {
      Service aService = services.get(i - 1);
      aService.delete();
    }
    ServiceCombo existingServiceCombo = serviceCombo;
    serviceCombo = null;
    if (existingServiceCombo != null)
    {
      existingServiceCombo.delete();
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "numberofServices" + ":" + getNumberofServices()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "serviceCombo = "+(getServiceCombo()!=null?Integer.toHexString(System.identityHashCode(getServiceCombo())):"null");
  }
}