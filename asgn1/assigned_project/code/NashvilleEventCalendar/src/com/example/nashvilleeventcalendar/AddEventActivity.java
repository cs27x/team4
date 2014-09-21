package com.example.nashvilleeventcalendar;

import java.io.IOException;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import android.app.Activity;
import android.app.ProgressDialog;
import android.location.Address;
import android.location.Geocoder;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import com.asgn1group4.nashvilleeventcalendar.Event;
import com.asgn1group4.nashvilleeventcalendar.EventAdapter;

public class AddEventActivity extends Activity {

	private String TAG = getClass().getSimpleName();

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
	
	private static ProgressDialog progressDialog;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_event);
		eventTitle = (EditText) this.findViewById(R.id.add_event_title);
		eventLocation = (EditText) this.findViewById(R.id.add_event_location);
		eventDescription = (EditText) this
				.findViewById(R.id.add_event_description);
		eventDate = (DatePicker) this.findViewById(R.id.add_event_date);
		// Set so you can only choose dates after yesterday
		eventDate.setMinDate(System.currentTimeMillis()
				- dayWorthOfMilliseconds);
		eventTime = (TimePicker) this.findViewById(R.id.add_event_time);
		eventCategory = (Spinner) this.findViewById(R.id.add_event_category);
		setupSpinnerForCategories();
	}

	private void setupSpinnerForCategories() {
		ArrayAdapter<String> categoryAdapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, adapter.categories);
		categoryAdapter
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
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
		if (focusView != null) {
			focusView.requestFocus();
		} else {
			// verify address via GeoCoding Web Service
			new GeocoderTask().execute(this.newEventLocation);
		}
	}

	private void addValidNewEvent() {
		// TODO get next valid id from Scheme to use for new event, replace line
		// below.
		String id = Integer.toString(adapter.getCount());

		Event newEvent = new Event(id, newEventTitle, newEventLocation,
				newEventDescription, newEventDateTime, newEventCategory);
		adapter.addEvent(newEvent);

		// TODO add necessary code to add the new event to Scheme
	}

	private View getViewWithErrorIfThereIsOne() {
		// Check valid entries. Title not empty, location not empty and valid
		// address,
		// description not empty, date not before current date, category must be
		// selected

		// Reset errors
		this.eventTitle.setError(null);
		this.eventLocation.setError(null);
		this.eventDescription.setError(null);
		View focusView = null;
		if (TextUtils.isEmpty(this.newEventTitle)) {
			eventTitle.setError(getString(R.string.error_field_required));
			focusView = eventTitle;
		}
		if (TextUtils.isEmpty(this.newEventLocation)) {
			eventLocation.setError(getString(R.string.error_field_required));
			focusView = eventLocation;
		}
//		if (isInvalidNewAddress(this.newEventLocation)) {
//			eventLocation.setError(getString(R.string.not_a_valid_address));
//			focusView = eventLocation;
//		}
		if (TextUtils.isEmpty(this.newEventDescription)) {
			eventDescription.setError(getString(R.string.error_field_required));
			focusView = eventDescription;
		}
		return focusView;
	}

	// perform address validation
	private View getViewWithErrorIfAddressIsWrong(List<Address> addresses) {

		// Reset errors
		this.eventLocation.setError(null);
		View focusView = null;

		if (addresses == null || addresses.size() == 0) {
			// if no address is returned, the address is invalid
			eventLocation.setError(getString(R.string.not_a_valid_address));
			focusView = eventLocation;
		} else {
			// get the address
			Address address = (Address) addresses.get(0);
			// get necessary info to further verify the address
			String country = address.getCountryName();
			String state = address.getAdminArea();
			String city = address.getLocality();
			String street = address.getThoroughfare();

			// verify that the address is in Nashville, TN, US
			if (!(country == null && state == null && city == null)) {
				
				if (!(country.equals(getString(R.string.country))
						&& state.equals(getString(R.string.state)) && city
							.equals(getString(R.string.city)))) {

					eventLocation
							.setError(getString(R.string.request_nashville_addr));
					focusView = eventLocation;
				} else {
					// verify that a street name and number is there
					if (street == null) {

						eventLocation
								.setError(getString(R.string.request_street));
						focusView = eventLocation;
					}
				}

				// sometimes GeoCoder still returns an "address" but it is not valid
			} else {
				eventLocation.setError(getString(R.string.not_a_valid_address));
				focusView = eventLocation;
			}
		}

		return focusView;
	}

//	private boolean isInvalidNewAddress(String address) {
//		// TODO add logic to cheeck if an address is valid
//		return false;
//	}

	private void getDateTimeFromInput() {
		this.newEventDateTime.set(eventDate.getYear(), eventDate.getMonth(),
				eventDate.getDayOfMonth(), eventTime.getCurrentHour(),
				eventTime.getCurrentMinute());
	}

	// An AsyncTask class for accessing the GeoCoding Web Service
	private class GeocoderTask extends AsyncTask<String, Void, List<Address>> {

		@Override
		protected void onPreExecute() {

			showProgressDialog(getString(R.string.verifying));
		}
		
		@Override
		protected List<Address> doInBackground(String... locationName) {

			Geocoder geocoder = new Geocoder(getBaseContext());
			// a list to contain a result address
			List<Address> addresses = null;

			try {
				// Getting a maximum of 1 address that matches the user input
				addresses = geocoder.getFromLocationName(locationName[0], 1);
			} catch (IOException e) {
				Log.e(TAG, "message: ", e);
			}
			return addresses;
		}

		@Override
		protected void onPostExecute(List<Address> addresses) {

			progressDialog.dismiss();
			// verify that the address is correct
			View focusView = getViewWithErrorIfAddressIsWrong(addresses);
			// address is not correct
			if (focusView != null) {
				focusView.requestFocus();
				// address is correct, so save the event
			} else {
				addValidNewEvent();
				finish();
			}
		}
	}
	
	private void showProgressDialog(String message) {

		progressDialog = ProgressDialog.show(AddEventActivity.this, getString(R.string.wait),
				message);
	}
}
