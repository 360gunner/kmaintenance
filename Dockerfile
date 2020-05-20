FROM openjdk:8-jdk-alpine
EXPOSE 9001
ADD target/Maintenance-0.0.1-SNAPSHOT.jar /Maintenance-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar", "Maintenance-0.0.1-SNAPSHOT.jar"]