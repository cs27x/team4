package com.asgn1group4.nashvilleeventcalendar;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.nashvilleeventcalendar.R;

/**
 * A fragment representing a single Event detail screen.
 * This fragment is either contained in a {@link EventListActivity}
 * in two-pane mode (on tablets) or a {@link EventDetailActivity}
 * on handsets.
 */
public class EventDetailFragment extends Fragment {
	private Button personToEventButton;
	private EventAdapter adapter;
    /**
     * The fragment argument representing the item ID that this fragment
     * represents.
     */
	public static final String ARG_EVENT_INDEX = "event_index";

    /**
     * The dummy content this fragment is presenting.
     */
    private Event mEvent;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public EventDetailFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle args = getArguments();
        adapter = EventAdapter.getInstance(this.getActivity());
        if(args.containsKey(ARG_EVENT_INDEX)) {
        	int index = args.getInt(ARG_EVENT_INDEX);
        	mEvent = EventAdapter.getInstance(this.getActivity().getApplicationContext())
        			.getItem(index);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_event_detail, container, false);

        // Show the dummy content as text in a TextView.
        if (mEvent != null) {
            ((TextView) rootView.findViewById(R.id.event_detail_title)).setText(mEvent.title);
            ((TextView) rootView.findViewById(R.id.event_detail_category)).setText(mEvent.category);
            ((TextView) rootView.findViewById(R.id.event_detail_location)).setText(mEvent.address);
            ((TextView) rootView.findViewById(R.id.event_detail_date_time)).setText(mEvent.getFormattedDateTimeString());
            ((TextView) rootView.findViewById(R.id.event_detail_num_people)).setText(Integer.toString(mEvent.numberGoing));
            ((TextView) rootView.findViewById(R.id.event_detail_description)).setText(mEvent.description);
        }
        personToEventButton = (Button)rootView.findViewById(R.id.going_to_event_button);
        addClickListener(rootView);

        return rootView;
    }
     
    private void addClickListener(View v) {
    	personToEventButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				personToEventButton.setEnabled(false);
				mEvent.anotherUserGoing();
		    	((TextView)((View)v.getParent()).findViewById(R.id.event_detail_num_people))
	    		.setText(Integer.toString(mEvent.numberGoing));
	    	
		    	// TODO add database logic to increment number of people going
		    	
		    	adapter.notifyDataSetChanged();  
			}
		});
    }
}
