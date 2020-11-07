/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.30.1.5099.60569f335 modeling language!*/

package ca.mcgill.ecse.flexibook.model;
import java.io.Serializable;
import java.util.*;
import ca.mcgill.ecse.flexibook.util.SystemTime;
import java.time.LocalTime;
import java.sql.Time;

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
        // line 13 "../../../../../FlexiBookStates.ump"
          doUpdateAppointment(timeslot, newBookableService, optionalService);
          setAppointmentStatus(AppointmentStatus.Booked);
          wasEventProcessed = true;
          break;
        }
        break;
      case InProgress:
        if (isWithinAppTimeSlot())
        {
        // line 32 "../../../../../FlexiBookStates.ump"
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
        // line 17 "../../../../../FlexiBookStates.ump"
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
        if (isWithinAppTimeSlot()||isAfterAppTimeSlot())
        {
        // line 21 "../../../../../FlexiBookStates.ump"
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
        // line 25 "../../../../../FlexiBookStates.ump"
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
        // line 36 "../../../../../FlexiBookStates.ump"
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


  /**
   * Author: Haipeng Yue
   */
  // line 45 "../../../../../FlexiBookStates.ump"
   private void doUpdateAppointment(TimeSlot timeslot, BookableService newBookableService, List<ComboItem> optionalService){
    if(!(this.getTimeSlot().getStartTime().equals(timeslot.getStartTime()))){
    Boolean check = false;
	try {
    	check = availableTimeSlot(timeslot);
    }
    catch(RuntimeException e) {
    	if(e != null) {
    		throw new RuntimeException("unsuccessful");
    	}
    }
	
    if(check == false) {
    	throw new RuntimeException("unsuccessful");
    }
    }
	
	this.setTimeSlot(timeslot);
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
				}
			}
		}
		else if(this.getBookableService() instanceof ServiceCombo) {
			if(this.getAppointmentInProgress() == true) {
			}
			if(optionalService != null) {
			}
			else {
				this.setBookableService(newBookableService);
			}
		}
  }


  /**
   * Author: Haipeng Yue
   */
  // line 100 "../../../../../FlexiBookStates.ump"
   private Boolean availableTimeSlot(TimeSlot newslot){
    Boolean check = false;
	FlexiBook fb = this.getFlexiBook();
	Service service = (Service) this.getBookableService();
	Time startTime = this.getTimeSlot().getStartTime();

	for(TimeSlot slot : fb.getBusiness().getHolidays()) {
		if(!isNoOverlap(newslot,slot)) {
			throw new RuntimeException("unsuccessful");
		}
	}
	for(Appointment appointment : fb.getAppointments()) {
		if(appointment != this) {
			if(appointment.getBookableService() instanceof Service) {
				Service s = (Service) appointment.getBookableService();
				TimeSlot slot = appointment.getTimeSlot();
				if(s.getDowntimeStart() == 0) {
					if(!isNoOverlap(newslot,slot)) {
						throw new RuntimeException("unsuccessful");
					}
				}
				else {
					LocalTime ST = appointment.getTimeSlot().getStartTime().toLocalTime().plusMinutes(service.getDowntimeStart());
					LocalTime endTime = ST.plusMinutes(service.getDowntimeDuration());
					Time start = Time.valueOf(ST);
					Time end = Time.valueOf(endTime);
					TimeSlot TS = new TimeSlot(appointment.getTimeSlot().getStartDate(), start, appointment.getTimeSlot().getStartDate(), end, fb);
					if(!isNoOverlap(newslot, slot)) {
						if(isFullyCovered(newslot, TS)) {
							check = true;
						}
						else {
							throw new RuntimeException("unsuccessful");
						}
					}
				}

			}
		}
	}
	for(Appointment app : fb.getAppointments()) {
		if(app.getBookableService() instanceof ServiceCombo) {
			boolean successful = false;
			List<TimeSlot> dtTS = new ArrayList<TimeSlot>();
			ServiceCombo combo = (ServiceCombo) app.getBookableService();
			int min = 0;
			for (ComboItem item : combo.getServices()) {
				Service s = item.getService();
				min += s.getDuration(); 
				if(s.getDowntimeDuration() != 0) {
					min -= s.getDuration();
					LocalTime ST = app.getTimeSlot().getStartTime().toLocalTime().plusMinutes(s.getDowntimeStart() + min);
					LocalTime endTime = ST.plusMinutes(s.getDowntimeDuration());
					Time start = Time.valueOf(ST);
					Time end = Time.valueOf(endTime);
					TimeSlot TS = new TimeSlot(app.getTimeSlot().getStartDate(), start, app.getTimeSlot().getStartDate(), end, fb);
					dtTS.add(TS);
				}
			}
			for(TimeSlot t : dtTS) {
				if(!isNoOverlap(newslot, t)) {
					if(isFullyCovered(newslot, t)) {
						check = true;
						successful = true;
					}
				}
			}
			
			if(!(isNoOverlap(app.getTimeSlot(), newslot)) && successful == false) {
				throw new RuntimeException("unsuccessful");
			}
		}
	}
	check = true;
	return check;
  }


  /**
   * Author: Jewoo Lee
   */
  // line 179 "../../../../../FlexiBookStates.ump"
   private Boolean OneDayDiff(){
    String sDate = SystemTime.getdate(SystemTime.getSysTime());
	String date = this.getTimeSlot().getStartDate().toString();
	   
	if(date.equals(sDate)) {
		return false;
	}
	else {
		return true;
	}
  }


  /**
   * Author: Jewoo Lee
   */
  // line 193 "../../../../../FlexiBookStates.ump"
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


  /**
   * Author: James Willems
   */
  // line 216 "../../../../../FlexiBookStates.ump"
   private Boolean isAfterAppTimeSlot(){
    String systemTimeRN = SystemTime.gettime(SystemTime.getSysTime());
	String endTime = this.getTimeSlot().getEndTime().toLocalTime().toString();
	int b = SystemTime.comparetime(endTime, systemTimeRN);
	if(b==2){
	return true;
	}
	else{ 
	return false;
	}
  }


  /**
   * Author: Jewoo Lee
   */
  // line 229 "../../../../../FlexiBookStates.ump"
   private void doCancelAppointment(){
    if(OneDayDiff() == true) {
		this.delete();
	}
  }


  /**
   * Author: Jewoo Lee
   */
  // line 236 "../../../../../FlexiBookStates.ump"
   private void doCancelAppointmentO(){
    Customer a = this.getCustomer();
	int noShowCountOld = a.getNoShowCount();
	a.setNoShowCount(noShowCountOld + 1);
	this.setNoShowCheck(true);
	this.delete();
  }


  /**
   * Author: Jewoo Lee
   */
  // line 245 "../../../../../FlexiBookStates.ump"
   private void doStartAppointment(Owner owner){
    if(owner != null) {
		this.setAppointmentInProgress(true);
	}
	else {
		throw new RuntimeException("You are not the owner of the Flexibook");
	}
  }


  /**
   * Author: Haipeng Yue
   */
  // line 255 "../../../../../FlexiBookStates.ump"
   private void doEndAppointment(){
    this.setAppointmentInProgress(false);
  	this.delete();
  }


  /**
   * Author: Haipeng Yue
   */
  // line 262 "../../../../../FlexiBookStates.ump"
   public static  boolean isNoOverlap(TimeSlot t1, TimeSlot t2){
    if(t1.getStartDate().equals(t2.getStartDate())) {
			if(t1.getEndTime().before(t2.getStartTime()) || 
					t2.getEndTime().before(t1.getStartTime())) {
				//is not overlap
				return true;
			}else {
				return false;
			}
		}
		return true;
  }


  /**
   * Author: Jewoo Lee
   */
  // line 276 "../../../../../FlexiBookStates.ump"
   public static  boolean isFullyCovered(TimeSlot t1, TimeSlot t2){
    if(t1.getStartDate().equals(t2.getStartDate())) {
			if(t1.getEndTime().before(t2.getEndTime()) && 
					t1.getStartTime().after(t2.getStartTime())) {
				return true;
			}
			else {
				return false;
			}
		}
		else {
			return false;
		}
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