# Sprint_4 — Автотесты на Учебный стенд Яндекс.Самокат

UI-тесты на выпадающий список вопросов и форму заказа самоката
Сайт: https://qa-scooter.praktikum-services.ru

## Покрытые сценарии

1. **Выпадающий список «Вопросы о важном»**  
   - Проверка открытия всех 8 панелей  
   - Сравнение ожидаемого текста с фактическим

2. **Позитивный сценарий оформления заказа**  
   - Две точки входа: кнопка «Заказать» в хедере и внизу страницы  
   - Заполнение всех полей (имя, фамилия, адрес, метро, телефон, дата, срок аренды, цвет, комментарий)  
   - Подтверждение заказа (кнопка «Да»)  
   - Появление модального окна успеха («Заказ оформлен»)

## Стек

- Java 11  
- JUnit 4.13.2  
- Selenium Java 4.41.0  
- WebDriverManager 6.3.3 (автоматическая установка драйверов)  
- Page Object Model  
- Параметризация тестов (JUnit Parameterized Runner)  
- Браузер: Chrome

## Структура проекта

Sprint_4
├── pom.xml
├── src
│   └── test
│       └── java
│           ├── pom
│           │   ├── HomePage.java
│           │   └── OrderPage.java
│           └── tests
│               ├── OrderAScooter.java
│               └── QuestionsPanelTextTest.java
└── README.md

## Как запустить тесты

### Через Maven (рекомендуется)

```bash
# Все тесты
mvn clean test

# Только аккордеон
mvn clean test -Dtest=QuestionsPanelTextTest

# Только заказ самоката
mvn clean test -Dtest=OrderAScooter