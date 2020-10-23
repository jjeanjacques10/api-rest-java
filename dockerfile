FROM openjdk:8u265-slim
COPY build/libs/produtos-boot-*.war app.war
ENTRYPOINT ["java", "-jar", "/app.war"]