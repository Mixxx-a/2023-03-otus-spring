FROM bellsoft/liberica-openjdk-alpine:19.0.2-9
COPY target/hw17-docker-*.jar /app/hw17.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app/hw17.jar"]