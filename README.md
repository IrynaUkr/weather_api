# weather_api
weather api project for tech returners 2023
## drawio - uml
https://app.diagrams.net/#Hweatherapiproject2023%2Fweather_api%2Fmain%2Fweather_api_get_request.drawio

## Update (Anthony)
21Mar(Tue)
Added repository interface and class. Added a table Advice in Model class, to persist rules for weather advice.
There is now a method getAdviceForCurrent(city) which loops through Advice table and construct the advice based on rule checking on weather. This method will call Irina's open API codes which returns the weather object.
We can run the test case in WeatherManagerServiceTests to test the getAdviceForCurrent()method. At moment, h2 database is used; the test case thus creates a list of rules for the getAdvice method to check. Later on, I will test using rules persisted in postgres database.
