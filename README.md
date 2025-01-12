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

## ⚙️ **Flowchart of the Project**
**The Following is a very basic and simplified flowchart for understanding the rudimentary process of a cell input and evaluation, it is by no means to be taken literally, only to give a basic top down view of the cardinal flow of the chart and its principles, without getting into the intricacies of the Parsing Engine composed and implemented for this project.**

----------
[![](https://mermaid.ink/img/pako:eNp1VW1v2jAQ_isnf6ZVCi2FTNsUArRQChRoqzagyUtMyQY2chxUBvz3Obbztq75EDjf4-funjs7B-SzgCAbvXG8XcGs_WVOQT6ONxWYiwWcnX2Dltej21iAS9ZraGOBFxrUUl730ItAA7qMb-I1_n7Sfv12E9TxhURHaHtjzCOS4hZFwJAdoeO5K-L_ht4ShvHmJ-EfEN0cMSPvQvo1oq1SuTFeww9PeB0GodgbmhtFoxaPcOt1dngdY_FvOhrVozuN63kTImJOocM54zYYRxYj-cV5IrcqkX6iyYAsBciCBWBTTiqMBt0loEn4tvoEpd_9XL6BbArjRBOXBOpnAt17LtvIXhhUuTT9vssZh4ZRZ1GivMsoRxmlhv2Pc6AqGntjwpfSDaMt4ViEjBrUvfZrY1g0RqmhzbEyHw66kZIL3JD7MhyHNtkSGhDq71OFHvJKJlmbJpMf7os76CyKmKSOaQox5QQwIVG8znvXUbFnSVu0FKa5eo7SoLM86KORryTcLAv45E2JALHfEsBROq-5Zl0V7tlwlMb5Ubmm2ngqGs-5UfS-GpY8TvFcOM6BxzRRU43szhwLECQSkSnLcTS0JVXasB2BryAr91dSeCujc_SRd9r5MZSjS6hYkYhE0MJrTH0SmDIdFf2Yrh7BSQ-46iujEfFjEcpYelwYj8o7H-nPfG-32OHuaHKfZ9UpHmznphBErX2k75TPuHP7OXfp0nB6H7h17yNwgl_Yl0qAYEVN0oi9Ek3fG3PmkyjKrhFMk3FUOWRTWdiYp3r3ear9dDbSlanu1sB7xqFQOVM5HBAml7XchipoQ-SAh4H8ABzUSCGZ9YbMkS3_BmSJZSJzNKcnCcWxYNM99ZEteEwqiLP4bYXsJV5H0oq3gbxK2yGWH5JNCtli-spY0UT2Ab0ju3ZdPbcatSvrstGsNZvWdQXtkX1RvTiv1qu1erVu1RrXF7WrUwX9UQTW-ZWln0b9stmwmtXTX_ncFZQ?type=png)](https://mermaid.live/edit#pako:eNp1VW1v2jAQ_isnf6ZVCi2FTNsUArRQChRoqzagyUtMyQY2chxUBvz3Obbztq75EDjf4-funjs7B-SzgCAbvXG8XcGs_WVOQT6ONxWYiwWcnX2Dltej21iAS9ZraGOBFxrUUl730ItAA7qMb-I1_n7Sfv12E9TxhURHaHtjzCOS4hZFwJAdoeO5K-L_ht4ShvHmJ-EfEN0cMSPvQvo1oq1SuTFeww9PeB0GodgbmhtFoxaPcOt1dngdY_FvOhrVozuN63kTImJOocM54zYYRxYj-cV5IrcqkX6iyYAsBciCBWBTTiqMBt0loEn4tvoEpd_9XL6BbArjRBOXBOpnAt17LtvIXhhUuTT9vssZh4ZRZ1GivMsoRxmlhv2Pc6AqGntjwpfSDaMt4ViEjBrUvfZrY1g0RqmhzbEyHw66kZIL3JD7MhyHNtkSGhDq71OFHvJKJlmbJpMf7os76CyKmKSOaQox5QQwIVG8znvXUbFnSVu0FKa5eo7SoLM86KORryTcLAv45E2JALHfEsBROq-5Zl0V7tlwlMb5Ubmm2ngqGs-5UfS-GpY8TvFcOM6BxzRRU43szhwLECQSkSnLcTS0JVXasB2BryAr91dSeCujc_SRd9r5MZSjS6hYkYhE0MJrTH0SmDIdFf2Yrh7BSQ-46iujEfFjEcpYelwYj8o7H-nPfG-32OHuaHKfZ9UpHmznphBErX2k75TPuHP7OXfp0nB6H7h17yNwgl_Yl0qAYEVN0oi9Ek3fG3PmkyjKrhFMk3FUOWRTWdiYp3r3ear9dDbSlanu1sB7xqFQOVM5HBAml7XchipoQ-SAh4H8ABzUSCGZ9YbMkS3_BmSJZSJzNKcnCcWxYNM99ZEteEwqiLP4bYXsJV5H0oq3gbxK2yGWH5JNCtli-spY0UT2Ab0ju3ZdPbcatSvrstGsNZvWdQXtkX1RvTiv1qu1erVu1RrXF7WrUwX9UQTW-ZWln0b9stmwmtXTX_ncFZQ)
----------

