# To Do API

This project is a REST-type server which has the functionality of showing pending and completed tasks or "To Do's". For now, you can only see the tasks registered in the database. This was done by Alexis Adán Ramírez Hernández.

## Installation

First, you have to clone the project from my GitHub repository.

```git clone https://github.com/AlexisRmH/rest-api```

In order to build it, you will need to assemble the dependencies and then package the project into a jar:

```mvn package```

Then, in my case, I used the following command to run the server:

```java -cp target/rest-server-1.0-SNAPSHOT-jar-with-dependencies.jar mx.ucol.App```

## Usage

The API can respond to the following endpoint (just one for now). I decided to use the port number 8000.

You can type the following URL in your browser:

```localhost:8000/api/v1/todos```

![Browser endpoint](./browserUnf.PNG)

![Browser endpoint](./browser.PNG)

_The JSON was formatted using the Chrome extension "JSON Formatter"_

Or use the following command in a prompt:

```curl localhost:8000/api/v1/todos```

![Command prompt endpoint](./cmd.PNG)

The endpoint responds with the following JSON, which lists all of the pending tasks:

```
{
   id: 1,
   title: "First To Do!",
   completed: false,
}
```

## Project dependencies

```
<dependencies>
    <dependency>
      <groupId>junit</groupId>
      <artifactId>junit</artifactId>
      <version>4.11</version>
      <scope>test</scope>
    </dependency>
    <dependency>
      <groupId>com.fasterxml.jackson.core</groupId>
      <artifactId>jackson-databind</artifactId>
      <version>2.11.1</version>
    </dependency>
    <dependency>
        <groupId>org.xerial</groupId>
        <artifactId>sqlite-jdbc</artifactId>
        <version>3.34.0</version>
    </dependency>
  </dependencies>
```
sqlite-jdbc is the driver to connect to the SQLite database.

jackson-databind helps us to convert java objects to objects and the other way around.

JUnit is useful to execute specific Java classes in a controlled environment to test the system.

## Database Management System

SQLite was used as the DBMS to keep the record of the data.

## Conclusions

This project was an interesting learning experience that helped me to understand a bit deeper the development and behaviour of REST-type APIs, at least to the extent that we completed our project.
Even in this state, it's still a functioning API which can be developed and expanded further, giving me a model to follow for future projects.

