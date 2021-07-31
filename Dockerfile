FROM amazoncorretto:11-alpine-jdk
MAINTAINER Marcio Franklin
COPY target/proposta-0.9.5.jar proposta-0.9.5.jar
ENTRYPOINT ["java","-jar","/proposta-0.9.5.jar"]