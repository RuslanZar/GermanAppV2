#!/bin/bash

# Путь к директории с вашим проектом
PROJECT_DIR="/root/security"

# Имя вашего JAR файла
JAR_NAME="ger-2.jar"

# Переходим в директорию с проектом
cd $PROJECT_DIR

# Останавливаем старый процесс (если приложение уже запущено)
# Предполагается, что ваше приложение запущено в фоновом режиме с помощью nohup или другого менеджера процессов
echo "Остановка старого процесса..."
pkill -f $JAR_NAME

# Обновляем код до последней версии main ветки
echo "Обновление репозитория..."
git fetch origin
git reset --hard origin/master
git pull origin master

# Сборка проекта и создание JAR файла
echo "Сборка проекта..."
mvn clean package -DskipTests  # Используем Maven Wrapper для сборки. Если у вас Gradle, используйте ./gradlew build

# Проверяем, что сборка прошла успешно и JAR файл создан
if [ ! -f target/$JAR_NAME ]; then
  echo "Ошибка: JAR файл не создан. Проверьте вывод Maven."
  exit 1
fi

# Запускаем новое приложение
echo "Запуск нового JAR файла..."
nohup java -jar target/$JAR_NAME > app.log 2>&1 &

echo "Деплой завершён успешно."