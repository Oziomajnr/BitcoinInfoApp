# Bitcoin Info App

How to use: To build this project, download the project Zip file, unzip it and open it with android studio

Architecture: MVVM
![enter image description here](https://miro.medium.com/max/638/1*sdOtTrxrOVQzYRygaB1qqw.jpeg)

## Project Overview

The project consist of two modules, the **app module** and the **data module**.
The app module contain view specific code and logic involved in manipulating data that is required by the view, it gets the data from the data module by subscribing to its observables, mapping the data to its own view data and exposing it to the UI through a live data that exists in the view model.

 The data module is responsible for fetching the data from data sources and exposing it to any client through Rx Java Observables. In this example the only data source is the bitcoin remote server.

Further improvement could be made to the project architecture by adding a middle layer that separates the app module from the data module, but that would be an overkill for this simple app.

Technology/Library Used:
	1.  Retrofit
	2.  Moshi
	3. Rx Java
	4. Android x
	5. Jetpack Navigation
	6. Junit
	7. Robolectric
	8. Dagger
	9. MpChart


Link to screenshots:
https://postimg.cc/gallery/3w9dXJQ

Apk:
https://drive.google.com/file/d/1KxP6gxmV3ZhjHE9JzebwoEDIV_tgzCfV/view?usp=sharing