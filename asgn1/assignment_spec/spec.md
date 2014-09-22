# NashPast

## People in Class on 9/16/2014

Alex Meyer

Taylor Beck

Violetta Vylegzhanina

Liyan Hou

Monica Hedda

## Overview

Nashville is a city filled with historic value that may not be known by many people who
live or visit the city. Nashville has a list of historic markers that give the latitude
and longitude of historic locations and their significance in Nashville history found at
https://data.nashville.gov/Culture/Historic-Markers/vk65-u7my. Many people don't know
that these historical markers exist throughout the city and don't know the significance
of each location.

For this assignment, you will create an application to help all the people who live or
visit Nashville to have one place to go to view historical markers throughout the city
and be able to add markers from the list to their queue to visit as they tour the city.
The application will keep track of previously visited locations so the user can see
where they have visited before. When viewing locations they can view see the information
associated with each location.

## User Stories

0. As a tourist, I want to see a list of all historical markers so I can view interesting
historical locations in Nashville (0.5 hrs)
   
1. As a tourist, I want to see all of my unvisited historical markers so I can view which
markers I have not seen yet (0.5 hrs)
   
2. As a tourist, I want to see all of my visited historical markers so I can view which 
markers I have already visited (0.5 hrs)

3. As a tourist, I want to see a list of all historical markers sorted by distance from a certain
location (which can also be where I currently am) so I can see historical markers near a place I
am going to. (2 hrs)

4. As a tourist, I want to view my queue of historical markers that I wanted to visit so I can see which historical
markers I have in my queue and visit them in the order I want to by the order I decided for my 
queue. (1 hrs)

5. As a tourist, I want to be able to edit (add, remove, rearrange) my queue of historical markers to visit so I can 
add historical markers to visit, change the order in which I visit them or remove historical markers from my list to visit. 
(1 hrs)

**Removed following user stories as per the accepted requirement changes requested by the team implementing it -

6. As a tourist, I want to be able to click on a historical marker and see the description,  
location (lat/long), and address of that historical marker so I can see the interesting 
historical information related to that marker and its relative location on the map of Nashville (1.5 hrs)

7. As a tourist, I want to be able to see where historical markers are on a map so I can view
the relative location of all of them on the map of Nashville (2 hrs)
   
## Possibly Helpful Technical Information

1. If you are unfamiliar with the Javascript Object Notation format that data.nashville.gov
   can provide data in, you can read this tutorial:
   http://www.copterlabs.com/blog/json-what-it-is-how-it-works-how-to-use-it/

2. These datasets may be helpful for implementing this application:
  - Historical marker data in JSON format: https://data.nashville.gov/api/views/vk65-u7my/rows.json?accessType=DOWNLOADy
  
3. You can use the Jackson library to convert JSON Strings into Java Objects as 
   described in their 1-minute tutorial: https://github.com/FasterXML/jackson-databind/
   
## Evaluation Rubrics

There are 290 total points for this application. The breakdown of these points to
user stories is as follows:

0. (20pts) - As a tourist, I want to see a list of all historical markers so I can view interesting
	historical locations in Nashville - Evaluation process:
	- Use the historical marker data from https://data.nashville.gov/Culture/Historic-Markers/vk65-u7my
	to find all the possible historical markers Nashville.
	- Within the application the user should be able to see a list of all historical markers within Nashville
	from the data provided.
	- Open up the application and ensure that you can see all of the historical markers from the data.
	If it appears that all of the markers are visible within the list then award 20pts.

1. (20pts) - As a tourist, I want to see all of my unvisited historical markers so I can view which
	markers I have not seen yet - Evaluation process:
	- Use the same historical marker data from above
	- Should keep track of previously viewed marks by the user on the current device and should
	store the data somewhere so it persists across uses of the app.
	- User should only see markers they haven't visited yet.
	- Open up the application and go to certain markers/mark certain markers as visited then check
	the unvisited section to ensure none of the places visited are displayed in the list. If it doesn't
	show them then award 10pts.
	- Close the application after using it and reopen the app to ensure previously visited markers
	are still not shown in the unvisited section of the app which means the data persisted across
	uses. If it doesn't show them then award 10pts.

2. (20pts) - As a tourist, I want to see all of my visited historical markers so I can view which 
	markers I have already visited - Evaluation process:
	- Use the same historical marker data from above
	- Should keep track of previously viewed markers by the user on the current device and should
	store the data somewhere so it persists across uses of the app.
	- User should only see markers they have previously visited.
	- Open up the application and go to certain markers/mark certain markers as visited then check
	previously visited section of the app and ensure the markers shown are only previously visited.
	If so award 10pts. 
	- Close the application after using it and reopen the app to ensure previously visited markers
	are still shown in the previously visited list which means that data persisted across uses. If
	so award 10pts.

3. (50pts) - As a tourist, I want to see a list of all historical markers sorted by distance from a certain
	location (which can also be where I currently am) so I can see historical markers near a place I
	am going to - Evaluation process:
	- Use same historical marker data from above
	- Should use either current long/lat estimate of user location or the lat/long estimate of a
	certain location they enter the address of to order the list of historical
	markers by closest to either where the user is or where they enter address of
	- Open the list of historical markers/closest location section of app and ensure that the
	markers are ordered in the list by closest to current user location. If so award 25pts.
	- Open list of historical markers/closest location section of app and enter the address of a location
	and ensure that the markers are ordered in the list by closest to the user specified location.
	If so award 25pts.

4. (20pts) - As a tourist, I want to view my queue of historical markers that I wanted to visit so I can see which
	historical markers I have in my queue and visit them in the order I want to by the order I decided for my 
	queue - Evaluation process:
	- Use only historical marker data from above
	- Within the app should be able to view only historical markers that are in the user-specific queue
	and they should be in the order defined by the user
	- Open location in app where user's queued locations are and ensure that all items that the user
	has added to their queue are there and that they are in the correct order defined by how the user
	added/deleted/rearranged them. If so award 20pts

5. (75pts) - As a tourist, I want to be able to edit (add, remove, rearrange) my queue of historical markers to visit 
	so I can add historical markers to visit, change the order in which I visit them, or remove historical markers from my list 
	to visit - Evaluation process:
	- Somewhere within the app where the user is either viewing a list of markers or the specific
	information on one marker, the user should be able to add a specific historical marker to their
	queue of markers to visit.
	- Within the app where the user can view their queue the user should be able to remove markers from
	their queue or rearrange the order of them.
	- This queue should persist across app uses so if the app is closed the queue still exists.
	- Open the location in the app where markers can be added to the user's queue and add multiple
	locations to the queue. If you can see those markers in the user's queue in the app then award 15pts.
	- Open the location in the app where the user can see their queue of markers and modify this queue by
	removing items. Exit the queue area and go back and make sure the changes made still exist in the user
	queue. If so award 15pts.
	- Open the location in the app where the user can see their queue of markers and modify this queue by
	rearranging items. Exit the queue area and go back and make sure the changes made still exist in the 
	user queue. If so award 15pts.
	- Do both of the above actions but close the app after adding/removing/rearranging and reopen the app
	and ensure all changes still exist in the user queue. If so award 30pts.
     
6. (20pts) - Uses some library to parse JSON - Evaluation process:
   - Checkout the source code for the application and find the locations in the code
      where it is downloading data from nashville.data.gov. Check and see if it is
     using the a library to convert the JSON to objects. If so,
     award 20pts.
     
7. (20pts) - Downloads the historical marker list from data.nashville.gov - Evaluation process:
   - Checkout the source code for the application and find the locations in the code
      where it is downloading data from nashville.data.gov. If it is not downloading
      data from data.nashville.gov, award 0pts, otherwise award 20pts. 
      
8. (45pts) - Uses a consistent code style and applies Java best practices - Evaluation process:
   - Checkout the source code for the application 
   - If all classes are in packages with full lower case names, no non-alphanumeric 
     characters, and at least 2 dotted package components (e.g., "com.foo" vs. just "com"),
     award 5pts.
   - If all classes have only private member variables that are accessed from other classes
     via getter/setter methods, award 5pts.
   - If all class names are title cased, award 5pts.
   - If all classes have the same variable naming / spacing scheme, award 5pts.
   - If all constants are with full upper case names, award 5pts.
   - If class members are ordered by scope, award 5pts.
   - If no empty catch blocks that suppress exceptions, award 10pts.
   - If no class is more than about 300 lines long, award 5pts.

**Removed following  Evaluation Rubrics as per the accepted requirement changes requested by the team implementing it -

1. (30pts) - As a tourist, I want to be able to click on a historical marker and see the description,  
	location (lat/long), and address of that historical marker so I can see the interesting 
	historical information related to that marker and its relative location on the map of Nashville - 
	Evaluation process:
	- Anywhere in the app where there is a list of historical markers (user marker queue, list) the user
	should be able to click on a specific marker and it will open a page where generic information about
	the historical marker is shown such as the description, location (lat/long), and address of the 
	historical marker
	- Open all locations in the app where there is a list of markers and click on multiple markers to 
	ensure that it opens a page that is specific to that marker and provides the general information
	associated with the marker (description, location, address). If so award 30pts.

2. (50pts) - As a tourist, I want to be able to see where historical markers are on a map so I can view
	the relative location of all of them on the map of Nashville - Evaluation process:
	- Use same historical marker data as listed above.
	- User should be able to open a map with all historical markers marked on the map
	- Open the location in the app where user can view locations on a map and ensure that all markers
	are somehow demonstrated on the map. If so award 50pts.

