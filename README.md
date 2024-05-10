# java_automation_solution
This is a repository initiated as an automation solution by JAVA
# when do we need automation testing?
## 1. reducing the cost of regression testing
## 2. build stubborn trustworthiness with developers
## 3. increase the test coverage
# how do we start automation testing?
## punctuate the testing pyramid, the guideline for automation testing is the closer to the code, the more automation testing should be done
## 1. initialize the automation testing goals with the team, including the developers, manual testers, and the product owners
## 2. investigate and choose the right automation testing tools
## 3. create a plan for automation testing, ensure who will contribute to the automation testing, and how to maintain the automation testing
## 4. start the automation testing
# what are the common automation testing tools?
## UNIT TESTING: JUnit, TestNG
### unit test must be followed the program language, for example, JUnit is for JAVA, TestNG is for JAVA；
### and the Unit test code should be together with the source code 
## API TESTING: RestAssured, Postman, SoapUI, Pytest, JMeter
### API testing is to test the API interface, the input and output of the API, and the API performance;
### API testing is an integration testing, it is not only to test the API itself, but also to test the interaction between the API and the database, the API and the front-end
### the most challenging part of API testing is to analyze the API doc, prepare the test data, verify the test result, and fix the dependency issue
## UI TESTING: Selenium, Appium, Cucumber
### UI testing is to test the user interface, the user experience, the user interaction, and the user performance
### UI testing is an end-to-end testing, it is to test the whole system, including the front-end, the back-end, the database, the API, and the server
### the most challenging part of UI testing is to locate the UI element, and the vulnerability of the UI element 
## PERFORMANCE TESTING: JMeter, LoadRunner, K6
### performance testing is to test the system performance, the system scalability, the system reliability, and the system availability
### performance testing is a non-functional testing, it is to test the system under the stress, the load, the volume, the spike, and the endurance
### the most challenging part of performance testing is to simulate the real user behavior, and the real user environment
## how to prove your automation testing is successful?
### testing have to be designed to in parallel with the development, any setup or cleanup should be isolated from the test case
### clean code is essential to the value of automation testing, the code should be readable, maintainable, and reusable,avoid excessive code
### use design pattern to improve the code quality, such as page object model, factory pattern, singleton pattern, and strategy pattern
## scaling the automation projects
### make considerations for the multiple environments, the multiple browsers, the multiple devices, the multiple data, the multiple users, and the multiple scenarios, with using
### of properties file, configuration file, environment variable, and command line argument
## how to measure the value of your automation testing?
### shortened regression testing time: make a note of the time spent on the manual testing, and the time spent on the automation testing, and compare the two
### frequent feedback: receive feedback as needed, the faster the feedback, the less expensive the fix
### trustworthiness: the more the automation testing, the more the trustworthiness
### scalability: 



