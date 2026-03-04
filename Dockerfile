FROM eclipse-temurin:17-jdk-alpine
WORKDIR /app
# Xüsusi olaraq əsas jar faylını kopyalayırıq
COPY build/libs/SSP-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]

# bura otaqdi, burda jar faylini qoy ve ise sal