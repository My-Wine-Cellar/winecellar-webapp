# Winecellar [![Build Status](https://codebuild.us-east-2.amazonaws.com/badges?uuid=eyJlbmNyeXB0ZWREYXRhIjoiQ0xsMER4WjIrL08rTForL2NFQ3hkSzhtVkNkWkQ2YkVsblhCbjlSYW53U3UvRklmajhNVm5nMWg5US9iUnQ1dmxubHdtUkd5S1dWYVIrNHNxWTF1M2VRPSIsIml2UGFyYW1ldGVyU3BlYyI6ImFlV3gzV0lGSmpTVDBhQzIiLCJtYXRlcmlhbFNldFNlcmlhbCI6MX0%3D&branch=master)](https://aws.amazon.com/console/) [![SonarQube](https://sonarcloud.io/api/project_badges/measure?project=pauldpearson_winecellar-webapp&metric=alert_status)](https://sonarcloud.io/dashboard?id=pauldpearson_winecellar-webapp)

## Table of contents
* [General info](#general-info)
* [Technologies](#technologies)
* [Setup](#setup)
* [Docker setup](#docker-setup)
* [Contribution](#contribution)
* [Status](#status)
* [Contact](#contact)

## General info
> This is my first real project built and deployed as a means to learn and grow as a junior developer.
> All development is run on MacBook Pro (Retina, 13-inch, Early 2015) macOS High Sierra

## Technologies
* Spring Boot
* Spring Security
* Gradle
* Project Lombok
* Bootstrap
* Thymeleaf
* Hibernate
* Docker
* PostgreSQL
* AWS
* Linux

## Setup
```
$ git clone https://github.com/pauldpearson/winecellar-webapp.git
$ cd winecellarwebapp
$ ./gradlew bootRun
```
> Access here: http://localhost:8080/ <br/> You can then login as user1, user2, or admin; both use password for...password

## Docker setup

```
$ docker run --name postgres-dev -p 5432:5432 -d -e POSTGRES_USER=winecellar -e POSTGRES_PASSWORD=winecellar -e POSTGRES_DB=winecellar postgres
```
> This has been the simplest way I found to work on my Mac for local development and the application.properties file will reflect the connection.

## Contribution
> Feel free!  I'm trying to setup issues and project boards as best I can as it helps me remain focused when I'm working.  Contact info is below.

## Status
> *Project is deployed on AWS EC2 RHEL-7.6 AMI here:*
[My Wine Cellar](http://www.mywinecellar.info)

## License 
Wincellar is released under version 2.0 of the Apache License  
[![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](https://opensource.org/licenses/Apache-2.0)

## Contact
> Created by [Paul Pearson](mailto:paul.darlington.pearson@gmail.com) - feel free to contact me!
