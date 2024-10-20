package edu.sc.cse4495.MeetingPlanner;

import org.junit.Test;

import java.util.ArrayList;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.core.CombinableMatcher.both;
import static org.junit.Assert.*;
//import static org.mockito.Mockito.*;

public class CalendarTest {
	// Add test methods here. 
    // You are not required to write tests for all classes.
	
	@Test
	public void testAddMeeting_holiday() {
		// Create Midsommar holiday
		Meeting christmas = new Meeting(6, 26, "Midsommar");
		Calendar calendar = new Calendar();
		// Add to calendar object.
		try {
			calendar.addMeeting(christmas);
			// Verify that it was added.
			Boolean added = calendar.isBusy(6, 26, 0, 23);
			assertTrue("Midsommar should be marked as busy on the calendar",added);
		} catch(TimeConflictException e) {
			fail("Should not throw exception: " + e.getMessage());
		}
	}


	@Test
	public void testIsBusy_should_work(){
		try{
			Calendar calendar = new Calendar();
			Meeting meeting = new Meeting(2,26,13,15);
			calendar.addMeeting(meeting);
			boolean isBusy = calendar.isBusy(2,26,13,15);
			boolean notBusy = calendar.isBusy(2,26,10,12);
			assertTrue("meeting time should show busy", isBusy);
			assertFalse("meeting time should not show busy", notBusy);
		}catch (Exception e){
			fail("Should not throw exception: " + e.getMessage());
		}
	}


	@Test
	public void testCheckTimes_invalid_day(){
		Calendar calendar = new Calendar();
		Throwable t = assertThrows(TimeConflictException.class, ()->{
			calendar.isBusy(6,32,12,16);
		});
		assertEquals("Day does not exist.", t.getMessage());
	}

	@Test
	public void testCheckTimes_invalid_month(){
		Calendar calendar = new Calendar();

		Throwable t = assertThrows(TimeConflictException.class,
				()->{ calendar.isBusy(13,15,12,16);});
		assertEquals("Month does not exist.", t.getMessage());
	}

	@Test
	public void testCheckTimes_invalid_start_hour(){
		Calendar calendar = new Calendar();
		Throwable t = assertThrows(TimeConflictException.class,
				()->{ calendar.isBusy(11,15,-2,16);});
		assertEquals("Illegal hour.", t.getMessage());
	}

	@Test
	public void testCheckTimes_invalid_end_hour(){
		Calendar calendar = new Calendar();

		Throwable t = assertThrows(TimeConflictException.class,
				()->{ calendar.isBusy(11,15,5,25);});
		assertEquals("Illegal hour.", t.getMessage());
	}

	@Test
	public void testCheckTimes_invalid_meeting_time(){
		Calendar calendar = new Calendar();

		Throwable t = assertThrows(TimeConflictException.class,
				()->{calendar.isBusy(11,15,16,15);});
		assertEquals("Meeting starts before it ends.", t.getMessage());
	}

	@Test
	public void testAddMeeting_should_work(){
		try{
			Calendar calendar = new Calendar();
			Organization org = new Organization();
			Room room = org.getRoom("2A01");

			Meeting meeting = new Meeting(2,25,12,14,org.getEmployees(),room,"Come to meeting soon");

			calendar.addMeeting(meeting);

			assertTrue("meeting added successfully", calendar.isBusy(2,25,12,14));
		}catch (Exception e){
			fail("Should not throw exception: " + e.getMessage());
		}
	}



	@Test
	public void testAddMeeting_invalid_month(){
		Calendar calendar = new Calendar();
		Throwable t = assertThrows(TimeConflictException.class, ()->{
				Meeting meeting = new Meeting(13,1);
				calendar.addMeeting(meeting);
		});
		assertEquals("Month does not exist.", t.getMessage());
	}



	@Test
	public void testAddMeeting_invalid_start_hour(){
		Calendar calendar = new Calendar();
		Meeting meeting = new Meeting(11,16,-2,16);
		Throwable t = assertThrows(TimeConflictException.class,
				()->{ calendar.addMeeting(meeting);});
		assertEquals("Illegal hour.", t.getMessage());
	}

	@Test
	public void testAddMeeting_invalid_end_hour(){
		Calendar calendar = new Calendar();
		Meeting meeting = new Meeting(11,16,17,24);
		Throwable t = assertThrows(TimeConflictException.class,
				()->{ calendar.addMeeting(meeting);});
		assertEquals("Illegal hour.", t.getMessage());
	}

	@Test
	public void testAddMeeting_invalid_meeting_time(){
		Calendar calendar = new Calendar();
		Meeting meeting = new Meeting(11,16,16,14);

		Throwable t = assertThrows(TimeConflictException.class,
				()->{calendar.addMeeting(meeting);});
		assertEquals("Meeting starts before it ends.", t.getMessage());
	}


	@Test
	public void testAddMeeting_conflict(){
		try {
			Calendar calendar = new Calendar();
			Organization org = new Organization();

			Room room = org.getRoom("2A01");
			Room room2 = org.getRoom("2A02");

			ArrayList<Person> p = new ArrayList<Person>();
			p.add(org.getEmployee("Greg Gay"));
			p.add(org.getEmployee("Manton Matthews"));


			ArrayList<Person> p2 = new ArrayList<Person>();
			p2.add(org.getEmployee("Ryan Austin"));
			p2.add(org.getEmployee("Csilla Farkas"));

			Meeting meeting1 = new Meeting(2,25,12,14,p,room,"Come to meeting soon1");


			Meeting meeting2 = new Meeting(2,25,13,15,p2,room2,"Come to meeting soon2");

			calendar.addMeeting(meeting1);

			Throwable t = assertThrows(TimeConflictException.class,()->{
				calendar.addMeeting(meeting2);
			});
			assertNotNull(t.getMessage());
		}catch (Exception e){
			fail("Should not throw exception: " + e.getMessage());
		}
	}

	@Test
	public void testClearSchedule_should_work(){
		Calendar calendar = new Calendar();
		Organization org = new Organization();

		try{
			Room room = org.getRoom("2A01");
			Room room2 = org.getRoom("2A02");
			Room room3 = org.getRoom("2A03");

			ArrayList<Person> p = new ArrayList<Person>();
			p.add(org.getEmployee("Greg Gay"));
			p.add(org.getEmployee("Manton Matthews"));

			ArrayList<Person> p2 = new ArrayList<Person>();
			p2.add(org.getEmployee("Ryan Austin"));
			p2.add(org.getEmployee("Csilla Farkas"));

			Meeting meeting1 = new Meeting(7,15,1,3,p,room,"meeting1");
			Meeting meeting2 = new Meeting(7,15,4,6,p2,room2,"meeting2");
			Meeting meeting3 = new Meeting(7,15,10,12,p,room3,"meeting3");

			calendar.addMeeting(meeting1);
			calendar.addMeeting(meeting2);
			calendar.addMeeting(meeting3);

			assertTrue(calendar.isBusy(7,15,1,3));
			assertTrue(calendar.isBusy(7,15,4,6));
			assertTrue(calendar.isBusy(7,15,10,12));

			calendar.clearSchedule(7,15);

			assertFalse(calendar.isBusy(7,15,1,3));
			assertFalse(calendar.isBusy(7,15,4,6));
			assertFalse(calendar.isBusy(7,15,10,12));

		}catch (Exception e){
			fail("Should not throw exception: " + e.getMessage());
		}

	}

	@Test
	public void test_printAgenda_month_should_work(){

		try{

			Calendar calendar = new Calendar();
			Organization org = new Organization();

			Room room = org.getRoom("2A01");

			ArrayList<Person> p = new ArrayList<Person>();
			p.add(org.getEmployee("Greg Gay"));
			p.add(org.getEmployee("Manton Matthews"));

			Meeting meeting1 = new Meeting(7,1,1,3,p,room,"meeting1");
			Meeting meeting2 = new Meeting(7,15,1,3,p,room,"meeting2");
			Meeting meeting3 = new Meeting(7,31,1,3,p,room,"meeting3");

			calendar.addMeeting(meeting1);
			calendar.addMeeting(meeting2);
			calendar.addMeeting(meeting3);

			String expected = "Agenda for 7:\n" +
					"7/1, 1 - 3,2A01: meeting1\n" +
					"Attending: Greg Gay,Manton Matthews\n" +
					"7/15, 1 - 3,2A01: meeting2\n" +
					"Attending: Greg Gay,Manton Matthews\n" +
					"7/31, 1 - 3,2A01: meeting3\n" +
					"Attending: Greg Gay,Manton Matthews\n";

			String output = calendar.printAgenda(7);

			assertEquals(expected,output);



		}catch (Exception e){
			fail("Should not throw exception: " + e.getMessage());
		}

	}

	@Test
	public void test_printAgenda_month_and_day_should_work(){

		try{
			Calendar calendar = new Calendar();
			Organization org = new Organization();

			Room room = org.getRoom("2A01");

			ArrayList<Person> p = new ArrayList<Person>();
			p.add(org.getEmployee("Greg Gay"));
			p.add(org.getEmployee("Manton Matthews"));

			Meeting meeting1 = new Meeting(7,1,1,3,p,room,"meeting1");
			Meeting meeting2 = new Meeting(7,15,1,3,p,room,"meeting2");
			Meeting meeting3 = new Meeting(7,31,1,3,p,room,"meeting3");

			calendar.addMeeting(meeting1);
			calendar.addMeeting(meeting2);
			calendar.addMeeting(meeting3);

			String expected1 = "Agenda for 7/1:\n" +
					"7/1, 1 - 3,2A01: meeting1\n" +
					"Attending: Greg Gay,Manton Matthews\n";

			String expected2 = "Agenda for 7/15:\n" +
					"7/15, 1 - 3,2A01: meeting2\n" +
					"Attending: Greg Gay,Manton Matthews\n";

			String expected3 = "Agenda for 7/31:\n" +
					"7/31, 1 - 3,2A01: meeting3\n" +
					"Attending: Greg Gay,Manton Matthews\n";

			String output = calendar.printAgenda(7,1);
			assertEquals(expected1,output);

			String output2 = calendar.printAgenda(7,15);
			assertEquals(expected2,output2);

			String output3 = calendar.printAgenda(7,31);
			assertEquals(expected3,output3);

		}catch (Exception e){
			fail("Should not throw exception: " + e.getMessage());
		}
	}

	@Test
	public void test_getMeeting_should_work(){
		try{
			Calendar calendar = new Calendar();
			Organization org = new Organization();
			Room room = org.getRoom("2A01");

			ArrayList<Person> p = new ArrayList<Person>();
			p.add(org.getEmployee("Greg Gay"));
			p.add(org.getEmployee("Manton Matthews"));

			Meeting meeting1 = new Meeting(7,1,1,3,p,room,"meeting1");
			Meeting meeting2 = new Meeting(7,1,5,8,p,room,"meeting2");
			Meeting meeting3 = new Meeting(7,31,1,3,p,room,"meeting3");
			Meeting meeting4 = new Meeting(7,31,5,8,p,room,"meeting4");


			calendar.addMeeting(meeting1);
			calendar.addMeeting(meeting2);
			calendar.addMeeting(meeting3);
			calendar.addMeeting(meeting4);

			Meeting m1 = calendar.getMeeting(7,1,0);
			Meeting m2 = calendar.getMeeting(7,1,1);
			Meeting m3 = calendar.getMeeting(7,31,0);
			Meeting m4 = calendar.getMeeting(7,31,1);

//			System.err.println(m1.toString());

			assertEquals(meeting1,m1);
			assertEquals(meeting2,m2);
			assertEquals(meeting3,m3);
			assertEquals(meeting4,m4);

		}catch (Exception e){
			fail("Should not throw exception: " + e.getMessage());
		}

	}

	@Test
	public void test_removeMeeting_should_work(){

		try{

			Calendar calendar = new Calendar();
			Organization org = new Organization();
			Room room = org.getRoom("2A01");

			ArrayList<Person> p = new ArrayList<Person>();
			p.add(org.getEmployee("Greg Gay"));
			p.add(org.getEmployee("Manton Matthews"));

			Meeting meeting1 = new Meeting(7,1,1,3,p,room,"meeting1");
			Meeting meeting4 = new Meeting(7,31,5,8,p,room,"meeting4");


			calendar.addMeeting(meeting1);
			calendar.addMeeting(meeting4);

			assertTrue(calendar.isBusy(7,1,1,3));
			calendar.removeMeeting(7,1,0);
			assertFalse(calendar.isBusy(7,1,1,3));


			assertTrue(calendar.isBusy(7,31,5,8));
			calendar.removeMeeting(7,31,0);
			assertFalse(calendar.isBusy(7,31,5,8));



		}catch (Exception e){
			fail("Should not throw exception: " + e.getMessage());
		}

	}







}
