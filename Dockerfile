FROM maven:3.9.9-eclipse-temurin-24-alpine AS builder

WORKDIR /app

COPY pom.xml .
COPY src ./src

RUN mvn clean package -DskipTests

FROM jetty:12-jdk24-alpine

WORKDIR /var/lib/jetty

COPY --from=builder /app/target/pingpal-1.0.war webapps/root.war

EXPOSE 8080