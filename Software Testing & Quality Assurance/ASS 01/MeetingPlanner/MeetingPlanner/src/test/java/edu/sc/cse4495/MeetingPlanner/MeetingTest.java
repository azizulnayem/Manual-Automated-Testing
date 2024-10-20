package edu.sc.cse4495.MeetingPlanner;

import org.junit.Test;

import static org.junit.Assert.fail;

public class MeetingTest {
	// Add test methods here. 
    // You are not required to write tests for all classes.

    // add Attendee
    @Test
    public void testaddAttendee(){
        Person p1 = new Person("Siam");
        Meeting m1 = new Meeting(2,12,"party");

        try{
            m1.addAttendee(p1);
        }
        catch(Exception e){
            fail("Should not get message:" + e.getMessage());
        }
    }
}
