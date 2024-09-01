FROM maven:3.6.0-jdk-11-slim AS build
COPY src /home/app/src
COPY pom.xml /home/app
RUN mvn -f /home/app/pom.xml clean package -DskipTests=true

FROM mcr.microsoft.com/openjdk/jdk:17-ubuntu
LABEL maintainer="phuongluong106@gmail.com"
RUN mkdir /usr/app && mkdir /usr/app/tmp
WORKDIR /usr/app

COPY --from=build /home/app/target/*.jar /usr/app/order-service.jar

EXPOSE 8080
ENTRYPOINT  [ "sh", "-c", "java -jar /usr/app/order-service.jar"]