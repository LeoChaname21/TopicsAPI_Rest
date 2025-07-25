FROM maven:3.9.4-eclipse-temurin-17 AS build

WORKDIR /app

COPY pom.xml .

COPY src ./src

RUN mvn clean package -DskipTests

FROM mcr.microsoft.com/openjdk/jdk:17-ubuntu as base

COPY --from=build /app/target/*.jar /app.jar

EXPOSE 8081

ENV TZ=America/Lima

ENV SPRING_PROFILES_ACTIVE=prod

ENTRYPOINT ["java","-jar","/app.jar"]