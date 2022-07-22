FROM openjdk:17-alpine
ARG JAR_FILE=build/libs/sticker-maker-0.0.1.jar
COPY ${JAR_FILE} sticker-maker-0.0.1.jar
ENTRYPOINT ["java", "-jar", "/sticker-maker-0.0.1.jar"]