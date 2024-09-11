FROM azul/zulu-openjdk:21
COPY target/ger-2.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]