# Utiliser une image légère OpenJDK basée sur Alpine Linux
FROM openjdk:17-jdk-alpine

# Exposer le port sur lequel l'application Spring Boot sera disponible
EXPOSE 8089

# Ajouter votre fichier JAR généré dans le conteneur
COPY target/eventsProject-1.O.O.jar /eventsProject-1.O.O.jar

# Définir le point d'entrée pour exécuter l'application avec Java
ENTRYPOINT ["java", "-jar", "/eventsProject-1.O.O.jar"]