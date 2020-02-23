Meta:

Narrative:
As a user
I want to perform an action
So that I can achieve a business goal

Scenario: Chosen items are added to cart
Given an opened browser with an https://rozetka.com.ua/
When user clicks on the Каталог товаров button
And user clicks on the Ноутбуки category
And user clicks on cart icon at the random 2 items
And user opens a cart by clicking on the cart icon in the top
Then the chosen products are in the cart

Scenario: User can change the number of ordered products from the cart
Given an opened browser with an https://rozetka.com.ua/
When user opens a cart by clicking on the cart icon in the top
Then the cart is not empty
When user clicks on plus near any product in the cart
Then the sum is changed and it is correct
When user clicks on minus near any product in the cart
Then the sum is changed and it is correct