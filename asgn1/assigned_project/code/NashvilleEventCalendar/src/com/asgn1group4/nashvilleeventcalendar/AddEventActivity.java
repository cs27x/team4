package com.asgn1group4.nashvilleeventcalendar;

import java.util.Calendar;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;

import com.example.nashvilleeventcalendar.R;

public class AddEventActivity extends Activity {
	private EventAdapter adapter = EventAdapter.getInstance(this);
	private long dayWorthOfMilliseconds = 1000 * 60 * 60 * 24;
	
	private EditText eventTitle;
	private EditText eventLocation;
	private EditText eventDescription;
	private DatePicker eventDate;
	private TimePicker eventTime;
	private Spinner eventCategory;
	
	private String newEventTitle;
	private String newEventLocation;
	private String newEventDescription;
	private Calendar newEventDateTime;
	private String newEventCategory;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_event);
		eventTitle = (EditText)this.findViewById(R.id.add_event_title);
		eventLocation = (EditText)this.findViewById(R.id.add_event_location);
		eventDescription = (EditText)this.findViewById(R.id.add_event_description);
		eventDate = (DatePicker)this.findViewById(R.id.add_event_date);
		// Set so you can only choose dates after yesterday
		eventDate.setMinDate(System.currentTimeMillis() - dayWorthOfMilliseconds);
		eventTime = (TimePicker)this.findViewById(R.id.add_event_time);
		eventCategory = (Spinner)this.findViewById(R.id.add_event_category);
		setupSpinnerForCategories();
	}
	
	private void setupSpinnerForCategories() {
		ArrayAdapter<String> categoryAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, adapter.categories);
		categoryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		eventCategory.setAdapter(categoryAdapter);
	}
	
    public void addNewEventDoneButtonClick(View view) {
    	this.newEventTitle = this.eventTitle.getText().toString();
    	this.newEventLocation = this.eventLocation.getText().toString();
    	this.newEventDescription = this.eventDescription.getText().toString();
    	this.newEventDateTime = Calendar.getInstance();
    	getDateTimeFromInput(); 
    	this.newEventCategory = this.eventCategory.getSelectedItem().toString();
    	
    	View focusView = getViewWithErrorIfThereIsOne();
    	// Error with one of the fields
    	if(focusView != null) {
    		focusView.requestFocus();
    	} else {
    		addValidNewEvent();
    		finish();
    	}
    }

	private void addValidNewEvent() {
		// TODO get next valid id from Scheme to use for new event, replace line below.
		String id = Integer.toString(adapter.getCount());
		
		Event newEvent = new Event(id, newEventTitle, newEventLocation, newEventDescription,
				newEventDateTime, newEventCategory);
		adapter.addEvent(newEvent);
		
    	// TODO add necessary code to add the new event to Scheme
	}
    
    private View getViewWithErrorIfThereIsOne() {
		// Check valid entries. Title not empty, location not empty and valid address,
		// description not empty, date not before current date, category must be selected
 
    	// Reset errors
    	this.eventTitle.setError(null);
    	this.eventLocation.setError(null);
    	this.eventDescription.setError(null);
    	View focusView = null;
    	if(TextUtils.isEmpty(this.newEventTitle)) {
    		eventTitle.setError(getString(R.string.error_field_required));
    		focusView = eventTitle;
    	}
    	if(TextUtils.isEmpty(this.newEventLocation)) {
    		eventLocation.setError(getString(R.string.error_field_required));
    		focusView = eventLocation;
    	}
    	if(isInvalidNewAddress(this.newEventLocation)) {
    		eventLocation.setError(getString(R.string.not_a_valid_address));
    		focusView = eventLocation;
    	}
    	if(TextUtils.isEmpty(this.newEventDescription)) {
    		eventDescription.setError(getString(R.string.error_field_required));
    		focusView = eventDescription;
    	}
	    return focusView;
    }
    
    private boolean isInvalidNewAddress(String address) {
    	// TODO add logic to cheeck if an address is valid
    	return false;
    }
    
    private void getDateTimeFromInput() {
    	this.newEventDateTime.set(eventDate.getYear(), eventDate.getMonth(), 
    			eventDate.getDayOfMonth(), eventTime.getCurrentHour(), eventTime.getCurrentMinute());
    }
}
