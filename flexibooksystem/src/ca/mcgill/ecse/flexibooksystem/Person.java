/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.30.1.5099.60569f335 modeling language!*/

package ca.mcgill.ecse.flexibooksystem;
import java.util.*;

// line 2 "../../../../flexibook.ump"
public class Person
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Person Attributes
  private String name;
  private String email;
  private String phoneNumber;

  //Person Associations
  private List<FlexiBook> flexiBooks;
  private List<Account> accounts;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Person(String aName, String aEmail, String aPhoneNumber)
  {
    name = aName;
    email = aEmail;
    phoneNumber = aPhoneNumber;
    flexiBooks = new ArrayList<FlexiBook>();
    accounts = new ArrayList<Account>();
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

  public boolean setEmail(String aEmail)
  {
    boolean wasSet = false;
    email = aEmail;
    wasSet = true;
    return wasSet;
  }

  public boolean setPhoneNumber(String aPhoneNumber)
  {
    boolean wasSet = false;
    phoneNumber = aPhoneNumber;
    wasSet = true;
    return wasSet;
  }

  public String getName()
  {
    return name;
  }

  public String getEmail()
  {
    return email;
  }

  public String getPhoneNumber()
  {
    return phoneNumber;
  }
  /* Code from template association_GetMany */
  public FlexiBook getFlexiBook(int index)
  {
    FlexiBook aFlexiBook = flexiBooks.get(index);
    return aFlexiBook;
  }

  public List<FlexiBook> getFlexiBooks()
  {
    List<FlexiBook> newFlexiBooks = Collections.unmodifiableList(flexiBooks);
    return newFlexiBooks;
  }

  public int numberOfFlexiBooks()
  {
    int number = flexiBooks.size();
    return number;
  }

  public boolean hasFlexiBooks()
  {
    boolean has = flexiBooks.size() > 0;
    return has;
  }

  public int indexOfFlexiBook(FlexiBook aFlexiBook)
  {
    int index = flexiBooks.indexOf(aFlexiBook);
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
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfFlexiBooks()
  {
    return 0;
  }
  /* Code from template association_AddManyToManyMethod */
  public boolean addFlexiBook(FlexiBook aFlexiBook)
  {
    boolean wasAdded = false;
    if (flexiBooks.contains(aFlexiBook)) { return false; }
    flexiBooks.add(aFlexiBook);
    if (aFlexiBook.indexOfPerson(this) != -1)
    {
      wasAdded = true;
    }
    else
    {
      wasAdded = aFlexiBook.addPerson(this);
      if (!wasAdded)
      {
        flexiBooks.remove(aFlexiBook);
      }
    }
    return wasAdded;
  }
  /* Code from template association_RemoveMany */
  public boolean removeFlexiBook(FlexiBook aFlexiBook)
  {
    boolean wasRemoved = false;
    if (!flexiBooks.contains(aFlexiBook))
    {
      return wasRemoved;
    }

    int oldIndex = flexiBooks.indexOf(aFlexiBook);
    flexiBooks.remove(oldIndex);
    if (aFlexiBook.indexOfPerson(this) == -1)
    {
      wasRemoved = true;
    }
    else
    {
      wasRemoved = aFlexiBook.removePerson(this);
      if (!wasRemoved)
      {
        flexiBooks.add(oldIndex,aFlexiBook);
      }
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addFlexiBookAt(FlexiBook aFlexiBook, int index)
  {  
    boolean wasAdded = false;
    if(addFlexiBook(aFlexiBook))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfFlexiBooks()) { index = numberOfFlexiBooks() - 1; }
      flexiBooks.remove(aFlexiBook);
      flexiBooks.add(index, aFlexiBook);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveFlexiBookAt(FlexiBook aFlexiBook, int index)
  {
    boolean wasAdded = false;
    if(flexiBooks.contains(aFlexiBook))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfFlexiBooks()) { index = numberOfFlexiBooks() - 1; }
      flexiBooks.remove(aFlexiBook);
      flexiBooks.add(index, aFlexiBook);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addFlexiBookAt(aFlexiBook, index);
    }
    return wasAdded;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfAccounts()
  {
    return 0;
  }
  /* Code from template association_AddManyToManyMethod */
  public boolean addAccount(Account aAccount)
  {
    boolean wasAdded = false;
    if (accounts.contains(aAccount)) { return false; }
    accounts.add(aAccount);
    if (aAccount.indexOfPerson(this) != -1)
    {
      wasAdded = true;
    }
    else
    {
      wasAdded = aAccount.addPerson(this);
      if (!wasAdded)
      {
        accounts.remove(aAccount);
      }
    }
    return wasAdded;
  }
  /* Code from template association_RemoveMany */
  public boolean removeAccount(Account aAccount)
  {
    boolean wasRemoved = false;
    if (!accounts.contains(aAccount))
    {
      return wasRemoved;
    }

    int oldIndex = accounts.indexOf(aAccount);
    accounts.remove(oldIndex);
    if (aAccount.indexOfPerson(this) == -1)
    {
      wasRemoved = true;
    }
    else
    {
      wasRemoved = aAccount.removePerson(this);
      if (!wasRemoved)
      {
        accounts.add(oldIndex,aAccount);
      }
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

  public void delete()
  {
    ArrayList<FlexiBook> copyOfFlexiBooks = new ArrayList<FlexiBook>(flexiBooks);
    flexiBooks.clear();
    for(FlexiBook aFlexiBook : copyOfFlexiBooks)
    {
      aFlexiBook.removePerson(this);
    }
    ArrayList<Account> copyOfAccounts = new ArrayList<Account>(accounts);
    accounts.clear();
    for(Account aAccount : copyOfAccounts)
    {
      aAccount.removePerson(this);
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "name" + ":" + getName()+ "," +
            "email" + ":" + getEmail()+ "," +
            "phoneNumber" + ":" + getPhoneNumber()+ "]";
  }
}