Feature: Job management test

  Scenario: Test addition and deletion of job and all its fields

    Given user enters login page

    When user logs in
    And user loads Admin page
    And user loads Job Titles
    And user clicks on the Add button
    And user adds new job with title "New Job By Student", description "My new description", and note "My new note"
    Then the new job "New Job By Student" is on the main page

    When user selects new job and deletes "New Job By Student"
    And user clicks 'Yes, Delete'
    Then the job "New Job By Student" is removed

