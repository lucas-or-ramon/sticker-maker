FROM openjdk:17-alpine
ARG JAR_FILE=build/libs/movie-sticker-0.0.1.jar
COPY ${JAR_FILE} movie-sticker-0.0.1.jar
ENTRYPOINT ["java", "-jar", "/movie-sticker-0.0.1.jar"]