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

```
src/
├─ main/
│ ├─ java/com/herobattle/
│ │ ├─ configuration/ # Конфигурации приложения
│ │ ├─ controller/ # REST контроллеры
│ │ │ ├─ dto/ # DTO
│ │ │ └─ request/ # Request объекты
│ │ ├─ exception/ # Кастомные исключения
│ │ ├─ mapper/ # MapStruct мапперы
│ │ ├─ repository/ # Репозитории и сущности
│ │ └─ service/ # Бизнес-логика, модели, persistence
│ └─ resources/
│   ├─ application.yaml
│   └─ db/changelog/ # Миграции базы данных
└─ test/
├─ java/com/herobattle/ # Unit и интеграционные тесты
│ ├─ integration/
│ └─ service/
└─ resources/
```

---

## Сборка и запуск

### Локально

```bash
./gradlew build
./gradlew bootRun
```

### С Docker

```bash
docker-compose up -d
```
- Контейнер поднимает PostgreSQL, Redis и StoryTeller

---

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
