
# REST API example application


## Install

    npm install

## Run the app

    mvn spring-boot:run


# REST API

The REST API to the example app is described below.

## Get todo List of specific user (user id = {1,10} )

### Request

`GET /todo/{userid}`

    curl -i -H 'Accept: application/json' http://localhost:8080/todo/1


# UI

## Home Page

### http://localhost:8080/home

## All users tasks 

### http://localhost:8080/tasks


© 2020 GitHub, Inc.
Terms
Privacy
Security
Status
Help
Contact GitHub
Pricing
API
Training
Blog
About
