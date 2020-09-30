/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.30.1.5099.60569f335 modeling language!*/

package ca.mcgill.ecse.flexibooksystem;
import java.util.*;

// line 53 "../../../../flexibook.ump"
public class ServiceCombo
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //ServiceCombo Attributes
  private String name;
  private int numberOfMainServices;
  private int numberOfSubServices;
  private boolean hasSubServices;

  //ServiceCombo Associations
  private List<Service> Main;
  private Appointment appointment;
  private List<Service> services;
  private Business business;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public ServiceCombo(String aName, int aNumberOfMainServices, int aNumberOfSubServices, boolean aHasSubServices, Appointment aAppointment, Business aBusiness, Service[] allMain, Service[] allServices)
  {
    name = aName;
    numberOfMainServices = aNumberOfMainServices;
    numberOfSubServices = aNumberOfSubServices;
    hasSubServices = aHasSubServices;
    Main = new ArrayList<Service>();
    boolean didAddMain = setMain(allMain);
    if (!didAddMain)
    {
      throw new RuntimeException("Unable to create ServiceCombo, must have at least 1 Main. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    boolean didAddAppointment = setAppointment(aAppointment);
    if (!didAddAppointment)
    {
      throw new RuntimeException("Unable to create choosecombo due to appointment. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    services = new ArrayList<Service>();
    boolean didAddServices = setServices(allServices);
    if (!didAddServices)
    {
      throw new RuntimeException("Unable to create ServiceCombo, must have at least 2 services. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    boolean didAddBusiness = setBusiness(aBusiness);
    if (!didAddBusiness)
    {
      throw new RuntimeException("Unable to create combo due to business. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setName(String aName)
  {
    boolean wasSet = false;
    name = aName;
    wasSet = true;
    return wasSet;
  }

  public boolean setNumberOfMainServices(int aNumberOfMainServices)
  {
    boolean wasSet = false;
    numberOfMainServices = aNumberOfMainServices;
    wasSet = true;
    return wasSet;
  }

  public boolean setNumberOfSubServices(int aNumberOfSubServices)
  {
    boolean wasSet = false;
    numberOfSubServices = aNumberOfSubServices;
    wasSet = true;
    return wasSet;
  }

  public boolean setHasSubServices(boolean aHasSubServices)
  {
    boolean wasSet = false;
    hasSubServices = aHasSubServices;
    wasSet = true;
    return wasSet;
  }

  public String getName()
  {
    return name;
  }

  public int getNumberOfMainServices()
  {
    return numberOfMainServices;
  }

  public int getNumberOfSubServices()
  {
    return numberOfSubServices;
  }

  public boolean getHasSubServices()
  {
    return hasSubServices;
  }
  /* Code from template attribute_IsBoolean */
  public boolean isHasSubServices()
  {
    return hasSubServices;
  }
  /* Code from template association_GetMany */
  public Service getMain(int index)
  {
    Service aMain = Main.get(index);
    return aMain;
  }

  public List<Service> getMain()
  {
    List<Service> newMain = Collections.unmodifiableList(Main);
    return newMain;
  }

  public int numberOfMain()
  {
    int number = Main.size();
    return number;
  }

  public boolean hasMain()
  {
    boolean has = Main.size() > 0;
    return has;
  }

  public int indexOfMain(Service aMain)
  {
    int index = Main.indexOf(aMain);
    return index;
  }
  /* Code from template association_GetOne */
  public Appointment getAppointment()
  {
    return appointment;
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
  public Business getBusiness()
  {
    return business;
  }
  /* Code from template association_IsNumberOfValidMethod */
  public boolean isNumberOfMainValid()
  {
    boolean isValid = numberOfMain() >= minimumNumberOfMain();
    return isValid;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfMain()
  {
    return 1;
  }
  /* Code from template association_AddManyToManyMethod */
  public boolean addMain(Service aMain)
  {
    boolean wasAdded = false;
    if (Main.contains(aMain)) { return false; }
    Main.add(aMain);
    if (aMain.indexOfServiceCombo(this) != -1)
    {
      wasAdded = true;
    }
    else
    {
      wasAdded = aMain.addServiceCombo(this);
      if (!wasAdded)
      {
        Main.remove(aMain);
      }
    }
    return wasAdded;
  }
  /* Code from template association_AddMStarToMany */
  public boolean removeMain(Service aMain)
  {
    boolean wasRemoved = false;
    if (!Main.contains(aMain))
    {
      return wasRemoved;
    }

    if (numberOfMain() <= minimumNumberOfMain())
    {
      return wasRemoved;
    }

    int oldIndex = Main.indexOf(aMain);
    Main.remove(oldIndex);
    if (aMain.indexOfServiceCombo(this) == -1)
    {
      wasRemoved = true;
    }
    else
    {
      wasRemoved = aMain.removeServiceCombo(this);
      if (!wasRemoved)
      {
        Main.add(oldIndex,aMain);
      }
    }
    return wasRemoved;
  }
  /* Code from template association_SetMStarToMany */
  public boolean setMain(Service... newMain)
  {
    boolean wasSet = false;
    ArrayList<Service> verifiedMain = new ArrayList<Service>();
    for (Service aMain : newMain)
    {
      if (verifiedMain.contains(aMain))
      {
        continue;
      }
      verifiedMain.add(aMain);
    }

    if (verifiedMain.size() != newMain.length || verifiedMain.size() < minimumNumberOfMain())
    {
      return wasSet;
    }

    ArrayList<Service> oldMain = new ArrayList<Service>(Main);
    Main.clear();
    for (Service aNewMain : verifiedMain)
    {
      Main.add(aNewMain);
      if (oldMain.contains(aNewMain))
      {
        oldMain.remove(aNewMain);
      }
      else
      {
        aNewMain.addServiceCombo(this);
      }
    }

    for (Service anOldMain : oldMain)
    {
      anOldMain.removeServiceCombo(this);
    }
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addMainAt(Service aMain, int index)
  {  
    boolean wasAdded = false;
    if(addMain(aMain))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfMain()) { index = numberOfMain() - 1; }
      Main.remove(aMain);
      Main.add(index, aMain);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveMainAt(Service aMain, int index)
  {
    boolean wasAdded = false;
    if(Main.contains(aMain))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfMain()) { index = numberOfMain() - 1; }
      Main.remove(aMain);
      Main.add(index, aMain);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addMainAt(aMain, index);
    }
    return wasAdded;
  }
  /* Code from template association_SetOneToMany */
  public boolean setAppointment(Appointment aAppointment)
  {
    boolean wasSet = false;
    if (aAppointment == null)
    {
      return wasSet;
    }

    Appointment existingAppointment = appointment;
    appointment = aAppointment;
    if (existingAppointment != null && !existingAppointment.equals(aAppointment))
    {
      existingAppointment.removeChoosecombo(this);
    }
    appointment.addChoosecombo(this);
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_IsNumberOfValidMethod */
  public boolean isNumberOfServicesValid()
  {
    boolean isValid = numberOfServices() >= minimumNumberOfServices();
    return isValid;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfServices()
  {
    return 2;
  }
  /* Code from template association_AddManyToManyMethod */
  public boolean addService(Service aService)
  {
    boolean wasAdded = false;
    if (services.contains(aService)) { return false; }
    services.add(aService);
    if (aService.indexOfCombo(this) != -1)
    {
      wasAdded = true;
    }
    else
    {
      wasAdded = aService.addCombo(this);
      if (!wasAdded)
      {
        services.remove(aService);
      }
    }
    return wasAdded;
  }
  /* Code from template association_AddMStarToMany */
  public boolean removeService(Service aService)
  {
    boolean wasRemoved = false;
    if (!services.contains(aService))
    {
      return wasRemoved;
    }

    if (numberOfServices() <= minimumNumberOfServices())
    {
      return wasRemoved;
    }

    int oldIndex = services.indexOf(aService);
    services.remove(oldIndex);
    if (aService.indexOfCombo(this) == -1)
    {
      wasRemoved = true;
    }
    else
    {
      wasRemoved = aService.removeCombo(this);
      if (!wasRemoved)
      {
        services.add(oldIndex,aService);
      }
    }
    return wasRemoved;
  }
  /* Code from template association_SetMStarToMany */
  public boolean setServices(Service... newServices)
  {
    boolean wasSet = false;
    ArrayList<Service> verifiedServices = new ArrayList<Service>();
    for (Service aService : newServices)
    {
      if (verifiedServices.contains(aService))
      {
        continue;
      }
      verifiedServices.add(aService);
    }

    if (verifiedServices.size() != newServices.length || verifiedServices.size() < minimumNumberOfServices())
    {
      return wasSet;
    }

    ArrayList<Service> oldServices = new ArrayList<Service>(services);
    services.clear();
    for (Service aNewService : verifiedServices)
    {
      services.add(aNewService);
      if (oldServices.contains(aNewService))
      {
        oldServices.remove(aNewService);
      }
      else
      {
        aNewService.addCombo(this);
      }
    }

    for (Service anOldService : oldServices)
    {
      anOldService.removeCombo(this);
    }
    wasSet = true;
    return wasSet;
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
  public boolean setBusiness(Business aBusiness)
  {
    boolean wasSet = false;
    if (aBusiness == null)
    {
      return wasSet;
    }

    Business existingBusiness = business;
    business = aBusiness;
    if (existingBusiness != null && !existingBusiness.equals(aBusiness))
    {
      existingBusiness.removeCombo(this);
    }
    business.addCombo(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    ArrayList<Service> copyOfMain = new ArrayList<Service>(Main);
    Main.clear();
    for(Service aMain : copyOfMain)
    {
      aMain.removeServiceCombo(this);
    }
    Appointment placeholderAppointment = appointment;
    this.appointment = null;
    if(placeholderAppointment != null)
    {
      placeholderAppointment.removeChoosecombo(this);
    }
    ArrayList<Service> copyOfServices = new ArrayList<Service>(services);
    services.clear();
    for(Service aService : copyOfServices)
    {
      aService.removeCombo(this);
    }
    Business placeholderBusiness = business;
    this.business = null;
    if(placeholderBusiness != null)
    {
      placeholderBusiness.removeCombo(this);
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "name" + ":" + getName()+ "," +
            "numberOfMainServices" + ":" + getNumberOfMainServices()+ "," +
            "numberOfSubServices" + ":" + getNumberOfSubServices()+ "," +
            "hasSubServices" + ":" + getHasSubServices()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "appointment = "+(getAppointment()!=null?Integer.toHexString(System.identityHashCode(getAppointment())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "business = "+(getBusiness()!=null?Integer.toHexString(System.identityHashCode(getBusiness())):"null");
  }
}