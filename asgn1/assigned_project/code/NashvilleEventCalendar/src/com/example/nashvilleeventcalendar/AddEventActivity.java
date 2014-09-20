package com.example.nashvilleeventcalendar;

import android.app.Activity;
import android.os.Bundle;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;

public class AddEventActivity extends Activity {
	private EditText eventTitle;
	private EditText eventLocation;
	private EditText eventDescription;
	private DatePicker eventDate;
	private TimePicker eventTime;
	private Spinner eventCategory;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_event);
		eventTitle = (EditText)this.findViewById(R.id.add_event_title);
		eventLocation = (EditText)this.findViewById(R.id.add_event_location);
		eventDescription = (EditText)this.findViewById(R.id.add_event_description);
		eventDate = (DatePicker)this.findViewById(R.id.add_event_date);
		eventTime = (TimePicker)this.findViewById(R.id.add_event_time);
		eventCategory = (Spinner)this.findViewById(R.id.add_event_category);
		//eventCategory.setAdapter(adapter)
	}
}
