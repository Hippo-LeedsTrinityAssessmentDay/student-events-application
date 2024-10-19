# student-events-application

This is a simple web application to return and post events.

## Pre-requisites 

## How to run the application
In terminal use command:

To do a clean install:
`mvn clean`

To run the application:
`mvn spring-boot:run`


## How to test the endpoints
To use curl commands to hit the existing endpoints
Open a terminal and enter the following command for GET

`curl -X GET http://localhost:8080/test`

or for POST

`curl -X POST http://localhost:8080/test`

To run unit tests enter the floowing command

`mvn test -Dtest={TestClassName}`

## Future features

Given a student wants to filter events When looking for events Then they have a more refined event list
Given a student When buys a ticket for a specific event Then they are on the guest list
Given a student When they want to know more information about a specific event Then they can contact the event supplier
