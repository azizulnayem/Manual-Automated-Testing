package edu.sc.cse4495.MeetingPlanner;

import static org.junit.Assert.*;

import org.junit.Test;

public class RoomTest {
	// Add test methods here. 
    // You are not required to write tests for all classes.
	
	
    @Test
    public void testmeetingnot_present(){
    	int  ind = 0;
    	int day = 22;
        int month = 7;
        
        
        Room room = new Room("103");

        Throwable exception = assertThrows(Exception.class,()->{room.removeMeeting(ind,month, day);});

        assertEquals("Index out of bound",exception.getMessage());
    }
 
    @Test
    public void testgetmeeing_normalcase(){
        int ind = 0;
        int day=2;
        int month=10;
        
        Room room = new Room("A123");
        try{
            room.getMeeting( ind,month, day);
        }
        catch(Exception exception){
            fail(exception.getMessage());
        }
    }


   
    @Test
    public void testAddMeeting_month_12() {

        Meeting meeting_12 = new Meeting(12, 25, "Happy Birthday");
        Room room = new Room("412");

        try {
            room.addMeeting(meeting_12);
        } catch(TimeConflictException exception) {
            fail(exception.getMessage());
        }
    }

    @Test
    public void testAddMeeting_month_10() {

        Meeting meeting_10 = new Meeting(1, 25, "Happy Birthday");
        Room room = new Room("A123");

        try {
            room.addMeeting(meeting_10);
        } catch(TimeConflictException exception) {
            fail(exception.getMessage());
        }
    }

  

    @Test
    public void testbusy_boundarycase(){
                int month = 5;
        		int day = 2;
        		int start = 0;
        		int end = 10;
        Room room = new Room("426");
        try{
            Boolean busy = room.isBusy(month, day, start, end);

            assertFalse("this is slote is free" ,busy);
        }
        catch(Exception exception){
            fail(exception.getMessage());
        }
    }


    @Test
    public void testbusy_normal(){
                int month = 7;
        		int day = 15;
        		int start = 0;
        		int end = 8;
        Room room = new Room("426");
        try{
            Boolean busy = room.isBusy(month, day, start, end);

            assertFalse("this slot is free" ,busy);
        }
        catch(Exception exception){
            fail(exception.getMessage());
        }
    }

  


    @Test
    public void testremove_meeting_not_present(){
        int  ind = 0; 
        int day=20;
        int month=2;
        Room room = new Room("402");

        Throwable exception = assertThrows(IndexOutOfBoundsException.class,()->{room.removeMeeting(month, day, ind);});

        assertEquals("Index out of bound =",exception.getMessage());
    }


    @Test
    public void test_remove_meetingnormal(){
        int ind=0;
        int day=10;
        int month=12;
        Room room = new Room("426");
        try{
            room.removeMeeting(month, day, ind);
        }
        catch(Exception exception){
            fail(exception.getMessage());
        }
    }


}
