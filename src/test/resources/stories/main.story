Meta:
@smoke
@main_functions,

Narrative:
As a user
I want to login to the site
So that I can order products online

Lifecycle:
Before:
Scope: SCENARIO
Given an opened browser with an https://rozetka.com.ua/
When page home page is fully loaded

Scenario: User can successfully log in to the cabinet
When user clicks on the log in to your account button
Then popup with login form is shown
When user fills required fields in login form:
|email|password|
|milawka.ksu@gmail.com|Ksu123|
And user clicks on the 'Log in' button on the login popup
Then user Kseniia is logged in

Scenario: Search is working. Appropriate products are shown after search
Meta:
@search
When user searches for Asus
And page products is fully loaded
Then the products are shown

