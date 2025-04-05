FROM openjdk:23-ea-1-jdk-slim

WORKDIR /app

COPY target/itau-desafio-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8080

CMD ["java", "-jar", "itau_desafio-0.0.1-SNAPSHOT.jar"]

LABEL authors="Sandro Caputo"
