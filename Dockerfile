FROM maven:3.6.3-openjdk-11 AS builder
WORKDIR /code
COPY pom.xml .

RUN mvn dependency:resolve
COPY src ./src
RUN ["mvn", "package"]

FROM adoptopenjdk/openjdk11:alpine-jre
COPY ./target/hapi-fhir-authentication-0.0.1-SNAPSHOT.jar /opt/hapi-fhir/
WORKDIR /opt/hapi-fhir/

EXPOSE 8084

CMD ["java", "-jar", "hapi-fhir-authentication-0.0.1-SNAPSHOT.jar"]
