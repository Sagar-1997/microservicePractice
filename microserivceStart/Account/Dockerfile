#start with base image containing Java runtime
FROM openjdk:17-jdk-slim

#Information arounf who is managing image
MAINTAINER microservie.com

#add appliation jar into image
COPY target/Account-0.0.1-SNAPSHOT.jar Account-0.0.1-SNAPSHOT.jar

#execute the command when image is run
ENTRYPOINT [ "java","-jar","Account-0.0.1-SNAPSHOT.jar" ]