package edu.sc.cse4495.MeetingPlanner;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;


import org.junit.Test;
public class PersonTest {
	
    @Test
    public void test_isbusy() {
        Meeting promotion = new Meeting(3, 3);
        Person person = new Person();

       
        try {
            person.addMeeting(promotion);
           
            Boolean added = person.isBusy(3, 3, 8, 23);
            assertEquals( true, added);
        } catch(TimeConflictException e) {
            fail("Should not throw exception: " + e.getMessage());
        }

    }

    @Test
    public void test_isbusy_overlap() {

        Person person = new Person();

        try {
            Boolean added = person.isBusy(3, 3, 9, 20);
            assertEquals( true, added);
        } catch(TimeConflictException e) {
            fail("Should not throw exception: " + e.getMessage());
        }

    }

    @Test
    public void test_isbusy_overlap1() {

        Person person = new Person();

        try {
            Boolean added = person.isBusy(3, 3, 0, 8);
            assertEquals(true, added);
        } catch(TimeConflictException e) {
            fail("Should not throw exception: " + e.getMessage());
        }

    }

    @Test
    public void test_isbusy_sameday_no_overlap() {

        Person person = new Person();

        try {
            Boolean added = person.isBusy(3, 3, 0, 7);
            assertEquals( false, added);
        } catch(TimeConflictException e) {
            fail("Should not throw exception." + e.getMessage());
        }

    }

    @Test
    public void isbusy_dont_exist() {
        Person person = new Person();

        Throwable e = assertThrows(Exception.class,
                ()->{person.isBusy(3,3,0,25);});
        assertEquals("Illegal hour.",e.getMessage());

    }

   
    @Test
    public void testAddMeeting() {
        // Create meeting
        Meeting promotion = new Meeting(6, 11, "All will get a raise");
        Person person = new Person();

        // Add to calendar object.
        try {
            person.addMeeting(promotion);
            // Verify that it was added.
            Boolean added = person.isBusy(6, 11, 0, 23);
            assertTrue("promotion should be marked as busy on the calendar",added);
        } catch(TimeConflictException e) {
            fail("Should not throw exception: " + e.getMessage());
        }

    }

    @Test
    public void testAddMeeting1() {
       
        Meeting promotion = new Meeting(1, 1, "Birthday");
        Person person = new Person();

        try {
            person.addMeeting(promotion);
            
            Boolean added = person.isBusy(1, 1, 0, 2);
            assertTrue("We will celebrate",added);
        } catch(TimeConflictException e) {
            fail("Should not throw exception: " + e.getMessage());
        }

    }

    @Test
    public void testAddMeeting2() {
        // Create meeting
        Meeting promotion = new Meeting(12, 30);
        Person person = new Person();

        // Add to calendar object.
        try {
            person.addMeeting(promotion);
            // Verify that it was added.
            Boolean added = person.isBusy(12, 31, 0, 2);
            assertTrue("just another day",added);
        } catch(TimeConflictException e) {
            fail("Should not throw exception: " + e.getMessage());
        }

    }

    @Test
    public void testAddMeeting3() {
        Person person = new Person();
        Throwable e = assertThrows(TimeConflictException.class,
                ()->{person.isBusy(13,1,1,5);}
        );

        assertEquals("Month does not exist.", e.getMessage());

    }

    @Test
    public void testAddMeeting4() {
        Person person = new Person();
        Throwable e = assertThrows(TimeConflictException.class,
                ()->{person.isBusy(11,32,1,5);}
        );

        assertEquals("Day does not exist.", e.getMessage());

    }

    @Test
    public void testAddMeeting_is_occupied() {

        Meeting promotion1 = new Meeting(6, 11, "All will get a promotion");

        Person person= new Person();

        try {
            person.addMeeting(promotion1);
            Boolean added = person.isBusy(6, 11, 8, 23);
            assertEquals( true, added);

        } catch(TimeConflictException e) {
            fail("Should not throw exception: " + e.getMessage());
        }

    }
  
    @Test
    public void test_getmeeting() {
        Person c=new Person();
        try{
            c.getMeeting(11,31,0);
        }catch(Exception e) {
            assertEquals("Should not throw exception: ",e.getMessage());
        }

    }

    @Test
    public void test_getmeeting_does_not_exists() {
        Person c=new Person();
        try{
            c.removeMeeting(12,31,0);
            c.getMeeting(12,31,0);
        }catch(Exception e) {
            assertEquals("Index 0 out of bounds for length 0",e.getMessage());
        }

    }

    @Test
    public void test_remove_meeting() {
        //adding an object
        Meeting birthday = new Meeting(1, 11, "It's my Birthday");
        Person c=new Person();
        try{
            c.addMeeting(birthday);
            //removing the object
            c.removeMeeting(1,11,0);

        }catch(Exception e) {
            assertEquals("Should not throw exception: ",e.getMessage());
        }

    }

    @Test
    public void test_remove_meeting1() {

        Person c=new Person();
        try{
            c.removeMeeting(11,31,0);

        }catch(Exception e) {
            assertEquals("Should not throw exception: ", e.getMessage());
        }

    }

    @Test
    public void test_remove_meeting_doesnot_exist() {
        Person c=new Person();
        Throwable e = assertThrows(Exception.class,
                ()->{c.removeMeeting(1,11,0);});
        assertEquals("Index 0 out of bounds for length 0",e.getMessage());

    }
    

}
