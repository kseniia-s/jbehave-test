Meta: @keyword hello

Narrative:
As a user
I want to be able to make a search
So that I can get search results

Scenario: Word “Automation” exists in all search items’ titles
Given an opened browser with an https://www.google.com/
When user searches by keyword Automation
Then the word Automation exists in all search items’ titles on the page