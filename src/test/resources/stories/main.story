Meta:
@author Kseniia
@themes UI Usability

Narrative:
As a user
I want to login to the site
So that I can order products online

Scenario: User can successfully log in to the cabinet
Given an opened browser with an https://rozetka.com.ua/
When page home page is fully loaded
When user clicks on the log in to your account button
Then popup with login form is shown
When user fills required fields in login form:
|email|password|
|milawka.ksu@gmail.com|Ksu123|
And user clicks on the 'Log in' button on the login popup
Then user Kseniia is logged in

Scenario: Search is working. Appropriate products are shown after search
Given an opened browser with an https://rozetka.com.ua/
When user types Asus in the search field
And user clicks on the Search button
Then the products are shown

