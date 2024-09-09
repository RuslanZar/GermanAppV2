FROM zulu-21
ARG JAR_FILE=target/*.jar
COPY ./target/german-2.0.*.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]