# Use an official OpenJDK runtime as a parent image
FROM openjdk:17-jdk-slim

# Set the working directory in the container
WORKDIR /app

# Install curl to download the JAR file
RUN apt-get update && apt-get install -y curl

# Download the JAR file from Nexus (download during Docker build)
ARG NEXUS_URL=http://192.168.33.10:8082/repository/maven-snapshots/com/hend/EquipeMicroservice/0.0.1-SNAPSHOT/EquipeMicroservice-0.0.1-SNAPSHOT.jar
ARG NEXUS_USERNAME
ARG NEXUS_PASSWORD
RUN curl -u ${NEXUS_USERNAME}:${NEXUS_PASSWORD} -o app.jar ${NEXUS_URL}

# RUN --mount=type=secret,id=nexus_creds \
  #  curl -u $(cat /run/secrets/nexus_creds) -o app.jar ${NEXUS_URL}

# Expose the port the app runs on (adjust if needed)
EXPOSE 8084

# Command to run the application
CMD ["java", "-jar", "app.jar"]
