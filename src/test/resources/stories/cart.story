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

Scenario: Chosen items are added to cart
When user clicks on the Каталог товаров button
And user clicks on the Ноутбуки category
And page products is fully loaded
Then the products are shown
When user clicks on cart icon of 1 products
Then the cart is opened
And 1 products are in the cart


Scenario: User can change the number of ordered products from the cart
When user opens a cart
Then the cart is not empty
And amount to be paid for the first product is greater than 0
When user clicks on plus near any product in the cart
Then the amount to be paid is changed
When user clicks on minus near any product in the cart
Then the amount to be paid is changed

!-- Scenario: User can delete product from cart
!-- Given Kseniia previously ordered a product
!-- When a Asus is added to the cart