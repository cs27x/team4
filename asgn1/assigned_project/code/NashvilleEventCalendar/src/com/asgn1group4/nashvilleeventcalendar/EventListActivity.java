package com.asgn1group4.nashvilleeventcalendar;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.nashvilleeventcalendar.R;

/**
 * An activity representing a list of Events. This activity
 * has different presentations for handset and tablet-size devices. On
 * handsets, the activity presents a list of items, which when touched,
 * lead to a {@link EventDetailActivity} representing
 * item details. On tablets, the activity presents the list of items and
 * item details side-by-side using two vertical panes.
 * <p>
 * The activity makes heavy use of fragments. The list of items is a
 * {@link EventListFragment} and the item details
 * (if present) is a {@link EventDetailFragment}.
 * <p>
 * This activity also implements the required
 * {@link EventListFragment.Callbacks} interface
 * to listen for item selections.
 */
public class EventListActivity extends Activity
        implements EventListFragment.Callbacks {

    /**
     * Whether or not the activity is in two-pane mode, i.e. running on a tablet
     * device.
     */
    private boolean mTwoPane;
    public static boolean filtered = false;
    private Button addEventButton;
    private Button filterEventButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_list);
        addEventButton = (Button)findViewById(R.id.add_event_button);
        filterEventButton = (Button)findViewById(R.id.filter_event_button);
        addClickListeners();

        if (findViewById(R.id.event_detail_container) != null) {
            // The detail container view will be present only in the
            // large-screen layouts (res/values-large and
            // res/values-sw600dp). If this view is present, then the
            // activity should be in two-pane mode.
            mTwoPane = true;

            // In two-pane mode, list items should be given the
            // 'activated' state when touched.
            ((EventListFragment) getFragmentManager()
                    .findFragmentById(R.id.event_list))
                    .setActivateOnItemClick(true);
        }
    }
    
    private void addClickListeners() {
    	addEventButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
	        	Intent intent = new Intent(v.getContext(), AddEventActivity.class);
	        	startActivity(intent);
			}
		});
        
    	filterEventButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
	        	filtered = true;
	        	Intent intent = new Intent(v.getContext(), FilterEventListActivity.class);
	        	startActivity(intent);
			}
		});
    }

    /**
     * Callback method from {@link EventListFragment.Callbacks}
     * indicating that the item with the given ID was selected.
     */
    @Override
    public void onItemSelected(int index) {
        if (mTwoPane) {
            // In two-pane mode, show the detail view in this activity by
            // adding or replacing the detail fragment using a
            // fragment transaction.
            Bundle arguments = new Bundle();
            arguments.putInt(EventDetailFragment.ARG_EVENT_INDEX, index);
            EventDetailFragment fragment = new EventDetailFragment();
            fragment.setArguments(arguments);
            getFragmentManager().beginTransaction()
                    .replace(R.id.event_detail_container, fragment)
                    .commit();
        } else {
            // In single-pane mode, simply start the detail activity
            // for the selected item ID.
            Intent detailIntent = new Intent(this, EventDetailActivity.class);
            detailIntent.putExtra(EventDetailFragment.ARG_EVENT_INDEX, index);
            startActivity(detailIntent);
        }
    }
}
