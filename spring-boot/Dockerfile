FROM gradle
COPY . .
RUN gradle build
ENTRYPOINT ["java", "-jar", "build/libs/spring-boot-0.0.1-SNAPSHOT.jar"]
