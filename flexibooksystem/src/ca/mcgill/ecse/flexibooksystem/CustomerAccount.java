/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.30.1.5099.60569f335 modeling language!*/

package ca.mcgill.ecse.flexibooksystem;
import java.util.*;

// line 9 "../../../../flexibook.ump"
public class CustomerAccount extends Account
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //CustomerAccount Attributes
  private boolean isDeleted;

  //CustomerAccount Associations
  private List<Appointnement> appointnements;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public CustomerAccount(String aUsername, String aPassword, boolean aIsLoggedIn, boolean aIsDeleted)
  {
    super(aUsername, aPassword, aIsLoggedIn);
    isDeleted = aIsDeleted;
    appointnements = new ArrayList<Appointnement>();
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setIsDeleted(boolean aIsDeleted)
  {
    boolean wasSet = false;
    isDeleted = aIsDeleted;
    wasSet = true;
    return wasSet;
  }

  public boolean getIsDeleted()
  {
    return isDeleted;
  }
  /* Code from template attribute_IsBoolean */
  public boolean isIsDeleted()
  {
    return isDeleted;
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
    if (aAppointnement.indexOfCustomerAccount(this) != -1)
    {
      wasAdded = true;
    }
    else
    {
      wasAdded = aAppointnement.addCustomerAccount(this);
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
    if (aAppointnement.indexOfCustomerAccount(this) == -1)
    {
      wasRemoved = true;
    }
    else
    {
      wasRemoved = aAppointnement.removeCustomerAccount(this);
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
    ArrayList<Appointnement> copyOfAppointnements = new ArrayList<Appointnement>(appointnements);
    appointnements.clear();
    for(Appointnement aAppointnement : copyOfAppointnements)
    {
      aAppointnement.removeCustomerAccount(this);
    }
    super.delete();
  }


  public String toString()
  {
    return super.toString() + "["+
            "isDeleted" + ":" + getIsDeleted()+ "]";
  }
}