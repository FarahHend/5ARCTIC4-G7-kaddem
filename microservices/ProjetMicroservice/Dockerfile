# Use an official OpenJDK runtime as a parent image
FROM openjdk:17-jdk-slim

# Set the working directory in the container
WORKDIR /app

COPY target/ProjetMicroservice-0.0.1-SNAPSHOT.jar app.jar

#RUN --mount=type=secret,id=nexus_creds \
   # curl -u $(cat /run/secrets/nexus_creds) -o app.jar ${NEXUS_URL}

# Copy the jar file from the target directory to the container
# COPY target/ProjetMicroservice-0.0.1-SNAPSHOT.jar app.jar

# Expose the port the app runs on (adjust if needed)
EXPOSE 8086

# Command to run the application
CMD ["java", "-jar", "app.jar"]
