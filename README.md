Запуск через docker-compose, на локальной машине должен быть установлен докер.
На машине где будет стартовать docker-compose должна быть установлена maria-db и создана база данных со следующими параметрами:

    url: 'jdbc:mariadb://localhost:3306/springbootdb'
    username: root
    password: root
    
DDL-скрипт для создания таблиц находится в файле script.sql.

http://localhost:8082/start - начало взаимодействие (запуск шедуллера)

http://localhost:8082/stop - окончание взаимодействие (остановка шедуллера)