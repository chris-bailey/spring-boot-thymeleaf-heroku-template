
#Spring Boot + Thymeleaf + Heroku Template#
This template has been designed to be used in conjunction with [JHipster](https://jhipster.github.io/) to enable rapid development of Spring Boot + Thymeleaf applications that are fully deployable to Heroku.  The template's features have been fully documented in the [Spring Boot + Thymeleaf + Heroku Template](http://chrisbaileydeveloper.com/projects/spring-boot-thymeleaf-heroku-template/) web page on my website.

###Technology Stack###
- Spring Boot, no-xml Spring MVC 4 web application for Servlet 3.0 environment
- Thymeleaf templates with added Joda Time & Spring Security Dialects  
- Heroku fully cloud deployable
- JPA 2.0 (Spring Data JPA/Hibernate)
- Database (Liquibase/PostgreSQL/H2 embedded/HikariCP)  
- Testing (JUnit/Mockito/MockMVC/AssertJ/Hamcrest)  
- Java 8, Spring Security 3.2, Maven 3, SLF4J, Logback, Bootstrap 3.3.4, jQuery 1.11.2, i18n, etc

###Live Demo###
Be aware that the template is currently running on a free Heroku account.  If it hasn't been accessed in 30 minutes, then ***the first request will take up to 60 seconds***.  Please be patient with the first request. Subsequent requests will be normal.  

Here is the [Spring Boot + Thymeleaf + Heroku Template](https://spring-boot-thymeleaf-heroku.herokuapp.com/) running on Heroku.


###Suggested Usage###
Utilize [JHipster](https://jhipster.github.io/) to rapidly generate entities and Liquibase database changelogs that can then be transferred into this template.  

Entity classes can be transferred from JHipster's `domain` package.  Liquibase changelogs can be transferred from JHipster's `src/main/resources/config/liquibase` folder.  

This template has been kept as <i>lean</i> as possible so that it can deploy successfully to Heroku without timing out while using an Heroku account with one allocated dyno.  

If interested in production-ready features, check out the [Spring Boot Actuator](http://docs.spring.io/spring-boot/docs/current-SNAPSHOT/reference/htmlsingle/#production-ready) which will add many useful tools with very little effort.  Also, you can look at JHipster which is utilizing the [Metrics project](https://dropwizard.github.io/metrics/3.1.0/) as well as [Swagger](http://swagger.io/).


###JHipster Setup###
While generating your [JHipster](https://jhipster.github.io/) application, select the following settings when prompted.  The selected settings will allow for the highest level of integration between JHipster and this template.

Do you want to use Java 8?  
**Yes (use Java 8)**  
  
Which \*type\* of authentication would you like to use?  
**HTTP Session Authentication**  
  
Which \*type\* of database would you like to use?  
**SQL (H2, MySQL, PostgreSQL)**  

Which \*production\* database would you like to use?  
**PostgreSQL**

Which \*development\* database would you like to use?  
**H2 in-memory**  

Do you want to use Hibernate 2nd level cache?  
**No**  

Choose **Maven** as the build tool.

###Local Deployment###
```
$ mvn clean install  
$ mvn spring-boot:run
```

Navigate to [http://localhost:8080](http://localhost:8080).  
 
The application can also be deployed by running the `Application.java` class.

###Deploying to Heroku###
<i>The following steps require that the [Heroku Toolbelt](https://toolbelt.heroku.com/) has been installed locally and that a Heroku account has been created.</i>

Navigate to the project directory on the command line.

Before creating your Heroku application, make sure that there is a Git repository associated with the project.   
```
$ git status
```  

If a Git repository is not associated with the project, then create one before continuing. 

Create a new application on Heroku  
```
$ heroku create
```

Rename your Heroku application if interested  
``` 
$ heroku apps:rename new-name
```

Add a PostgreSQL database to your Heroku application  
```
$ heroku addons:create heroku-postgresql
```

Deploy project to Heroku  
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

###Updating your Heroku application###
After making changes to your project, and updating your Git repository with those changes, you can push those changes to Heroku as follows:  
 
```
$ git push heroku master
```

###Deploying to a new Heroku dyno###
If for any reason you are interested in starting from scratch with a new Heroku application, you can do the following:    

```  
$ git remote rm heroku
```  

You can then start from scratch with the `heroku create` command.

###Template Customizations###
In addition to the renaming of the template's packages, there are a few specific locations that should also be modified.  They are as follows:

Modify the `DatabaseConfiguration.java` class so that the following line contains your package name:  
```
@EnableJpaRepositories("*com.chrisbaileydeveloper.myapp.repository")
```
<br/>
Modify the `src/main/resources/logback.xml` file so that the following line contains your package name:  
```
<logger name="com.chrisbaileydeveloper.myapp" level="DEBUG"/>
```
<br/>
Modify the `src/test/resources/logback-test.xml` file so that the following line contains your package name:  
```
<logger name="com.chrisbaileydeveloper.myapp" level="DEBUG"/>
```
<br/>
Modify the `pom.xml` file so that the following line contains your package name:  
`<referenceUrl>hibernate:spring:com.chrisbaileydeveloper.myapp.domain?...</referenceUrl>`


###Local Database Selection###
An embedded H2 database is the default local development database for this template so that it can be run from the command line without any modifications.  

I would recommend using a PostgreSQL database for local development since Heroku is utilizing a PostgreSQL database in Production.  This will enable you to catch database errors locally before they are deployed to Heroku where they are more complicated to troubleshoot.

Here are the steps for converting the template so that it is utilizing a local PostgreSQL database in development.  

1.	Install PostgreSQL on your system and make sure the PostgreSQL service is running.
2.	Create a new database called `sample`, or any other database name of your choosing.
3.	Set the owner of the new `sample` database to `postgres`.
4.	Navigate to the following project directory: `src/main/resources/config`
5.	Rename `application-dev.yml` to `application-dev (H2).yml`
6.	Rename `application-dev (PostgreSQL).yml` to `application-dev.yml`.
7.	Open up the new `application-dev.yml` and modify the password field so that it holds your postgres user password.
8.	`$ mvn clean install`
9.	`$ mvn spring-boot:run`
10.	Open up the `sample` database and verify that the following two Liquibase tables have been generated:  `databasechangelog` & `databasechangeloglock`.

###Setting the Logging Level###
The logging level is set in the `src/main/resources/logback.xml` configuration file.  

In order of most verbose to least verbose, the logging levels available are as follows: TRACE, DEBUG, INFO, WARN, ERROR

For example, the following logging levels can be modified to INFO or WARN when moving to Production if you are receiving more information than you find necessary.

```
<root level="DEBUG">
        <appender-ref ref="CONSOLE"/>
</root>
    
<logger name="com.chrisbaileydeveloper.myapp" level="DEBUG"/>
```


###Special Thanks###
A big thank you to [Julien Dubois](http://www.julien-dubois.com/) and the [JHipster](https://jhipster.github.io/) project for leading the way in the rapid development of Spring applications, as well as for part of the foundation for this template.  JHipster is a Yeoman generator used to create Spring + AngularJS projects, with full hot reload of Java and JavaScript code.

Also, thank you to Rafal Borowiec's for his impressive [spring-mvc-quickstart-archetype](https://github.com/kolorobot/spring-mvc-quickstart-archetype) project.

### Author ###
[Chris Bailey](http://www.chrisbaileydeveloper.com)

