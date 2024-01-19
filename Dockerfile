FROM maven:3-eclipse-temurin-21-alpine AS build
COPY . .
RUN mvn clean package -DskipTests

FROM openjdk:21-ea-14-jdk-slim
COPY --from=build /target/resume-0.0.1-SNAPSHOT.jar resume.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "resume.jar"]

