# Этап сборки
FROM maven:3.9.5-openjdk-21 AS build

# Устанавливаем рабочую директорию
WORKDIR /app

# Копируем файлы проекта
COPY pom.xml .
COPY src /app/src

# Сборка проекта
RUN mvn clean package -DskipTests

# Финальный этап
FROM azul/zulu-openjdk:21

# Устанавливаем рабочую директорию
WORKDIR /jar

# Копируем JAR файл из предыдущего этапа
COPY --from=build /app/target/ger-2.jar /jar/ger-2.jar

# Открываем порт
EXPOSE 8080

# Указываем команду для запуска JAR файла
ENTRYPOINT ["java", "-jar", "ger-2.jar"]
