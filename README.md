# Winecellar ![Github Actions Status](https://github.com/My-Wine-Cellar/winecellar-webapp/workflows/CI/badge.svg)

## Table of contents
* [General info](#general-info)
* [Technologies](#technologies)
* [Docker](#docker)
* [Project Setup](#project-setup)
* [Contribution](#contribution)
* [Status](#status)
* [Contact](#contact)

## General info
Webapp for keeping track of wines, their tasting notes, and reviews. Goal is to be the premier open-source winecellar application.

## Technologies
* Java 8+
* Spring Boot
* Spring Security
* Gradle (v4.10)
* Project Lombok
* Bootstrap
* Thymeleaf
* Hibernate
* Docker
* PostgreSQL (v9.4 or higher is required)

## PostgreSQL

You will need to have either

* [Docker Engine](https://docs.docker.com/install/)
* [podman](https://podman.io/)
* Bare metal [PostgreSQL](https://www.postgresql.org/) instance

installed before proceeding

*Docker*
```
$ docker run --name winecellar -p 5432:5432 -d -e POSTGRES_USER=winecellar -e POSTGRES_PASSWORD=winecellar -e POSTGRES_DB=winecellar postgres
```

*podman*
```
$ podman run --name winecellar -p 5432:5432 -d -e POSTGRES_USER=winecellar -e POSTGRES_PASSWORD=winecellar -e POSTGRES_DB=winecellar postgres
```

This will pull down the latest PostgreSQL image and run the container with all necessary Spring Boot properties for getting a connection. 

## Project Setup

```
$ git clone https://github.com/pauldpearson/winecellar-webapp
$ cd winecellar-webapp
$ ./gradlew bootRun
```

Access here: http://localhost:8080/

| Account | Type  | Password |
| ------- | ----- | -------- |
| wineuser1   | user  | password |
| wineuser2   | user  | password |
| wineadmin   | admin | password |

## Contribution

Feel free!  There are issues and a project board.  Contact info is below.

## License

[![License](https://img.shields.io/badge/License-EPL%202.0-orange.svg)](https://www.eclipse.org/legal/epl-2.0/)
Winecellar is released under Eclipse Public License version 2.0

## Contact

Created by [Paul Pearson](mailto:paul.darlington.pearson@gmail.com) & Jesper Pedersen
