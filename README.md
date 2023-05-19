# Stanislav Skorobogatov

WebUI task

Technologies used:

- Java 20
- Lombok
- Log4j
- Maven
- Cucumber
- Selenium
- Allure

You can see logs in the console to get more information about the test execution process.

The tests are run in the FireFox browser by default. 

You can add driver for other browsers in the `src/test/resources/drivers` folder and change the browser in the ObjectFactory class.

## How to run tests

1. Open terminal in project folder
2. You can run tests by executing the command: `mvn test`
3. You can check allure report by executing the command: `allure serve allure-results`