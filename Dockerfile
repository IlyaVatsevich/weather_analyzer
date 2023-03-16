FROM openjdk:17-oracle
MAINTAINER IV
COPY target/weather_analyzer-1.0.0.jar weather_analyzer-1.0.0.jar
ENTRYPOINT ["java","-jar","/weather_analyzer-1.0.0.jar"]