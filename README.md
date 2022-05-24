# sii_recruitment_task
You can run project with one of three ways:
1. Open project in IntelliJ, set Run configuration as Maven with command line "spring-boot:run"
![image](https://user-images.githubusercontent.com/44434406/170015862-8f2b1502-8a33-4af4-8bb1-ef17788768a2.png)

2. Open project directory with command prompt and execute "mvn spring-boot:run"
![image](https://user-images.githubusercontent.com/44434406/170016728-ffd16890-020c-4513-818d-9f3fa939de5f.png)

3. Build project with "mvn clean install", move to target and execute "java -jar sii_recruitment_task-0.0.1-SNAPSHOT"

![image](https://user-images.githubusercontent.com/44434406/170017920-fcbfef01-c7ec-46ce-9d54-5660b778ecc6.png)
![image](https://user-images.githubusercontent.com/44434406/170017964-60cecbbe-55dd-4dde-95fd-722cb8c26ef5.png)

Server runs on port 8080 by default, You can change it in application.properties file in resources directory. 
To run tests You can:
1. Open project in IntelliJ, set Run configuration as Maven with command line "test"
![image](https://user-images.githubusercontent.com/44434406/170018106-1782b06d-0468-41d7-a036-ad735968210d.png)

2. Open project directory with command prompt and execute "mvn test"
![image](https://user-images.githubusercontent.com/44434406/170018218-d1ac541b-bc0e-4629-a577-dd573448f45b.png)

Example URLs to REST services in Postman:

1. Conference plan (GET)
http://127.0.0.1:8080/prelections/conferencePlan

![image](https://user-images.githubusercontent.com/44434406/170019902-de10c053-b296-45c0-8635-21ee15c5d611.png)

Responses:
  - 200 OK

2. Viewing User's reservations (GET) (Must add some reservations first!)
http://127.0.0.1:8080/users/login1/prelections

![image](https://user-images.githubusercontent.com/44434406/170020702-10b5af99-91db-4447-8bc6-5884471c19d0.png)

Responses:
  - 200 OK
  - 404 NOT FOUND - No such user!

3. Making reservation (POST)
http://127.0.0.1:8080/reservations/makeReservation
Request body:
{
    "prelectionId": 1,
    "login": "login1",
    "email": "email1@example.com"
}

![image](https://user-images.githubusercontent.com/44434406/170020957-c337f55d-974e-4f40-83de-6546c61ebf6d.png)

Responses:
  - 201 CREATED
  - 404 NOT FOUND - No such prelection!
  - 404 NOT FOUND - No such user!
  - 409 CONFLICT - Podany login jest juz zajety!
  - 409 CONFLICT - User have reservation for this hour!
  - 409 CONFLICT - Not enough place in this prelection!

4. Canceling reservation (DELETE) (Must add some reservations first!)
http://127.0.0.1:8080/reservations/login1/cancelReservation?prelectionId=1

![image](https://user-images.githubusercontent.com/44434406/170022073-188e28f2-a337-4dba-a63d-9e05811e6646.png)

Responses:
  - 204 NO CONTENT
  - 404 NOT FOUND - No such user!
  - 404 NOT FOUND - User dont have reservation for this prelection!

5. Updating user mail (PATCH) (Must add user first!)
http://127.0.0.1:8080/users/login1/changeMail
Request body:
{
    "oldMail": "email1@example.com",
    "newMail": "newEmail1@example.com"
}

![image](https://user-images.githubusercontent.com/44434406/170022467-6fe2bda4-cb76-4b33-a4b8-0e13b2c46159.png)

Responses:
  - 200 OK
  - 409 CONFLICT - Old mails are not equal!
  - 404 NOT FOUND - No such user!

6. List users (GET) (Must add some users first!)
http://127.0.0.1:8080/users/getUsers

![image](https://user-images.githubusercontent.com/44434406/170022924-61d94103-8187-40d3-b102-86690a3f8ac7.png)

Responses:
  - 200 OK

7. Percents of prelections interest in time segments (GET)
http://127.0.0.1:8080/reservations/getPrelectionsInterest

![image](https://user-images.githubusercontent.com/44434406/170023423-4cc31747-b855-45c0-be22-7a1c45a2fd7e.png)

Responses:
  - 200 OK
 
8. Percents of topic interest (GET)
http://127.0.0.1:8080/reservations/getTopicInterest

![image](https://user-images.githubusercontent.com/44434406/170023768-bd13b629-15ba-46a0-afd0-f623a0206df7.png)

Responses:
  - 200 OK


