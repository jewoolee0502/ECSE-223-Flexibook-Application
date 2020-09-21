/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.30.1.5099.60569f335 modeling language!*/

package ca.mcgill.ecse.flexibooksystem;
import java.util.*;

// line 76 "../../../../flexibook.ump"
public class Account
{

  //------------------------
  // STATIC VARIABLES
  //------------------------

  private static Map<String, Account> accountsByUsername = new HashMap<String, Account>();

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Account Attributes
  private String username;
  private String password;
  private boolean isLoggedIn;

  //Account Associations
  private List<Person> persons;
  private List<FlexiBook> flexiBooks;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Account(String aUsername, String aPassword, boolean aIsLoggedIn)
  {
    password = aPassword;
    isLoggedIn = aIsLoggedIn;
    if (!setUsername(aUsername))
    {
      throw new RuntimeException("Cannot create due to duplicate username. See http://manual.umple.org?RE003ViolationofUniqueness.html");
    }
    persons = new ArrayList<Person>();
    flexiBooks = new ArrayList<FlexiBook>();
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setUsername(String aUsername)
  {
    boolean wasSet = false;
    String anOldUsername = getUsername();
    if (anOldUsername != null && anOldUsername.equals(aUsername)) {
      return true;
    }
    if (hasWithUsername(aUsername)) {
      return wasSet;
    }
    username = aUsername;
    wasSet = true;
    if (anOldUsername != null) {
      accountsByUsername.remove(anOldUsername);
    }
    accountsByUsername.put(aUsername, this);
    return wasSet;
  }

  public boolean setPassword(String aPassword)
  {
    boolean wasSet = false;
    password = aPassword;
    wasSet = true;
    return wasSet;
  }

  public boolean setIsLoggedIn(boolean aIsLoggedIn)
  {
    boolean wasSet = false;
    isLoggedIn = aIsLoggedIn;
    wasSet = true;
    return wasSet;
  }

  public String getUsername()
  {
    return username;
  }
  /* Code from template attribute_GetUnique */
  public static Account getWithUsername(String aUsername)
  {
    return accountsByUsername.get(aUsername);
  }
  /* Code from template attribute_HasUnique */
  public static boolean hasWithUsername(String aUsername)
  {
    return getWithUsername(aUsername) != null;
  }

  public String getPassword()
  {
    return password;
  }

  public boolean getIsLoggedIn()
  {
    return isLoggedIn;
  }
  /* Code from template attribute_IsBoolean */
  public boolean isIsLoggedIn()
  {
    return isLoggedIn;
  }
  /* Code from template association_GetMany */
  public Person getPerson(int index)
  {
    Person aPerson = persons.get(index);
    return aPerson;
  }

  public List<Person> getPersons()
  {
    List<Person> newPersons = Collections.unmodifiableList(persons);
    return newPersons;
  }

  public int numberOfPersons()
  {
    int number = persons.size();
    return number;
  }

  public boolean hasPersons()
  {
    boolean has = persons.size() > 0;
    return has;
  }

  public int indexOfPerson(Person aPerson)
  {
    int index = persons.indexOf(aPerson);
    return index;
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
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfPersons()
  {
    return 0;
  }
  /* Code from template association_AddManyToManyMethod */
  public boolean addPerson(Person aPerson)
  {
    boolean wasAdded = false;
    if (persons.contains(aPerson)) { return false; }
    persons.add(aPerson);
    if (aPerson.indexOfAccount(this) != -1)
    {
      wasAdded = true;
    }
    else
    {
      wasAdded = aPerson.addAccount(this);
      if (!wasAdded)
      {
        persons.remove(aPerson);
      }
    }
    return wasAdded;
  }
  /* Code from template association_RemoveMany */
  public boolean removePerson(Person aPerson)
  {
    boolean wasRemoved = false;
    if (!persons.contains(aPerson))
    {
      return wasRemoved;
    }

    int oldIndex = persons.indexOf(aPerson);
    persons.remove(oldIndex);
    if (aPerson.indexOfAccount(this) == -1)
    {
      wasRemoved = true;
    }
    else
    {
      wasRemoved = aPerson.removeAccount(this);
      if (!wasRemoved)
      {
        persons.add(oldIndex,aPerson);
      }
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addPersonAt(Person aPerson, int index)
  {  
    boolean wasAdded = false;
    if(addPerson(aPerson))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfPersons()) { index = numberOfPersons() - 1; }
      persons.remove(aPerson);
      persons.add(index, aPerson);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMovePersonAt(Person aPerson, int index)
  {
    boolean wasAdded = false;
    if(persons.contains(aPerson))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfPersons()) { index = numberOfPersons() - 1; }
      persons.remove(aPerson);
      persons.add(index, aPerson);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addPersonAt(aPerson, index);
    }
    return wasAdded;
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
    if (aFlexiBook.indexOfAccount(this) != -1)
    {
      wasAdded = true;
    }
    else
    {
      wasAdded = aFlexiBook.addAccount(this);
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
    if (aFlexiBook.indexOfAccount(this) == -1)
    {
      wasRemoved = true;
    }
    else
    {
      wasRemoved = aFlexiBook.removeAccount(this);
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

  public void delete()
  {
    accountsByUsername.remove(getUsername());
    ArrayList<Person> copyOfPersons = new ArrayList<Person>(persons);
    persons.clear();
    for(Person aPerson : copyOfPersons)
    {
      aPerson.removeAccount(this);
    }
    ArrayList<FlexiBook> copyOfFlexiBooks = new ArrayList<FlexiBook>(flexiBooks);
    flexiBooks.clear();
    for(FlexiBook aFlexiBook : copyOfFlexiBooks)
    {
      aFlexiBook.removeAccount(this);
    }
  }


  public String toString()
  {
    return super.toString() + "["+
            "username" + ":" + getUsername()+ "," +
            "password" + ":" + getPassword()+ "," +
            "isLoggedIn" + ":" + getIsLoggedIn()+ "]";
  }
}