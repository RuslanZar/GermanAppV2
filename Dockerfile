# Используем базовый образ Maven
FROM jelastic/maven:3.9.5-openjdk-21

# Устанавливаем рабочую директорию
WORKDIR /jar/

# Копируем файлы проекта
COPY pom.xml .
COPY src /root/security/src

# Собираем проект
RUN mvn clean package

# Используем базовый образ для запуска Java
FROM azul/zulu-openjdk:21

# Копируем собранный JAR-файл
#COPY --from=build /root/security/target/ger-2.jar /jar/ger-2.jar

# Открываем порт для приложения
EXPOSE 8080

# Команда для запуска приложения
ENTRYPOINT ["java", "-jar", "./ger-2.jar"]