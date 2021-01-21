/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.30.1.5099.60569f335 modeling language!*/



// line 1 "classtest.ump"
public class Test
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Test State Machines
  public enum Status { A, B }
  private Status status;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Test()
  {
    setStatus(Status.A);
  }

  //------------------------
  // INTERFACE
  //------------------------

  public String getStatusFullName()
  {
    String answer = status.toString();
    return answer;
  }

  public Status getStatus()
  {
    return status;
  }

  public boolean x()
  {
    boolean wasEventProcessed = false;
    
    Status aStatus = status;
    switch (aStatus)
    {
      case A:
        // line 5 "classtest.ump"
        b();
        setStatus(Status.A);
        wasEventProcessed = true;
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  public boolean y()
  {
    boolean wasEventProcessed = false;
    
    Status aStatus = status;
    switch (aStatus)
    {
      case A:
        if (c())
        {
        // line 6 "classtest.ump"
          a();
          setStatus(Status.B);
          wasEventProcessed = true;
          break;
        }
        break;
      case B:
        setStatus(Status.A);
        wasEventProcessed = true;
        break;
      default:
        // Other states do respond to this event
    }

    return wasEventProcessed;
  }

  private void setStatus(Status aStatus)
  {
    status = aStatus;
  }

  public void delete()
  {}

  // line 15 "classtest.ump"
   private booean c(){
    return true;
  }

  // line 17 "classtest.ump"
   private void a(){
    
  }

  // line 18 "classtest.ump"
   private void b(){
    
  }

}