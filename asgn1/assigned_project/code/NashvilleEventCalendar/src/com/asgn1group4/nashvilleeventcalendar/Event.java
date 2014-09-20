package com.asgn1group4.nashvilleeventcalendar;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import android.util.Log;

public class Event {
    public String id;
    public String title;
    public String address;
    public String description;
    public Calendar dateTime; 
    public String category;

    private SimpleDateFormat sdf = new SimpleDateFormat("M/d/yyyy h:mm aaa", Locale.US);
    
    // TODO edit with correct categories
    public static final String[] CATEGORIES = {"Fun", "Community", "Games", "Technical", "Party", "Random"};
    
    // Date String must be in format "M/d/yyyy h:mm AM or PM"
    // "month/day/4-digit-year hour:minute AM-or-PM"
    public Event(String id, String title, String address, String description, String dateTime, String category) {
        this.id = id;
        this.title = title;
        this.address = address;
        this.description = description;
        getDateFromString(dateTime);
        this.category = category;
    }
    
    private void getDateFromString(String dateTime) {
    	this.dateTime = Calendar.getInstance();
    	
    	try {
			this.dateTime.setTime(sdf.parse(dateTime));
		} catch (ParseException e) {
			Log.d(this.getClass().getSimpleName(), "Failure parsing the date and time.");
			e.printStackTrace();
		} 
    }
    
    public String getFormattedDateTimeString() {
    	return sdf.format(dateTime.getTime());
    }

    @Override
    public String toString() {
        return title;
    }
}
