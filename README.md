Для создания базы данных используется Docker. 
Для запуска базы данных используйте docker-compose up -d
Далее необходимо скомпилировать приложение в maven.
После запуска jar файла доступ к серверу получается через http://localhost:8080
Для поиска http://localhost:8080/find?start=2019-01-01&end=2020-01-01&firstname=Serenity&lastname=Gennadiya
Для поиска по имени или фамилии режиссера http://localhost:8080/byDirector?firstname=Serenity&lastname=Gennadiya
Для поиска по периоду времени, в течение которого выходили фильмы http://localhost:8080/byDates?start=2019-01-01&end=2020-01-01
Параметр start - начало временного отрезка, end - конец временного отрезка.
Параметр firstname - имя, lastname - фамилия.