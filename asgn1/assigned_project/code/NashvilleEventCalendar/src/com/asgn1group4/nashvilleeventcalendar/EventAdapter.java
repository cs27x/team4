package com.asgn1group4.nashvilleeventcalendar;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;

import android.content.Context;
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
	private static EventAdapter instance = null;
	private LayoutInflater mInflate;
	private Context mContext;
	private ArrayList<Event> mData;
	private EventFilter eventFilter;
	
    public ArrayList<String> categories = new ArrayList<String>();
	
	public static EventAdapter getInstance(Context context) {
		if(instance == null)
			instance = new EventAdapter(context);
		return instance;
	}
	
	protected EventAdapter(Context context) {
		this.mContext = context;
		mInflate = (LayoutInflater) this.mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		this.mData = new ArrayList<Event>();
		getFilter();
		initializePossibleCategories();
		loadDataFromDatabase(true);
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
	
	// TODO needs implemented to get the data from the database when the app opens.
	public void loadDataFromDatabase(boolean update_view) {
		mData.clear();
		// Need database to handle the event id
		// Start events at id 0
		
		// id, title, address, description, dateTime, category
		mData.add(new Event("0", "Event0", "123 Road Rd Nashville, TN 37235", "Stuff stuff stuff Stuff stuff stuff Stuff stuff stuff Stuff " +
				"stuff stuff Stuff stuff stuff Stuff stuff stuff Stuff stuff stuff Stuff stuff stuff Stuff stuff stuff Stuff stuff stuff",
				"12/25/2014 9:00 AM", "Other"));
		mData.add(new Event("1", "Event1", "123 Road Rd Nashville, TN 37235", "Such event! Much fun! Wow!", "9/21/2014 5:00 PM", "Arts"));
		mData.add(new Event("2", "Event2", "111 Road Rd Nashville, TN 37235", "Get together and such.", "10/2/2014 11:00 PM", "Networking"));
		mData.add(new Event("3", "Event3", "555 Road Rd Nashville, TN 37235", "Birthday party!", "9/21/2014 9:00 AM", "Community"));
		mData.add(new Event("4", "Event4", "554 Road Rd Nashville, TN 37235", "Board game night.", "10/20/2014 10:00 PM", "Classes"));
		mData.add(new Event("5", "Event5", "553 Road Rd Nashville, TN 37235", "LAN party.", "11/1/2014 9:00 AM", "Other"));
		mData.add(new Event("6", "Event6", "552 Road Rd Nashville, TN 37235", "Coding challenge.", "10/30/2014 8:00 PM", "Technical"));
		mData.add(new Event("7", "Event7", "551 Road Rd Nashville, TN 37235", "Interviews for positions.", "10/30/2014 11:00 AM", "Networking"));
		mData.add(new Event("8", "Event8", "55 Road Rd Nashville, TN 37235", "Hangout together.", "1/1/2015 9:00 PM", "Food and Drink"));
		mData.add(new Event("9", "Event9", "111 Road Rd Nashville, TN 37235", "Send-off party.", "1/2/2015 8:30 PM", "Parties"));
		mData.add(new Event("10", "Event10", "678 Road Rd Nashville, TN 37235", "Graduation party", "10/22/2014 10:00 AM", "Parties"));
		mData.add(new Event("11", "Event11", "999 Road Rd Nashville, TN 37235", "Trash pickup party", "12/19/2014 11:30 AM", "Community"));
		if(update_view)
			this.notifyDataSetChanged();
	}
	
	public void updateData() {
		//TODO update the data from the database, remove following line
		//loadDataFromDatabase();
		
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
		if(convertView == null) {
			convertView = this.mInflate.inflate(R.layout.list_event_layout, parent, false);
		}
		Event curEvent = getItem(position);
		((TextView)convertView.findViewById(R.id.event_title)).setText(curEvent.title);
		((TextView)convertView.findViewById(R.id.event_description)).setText(curEvent.description);
		((TextView)convertView.findViewById(R.id.event_category)).setText("Category: " + curEvent.category);
		((TextView)convertView.findViewById(R.id.event_num_people)).setText("People going: " 
				+ Integer.toString(curEvent.numberGoing));
		((TextView)convertView.findViewById(R.id.event_date_time)).setText(curEvent.getFormattedDateTimeString());
		return convertView;
	}
	
	public void addEvent(Event newEvent) {
		this.mData.add(newEvent);
		this.notifyDataSetChanged();
	}

	@Override
	public Filter getFilter() {
		if(eventFilter==null)
			eventFilter = new EventFilter();
		return eventFilter;
	}
	
	private class EventFilter extends Filter {

		@Override
		protected FilterResults performFiltering(CharSequence constraint) {
			Log.d(this.getClass().getSimpleName(), "Filtering events.");
			FilterResults result = new FilterResults();
			if(constraint.length() == 0) {
				result.values = mData;
				result.count = mData.size();
			} else {
				ArrayList<Event> filteredEvents = new ArrayList<Event>(mData);
				String constr_str = constraint.toString();
				String[] filterTypeAndValue = constr_str.split("\\|");
				String filterType = filterTypeAndValue[0];
				String filterValue =  filterTypeAndValue[1];
				if(filterType.equals("Category")) {
					filteredEvents.clear();
					for(Event e : mData) {
						if(e.category.equals(filterValue))
							filteredEvents.add(e);
					}
				} else if (filterType.equals("By Date")) {
					filteredEvents.clear();
					Calendar dateToCompare = Calendar.getInstance();
					dateToCompare.setTime(Event.getDateFromString(filterValue));
					for(Event e : mData) {
						if(e.dateTime.get(Calendar.YEAR) == dateToCompare.get(Calendar.YEAR) 
								&& e.dateTime.get(Calendar.DAY_OF_YEAR) == dateToCompare.get(Calendar.DAY_OF_YEAR)) {
							filteredEvents.add(e);
						}
					}
				} else if (filterType.equals("People Going")) {
					Collections.copy(filteredEvents, mData);
					Collections.sort(filteredEvents, new PeopleGoingComparator());
				} else if (filterType.equals("Closest to Me")) {
					Collections.copy(filteredEvents, mData);
					Collections.sort(filteredEvents, new LocationComparator());
					// TODO need to filter values by location, implement comparator, see below
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
			// TODO Auto-generated method stub
			return 0;
		}
	}
}
