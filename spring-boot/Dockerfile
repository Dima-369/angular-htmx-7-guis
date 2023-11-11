# FROM gradle:8.4-jdk17
FROM gradle
COPY . .
# EXPOSE 8083
RUN gradle clean
RUN gradle build
# RUN ./gradlew build
# RUN ./gradlew bootRun
ENTRYPOINT ["gradle", "bootRun"]
