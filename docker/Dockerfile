FROM gradle:7.2-jdk11-alpine AS build
COPY --chown=gradle:gradle . /src
WORKDIR /src
RUN gradle build --no-daemon -x test

FROM openjdk:11-jre-slim
EXPOSE 8080
RUN mkdir /app
COPY --from=build /src/build/libs/*.jar /app/assembleia.jar
ENTRYPOINT ["java", "-jar","/app/assembleia.jar"]