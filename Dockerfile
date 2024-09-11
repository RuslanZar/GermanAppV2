# Используем базовый образ Maven
FROM maven:3.8.4-openjdk-17 AS build

# Устанавливаем рабочую директорию
WORKDIR /app

# Копируем файлы проекта
COPY pom.xml .
COPY src /app/src

# Собираем проект
RUN mvn clean package

# Используем базовый образ для запуска Java
FROM azul/zulu-openjdk:21

# Копируем собранный JAR-файл
COPY --from=build /app/target/my-app.jar /app/my-app.jar

# Открываем порт для приложения
EXPOSE 8080

# Команда для запуска приложения
CMD ["java", "-jar", "my-app.jar"]