/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.30.1.5099.60569f335 modeling language!*/

package ca.mcgill.ecse.flexibooksystem;
import java.util.*;
import java.sql.Time;
import java.sql.Date;

// line 2 "../../../../flexibook.ump"
public class FlexiBook
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //FlexiBook Associations
  private List<User> users;
  private List<Account> accounts;
  private List<Business> businesses;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public FlexiBook()
  {
    users = new ArrayList<User>();
    accounts = new ArrayList<Account>();
    businesses = new ArrayList<Business>();
  }

  //------------------------
  // INTERFACE
  //------------------------
  /* Code from template association_GetMany */
  public User getUser(int index)
  {
    User aUser = users.get(index);
    return aUser;
  }

  public List<User> getUsers()
  {
    List<User> newUsers = Collections.unmodifiableList(users);
    return newUsers;
  }

  public int numberOfUsers()
  {
    int number = users.size();
    return number;
  }

  public boolean hasUsers()
  {
    boolean has = users.size() > 0;
    return has;
  }

  public int indexOfUser(User aUser)
  {
    int index = users.indexOf(aUser);
    return index;
  }
  /* Code from template association_GetMany */
  public Account getAccount(int index)
  {
    Account aAccount = accounts.get(index);
    return aAccount;
  }

  public List<Account> getAccounts()
  {
    List<Account> newAccounts = Collections.unmodifiableList(accounts);
    return newAccounts;
  }

  public int numberOfAccounts()
  {
    int number = accounts.size();
    return number;
  }

  public boolean hasAccounts()
  {
    boolean has = accounts.size() > 0;
    return has;
  }

  public int indexOfAccount(Account aAccount)
  {
    int index = accounts.indexOf(aAccount);
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
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfUsers()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public User addUser(String aName, String aEmail, String aPhoneNumber)
  {
    return new User(aName, aEmail, aPhoneNumber, this);
  }

  public boolean addUser(User aUser)
  {
    boolean wasAdded = false;
    if (users.contains(aUser)) { return false; }
    FlexiBook existingFlexiBook = aUser.getFlexiBook();
    boolean isNewFlexiBook = existingFlexiBook != null && !this.equals(existingFlexiBook);
    if (isNewFlexiBook)
    {
      aUser.setFlexiBook(this);
    }
    else
    {
      users.add(aUser);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeUser(User aUser)
  {
    boolean wasRemoved = false;
    //Unable to remove aUser, as it must always have a flexiBook
    if (!this.equals(aUser.getFlexiBook()))
    {
      users.remove(aUser);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addUserAt(User aUser, int index)
  {  
    boolean wasAdded = false;
    if(addUser(aUser))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfUsers()) { index = numberOfUsers() - 1; }
      users.remove(aUser);
      users.add(index, aUser);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveUserAt(User aUser, int index)
  {
    boolean wasAdded = false;
    if(users.contains(aUser))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfUsers()) { index = numberOfUsers() - 1; }
      users.remove(aUser);
      users.add(index, aUser);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addUserAt(aUser, index);
    }
    return wasAdded;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfAccounts()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */


  public boolean addAccount(Account aAccount)
  {
    boolean wasAdded = false;
    if (accounts.contains(aAccount)) { return false; }
    FlexiBook existingFlexiBook = aAccount.getFlexiBook();
    boolean isNewFlexiBook = existingFlexiBook != null && !this.equals(existingFlexiBook);
    if (isNewFlexiBook)
    {
      aAccount.setFlexiBook(this);
    }
    else
    {
      accounts.add(aAccount);
    }
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeAccount(Account aAccount)
  {
    boolean wasRemoved = false;
    //Unable to remove aAccount, as it must always have a flexiBook
    if (!this.equals(aAccount.getFlexiBook()))
    {
      accounts.remove(aAccount);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addAccountAt(Account aAccount, int index)
  {  
    boolean wasAdded = false;
    if(addAccount(aAccount))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfAccounts()) { index = numberOfAccounts() - 1; }
      accounts.remove(aAccount);
      accounts.add(index, aAccount);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveAccountAt(Account aAccount, int index)
  {
    boolean wasAdded = false;
    if(accounts.contains(aAccount))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfAccounts()) { index = numberOfAccounts() - 1; }
      accounts.remove(aAccount);
      accounts.add(index, aAccount);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addAccountAt(aAccount, index);
    }
    return wasAdded;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfBusinesses()
  {
    return 0;
  }
  /* Code from template association_AddManyToOne */
  public Business addBusiness(String aContactInfo, Time aBusinesshourstart, Time aBusinesshourend, Date aHolidays, String aNameBusiness, String aAddress, OwnerAccount aOwnerAccount)
  {
    return new Business(aContactInfo, aBusinesshourstart, aBusinesshourend, aHolidays, aNameBusiness, aAddress, this, aOwnerAccount);
  }

  public boolean addBusiness(Business aBusiness)
  {
    boolean wasAdded = false;
    if (businesses.contains(aBusiness)) { return false; }
    FlexiBook existingFlexiBook = aBusiness.getFlexiBook();
    boolean isNewFlexiBook = existingFlexiBook != null && !this.equals(existingFlexiBook);
    if (isNewFlexiBook)
    {
      aBusiness.setFlexiBook(this);
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
    //Unable to remove aBusiness, as it must always have a flexiBook
    if (!this.equals(aBusiness.getFlexiBook()))
    {
      businesses.remove(aBusiness);
      wasRemoved = true;
    }
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
    while (users.size() > 0)
    {
      User aUser = users.get(users.size() - 1);
      aUser.delete();
      users.remove(aUser);
    }
    
    while (accounts.size() > 0)
    {
      Account aAccount = accounts.get(accounts.size() - 1);
      aAccount.delete();
      accounts.remove(aAccount);
    }
    
    while (businesses.size() > 0)
    {
      Business aBusiness = businesses.get(businesses.size() - 1);
      aBusiness.delete();
      businesses.remove(aBusiness);
    }
    
  }

}