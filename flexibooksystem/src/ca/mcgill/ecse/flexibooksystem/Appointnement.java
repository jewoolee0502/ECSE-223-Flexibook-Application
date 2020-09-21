/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.30.1.5099.60569f335 modeling language!*/

package ca.mcgill.ecse.flexibooksystem;
import java.util.*;

// line 22 "../../../../flexibook.ump"
public class Appointnement
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Appointnement Attributes
  private String beginTime;
  private String endTime;
  private String date;

  //Appointnement Associations
  private List<CustomerAccount> customerAccounts;
  private List<Service> services;
  private List<OwnerAccount> ownerAccounts;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Appointnement(String aBeginTime, String aEndTime, String aDate)
  {
    beginTime = aBeginTime;
    endTime = aEndTime;
    date = aDate;
    customerAccounts = new ArrayList<CustomerAccount>();
    services = new ArrayList<Service>();
    ownerAccounts = new ArrayList<OwnerAccount>();
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setBeginTime(String aBeginTime)
  {
    boolean wasSet = false;
    beginTime = aBeginTime;
    wasSet = true;
    return wasSet;
  }

  public boolean setEndTime(String aEndTime)
  {
    boolean wasSet = false;
    endTime = aEndTime;
    wasSet = true;
    return wasSet;
  }

  public boolean setDate(String aDate)
  {
    boolean wasSet = false;
    date = aDate;
    wasSet = true;
    return wasSet;
  }

  public String getBeginTime()
  {
    return beginTime;
  }

  public String getEndTime()
  {
    return endTime;
  }

  public String getDate()
  {
    return date;
  }
  /* Code from template association_GetMany */
  public CustomerAccount getCustomerAccount(int index)
  {
    CustomerAccount aCustomerAccount = customerAccounts.get(index);
    return aCustomerAccount;
  }

  public List<CustomerAccount> getCustomerAccounts()
  {
    List<CustomerAccount> newCustomerAccounts = Collections.unmodifiableList(customerAccounts);
    return newCustomerAccounts;
  }

  public int numberOfCustomerAccounts()
  {
    int number = customerAccounts.size();
    return number;
  }

  public boolean hasCustomerAccounts()
  {
    boolean has = customerAccounts.size() > 0;
    return has;
  }

  public int indexOfCustomerAccount(CustomerAccount aCustomerAccount)
  {
    int index = customerAccounts.indexOf(aCustomerAccount);
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
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfCustomerAccounts()
  {
    return 0;
  }
  /* Code from template association_AddManyToManyMethod */
  public boolean addCustomerAccount(CustomerAccount aCustomerAccount)
  {
    boolean wasAdded = false;
    if (customerAccounts.contains(aCustomerAccount)) { return false; }
    customerAccounts.add(aCustomerAccount);
    if (aCustomerAccount.indexOfAppointnement(this) != -1)
    {
      wasAdded = true;
    }
    else
    {
      wasAdded = aCustomerAccount.addAppointnement(this);
      if (!wasAdded)
      {
        customerAccounts.remove(aCustomerAccount);
      }
    }
    return wasAdded;
  }
  /* Code from template association_RemoveMany */
  public boolean removeCustomerAccount(CustomerAccount aCustomerAccount)
  {
    boolean wasRemoved = false;
    if (!customerAccounts.contains(aCustomerAccount))
    {
      return wasRemoved;
    }

    int oldIndex = customerAccounts.indexOf(aCustomerAccount);
    customerAccounts.remove(oldIndex);
    if (aCustomerAccount.indexOfAppointnement(this) == -1)
    {
      wasRemoved = true;
    }
    else
    {
      wasRemoved = aCustomerAccount.removeAppointnement(this);
      if (!wasRemoved)
      {
        customerAccounts.add(oldIndex,aCustomerAccount);
      }
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addCustomerAccountAt(CustomerAccount aCustomerAccount, int index)
  {  
    boolean wasAdded = false;
    if(addCustomerAccount(aCustomerAccount))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfCustomerAccounts()) { index = numberOfCustomerAccounts() - 1; }
      customerAccounts.remove(aCustomerAccount);
      customerAccounts.add(index, aCustomerAccount);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveCustomerAccountAt(CustomerAccount aCustomerAccount, int index)
  {
    boolean wasAdded = false;
    if(customerAccounts.contains(aCustomerAccount))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfCustomerAccounts()) { index = numberOfCustomerAccounts() - 1; }
      customerAccounts.remove(aCustomerAccount);
      customerAccounts.add(index, aCustomerAccount);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addCustomerAccountAt(aCustomerAccount, index);
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
    if (aService.indexOfAppointnement(this) != -1)
    {
      wasAdded = true;
    }
    else
    {
      wasAdded = aService.addAppointnement(this);
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
    if (aService.indexOfAppointnement(this) == -1)
    {
      wasRemoved = true;
    }
    else
    {
      wasRemoved = aService.removeAppointnement(this);
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
    if (aOwnerAccount.indexOfAppointnement(this) != -1)
    {
      wasAdded = true;
    }
    else
    {
      wasAdded = aOwnerAccount.addAppointnement(this);
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
    if (aOwnerAccount.indexOfAppointnement(this) == -1)
    {
      wasRemoved = true;
    }
    else
    {
      wasRemoved = aOwnerAccount.removeAppointnement(this);
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

  public void delete()
  {
    ArrayList<CustomerAccount> copyOfCustomerAccounts = new ArrayList<CustomerAccount>(customerAccounts);
    customerAccounts.clear();
    for(CustomerAccount aCustomerAccount : copyOfCustomerAccounts)
    {
      aCustomerAccount.removeAppointnement(this);
    }
    ArrayList<Service> copyOfServices = new ArrayList<Service>(services);
    services.clear();
    for(Service aService : copyOfServices)
    {
      aService.removeAppointnement(this);
    }
    ArrayList<OwnerAccount> copyOfOwnerAccounts = new ArrayList<OwnerAccount>(ownerAccounts);
    ownerAccounts.clear();
    for(OwnerAccount aOwnerAccount : copyOfOwnerAccounts)
    {
      aOwnerAccount.removeAppointnement(this);
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "beginTime" + ":" + getBeginTime()+ "," +
            "endTime" + ":" + getEndTime()+ "," +
            "date" + ":" + getDate()+ "]";
  }
}