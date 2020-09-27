/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.30.1.5099.60569f335 modeling language!*/

package ca.mcgill.ecse.flexibooksystem;

// line 71 "../../../../flexibook.ump"
public class ServiceCombo
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //ServiceCombo Attributes
  private boolean hasSubServices;

  //ServiceCombo Associations
  private MainService main;
  private SubService sub;
  private OwnerAccount ownerAccount;
  private Appointment appointment;
  private Business business;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public ServiceCombo(boolean aHasSubServices, MainService aMain, OwnerAccount aOwnerAccount, Appointment aAppointment, Business aBusiness)
  {
    hasSubServices = aHasSubServices;
    if (aMain == null || aMain.getServiceCombo() != null)
    {
      throw new RuntimeException("Unable to create ServiceCombo due to aMain. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    main = aMain;
    boolean didAddOwnerAccount = setOwnerAccount(aOwnerAccount);
    if (!didAddOwnerAccount)
    {
      throw new RuntimeException("Unable to create makecombo due to ownerAccount. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    boolean didAddAppointment = setAppointment(aAppointment);
    if (!didAddAppointment)
    {
      throw new RuntimeException("Unable to create choosecombo due to appointment. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    boolean didAddBusiness = setBusiness(aBusiness);
    if (!didAddBusiness)
    {
      throw new RuntimeException("Unable to create combo due to business. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
  }

  public ServiceCombo(boolean aHasSubServices, int aNumberofServicesForMain, OwnerAccount aOwnerAccount, Appointment aAppointment, Business aBusiness)
  {
    hasSubServices = aHasSubServices;
    main = new MainService(aNumberofServicesForMain, this);
    boolean didAddOwnerAccount = setOwnerAccount(aOwnerAccount);
    if (!didAddOwnerAccount)
    {
      throw new RuntimeException("Unable to create makecombo due to ownerAccount. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    boolean didAddAppointment = setAppointment(aAppointment);
    if (!didAddAppointment)
    {
      throw new RuntimeException("Unable to create choosecombo due to appointment. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
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

  public boolean setHasSubServices(boolean aHasSubServices)
  {
    boolean wasSet = false;
    hasSubServices = aHasSubServices;
    wasSet = true;
    return wasSet;
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
  /* Code from template association_GetOne */
  public MainService getMain()
  {
    return main;
  }
  /* Code from template association_GetOne */
  public SubService getSub()
  {
    return sub;
  }

  public boolean hasSub()
  {
    boolean has = sub != null;
    return has;
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
  public Business getBusiness()
  {
    return business;
  }
  /* Code from template association_SetOptionalOneToOne */
  public boolean setSub(SubService aNewSub)
  {
    boolean wasSet = false;
    if (sub != null && !sub.equals(aNewSub) && equals(sub.getServiceCombo()))
    {
      //Unable to setSub, as existing sub would become an orphan
      return wasSet;
    }

    sub = aNewSub;
    ServiceCombo anOldServiceCombo = aNewSub != null ? aNewSub.getServiceCombo() : null;

    if (!this.equals(anOldServiceCombo))
    {
      if (anOldServiceCombo != null)
      {
        anOldServiceCombo.sub = null;
      }
      if (sub != null)
      {
        sub.setServiceCombo(this);
      }
    }
    wasSet = true;
    return wasSet;
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
      existingOwnerAccount.removeMakecombo(this);
    }
    ownerAccount.addMakecombo(this);
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
      existingAppointment.removeChoosecombo(this);
    }
    appointment.addChoosecombo(this);
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
      existingBusiness.removeCombo(this);
    }
    business.addCombo(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    MainService existingMain = main;
    main = null;
    if (existingMain != null)
    {
      existingMain.delete();
    }
    SubService existingSub = sub;
    sub = null;
    if (existingSub != null)
    {
      existingSub.delete();
      existingSub.setServiceCombo(null);
    }
    OwnerAccount placeholderOwnerAccount = ownerAccount;
    this.ownerAccount = null;
    if(placeholderOwnerAccount != null)
    {
      placeholderOwnerAccount.removeMakecombo(this);
    }
    Appointment placeholderAppointment = appointment;
    this.appointment = null;
    if(placeholderAppointment != null)
    {
      placeholderAppointment.removeChoosecombo(this);
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
            "hasSubServices" + ":" + getHasSubServices()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "main = "+(getMain()!=null?Integer.toHexString(System.identityHashCode(getMain())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "sub = "+(getSub()!=null?Integer.toHexString(System.identityHashCode(getSub())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "ownerAccount = "+(getOwnerAccount()!=null?Integer.toHexString(System.identityHashCode(getOwnerAccount())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "appointment = "+(getAppointment()!=null?Integer.toHexString(System.identityHashCode(getAppointment())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "business = "+(getBusiness()!=null?Integer.toHexString(System.identityHashCode(getBusiness())):"null");
  }
}