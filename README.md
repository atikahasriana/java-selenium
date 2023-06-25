# GTL/Tokopedia SDET Home Assignment
This project showcases an automation script as part of my submission for the GTL/Tokopedia Home Assignment as an SDET (Software Development Engineer in Test).

# Getting Started

## Prerequisites
Before you can run this project, you must have the following software installed on your computer:

- Java Development Kit (JDK) version 8 or later
- Apache Maven
- Google Chrome web browser

## Installation
1. Clone this repository to your local machine using `git clone https://github.com/marizala/java-selenium.git`
2. Navigate to the project directory using the command line.
3. Install the dependencies and run the smoke test plan (configured).  
   `mvn clean install`

## Tests

This project contains 2 sample test cases that demonstrate how to use Selenium to interact with web pages. 

### Test Cases
- `TC001_AddToCart_Checkout`: Adds product to the cart, sorts the product list by price (High to Low) and verifies that checkout is successful.
- `TC002_Logout`: User successfully logging in and out of the application.

## How to run the tests

#### Running a specific test case
1. Navigate to the project directory using command line.
2. Run the following command but replace "TestClass" with the name of the test case.   
   `mvn test -Dtest=TestClass`  
   E.g. `mvn test -Dtest=TC001_AddToCartCheckout`

## Dependencies

This project uses the following Java and Selenium dependencies:

- Selenium Java version 4.9.0
- TestNG version 7.7.0
