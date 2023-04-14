FROM openjdk:17-oracle
COPY target/thermo-backend-1.0.0.jar thermo-backend-1.0.0.jar
ENTRYPOINT ["java","-jar","/thermo-backend-1.0.0.jar"]