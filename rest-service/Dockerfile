FROM gradle:7.4.2-jdk17-alpine as builder
USER root
WORKDIR /builder
ADD . /builder
RUN gradle build --stacktrace

FROM openjdk:17.0.2-jdk
RUN groupadd -r spring && useradd -r spring -g spring
USER spring:spring
WORKDIR /app
EXPOSE 8080
COPY --from=builder /builder/build/libs/*.jar .
ENTRYPOINT ["java","-jar","/app.jar"]
