# Winecellar ![Github Actions Status](https://github.com/My-Wine-Cellar/winecellar-webapp/workflows/CI/badge.svg)

Web application to keep track of wines, their tasting notes, and reviews.

The goal is to be the premier open-source wine cellar application.

## Technologies

* [Java 17](https://www.oracle.com/java/technologies/)
* [Apache Maven](https://maven.apache.org/)
* [Spring Boot](https://spring.io/projects/spring-boot)
* [Spring Security](https://spring.io/projects/spring-security)
* [Thymeleaf](https://www.thymeleaf.org/)
* [PostgreSQL 11+](https://www.postgresql.org/)

## Prerequisites

[Java](https://www.oracle.com/java/technologies/) and [Apache Maven](https://maven.apache.org/) are needed to execute any of the build commands.

[git](https://git-scm.com/) is needed to control the source code repository. You can learn more about [git](https://git-scm.com/) in the
[Pro Git](https://git-scm.com/book/en/v2) book.

### PostgreSQL

You will need to have either:

* [Docker Engine](https://docs.docker.com/install/)
* [podman](https://podman.io/)
* Bare metal [PostgreSQL](https://www.postgresql.org/) instance

installed before proceeding.

*Docker*
```
docker run --name winecellardb -p 5432:5432 -d -e POSTGRES_USER=winecellar -e POSTGRES_PASSWORD=winecellar -e POSTGRES_DB=winecellar postgres
```

*podman*
```
podman run --name winecellardb -p 5432:5432 -d -e POSTGRES_USER=winecellar -e POSTGRES_PASSWORD=winecellar -e POSTGRES_DB=winecellar postgres
```

This will pull down the latest PostgreSQL image and run the container with all necessary Spring Boot properties for getting a connection. 

## Running

```
$ git clone https://github.com/My-Wine-Cellar/winecellar-webapp
$ cd winecellar-webapp
$ mvn spring-boot:run
```

Access here: http://localhost:8080/

| Account   | Password   | Type  |
| --------- | ---------- | ----- |
| `user1`   | `password` | user  |
| `user2`   | `password` | user  |
| `admin`   | `password` | admin |

## Contribution

Contributions to winecellar-webapp are managed on [GitHub.com](https://github.com/My-Wine-Cellar/winecellar-webapp)

* [Ask a question](https://github.com/My-Wine-Cellar/winecellar-webapp/discussions)
* [Raise an issue](https://github.com/My-Wine-Cellar/winecellar-webapp/issues)
* [Feature request](https://github.com/My-Wine-Cellar/winecellar-webapp/issues)
* [Code submission](https://github.com/My-Wine-Cellar/winecellar-webapp/pulls)

Contributions are most welcome !

## License

[![License](https://img.shields.io/badge/License-EPL%202.0-orange.svg)](https://www.eclipse.org/legal/epl-2.0/)
Winecellar is released under Eclipse Public License version 2.0

## Contact

Created by [Paul Pearson](mailto:paul.darlington.pearson@gmail.com) & Jesper Pedersen
