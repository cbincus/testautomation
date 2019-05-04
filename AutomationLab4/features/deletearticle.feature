Feature: Delete Article
	As the editor of the article or as an administrator
	I want to delete an article
	
Background: the user is logged in
	Given the login page is opened
	When I submit my username and password
	Then I should be logged in
	
Scenario: Delete article
	Given the article page is opened
	When the "Delete" button is clicked
	And the deletion is confirmed
	Then a status message is shown