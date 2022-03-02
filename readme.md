# Gracker - The Grocery Tracker
#### v1.0 September 2021

## Summary
- Automates the gathering of grocery price data
- Tracks and analyzes the grocery price data
- Serves and displays the gathered data to localhost
- Automation methods
  - Selenium
  - ReST

## Store whose pricing is tracked
  - Target
  
## Store whose pricing is planned to be tracked
  - WalMart
  - Smiths
  - Dick's Market
  - Lee's Market

## General Styling Guide
  - Packages should be all lowercase ASCII letters.
  - Classes and Interfaces should follow a mixed case (aka "camel case"), with each subsequent word's first letter
        capitalized
    - e.g. `class TestClass`
  - Methods should be verbs, and should follow a mixed case (aka "camel case") pattern, with the first letter of the
        first word in lower case.
    - e.g. `void testMethod()`
    - Braces follow the Kernighan and Ritchie style ("Egyptian brackets") for nonempty blocks and block-like constructs:
      - No line break before the opening brace.
      - Line break after the opening brace.
      - Line break before the closing brace.
      - Line break after the closing brace, only if that brace terminates a statement or terminates the body of a method, constructor, or named class. For example, there is no line break after the brace if it is followed by else or a comma.
      - Examples:
    ```
    return () -> {
      while (condition()) {
        method();
      }
    };
    
    return new MyClass() {
      @Override public void method() {
        if (condition()) {
          try {
            something();
          } catch (ProblemException e) {
            recover();
          }
        } else if (otherCondition()) {
          somethingElse();
        } else {
          lastThing();
        }
      }
    };
     ```
    - Variables are similar to methods, in that they should follow a mixed case (aka "camel case") patter, with a
          lowercase first letter.
    - Constants should be all uppercase, with underscores replacing spaces.
      - e.g. `int MAX_RETRY_COUNT = 5;`

## Coding Standards
  - Methods should only test one thing (not always possible)
    - e.g. `Create configuration`
    - e.g. `Test Configuration`
    - e.g. `Delete Configuration`
  - Methods should be written in tandem with unit, negative, and appropriate end to end testing
    - After writing a test to perform as expected, make sure if it isn't configured as expected it fails
    - When catching exceptions, catch specific exceptions, log the error and throw a new exception so the test fails
    - Use logging frequently, it helps to quickly debug code and write tests (every method should have logging)
    - Add unit tests to try and break your method. 
    - You wont always have an opportunity to add end to end testing, but add them where needed.
        - Merge requests will be denied if appropriate testing isn't written 
  - Selenium code should use the Page Object Model making each page a class
    - Use inheritance to pull in common variables and methods for your product
    - The top of the class should contain web elements
    - Inside the class should contain methods that do one thing with JavaDoc
    - Inner classes can be used to help limit scope (classes using this logic can
      only be called in a groovy class)
    - e.g.
    ```
        /**
         * method triggers click event on New Setting selection to access the Edit Setting Definition
         * pop-up window
         * @return returns new instance of inner class clickNewSetting in order to select OK and
         * cancel buttons in this pop-up window
         */
        def clickNewSetting() {
            newSettingElement.click()
            return new clickNewSetting(driver)
        }

        /**
         * Inner Class provides methods to select OK or Cancel in the Edit Setting Definition pop-up window
         */
        private class clickNewSetting extends CreateConnectorSharedTabs.newSetting<clickNewSetting> {
            private @FindBy(id = 'saveButton')              WebElement editSettingOkButton
            private @FindBy(id = 'cancelButton')            WebElement editSettingCancelButton

            clickNewSetting(WebDriver driver) {
                super(driver)
            }

            /**
             * method triggers click event on OK button
             * @return returns instance of main Desktop tab
             */
            def clickOK() {
                editSettingOkButton.click()
                log.info("Clicked Edit Setting Definition OK button")
                return new openDesktopTab(driver)
            }

            /**
             * method triggers click event on Cancel button
             * @return returns instance of main Desktop tab
             */
            def clickCancel() {
                editSettingCancelButton.click()
                log.info("Clicked Edit Setting Definition Cancel button")
                return new openDesktopTab(driver)
            }
        }
    ```

  - Selenium Web Programs
    - Tests should be made up of methods, and logic (such as loops) should be in a utils class
    - Each test class should inherit from either a UI base test or a REST base test
    - A test should never call another test.
    - Helper methods should go in a utils class (when possible the Util should be a java class)
    - e.g. Web Test
    ```
      @Test
      void changeStoreLocation(){
          new StoreLogin()
            .clickStoreBanner()
            .addNextZipCode()
            .clickSubmit
      }
    ```



## Installation
  - `git clone <repo url>`
  - `git checkout <name>-<project>`


### IntelliJ
  - Open new project with existing sources
  - Import project from external module | Maven
  - Select maven project to import
  - Set the groovy folder as sources in project structure

## Contributing
  - This project uses GitFlow. For an in depth overview of GitFlow, check [here](https://www.atlassian.com/git/tutorials/comparing-workflows/gitflow-workflow).
  - In summary, branch off of develop, merge into develop when a feature/bugfix/thing is complete.

## GitHub
  - Our merge requests and code reviews will be done using Github's tools.
  - The master branch is protected.
  - The develop branch is our mainline development branch.

## Authors
  - Jonathan Zollinger
