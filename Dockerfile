FROM openjdk:8-jdk-slim

RUN apt-get update && apt-get install -y curl && rm -rf /var/lib/apt/lists/*

RUN curl -o app.jar -u admin:admin "http://localhost:8081/repository/maven-releases/tn/esprit/spring/kaddem/0.0.1/kaddem-0.0.1.jar"

EXPOSE 8080

CMD ["java", "-jar", "app.jar"]
