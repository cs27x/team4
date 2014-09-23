package com.asgn1group4.nashvilleeventcalendar;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import android.app.Activity;
import android.content.Context;
import android.location.Location;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import com.example.nashvilleeventcalendar.R;

public class EventAdapter extends BaseAdapter implements Filterable {

	private String TAG = getClass().getSimpleName();

	private static EventAdapter instance = null;
	private LayoutInflater mInflate;
	private Context mContext;
	private Activity parentActivity;
	private ArrayList<Event> mData;
	private EventFilter eventFilter;
	ArrayList<Event> filteredEvents;

	private double curlatitude;
	private double curlongitude;
	
	private Location curLocation;

	public ArrayList<String> categories = new ArrayList<String>();

	public static EventAdapter getInstance(Context context,
			Activity parentActivity) {
		if (instance == null)
			instance = new EventAdapter(context, parentActivity);
		return instance;
	}

	protected EventAdapter(Context context, Activity parentActivity) {
		this.mContext = context;
		this.parentActivity = parentActivity;
		mInflate = (LayoutInflater) this.mContext
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		this.mData = new ArrayList<Event>();
		getFilter();
		initializePossibleCategories();
		loadDataFromDatabase(true);
	}
	
	public void setLocation(Location l){
		Log.d(TAG, "Set Location Called");
		Log.d(TAG, curLocation.getLatitude()+"");
		curLocation = l;
	}

	public void initializePossibleCategories() {
		// Possible categories for events
		categories.add("Other");
		categories.add("Arts");
		categories.add("Food and Drink");
		categories.add("Music");
		categories.add("Classes");
		categories.add("Community");
		categories.add("Parties");
		categories.add("Technical");
		categories.add("Networking");
	}

	// TODO needs implemented to get the data from the database when the app
	// opens.
	public void loadDataFromDatabase(boolean update_view) {
		mData.clear();
		// Need database to handle the event id
		// Start events at id 0

		// id, title, address, description, dateTime, category
		// mData.add(new Event("0", "Event0", "123 Road Rd Nashville, TN 37235",
		// "Stuff stuff stuff Stuff stuff stuff Stuff stuff stuff Stuff " +
		// "stuff stuff Stuff stuff stuff Stuff stuff stuff Stuff stuff stuff Stuff stuff stuff Stuff stuff stuff Stuff stuff stuff",
		// "12/25/2014 9:00 AM", "Other", 36.1448748, -86.7932314));
		// mData.add(new Event("1", "Event1", "123 Road Rd Nashville, TN 37235",
		// "Such event! Much fun! Wow!", "9/21/2014 5:00 PM", "Arts"));
		// mData.add(new Event("2", "Event2", "111 Road Rd Nashville, TN 37235",
		// "Get together and such.", "10/2/2014 11:00 PM", "Networking"));
		// mData.add(new Event("3", "Event3", "555 Road Rd Nashville, TN 37235",
		// "Birthday party!", "9/21/2014 9:00 AM", "Community"));
		// mData.add(new Event("4", "Event4", "554 Road Rd Nashville, TN 37235",
		// "Board game night.", "10/20/2014 10:00 PM", "Classes"));
		// mData.add(new Event("5", "Event5", "553 Road Rd Nashville, TN 37235",
		// "LAN party.", "11/1/2014 9:00 AM", "Other"));
		// mData.add(new Event("6", "Event6", "552 Road Rd Nashville, TN 37235",
		// "Coding challenge.", "10/30/2014 8:00 PM", "Technical"));
		// mData.add(new Event("7", "Event7", "551 Road Rd Nashville, TN 37235",
		// "Interviews for positions.", "10/30/2014 11:00 AM", "Networking"));
		// mData.add(new Event("8", "Event8", "55 Road Rd Nashville, TN 37235",
		// "Hangout together.", "1/1/2015 9:00 PM", "Food and Drink"));
		// mData.add(new Event("9", "Event9", "111 Road Rd Nashville, TN 37235",
		// "Send-off party.", "1/2/2015 8:30 PM", "Parties"));
		// mData.add(new Event("10", "Event10",
		// "678 Road Rd Nashville, TN 37235", "Graduation party",
		// "10/22/2014 10:00 AM", "Parties"));
		// mData.add(new Event("11", "Event11",
		// "999 Road Rd Nashville, TN 37235", "Trash pickup party",
		// "12/19/2014 11:30 AM", "Community"));

		mData.add(new Event("1", "Vanderbilt Event",
				"2201 West End Ave, Nashville, TN 37235", "Project meeting",
				"10/30/2014 8:00 PM", "Classes", 36.1486927, -86.8037597));
		mData.add(new Event("2", "Work Event",
				"1025 16th Ave South, Nashville, TN 37235",
				"Research Seminar.", "10/30/2014 8:00 PM", "Technical",
				36.1448748, -86.7932214));
		mData.add(new Event("3", "Birthday at Carrabba's",
				"2101 Green Hills Village Dr Nashville, TN 37215",
				"Birthday Party", "10/30/2014 8:00 PM", "Parties", 36.1095147,
				-86.8153889));
		mData.add(new Event("4", "Frist Museum Event",
				"919 Broadway Nashville, TN 37203", "Visiting Exhibition",
				"10/30/2014 8:00 PM", "Arts", 36.157536, -86.783587));
		mData.add(new Event("5", "Bridgestone Arena Event",
				"501 Broadway Nashville, TN 37203", "Sports.",
				"10/30/2014 8:00 PM", "Other", 36.15913, -86.778492));
		mData.add(new Event("6", "Zoo Event",
				"3777 Nolensville Pike Nashville, TN 37211", "Visiting Zoo.",
				"10/30/2014 8:00 PM", "Community", 36.087606, -86.7394769));
		mData.add(new Event("7", "Friends Meeting, Panera",
				"407 Jericho Turnpike Nashville, TN 37240",
				"Hangout together.", "10/30/2014 8:00 PM", "Food and Drink",
				36.1457192, -86.8066559));
		mData.add(new Event("8", "Interview Event",
				"4220 Harding Pike Nashville, TN 37205",
				"Interviews for positions.", "10/30/2014 8:00 PM",
				"Networking", 36.1292271, -86.844511));

		if (update_view)
			this.notifyDataSetChanged();
	}

	public void updateData() {
		// TODO update the data from the database, remove following line
		// loadDataFromDatabase();

		this.notifyDataSetChanged();
	}

	@Override
	public int getCount() {
		return mData.size();
	}

	@Override
	public Event getItem(int position) {
		return mData.get(position);
	}

	@Override
	public long getItemId(int position) {
		return Long.parseLong(mData.get(position).id);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if (convertView == null) {
			convertView = this.mInflate.inflate(R.layout.list_event_layout,
					parent, false);
		}
		Event curEvent = getItem(position);
		((TextView) convertView.findViewById(R.id.event_title))
				.setText(curEvent.title);
		((TextView) convertView.findViewById(R.id.event_description))
				.setText(curEvent.description);
		((TextView) convertView.findViewById(R.id.event_category))
				.setText("Category: " + curEvent.category);
		((TextView) convertView.findViewById(R.id.event_num_people))
				.setText("People going: "
						+ Integer.toString(curEvent.numberGoing));
		((TextView) convertView.findViewById(R.id.event_date_time))
				.setText(curEvent.getFormattedDateTimeString());
		return convertView;
	}

	public void addEvent(Event newEvent) {
		this.mData.add(newEvent);
		this.notifyDataSetChanged();
	}

	@Override
	public Filter getFilter() {
		if (eventFilter == null)
			eventFilter = new EventFilter();
		return eventFilter;
	}

	private class EventFilter extends Filter {

		@Override
		protected FilterResults performFiltering(CharSequence constraint) {
			Log.d(this.getClass().getSimpleName(), "Filtering events.");
			FilterResults result = new FilterResults();
			if (constraint.length() == 0) {
				result.values = mData;
				result.count = mData.size();
			} else {
				filteredEvents = new ArrayList<Event>(mData);
				String constr_str = constraint.toString();
				String[] filterTypeAndValue = constr_str.split("\\|");
				String filterType = filterTypeAndValue[0];
				String filterValue = filterTypeAndValue[1];

				if (filterType.equals("Category")) {

					filteredEvents.clear();
					for (Event e : mData) {
						if (e.category.equals(filterValue))
							filteredEvents.add(e);
					}
				} else if (filterType.equals("By Date")) {
					filteredEvents.clear();
					Calendar dateToCompare = Calendar.getInstance();
					dateToCompare.setTime(Event.getDateFromString(filterValue));
					for (Event e : mData) {
						if (e.dateTime.get(Calendar.YEAR) == dateToCompare
								.get(Calendar.YEAR)
								&& e.dateTime.get(Calendar.DAY_OF_YEAR) == dateToCompare
										.get(Calendar.DAY_OF_YEAR)) {
							filteredEvents.add(e);
						}
					}
				} else if (filterType.equals("People Going")) {
					Collections.copy(filteredEvents, mData);
					Collections.sort(filteredEvents,
							new PeopleGoingComparator());
				} else if (filterType.equals("Closest to Me")) {

					String[] latLon = filterValue.split("&");
					curlatitude = Double.parseDouble(latLon[0]);
					curlongitude = Double.parseDouble(latLon[1]);
					
					Collections.copy(filteredEvents, mData);
					Collections.sort(filteredEvents, new LocationComparator());

				} else if (filterType.equals("Reset Filters")) {
					loadDataFromDatabase(false);
					filteredEvents = new ArrayList<Event>(mData);
				}
				result.values = filteredEvents;
				result.count = filteredEvents.size();
			}
			return result;
		}

		// I know I'm going to have an ArrayList of events so supressing warning
		// rather than dealing with an ArrayList<?>
		@SuppressWarnings("unchecked")
		@Override
		protected void publishResults(CharSequence constraint,
				FilterResults results) {

			mData = (ArrayList<Event>) results.values;
			notifyDataSetChanged();
		}
	}

	private class PeopleGoingComparator implements Comparator<Event> {
		// return an integer < 0 if event1 val less than event2val,
		// 0 if they are equal, and > 0 if event1 val is greater than event2 val
		@Override
		public int compare(Event event1, Event event2) {
			return event2.numberGoing - event1.numberGoing;
		}
	}

	private class LocationComparator implements Comparator<Event> {

		// return an integer < 0 if event1 val less than event2val,
		// 0 if they are equal, and > 0 if event1 val is greater than event2 val
		@Override
		public int compare(Event event1, Event event2) {

			double dist1 = distance(curlatitude, curlongitude, event1.latitude,
					event1.longitude);
			double dist2 = distance(curlatitude, curlongitude, event2.latitude,
					event2.longitude);

			Double diff = dist1 - dist2;
			return diff.intValue();
		}
	}

	private static double distance(double fromLat, double fromLon,
			double toLat, double toLon) {

		double radius = 6378137;
		double deltaLat = toLat - fromLat;
		double deltaLon = toLon - fromLon;
		double angle = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(deltaLat / 2),
				2)
				+ Math.cos(fromLat)
				* Math.cos(toLat)
				* Math.pow(Math.sin(deltaLon / 2), 2)));
		return radius * angle;
	}
}
