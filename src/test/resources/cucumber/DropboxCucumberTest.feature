Feature: Dropbox API testing

  Scenario: Upload file
    Given the file is created
      When user uploads the file
      Then the file is on Dropbox

  Scenario: Get file's metadata
    Given the file is created
      When user uploads the file
        And user requests file's metadata
      Then file's metadata is received

  Scenario: Delete file
    Given the file is created
      When user uploads the file
        And user deletes the file
      Then there is no file on Dropbox
