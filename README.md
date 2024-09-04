# CarePets Backend Project

## Project Context:
Margarita is about to open her veterinary clinic and has requested the creation of a patient management system. The previous developer prepared a frontend interface using VUE but abandoned the project before developing the backend. Margarita has asked us to complete the project by creating a REST API that stores data in a MySQL database, which can be consumed by the frontend.

The objective of this project is to develop a patient management system in Java that allows Margarita to list, add, edit, and delete patients. The application will also enable users to manage their appointments efficiently (schedule appointments, view a list of appointments, modify, and cancel appointments), maintaining an organized record of them.

## Overview

This project is a RESTful web service for a veterinary patient management system, built using Spring Boot, MySQL, JPA, and Spring Web. It allows users to perform CRUD operations on patients and manage appointments. Postman was used for testing the API endpoints, and the application follows the MVC architectural pattern.

## Table of Contents

- [Overview](#overview)
- [Dependencies](#dependencies)
- [Installation](#installation)
- [Configuration](#configuration)
- [API Endpoints](#api-endpoints)
- [Testing](#testing)

## Dependencies

The project uses the following dependencies:

- Spring Boot
- Spring Web
- Spring Data JPA
- MySQL Connector
- Lombok
- JUnit 5
- Mockito

## Installation

1. Clone the repository:
    ```bash
    git clone https://github.com/yourusername/carepets-backend.git
    cd carepets-backend
    ```

2. Set up the MySQL database:
    ```sql
    CREATE DATABASE db_carepets;
    ```

3. Update the application.properties file with your MySQL credentials:
    ```properties
    spring.datasource.url=jdbc:mysql://localhost:3306/db_carepets
    spring.datasource.username=root
    spring.datasource.password=yourpassword
    spring.jpa.hibernate.ddl-auto=update
    ```

4. Build and run the application:
    ```bash
    ./mvnw spring-boot:run
    ```

## Configuration

The application.properties file contains the configuration for the database connection and server settings:
```properties
spring.application.name=CarePets
spring.datasource.url=jdbc:mysql://localhost:3306/db_carepets
spring.datasource.username=root
spring.datasource.password=
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL8Dialect
server.port=8080
spring.jpa.hibernate.ddl-auto=update
```
## API Endpoints
The API provides the following endpoints
### Appointment Controller
#### Get All Appointments
```GET /appointment/appointments```\
Response: A list of all appointments.
#### Get appointment by Status
```GET /appointment/appointments/status?status=<insert_string_here>```\
Response: The appointment with the specified status.
#### Get appointment by Type
```GET /appointment/appointments/type?typeConsult=<insert_string_here>```\
Response: The appointment with the specified consult type.
#### Get appointment by ID
```GET /appointment/appointments/{id}```\
Response: The appointment with the specified ID.
#### Create a New appointment
```POST /appointment/appointments```\
Request Body: JSON representation of the new appointment.
#### Update an appointment
```PUT /appointment/appointments/{id}```\
Request Body: JSON representation of the updated appointment.
#### Delete an appointment
```DELETE /api/v1/images/{id}```\
Deletes the appointment with the specified ID.

### Guardian Controller
#### Get All Appointments OJO ESTO NO!!!
```GET /appointment/appointments```\
Response: A list of all appointments.
#### Get appointment by ID
```GET /appointment/appointments/{id}```\
Response: The appointment with the specified ID.
#### Create a New Guardian
```POST /guardian/guardians```\
Request Body: JSON representation of the new guardian.
#### Update a Guardian
```PUT /guardian/guardians/{id}```\
Request Body: JSON representation of the updated guardian.
#### Delete a Guardian
```DELETE /guardian/{id}```\
Deletes the guardian with the specified ID.


### Pet Controller
#### Get All Pets
```GET /pet```\
Response: A list of all pets.
#### Create a New Guardian OJO ESTO NO
```POST /guardian/guardians```\
Request Body: JSON representation of the new guardian.
#### Update a Pet
```PUT /pet/{id}```\
Request Body: JSON representation of the updated pet.
#### Delete a Pet
```DELETE /pet/pets/{id}```\
Deletes the pet with the specified ID.

## Testing
Unit tests are written using JUnit 5 and Mockito. The tests cover the controller, service, and model layers.

