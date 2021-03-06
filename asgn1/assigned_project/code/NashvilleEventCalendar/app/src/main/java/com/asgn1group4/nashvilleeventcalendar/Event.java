/*
 * Note:	this code can be cleaned up by using getters and setters that directly access the ParseObject
 * 			code throughout project would need to be altered to use getters and setters
 */

package com.asgn1group4.nashvilleeventcalendar;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import com.parse.*;

import android.util.Log;

@ParseClassName("Event")
public class Event extends ParseObject {
	private static String log_class;
		
    public static SimpleDateFormat sdf = new SimpleDateFormat("M/d/yyyy h:mm aaa", Locale.US);
    
    //default constructor required by Parse
    public Event() {
    	//empty
    }
    
    // Date String must be in format "M/d/yyyy h:mm AM or PM"
    // "month/day/4-digit-year hour:minute AM-or-PM"
    public Event(String id, String title, String address, String description, String dateTime, String category, double latitude, double longitude) {
    	log_class = this.getClass().getSimpleName();
    	
        put("evid", id);
        put("title", title);
        put("address", address);
        put("description", description);
        put("dateTime", dateTime);
        put("category", category);
        put("numberGoing", "0");
        put("latitude", latitude);
        put("longitude", longitude);
        
        this.saveInBackground();
    }
    
    public Event(String id, String title, String address, String description, Calendar dateTime, String category, double latitude, double longitude) {
    	this(id, title, address, description, sdf.format(dateTime.getTime()), category, latitude, longitude);
    }
    
    public static Date getDateFromString(String dateTime) {
    	Date date = null;
    	try {
			date = sdf.parse(dateTime);
		} catch (ParseException e) {
			Log.d(log_class, "Failure parsing the date and time.");
			e.printStackTrace();
		} 
    	return date;
    }

	public Calendar getCal() {
		Calendar dt = Calendar.getInstance();
	    dt.setTime(getDateFromString(getString("dateTime")));
	    return dt;
	}
    
    public void anotherUserGoing(){
    	int numberGoing = getInt("numberGoing");
    	put("numberGoing", ++numberGoing);
    	this.saveInBackground();
    }
    
    public String getFormattedDateTimeString() {
    	return sdf.format(getDateFromString(getString("dateTime")));
    }

    @Override
    public String toString() {
        return getString("title");
    }
}
