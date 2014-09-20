Event Calendar
==

### People in Class on 9/16/2014

* Brendan McNamara
* Jake Bray
* Walton Seymour
* Michael Nakayama
* Ethan Dixius
* Joseph Navin

### Overview

This is an app where people can post and receive events that are happening in Nashville.  This would include checking today's events, sorting events into certain categories, and looking up events based on location. Users should also be able to declare they are going to events so that others can get a sense of how many people are attending and so events can be stored for the user to go back to later.

This app can be designed as a web or mobile app (this is left up to the implementing team's discretion). In addition, where the data comes from and the backend infrastructure is also left as a decision for the implement team. At the bottom, there are some suggestions for what to use as infrastructure.

### User Stories

0. As a resident of Nashville, I can search events that are going on today so that I can find something to do.
1. As a resident of Nashville, I can post events that I know about so that other people can discover them.
2. As a resident of Nashville, I can search events in a particular location so that I can go to events that are close to me.
3. As a resident of Nasvhille, I can search events by category so that I can find events that are relevant to what I want to do (categories of events are up to you).
4. As a resident of Nashville, I can mark events that I would like to go to so that I can look them up later.
5. As a resident of Nashville, I can see how many people are planning to go to this event, so that I can determine if it's an event I would like to go to.
6. As a resident of Nashville, I can see events that I posted so that I know what events I've published to the app.

###Suggestions

* Look at https://data.nashville.gov/ first to see if this has an API you would like to use for getting/sending events

* Look at https://parse.com if you want to build a quick backend to interact with your app.

* User verification can be easily done through either parse.com or the Facebook SDK.


###Evaluation Rubric

This is a total of 85 points.

0. **(25pt)** - As a resident of Nashville, I can search events that are going on today so that I can find something to do.
    * Provide a way for users to explicitly search for events that are going on today
    * All the events that show in your today list also show up in the list of today events for whatever backend you are using.
    
1. **(20pt)** - As a resident of Nashville, I can post events that I know about so that other people can discover them.
    * A user who is posting an event can then find there event somewhere in the app
    
2. **(10pt)** - As a resident of Nashville, I can search events in a particular location so that I can go to events that are close to me.
    * A user can search for events by address.

3. **(10pt)** - As a resident of Nasvhille, I can search events by category so that I can find events that are relevant to what I want to do (categories of events are up to you).
    * A user has some way of explicitly filtering content by category.
    * All events in this category should show up when searching the category.

4. **(10 pt)** - As a resident of Nashville, I can see how many people are planning to go to an event, so that I can determine if it's an event I would like to go to.
    * Each event has some indication of the number of people attending.
    * Note: User story 5 has users indicate they are going to an event, which is an important step in adding this feature.

5. **(10 pt)** - Consistent and good style.
    * All classes are in packages with full lower case names, no non-alphanumeric characters, and at least 2 dotted package components (e.g., "com.foo" vs. just "com") (2pt)
    * All classes have only private member variables that are accessed from other classes via getter/setter methods (2pt)
    * All class names are title cased (2pt)
    * All classes have the same variable naming scheme (camelCase, underscore, etc...) (2pt)
    * All methods have been broken up into code chunks of 30 lines or less (2pt)
