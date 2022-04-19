FROM openjdk:17.0.2-jdk
RUN groupadd -r spring && useradd -r spring -g spring
USER spring:spring
ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]