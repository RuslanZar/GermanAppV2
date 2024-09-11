# Используем базовый образ Maven
FROM jelastic/maven:3.9.5-openjdk-21

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
COPY --from=build /app/target/ger-2.jar /app/my-ger-2.jar

# Открываем порт для приложения
EXPOSE 8080

# Команда для запуска приложения
CMD ["java", "-jar", "my-app.jar"]