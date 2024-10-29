# Use the official OpenJDK image as the base image
FROM openjdk:17-jdk-slim

# Set the working directory inside the container
WORKDIR /app

# Copy the Maven build file and the source code into the container
COPY pom.xml .
COPY src ./src

# Install Maven (optional: only if you're not using a pre-built JAR)
RUN apt-get update && \
    apt-get install -y maven && \
    mvn clean package -DskipTests

# Copy the built JAR file to the working directory
# Make sure to replace `your-app.jar` with the actual name of your JAR file
COPY target/ConfigServer-0.0.1-SNAPSHOT.jar config-server.jar

# Expose the port the application runs on
EXPOSE 8887

# Command to run the application
ENTRYPOINT ["java", "-jar", "config-server.jar"]
