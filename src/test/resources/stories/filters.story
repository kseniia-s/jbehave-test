Meta:

Narrative:
As a user
I want to perform an action
So that I can achieve a business goal

Lifecycle:
Before:
Scope: SCENARIO
Given an opened browser with an https://rozetka.com.ua/
When page home page is fully loaded

Scenario: Filtering is working as expected
When user hover on the Laptops and computers category
And user clicks on the Laptops category
And user applies filters:
|filter|value|
|Manufacturer|Lenovo|
|Price min|2000|
|Price max|25000|
|CPU|Intel Core i7|
|RAM size|16 - 24|
|OS|Windows 10 Home|
|RAM type|DDR4|
Then the products are shown