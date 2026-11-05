# ThogaKade — Point-of-Sale Desktop App

A desktop point-of-sale application for a retail store, built with **JavaFX** and **MySQL**. Staff can manage customers and items and build orders with a live cart.

## Features

- **Customer management**: add, view, update and delete customers
- **Item management**: manage item code, description, unit price and stock quantity
- **Place order**: add items to a cart, with the total for each line and for the whole order updated as you go

## Tech stack

- **Java**, **JavaFX** with **FXML** views
- **JDBC** with **MySQL**
- MVC structure

## Project structure

```
src/
├── Main.java                         → launches the JavaFX app
├── controller/
│   ├── CustomerFormController.java   → customer screen
│   ├── CustomerController.java       → customer database operations (JDBC)
│   ├── CustomerService.java          → customer service interface
│   ├── ItemFormController.java       → item screen
│   └── PlaceOrderFormController.java → order screen and cart
├── db/
│   └── DBConnection.java             → Singleton database connection
├── model/
│   ├── Customer.java                 → id, name, address, salary
│   ├── Item.java                     → code, description, unitPrice, qty
│   └── CartTm.java                   → cart row: code, description, qty, unitPrice, total
└── view/
    ├── customer-form.fxml
    ├── item-form.fxml
    └── place-order-form.fxml
```

### Design notes

- **MVC**: FXML files define the views, controllers handle UI events, and model classes hold the data.
- **Singleton connection**: `DBConnection` creates one shared MySQL connection that every controller reuses.
- **Prepared statements**: insert and update queries use `PreparedStatement` with parameter placeholders.
- **Observable lists**: JavaFX `TableView`s are bound to `ObservableList`s, so the cart and tables update immediately when data changes.

## Getting started

### Prerequisites

- JDK 11 or newer
- JavaFX SDK (if your JDK does not include JavaFX)
- MySQL 8
- IntelliJ IDEA (recommended; the project includes an IntelliJ module file)

### 1. Create the database

.

```sql
CREATE DATABASE ThogaKade;
USE ThogaKade;

CREATE TABLE customer (
  id      VARCHAR(10) PRIMARY KEY,
  name    VARCHAR(100),
  address VARCHAR(200),
  salary  DOUBLE
);

CREATE TABLE item (
  code        VARCHAR(10) PRIMARY KEY,
  description VARCHAR(200),
  unitPrice   DOUBLE,
  qtyonHand   INT
);
```

### 2. Configure the connection

Update the username and password in `src/db/DBConnection.java` to match your MySQL setup.

### 3. Run

1. Open the project in IntelliJ IDEA.
2. Add the JavaFX SDK as a library if needed, along with the MySQL Connector/J driver.
3. Run `Main.java`.


