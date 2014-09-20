package com.asgn1group4.nashvilleeventcalendar;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.nashvilleeventcalendar.R;

public class EventAdapter extends BaseAdapter {
	private static EventAdapter instance = null;
	private LayoutInflater mInflate;
	private Context mContext;
	private Event[] mData;
	
	public static EventAdapter getInstance(Context context) {
		if(instance == null)
			instance = new EventAdapter(context);
		return instance;
	}
	
	protected EventAdapter(Context context) {
		this.mContext = context;
		mInflate = (LayoutInflater) this.mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		updateData();
	}
	
	// TODO needs implemented to get the data from the database
	public void updateData() {
		// id, title, address, description, dateTime
		mData = new Event[] {
				new Event("1", "Event1", "123 Road Rd Nashville, TN 37235", "Such event! Much fun! Wow!", "9/21/2014 5:00 PM", "Fun"),
				new Event("2", "Event2", "111 Road Rd Nashville, TN 37235", "Get together and such.", "10/2/2014 11:00 PM", "Fun"),
				new Event("3", "Event3", "555 Road Rd Nashville, TN 37235", "Birthday party!", "9/21/2014 9:00 AM", "Community"),
				new Event("4", "Event4", "554 Road Rd Nashville, TN 37235", "Board game night.", "10/20/2014 10:00 PM", "Games"),
				new Event("5", "Event5", "553 Road Rd Nashville, TN 37235", "LAN party.", "11/1/2014 9:00 AM", "Games"),
				new Event("6", "Event6", "552 Road Rd Nashville, TN 37235", "Coding challenge.", "10/30/2014 8:00 PM", "Technical"),
				new Event("7", "Event7", "551 Road Rd Nashville, TN 37235", "Interviews for positions.", "10/30/2014 11:00 AM", "Technical"),
				new Event("8", "Event8", "55 Road Rd Nashville, TN 37235", "Hangout together.", "1/1/2015 9:00 PM", "Fun"),
				new Event("9", "Event9", "111 Road Rd Nashville, TN 37235", "Send-off party.", "1/2/2015 8:30 PM", "Party"),
				new Event("10", "Event10", "678 Road Rd Nashville, TN 37235", "Graduation party", "10/22/2014 10:00 AM", "Party"),
				new Event("11", "Event11", "999 Road Rd Nashville, TN 37235", "Trash pickup party", "12/19/2014 11:30 AM", "Community"),
				new Event("12", "Event12", "123 Road Rd Nashville, TN 37235", "Stuff stuff stuff Stuff stuff stuff Stuff stuff stuff Stuff " +
						"stuff stuff Stuff stuff stuff Stuff stuff stuff Stuff stuff stuff Stuff stuff stuff Stuff stuff stuff Stuff stuff stuff",
						"12/25/2014 9:00 AM", "Random")
		};
	}
	
	@Override
	public int getCount() {
		return mData.length;
	}

	@Override
	public Event getItem(int position) {
		return mData[position];
	}

	@Override
	public long getItemId(int position) {
		return Long.parseLong(mData[position].id);
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
		((TextView)convertView.findViewById(R.id.event_date_time)).setText(curEvent.getFormattedDateTimeString());
		return convertView;
	}
}
