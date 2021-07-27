FROM amazoncorretto:11-alpine-jdk
MAINTAINER Marcio Franklin
COPY target/proposta-0.5.0.jar proposta-0.5.0.jar
ENTRYPOINT ["java","-jar","/proposta-0.5.0.jar"]