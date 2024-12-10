# Use a lightweight OpenJDK image based on Alpine Linux
FROM openjdk:17-jdk-alpine

# Expose the port that the Spring Boot application will be available on
EXPOSE 8089

# Copy the generated .jar file to the container (use wildcard to match any .jar in target directory)
COPY target/*.jar /app.jar

# Set the entry point to run the Spring Boot application with Java
ENTRYPOINT ["java", "-jar", "/app.jar"]
