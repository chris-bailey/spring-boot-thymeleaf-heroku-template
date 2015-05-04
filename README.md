
Project name: Spring Boot + Thymeleaf + Heroku Template
Quick description: Spring Boot + Thymeleaf + Heroku Template for rapid development


#Spring Boot + Thymeleaf + Heroku Template#
This template has been designed to be used in conjunction with [JHipster](https://jhipster.github.io/) to enable rapid development of Spring Boot + Thymeleaf applications that are fully deployable to Heroku.  

###Project Characteristics###
- Spring Boot, no-xml Spring MVC 4 web application for Servlet 3.0 environment
- Thymeleaf templates with added Joda Time & Spring Security Dialects  
- Heroku fully cloud deployable
- JPA 2.0 (Spring Data JPA/Hibernate)
- Database (Liquibase/PostgreSQL/H2 embedded/HikariCP)  
- Testing (JUnit/Mockito/MockMVC/AssertJ/Hamcrest)  
- Java 8, Spring Security 3.2, Maven 3, Logback, Bootstrap 3.3.4, jQuery 1.11.2, i18n, etc

###Suggested Usage###
Utilize [JHipster](https://jhipster.github.io/) to rapidly generate entities and Liquibase database changelogs that can then be transferred into this template.  

Entity classes can be transferred into this template from JHipster's domain package.  Liquibase changelogs can be transferred from JHipster's src/main/resources/config/liquibase folder.  

This template has been kept as <i>lean</i> as possible so that it can deploy successfully to Heroku without timing out while using a Heroku account with one allocated dyno.  

If interested in production-ready features, check out the [Spring Boot Actuator](http://docs.spring.io/spring-boot/docs/current-SNAPSHOT/reference/htmlsingle/#production-ready) which will add many useful tools with very little effort.  Also, you can look at JHipster which is utilizing the [Metrics project](https://dropwizard.github.io/metrics/3.1.0/) as well as [Swagger](http://swagger.io/).


###Local Deployment###
```
$ mvn clean install  
$ mvn spring-boot:run
```

Navigate to [http://localhost:8080](http://localhost:8080).  
 
The application can also be deployed by running the `Application.java` class.

###Deploying to Heroku###
Create a new application on Heroku  
```
$ heroku create
```

Rename your application if you like  
``` 
$ heroku apps:rename new-name
```

Add a PostgreSQL database to your application  
```
$ heroku addons:add heroku-postgresql
```

Deploy application to Heroku  
```
$ git push heroku master
```

Look at your application logs to see what is happening behind the scenes  
```
$ heroku logs
```

If your application deploys without timing out then open it as follows 
```
$ heroku open
```

If it takes longer than 60 seconds to build the application then Heroku will send a kill signal.  If the application timed out while it was building then give it a few more minutes so that Heroku can attempt to load the application again.  

If your application times out while your Liquibase changes are being applied, then Liquibase will 'lock' the database.  I have read about this occurring, but have not had to deal with it myself.  I have tested out the following steps in a local development environment, but they have not yet been tested on Heroku.
  
In order to manually unlock the database do the following: 

1. In the `databasechangelog` table, remove the changelogs that did not execute properly.


1. In the `databasechangeloglock` table, set the locked value to false.


1. Modify the database to the state it was in before the changelogs.  For example, if your changelog added a table called `T_Employee` then remove the `T_Employee` table from the database before attempting to redeploy your application to Heroku.

###Updating your application###
After making changes to your application, and updating your Git repository with those changes, you can push those changes to Heroku as follows:  
 
```
$ git push heroku master
```

###Template Customizations###
When renaming the template packages, also note that there are few locations that should also be modified.

`com.chrisbaileydeveloper.myapp.config/DatabaseConfiguration.java`
Modify the following line:
```
@EnableJpaRepositories("*com.chrisbaileydeveloper.myapp.repository")
```

`src/main/resources/logback.xml`  
Modify the following line: 
```
<logger name="com.chrisbaileydeveloper.myapp" level="DEBUG"/>
```

`src/test/resources/logback-test.xml`  
Modify the following line:
```
<logger name="com.chrisbaileydeveloper.myapp" level="DEBUG"/>
```

`pom.xml`
Modify Liquibase plugin's `<referenceUrl>` to match your package name.
                    `<referenceUrl>hibernate:spring:com.chrisbaileydeveloper.myapp.domain?...</referenceUrl>`



###Local Database Selection###
An embedded H2 database is the default local development database for this template so that it can be run from the command line without any modifications.  

However, I would recommend using a PostgreSQL database for local development instead of the H2 database that has been packaged with this template.  Heroku is using a PostgreSQL database, so if something breaks in your local PostgreSQL database then you can fix it before deploying to Heroku.  

Here are the steps for converting the template so that it is utilizing a local PostgreSQL database in development.  

1.	Install PostgreSQL on your system and make sure the PostgreSQL service is running.
2.	Create a new database called `sample`.
3.	Set the owner of the new `sample` database to `postgres`.
4.	Navigate to the following directory: `src/main/resources/config`
5.	Rename `application-dev.yml` to `application-dev (H2).yml`
6.	Rename `application-dev (PostgreSQL).yml` to `application-dev.yml`.
7.	Open up the new `application-dev.yml` and modify the password field so that it holds your postgres user password.
8.	`$ mvn clean install`
9.	`$ mvn spring-boot:run`
10.	Open up the `sample` database and check to make sure that your Liquibase tables have been generated (`databasechangelog` & `databasechangeloglock`).

###Special Thanks###
A big thank you to [Julien Dubois](http://www.julien-dubois.com/) and the [JHipster](https://jhipster.github.io/) project for leading the way in the rapid development of Spring applications, as well as for part of the foundation for this template.  JHipster is a Yeoman generator used to create Spring + AngularJS projects, with full hot reload of Java and JavaScript code.

Also, thank you to Rafal Borowiec's for his impressive [spring-mvc-quickstart-archetype](https://github.com/kolorobot/spring-mvc-quickstart-archetype) project.



