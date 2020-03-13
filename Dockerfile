FROM openjdk:8-jre-alpine
VOLUME /tmp
COPY build/libs/*.jar app.jar
# ENTRYPOINT overrides application.properties to account for postgres container connection
ENTRYPOINT ["java", "-jar", "app.jar", "--spring.datasource.url=jdbc:postgresql://winecellar-postgres:5432/winecellar"]