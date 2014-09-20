package com.asgn1group4.nashvilleeventcalendar;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import android.util.Log;

public class Event {
    public String id;
    public String title;
    public String address;
    public String description;
    public Calendar dateTime; 
    public String category;
    public int numberGoing;

    private static SimpleDateFormat sdf = new SimpleDateFormat("M/d/yyyy h:mm aaa", Locale.US);
    
    // Date String must be in format "M/d/yyyy h:mm AM or PM"
    // "month/day/4-digit-year hour:minute AM-or-PM"
    public Event(String id, String title, String address, String description, String dateTime, String category) {
        this.id = id;
        this.title = title;
        this.address = address;
        this.description = description;
        this.dateTime = Calendar.getInstance();
        this.dateTime.setTime(getDateFromString(dateTime));
        this.category = category;
        this.numberGoing = 0;
    }
    
    public Event(String id, String title, String address, String description, Calendar dateTime, String category) {
    	this(id, title, address, description, sdf.format(dateTime.getTime()), category);
    }
    
    public Date getDateFromString(String dateTime) {
    	Date date = null;
    	try {
			date = sdf.parse(dateTime);
		} catch (ParseException e) {
			Log.d(this.getClass().getSimpleName(), "Failure parsing the date and time.");
			e.printStackTrace();
		} 
    	return date;
    }
    
    public void anotherUserGoing(){
    	++this.numberGoing;
    }
    
    public String getFormattedDateTimeString() {
    	return sdf.format(dateTime.getTime());
    }

    @Override
    public String toString() {
        return title;
    }
}