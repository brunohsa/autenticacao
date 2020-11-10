# Dockerfile
FROM openjdk:11-jre

#RUN docker network create -d bridge appetito

RUN mkdir app

EXPOSE 8081

ADD google-credentials.json /app/google-credentials.json
ENV GOOGLE_APPLICATION_CREDENTIALS="/app/google-credentials.json"

ADD autenticacao-service/target/autenticacao-service-1.0-SNAPSHOT.jar app/autenticacao.jar
ENTRYPOINT ["java","-jar", "-Dspring.profiles.active=docker", "app/autenticacao.jar"]