/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.30.1.5099.60569f335 modeling language!*/

package ca.mcgill.ecse.flexibook.model;
import java.io.Serializable;
import java.util.*;
import ca.mcgill.ecse.flexibook.util.SystemTime;
import java.time.LocalTime;

// line 101 "../../../../../FlexiBookPersistence.ump"
// line 2 "../../../../../FlexiBookStates.ump"
// line 88 "../../../../../FlexiBook.ump"
public class Appointment implements Serializable
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Appointment Attributes
  private boolean noShowCheck;
  private boolean appointmentInProgress;

  //Appointment State Machines
  public enum AppointmentStatus { Booked, Final, InProgress }
  private AppointmentStatus appointmentStatus;

  //Appointment Associations
  private Customer customer;
  private BookableService bookableService;
  private List<ComboItem> chosenItems;
  private TimeSlot timeSlot;
  private FlexiBook flexiBook;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Appointment(Customer aCustomer, BookableService aBookableService, TimeSlot aTimeSlot, FlexiBook aFlexiBook)
  {
    noShowCheck = false;
    appointmentInProgress = false;
    boolean didAddCustomer = setCustomer(aCustomer);
    if (!didAddCustomer)
    {
      throw new RuntimeException("Unable to create appointment due to customer. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    boolean didAddBookableService = setBookableService(aBookableService);
    if (!didAddBookableService)
    {
      throw new RuntimeException("Unable to create appointment due to bookableService. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    chosenItems = new ArrayList<ComboItem>();
    if (!setTimeSlot(aTimeSlot))
    {
      throw new RuntimeException("Unable to create Appointment due to aTimeSlot. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    boolean didAddFlexiBook = setFlexiBook(aFlexiBook);
    if (!didAddFlexiBook)
    {
      throw new RuntimeException("Unable to create appointment due to flexiBook. See http://manual.umple.org?RE002ViolationofAssociationMultiplicity.html");
    }
    setAppointmentStatus(AppointmentStatus.Booked);
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setNoShowCheck(boolean aNoShowCheck)
  {
    boolean wasSet = false;
    noShowCheck = aNoShowCheck;
    wasSet = true;
    return wasSet;
  }

  public boolean setAppointmentInProgress(boolean aAppointmentInProgress)
  {
    boolean wasSet = false;
    appointmentInProgress = aAppointmentInProgress;
    wasSet = true;
    return wasSet;
  }

  public boolean getNoShowCheck()
  {
    return noShowCheck;
  }

  public boolean getAppointmentInProgress()
  {
    return appointmentInProgress;
  }
  /* Code from template attribute_IsBoolean */
  public boolean isNoShowCheck()
  {
    return noShowCheck;
  }
  /* Code from template attribute_IsBoolean */
  public boolean isAppointmentInProgress()
  {
    return appointmentInProgress;
  }

  public String getAppointmentStatusFullName()
  {
    String answer = appointmentStatus.toString();
    return answer;
  }

  public AppointmentStatus getAppointmentStatus()
  {
    return appointmentStatus;
  }

  public boolean updateAppointment(TimeSlot timeslot,BookableService newBookableService,List<ComboItem> optionalService)
  {
    boolean wasEventProcessed = false;
    
    AppointmentStatus aAppointmentStatus = appointmentStatus;
    switch (aAppointmentStatus)
    {
      case Booked:
        if (OneDayDiff())
        {
        // line 11 "../../../../../FlexiBookStates.ump"
          doUpdateAppointment(timeslot, newBookableService, optionalService);
          setAppointmentStatus(AppointmentStatus.Booked);
          wasEventProcessed = true;
          break;
        }
        break;
      case InProgress:
        if (isWithinAppTimeSlot())
        {
        // line 30 "../../../../../FlexiBookStates.ump"
          doUpdateAppointment(timeslot, newBookableService, optionalService);
          setAppointmentStatus(AppointmentStatus.InProgress);
          wasEventProcessed = true;
          break;
        }
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  public boolean cancelAppointment()
  {
    boolean wasEventProcessed = false;
    
    AppointmentStatus aAppointmentStatus = appointmentStatus;
    switch (aAppointmentStatus)
    {
      case Booked:
        if (OneDayDiff())
        {
        // line 15 "../../../../../FlexiBookStates.ump"
          doCancelAppointment();
          setAppointmentStatus(AppointmentStatus.Final);
          wasEventProcessed = true;
          break;
        }
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  public boolean ownerCancelAppointment()
  {
    boolean wasEventProcessed = false;
    
    AppointmentStatus aAppointmentStatus = appointmentStatus;
    switch (aAppointmentStatus)
    {
      case Booked:
        if (isWithinAppTimeSlot())
        {
        // line 19 "../../../../../FlexiBookStates.ump"
          doCancelAppointmentO();
          setAppointmentStatus(AppointmentStatus.Final);
          wasEventProcessed = true;
          break;
        }
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  public boolean startAppointment(Owner owner)
  {
    boolean wasEventProcessed = false;
    
    AppointmentStatus aAppointmentStatus = appointmentStatus;
    switch (aAppointmentStatus)
    {
      case Booked:
        if (isWithinAppTimeSlot())
        {
        // line 23 "../../../../../FlexiBookStates.ump"
          doStartAppointment(owner);
          setAppointmentStatus(AppointmentStatus.InProgress);
          wasEventProcessed = true;
          break;
        }
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  public boolean endAppointment()
  {
    boolean wasEventProcessed = false;
    
    AppointmentStatus aAppointmentStatus = appointmentStatus;
    switch (aAppointmentStatus)
    {
      case InProgress:
        // line 34 "../../../../../FlexiBookStates.ump"
        doEndAppointment();
        setAppointmentStatus(AppointmentStatus.Final);
        wasEventProcessed = true;
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  private void setAppointmentStatus(AppointmentStatus aAppointmentStatus)
  {
    appointmentStatus = aAppointmentStatus;

    // entry actions and do activities
    switch(appointmentStatus)
    {
      case Final:
        delete();
        break;
    }
  }
  /* Code from template association_GetOne */
  public Customer getCustomer()
  {
    return customer;
  }
  /* Code from template association_GetOne */
  public BookableService getBookableService()
  {
    return bookableService;
  }
  /* Code from template association_GetMany */
  public ComboItem getChosenItem(int index)
  {
    ComboItem aChosenItem = chosenItems.get(index);
    return aChosenItem;
  }

  public List<ComboItem> getChosenItems()
  {
    List<ComboItem> newChosenItems = Collections.unmodifiableList(chosenItems);
    return newChosenItems;
  }

  public int numberOfChosenItems()
  {
    int number = chosenItems.size();
    return number;
  }

  public boolean hasChosenItems()
  {
    boolean has = chosenItems.size() > 0;
    return has;
  }

  public int indexOfChosenItem(ComboItem aChosenItem)
  {
    int index = chosenItems.indexOf(aChosenItem);
    return index;
  }
  /* Code from template association_GetOne */
  public TimeSlot getTimeSlot()
  {
    return timeSlot;
  }
  /* Code from template association_GetOne */
  public FlexiBook getFlexiBook()
  {
    return flexiBook;
  }
  /* Code from template association_SetOneToMany */
  public boolean setCustomer(Customer aCustomer)
  {
    boolean wasSet = false;
    if (aCustomer == null)
    {
      return wasSet;
    }

    Customer existingCustomer = customer;
    customer = aCustomer;
    if (existingCustomer != null && !existingCustomer.equals(aCustomer))
    {
      existingCustomer.removeAppointment(this);
    }
    customer.addAppointment(this);
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_SetOneToMany */
  public boolean setBookableService(BookableService aBookableService)
  {
    boolean wasSet = false;
    if (aBookableService == null)
    {
      return wasSet;
    }

    BookableService existingBookableService = bookableService;
    bookableService = aBookableService;
    if (existingBookableService != null && !existingBookableService.equals(aBookableService))
    {
      existingBookableService.removeAppointment(this);
    }
    bookableService.addAppointment(this);
    wasSet = true;
    return wasSet;
  }
  /* Code from template association_MinimumNumberOfMethod */
  public static int minimumNumberOfChosenItems()
  {
    return 0;
  }
  /* Code from template association_AddUnidirectionalMany */
  public boolean addChosenItem(ComboItem aChosenItem)
  {
    boolean wasAdded = false;
    if (chosenItems.contains(aChosenItem)) { return false; }
    chosenItems.add(aChosenItem);
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeChosenItem(ComboItem aChosenItem)
  {
    boolean wasRemoved = false;
    if (chosenItems.contains(aChosenItem))
    {
      chosenItems.remove(aChosenItem);
      wasRemoved = true;
    }
    return wasRemoved;
  }
  /* Code from template association_AddIndexControlFunctions */
  public boolean addChosenItemAt(ComboItem aChosenItem, int index)
  {  
    boolean wasAdded = false;
    if(addChosenItem(aChosenItem))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfChosenItems()) { index = numberOfChosenItems() - 1; }
      chosenItems.remove(aChosenItem);
      chosenItems.add(index, aChosenItem);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveChosenItemAt(ComboItem aChosenItem, int index)
  {
    boolean wasAdded = false;
    if(chosenItems.contains(aChosenItem))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfChosenItems()) { index = numberOfChosenItems() - 1; }
      chosenItems.remove(aChosenItem);
      chosenItems.add(index, aChosenItem);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addChosenItemAt(aChosenItem, index);
    }
    return wasAdded;
  }
  /* Code from template association_SetUnidirectionalOne */
  public boolean setTimeSlot(TimeSlot aNewTimeSlot)
  {
    boolean wasSet = false;
    if (aNewTimeSlot != null)
    {
      timeSlot = aNewTimeSlot;
      wasSet = true;
    }
    return wasSet;
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
      existingFlexiBook.removeAppointment(this);
    }
    flexiBook.addAppointment(this);
    wasSet = true;
    return wasSet;
  }

  public void delete()
  {
    Customer placeholderCustomer = customer;
    this.customer = null;
    if(placeholderCustomer != null)
    {
      placeholderCustomer.removeAppointment(this);
    }
    BookableService placeholderBookableService = bookableService;
    this.bookableService = null;
    if(placeholderBookableService != null)
    {
      placeholderBookableService.removeAppointment(this);
    }
    chosenItems.clear();
    timeSlot = null;
    FlexiBook placeholderFlexiBook = flexiBook;
    this.flexiBook = null;
    if(placeholderFlexiBook != null)
    {
      placeholderFlexiBook.removeAppointment(this);
    }
  }

  // line 42 "../../../../../FlexiBookStates.ump"
   private void doUpdateAppointment(TimeSlot timeslot, BookableService newBookableService, List<ComboItem> optionalService){
    if(availableTimeSlot(timeslot) == true) {
  		this.setTimeSlot(timeslot);
  	}
  	else {
  		throw new RuntimeException("Unavailable time slot for this new updated appointment");
  	}
	
	if(this.getBookableService() instanceof ServiceCombo) {
		if(this.getAppointmentInProgress() == false) {
				this.setBookableService(newBookableService);
				List<ComboItem> a = this.getChosenItems();
				for(ComboItem c : a) {
					c.delete();
				}
				for(ComboItem newC : optionalService) {
					this.addChosenItem(newC);
				}
			}
			else {
				List<ComboItem> a = this.getChosenItems();
				for(ComboItem c : a) {
					c.delete();
				}
				for(ComboItem newC : optionalService) {
					this.addChosenItem(newC);
				}
				if(newBookableService != null) {
					throw new RuntimeException("You cannot change the Bookable Service during the service");
				}
			}
		}
		else if(this.getBookableService() instanceof ServiceCombo) {
			if(this.getAppointmentInProgress() == true) {
				throw new RuntimeException("You cannot change this service during the service");
			}
			if(optionalService != null) {
				throw new RuntimeException("You cannot optional service to a single service");
			}
			else {
				this.setBookableService(newBookableService);
			}
		}
  }


  /**
   * Author: Haipeng Yue
   */
  // line 87 "../../../../../FlexiBookStates.ump"
   private Boolean availableTimeSlot(TimeSlot TS){
    return true;
  }

  // line 92 "../../../../../FlexiBookStates.ump"
   private Boolean OneDayDiff(){
    String sDate = SystemTime.getdate(SystemTime.getSysTime());
	String date = this.getTimeSlot().getStartDate().toString();
	   
	if(date.equals(sDate)) {
		return true;
	}
	else {
		return false;
	}
  }

  // line 105 "../../../../../FlexiBookStates.ump"
   private Boolean isWithinAppTimeSlot(){
    String st = this.getTimeSlot().getStartTime().toLocalTime().toString();
	String systemTimeRN = SystemTime.gettime(SystemTime.getSysTime());
	String endTime = this.getTimeSlot().getEndTime().toLocalTime().toString();
	   
	int a = SystemTime.comparetime(st, systemTimeRN);
	int b = SystemTime.comparetime(endTime, systemTimeRN);
	   
	if(a == 0 || b == 0) {
		return true;
	}
	else if(a == 1) {
		return false;
	}
	else if(a == 2 && b == 1) {
		return true;
	}
	else {
		return false;
	}
  }

  // line 127 "../../../../../FlexiBookStates.ump"
   private void doCancelAppointment(){
    this.delete();
  }

  // line 131 "../../../../../FlexiBookStates.ump"
   private void doCancelAppointmentO(){
    Customer a = this.getCustomer();
	int noShowCountOld = a.getNoShowCount();
	a.setNoShowCount(noShowCountOld + 1);
	this.setNoShowCheck(true);
	this.delete();
  }

  // line 139 "../../../../../FlexiBookStates.ump"
   private void doStartAppointment(Owner owner){
    if(this.getFlexiBook().getOwner().equals(owner)) {
		this.setAppointmentInProgress(true);
	}
	else {
		throw new RuntimeException("You are not the owner of the Flexibook");
	}
  }

  // line 148 "../../../../../FlexiBookStates.ump"
   private void doEndAppointment(){
    this.setAppointmentInProgress(false);
  	this.delete();
  }


  public String toString()
  {
    return super.toString() + "["+
            "noShowCheck" + ":" + getNoShowCheck()+ "," +
            "appointmentInProgress" + ":" + getAppointmentInProgress()+ "]" + System.getProperties().getProperty("line.separator") +
            "  " + "customer = "+(getCustomer()!=null?Integer.toHexString(System.identityHashCode(getCustomer())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "bookableService = "+(getBookableService()!=null?Integer.toHexString(System.identityHashCode(getBookableService())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "timeSlot = "+(getTimeSlot()!=null?Integer.toHexString(System.identityHashCode(getTimeSlot())):"null") + System.getProperties().getProperty("line.separator") +
            "  " + "flexiBook = "+(getFlexiBook()!=null?Integer.toHexString(System.identityHashCode(getFlexiBook())):"null");
  }  
  //------------------------
  // DEVELOPER CODE - PROVIDED AS-IS
  //------------------------
  
  // line 104 "../../../../../FlexiBookPersistence.ump"
  private static final long serialVersionUID = 6541L ;

  
}