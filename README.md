# Weather Manager REST API Project
### 📋The Brief
Project based on a public API to meet a set of requirements as detailed below :    
> - Weather API – Get weather information for a given location  
> - Create advice to determine how the user should prepare for a day outside (i.e. clothes/sunscreen/umbrella/coat etc)  

## Table of contents
* [Acceptance criteria and assumptions](#acceptance-criteria-and-assumptions)
* [Technologies](#technologies)
* [How to run the project](#how-to-run-the-project)
* [Project Update](#project-update)
* [System Design](#system-design)

## Acceptance Criteria and Assumptions
Weather service should provide current weather conditions for a given location  
The advice message should provide the following information:  
 - Current temperature with suggested clothing for comfort and protection
 - Current wind speed with suggested precaution for driving
 - Current raining or snowing condition for using umbrella or snow precaution shoe
 - Current humidity level with advice of health precaution and comfort level

## Technologies
- java version 17
- apache-maven-3.9.0
- Spring/Spring Boot
- Swagger
  - http://localhost:8080/v3/api-docs
  - http://localhost:8080/swagger-ui.html
- Health check using Spring Boot Actuator

## How to run the project
- On the command line execute :  
  `mvn spring-boot:run`

## Project Update
21 March(Tue) Anthony

Added repository interface and class. 
Added a table AdviceRule in Model class, to persist rules for weather advice.
There is now a method generateAdvice(weather) which loops through AdviceRule table and constructs the advice.
We can run the test case in WeatherManagerServiceTests to test the getCurrent weather and generate advice. 
At moment, h2 database is used. Will use postgres DB.

New model classes added - Advice for the advice object, and ??ManagerReponse for our API response body.
When WeatherManager receives REST API get request for a city, the controller can call Service class methods to fetch weather object and advice object, 
to insert to ManagerResponse?? Object to form the ResponseEntity.* Please ignore ManagerResponse for now (Tuesday), I cannot get it working. use advice class for responseEntity is fine.

## System Design
https://app.diagrams.net/#Hweatherapiproject2023%2Fweather_api%2Fmain%2Fweather_api_get_request.drawio