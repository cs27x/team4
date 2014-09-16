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

4. As a tourist, I want to choose the historical markers I want to add to the queue of places 
 to visit (in a certain order) so I can visit locations in a tour of markers (1 hrs)

5. As a tourist, I want to be able to click on a historical marker and see the description,  
location (lat/long), and address of that historical marker so I can see the interesting 
historical information related to that marker and its relative location on the map of Nashville (1.5 hrs)

6. As a tourist, I want to be able to see where historical markers are on a map so I can view
the relative location of all of them on the map of Nashville (2 hrs)

7. As a tourist, I want to be able to edit my queue of locations to visit so I can change the
order in which I visit locations. (1 hrs)
   
## Possibly Helpful Technical Information

1. If you are unfamiliar with the Javascript Object Notation format that data.nashville.gov
   can provide data in, you can read this tutorial:
   http://www.copterlabs.com/blog/json-what-it-is-how-it-works-how-to-use-it/

2. These datasets may be helpful for implementing this application:
  - Historical marker data in JSON format: https://data.nashville.gov/api/views/vk65-u7my/rows.json?accessType=DOWNLOADy
  
3. You can use the Jackson library to convert JSON Strings into Java Objects as 
   described in their 1-minute tutorial: https://github.com/FasterXML/jackson-databind/
   
## Evaluation Rubrics

There are 350 total points for this application. The breakdown of these points to
user stories is as follows:

NEED TO DO

0. (10pts) - As a resident of Nashville, I would like to be able to find 3 events in Nashville
   parks and information about them so that I don't get bored. Evaluation process:
   - Use the parks special event permit data from https://data.nashville.gov/Environment/Parks-Special-Events-Permits-Data-2014-/vygj-v677
     to find a day that there are more than 3 events in Nashville.
   - Open a browser to the GitHub repo for the project and view the README.md file. If
     it includes instructions on how to run the application, follow the instructions 
     to run the application and attempt to assess if the application has randomly chosen
     3 events (e.g., there is no obvious selection of the first / last set of events, etc.).
     If so, award 5pts. If not, award 0pts and continue to the next rubric.
   - Use the parks special event permit data from https://data.nashville.gov/Environment/Parks-Special-Events-Permits-Data-2014-/vygj-v677
     to find a day that there are less than 3 events in Nashville.
   - Run the application and make sure that it prints out all of the possible events for
     that day and doesn't crash. If it does, award 5pts.
   
1. (10pts) - As a resident of Nashville, I would like to be able to ask for an additional 3
   random things to do if I don't like the first list of things so that I can find
   something that I like. Evaluation process:
   - Use the parks special event permit data from https://data.nashville.gov/Environment/Parks-Special-Events-Permits-Data-2014-/vygj-v677
     to find a day that there are more than 3 events in Nashville.
   - Open a browser to the GitHub repo for the project and view the README.md file. If
     it includes instructions on how to run the application, follow the instructions 
     to run the application 10 times or use the whatever facility is provided by
     the application to generate additional things to do in order to get 10 sets
     of random things to do. If at least 2 different sets of things to do are presented,
     award 10pts. If you cannot figure out how to rerun or generate a new list of things
     to do from the README.md file, award 0pts.

   
2. (20pts) - As someone with occasional engagements on the weekend, I would like to be able to
   find 3 random events on a specific date so that I can find things to do on a day
   that I have free time. Evaluation process:
   - Use the parks special event permit data from https://data.nashville.gov/Environment/Parks-Special-Events-Permits-Data-2014-/vygj-v677
     to find a day that there are more than 3 events in Nashville.
   - Open a browser to the GitHub repo for the project and view the README.md file. If
     it includes instructions on how to run the application and select a specific date
     to find events on, follow the instructions 
     to run the application with your chosen data and attempt to assess if the application 
     chooses 3 events for that date. If so, award 10pts. 
     If you cannot figure out how to run the application and specify a date for the
     events to be chosen on or it doesn't work, award 0pts and continue to the next rubric.
   - Run the application 2 additional times using different dates and ensure that each time
     the appropriate events for that date are displayed. If this test is passed, 
     award 10pts. 
   
3. (5pts) - As someone who hates looking for a place to park, I would like to be able to only
   see 3 random events that will be easy to park at so I don't spend a lot of time
   driving around looking for parking. Evaluation process:
   - Use the parks special event permit data from https://data.nashville.gov/Environment/Parks-Special-Events-Permits-Data-2014-/vygj-v677
     to find a day that has at least one event with more than 1000 people.
   - Open a browser to the GitHub repo for the project and view the README.md file. If
     it includes instructions on how to run the application and specify that only
     events that are easy to park at should be chosen, follow the instructions 
     to run the application with your chosen date and assess if the application 
     only chooses events with an attendance under 1000 people. If this test is passed, 
     award 5pts. 
   
4. (30pts) - As someone who is into exercise, I would like to be able to see 3 random events that 
   involve some form of physical activity so that I can go to the event in place of my
   daily workout. Evaluation process:
   - Use the parks special event permit data from https://data.nashville.gov/Environment/Parks-Special-Events-Permits-Data-2014-/vygj-v677
     to find a day that has at least one event that involves running or biking and the
     words "run", "5K", "bike", or "race" in the title.
   - Open a browser to the GitHub repo for the project and view the README.md file. If
     it includes instructions on how to run the application and specify that only
     events that involve exercise should be chosen, follow the instructions 
     to run the application with your chosen date and assess if the application 
     only chooses from the exercise events with the appropriate terms in the title. 
     If this test is passed, award 30pts. 
   
5. (30pts) - As an inexperienced computer user, I would like to be able to go to a website that
   shows me 3 random things to do on a specific date so that I don't have to download,
   install, and run any software. Evaluation process:
   - Open a browser to the GitHub repo for the project and view the README.md file. If
     it includes a URL for accessing the service and you are able to browse to the service,
     award 30pts. 
   
6. (20pts) - As a trip planner, I would like to be able to provide a series of dates and time blocks and
   receive a possible itinerary listing 3 random things that could be done in each
   day / time block so that I can create itineraries for Nashville tourists.  Evaluation process:
   - Use the parks special event permit data from https://data.nashville.gov/Environment/Parks-Special-Events-Permits-Data-2014-/vygj-v677
     to find 3 dates that have at least one event.
   - Open a browser to the GitHub repo for the project and view the README.md file. If
     it includes instructions on how to run the application and specify a block of
     dates/times that things to do should be chosen for, follow the instructions 
     to run the application with your chosen dates/times and assess if the application 
     selects at least one event for each date/time block. 
     If this test is passed, award 20pts. 
   
7. (40pts) - As a student without a car, I would like to be able to only see events that are taking
   place at parks that are accessible via the bus system so that I can actually get to
   the event. Evaluation process:
   - Use the parks special event permit data from https://data.nashville.gov/Environment/Parks-Special-Events-Permits-Data-2014-/vygj-v677
     to find a date that has at least 1 event that is not on the list of public bus
     stops: https://data.nashville.gov/Transportation/Metro-Transit-Authority-Bus-Stops-Map-/869d-gy6r
   - Open a browser to the GitHub repo for the project and view the README.md file. If
     it includes instructions on how to run the application and specify that only events
     accessible via the bus should be chosen, follow the instructions 
     to run the application 20 times with the bus accessible option for your chosen date. 
     If the application never selects your event that is not accessible via the bus,
     award 40pts (is this really a good test for this...?). 
   
8. (20pts) - As someone with kids, I would like to be able to only see events that are at parks that
   have playgrounds so that my kids don't get bored and need to go home. Evaluation process:
   - Use the parks special event permit data from https://data.nashville.gov/Environment/Parks-Special-Events-Permits-Data-2014-/vygj-v677
     to find a date that has at least 1 event that is at a park with a playground: 
     http://data.nashville.gov/resource/n37w-5mq8.json
   - Open a browser to the GitHub repo for the project and view the README.md file. If
     it includes instructions on how to run the application and specify that only events
     at parks with playgrounds should be chosen, follow the instructions 
     to run the application 10 times for your chosen date. 
     If the application only selects events that are at parks with playgrounds,
     award 40pts. 
   
9. (20pts) - As someone with kids, I would like to be able to only see events that are at parks that
   have restrooms so that I don't have to leave to take my kids to the bathroom. Evaluation process:
   - Use the parks special event permit data from https://data.nashville.gov/Environment/Parks-Special-Events-Permits-Data-2014-/vygj-v677
     to find a date that has at least 1 event that is at a park with a restroom: 
     http://data.nashville.gov/resource/n37w-5mq8.json
   - Open a browser to the GitHub repo for the project and view the README.md file. If
     it includes instructions on how to run the application and specify that only events
     at parks with restrooms should be chosen, follow the instructions 
     to run the application 10 times for your chosen date. 
     If the application only selects events that are at parks with restrooms,
     award 20pts. 
   
10. (20pts) - As someone with a dog, I would like to be able to only see events that are at parks that
   have dog parks so that I can take my dog with me. Evaluation process:
   - Use the parks special event permit data from https://data.nashville.gov/Environment/Parks-Special-Events-Permits-Data-2014-/vygj-v677
     to find a date that has at least 1 event that is at a park with a dog park: 
     http://data.nashville.gov/resource/n37w-5mq8.json
   - Open a browser to the GitHub repo for the project and view the README.md file. If
     it includes instructions on how to run the application and specify that only events
     at parks with dog parks should be chosen, follow the instructions 
     to run the application 10 times for your chosen date. 
     If the application only selects events that are at parks with dog parks,
     award 20pts. 
   
11. (25pts) - As a new user of the things to do generator, I would like to be able to read a 
    README.md file with complete instructions on how to obtain/access and run the application
    so that I can use it. Evaluation process:
   - Open a browser to the GitHub repository for the application
   - If a README.md file is present and uses appropriate markdown, award 5pts.
   - If the README.md file includes instructions on how to access/compile/run the application
     on the web or locally, follow the instructions without doing anything that isn't 
     explicitly described. If you are able to run the application, award 20pts.
     
12. (20pts) - Uses the Jackson library to parse JSON - Evaluation process:
   - Checkout the source code for the application and find the locations in the code
      where it is downloading data from nashville.data.gov. Check and see if it is
     using the Jackson library's ObjectMapper to convert the JSON to objects. If so,
     award 25pts.
     
13. (30pts) - Downloads the special permit list from data.nashville.gov - Evaluation process:
   - Checkout the source code for the application and find the locations in the code
      where it is downloading data from nashville.data.gov. If it is not downloading
      data from data.nashville.gov, award 0pts, otherwise award 30pts. 
      
12. (50pts) - Uses a consistent code style and applies Java best practices - Evaluation process:
   - Checkout the source code for the application 
   - If all classes are in packages with full lower case names, no non-alphanumeric 
     characters, and at least 2 dotted package components (e.g., "com.foo" vs. just "com"),
     award 10pts.
   - If all classes have only private member variables that are accessed from other classes
     via getter/setter methods, award 10pts.
   - If all class names are title cased, award 10pts.
   - If all classes have the same variable naming / spacing scheme, award 10pts.
   - If all methods have been broken up into code chunks of 15 lines or less, award
     10pts

