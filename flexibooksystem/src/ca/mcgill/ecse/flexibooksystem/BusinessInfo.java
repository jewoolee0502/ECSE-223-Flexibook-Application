/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.30.1.5099.60569f335 modeling language!*/

package ca.mcgill.ecse.flexibooksystem;
import java.sql.Date;
import java.util.*;

// line 66 "../../../../flexibook.ump"
public class BusinessInfo
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //BusinessInfo Attributes
  private String contactInfo;
  private int businessHours;
  private Date holidays;
  private String nameBusiness;
  private String address;

  //BusinessInfo Associations
  private List<OwnerAccount> ownerAccounts;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public BusinessInfo(String aContactInfo, int aBusinessHours, Date aHolidays, String aNameBusiness, String aAddress)
  {
    contactInfo = aContactInfo;
    businessHours = aBusinessHours;
    holidays = aHolidays;
    nameBusiness = aNameBusiness;
    address = aAddress;
    ownerAccounts = new ArrayList<OwnerAccount>();
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setContactInfo(String aContactInfo)
  {
    boolean wasSet = false;
    contactInfo = aContactInfo;
    wasSet = true;
    return wasSet;
  }

  public boolean setBusinessHours(int aBusinessHours)
  {
    boolean wasSet = false;
    businessHours = aBusinessHours;
    wasSet = true;
    return wasSet;
  }

  public boolean setHolidays(Date aHolidays)
  {
    boolean wasSet = false;
    holidays = aHolidays;
    wasSet = true;
    return wasSet;
  }

  public boolean setNameBusiness(String aNameBusiness)
  {
    boolean wasSet = false;
    nameBusiness = aNameBusiness;
    wasSet = true;
    return wasSet;
  }

  public boolean setAddress(String aAddress)
  {
    boolean wasSet = false;
    address = aAddress;
    wasSet = true;
    return wasSet;
  }

  public String getContactInfo()
  {
    return contactInfo;
  }

  public int getBusinessHours()
  {
    return businessHours;
  }

  public Date getHolidays()
  {
    return holidays;
  }

  public String getNameBusiness()
  {
    return nameBusiness;
  }

  public String getAddress()
  {
    return address;
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
    if (aOwnerAccount.indexOfBusinessInfo(this) != -1)
    {
      wasAdded = true;
    }
    else
    {
      wasAdded = aOwnerAccount.addBusinessInfo(this);
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
    if (aOwnerAccount.indexOfBusinessInfo(this) == -1)
    {
      wasRemoved = true;
    }
    else
    {
      wasRemoved = aOwnerAccount.removeBusinessInfo(this);
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
    ArrayList<OwnerAccount> copyOfOwnerAccounts = new ArrayList<OwnerAccount>(ownerAccounts);
    ownerAccounts.clear();
    for(OwnerAccount aOwnerAccount : copyOfOwnerAccounts)
    {
      aOwnerAccount.removeBusinessInfo(this);
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "contactInfo" + ":" + getContactInfo()+ "," +
            "businessHours" + ":" + getBusinessHours()+ "," +
            "nameBusiness" + ":" + getNameBusiness()+ "," +
            "address" + ":" + getAddress()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "holidays" + "=" + (getHolidays() != null ? !getHolidays().equals(this)  ? getHolidays().toString().replaceAll("  ","    ") : "this" : "null");
  }
}