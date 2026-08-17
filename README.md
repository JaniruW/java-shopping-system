# Java Online Shopping Management System

A Java-based online shopping management system developed as part of the **Object-Oriented Programming** module at the University of Westminster.

The application demonstrates core Object-Oriented Programming principles through a combination of a **console-based management system** and a **Graphical User Interface (GUI)** for customers.

## Overview

The system is designed to manage an online catalogue containing different types of products, including **Electronics** and **Clothing**.

The application provides functionality for managing products, displaying products through a graphical interface, adding products to a shopping cart, calculating purchases, and applying applicable discounts.

The project also includes automated tests for key components of the system.

## Features

### Product Management

* Add new products to the system
* Support for different product types:

  * Electronics
  * Clothing
* Delete products using their product ID
* Display available products and their details
* Sort products alphabetically by product ID
* Save product information to a file
* Load saved product information when the application starts

### Object-Oriented Design

The project applies several core Object-Oriented Programming concepts, including:

* **Inheritance**
* **Encapsulation**
* **Abstraction**
* **Polymorphism**
* **Interfaces**
* **Constructors**
* **Getters and setters**
* **Object composition**

The main product hierarchy is structured around an abstract **Product** class with specialised **Electronics** and **Clothing** subclasses.

### Graphical User Interface

The GUI allows users to:

* View available products
* Filter products by category
* Display product information in a table
* Sort products
* Select a product and view its details
* Add products to a shopping cart
* View the shopping cart
* Calculate the final purchase cost
* Apply applicable discounts

Products with low availability can also be visually highlighted in the product table.

### Shopping Cart

The shopping cart allows users to:

* Add products
* Remove products
* View selected products
* Calculate the total purchase price
* Apply category-based discounts
* Apply the first-purchase discount where applicable

### File Persistence

Product information can be saved to a file and loaded again when the application is restarted, allowing the system to maintain the product catalogue between executions.

## Project Structure

The project is organised into the following main components:

* **Product** — Defines common product properties and behaviour
* **Electronics** — Represents electronic products
* **Clothing** — Represents clothing products
* **User** — Represents a user account
* **ShoppingCart** — Manages products selected for purchase
* **ShoppingManager** — Defines shopping management operations
* **WestminsterShoppingManager** — Implements product management and console operations
* **GUI** — Provides the graphical interface for customers
* **Main** — Entry point of the application
* **ClothingTest** — Tests clothing-related functionality
* **WestminsterShoppingManagerTest** — Tests shopping manager functionality

## Technologies Used

* **Java**
* **Object-Oriented Programming**
* **Java Swing**
* **JUnit**
* **File I/O**
* **IntelliJ IDEA**
* **Git**
* **GitHub**

## OOP Concepts Demonstrated

### Abstraction

The **Product** class provides the common structure for different product types while allowing specialised subclasses to extend its functionality.

### Inheritance

**Electronics** and **Clothing** inherit common attributes and behaviour from **Product**.

### Encapsulation

Product and user information is managed through private attributes and appropriate getter and setter methods.

### Polymorphism

Different product types can be handled through their common **Product** type while retaining their specialised behaviour.

### Interfaces

The **ShoppingManager** interface defines the operations required for managing the shopping system, which are implemented by **WestminsterShoppingManager**.

## Testing

The project includes automated test classes for important parts of the application.

Testing was used to verify the behaviour of key system components and ensure that the implemented functionality operates as expected.

## How to Run

1. Clone this repository from GitHub.
2. Open the project using **IntelliJ IDEA**.
3. Configure a compatible Java JDK.
4. Locate **Main.java** inside the `src` directory.
5. Run **Main.java** to start the application.
6. Use the console menu to access the available management functions and launch the customer GUI.

## Example Workflow

The typical application workflow is:

**Start Application → Management Console → Manage Products → Open Customer GUI → Browse Products → Select Product → Add to Cart → View Shopping Cart → Calculate Total**

## Learning Outcomes

This project provided practical experience in:

* Designing object-oriented software
* Applying inheritance and encapsulation
* Developing a Java GUI
* Working with interfaces and class hierarchies
* Implementing file-based data persistence
* Designing and executing automated tests
* Validating user input and handling errors
* Structuring a Java application into reusable components
* Using IntelliJ IDEA and Git for software development

## Academic Context

**Module:** Object-Oriented Programming
**Module Code:** 5COSC019C
**Institution:** University of Westminster
**Academic Year:** 2023/24

This project was developed as part of university coursework.

