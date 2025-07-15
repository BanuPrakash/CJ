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


===========

Http protocol is a stateless protocol. Once response is committed HttpServletRequest and HttpServletResponse objects are destroyed. There is no conversational state of client.

Session Tracking: Ability given to server side applications to keep track of conversational state of client.

HttpSession API provides mechanism for session tracking

===========

Protected resources:

Filter --> Servlet API are used for interceptor pattern, generally used for cross-cutting concerns like
logging, security, profile, encrption, encoding,...

Listeners: executed automatically when certain events happen within the Servlet Engine

```
ServletContext: Environment where serlvets are managed
public class ContextInit implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        // this code is executed as soon as Servlet Context is created
        Database Connection pool
        User Thread pool
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        // when servlet context is destroyed
    }
}

public class UserInit implements HttpSessionListener {
    // called whenever session is created or destroyed
}
```

Web application development: Servlet, JSP , HTML , Filter and Listener

SOLID Design Principle:
S --> Single Responsibility
O --> Open Close Principle [Closed for Change, but open for extension]
L --> Liskov Substitution Principle: 
    Generalization and Specialization relationship. A Specialization component can replace the Generalization and still get the work done what Generalization does.

    BankingAccount is Generalization
    SavingsAccount is Specialization
    LoanAccount is Specialization
    CurrentAccount is Specialization

    If I am able to login and logout using BankingAccount, I should be able to perform these operarions using Savings, loan and Current account

    If BankingAccount has credit() and debit(), this fails Liskov Substitution Principle
I --> Interface seggregation
D --> Dependency Injection using IoC Container
Inversion of Control

Web Container already uses DI.
HttpServletRequest and HttpServletResponse objects are injected to Servlet and Filter

UI --> Service ---> Repository / DAO --> Database connection

=============

Day 1 Recap:
```
Web application development wrt to Java 
Servlet API: Servlet, JSP, Filter and Listeners are termed as web components of JEE
Servlet Container / Web Container / Servlet engine --> JETTY / TOMCAT and NETTY 

Servlet Container manages life cycle of Servlet APIs like instantiating Servlets, HttpServletRequest and HttpServletResponse.
Also DI of request and response objects are handled by Servlet Container

Servlet/ Filter --> Controller
JSP/HTML, CSS and JS --> View
Java Bean [business data and logic] --> Model

client side forwarding : response.sendRedirect()
server side forwarding: request.getRequestDispatcher("resource").forward(req, resp)

HttpSession API: to track conversational state of the client

```

Spring Framework: provides Spring Container to manage life cycle of beans and dependency injection.
Bean --> any object managed by spring framework is a bean.

Why Spring Framework?
1) application can be built using loosely coupled objects 
2) Reduces a lot of boiler plate code.
3) provides many templates for Enterprise Application dev.
4) Declarative style of programming instead of programmitic

Spring framework uses XML / annotation as metadata for  manage life cycle and DI.

```
    interface EmployeeDao {
        void addEmployee(Employee e);
    }

    public class EmployeeDaoJdbcImpl implements EmployeeDao {
        public void addEmployee(Employee e) {
            ..
        }
    }

     public class EmployeeDaoMongoDbImpl implements EmployeeDao {
        public void addEmployee(Employee e) {
            ..
        }
    }

    public class AppService {
        private EmployeeDao empDao;

        public void setEmpDao(EmployeeDao edao) {
            this.empDao = edao;
        }

        public void doTask(Employee e) {
            empDao.addEmployee(e);
        }
    }

```

beans.xml

```

    <beans>
        <bean id="mongo" class="pkg.EmployeeDaoMongoDbImpl" />
        <bean id="jdbc" class="pkg.EmployeeDaoJdbcImpl" />
        <bean id="service" class="pkg.AppService">
            <property name="empDao" ref="jdbc" />
        </bean>
    </beans>

ApplicationContext ctx = new ClassPathXmlApplicationContext("beans.xml");

AppService ser = ctx.getBean("service", AppService.class);

ser.doTask(new Employee(...));

```

==============

Annotations as meta-data:
Spring is going to instantiate objects if it finds any of the below annotations:
1) @Component
2) @Repository
3) @Service
4) @Controller
5) @RestController
6) @Configuration
7) @ControllerAdvice

Wiring can be done using @Autowired or using constructors

```
  interface EmployeeDao {
        void addEmployee(Employee e);
    }

    @Repository
    public class EmployeeDaoJdbcImpl implements EmployeeDao {
        public void addEmployee(Employee e) {
            ..
        }
    }

    @Service
    public class AppService {
        @Autowired
        private EmployeeDao empDao;

        public void doTask(Employee e) {
            empDao.addEmployee(e);
        }
    }

```

```

ApplicationContext ctx = new AnnotationConfigApplicationContext();
ctx.scan("com.cisco.prj");
ctx.refresh();

AppService ser = ctx.getBean("service", AppService.class);

ser.doTask(new Employee(...));

    @Service
    public class AppService {
        // Constructor DI
        public AppService(EmployeeDao empDao) {
            this.empDao = empDao;
        }
    }


```

Advantage of using @Repository --> ErrorCode to proper exceptions

https://github.com/spring-projects/spring-framework/blob/main/spring-jdbc/src/main/resources/org/springframework/jdbc/support/sql-error-codes.xml

Without Spring and @Repository

```
    // Oracle
    try {


    } catch(SQLException ex) {
        if(ex.getErrorCode() == 1) {
            throw new DuplicateKeyException("...");
        }
    }
    // MySQL
    try {


    } catch(SQLException ex) {
        if(ex.getErrorCode() == 1062) {
            throw new DuplicateKeyException("...");
        }
    }

```

Spring Boot framework is built on top of Spring Framework
Spring boot 2.x is built on Spring Framework 5.x
Spring boot 3.x is built on top of Spring Framework 6.x

Why Spring Boot?
Highly opiniated framework, lots of configurations comes out of the box.
Example:
* While building JDBC based applications, database connection pool is configured  out of the box.
* While building Web applications 
1) Embedded Tomcat container is configured out of the box.
2) FrontController is configured and ready to use
3) Provides Java <--> JSON conversion HttpMessageConverter
* many more ...
* Easy to Dockerize

====

@SpringBootApplication is 3 in one:
1) @ComponentScan
2) @EnableAutoConfiguration
3) @Configuration


Problem:

```

Field employeeDao in com.cisco.springdemo.service.AppService 
    required a single bean, but 2 were found:
	- employeeDaoJdbcImpl: 	
    - employeeDaoMongoImpl:

```

Solution 1: Using @Primary
Mark one of the eligible ones as @Primary

```

@Repository
@Primary
public class EmployeeDaoMongoImpl implements EmployeeDao{
@Repository
public class EmployeeDaoJdbcImpl implements EmployeeDao{

```

Solution 2: using @Qualifier

```

@Repository
public class EmployeeDaoMongoImpl implements EmployeeDao{
@Repository
public class EmployeeDaoJdbcImpl implements EmployeeDao{   


@Service
public class AppService {
    @Autowired
    @Qualifier("employeeDaoMongoImpl")
    private EmployeeDao employeeDao;

```


Solution 3: @Profile

```

@Repository
@Profile("prod")
public class EmployeeDaoMongoImpl implements EmployeeDao{

@Repository
@Profile("dev")
public class EmployeeDaoJdbcImpl implements EmployeeDao{

@Service
public class AppService {
    @Autowired
    private EmployeeDao employeeDao;

application.properties
spring.profiles.active=dev

```

Solution 4: @ConditionalOnMissingBean

```

@Repository
@ConditionalOnMissingBean(EmployeeDaoMongoImpl.class)
public class EmployeeDaoJdbcImpl implements EmployeeDao{

@Repository
public class EmployeeDaoMongoImpl implements EmployeeDao{

```

==================

Factory Methods:
If 3rd party objects has to be managed by spring container, or object creation is complex then we need factory methods.

DataSource --> Pool of database connection
DriverManager --> to establish a single connection; latency involved in establishing connection and releasing.


DataSource: C3p0, Hikari, DriverManagerDataSource , ....

=============================================

Spring Framework integrating with ORM Framework.

ORM Framework: Object Relational mapping
Object-Relational Mapping (ORM) is a programming technique that converts data between a relational database and an object-oriented programming language. 

class is mapped to database table
fields are mapped to columns of database.

ORM frameworks helps generate DDL and DML statements, no need to write SQL.

ORM Frameworks: 
* Hibernate is by JBoss [RedHat]
* TopLink is by Oracle
* KODO is by BEA [ acquired by Oracle ]
* JDO is by Sun [ acquired by Oracle ]
* openJPA by Apache
* Eclipselink


Spring Boot JPA module is using Hibernate by default [ opiniated framework]
and HikariCP for database connection pool [ opiniated framework]

JPA: Java Persistence API is a specification for ORMs, like an layer/interface for ORMS

ORMs eliminate scaffolding codes like establishing connection, Statement, handling exceptions and also releasing resources..

=======================

```

@Configuration
public class AppConfig {

    @Bean
    public LocalContainerEntityManagerFactoryBean emf(DataSource ds) {
        LocalContainerEntityManagerFactoryBean emf = new LocalContainerEntityManagerFactoryBean();
        emf.setDataSource(ds);
        emf.setPackagesToScan("com.cisco.prj.entity");
        emf.setJpaVendor(new HibernateJpaVendor());
        ...
        return emf;
    }

    // factory method
    @Bean
    public DataSource getDataSource() throws Exception {
        ComboPooledDataSource cpds = new ComboPooledDataSource();
        cpds.setDriverClass( "com.mysql.cj.jdbc.Driver" ); //loads the jdbc driver
        cpds.setJdbcUrl( "jdbc:mysql://localhost:3306/cj" );
        cpds.setUser("root");
        cpds.setPassword("Welcome123");
        cpds.setMinPoolSize(5);
        cpds.setAcquireIncrement(5);
        cpds.setMaxPoolSize(20);
        return  cpds;
    }
}


```

Spring Data JPA configured along with JPA /ORM simplifies using ORMs.
No need to write @Repository classes. we just create interfaces, implementation classes are generated by Spring Data JPA.

==============

Spring Boot application:
Dependencies:
1) MySQL
2) JPA
3) Lombok

1) spring.jpa.hibernate.ddl-auto=update

Map to existing table.
If required alter table
If table doesn't exist create a table

Can be used for Top to bottom apprach

2) spring.jpa.hibernate.ddl-auto=create
create tables when application starts, delete them them on application terminate.
Good for testing environment

3) spring.jpa.hibernate.ddl-auto=verify
Map to existing tables in database. Exact matching has to be there.
No alter or creation of new tables.
DBA controls the Database.
Used for Bottom to Top approach.

with Spring Data JPA we just need interfaces, implementation class [@Repository] are generated:

```

```

public interface CustomerRepo extends JpaRepository<Customer, String> {
}

public interface ProductRepo extends JpaRepository<Product, Integer> {
}


```

======

CommandLineRunner is a Spring interface used to execute code after the Spring Boot application context has been loaded and before the application starts serving requests.

Settings:
build, Execution, deployment --> Annotation Processors
orderapp--> Obtain processors from classpath


==========

@Transactional is required for any custom mutation to the database table like insert, delete or update
default for built-in JpaRepository methods it's enabled --> Auto commit is set to true.


Association Mapping: entities are associated
* One-to-many
* many-to-one
* one-to-one
* many-to-many

Domain Driven Design
https://martinfowler.com/bliki/BoundedContext.html

@JoinColumn used with @ManyToOne introduces FK in owning entity
@JoinColumn used with @OneToMany introduces FK in child entity 