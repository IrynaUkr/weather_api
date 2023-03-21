# weather_api
weather api project for tech returners 2023
## drawio - uml
https://app.diagrams.net/#Hweatherapiproject2023%2Fweather_api%2Fmain%2Fweather_api_get_request.drawio

## Update (Anthony)
21Mar(Tue)

Added repository interface and class. Added a table AdviceRule in Model class, to persist rules for weather advice.
There is now a method generateAdvice(weather) which loops through AdviceRule table and construct the advice.
We can run the test case in WeatherManagerServiceTests to test the getCurrent weather and generate advice. At moment, h2 database is used. Will use postgres DB.

New model classes added - Advice for the advice object, and ManagerReponse for our API response body.

When WeatherManager receives REST API get request for a city, the controller can call Service class methods to fetch weather object and advice object, to insert to ManagerResponse Object to form the ResponseEntity.
