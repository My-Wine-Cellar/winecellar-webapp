# Winecellar [![Build Status](https://codebuild.us-east-2.amazonaws.com/badges?uuid=eyJlbmNyeXB0ZWREYXRhIjoiQ0xsMER4WjIrL08rTForL2NFQ3hkSzhtVkNkWkQ2YkVsblhCbjlSYW53U3UvRklmajhNVm5nMWg5US9iUnQ1dmxubHdtUkd5S1dWYVIrNHNxWTF1M2VRPSIsIml2UGFyYW1ldGVyU3BlYyI6ImFlV3gzV0lGSmpTVDBhQzIiLCJtYXRlcmlhbFNldFNlcmlhbCI6MX0%3D&branch=master)](https://aws.amazon.com/console/) [![SonarQube](https://sonarcloud.io/api/project_badges/measure?project=pauldpearson_winecellar-webapp&metric=alert_status)](https://sonarcloud.io/dashboard?id=pauldpearson_winecellar-webapp)

## Table of contents
* [General info](#general-info)
* [Technologies](#technologies)
* [Docker](#docker)
* [Project Setup](#project-setup)
* [Contribution](#contribution)
* [Status](#status)
* [Contact](#contact)

## General info
> Webapp for keeping track of wines, their tasting notes, and reviews. Goal is to be the premier open-source winecellar application.

## Technologies
* Spring Boot
* Spring Security
* Gradle (v4.10)
* Project Lombok
* Bootstrap
* Thymeleaf
* Hibernate
* Docker
* PostgreSQL (v9.4 or higher is required)

## Docker

> You will need to have [Docker Engine](https://docs.docker.com/install/) installed before proceeding

```
$ docker run --name winecellar -p 5432:5432 -d -e POSTGRES_USER=winecellar -e POSTGRES_PASSWORD=winecellar -e POSTGRES_DB=winecellar postgres
```
> This will pull down the latest PostgreSQL image and run the container with all necessary Spring Boot properties for connection. 

## Project Setup
```
$ git clone https://github.com/pauldpearson/winecellar-webapp
$ cd winecellar-webapp
$ ./gradlew bootRun
```
> Access here: http://localhost:8080/

| Account | Type  | Password |
| ------- | ----- | -------- |
| user1   | user  | password |
| user2   | user  | password |
| admin   | admin | password |

## Contribution
> Feel free!  There are issues and a project board.  Contact info is below.

## Status
> *Once v2.0 is ready and in production this will be updated*

## License
[![License](https://img.shields.io/badge/License-EPL%202.0-orange.svg)](https://www.eclipse.org/legal/epl-2.0/)
> Wincellar is released under version 2.0 of the Eclipse Public License

## Contact
> Created by [Paul Pearson](mailto:paul.darlington.pearson@gmail.com) & Jesper Pedersen
