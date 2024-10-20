package edu.sc.cse4495.MeetingPlanner;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;


import org.junit.Test;

public class CalendarTest {
//	// Add test methods here. 
//	// You are not required to write tests for all classes.
	

	@Test
	public void test_meeting_normalcase(){
		int month = 5;
		int day = 24;
		int index = 0;
		Calendar calendar = new Calendar();
		try{
			calendar.getMeeting(month, day, index);
		}
		catch(Exception exception){
			fail("the exception message is-> " + exception.getMessage());
		}
	}

	@Test
	public void testgetMeeting_DoesNotExist(){
		int month = 12;
		int day = 22;
		int index = 0;
		Calendar calendar = new Calendar();

		Throwable exception = assertThrows(Exception.class,()->{calendar.removeMeeting(month, day, index);});

		assertEquals("Index out of bound ->",exception.getMessage());
	}
	@Test
	public void testAddMeeting_holiday1() {
		
		Meeting chirsmas = new Meeting(5, 20, "Finallsummar");
	Calendar calendar = new Calendar();
		
		try {
			calendar.addMeeting(chirsmas);
	
			Boolean result = calendar.isBusy(5, 06, 0, 25);
			assertTrue("Midsommar should be marked as busy on the calendar", result);
		} catch (TimeConflictException exception) {
			fail("The excepition message is -> " + exception.getMessage());
		}
	}

	@Test
	public void testisBusy_Normal(){
		int month = 5;
		int day = 24;
		int start = 0; 
		int end = 10;
		Calendar calendar = new Calendar();
		try{
			Boolean busy = calendar.isBusy(month, day, start, end);

			assertTrue("This slot is not free." ,busy);
		}
		catch(TimeConflictException exception){
			fail("The exception message is-> " + exception.getMessage());
		}
	}

	@Test
	public void testisBusy_Free(){
		int month = 5;
		int day = 24;
		int start = 0;
		int end=12;
		
		Calendar calendar = new Calendar();
		try{
			Boolean busy = calendar.isBusy(month, day, start, end);

			assertFalse("The slote is free now." ,busy);
		}
		catch(Exception exception){
			fail("the exception message is ->" + exception.getMessage());
		}
	}


	@Test
	public void testCheckTime_Normal1() {

		int month = 5;
		int day = 25;
		int start = 0;
		int end = 10;
		try {
			Calendar.checkTimes(month, day, start, end);
		} catch (TimeConflictException e) {
			fail("Should not throw this exception: 1-" + e.getMessage());
		}
	}

	@Test
	public void testCheckTime_EndBeforStart() {
		int month = 1;
		int day = 1;
		int start = 12;
		int end = 15;
		try {
			Calendar.checkTimes(month, day, start, end);
		} catch (TimeConflictException exception) {
			fail("The exception message is ->" + exception.getMessage());
		}
	}

	@Test
	public void testCheckTime_Normal3() {

		int month = 3;
		int day = 23;
		int start = 0;
		int end = 20;
		try {
			Calendar.checkTimes(month, day, start, end);
		} catch (TimeConflictException e) {
			fail("The exception is ->" + e.getMessage());
		}
	}

	@Test
	public void test_day_zero(){

		int month = 5, day = 0, start = 10, end =12;

		Throwable e = assertThrows(TimeConflictException.class,
				()->{Calendar.checkTimes(month,day,start,end);});

		assertEquals("Day does not exist.", e.getMessage());
	}

	@Test
	public void test_day_thertyTwo(){

		int month = 4, day = 32, start = 10, end =12;

		Throwable e = assertThrows(TimeConflictException.class,
				()->{Calendar.checkTimes(month,day,start,end);});

		assertEquals("Day does not exist.", e.getMessage());
	}

	@Test
	public void test_month_zero(){

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

	@Test
	public void testAddMeeting_Dec() {

		Meeting birthday = new Meeting(12, 25, "Happy Birthday");
		Calendar calendar = new Calendar();
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
		try {
			calendar.addMeeting(birthday);
		} catch(TimeConflictException e) {
			fail("Should not throw this exception: " + e.getMessage());
		}
	}

	@Test
	public void testAddMeeting_holiday() {
		Meeting planBudget = new Meeting(3, 26, "Budget");
		Calendar calendar = new Calendar();

		Throwable e = assertThrows(TimeConflictException.class,
				()->{calendar.addMeeting(planBudget);});

		assertEquals("", e.getMessage());
	}
	@Test
	public void testmeeting_remove(){
			 int month = 5;
			 int day = 30;
			
			 int ind = 0;
			
		Calendar calendar = new Calendar();
		try{
			calendar.removeMeeting(month, day, ind);
		}
		catch(Exception exception){
			fail(exception.getMessage());
		}
	}

	@Test
	public void testremove_meeting_not_present(){
		int month = 8;
		int day = 25;
		int ind = 0;
		Calendar calendar = new Calendar();

		Throwable exception = assertThrows(IndexOutOfBoundsException.class,()->{calendar.removeMeeting(month, day, ind);});

		assertEquals("Index out of boudnd exception ->",exception.getMessage());
	}


	@Test
	public void textscheduleDelete(){
		int day = 23;
		int month = 6;
		Calendar calendar = new Calendar();
		try{
			calendar.clearSchedule(month, day);
			assertFalse("The calendar day is free->", calendar.isBusy(month,day,0,22));
		}
		catch(Exception exception){
			fail("The exception message is -> " + exception.getMessage());
		}
	}
	

}




