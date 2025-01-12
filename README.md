# **Ariel University Intro2C.S Ex2 - Spreadsheet Cell Evaluation System**

## Overview

This project is done as part of the Intro2C.S course in the Ariel University as part of the computer science degree.
The Projects code contains the inner workings of a **spreadsheet cell evaluation system** where each cell can contain and be evaluated as:

-   **Numbers**
-   **Text**
-   **Formulas**
-  **A Refrence to another cell**

The core functionality of the code includes interpreting formulas, handling circular dependencies, validating formula syntax, and evaluating expressions, including mathematical operations and cell references.


##  **Core Learning Elements**

### 1. **Object-Oriented Programming (OOP)**

-   **Encapsulation**: We encapsulated data (e.g., cell contents) and methods (e.g., parsing formulas) into classes.
-   **Abstraction**: We abstracted the details of formula parsing and validation, making it easier to work and handle complex formulas.
-   **Inheritance & Interfaces**: By using interfaces, we were able to create a flexible system for handling different cell types (Text, Number, and Formula), all with the same methods and functions, based on a "Type" property which identifies each cell object.

### 2. **Formula Parsing & Evaluation**

-   Implemented a robust formula parser to handle cell references, operators, and parentheses.
-   Utilized recursion to break down complex formulas into manageable parts for evaluation, based on the principle that all formulas are forms of (Function+Operator+Function).
-   Validated formulas to ensure they follow correct syntax, avoiding errors such as circular dependencies.

### 3. **Mathematical Operations & Error Handling**

-   Implemented common mathematical operations (+, -, *, /) and ensured proper handling of cases such as division by zero, multiple operators, and invalid formula syntax.
-   Implemented error handling for common issues like invalid formulas or cyclic references between cells.

### 4. **String Manipulation & Regular Expressions**

-   Used regular expressions to identify and manipulate parts of formulas, such as operators and parentheses.
-   Applied string handling techniques to validate and "prep" user input for entry into the Parsing Engine.


##  **Features & Functionalities**

###  **Cell Types**

-   **Number**: Supports numerical values like `5`, `100.25`, etc.
-   **Text**: Supports plain text values.
-   **Formula**: Supports cell references, basic mathematical operations, and parentheses for expressions like `=A1+B1`.

###  **Formula Evaluation**

-   **Recursive Parsing**: Formulas are broken down recursively for evaluation.
-   **Operator Precedence**: Supports correct precedence for operations like addition, subtraction, multiplication, and division.
-   **Parentheses Handling**: Proper handling of parentheses to ensure correct expression evaluation.

###  **Error Handling**

-   **Formula Format Errors**: Catches invalid syntax in formulas (e.g., consecutive operators, parentheses order).
-   **Circular Dependencies**: Detects and prevents circular references, ensuring that no formula depends on itself, no matter how deep the circular connection.


##  **Concepts Covered**

-   **Recursion**: Key for evaluating complex formulas.
-   **Regular Expressions**: Used to validate and manipulate formula strings.
-   **Error Handling**: Ensures the system is robust and prevents crashes from invalid user input.
-   **Data Structures**: Efficient use of 2D arrays for storing cell references, cell depths and evaluation states.

## ⚙️ **Picture of the Project**

----------
![Screenshot 2025-01-12 205235](https://github.com/user-attachments/assets/28f5890c-f918-4ba3-ac93-fefe2656b664)

----------

