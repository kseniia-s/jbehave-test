Meta:

Narrative:
As a user
I want to perform an action
So that I can achieve a business goal

Scenario: Filtering is working as expected
Given an opened browser with an https://rozetka.com.ua/
When user hover on the Ноутбуки и компьютеры category
And user clicks on the Ноутбуки category
And user applies filters:
|filter|value|
|Производитель|Lenovo|
|Цена min|2000|
|Цена max|25000|
|Процессор|Intel Core i7|
|Объем оперативной памяти|16 - 24 ГБ|
|Операционная система|Windows 10 Home|
|Тип оперативной памяти|DDR4|
!-- Then products are shown according to the filters