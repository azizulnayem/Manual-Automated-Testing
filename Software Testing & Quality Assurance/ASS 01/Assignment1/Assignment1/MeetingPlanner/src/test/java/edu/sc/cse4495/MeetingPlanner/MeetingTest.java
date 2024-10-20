package edu.sc.cse4495.MeetingPlanner;

import org.junit.Test;

import static org.junit.Assert.fail;

public class MeetingTest {
	// Add test methods here. 
    // You are not required to write tests for all classes.

    @Test
    public void testattend_normal(){
        Person person = new Person("Azizul");
        Meeting meeting = new Meeting(22,12,"summer");

        try{
            meeting.addAttendee(person);
        }
        catch(Exception exception){
            fail("The exception message is ->" + exception.getMessage());
        }
    }

    @Test
    public void testattend_boundary_one(){
        Person person = new Person("Abdul");
        Meeting meeting = new Meeting(8,10,"spring");

        try{
            meeting.addAttendee(person);
        }
        catch(Exception exception){
            fail("The exception message is ->" + exception.getMessage());
        }
    }

    @Test
    public void testattende_boundary_two(){
        Person person = new Person("Wahab");
        Meeting meeting = new Meeting(0,12,"summer");

        try{
            meeting.addAttendee(person);
        }
        catch(Exception exception){
          fail("The exception message is ->" + exception.getMessage());
      }
  }
}
