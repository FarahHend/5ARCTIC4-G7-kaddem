# Use an official OpenJDK runtime as a parent image
FROM openjdk:17-jdk-slim

# Set the working directory in the container
WORKDIR /app

COPY target/EquipeMicroservice-0.0.1-SNAPSHOT.jar app.jar
# RUN --mount=type=secret,id=nexus_creds \
  #  curl -u $(cat /run/secrets/nexus_creds) -o app.jar ${NEXUS_URL}

# Expose the port the app runs on (adjust if needed)
EXPOSE 8084

# Command to run the application
CMD ["java", "-jar", "app.jar"]
