FROM maven:3.8.3-openjdk-17 AS build

COPY pom.xml .
RUN mvn dependency:go-offline -B
COPY src ./src
RUN mvn clean package -DskipTests


FROM openjdk:17.0.2-jdk-slim-bullseye
WORKDIR /app
COPY --from=build /target/*.jar app.jar

EXPOSE 7777

ENV JAVA_OPTS=""
ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar app.jar"]
