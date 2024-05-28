# Use the official OpenJDK 17 image as a base
FROM openjdk:17-jdk-slim

# Set the working directory in the container
WORKDIR /app

# Copy the built JAR file into the container
COPY ./target/*.jar app.jar

# Expose the port that your Spring Boot application listens on
EXPOSE ${PORT}

# Command to run the Spring Boot application when the container starts
CMD ["java", "-jar", "app.jar"]
