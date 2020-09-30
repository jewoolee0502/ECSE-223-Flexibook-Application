/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.30.1.5099.60569f335 modeling language!*/

package ca.mcgill.ecse.flexibooksystem;
import java.util.*;
import java.sql.Time;
import java.sql.Date;

// line 25 "../../../../flexibook.ump"
public class OwnerAccount extends Account
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //OwnerAccount Associations
  private List<Business> businesses;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public OwnerAccount(String aUsername, String aPassword, boolean aIsLoggedIn, FlexiBook aFlexiBook)
  {
    super(aUsername, aPassword, aIsLoggedIn, aFlexiBook);
    businesses = new ArrayList<Business>();
  }

  //------------------------
  // INTERFACE
  //------------------------
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
  public Business addBusiness(String aContactInfo, Time aBusinesshourstart, Time aBusinesshourend, Date aHolidays, String aNameBusiness, String aAddress, FlexiBook aFlexiBook)
  {
    Business aNewBusiness = new Business(aContactInfo, aBusinesshourstart, aBusinesshourend, aHolidays, aNameBusiness, aAddress, aFlexiBook, this);
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

  public void delete()
  {
    for(int i=businesses.size(); i > 0; i--)
    {
      Business aBusiness = businesses.get(i - 1);
      aBusiness.delete();
    }
    super.delete();
  }

}