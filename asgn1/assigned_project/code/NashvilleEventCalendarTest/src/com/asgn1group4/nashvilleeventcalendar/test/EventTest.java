package com.asgn1group4.nashvilleeventcalendar.test;

import static org.junit.Assert.*;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.junit.Test;

import com.asgn1group4.nashvilleeventcalendar.Event;

public class EventTest {

	public class EventStub extends Event {
		
		Map<String, Object> eventData = new HashMap<String, Object>();
		List<String> getInts = new LinkedList<String>();
		int returnInt = 7;
		
	    public EventStub(String id, String title, String address, String description, String dateTime, String category, double latitude, double longitude) {
	    	super(id, title, address, description, dateTime, category, latitude, longitude);
	    }

		@Override
		public void put(String s, Object value) {
			eventData.put(s, value);
		}
		
		
		@Override
		public int getInt(String s) {
			// getInts.add(s);
			return (int) eventData.get(s);
		}
		
		@Override
		public String getString(String s) {
			return (String) eventData.get(s);
		}
	}
	
//	@Before
//	public void setUp() throws Exception {
//		//ParseObject.registerSubclass(EventStub.class);
//		//final Application a = new Application();
//	    //Parse.initialize(a, "FoSWd8SBKQ7kTs4nny52GfDhU5ewXBG6ow4G65tT", "wmX1STIDtyJYcatsNsgGtKZjMJn6a4IgpfS1Grgt");
//	}
	
	@Test
	public void testGetDateFromString() {
		String dateTime = "09/22/2014 12:00 PM";
		SimpleDateFormat df = new SimpleDateFormat("M/d/yyyy h:mm aaa", Locale.US);
		Date date = EventStub.getDateFromString(dateTime);
		String convertedStr = df.format(date);
		
		assertEquals(dateTime, convertedStr);
	}
	
	@Test
	public void testGetCal() {
		EventStub myEvent = new EventStub(
				"id",
				"title",
				"address",
				"description",
				"09/22/2014 12:00 PM",
				"category",
				1.0,
				1.0);
		Calendar cal = myEvent.getCal();
		SimpleDateFormat df = new SimpleDateFormat("M/d/yyyy h:mm aaa", Locale.US);
		String convertedStr = df.format(cal.getTime());

		assertEquals(convertedStr, "09/22/2014 12:00 PM");
	}
	
	@Test
	public void testAnotherUserGoing() {
		EventStub myEvent = new EventStub(
				"id",
				"title",
				"address",
				"description",
				"09/22/2014 12:00 PM",
				"category",
				1.0,
				1.0);
		myEvent.anotherUserGoing();
		assertEquals(myEvent.getInt("numberGoing"), 1);
	}
	
	@Test
	public void testGetFormattedDateTimeString() {
		EventStub myEvent = new EventStub(
				"id",
				"title",
				"address",
				"description",
				"09/22/2014 12:00 PM",
				"category",
				1.0,
				1.0);
		String formatted = myEvent.getFormattedDateTimeString();
		//TODO: change the assert
		System.out.println("TestGetFormattedDateTimeString: "+ formatted);
		assertTrue(true);
	}
	
	@Test
	public void testToString() {
		EventStub myEvent = new EventStub(
				"id",
				"title",
				"address",
				"description",
				"09/22/2014 12:00 PM",
				"category",
				1.0,
				1.0);
		assertEquals(myEvent.toString(), "title");
	}
}
