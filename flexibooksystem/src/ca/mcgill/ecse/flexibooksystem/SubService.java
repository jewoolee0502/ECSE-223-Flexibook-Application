/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.30.1.5099.60569f335 modeling language!*/

package ca.mcgill.ecse.flexibooksystem;
import java.util.*;
import java.sql.Time;

// line 65 "../../../../flexibook.ump"
public class SubService
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //SubService Attributes
  private int numberofServices;

  //SubService Associations
  private List<Service> services;
  private ServiceCombo serviceCombo;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public SubService(int aNumberofServices, ServiceCombo aServiceCombo)
  {
    numberofServices = aNumberofServices;
    services = new ArrayList<Service>();
    boolean didAddServiceCombo = setServiceCombo(aServiceCombo);
    if (!didAddServiceCombo)
    {
      throw new RuntimeException("Unable to create sub due to serviceCombo. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
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
  public Service addService(String aName, float aPrice, Time aDownTimeStart, Time aDownTimeEnd, String aType, OwnerAccount aOwnerAccount, Appointment aAppointment, MainService aMainService, Business aBusiness)
  {
    return new Service(aName, aPrice, aDownTimeStart, aDownTimeEnd, aType, aOwnerAccount, aAppointment, aMainService, this, aBusiness);
  }

  public boolean addService(Service aService)
  {
    boolean wasAdded = false;
    if (services.contains(aService)) { return false; }
    SubService existingSubService = aService.getSubService();
    boolean isNewSubService = existingSubService != null && !this.equals(existingSubService);
    if (isNewSubService)
    {
      aService.setSubService(this);
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
    //Unable to remove aService, as it must always have a subService
    if (!this.equals(aService.getSubService()))
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
  /* Code from template association_SetOneToOptionalOne */
  public boolean setServiceCombo(ServiceCombo aNewServiceCombo)
  {
    boolean wasSet = false;
    if (aNewServiceCombo == null)
    {
      //Unable to setServiceCombo to null, as sub must always be associated to a serviceCombo
      return wasSet;
    }
    
    SubService existingSub = aNewServiceCombo.getSub();
    if (existingSub != null && !equals(existingSub))
    {
      //Unable to setServiceCombo, the current serviceCombo already has a sub, which would be orphaned if it were re-assigned
      return wasSet;
    }
    
    ServiceCombo anOldServiceCombo = serviceCombo;
    serviceCombo = aNewServiceCombo;
    serviceCombo.setSub(this);

    if (anOldServiceCombo != null)
    {
      anOldServiceCombo.setSub(null);
    }
    wasSet = true;
    return wasSet;
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
      existingServiceCombo.setSub(null);
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "numberofServices" + ":" + getNumberofServices()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "serviceCombo = "+(getServiceCombo()!=null?Integer.toHexString(System.identityHashCode(getServiceCombo())):"null");
  }
}