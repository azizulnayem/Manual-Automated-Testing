package edu.sc.cse4495.MeetingPlanner;

import org.junit.Test;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CalendarTest {
	// Add test methods here. 
	// You are not required to write tests for all classes.

//	@Test
//	public void testAddMeeting_holiday() {
//		// Create Midsommar holiday
//		Meeting christmas = new Meeting(6, 26, "Midsommar");
//		Calendar calendar = new Calendar();
//		// Add to calendar object.
//		try {
//			calendar.addMeeting(christmas);
//			// Verify that it was added.
//			Boolean added = calendar.isBusy(6, 26, 0, 23);
//			assertTrue("Midsommar should be marked as busy on the calendar", added);
//		} catch (TimeConflictException e) {
//			fail("Should not throw exception: " + e.getMessage());
//		}
//	}



	//Is Busy

	@Test
	public void testisBusy_Normal(){
		int month = 2, day = 29, start = 0, end = 10;
		Calendar calendar = new Calendar();
		try{
			Boolean busy = calendar.isBusy(month, day, start, end);

			assertTrue("This slot should be busy." ,busy);
		}
		catch(TimeConflictException e){
			fail("Should not throw this exception: " + e.getMessage());
		}
	}

	@Test
	public void testisBusy_Free(){
		int month = 5, day = 29, start = 0, end = 10;
		Calendar calendar = new Calendar();
		try{
			Boolean busy = calendar.isBusy(month, day, start, end);

			assertFalse("This slot should not be busy." ,busy);
		}
		catch(Exception e){
			fail("Should not throw this exception: " + e.getMessage());
		}
	}


	// Check Times

	@Test
	public void testCheckTime_Normal1() {

		int month = 1, day = 30, start = 0, end = 12;
		try {
			Calendar.checkTimes(month, day, start, end);
		} catch (TimeConflictException e) {
			fail("Should not throw this exception: 1-" + e.getMessage());
		}
	}

	@Test
	public void testCheckTime_EndBeforStart() {

		int month = 1, day = 1, start = 12, end = 5;

		Throwable e = assertThrows(TimeConflictException.class,
				()->{Calendar.checkTimes(month,day,start,end);});

		assertEquals("Meeting starts before it ends.", e.getMessage());
	}

	@Test
	public void testCheckTime_Normal3() {

		int month = 1, day = 30, start = 0, end = 23;
		try {
			Calendar.checkTimes(month, day, start, end);
		} catch (TimeConflictException e) {
			fail("Should not throw this exception: 3-" + e.getMessage());
		}
	}

	@Test
	public void testCheckTime_Day0(){

		int month = 5, day = 0, start = 10, end =12;

		Throwable e = assertThrows(TimeConflictException.class,
				()->{Calendar.checkTimes(month,day,start,end);});

		assertEquals("Day does not exist.", e.getMessage());
	}

	@Test
	public void testCheckTime_Day32(){

		int month = 4, day = 32, start = 10, end =12;

		Throwable e = assertThrows(TimeConflictException.class,
				()->{Calendar.checkTimes(month,day,start,end);});

		assertEquals("Day does not exist.", e.getMessage());
	}

	@Test
	public void testCheckTime_Month0(){

		int month = 0, day = 30, start = 10, end =12;

		Throwable e = assertThrows(TimeConflictException.class,
				()->{Calendar.checkTimes(month,day,start,end);});

		assertEquals("Month does not exist.", e.getMessage());
	}

	@Test
	public void testCheckTime_Month13(){

		int month = 13, day = 30, start = 10, end =12;

		Throwable e = assertThrows(TimeConflictException.class,
				()->{Calendar.checkTimes(month,day,start,end);});

		assertEquals("Month does not exist.", e.getMessage());
	}

	@Test
	public void testCheckTime_StartTimeNegetive(){

		int month = 4, day = 30, start = -10, end =12;

		Throwable e = assertThrows(TimeConflictException.class,
				()->{Calendar.checkTimes(month,day,start,end);});

		assertEquals("Illegal hour.", e.getMessage());
	}

	@Test
	public void testCheckTime_StartTime24(){

		int month = 4, day = 30, start = 24, end =12;

		Throwable e = assertThrows(TimeConflictException.class,
				()->{Calendar.checkTimes(month,day,start,end);});

		assertEquals("Illegal hour.", e.getMessage());
	}

	@Test
	public void testCheckTime_EndTimeNegetive(){

		int month = 2, day = 30, start = 10, end =-12;
		Throwable e = assertThrows(TimeConflictException.class,
				()->{Calendar.checkTimes(month,day,start,end);});
		Object expected;
		assertEquals("Illegal hour.", e.getMessage());
	}

	@Test
	public void testCheckTime_End24(){
		Calendar calendar = new Calendar();

		int month = 5, day = 30, start = 10, end =24;
		Throwable e = assertThrows(TimeConflictException.class,
				()->{Calendar.checkTimes(month,day,start,end);});
		assertEquals("Illegal hour.", e.getMessage());
	}


	// Add Meeting
	@Test
	public void testAddMeeting_Dec() {

		Meeting birthday = new Meeting(12, 25, "Happy Birthday");
		Calendar calendar = new Calendar();
		// Add to calendar object.
		try {
			calendar.addMeeting(birthday);
		} catch(TimeConflictException e) {
			fail("Should not throw this exception: " + e.getMessage());
		}
	}

	@Test
	public void testAddMeeting_Jan() {

		Meeting birthday = new Meeting(1, 25, "Happy Birthday");
		Calendar calendar = new Calendar();
		// Add to calendar object.
		try {
			calendar.addMeeting(birthday);
		} catch(TimeConflictException e) {
			fail("Should not throw this exception: " + e.getMessage());
		}
	}

	@Test
	public void testAddMeeting_holiday() {
		// Create Midsommar holiday
		Meeting planBudget = new Meeting(3, 26, "Budget");
		Calendar calendar = new Calendar();

		Throwable e = assertThrows(TimeConflictException.class,
				()->{calendar.addMeeting(planBudget);});

		assertEquals("", e.getMessage());
	}

	// Clear Schedule
	@Test
	public void testclearSchedule(){
		int day = 29, month = 2;
		Calendar calendar = new Calendar();
		try{
			calendar.clearSchedule(month, day);
			assertFalse("This day should be empty", calendar.isBusy(month,day,0,22));
		}
		catch(Exception e){
			fail("Should not throw this exception: " + e.getMessage());
		}
	}
	// Print Agenda

	// Get Meeting

	@Test
	public void testgetMeeting_normal(){
		int month = 2, day = 29, index = 0;
		Calendar calendar = new Calendar();
		try{
			calendar.getMeeting(month, day, index);
		}
		catch(Exception e){
			fail("Should not throw exception: " + e.getMessage());
		}
	}

	@Test
	public void testgetMeeting_DoesNotExist(){
		int month = 5, day = 29, index = 0;
		Calendar calendar = new Calendar();

		Throwable e = assertThrows(Exception.class,
				()->{calendar.removeMeeting(month, day, index);});

		assertEquals("Index 0 out of bounds for length 0",e.getMessage());
	}


	// Remove Meeting

	@Test
	public void testremoveMeeting_Normal(){
		int month = 2, day = 29, index = 0;
		Calendar calendar = new Calendar();
		try{
			calendar.removeMeeting(month, day, index);
		}
		catch(Exception e){
			fail(e.getMessage());
		}
	}

	@Test
	public void testremoveMeeting_DoesNotExist(){
		int month = 5, day = 29, index = 0;
		Calendar calendar = new Calendar();

		Throwable e = assertThrows(IndexOutOfBoundsException.class,
				()->{calendar.removeMeeting(month, day, index);});

		assertEquals("Index 0 out of bounds for length 0",e.getMessage());
	}

}


