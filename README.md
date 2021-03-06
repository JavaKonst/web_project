## Описание:
Проект представляет собой работу веб-клиента с веб-сервисом: модуль web-service, модуль web-client.

Приложение развернуто на сервере: http://reporook.uitk.ru/web_client-1.0/

## Установка:
- В файлах модуля web-client "/index.html", "/services/host.js", "/view/main_window.html" нужно изменить ссылки, в зависимости от цели - или отладка, или деплойд (см. комментарии в файлах).
- Для деплойда, в проекте нужно сделать два war-файла (maven -> web_service/web_client -> package), которые нужно скопировать в папку webapps в установочной папке Tomcat'а. Через некоторое время в папке webapps должны появиться папки
"web-client-1.0" и "web-service-1.0". После этого нужно перейти в браузере по адресу "http://localhost:8080/web-client/" (порт обычно 8080, но может быть другой).

## Работа с приложением:
После перехода по адресу (см. выше) появится простой интерфейс из трех кнопок, нажимая на которые можно работать (удалять, изменять, создавать) со списками отделов и сотрудников компании.
Для списка сотрудников реализован поиск. Сотрудников можно искать по дню рождения или по диапазону дат. При запуске, база сама наполняется случайными сотрудниками и отделами, можно
подкорректировать количество (см. метод run() в классе WebServiceApplication.class модуля web_service).

## Реализация:
### Модуль web-client
Структура модуля
- model
- - main_model.js (бизнес-логика интерфейса)
- services
- - controller.js (функции, работающие с запросами)
- - host.js (функции получения ссылок)
- view
- - main_window.html (интерфейс приложения)
- - style.css (стили приложения)
- index.html (начальная страница, автопереход на основную страницу интерфейса)

### Модуль web-service
Структура модуля
- dao (классы предоставляющие доступ к БД и обеспечивающие основными функциями)
- entity (сущности-таблицы БД)
- services (класс, работающий на запросы)
- application.properties (настройки БД, используется встроенная-временная база данных H2)
