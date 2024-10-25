# student-events-application

This is a simple web application to be used by students to post and retrieve events.

## Pre-requisites 

- Have a SSH Key set up for GitHub
- Have installed
  - Java (recommend 17 Correto)
  - Maven (recommend 3.9.9)
- Optional postman

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

To run unit tests enter the following command

`mvn test -Dtest={TestClassName}`

## Future features

Add a event description field to event data
Have a guest list for events so students can sign up to events and there could be a max number of attendees
Could there be a contact supplier option