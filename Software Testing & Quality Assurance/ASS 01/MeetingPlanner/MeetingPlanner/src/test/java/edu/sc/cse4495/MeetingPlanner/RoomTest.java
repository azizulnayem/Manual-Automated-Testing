package edu.sc.cse4495.MeetingPlanner;

import org.junit.Test;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class RoomTest {
	// Add test methods here. 
    // You are not required to write tests for all classes.

    // Add Meeting
    @Test
    public void testAddMeeting_Dec() {

        Meeting birthday = new Meeting(12, 25, "Happy Birthday");
        Room R1 = new Room("A123");

        try {
            R1.addMeeting(birthday);
        } catch(TimeConflictException e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void testAddMeeting_Jan() {

        Meeting birthday = new Meeting(1, 25, "Happy Birthday");
        Room R1 = new Room("A123");

        try {
            R1.addMeeting(birthday);
        } catch(TimeConflictException e) {
            fail(e.getMessage());
        }
    }

    //Is Busy

    @Test
    public void testisBusy_Normal(){
        int month = 2, day = 29, start = 0, end = 10;
        Room R1 = new Room("A123");
        try{
            Boolean busy = R1.isBusy(month, day, start, end);

            assertTrue("This slot should be busy." ,busy);
        }
        catch(TimeConflictException e){
            fail(e.getMessage());
        }
    }

    @Test
    public void testisBusy_Free(){
        int month = 5, day = 29, start = 0, end = 10;
        Room R1 = new Room("A123");
        try{
            Boolean busy = R1.isBusy(month, day, start, end);

            assertFalse("This slot should not be busy." ,busy);
        }
        catch(Exception e){
            fail(e.getMessage());
        }
    }

    // Get Meeting

    @Test
    public void testgetMeeting_normal(){
        int month = 2, day = 29, index = 0;
        Room R1 = new Room("A123");
        try{
            R1.getMeeting(month, day, index);
        }
        catch(Exception e){
            fail(e.getMessage());
        }
    }

    @Test
    public void testgetMeeting_DoesNotExist(){
        int month = 5, day = 29, index = 0;
        Room R1 = new Room("A123");

        Throwable e = assertThrows(Exception.class,
                ()->{R1.removeMeeting(month, day, index);});

        assertEquals("Index 0 out of bounds for length 0",e.getMessage());
    }


    // Remove Meeting

    @Test
    public void testremoveMeeting_Normal(){
        int month = 2, day = 29, index = 0;
        Room R1 = new Room("A123");
        try{
            R1.removeMeeting(month, day, index);
        }
        catch(Exception e){
            fail(e.getMessage());
        }
    }

    @Test
    public void testremoveMeeting_DoesNotExist(){
        int month = 5, day = 29, index = 0;
        Room R1 = new Room("A123");

        Throwable e = assertThrows(IndexOutOfBoundsException.class,
                ()->{R1.removeMeeting(month, day, index);});

        assertEquals("Index 0 out of bounds for length 0",e.getMessage());
    }


}
