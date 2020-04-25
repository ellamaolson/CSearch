# CSearch

### San Jose State University, Enterprise Software - CMPE 172/Spring 2020

## Team Members

- [Aleksandra Kovina](https://github.com/Sashanity)
- [Cuong Nguyen](https://github.com/calvinqc)
- [Elana Olson](https://github.com/ellamaolson)

## Description

CSearch is a search engine for software and computer science projects. Learn how to code, resfresh on previous learned topics, and challenge yourself to continue learning new skills. It is a one-stop-shop for any and all computer science projects to help expand your knowledge and create awesome projects!

## Setup Environment

To install and run this project, you need to have Node.js, an NPM package manager, Angular CLI, Java, and Maven package manager installed.

- Check if you already have Node.js installed with `node -v`. To install Node.js and npm, go to [nodejs.org](https://nodejs.org/)
- To install the [Angular CLI](https://github.com/angular/angular-cli), run `npm install -g @angular/cli`
- Check if you already have Java and Maven installed with `java -version` and `mvn -version`. To install Java and/or Maven, go to [maven install](https://www.baeldung.com/install-maven-on-windows-linux-mac).
- Clone project `git clone https://github.com/ellamaolson/CSearch.git`
- Install [MySQL](https://dev.mysql.com/doc/refman/8.0/en/installing.html) if creating a local database for building locally.

  - Create a file called **application.properties** in **/resources** folder in the server. Input the following code, then change `<database-name>` to your database and add your local MySQL username and password.

  ```java
  spring.datasource.url=jdbc:mysql://localhost:3306/<database-name>?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC

  spring.datasource.username=

  spring.datasource.password=

  spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

  spring.jpa.show-sql=true

  spring.jpa.database-platform=org.hibernate.dialect.MySQL5Dialect

  spring.jpa.hibernate.ddl-auto=update
  ```

## Build locally

The frontend (client) is an Angular application that is run using the Angular CLI. The project can be run with a local MySQL database as the third tier of the app.

Start the client:

- `npm install`
- `ng serve` for a dev client on `http://localhost:4200/`. The app will automatically reload if you change any of the source files.

Start the server:

- `mvn clean install`
- `mvn spring-boot:run` for a dev server on `http://localhost:8080/`.

## Database

### Schema

### Database Queries

## Mid tier APIs

## UI data transport
