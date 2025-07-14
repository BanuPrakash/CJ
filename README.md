# Advance Java
```
Banuprakash C
Full Stack Architect, Corporate Trainer
Co-founder & CTO: Lucida Technologies Pvt Ltd.,
Email: banuprakashc@yahoo.co.in; banuprakash.cr@gmail.com;
https://www.linkedin.com/in/banu-prakash-50416019/
https://github.com/BanuPrakash/CJ

===================================

Softwares Required:
1) openJDK 21
https://jdk.java.net/java-se-ri/21

 For Mac machine USE SDKMAN to manage java

curl -s "https://get.sdkman.io" | bash

sdk install java 21.0.6-tem

sdk default java 21.0.6-tem 

https://mydeveloperplanet.com/2022/04/05/how-to-manage-your-jdks-with-sdkman/#:~:text=Some%20time%20ago%2C%20a%20colleague%20of%20mine,maintain%20different%20versions%20of%20JDKs%2C%20Maven%2C%20etc.


2) IntelliJ Ultimate edition https://www.jetbrains.com/idea/download/?section=mac

3) MySQL  [ Prefer on Docker]

Install Docker Desktop

Docker steps:

a) docker pull mysql
b) docker run --name local-mysql –p 3306:3306 -e MYSQL_ROOT_PASSWORD=Welcome123 -d mysql

container name given here is "local-mysql"

For Mac:
docker run -p 3306:3306 -d --name local-mysql -e MYSQL_ROOT_PASSWORD=Welcome123 mysql


c) CONNECT TO A MYSQL RUNNING CONTAINER:

$ docker exec -t -i local-mysql bash

d) Run MySQL client:

bash terminal> mysql -u "root" -p

mysql> exit

```

Agenda:

Day 1: Web application development using Maven, RDBMS for CRUD operations

Day 2, 3, and 4: Spring Boot 3.x with JPA for persistence

Day 5: Spring Security and Introduction to MicroServices.

==========================================

Web Application Development:

JSE --> Java Standard edition
JEE --> Java Enterprise Edition
* Web application development using Servlet API
* Enterprise Java Bean [EJB] for distributed computing 
* JNDI
* ...

Web application development:
1) Server Side Rendering
* Server sends the presentation pages for the data
* Pros:
Thin clients, SEO
* Cons:
Can't have heterogenous clients like Mobile/ Tv/ Desktop / Web
* Heavy payload

2) Client Side Rendering
* send different formats of representation of data like JSON / XML / RSS / ATOM / CSV

==============

Servlet APIs provide a way to build Server side java applications.

HttpServletRequest ans HttpServletResponse objects are created by the Servlet engine for every request.
HttpServletRequest: encapsulates all data coming from client [ client specific data, Browser, OS]
HttpServletResponse: used to write data back to client

HttpServletRequest ans HttpServletResponse objects are attached to a Thread: ThreadLocal

==============

How does the engine know which Servlets to instantiate and map URL to servlet --> Deployment descriptor: XML or Annotation

Life cycle management of Servlets are done by the Servlet engine/web container
--> By default all Servlets are Singleton
--> All Servlets are multi threaded

web.xml -> one per web application
```
    <servlet>
		<servlet-name>first</servlet-name>
		<servlet-class>pkg.LoginServlet</servlet-class>
	</servlet>
    <servlet-mapping>
		<servlet-name>first</servlet-name>
		<url-pattern>/login</url-pattern>
	</servlet-mapping>
```
Annotation : @WebServlet("/login")


Maven / Gradle are build tools:
manage dependencies, execute goals like compile, testing, packaging,...

jakarta.servlet-api --> provides Servlet API
HttpServlet, Servlet, HttpServletRequest, HttpServletReponse

lombok --> code generation library

mysql-connector-j --> Mysql Implementation class for integrating Java <---> Mysql

---
Packaging --> jar / war / ear / sar

Web archive --> war --> understood by Servlet engine / Web container

```
    sampleweb
        |
        index.html
        events.js
        styles.css
        |
        WEB-INF
            |
            web.xml
            |
            classes
            |
                pkg
                 |
                 LoginServlet.class
                 RegisterServlet.class
```

maven-compiler-plugin
mvn compile

maven-war-plugin
mvn package --> mvn compile

embedded JETTY server / Tomcat Server
mvn jetty:run --> mvn package --> mvn compile

Web application ---> Servlet --> DAO --> JDBC --> Database

HTTP Methods: GET / POST / PUT / PATCH / DELETE

mvn jetty:run
http://localhost:8080/sample

================================

Docker is an open source software platform used to create, deploy and manage applications in virtualized environments called containers.

Images --> Software
https://hub.docker.com/

======

```
% docker exec -it local-mysql bash
bash-4.4# mysql -u root -p

mysql> create database cj;


mysql> use cj;

mysql> create table products (id int PRIMARY KEY AUTO_INCREMENT, name VARCHAR(100), price double);


mysql> insert into products values (0, 'iPhone 16', 89000.00);


mysql> insert into products values (0, 'Sony Bravia', 279000.00);


mysql> select * from products;
+----+-------------+--------+
| id | name        | price  |
+----+-------------+--------+
|  1 | iPhone 16   |  89000 |
|  2 | Sony Bravia | 279000 |
+----+-------------+--------+


``

DriverManager.getConnection() factory method to establish a database connection
Statement --> SQL which is fixed, no IN parameter
PreparedStatement --> SQL which takes IN parameter
select * from users where username = ? and password = ?
select * from products where price > ?

executeUpdate() -- INSERT , DELETE and UPDATE SQL
executeQuery() -- SELECT

JSP pages --> Static + dynamic content

======================================================

MVC Architectural Pattern
Model --> Business data and business logic
View --> Presentation layer [HTML, CSS , JS and JSP ]
Controller --> Application Logic [ flow of application ] --> Servlet and Filter

Server Side Redirection: One resource within the engine is redirecting to other resource with in the engine
ForntController redirects to list.jsp:
 req.getRequestDispatcher("list.jsp").forward(req, resp);

Server Side Redirection can be used for multi stage processing
