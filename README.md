# MostViewedNYTimesArticles
## About
A simple app to hit the NY Times Most Popular Articles API and show a list of articles, that shows details when items on the list are tapped (a typical master/detail app).


#Installation
Requirement:
* Any Operating System (i.e MacOS X, Linux, Windows)
* An IDE (Android Studio) - [download](https://developer.android.com/studio)

* Most Popular API from [nytimes developer](https://developer.nytimes.com/apis) have been used.

* For example: https://api.nytimes.com/svc/mostpopular/v2/mostviewed/all-sections/7.json?api-key=yourkey

* Where your_key has to be generated following [get-started](https://developer.nytimes.com/get-started) page from nytimes.

* Clone the repo

* make changes to build:gradle (:app) by changing the `API_KEY` to your own API_KEY in buildTypes (buildConfigField)

