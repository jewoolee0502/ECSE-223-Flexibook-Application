/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.30.1.5099.60569f335 modeling language!*/

package ca.mcgill.ecse.flexibooksystem;
import java.sql.Time;
import java.util.*;

// line 42 "../../../../flexibook.ump"
public class Service
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Service Attributes
  private String name;
  private float price;
  private Time downTimeStart;
  private Time downTimeEnd;
  private String duration;
  private String type;

  //Service Associations
  private List<ServiceCombo> combo;
  private List<Appointment> appointments;
  private List<ServiceCombo> serviceCombos;
  private Business business;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Service(String aName, float aPrice, Time aDownTimeStart, Time aDownTimeEnd, String aDuration, String aType, Business aBusiness)
  {
    name = aName;
    price = aPrice;
    downTimeStart = aDownTimeStart;
    downTimeEnd = aDownTimeEnd;
    duration = aDuration;
    type = aType;
    combo = new ArrayList<ServiceCombo>();
    appointments = new ArrayList<Appointment>();
    serviceCombos = new ArrayList<ServiceCombo>();
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

  public boolean setDownTimeStart(Time aDownTimeStart)
  {
    boolean wasSet = false;
    downTimeStart = aDownTimeStart;
    wasSet = true;
    return wasSet;
  }

  public boolean setDownTimeEnd(Time aDownTimeEnd)
  {
    boolean wasSet = false;
    downTimeEnd = aDownTimeEnd;
    wasSet = true;
    return wasSet;
  }

  public boolean setDuration(String aDuration)
  {
    boolean wasSet = false;
    duration = aDuration;
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

  public Time getDownTimeStart()
  {
    return downTimeStart;
  }

  public Time getDownTimeEnd()
  {
    return downTimeEnd;
  }

  public String getDuration()
  {
    return duration;
  }

  public String getType()
  {
    return type;
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
  public Appointment getAppointment(int index)
  {
    Appointment aAppointment = appointments.get(index);
    return aAppointment;
  }

  public List<Appointment> getAppointments()
  {
    List<Appointment> newAppointments = Collections.unmodifiableList(appointments);
    return newAppointments;
  }

  public int numberOfAppointments()
  {
    int number = appointments.size();
    return number;
  }

  public boolean hasAppointments()
  {
    boolean has = appointments.size() > 0;
    return has;
  }

  public int indexOfAppointment(Appointment aAppointment)
  {
    int index = appointments.indexOf(aAppointment);
    return index;
  }
  /* Code from template association_GetMany */
  public ServiceCombo getServiceCombo(int index)
  {
    ServiceCombo aServiceCombo = serviceCombos.get(index);
    return aServiceCombo;
  }

  public List<ServiceCombo> getServiceCombos()
  {
    List<ServiceCombo> newServiceCombos = Collections.unmodifiableList(serviceCombos);
    return newServiceCombos;
  }

  public int numberOfServiceCombos()
  {
    int number = serviceCombos.size();
    return number;
  }

  public boolean hasServiceCombos()
  {
    boolean has = serviceCombos.size() > 0;
    return has;
  }

  public int indexOfServiceCombo(ServiceCombo aServiceCombo)
  {
    int index = serviceCombos.indexOf(aServiceCombo);
    return index;
  }
  /* Code from template association_GetOne */
  public Business getBusiness()
  {
    return business;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfCombo()
  {
    return 0;
  }
  /* Code from template association_AddManyToManyMethod */
  public boolean addCombo(ServiceCombo aCombo)
  {
    boolean wasAdded = false;
    if (combo.contains(aCombo)) { return false; }
    combo.add(aCombo);
    if (aCombo.indexOfService(this) != -1)
    {
      wasAdded = true;
    }
    else
    {
      wasAdded = aCombo.addService(this);
      if (!wasAdded)
      {
        combo.remove(aCombo);
      }
    }
    return wasAdded;
  }
  /* Code from template association_RemoveMany */
  public boolean removeCombo(ServiceCombo aCombo)
  {
    boolean wasRemoved = false;
    if (!combo.contains(aCombo))
    {
      return wasRemoved;
    }

    int oldIndex = combo.indexOf(aCombo);
    combo.remove(oldIndex);
    if (aCombo.indexOfService(this) == -1)
    {
      wasRemoved = true;
    }
    else
    {
      wasRemoved = aCombo.removeService(this);
      if (!wasRemoved)
      {
        combo.add(oldIndex,aCombo);
      }
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
  public static int minimumNumberOfAppointments()
  {
    return 0;
  }
  /* Code from template association_AddManyToManyMethod */
  public boolean addAppointment(Appointment aAppointment)
  {
    boolean wasAdded = false;
    if (appointments.contains(aAppointment)) { return false; }
    appointments.add(aAppointment);
    if (aAppointment.indexOfService(this) != -1)
    {
      wasAdded = true;
    }
    else
    {
      wasAdded = aAppointment.addService(this);
      if (!wasAdded)
      {
        appointments.remove(aAppointment);
      }
    }
    return wasAdded;
  }
  /* Code from template association_RemoveMany */
  public boolean removeAppointment(Appointment aAppointment)
  {
    boolean wasRemoved = false;
    if (!appointments.contains(aAppointment))
    {
      return wasRemoved;
    }

    int oldIndex = appointments.indexOf(aAppointment);
    appointments.remove(oldIndex);
    if (aAppointment.indexOfService(this) == -1)
    {
      wasRemoved = true;
    }
    else
    {
      wasRemoved = aAppointment.removeService(this);
      if (!wasRemoved)
      {
        appointments.add(oldIndex,aAppointment);
      }
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addAppointmentAt(Appointment aAppointment, int index)
  {  
    boolean wasAdded = false;
    if(addAppointment(aAppointment))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfAppointments()) { index = numberOfAppointments() - 1; }
      appointments.remove(aAppointment);
      appointments.add(index, aAppointment);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveAppointmentAt(Appointment aAppointment, int index)
  {
    boolean wasAdded = false;
    if(appointments.contains(aAppointment))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfAppointments()) { index = numberOfAppointments() - 1; }
      appointments.remove(aAppointment);
      appointments.add(index, aAppointment);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addAppointmentAt(aAppointment, index);
    }
    return wasAdded;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfServiceCombos()
  {
    return 0;
  }
  /* Code from template association_AddManyToManyMethod */
  public boolean addServiceCombo(ServiceCombo aServiceCombo)
  {
    boolean wasAdded = false;
    if (serviceCombos.contains(aServiceCombo)) { return false; }
    serviceCombos.add(aServiceCombo);
    if (aServiceCombo.indexOfMain(this) != -1)
    {
      wasAdded = true;
    }
    else
    {
      wasAdded = aServiceCombo.addMain(this);
      if (!wasAdded)
      {
        serviceCombos.remove(aServiceCombo);
      }
    }
    return wasAdded;
  }
  /* Code from template association_RemoveMany */
  public boolean removeServiceCombo(ServiceCombo aServiceCombo)
  {
    boolean wasRemoved = false;
    if (!serviceCombos.contains(aServiceCombo))
    {
      return wasRemoved;
    }

    int oldIndex = serviceCombos.indexOf(aServiceCombo);
    serviceCombos.remove(oldIndex);
    if (aServiceCombo.indexOfMain(this) == -1)
    {
      wasRemoved = true;
    }
    else
    {
      wasRemoved = aServiceCombo.removeMain(this);
      if (!wasRemoved)
      {
        serviceCombos.add(oldIndex,aServiceCombo);
      }
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addServiceComboAt(ServiceCombo aServiceCombo, int index)
  {  
    boolean wasAdded = false;
    if(addServiceCombo(aServiceCombo))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfServiceCombos()) { index = numberOfServiceCombos() - 1; }
      serviceCombos.remove(aServiceCombo);
      serviceCombos.add(index, aServiceCombo);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveServiceComboAt(ServiceCombo aServiceCombo, int index)
  {
    boolean wasAdded = false;
    if(serviceCombos.contains(aServiceCombo))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfServiceCombos()) { index = numberOfServiceCombos() - 1; }
      serviceCombos.remove(aServiceCombo);
      serviceCombos.add(index, aServiceCombo);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addServiceComboAt(aServiceCombo, index);
    }
    return wasAdded;
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
    ArrayList<ServiceCombo> copyOfCombo = new ArrayList<ServiceCombo>(combo);
    combo.clear();
    for(ServiceCombo aCombo : copyOfCombo)
    {
      if (aCombo.numberOfServices() <= ServiceCombo.minimumNumberOfServices())
      {
        aCombo.delete();
      }
      else
      {
        aCombo.removeService(this);
      }
    }
    ArrayList<Appointment> copyOfAppointments = new ArrayList<Appointment>(appointments);
    appointments.clear();
    for(Appointment aAppointment : copyOfAppointments)
    {
      aAppointment.removeService(this);
    }
    ArrayList<ServiceCombo> copyOfServiceCombos = new ArrayList<ServiceCombo>(serviceCombos);
    serviceCombos.clear();
    for(ServiceCombo aServiceCombo : copyOfServiceCombos)
    {
      if (aServiceCombo.numberOfMain() <= ServiceCombo.minimumNumberOfMain())
      {
        aServiceCombo.delete();
      }
      else
      {
        aServiceCombo.removeMain(this);
      }
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
            "duration" + ":" + getDuration()+ "," +
            "type" + ":" + getType()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "downTimeStart" + "=" + (getDownTimeStart() != null ? !getDownTimeStart().equals(this)  ? getDownTimeStart().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "downTimeEnd" + "=" + (getDownTimeEnd() != null ? !getDownTimeEnd().equals(this)  ? getDownTimeEnd().toString().replaceAll("  ","    ") : "this" : "null") + System.getProperties().getProperty("line.separator") +
            "  " + "business = "+(getBusiness()!=null?Integer.toHexString(System.identityHashCode(getBusiness())):"null");
  }
}