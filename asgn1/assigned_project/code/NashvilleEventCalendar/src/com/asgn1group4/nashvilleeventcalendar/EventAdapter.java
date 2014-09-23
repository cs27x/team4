package com.asgn1group4.nashvilleeventcalendar;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import com.asgn1group4.nashvilleeventcalendar.R;
import com.parse.*;

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
	

	public void loadDataFromDatabase(boolean update_view) {
		mData.clear();
		// Need database to handle the event id -- discuss
		// Start events at id 0
		
		ParseQuery<Event> query = ParseQuery.getQuery("Event");
		query.setLimit(100); //returns list of 100, max is 1000
		query.findInBackground(new FindCallback<Event>() {
		    public void done(List<Event> eventList, ParseException e) {
		        if (e == null) {
		            mData.addAll(eventList);
		            notifyDataSetChanged();
		        } else {
		            Log.e("eventList", "Error: " + e.getMessage());
		        }
		    }
		});
		
		if(update_view)
			this.notifyDataSetChanged();
	}
	
	public void updateData() {
		//TODO update the data from the database, remove following line
		//loadDataFromDatabase(true);
		
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
		return Long.parseLong(mData.get(position).getString("evid"));
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if(convertView == null) {
			convertView = this.mInflate.inflate(R.layout.list_event_layout, parent, false);
		}
		Event curEvent = getItem(position);
		((TextView)convertView.findViewById(R.id.event_title)).setText(curEvent.getString("title"));
		((TextView)convertView.findViewById(R.id.event_description)).setText(curEvent.getString("description"));
		((TextView)convertView.findViewById(R.id.event_category)).setText("Category: " + curEvent.getString("category"));
		((TextView)convertView.findViewById(R.id.event_num_people)).setText("People going: " 
				+ Integer.toString(curEvent.getInt("numberGoing")));
		((TextView)convertView.findViewById(R.id.event_date_time)).setText(curEvent.getFormattedDateTimeString());
		return convertView;
	}
	
	public void addEvent(Event newEvent) { //TODO is this necessary? -Taylor
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
						if(e.getString("category").equals(filterValue))
							filteredEvents.add(e);
					}
				} else if (filterType.equals("By Date")) {
					filteredEvents.clear();
					Calendar dateToCompare = Calendar.getInstance();
					dateToCompare.setTime(Event.getDateFromString(filterValue));
					for(Event e : mData) {
						if(e.getCal().get(Calendar.YEAR) == dateToCompare.get(Calendar.YEAR) 
								&& e.getCal().get(Calendar.DAY_OF_YEAR) == dateToCompare.get(Calendar.DAY_OF_YEAR)) {
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
			return event2.getInt("numberGoing") - event1.getInt("numberGoing");
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
