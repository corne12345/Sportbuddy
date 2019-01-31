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
* **ViewActivity** gives an overview of the foods selected in the previous activity. Once the activity is started, it sends a **NutrientsRequest**: this class uses Volley to send a modified POST-request to Nutritionix with authentication details as the headers and a query containing the clicked food in a JSON-format. Part of this JSON is returned to the activity, where it is used to display the values of its macronutrients. 
