package com.asgn1group4.nashvilleeventcalendar;

import java.util.Calendar;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.asgn1group4.nashvilleeventcalendar.R;

public class FilterEventListActivity extends Activity implements LocationListener {
	
	private String TAG = getClass().getSimpleName();
	
	private EventAdapter adapter;
	private Spinner filterOptions;
	private Spinner filterCategoryOption;
	private DatePicker filterEventDate;
	private LinearLayout categoryOptionsView;
	private LinearLayout dateTimeOptionsView;
	
	LocationManager locationManager;
	boolean canGetLocation = false;
	// these should provide more accurate location
	private static final long MIN_DISTANCE = 10; // 10 meters
	private static final long MIN_TIME = 1000 * 60 * 1; // minute
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_filter_event_list);
		adapter = EventAdapter.getInstance(this, FilterEventListActivity.this);
		filterOptions = (Spinner)this.findViewById(R.id.filter_option);
		addItemSelectedListenerOnFilterOptions();
		filterCategoryOption = (Spinner)this.findViewById(R.id.filter_event_category);
		filterEventDate = (DatePicker)this.findViewById(R.id.filter_event_date);
		categoryOptionsView = (LinearLayout)this.findViewById(R.id.category_options);
		dateTimeOptionsView = (LinearLayout)this.findViewById(R.id.date_time_options);
		setupSpinnerForCategories();
		
		locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
 
	}
	
	private void addItemSelectedListenerOnFilterOptions() {
		filterOptions.setOnItemSelectedListener(
				new OnItemSelectedListener() {
					@Override
					public void onItemSelected(AdapterView<?> parent,
							View view, int position, long id) {
						categoryOptionsView.setVisibility(View.GONE);
						dateTimeOptionsView.setVisibility(View.GONE);
						String selectedFilter = filterOptions.getSelectedItem().toString();
						if(selectedFilter.equals("Category")) {
							categoryOptionsView.setVisibility(View.VISIBLE);
						} else if (selectedFilter.equals("By Date")) {
							Log.d("TEST", "By Date");
							dateTimeOptionsView.setVisibility(View.VISIBLE);
						}
					}

					@Override
					public void onNothingSelected(AdapterView<?> parent) {
						Log.d(this.getClass().getSimpleName(), "No item selected for filtering.");
					}
				});
	}
	
	private void setupSpinnerForCategories() {
		ArrayAdapter<String> categoryAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, adapter.categories);
		categoryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		filterCategoryOption.setAdapter(categoryAdapter);
	}
	
	public void filterEventsDoneButtonClick(View view) {
		boolean isDialog = false;
		
		String filter = filterOptions.getSelectedItem().toString() + "|";
		if (filter.equals("Category|")) {
			filter += filterCategoryOption.getSelectedItem().toString();
		} else if (filter.equals("By Date|")) {
			Calendar filterDateTime = Calendar.getInstance();
			filterDateTime.set(filterEventDate.getYear(), filterEventDate.getMonth(), filterEventDate.getDayOfMonth());
			filter += Event.sdf.format(filterDateTime.getTime());
		}else if(filter.equals("Closest to Me|")){			 
			
			Location location = getLocation();
			double lat = 0.0;
			double lon = 0.0;
			
			if (canGetLocation()) {
				if (location != null) {
					Log.d(TAG, "can get location");
					lat = location.getLatitude();
					lon = location.getLongitude();
					Log.d(TAG, "curlat: " + lat);
					Log.d(TAG, "curlon: " + lon);

				} else {
					Log.d(TAG, "location null");
					Toast.makeText(this, getString(R.string.msg),
							Toast.LENGTH_LONG).show();
				}
			} else {
				Log.d(TAG, "can't get location");
				showSettingsAlert();
				isDialog = true;
			}

			filter += lat + "&" + lon;

		} else {
			filter += "NULL";
		}
		adapter.getFilter().filter(filter);
		if(!isDialog)
			finish();
	}
	
	public Location getLocation() {

		boolean isGPSEnabled = false;
		boolean isNetworkEnabled = false;

		Location location = null;
		
		try {
			locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);

			isGPSEnabled = locationManager
					.isProviderEnabled(LocationManager.GPS_PROVIDER);
			isNetworkEnabled = locationManager
					.isProviderEnabled(LocationManager.NETWORK_PROVIDER);

			if (isGPSEnabled || isNetworkEnabled) {

				canGetLocation = true;
				if (isNetworkEnabled) {

					locationManager.requestLocationUpdates(
							LocationManager.NETWORK_PROVIDER,
							MIN_TIME,
							MIN_DISTANCE, this);
					if (locationManager != null) {
						location = locationManager
								.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
					}
				}

				if (isGPSEnabled) {
					if (location == null) {
						locationManager.requestLocationUpdates(
								LocationManager.GPS_PROVIDER,
								MIN_TIME,
								MIN_DISTANCE, this);
						if (locationManager != null) {
							location = locationManager
									.getLastKnownLocation(LocationManager.GPS_PROVIDER);
						}
					}
				}
			}
		} catch (Exception e) {

			Log.e(TAG, "message: ", e);
		}

		return location;
	}
	
	private boolean canGetLocation(){
		return canGetLocation;
	}

	public void showSettingsAlert() {

		AlertDialog.Builder alert = new AlertDialog.Builder(FilterEventListActivity.this);

		alert.setTitle(getString(R.string.gps_not_enabled));
		alert.setMessage(getString(R.string.want_enable_gps));

		alert.setPositiveButton(getString(R.string.settings),
				new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						Intent intent = new Intent(
								Settings.ACTION_LOCATION_SOURCE_SETTINGS);
						startActivity(intent);
					}
				});

		alert.setNegativeButton(getString(R.string.cancel),
				new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						dialog.cancel();
						finish();
					}
				});

		AlertDialog alertDialog = alert.create();

		alertDialog.show();
	}
	
	@Override
	public void onLocationChanged(Location location) {
		if (location != null) {
            Log.v("Location Changed", location.getLatitude() + " and " + location.getLongitude());
            locationManager.removeUpdates(this);
        }
	}

	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {		
	}

	@Override
	public void onProviderEnabled(String provider) {	
	}

	@Override
	public void onProviderDisabled(String provider) {		
	}
}