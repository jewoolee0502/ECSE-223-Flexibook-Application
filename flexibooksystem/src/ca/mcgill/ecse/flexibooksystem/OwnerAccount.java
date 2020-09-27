/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.30.1.5099.60569f335 modeling language!*/

package ca.mcgill.ecse.flexibooksystem;
import java.util.*;
import java.sql.Date;
import java.sql.Time;

// line 25 "../../../../flexibook.ump"
public class OwnerAccount extends Account
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //OwnerAccount Associations
  private List<ServiceCombo> makecombo;
  private List<Business> businesses;
  private List<Service> ServicesProvided;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public OwnerAccount(String aUsername, String aPassword, boolean aIsLoggedIn, FlexiBook aFlexiBook, User aUser)
  {
    super(aUsername, aPassword, aIsLoggedIn, aFlexiBook, aUser);
    makecombo = new ArrayList<ServiceCombo>();
    businesses = new ArrayList<Business>();
    ServicesProvided = new ArrayList<Service>();
  }

  //------------------------
  // INTERFACE
  //------------------------
  /* Code from template association_GetMany */
  public ServiceCombo getMakecombo(int index)
  {
    ServiceCombo aMakecombo = makecombo.get(index);
    return aMakecombo;
  }

  public List<ServiceCombo> getMakecombo()
  {
    List<ServiceCombo> newMakecombo = Collections.unmodifiableList(makecombo);
    return newMakecombo;
  }

  public int numberOfMakecombo()
  {
    int number = makecombo.size();
    return number;
  }

  public boolean hasMakecombo()
  {
    boolean has = makecombo.size() > 0;
    return has;
  }

  public int indexOfMakecombo(ServiceCombo aMakecombo)
  {
    int index = makecombo.indexOf(aMakecombo);
    return index;
  }
  /* Code from template association_GetMany */
  public Business getBusiness(int index)
  {
    Business aBusiness = businesses.get(index);
    return aBusiness;
  }

  public List<Business> getBusinesses()
  {
    List<Business> newBusinesses = Collections.unmodifiableList(businesses);
    return newBusinesses;
  }

  public int numberOfBusinesses()
  {
    int number = businesses.size();
    return number;
  }

  public boolean hasBusinesses()
  {
    boolean has = businesses.size() > 0;
    return has;
  }

  public int indexOfBusiness(Business aBusiness)
  {
    int index = businesses.indexOf(aBusiness);
    return index;
  }
  /* Code from template association_GetMany */
  public Service getServicesProvided(int index)
  {
    Service aServicesProvided = ServicesProvided.get(index);
    return aServicesProvided;
  }

  public List<Service> getServicesProvided()
  {
    List<Service> newServicesProvided = Collections.unmodifiableList(ServicesProvided);
    return newServicesProvided;
  }

  public int numberOfServicesProvided()
  {
    int number = ServicesProvided.size();
    return number;
  }

  public boolean hasServicesProvided()
  {
    boolean has = ServicesProvided.size() > 0;
    return has;
  }

  public int indexOfServicesProvided(Service aServicesProvided)
  {
    int index = ServicesProvided.indexOf(aServicesProvided);
    return index;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfMakecombo()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public ServiceCombo addMakecombo(int aMaxnumberOfServices, boolean aHasSubServices, MainService aMain, Appointment aAppointment, Business aBusiness)
  {
    return new ServiceCombo(aMaxnumberOfServices, aHasSubServices, aMain, this, aAppointment, aBusiness);
  }

  public boolean addMakecombo(ServiceCombo aMakecombo)
  {
    boolean wasAdded = false;
    if (makecombo.contains(aMakecombo)) { return false; }
    OwnerAccount existingOwnerAccount = aMakecombo.getOwnerAccount();
    boolean isNewOwnerAccount = existingOwnerAccount != null && !this.equals(existingOwnerAccount);
    if (isNewOwnerAccount)
    {
      aMakecombo.setOwnerAccount(this);
    }
    else
    {
      makecombo.add(aMakecombo);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeMakecombo(ServiceCombo aMakecombo)
  {
    boolean wasRemoved = false;
    //Unable to remove aMakecombo, as it must always have a ownerAccount
    if (!this.equals(aMakecombo.getOwnerAccount()))
    {
      makecombo.remove(aMakecombo);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addMakecomboAt(ServiceCombo aMakecombo, int index)
  {  
    boolean wasAdded = false;
    if(addMakecombo(aMakecombo))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfMakecombo()) { index = numberOfMakecombo() - 1; }
      makecombo.remove(aMakecombo);
      makecombo.add(index, aMakecombo);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveMakecomboAt(ServiceCombo aMakecombo, int index)
  {
    boolean wasAdded = false;
    if(makecombo.contains(aMakecombo))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfMakecombo()) { index = numberOfMakecombo() - 1; }
      makecombo.remove(aMakecombo);
      makecombo.add(index, aMakecombo);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addMakecomboAt(aMakecombo, index);
    }
    return wasAdded;
  }
  /* Code from template association_IsNumberOfValidMethod */
  public boolean isNumberOfBusinessesValid()
  {
    boolean isValid = numberOfBusinesses() >= minimumNumberOfBusinesses();
    return isValid;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfBusinesses()
  {
    return 1;
  }
  /* Code from template association_AddMandatoryManyToOne */
  public Business addBusiness(String aContactInfo, int aBusinessHours, Date aHolidays, String aNameBusiness, String aAddress, FlexiBook aFlexiBook)
  {
    Business aNewBusiness = new Business(aContactInfo, aBusinessHours, aHolidays, aNameBusiness, aAddress, aFlexiBook, this);
    return aNewBusiness;
  }

  public boolean addBusiness(Business aBusiness)
  {
    boolean wasAdded = false;
    if (businesses.contains(aBusiness)) { return false; }
    OwnerAccount existingOwnerAccount = aBusiness.getOwnerAccount();
    boolean isNewOwnerAccount = existingOwnerAccount != null && !this.equals(existingOwnerAccount);

    if (isNewOwnerAccount && existingOwnerAccount.numberOfBusinesses() <= minimumNumberOfBusinesses())
    {
      return wasAdded;
    }
    if (isNewOwnerAccount)
    {
      aBusiness.setOwnerAccount(this);
    }
    else
    {
      businesses.add(aBusiness);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeBusiness(Business aBusiness)
  {
    boolean wasRemoved = false;
    //Unable to remove aBusiness, as it must always have a ownerAccount
    if (this.equals(aBusiness.getOwnerAccount()))
    {
      return wasRemoved;
    }

    //ownerAccount already at minimum (1)
    if (numberOfBusinesses() <= minimumNumberOfBusinesses())
    {
      return wasRemoved;
    }

    businesses.remove(aBusiness);
    wasRemoved = true;
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addBusinessAt(Business aBusiness, int index)
  {  
    boolean wasAdded = false;
    if(addBusiness(aBusiness))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfBusinesses()) { index = numberOfBusinesses() - 1; }
      businesses.remove(aBusiness);
      businesses.add(index, aBusiness);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveBusinessAt(Business aBusiness, int index)
  {
    boolean wasAdded = false;
    if(businesses.contains(aBusiness))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfBusinesses()) { index = numberOfBusinesses() - 1; }
      businesses.remove(aBusiness);
      businesses.add(index, aBusiness);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addBusinessAt(aBusiness, index);
    }
    return wasAdded;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfServicesProvided()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public Service addServicesProvided(String aName, float aPrice, Time aDownTimeStart, Time aDownTimeEnd, String aType, Appointment aAppointment, MainService aMainService, SubService aSubService, Business aBusiness)
  {
    return new Service(aName, aPrice, aDownTimeStart, aDownTimeEnd, aType, this, aAppointment, aMainService, aSubService, aBusiness);
  }

  public boolean addServicesProvided(Service aServicesProvided)
  {
    boolean wasAdded = false;
    if (ServicesProvided.contains(aServicesProvided)) { return false; }
    OwnerAccount existingOwnerAccount = aServicesProvided.getOwnerAccount();
    boolean isNewOwnerAccount = existingOwnerAccount != null && !this.equals(existingOwnerAccount);
    if (isNewOwnerAccount)
    {
      aServicesProvided.setOwnerAccount(this);
    }
    else
    {
      ServicesProvided.add(aServicesProvided);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeServicesProvided(Service aServicesProvided)
  {
    boolean wasRemoved = false;
    //Unable to remove aServicesProvided, as it must always have a ownerAccount
    if (!this.equals(aServicesProvided.getOwnerAccount()))
    {
      ServicesProvided.remove(aServicesProvided);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addServicesProvidedAt(Service aServicesProvided, int index)
  {  
    boolean wasAdded = false;
    if(addServicesProvided(aServicesProvided))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfServicesProvided()) { index = numberOfServicesProvided() - 1; }
      ServicesProvided.remove(aServicesProvided);
      ServicesProvided.add(index, aServicesProvided);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveServicesProvidedAt(Service aServicesProvided, int index)
  {
    boolean wasAdded = false;
    if(ServicesProvided.contains(aServicesProvided))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfServicesProvided()) { index = numberOfServicesProvided() - 1; }
      ServicesProvided.remove(aServicesProvided);
      ServicesProvided.add(index, aServicesProvided);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addServicesProvidedAt(aServicesProvided, index);
    }
    return wasAdded;
  }

  public void delete()
  {
    for(int i=makecombo.size(); i > 0; i--)
    {
      ServiceCombo aMakecombo = makecombo.get(i - 1);
      aMakecombo.delete();
    }
    for(int i=businesses.size(); i > 0; i--)
    {
      Business aBusiness = businesses.get(i - 1);
      aBusiness.delete();
    }
    for(int i=ServicesProvided.size(); i > 0; i--)
    {
      Service aServicesProvided = ServicesProvided.get(i - 1);
      aServicesProvided.delete();
    }
    super.delete();
  }

}