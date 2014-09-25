package com.asgn1group4.nashvilleeventcalendar;

import android.app.Application;

import com.parse.Parse;
import com.parse.ParseObject;

public class App extends Application {
	@Override
    public void onCreate() {
      super.onCreate();

      //Parse.enableLocalDatastore(this);
      ParseObject.registerSubclass(Event.class);
      Parse.initialize(this, "FoSWd8SBKQ7kTs4nny52GfDhU5ewXBG6ow4G65tT", "wmX1STIDtyJYcatsNsgGtKZjMJn6a4IgpfS1Grgt");
    }
}
