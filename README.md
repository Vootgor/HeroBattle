# HeroBattle

HeroBattle — альфа-версия веб-приложения для симуляции боёв героев с врагами через REST API.
Реализованы базовые функции создания героев и врагов, проведение боёв и логирование действий с
комментариями рассказчика.

## Основные возможности

- Создание, получение и удаление героев и врагов через REST API
- Проведение боёв с генерацией случайного числа врагов
- Логирование действий героев и врагов
- Комментарии рассказчика к бою
- REST API с кастомными исключениями для ошибок

---

## Инфраструктура

- PostgreSQL для хранения данных
- Redis для кэширования
- Liquibase для миграций базы данных
- Gradle для сборки проекта
- Docker для контейнеризации

---

## Структура проекта

Проект состоит из двух модулей:

- **app** — основной модуль, в котором создаются герои, враги и происходят битвы.
- **storyTeller** — статический модуль, отвечающий за тексты. В нем ничего не выполняется, он лишь предоставляет описание событий.

---

## Сборка и запуск

1. Поднять базу данных и Redis через Docker Compose:  
   `docker compose up -d`

2. Запустить модуль **storyTeller** (Spring Boot):  
   `./gradlew :storyTeller:bootRun`

3. После успешного запуска **storyTeller** запустить модуль **app** (Spring Boot):  
   `./gradlew :app:bootRun`

## Технологии

- Java 21
- Spring Boot 3.4.3
- PostgreSQL 16
- Liquibase
- MapStruct
- Springdoc OpenAPI (Swagger UI)
- JUnit 5, Testcontainers
- Redis 7 (кэширование)

---

## Тесты

- Unit тесты: src/test/java/com/herobattle/service
- Integration тесты: src/test/java/com/herobattle/integration

***Запуск***

```bash
./gradlew test
```

---

## Миграции базы данных

- Используется Liquibase через YAML миграции: src/main/resources/db/changelog/
- Примеры: 001-create-heroes.yaml, 002-create-enemies.yaml
