/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.30.1.5099.60569f335 modeling language!*/

package ca.mcgill.ecse.flexibooksystem;
import java.util.*;

// line 61 "../../../../flexibook.ump"
public class ServiceCombo
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //ServiceCombo Associations
  private List<SubService> subServices;
  private List<OwnerAccount> ownerAccounts;
  private List<Service> services;
  private List<MainService> mainServices;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public ServiceCombo()
  {
    subServices = new ArrayList<SubService>();
    ownerAccounts = new ArrayList<OwnerAccount>();
    services = new ArrayList<Service>();
    mainServices = new ArrayList<MainService>();
  }

  //------------------------
  // INTERFACE
  //------------------------
  /* Code from template association_GetMany */
  public SubService getSubService(int index)
  {
    SubService aSubService = subServices.get(index);
    return aSubService;
  }

  public List<SubService> getSubServices()
  {
    List<SubService> newSubServices = Collections.unmodifiableList(subServices);
    return newSubServices;
  }

  public int numberOfSubServices()
  {
    int number = subServices.size();
    return number;
  }

  public boolean hasSubServices()
  {
    boolean has = subServices.size() > 0;
    return has;
  }

  public int indexOfSubService(SubService aSubService)
  {
    int index = subServices.indexOf(aSubService);
    return index;
  }
  /* Code from template association_GetMany */
  public OwnerAccount getOwnerAccount(int index)
  {
    OwnerAccount aOwnerAccount = ownerAccounts.get(index);
    return aOwnerAccount;
  }

  public List<OwnerAccount> getOwnerAccounts()
  {
    List<OwnerAccount> newOwnerAccounts = Collections.unmodifiableList(ownerAccounts);
    return newOwnerAccounts;
  }

  public int numberOfOwnerAccounts()
  {
    int number = ownerAccounts.size();
    return number;
  }

  public boolean hasOwnerAccounts()
  {
    boolean has = ownerAccounts.size() > 0;
    return has;
  }

  public int indexOfOwnerAccount(OwnerAccount aOwnerAccount)
  {
    int index = ownerAccounts.indexOf(aOwnerAccount);
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
  /* Code from template association_GetMany */
  public MainService getMainService(int index)
  {
    MainService aMainService = mainServices.get(index);
    return aMainService;
  }

  public List<MainService> getMainServices()
  {
    List<MainService> newMainServices = Collections.unmodifiableList(mainServices);
    return newMainServices;
  }

  public int numberOfMainServices()
  {
    int number = mainServices.size();
    return number;
  }

  public boolean hasMainServices()
  {
    boolean has = mainServices.size() > 0;
    return has;
  }

  public int indexOfMainService(MainService aMainService)
  {
    int index = mainServices.indexOf(aMainService);
    return index;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfSubServices()
  {
    return 0;
  }
  /* Code from template association_AddManyToManyMethod */
  public boolean addSubService(SubService aSubService)
  {
    boolean wasAdded = false;
    if (subServices.contains(aSubService)) { return false; }
    subServices.add(aSubService);
    if (aSubService.indexOfServiceCombo(this) != -1)
    {
      wasAdded = true;
    }
    else
    {
      wasAdded = aSubService.addServiceCombo(this);
      if (!wasAdded)
      {
        subServices.remove(aSubService);
      }
    }
    return wasAdded;
  }
  /* Code from template association_RemoveMany */
  public boolean removeSubService(SubService aSubService)
  {
    boolean wasRemoved = false;
    if (!subServices.contains(aSubService))
    {
      return wasRemoved;
    }

    int oldIndex = subServices.indexOf(aSubService);
    subServices.remove(oldIndex);
    if (aSubService.indexOfServiceCombo(this) == -1)
    {
      wasRemoved = true;
    }
    else
    {
      wasRemoved = aSubService.removeServiceCombo(this);
      if (!wasRemoved)
      {
        subServices.add(oldIndex,aSubService);
      }
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addSubServiceAt(SubService aSubService, int index)
  {  
    boolean wasAdded = false;
    if(addSubService(aSubService))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfSubServices()) { index = numberOfSubServices() - 1; }
      subServices.remove(aSubService);
      subServices.add(index, aSubService);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveSubServiceAt(SubService aSubService, int index)
  {
    boolean wasAdded = false;
    if(subServices.contains(aSubService))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfSubServices()) { index = numberOfSubServices() - 1; }
      subServices.remove(aSubService);
      subServices.add(index, aSubService);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addSubServiceAt(aSubService, index);
    }
    return wasAdded;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfOwnerAccounts()
  {
    return 0;
  }
  /* Code from template association_AddManyToManyMethod */
  public boolean addOwnerAccount(OwnerAccount aOwnerAccount)
  {
    boolean wasAdded = false;
    if (ownerAccounts.contains(aOwnerAccount)) { return false; }
    ownerAccounts.add(aOwnerAccount);
    if (aOwnerAccount.indexOfServiceCombo(this) != -1)
    {
      wasAdded = true;
    }
    else
    {
      wasAdded = aOwnerAccount.addServiceCombo(this);
      if (!wasAdded)
      {
        ownerAccounts.remove(aOwnerAccount);
      }
    }
    return wasAdded;
  }
  /* Code from template association_RemoveMany */
  public boolean removeOwnerAccount(OwnerAccount aOwnerAccount)
  {
    boolean wasRemoved = false;
    if (!ownerAccounts.contains(aOwnerAccount))
    {
      return wasRemoved;
    }

    int oldIndex = ownerAccounts.indexOf(aOwnerAccount);
    ownerAccounts.remove(oldIndex);
    if (aOwnerAccount.indexOfServiceCombo(this) == -1)
    {
      wasRemoved = true;
    }
    else
    {
      wasRemoved = aOwnerAccount.removeServiceCombo(this);
      if (!wasRemoved)
      {
        ownerAccounts.add(oldIndex,aOwnerAccount);
      }
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addOwnerAccountAt(OwnerAccount aOwnerAccount, int index)
  {  
    boolean wasAdded = false;
    if(addOwnerAccount(aOwnerAccount))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfOwnerAccounts()) { index = numberOfOwnerAccounts() - 1; }
      ownerAccounts.remove(aOwnerAccount);
      ownerAccounts.add(index, aOwnerAccount);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveOwnerAccountAt(OwnerAccount aOwnerAccount, int index)
  {
    boolean wasAdded = false;
    if(ownerAccounts.contains(aOwnerAccount))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfOwnerAccounts()) { index = numberOfOwnerAccounts() - 1; }
      ownerAccounts.remove(aOwnerAccount);
      ownerAccounts.add(index, aOwnerAccount);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addOwnerAccountAt(aOwnerAccount, index);
    }
    return wasAdded;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfServices()
  {
    return 0;
  }
  /* Code from template association_AddManyToManyMethod */
  public boolean addService(Service aService)
  {
    boolean wasAdded = false;
    if (services.contains(aService)) { return false; }
    services.add(aService);
    if (aService.indexOfServiceCombo(this) != -1)
    {
      wasAdded = true;
    }
    else
    {
      wasAdded = aService.addServiceCombo(this);
      if (!wasAdded)
      {
        services.remove(aService);
      }
    }
    return wasAdded;
  }
  /* Code from template association_RemoveMany */
  public boolean removeService(Service aService)
  {
    boolean wasRemoved = false;
    if (!services.contains(aService))
    {
      return wasRemoved;
    }

    int oldIndex = services.indexOf(aService);
    services.remove(oldIndex);
    if (aService.indexOfServiceCombo(this) == -1)
    {
      wasRemoved = true;
    }
    else
    {
      wasRemoved = aService.removeServiceCombo(this);
      if (!wasRemoved)
      {
        services.add(oldIndex,aService);
      }
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
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfMainServices()
  {
    return 0;
  }
  /* Code from template association_AddManyToManyMethod */
  public boolean addMainService(MainService aMainService)
  {
    boolean wasAdded = false;
    if (mainServices.contains(aMainService)) { return false; }
    mainServices.add(aMainService);
    if (aMainService.indexOfServiceCombo(this) != -1)
    {
      wasAdded = true;
    }
    else
    {
      wasAdded = aMainService.addServiceCombo(this);
      if (!wasAdded)
      {
        mainServices.remove(aMainService);
      }
    }
    return wasAdded;
  }
  /* Code from template association_RemoveMany */
  public boolean removeMainService(MainService aMainService)
  {
    boolean wasRemoved = false;
    if (!mainServices.contains(aMainService))
    {
      return wasRemoved;
    }

    int oldIndex = mainServices.indexOf(aMainService);
    mainServices.remove(oldIndex);
    if (aMainService.indexOfServiceCombo(this) == -1)
    {
      wasRemoved = true;
    }
    else
    {
      wasRemoved = aMainService.removeServiceCombo(this);
      if (!wasRemoved)
      {
        mainServices.add(oldIndex,aMainService);
      }
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addMainServiceAt(MainService aMainService, int index)
  {  
    boolean wasAdded = false;
    if(addMainService(aMainService))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfMainServices()) { index = numberOfMainServices() - 1; }
      mainServices.remove(aMainService);
      mainServices.add(index, aMainService);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveMainServiceAt(MainService aMainService, int index)
  {
    boolean wasAdded = false;
    if(mainServices.contains(aMainService))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfMainServices()) { index = numberOfMainServices() - 1; }
      mainServices.remove(aMainService);
      mainServices.add(index, aMainService);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addMainServiceAt(aMainService, index);
    }
    return wasAdded;
  }

  public void delete()
  {
    ArrayList<SubService> copyOfSubServices = new ArrayList<SubService>(subServices);
    subServices.clear();
    for(SubService aSubService : copyOfSubServices)
    {
      aSubService.removeServiceCombo(this);
    }
    ArrayList<OwnerAccount> copyOfOwnerAccounts = new ArrayList<OwnerAccount>(ownerAccounts);
    ownerAccounts.clear();
    for(OwnerAccount aOwnerAccount : copyOfOwnerAccounts)
    {
      aOwnerAccount.removeServiceCombo(this);
    }
    ArrayList<Service> copyOfServices = new ArrayList<Service>(services);
    services.clear();
    for(Service aService : copyOfServices)
    {
      aService.removeServiceCombo(this);
    }
    ArrayList<MainService> copyOfMainServices = new ArrayList<MainService>(mainServices);
    mainServices.clear();
    for(MainService aMainService : copyOfMainServices)
    {
      aMainService.removeServiceCombo(this);
    }
  }

}