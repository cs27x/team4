package com.asgn1group4.nashvilleeventcalendar;

import java.util.Calendar;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.Spinner;

import com.example.nashvilleeventcalendar.R;

public class FilterEventListActivity extends Activity {
	private EventAdapter adapter;
	private Spinner filterOptions;
	private Spinner filterCategoryOption;
	private DatePicker filterEventDate;
	private LinearLayout categoryOptionsView;
	private LinearLayout dateTimeOptionsView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_filter_event_list);
		adapter = EventAdapter.getInstance(this);
		filterOptions = (Spinner)this.findViewById(R.id.filter_option);
		addItemSelectedListenerOnFilterOptions();
		filterCategoryOption = (Spinner)this.findViewById(R.id.filter_event_category);
		filterEventDate = (DatePicker)this.findViewById(R.id.filter_event_date);
		categoryOptionsView = (LinearLayout)this.findViewById(R.id.category_options);
		dateTimeOptionsView = (LinearLayout)this.findViewById(R.id.date_time_options);
		setupSpinnerForCategories();
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
		String filter = filterOptions.getSelectedItem().toString() + "|";
		if (filter.equals("Category|")) {
			filter += filterCategoryOption.getSelectedItem().toString();
		} else if (filter.equals("By Date|")) {
			Calendar filterDateTime = Calendar.getInstance();
			filterDateTime.set(filterEventDate.getYear(), filterEventDate.getMonth(), filterEventDate.getDayOfMonth());
			filter += Event.sdf.format(filterDateTime.getTime());
		} else {
			filter += "NULL";
		}
		adapter.getFilter().filter(filter);
		finish();
	}
}
