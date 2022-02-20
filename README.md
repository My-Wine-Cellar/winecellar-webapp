# Winecellar ![Github Actions Status](https://github.com/My-Wine-Cellar/winecellar-webapp/workflows/CI/badge.svg)

## Table of contents
* [General info](#general-info)
* [Technologies](#technologies)
* [Project Setup](#project-setup)
* [Integration Tests](#integration-tests)
* [Contribution](#contribution)
* [Status](#status)
* [Contact](#contact)

## General info
Web application to keep track of wines, their tasting notes, and reviews.

The goal is to be the premier open-source wine cellar application.

## Technologies

* Java 11
* Spring Boot
* Spring Security
* Maven
* Project Lombok
* Bootstrap
* Thymeleaf
* Hibernate
* PostgreSQL (v10 or higher is required)

## Project Setup

#### Maven

Maven is needed to execute any and all build commands.

#### PostgreSQL

You will need to have either:

* [Docker Engine](https://docs.docker.com/install/)
* [podman](https://podman.io/)
* Bare metal [PostgreSQL](https://www.postgresql.org/) instance

installed before proceeding.

*Docker*
```
$ docker run --name winecellardb -p 5432:5432 -d -e POSTGRES_USER=winecellar -e POSTGRES_PASSWORD=winecellar -e POSTGRES_DB=winecellar postgres
```

*podman*
```
$ podman run --name winecellardb -p 5432:5432 -d -e POSTGRES_USER=winecellar -e POSTGRES_PASSWORD=winecellar -e POSTGRES_DB=winecellar postgres
```

This will pull down the latest PostgreSQL image and run the container with all necessary Spring Boot properties for getting a connection. 

#### Running

```
$ git clone https://github.com/My-Wine-Cellar/winecellar-webapp
$ cd winecellar-webapp
$ mvn spring-boot:run
```

Access here: http://localhost:8080/

| Account | Type  | Password |
| ------- | ----- | -------- |
| user1   | user  | password |
| user2   | user  | password |
| admin   | admin | password |

## Integration Tests

```mvn verify``` will build and run the winecellar-webapp container to connect with PostgreSQL for 
executing integration tests against our own API.

If you want to run manual tests in Postman or using curl you can
use ```mvn docker:build docker:start``` and that will launch both containers to test individual endpoints.  Use ```mvn docker:stop```
to shutdown both containers.

## Contribution

Contributions to winecellar-webapp are managed on [GitHub.com](https://github.com/My-Wine-Cellar/winecellar-webapp)

* [Ask a question](https://github.com/My-Wine-Cellar/winecellar-webapp/discussions)
* [Raise an issue](https://github.com/My-Wine-Cellar/winecellar-webapp/issues)
* [Feature request](https://github.com/My-Wine-Cellar/winecellar-webapp/issues)
* [Code submission](https://github.com/My-Wine-Cellar/winecellar-webapp/pulls)

Contributions are most welcome !

Feel free to fork the project.  There are issues and a project board.  Contact info is below.

## License

[![License](https://img.shields.io/badge/License-EPL%202.0-orange.svg)](https://www.eclipse.org/legal/epl-2.0/)
Winecellar is released under Eclipse Public License version 2.0

## Contact

Created by [Paul Pearson](mailto:paul.darlington.pearson@gmail.com) & Jesper Pedersen
