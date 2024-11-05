# student-events-application

This is a simple web application to be used by students to post and retrieve events.

## Pre-requisites 

- Have a SSH Key set up for GitHub
- Have installed
  - Java (recommend 17 Corretto) - https://docs.aws.amazon.com/corretto/latest/corretto-17-ug/what-is-corretto-17.html
  - Maven (recommend 3.9.9) - https://maven.apache.org/install.html
- Optional postman

## Setup

### 1. Clone the repo
- Create a branch following the naming convention `{team-name}-main`

### 2. Run the application
In terminal use command:

To do a clean install:
`mvn clean`

To run the application:
`mvn spring-boot:run`

### 3. Test the endpoints
Use curl commands to hit the existing endpoints
Open a terminal and enter the following command for GET

`curl -X GET http://localhost:8080/events/find-all`

or for POST

`curl -X POST http://localhost:8080/events/create`
(don't forget to add a request body)

or can use Postman

To run unit tests enter the following command

`mvn test -Dtest=EventsControllerTest`

## Tasks

Before starting a task, create a branch off your teams main branch.
Naming of branch should follow:
`{team-name}/{task-name}`.
Once completed the task, create a pull request and ask one of the presenters to review it.
Please ensure the pull request has been approved and merged into your team's main branch, before moving on to your next task.

### Get the EventsControllerTests to all pass
The unit tests are failing. You need to investigate why by looking at the application code and
implement the code required to pass the unit tests. The unit test code should remain unchanged
apart from the one unit tests that has an incomplete test body.

### Add a feature

Here is some options of features you could add...
- Add a event description field to event data
- Delete an event
- Could there be a contact supplier option
- Add attendees list to an event so students can sign up to it and there could be a max number of
    attendees

(Feel free to come up with your own feature)
