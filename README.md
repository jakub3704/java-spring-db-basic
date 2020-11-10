# Java / Spring - basic database setup (MySQL)

### Introduction
Basic database implementation with some back-end API.

Testing with POSTMAN or similar.

1. POST http://localhost:7788/item  
  BODY (JSON)  
  {  
  "name": "your_item_name",  
  "description": "your_item_description"  
  }
2. PUT http://localhost:7788/item  
  BODY (JSON)  
  {  
  "id": "id_of_item_to_update",    
  "name": "your_item_name",  
  "description": "your_item_description"  
  }
3. GET http://localhost:7788/item/{item_id}  

### Create Database
  CREATE DATABASE basicdb;  
  CREATE USER 'admin_basicdb'@'%' IDENTIFIED BY 'adminpass';   
  GRANT ALL PRIVILEGES ON basicdb.* TO 'admin_basicdb'@'%';   
  CREATE USER 'user_basicdb'@'%' IDENTIFIED BY 'userpass';  
  GRANT SELECT, INSERT, DELETE, UPDATE ON basicdb.* TO 'user_basicdb'@'%';  

### Running
  gradlew clean build  
  gradlew bootRun  

Used technologies:
- Java - source compatibility 1.8
- Gradle - 6.6.1
- Spring - 5.2.9.RELEASE
- org.springframework.boot - 2.3.4.RELEASE
