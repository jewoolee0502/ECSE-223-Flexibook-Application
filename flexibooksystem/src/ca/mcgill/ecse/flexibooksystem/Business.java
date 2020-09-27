/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.30.1.5099.60569f335 modeling language!*/

package ca.mcgill.ecse.flexibooksystem;
import java.sql.Date;
import java.util.*;

// line 79 "../../../../flexibook.ump"
public class Business
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Business Attributes
  private String contactInfo;
  private int businessHours;
  private Date holidays;
  private String nameBusiness;
  private String address;

  //Business Associations
  private List<ServiceCombo> combo;
  private List<Service> services;
  private FlexiBook flexiBook;
  private OwnerAccount ownerAccount;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Business(String aContactInfo, int aBusinessHours, Date aHolidays, String aNameBusiness, String aAddress, FlexiBook aFlexiBook, OwnerAccount aOwnerAccount)
  {
    contactInfo = aContactInfo;
    businessHours = aBusinessHours;
    holidays = aHolidays;
    nameBusiness = aNameBusiness;
    address = aAddress;
    combo = new ArrayList<ServiceCombo>();
    services = new ArrayList<Service>();
    boolean didAddFlexiBook = setFlexiBook(aFlexiBook);
    if (!didAddFlexiBook)
    {
      throw new RuntimeException("Unable to create business due to flexiBook. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    boolean didAddOwnerAccount = setOwnerAccount(aOwnerAccount);
    if (!didAddOwnerAccount)
    {
      throw new RuntimeException("Unable to create business due to ownerAccount. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
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
  public ServiceCombo getCombo(int index)
  {
    ServiceCombo aCombo = combo.get(index);
    return aCombo;
  }

  public List<ServiceCombo> getCombo()
  {
    List<ServiceCombo> newCombo = Collections.unmodifiableList(combo);
    return newCombo;
  }

  public int numberOfCombo()
  {
    int number = combo.size();
    return number;
  }

  public boolean hasCombo()
  {
    boolean has = combo.size() > 0;
    return has;
  }

  public int indexOfCombo(ServiceCombo aCombo)
  {
    int index = combo.indexOf(aCombo);
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
  public FlexiBook getFlexiBook()
  {
    return flexiBook;
  }
  /* Code from template association_GetOne */
  public OwnerAccount getOwnerAccount()
  {
    return ownerAccount;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfCombo()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public ServiceCombo addCombo(boolean aHasSubServices, MainService aMain, OwnerAccount aOwnerAccount, Appointment aAppointment)
  {
    return new ServiceCombo(aHasSubServices, aMain, aOwnerAccount, aAppointment, this);
  }

  public boolean addCombo(ServiceCombo aCombo)
  {
    boolean wasAdded = false;
    if (combo.contains(aCombo)) { return false; }
    Business existingBusiness = aCombo.getBusiness();
    boolean isNewBusiness = existingBusiness != null && !this.equals(existingBusiness);
    if (isNewBusiness)
    {
      aCombo.setBusiness(this);
    }
    else
    {
      combo.add(aCombo);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeCombo(ServiceCombo aCombo)
  {
    boolean wasRemoved = false;
    //Unable to remove aCombo, as it must always have a business
    if (!this.equals(aCombo.getBusiness()))
    {
      combo.remove(aCombo);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addComboAt(ServiceCombo aCombo, int index)
  {  
    boolean wasAdded = false;
    if(addCombo(aCombo))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfCombo()) { index = numberOfCombo() - 1; }
      combo.remove(aCombo);
      combo.add(index, aCombo);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveComboAt(ServiceCombo aCombo, int index)
  {
    boolean wasAdded = false;
    if(combo.contains(aCombo))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfCombo()) { index = numberOfCombo() - 1; }
      combo.remove(aCombo);
      combo.add(index, aCombo);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addComboAt(aCombo, index);
    }
    return wasAdded;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfServices()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public Service addService(String aName, float aPrice, int aDownTimeStart, int aDownTimeEnd, String aType, OwnerAccount aOwnerAccount, Appointment aAppointment, MainService aMainService, SubService aSubService)
  {
    return new Service(aName, aPrice, aDownTimeStart, aDownTimeEnd, aType, aOwnerAccount, aAppointment, aMainService, aSubService, this);
  }

  public boolean addService(Service aService)
  {
    boolean wasAdded = false;
    if (services.contains(aService)) { return false; }
    Business existingBusiness = aService.getBusiness();
    boolean isNewBusiness = existingBusiness != null && !this.equals(existingBusiness);
    if (isNewBusiness)
    {
      aService.setBusiness(this);
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
    //Unable to remove aService, as it must always have a business
    if (!this.equals(aService.getBusiness()))
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
  public boolean setFlexiBook(FlexiBook aFlexiBook)
  {
    boolean wasSet = false;
    if (aFlexiBook == null)
    {
      return wasSet;
    }

    FlexiBook existingFlexiBook = flexiBook;
    flexiBook = aFlexiBook;
    if (existingFlexiBook != null && !existingFlexiBook.equals(aFlexiBook))
    {
      existingFlexiBook.removeBusiness(this);
    }
    flexiBook.addBusiness(this);
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_SetOneToMandatoryMany */
  public boolean setOwnerAccount(OwnerAccount aOwnerAccount)
  {
    boolean wasSet = false;
    //Must provide ownerAccount to business
    if (aOwnerAccount == null)
    {
      return wasSet;
    }

    if (ownerAccount != null && ownerAccount.numberOfBusinesses() <= OwnerAccount.minimumNumberOfBusinesses())
    {
      return wasSet;
    }

    OwnerAccount existingOwnerAccount = ownerAccount;
    ownerAccount = aOwnerAccount;
    if (existingOwnerAccount != null && !existingOwnerAccount.equals(aOwnerAccount))
    {
      boolean didRemove = existingOwnerAccount.removeBusiness(this);
      if (!didRemove)
      {
        ownerAccount = existingOwnerAccount;
        return wasSet;
      }
    }
    ownerAccount.addBusiness(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    while (combo.size() > 0)
    {
      ServiceCombo aCombo = combo.get(combo.size() - 1);
      aCombo.delete();
      combo.remove(aCombo);
    }
    
    while (services.size() > 0)
    {
      Service aService = services.get(services.size() - 1);
      aService.delete();
      services.remove(aService);
    }
    
    FlexiBook placeholderFlexiBook = flexiBook;
    this.flexiBook = null;
    if(placeholderFlexiBook != null)
    {
      placeholderFlexiBook.removeBusiness(this);
    }
    OwnerAccount placeholderOwnerAccount = ownerAccount;
    this.ownerAccount = null;
    if(placeholderOwnerAccount != null)
    {
      placeholderOwnerAccount.removeBusiness(this);
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "contactInfo" + ":" + getContactInfo()+ "," +
            "businessHours" + ":" + getBusinessHours()+ "," +
            "nameBusiness" + ":" + getNameBusiness()+ "," +
            "address" + ":" + getAddress()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "holidays" + "=" + (getHolidays() != null ? !getHolidays().equals(this)  ? getHolidays().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "flexiBook = "+(getFlexiBook()!=null?Integer.toHexString(System.identityHashCode(getFlexiBook())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "ownerAccount = "+(getOwnerAccount()!=null?Integer.toHexString(System.identityHashCode(getOwnerAccount())):"null");
  }
}