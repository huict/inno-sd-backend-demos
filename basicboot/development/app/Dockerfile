FROM ubuntu as builder
RUN ["apt-get", "update", "-y"]
RUN ["apt-get", "install", "-y", "openjdk-21-jdk", "maven", "build-essential"]
ADD . "/src"
WORKDIR "/src"
RUN ["mvn", "clean", "package"]

FROM amazoncorretto:21.0.3
WORKDIR /app
COPY --from=builder /src/target/basicboot.jar ./basicboot.jar
ENTRYPOINT ["java", "-jar", "./basicboot.jar"]
EXPOSE 8080