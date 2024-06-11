Feature: Compose Email
  As a user
  I want to compose an email in Gmail
  So that I can send messages

  Scenario: Compose an email
    Given I am on the Gmail login page
    When I log in with valid credentials
    And I click on the compose button
    And I fill in the email subject and body
    And I click on the send button
    Then the email should be sent successfully
