# Stanislav Skorobogatov

WebAPIs bascis

Technologies used:
- Java 20
- Lombok
- Log4j
- Jackson
- Maven
- Cucumber
- Allure

You can see logs in the console to get more information about the test execution process.

## How to run tests
1. Get your DropboxAPI Bearer token and paste it in to src/test/resources/config.properties. You can get the token:
   - Go to https://www.dropbox.com/developers/
   - Create new app
   - Provide necessary permissions for file content and metadata
   - You can generate new access token in the OAuth 2 section
   - Copy token from Generated access token field
   - Paste it in to src/test/resources/config.properties
2. Open terminal in project folder
3. You can run tests by executing the command: `mvn test`
4. You can check allure report by executing the command: `allure serve allure-results`