FROM eclipse-temurin:17-jdk-alpine
VOLUME /tmp
COPY target/*.jar app.jar
ENTRYPOINT ["java","-jar","-Dspring.profiles.active=tuot","-Duser.timezone=Europe/Helsinki","/app.jar"]
