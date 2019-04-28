Feature: Delete Article
	As the editor of the article or as an administrator
	I want to delete an article
	
Scenario: Delete Article
	Given the user is logged in
	And the article page is opened
	When the "Delete" button is clicked
	And the deletion is confirmed
	Then a status message is shown