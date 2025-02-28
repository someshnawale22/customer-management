# Use a base image with Java runtime
FROM adoptopenjdk:11-jre-hotspot


# Set the working directory inside the container
WORKDIR /app

# Copy the executable JAR file to the container
COPY target/manage-0.0.1-SNAPSHOT.jar app.jar

# Expose the port the application runs on
EXPOSE 8080

# Define the command to run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
