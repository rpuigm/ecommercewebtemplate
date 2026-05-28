FROM eclipse-temurin:21-jre-alpine
WORKDIR /app
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java", "-Xms128m", "-Xmx256m", "-Dspring.jmx.enabled=false", "-jar", "app.jar"]
