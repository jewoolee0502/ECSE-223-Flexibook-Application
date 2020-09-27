/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.30.1.5099.60569f335 modeling language!*/

package ca.mcgill.ecse.flexibooksystem;

// line 43 "../../../../flexibook.ump"
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
  private OwnerAccount ownerAccount;
  private Appointment appointment;
  private MainService mainService;
  private SubService subService;
  private Business business;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Service(String aName, float aPrice, int aDownTimeStart, int aDownTimeEnd, String aType, OwnerAccount aOwnerAccount, Appointment aAppointment, MainService aMainService, SubService aSubService, Business aBusiness)
  {
    name = aName;
    price = aPrice;
    downTimeStart = aDownTimeStart;
    downTimeEnd = aDownTimeEnd;
    type = aType;
    boolean didAddOwnerAccount = setOwnerAccount(aOwnerAccount);
    if (!didAddOwnerAccount)
    {
      throw new RuntimeException("Unable to create ServicesProvided due to ownerAccount. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    boolean didAddAppointment = setAppointment(aAppointment);
    if (!didAddAppointment)
    {
      throw new RuntimeException("Unable to create service due to appointment. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    boolean didAddMainService = setMainService(aMainService);
    if (!didAddMainService)
    {
      throw new RuntimeException("Unable to create service due to mainService. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    boolean didAddSubService = setSubService(aSubService);
    if (!didAddSubService)
    {
      throw new RuntimeException("Unable to create service due to subService. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    boolean didAddBusiness = setBusiness(aBusiness);
    if (!didAddBusiness)
    {
      throw new RuntimeException("Unable to create service due to business. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
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
  /* Code from template association_GetOne */
  public OwnerAccount getOwnerAccount()
  {
    return ownerAccount;
  }
  /* Code from template association_GetOne */
  public Appointment getAppointment()
  {
    return appointment;
  }
  /* Code from template association_GetOne */
  public MainService getMainService()
  {
    return mainService;
  }
  /* Code from template association_GetOne */
  public SubService getSubService()
  {
    return subService;
  }
  /* Code from template association_GetOne */
  public Business getBusiness()
  {
    return business;
  }
  /* Code from template association_SetOneToMany */
  public boolean setOwnerAccount(OwnerAccount aOwnerAccount)
  {
    boolean wasSet = false;
    if (aOwnerAccount == null)
    {
      return wasSet;
    }

    OwnerAccount existingOwnerAccount = ownerAccount;
    ownerAccount = aOwnerAccount;
    if (existingOwnerAccount != null && !existingOwnerAccount.equals(aOwnerAccount))
    {
      existingOwnerAccount.removeServicesProvided(this);
    }
    ownerAccount.addServicesProvided(this);
    wasSet = true;
    return wasSet;
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
      existingAppointment.removeService(this);
    }
    appointment.addService(this);
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_SetOneToMany */
  public boolean setMainService(MainService aMainService)
  {
    boolean wasSet = false;
    if (aMainService == null)
    {
      return wasSet;
    }

    MainService existingMainService = mainService;
    mainService = aMainService;
    if (existingMainService != null && !existingMainService.equals(aMainService))
    {
      existingMainService.removeService(this);
    }
    mainService.addService(this);
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_SetOneToMany */
  public boolean setSubService(SubService aSubService)
  {
    boolean wasSet = false;
    if (aSubService == null)
    {
      return wasSet;
    }

    SubService existingSubService = subService;
    subService = aSubService;
    if (existingSubService != null && !existingSubService.equals(aSubService))
    {
      existingSubService.removeService(this);
    }
    subService.addService(this);
    wasSet = true;
    return wasSet;
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
      existingBusiness.removeService(this);
    }
    business.addService(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    OwnerAccount placeholderOwnerAccount = ownerAccount;
    this.ownerAccount = null;
    if(placeholderOwnerAccount != null)
    {
      placeholderOwnerAccount.removeServicesProvided(this);
    }
    Appointment placeholderAppointment = appointment;
    this.appointment = null;
    if(placeholderAppointment != null)
    {
      placeholderAppointment.removeService(this);
    }
    MainService placeholderMainService = mainService;
    this.mainService = null;
    if(placeholderMainService != null)
    {
      placeholderMainService.removeService(this);
    }
    SubService placeholderSubService = subService;
    this.subService = null;
    if(placeholderSubService != null)
    {
      placeholderSubService.removeService(this);
    }
    Business placeholderBusiness = business;
    this.business = null;
    if(placeholderBusiness != null)
    {
      placeholderBusiness.removeService(this);
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "name" + ":" + getName()+ "," +
            "price" + ":" + getPrice()+ "," +
            "downTimeStart" + ":" + getDownTimeStart()+ "," +
            "downTimeEnd" + ":" + getDownTimeEnd()+ "," +
            "type" + ":" + getType()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "ownerAccount = "+(getOwnerAccount()!=null?Integer.toHexString(System.identityHashCode(getOwnerAccount())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "appointment = "+(getAppointment()!=null?Integer.toHexString(System.identityHashCode(getAppointment())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "mainService = "+(getMainService()!=null?Integer.toHexString(System.identityHashCode(getMainService())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "subService = "+(getSubService()!=null?Integer.toHexString(System.identityHashCode(getSubService())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "business = "+(getBusiness()!=null?Integer.toHexString(System.identityHashCode(getBusiness())):"null");
  }
}