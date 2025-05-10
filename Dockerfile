FROM openjdk:17-jdk-slim
WORKDIR /service
COPY build/libs/webrayze-0.0.1-SNAPSHOT.jar service.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/service/service.jar"]