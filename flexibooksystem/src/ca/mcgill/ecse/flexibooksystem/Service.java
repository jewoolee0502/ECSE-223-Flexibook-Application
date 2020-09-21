/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.30.1.5099.60569f335 modeling language!*/

package ca.mcgill.ecse.flexibooksystem;
import java.util.*;

// line 32 "../../../../flexibook.ump"
public class Service
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Service Attributes
  private String name;
  private float price;
  private int downTimeStart;
  private int downTimeEnd;
  private String type;

  //Service Associations
  private List<ServiceCombo> serviceCombos;
  private List<OwnerAccount> ownerAccounts;
  private List<Appointnement> appointnements;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Service(String aName, float aPrice, int aDownTimeStart, int aDownTimeEnd, String aType)
  {
    name = aName;
    price = aPrice;
    downTimeStart = aDownTimeStart;
    downTimeEnd = aDownTimeEnd;
    type = aType;
    serviceCombos = new ArrayList<ServiceCombo>();
    ownerAccounts = new ArrayList<OwnerAccount>();
    appointnements = new ArrayList<Appointnement>();
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

  public boolean setPrice(float aPrice)
  {
    boolean wasSet = false;
    price = aPrice;
    wasSet = true;
    return wasSet;
  }

  public boolean setDownTimeStart(int aDownTimeStart)
  {
    boolean wasSet = false;
    downTimeStart = aDownTimeStart;
    wasSet = true;
    return wasSet;
  }

  public boolean setDownTimeEnd(int aDownTimeEnd)
  {
    boolean wasSet = false;
    downTimeEnd = aDownTimeEnd;
    wasSet = true;
    return wasSet;
  }

  public boolean setType(String aType)
  {
    boolean wasSet = false;
    type = aType;
    wasSet = true;
    return wasSet;
  }

  public String getName()
  {
    return name;
  }

  public float getPrice()
  {
    return price;
  }

  public int getDownTimeStart()
  {
    return downTimeStart;
  }

  public int getDownTimeEnd()
  {
    return downTimeEnd;
  }

  public String getType()
  {
    return type;
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
    if (aServiceCombo.indexOfService(this) != -1)
    {
      wasAdded = true;
    }
    else
    {
      wasAdded = aServiceCombo.addService(this);
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
    if (aServiceCombo.indexOfService(this) == -1)
    {
      wasRemoved = true;
    }
    else
    {
      wasRemoved = aServiceCombo.removeService(this);
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
    if (aOwnerAccount.indexOfService(this) != -1)
    {
      wasAdded = true;
    }
    else
    {
      wasAdded = aOwnerAccount.addService(this);
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
    if (aOwnerAccount.indexOfService(this) == -1)
    {
      wasRemoved = true;
    }
    else
    {
      wasRemoved = aOwnerAccount.removeService(this);
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
    if (aAppointnement.indexOfService(this) != -1)
    {
      wasAdded = true;
    }
    else
    {
      wasAdded = aAppointnement.addService(this);
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
    if (aAppointnement.indexOfService(this) == -1)
    {
      wasRemoved = true;
    }
    else
    {
      wasRemoved = aAppointnement.removeService(this);
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

  public void delete()
  {
    ArrayList<ServiceCombo> copyOfServiceCombos = new ArrayList<ServiceCombo>(serviceCombos);
    serviceCombos.clear();
    for(ServiceCombo aServiceCombo : copyOfServiceCombos)
    {
      aServiceCombo.removeService(this);
    }
    ArrayList<OwnerAccount> copyOfOwnerAccounts = new ArrayList<OwnerAccount>(ownerAccounts);
    ownerAccounts.clear();
    for(OwnerAccount aOwnerAccount : copyOfOwnerAccounts)
    {
      aOwnerAccount.removeService(this);
    }
    ArrayList<Appointnement> copyOfAppointnements = new ArrayList<Appointnement>(appointnements);
    appointnements.clear();
    for(Appointnement aAppointnement : copyOfAppointnements)
    {
      aAppointnement.removeService(this);
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "name" + ":" + getName()+ "," +
            "price" + ":" + getPrice()+ "," +
            "downTimeStart" + ":" + getDownTimeStart()+ "," +
            "downTimeEnd" + ":" + getDownTimeEnd()+ "," +
            "type" + ":" + getType()+ "]";
  }
}