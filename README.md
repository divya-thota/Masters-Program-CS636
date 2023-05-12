# Vinyl Station E-commerce Web Application

Vinyl Station is a web application built using Java Servlets, HTML, CSS, JavaScript, and Oracle database. This application allows customers to browse and purchase vinyl records. Bands can also upload their music for sale on the website. Employees can manage the customer orders.

## Features

### Customer

- Register a profile
- View top trending tracks
- Browse vinyl records and tracks
- Add vinyl records to cart
- customize record tracks
- Purchase vinyl records
- View order history and status

### Employee

- View pending orders
- View Team performance
- View monthly progress
- Make records
- Update invoice status

### Band

- Upload music for sale
- View sales history
- View trending tracks
- View progress over the years


## Requirements

- Java SE Development Kit 8 or higher
- Apache Tomcat 9.0 or higher
- Oracle Database 11g or higher
- Eclipse IDE for Enterprise Java Developers

## Installation

1. Clone the repository:

```
git clone https://github.com/divya-thota/Masters-Program-CS636.git
```

2. Import the project into Eclipse:

- Click File > Import
- Select General > Existing Projects into Workspace
- Choose the root directory of the cloned repository

3. Set up the database:

- Open the SQL script in the database directory
- Run the script to create the necessary tables and data

4. Configure the database connection:

- Open the DBUtil.java file in the utils package
- Replace the database URL, username, and password with your own

5. Deploy the project to Tomcat:

- Right-click on the project in Eclipse and select Export
- Choose Web > WAR file
- Select the project and choose a destination folder
- Copy the WAR file to the Tomcat webapps directory

6. Start Tomcat:

- Open a terminal or command prompt
- Navigate to the Tomcat bin directory
- Run the startup script

```
./startup.sh (Unix-based systems)
startup.bat (Windows)
```

7. Access the application:

- Open a web browser
- Go to `http://localhost:8080/VinylStation`
