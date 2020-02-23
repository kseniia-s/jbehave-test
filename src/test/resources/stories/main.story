Meta:

Narrative:
As a user
I want to login to the site
So that I can order products online

Scenario: User can successfully log in to the cabinet
Given an opened browser with an https://rozetka.com.ua/
When user clicks on the войдите в личный кабинет button
Then popup with login form is shown
When user fills <email> and <password> fields
And user clicks on the Войти button
Then user is logged in

Examples:
|<email>|<password>|
|kseniia.semenova91@gmail.com|123123123|
|milawka.ksu@gmail.com|321321321|

Scenario: Search is working. Appropriate products are shown after search
Given an opened browser with an https://rozetka.com.ua/
When user writes ноутбук in the search field
And user clicks on the Найти button
Then the products are shown
And each product has a keyword ноутбук in a short description
