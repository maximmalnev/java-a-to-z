# Финальные проекты курса "Java A до Я".
Целью данных проектов является демонстрация навыков разработки на языке Java.

Данные проекты были закончены в ноябре 2016 года и перезалиты в июле 2017.
***
### Темы:
* Базовый синтаксис языка Java
* ООП (Инкапсуляция, Наследование, Полиморфизм)
* Система ввода/вывода, IO, Socket
* ООД (SOLID)
* Collections (List, Set, Map, Tree, Queue)
* Garbage Collection (Виды GC, типы ссылок)
* Multithreading 
* SQL, JDBC
* Java EE (Serlvet, JSP, JSTL)
* Hibernate ORM (Mapping, HQL, тестирование с помощью HSQLDB)
* Spring Framework (IoC, MVC, Data, Security)

***

### Проекты:

#### 1) [Поиск файла в файловой системе](https://github.com/vladimirrepository/java-a-to-z/tree/master/finder)
Приложение позволяет осуществлять поиск файла в файловой системе пользователя по:
* точному имени файла
* маске
* регулярному выражению

Приложение конфигурируется через параметры при запуске jar-файла, все параметры указывается без кавычек:
* Ключ "-d". Используется для указания директории, с которой нужно начать поиск
* Путь к директории. Например, "D:/example"
* Ключ "-n". Используется для указания точного имени файла, маски или регулярного выражения
* Имя файла, маска или рег. выражение. Например, "*.txt"
* Указание типа поиска
    * "-f" - поиск по точному имени
    * "-m" - поиск по маске
    * "-r" - поиск по регулярному выражению
* Ключ "-o". Используется для указания файла, в который будет записан результат поиска.
* Имя файла для результата поиска. Например, "D:/log.txt"

Пример задания параметров приложения: **-d С:/ -n \*.txt -m -o D:/log.txt**

По завершении работы приложения в отчёте будут перечислены абсолютные пути всех найденных файлов.

В приложении были использованы следующие технологии и библиотеки:
* Java Core
* Apache Commons IO
* JUnit

#### 2) [Консольная игра "Крестики-нолики"](https://github.com/vladimirrepository/java-a-to-z/tree/master/tictactoe)
Приложение позволяет играть в игру "Крестики-нолики" с компьютером или с другим игроком. 
При необходимости можно изменить размер поля и клеток для победы.

Скриншот игры с компьютером приведён ниже:

<img src='https://s17.postimg.org/72c9mytkf/tictactoe.png'>


В приложении были использованы следующие технологии и библиотеки:
* Java Core
* JUnit

#### 3) [Парсер вакансий](https://github.com/vladimirrepository/java-a-to-z/tree/master/parser)
Данное приложение позволяет сохранять Java-вакансии с сайта HeadHunter в базу данных через определённый промежуток времени, работая в фоновом режиме.

Для удобства, по завершении работы генерируется список вакансий в html-формате, отсортированный по дате создания вакансии.
Внешний вид отчёта представлен на скриншоте:

[![Вакансии](https://s13.postimg.org/d6cfzke3r/parser.png)](https://s13.postimg.org/vlwwwys85/parser.png)

Для запуска приложения необходимо указать следующие параметры:
* Путь к файлу с конфигурацией для подключения к базе данных
* Путь к директории, в которую будет помещён отчёт по завершении работы
* Частота сканирования вакансий (в минутах)

Пример задания параметров приложения: **D:/config.txt D:/report 60**

Для того, чтобы остановить приложение, необходимо ввести в консоли команду **stop**.

В приложении были использованы следующие технологии и библиотеки:
* Java Core
* JDBC
* PostgreSQL
* Jsoup
* JUnit

#### 4) [Система учёта пользователей](https://github.com/vladimirrepository/java-a-to-z/tree/master/webcrudajax)

Данное веб-приложение позволяет авторизовываться под учётной своей учётной записью,
создавать, редактировать и удалять пользователей из базы данных, в зависимости от прав текущей учётной записи.

Начальная страница авторизации:

![](https://s11.postimg.org/m35x0aohv/auth.png)

Главная страница приложения для администратора:

<a href='https://s17.postimg.org/yu8p1rghp/admin.png'>
<img src='https://s17.postimg.org/yu8p1rghp/admin.png' width=500>
</a>
  
Главная страница приложения для пользователя:

<a href='https://s22.postimg.org/u6axqvly7/user.png'>
<img src='https://s22.postimg.org/u6axqvly7/user.png' width=500>
</a>

Пользователь может редактировать только свои данные.
Для создания, редактирования или удаления других пользователей необходимо зайти под учётной записью администратора.

Работа приложения основана на использовании сервлетов.
Аутентификация и авторизация реализована с помощью фильтров.
Внешний вид приложения был создан с помощью фрейморка Bootstrap.
Переход между окнами и валидация введённых данных реализованы с помощью JavaScript (jQuery).
Данные на сервер отправляются с помощью технологии Ajax.
Сервер отправляет информацию о пользователях в формате JSON.

В приложении были использованы следующие технологии и библиотеки:
* Java Core
* Java EE (Servlets)
* JDBC
* PostgreSQL
* HTML, CSS, Twitter Bootstrap, JavaScript, jQuery, Ajax, JSON
* JUnit

#### 5) [Площадка объявлений о продаже автомобилей](https://github.com/vladimirrepository/java-a-to-z/tree/master/springcars)

Данное веб-приложение позволяет пользователям размещать объявления о продаже автомобиля и оставлять контактные данные.
Просмотр объявлений доступен для неавторизованных пользователей. 
Для добавления нового объявления, редактирования или удаления уже созданного объявления необходимо авторизоваться.
При создании объявления, можно прикрепить фото автомобиля.

Список объявлений:

<a href='https://s22.postimg.org/6h6ye6dch/cars_list.png'>
<img src='https://s22.postimg.org/6h6ye6dch/cars_list.png' width=500>
</a>

Окно создания объявления:

<a href='https://s12.postimg.org/t8pcqebwd/cars_add.png'>
<img src='https://s12.postimg.org/t8pcqebwd/cars_add.png' width=500>
</a>

Обновлённый список объявлений:

<a href='https://s11.postimg.org/5q0efkwsz/cars_list2.png'>
<img src='https://s11.postimg.org/5q0efkwsz/cars_list2.png' width=500>
</a>

Приложение использует Spring Framework.
Модуль Spring MVC используется для написания контроллеров. 
Аутентификация и авторизация реализована с модуля Spring Security.
Данные хранятся в базе данных PostgreSQL, а доступ к ней обеспечивается с помощью Hibernate ORM.
Пользовательские картинки загружаются и сохраняются на сервере с помощью фреймворка Apache Commons FileUpload.

Внешний вид реализован с помощью фреймворков Twitter Bootstrap и jQuery.

В приложении были использованы следующие технологии и библиотеки:
* Java Core
* Spring Framework (IoC, MVC, Security)
* Hibernate ORM
* PostgreSQL
* JSP, JSTL
* HTML, CSS, Twitter Bootstrap, JavaScript, jQuery, Ajax, JSON
