Meta:

Narrative:
As a user
I want to perform an action
So that I can achieve a business goal

!-- Lifecycle:
!-- Before:
!-- Scope: STORY
!-- Given an opened browser with an https://rozetka.com.ua/
!-- When page home page is fully loaded
!-- After:
!-- Scope: SCENARIO|STORY
!-- Outcome: ANY
!-- Then user closes a browser

Scenario: Chosen items are added to cart
Given an opened browser with an https://rozetka.com.ua/
When page home page is fully loaded
When user clicks on the Каталог товаров button
And user clicks on the Ноутбуки category
And page products is fully loaded
Then the products are shown
When user clicks on cart icon of 1 products
Then the cart is opened
And 1 products are in the cart




!-- Scenario: User can change the number of ordered products from the cart
!-- Given an opened browser with an https://rozetka.com.ua/
!-- When page home page is fully loaded
!-- And user opens a cart
!-- Then the cart is not empty
!-- When user clicks on plus near any product in the cart
!-- Then the sum is changed and it is correct
!-- When user clicks on minus near any product in the cart
!-- Then the sum is changed and it is correct

!-- Scenario: User can delete product from cart
!-- Given Kseniia previously ordered a product
!-- When a Asus is added to the cart