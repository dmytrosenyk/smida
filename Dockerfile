FROM amazoncorretto:11

ARG JAR_FILE=target/*.jar

COPY ${JAR_FILE} application.jar

EXPOSE 8080

ENTRYPOINT ["java", "-Xmx2048M", "-jar", "/application.jar"]
