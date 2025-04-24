FROM openjdk:17-jdk-slim

# Устанавливаем рабочую директорию
WORKDIR /app

# Копируем JAR-файл приложения
COPY target/SmartHomeSystem-0.0.1-SNAPSHOT.jar app.jar

# Открываем порт приложения (9070) и порт для отладки (5005)
EXPOSE 9070
EXPOSE 5005

# Запускаем приложение с параметрами отладки
CMD ["java", "-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=*:5005", "-jar", "app.jar"]