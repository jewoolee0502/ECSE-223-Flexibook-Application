/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.30.1.5099.60569f335 modeling language!*/

package ca.mcgill.ecse.flexibooksystem;
import java.util.*;

// line 15 "../../../../flexibook.ump"
public class OwnerAccount extends Account
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //OwnerAccount Associations
  private List<Service> services;
  private List<ServiceCombo> serviceCombos;
  private List<Appointnement> appointnements;
  private List<BusinessInfo> businessInfos;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public OwnerAccount(String aUsername, String aPassword, boolean aIsLoggedIn)
  {
    super(aUsername, aPassword, aIsLoggedIn);
    services = new ArrayList<Service>();
    serviceCombos = new ArrayList<ServiceCombo>();
    appointnements = new ArrayList<Appointnement>();
    businessInfos = new ArrayList<BusinessInfo>();
  }

  //------------------------
  // INTERFACE
  //------------------------
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
  public ServiceCombo getServiceCombo(int index)
  {
    ServiceCombo aServiceCombo = serviceCombos.get(index);
    return aServiceCombo;
  }

  public List<ServiceCombo> getServiceCombos()
  {
    List<ServiceCombo> newServiceCombos = Collections.unmodifiableList(serviceCombos);
    return newServiceCombos;
  }

  public int numberOfServiceCombos()
  {
    int number = serviceCombos.size();
    return number;
  }

  public boolean hasServiceCombos()
  {
    boolean has = serviceCombos.size() > 0;
    return has;
  }

  public int indexOfServiceCombo(ServiceCombo aServiceCombo)
  {
    int index = serviceCombos.indexOf(aServiceCombo);
    return index;
  }
  /* Code from template association_GetMany */
  public Appointnement getAppointnement(int index)
  {
    Appointnement aAppointnement = appointnements.get(index);
    return aAppointnement;
  }

  public List<Appointnement> getAppointnements()
  {
    List<Appointnement> newAppointnements = Collections.unmodifiableList(appointnements);
    return newAppointnements;
  }

  public int numberOfAppointnements()
  {
    int number = appointnements.size();
    return number;
  }

  public boolean hasAppointnements()
  {
    boolean has = appointnements.size() > 0;
    return has;
  }

  public int indexOfAppointnement(Appointnement aAppointnement)
  {
    int index = appointnements.indexOf(aAppointnement);
    return index;
  }
  /* Code from template association_GetMany */
  public BusinessInfo getBusinessInfo(int index)
  {
    BusinessInfo aBusinessInfo = businessInfos.get(index);
    return aBusinessInfo;
  }

  public List<BusinessInfo> getBusinessInfos()
  {
    List<BusinessInfo> newBusinessInfos = Collections.unmodifiableList(businessInfos);
    return newBusinessInfos;
  }

  public int numberOfBusinessInfos()
  {
    int number = businessInfos.size();
    return number;
  }

  public boolean hasBusinessInfos()
  {
    boolean has = businessInfos.size() > 0;
    return has;
  }

  public int indexOfBusinessInfo(BusinessInfo aBusinessInfo)
  {
    int index = businessInfos.indexOf(aBusinessInfo);
    return index;
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
    if (aService.indexOfOwnerAccount(this) != -1)
    {
      wasAdded = true;
    }
    else
    {
      wasAdded = aService.addOwnerAccount(this);
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
    if (aService.indexOfOwnerAccount(this) == -1)
    {
      wasRemoved = true;
    }
    else
    {
      wasRemoved = aService.removeOwnerAccount(this);
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
  public static int minimumNumberOfServiceCombos()
  {
    return 0;
  }
  /* Code from template association_AddManyToManyMethod */
  public boolean addServiceCombo(ServiceCombo aServiceCombo)
  {
    boolean wasAdded = false;
    if (serviceCombos.contains(aServiceCombo)) { return false; }
    serviceCombos.add(aServiceCombo);
    if (aServiceCombo.indexOfOwnerAccount(this) != -1)
    {
      wasAdded = true;
    }
    else
    {
      wasAdded = aServiceCombo.addOwnerAccount(this);
      if (!wasAdded)
      {
        serviceCombos.remove(aServiceCombo);
      }
    }
    return wasAdded;
  }
  /* Code from template association_RemoveMany */
  public boolean removeServiceCombo(ServiceCombo aServiceCombo)
  {
    boolean wasRemoved = false;
    if (!serviceCombos.contains(aServiceCombo))
    {
      return wasRemoved;
    }

    int oldIndex = serviceCombos.indexOf(aServiceCombo);
    serviceCombos.remove(oldIndex);
    if (aServiceCombo.indexOfOwnerAccount(this) == -1)
    {
      wasRemoved = true;
    }
    else
    {
      wasRemoved = aServiceCombo.removeOwnerAccount(this);
      if (!wasRemoved)
      {
        serviceCombos.add(oldIndex,aServiceCombo);
      }
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addServiceComboAt(ServiceCombo aServiceCombo, int index)
  {  
    boolean wasAdded = false;
    if(addServiceCombo(aServiceCombo))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfServiceCombos()) { index = numberOfServiceCombos() - 1; }
      serviceCombos.remove(aServiceCombo);
      serviceCombos.add(index, aServiceCombo);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveServiceComboAt(ServiceCombo aServiceCombo, int index)
  {
    boolean wasAdded = false;
    if(serviceCombos.contains(aServiceCombo))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfServiceCombos()) { index = numberOfServiceCombos() - 1; }
      serviceCombos.remove(aServiceCombo);
      serviceCombos.add(index, aServiceCombo);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addServiceComboAt(aServiceCombo, index);
    }
    return wasAdded;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfAppointnements()
  {
    return 0;
  }
  /* Code from template association_AddManyToManyMethod */
  public boolean addAppointnement(Appointnement aAppointnement)
  {
    boolean wasAdded = false;
    if (appointnements.contains(aAppointnement)) { return false; }
    appointnements.add(aAppointnement);
    if (aAppointnement.indexOfOwnerAccount(this) != -1)
    {
      wasAdded = true;
    }
    else
    {
      wasAdded = aAppointnement.addOwnerAccount(this);
      if (!wasAdded)
      {
        appointnements.remove(aAppointnement);
      }
    }
    return wasAdded;
  }
  /* Code from template association_RemoveMany */
  public boolean removeAppointnement(Appointnement aAppointnement)
  {
    boolean wasRemoved = false;
    if (!appointnements.contains(aAppointnement))
    {
      return wasRemoved;
    }

    int oldIndex = appointnements.indexOf(aAppointnement);
    appointnements.remove(oldIndex);
    if (aAppointnement.indexOfOwnerAccount(this) == -1)
    {
      wasRemoved = true;
    }
    else
    {
      wasRemoved = aAppointnement.removeOwnerAccount(this);
      if (!wasRemoved)
      {
        appointnements.add(oldIndex,aAppointnement);
      }
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addAppointnementAt(Appointnement aAppointnement, int index)
  {  
    boolean wasAdded = false;
    if(addAppointnement(aAppointnement))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfAppointnements()) { index = numberOfAppointnements() - 1; }
      appointnements.remove(aAppointnement);
      appointnements.add(index, aAppointnement);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveAppointnementAt(Appointnement aAppointnement, int index)
  {
    boolean wasAdded = false;
    if(appointnements.contains(aAppointnement))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfAppointnements()) { index = numberOfAppointnements() - 1; }
      appointnements.remove(aAppointnement);
      appointnements.add(index, aAppointnement);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addAppointnementAt(aAppointnement, index);
    }
    return wasAdded;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfBusinessInfos()
  {
    return 0;
  }
  /* Code from template association_AddManyToManyMethod */
  public boolean addBusinessInfo(BusinessInfo aBusinessInfo)
  {
    boolean wasAdded = false;
    if (businessInfos.contains(aBusinessInfo)) { return false; }
    businessInfos.add(aBusinessInfo);
    if (aBusinessInfo.indexOfOwnerAccount(this) != -1)
    {
      wasAdded = true;
    }
    else
    {
      wasAdded = aBusinessInfo.addOwnerAccount(this);
      if (!wasAdded)
      {
        businessInfos.remove(aBusinessInfo);
      }
    }
    return wasAdded;
  }
  /* Code from template association_RemoveMany */
  public boolean removeBusinessInfo(BusinessInfo aBusinessInfo)
  {
    boolean wasRemoved = false;
    if (!businessInfos.contains(aBusinessInfo))
    {
      return wasRemoved;
    }

    int oldIndex = businessInfos.indexOf(aBusinessInfo);
    businessInfos.remove(oldIndex);
    if (aBusinessInfo.indexOfOwnerAccount(this) == -1)
    {
      wasRemoved = true;
    }
    else
    {
      wasRemoved = aBusinessInfo.removeOwnerAccount(this);
      if (!wasRemoved)
      {
        businessInfos.add(oldIndex,aBusinessInfo);
      }
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addBusinessInfoAt(BusinessInfo aBusinessInfo, int index)
  {  
    boolean wasAdded = false;
    if(addBusinessInfo(aBusinessInfo))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfBusinessInfos()) { index = numberOfBusinessInfos() - 1; }
      businessInfos.remove(aBusinessInfo);
      businessInfos.add(index, aBusinessInfo);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveBusinessInfoAt(BusinessInfo aBusinessInfo, int index)
  {
    boolean wasAdded = false;
    if(businessInfos.contains(aBusinessInfo))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfBusinessInfos()) { index = numberOfBusinessInfos() - 1; }
      businessInfos.remove(aBusinessInfo);
      businessInfos.add(index, aBusinessInfo);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addBusinessInfoAt(aBusinessInfo, index);
    }
    return wasAdded;
  }

  public void delete()
  {
    ArrayList<Service> copyOfServices = new ArrayList<Service>(services);
    services.clear();
    for(Service aService : copyOfServices)
    {
      aService.removeOwnerAccount(this);
    }
    ArrayList<ServiceCombo> copyOfServiceCombos = new ArrayList<ServiceCombo>(serviceCombos);
    serviceCombos.clear();
    for(ServiceCombo aServiceCombo : copyOfServiceCombos)
    {
      aServiceCombo.removeOwnerAccount(this);
    }
    ArrayList<Appointnement> copyOfAppointnements = new ArrayList<Appointnement>(appointnements);
    appointnements.clear();
    for(Appointnement aAppointnement : copyOfAppointnements)
    {
      aAppointnement.removeOwnerAccount(this);
    }
    ArrayList<BusinessInfo> copyOfBusinessInfos = new ArrayList<BusinessInfo>(businessInfos);
    businessInfos.clear();
    for(BusinessInfo aBusinessInfo : copyOfBusinessInfos)
    {
      aBusinessInfo.removeOwnerAccount(this);
    }
    super.delete();
  }

}