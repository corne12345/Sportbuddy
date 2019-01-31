# Report
*Corné Heijnen (12230170)*
*Project UvA*

## Description
<img src="https://github.com/corne12345/Sportbuddy/blob/master/doc/final/Screenshot_20190130-143841_SportBuddy.jpg" alt="drawing" width="200"/>

Sportbuddy is an application designed to help the user quantify its caloric intake on a so called cheat day and guide them through the process of compensating for this cheating by means of exercise. The user will fill in its intake and its activity of choice and the app will calculate the required duration to compensate this input. The user will be guided by a timer and followed by GPS on a map.

## Technical Design
Besides the standard functionality Android Studio provides to create high-quality apps, the use of some external libraries and components was also necessary to be able to create this application.

* First of all, an external Application Programming Interface (API) is required to parse the user's input of food and drinks. The [Nutritionix API](https://www.nutritionix.com/business/api) is used to process the data and return the contents of the given food in terms of macronutrients. Furthermore, it returns a link to a picture of the food (see Picasso).
* Secondly, the [Google Maps API](https://cloud.google.com/maps-platform/maps/?hl=nl) is used to display the user's current location on a map after the coordinates of their location have been obtained. This functionality is relatively easy to implement in an project.
* The third important external source is the use of [Picasso](http://square.github.io/picasso/). This is a little library which downloads an image with a given URL as parameter.
* All the requests were done using the volley library.

the in-detail description of the application can be best done by describing each independent activity and mentioning any related classes when necessary. 
* **Main Activity** is the first screen when the app is started. This is kind of a welcome screen with a nice layout. This activity contains just 1 button that starts an intent to the next activity when clicked.
* **Input Activity** is an activity in which the user can search the Nutritionix database for foods and drinks. When the user hits the search button, an instance of the **FoodRequest** class is started. This class uses Volley to build a request, add headers to it and send this request to the Nutritionix database, getting returned a JSONObject. 
This object is returned to the InputActivity, which creates lists of the foods' names and its serving sizes. These lists are passed to the **Foods Adapter** class. This adapter class uses both lists to create a list view of the search results. This view is displayed in InputActivity and is clickable to select an element and start an intent to the next activity.
* **ViewActivity** gives an overview of the foods selected in the previous activity. Once the activity is started, it sends a **NutrientsRequest**: this class uses Volley to send a modified POST-request to Nutritionix with authentication details as the headers and a query containing the clicked food in a JSON-format. Part of this JSON is returned to the activity, where it is used to display the values of its macronutrients. The amount of servings is wanted as input in the form of an EditText.
The user then has the choice to add more foods, change the last input or continue to the next screen. Al these buttons start different intents and contain the total amount of logged calories as an extra.
* **SumActivity** is een activity that mostly consists of a RadioGroup displaying different kinds of exercise the user can choose from. This choice, together with the logged calories, is send to the next activity.
* **ViewActivity** is kind of a summary showing the amount of time the user has to perform a certain activity to get even. This is the last chance for the user to change its activity or intake. 
* **MapsActvity** is mostly filled with an interactive GoogleMaps object. The upper part is a timer, which counts down starting from the duration of activity to 0. The timer can be paused and resumed. When the countdown has reached 0, the phone vibrates and the user has a chance to go to the next activity. The Maps object updates the user's current location and adds a mark to the map pinning the current location. This will lead to a trac of markers. At the first search the map moves to the current location.
* **OverviewActivity** is the final screen that just shows the passed time and travelled distance in that time. It is kind of a summary of the whole cycle of the app.

<img src="https://github.com/corne12345/Sportbuddy/blob/master/doc/Final%20layout.PNG" alt="drawing" height="200"/>
*Overview of the different activities in SportBuddy (MainActivity was unable to be displayed correctly).


## Process
This project started out at the end of 2018 with an initial proposal (included in this GitHub repository). This app was basically an account-based app where you could invite people to do sports together. It featured a Maps API, multiple account log-in system, a database containing these users and a chat history and many more complicated functions. 
I was advised to review my idea and to focus more on an app that uses an external API. In that process, I came up with the revised version, which focused more on searching foods and logging that. 
After that, I quit the total idea of logging and registering and to implement a Maps API.
At the end of the first week, the idea of this new app was starting to become more clearly defined after a period of constant changes. At that moment, I didn't really see the point of defining this all and I just wanted to get started. Looking back on it all, I understand the purpose of it all and am happy that I did so, or I would have otherwise had a nearly impossible job of completing a huge app.

In the meantime I started getting familiair with both the Nutritionix API. This was a lot harder than I had thought, since I had never sent request with extra parameters or this kind of complicated headers. I started using Volley to perform these requests, but then became convinced this was not the way to go. So I looked into external API's OKHTTP and Unirest , before switching back to Volley to finally get this all working. 
This was the steapest part of the learning curve of this project, since it really helped me to understand the ins and outs of requests in Android Studio. I became convinced that as of now, this will be pretty easy to perform in the future.

I also started using the Maps API. The implementation of a map on an app screen was pretty easy and straightforward, but adding extra features was not as easy as anticipated. It took me a lot of searching and experimenting to be able to get all the tracking done and to get results. It definitely taught me that it is better to just take the time to do a rather long tutorial in stead of just starting out and wasting time doing things I don't understand.

Most of the second week was filled with making the alfa version and getting the final hand of the API's. In this process I had some small insights and learning moments in terms of efficient coding. I also searched for an easy way to implement a timer and came to the conclusion that the easiest way was to construct one myself. I learned a lot in the process of making a rather simple thing working in all circumstances. It helped in identifying potential bugs and writing code to prevent the app from crashing.

The third week was mostly used for connecting every aspect in the app and getting things working exactly the way I wanted. On friday January 25th I was able to hand in a quite complete beta-version. In this process I learned a lot about identifying potential crashes and way to find and fix them. In the process of searching for a better way to implement my API, I added external .jar files in my library, which at a sudden point made my app crash in the building process itself. I had to dig in quite deep into the build.gradle and AndroidManifest files to understand it all and get it fixed. 

On January 27th, I met up with a friend of mine to get some tips on the design. Together we had some crazy ideas about how to make the app great looking. She is quite experienced in using the Adobe Create Suites Series, but recreating these things in Android Studio is pretty hard, since the layout files are not that user-friendly. I ended up making a color scheme and resizing images to get everything fixed. 

The last few days were spent on implementing these layout-changes and inspecting the entire code. I added some extra comments, reviewed the code in terms of style and efficiency and changed some buttons.
