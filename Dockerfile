FROM openjdk:17-alpine
WORKDIR /app
COPY --from=build app/build/libs/sticker-maker-0.0.1.jar sticker-maker-0.0.1.jar
ENTRYPOINT ["java", "-jar", "/sticker-maker-0.0.1.jar"]
