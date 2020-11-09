# Java / Spring - basic database setup (MySQL)

### Introduction
Basic database creation with some back-end API;
- use Postman or something similar to check it;

### Create Database

CREATE DATABASE basicdb;

CREATE USER 'admin_basicdb'@'%' IDENTIFIED BY 'adminpass'; 

GRANT ALL PRIVILEGES ON basicdb.* TO 'admin_basicdb'@'%'; 

CREATE USER 'user_basicdb'@'%' IDENTIFIED BY 'userpass';

GRANT SELECT, INSERT, DELETE, UPDATE ON basicdb.* TO 'user_basicdb'@'%';

### Running

gradlew clean build

gradlew bootRun

App is running on http://localhost:7070/  

Used technologies:
    - Java - source compatibility 12
    - Gradle - 6.6.1
    - Spring - 2.3.4