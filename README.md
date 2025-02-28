# Customer Management System
This is a Spring Boot application designed to manage customer information. It provides a RESTful API with capabilities to create, read, update, and delete customer records. The application uses an H2 in-memory database for data persistence and is secured with basic authentication.

# Swagger URL:

**http://localhost:8080/swagger-ui/index.html#/**

**username : admin passowrd : admin123**

# Features

**CRUD Operations:** Manage customer records with operations to create, read, update, and delete.

**Search Functionality:** Find customers by email or phone number.

**Data Validation:** Ensures data integrity with validation constraints.

**RESTful API:** Exposes a RESTful interface for client interactions.

**Security:** Basic authentication to protect endpoints.

# Technologies Used
**Spring Boot:** Framework for building the application.

**Spring Data JPA:** Manages database interactions.

**Hibernate:** ORM framework for data persistence.

**H2 Database:** In-memory database for development and testing.

**Spring Security:** Provides authentication and authorization.

**JUnit & Mockito:** Testing frameworks for unit and integration tests.

# Project Structure
src/main/java/com/customer/manage

├── CustomerController.java

├── CustomerService.java

├── CustomerRepository.java

└── Customer.java

# Installation

To set up and run the project locally:

**Clone the repository:**

**git clone https://github.com/someshnawale22/customer-management.git
cd customer-manage**

**Build the project:**

Ensure you have Maven and Java 11 installed. Then, run:

**mvn clean install**

**Run the application:**

Use the Spring Boot Maven plugin to start the application:

**mvn spring-boot:run**

**Access the application:**

Once the application is running, you can access the API at http://localhost:8080/api/customers.

# API Endpoints

**GET /api/customers:** Retrieve all customers.

**GET /api/customers/{id}:** Retrieve a customer by their ID.

**GET /api/customers/search:** Search for a customer by email or phone number.

**POST /api/customers:** Create a new customer.

**PUT /api/customers/{id}:** Update an existing customer’s information.

**DELETE /api/customers/{id}:** Delete a customer by their ID.


# Usage
# Authentication

The API is secured using basic authentication. Use the following credentials for testing:

**Username:** admin

**Password:** admin123

# Example Request
**To create a new customer, send a POST request to /api/customers with JSON data:**

{
    "name": "John Doe3",
    "email": "john.doe3@example.com",
    "phoneNumber": "3213-456-7890",
    "address": {
        "street": "123 Main St",
        "city": "Anytown",
        "state": "Anystate",
        "zipCode": "12345"
    },
    "contactInfo": {
        "secondaryEmail": "john.secondary@example.com",
        "faxNumber": "123-456-7899",
        "socialMediaProfile": "@johndoe"
    },
    "personalInfo": {
        "dateOfBirth": "1980-01-01",
        "gender": "Male"
    },
    "employmentInfo": {
        "companyName": "Tech Corp",
        "jobTitle": "Software Engineer",
        "employmentStartDate": "2020-06-01"
    },
    "additionalPhoneNumbers": [
        "234-567-8901",
        "345-678-9012"
    ],
    "additionalEmailAddresses": [
        "john.alt1@example.com",
     "john.alt2@example.com"
    ]
}


# Testing
The application includes unit and integration tests. To execute them, run:

**mvn test**


# Contributing
Contributions are welcome! Please fork the repository and use a feature branch. Pull requests are reviewed on a regular basis.

# License
This project is licensed under the MIT License. See the LICENSE file for more information.

# Contact
For questions or support, please contact someshnavale123@gmail.com

