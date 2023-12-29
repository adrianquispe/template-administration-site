FROM maven:3.8.5-openjdk-17-slim AS MAVEN_BUILD

RUN mkdir -p /t3-s2-socios/proyecto/dominio
WORKDIR /t3-s2-socios/proyecto/dominio

COPY ./pom.xml /t3-s2-socios/proyecto/dominio
COPY ./src /t3-s2-socios/proyecto/dominio/src

RUN mvn package -DskipTests -e

FROM openjdk:17-jdk-slim-bullseye

WORKDIR /app

COPY --from=MAVEN_BUILD /t3-s2-socios/proyecto/dominio/target/proyecto-0.0.1-SNAPSHOT.jar /app/

EXPOSE 3000

ENTRYPOINT ["java", "-jar", "proyecto-0.0.1-SNAPSHOT.jar"]