FROM openjdk:17-jdk-slim


WORKDIR /app


RUN apt-get update && apt-get install -y curl


ARG NEXUS_URL=http://192.168.33.10:8082/repository/maven-snapshots/com/hend/ProjetMicroservice/0.0.1-SNAPSHOT/ProjetMicroservice-0.0.1-SNAPSHOT.jar
ARG NEXUS_USERNAME
ARG NEXUS_PASSWORD
RUN curl -u ${NEXUS_USERNAME}:${NEXUS_PASSWORD} -o app.jar ${NEXUS_URL}

#RUN --mount=type=secret,id=nexus_creds \
   # curl -u $(cat /run/secrets/nexus_creds) -o app.jar ${NEXUS_URL}

# Copy the jar file from the target directory to the container
# COPY target/ProjetMicroservice-0.0.1-SNAPSHOT.jar app.jar


EXPOSE 8086


CMD ["java", "-jar", "app.jar"]
