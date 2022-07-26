FROM openjdk:17-alpine
COPY build/libs/sticker-maker-0.0.1.jar sticker-maker-0.0.1.jar
ENTRYPOINT ["java", "-jar", "/sticker-maker-0.0.1.jar"]
