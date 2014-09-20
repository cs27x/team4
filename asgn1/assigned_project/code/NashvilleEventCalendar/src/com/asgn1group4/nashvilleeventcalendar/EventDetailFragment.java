package com.asgn1group4.nashvilleeventcalendar;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.nashvilleeventcalendar.R;

/**
 * A fragment representing a single Event detail screen.
 * This fragment is either contained in a {@link EventListActivity}
 * in two-pane mode (on tablets) or a {@link EventDetailActivity}
 * on handsets.
 */
public class EventDetailFragment extends Fragment {
    /**
     * The fragment argument representing the item ID that this fragment
     * represents.
     */
	public static final String ARG_EVENT_ID = "event_id";

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
        if(args.containsKey(ARG_EVENT_ID)) {
        	int event_id_to_index = Integer.parseInt(args.getString(ARG_EVENT_ID));
        	mEvent = EventAdapter.getInstance(this.getActivity().getApplicationContext())
        			.getItem(event_id_to_index);
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

        return rootView;
    }
}
