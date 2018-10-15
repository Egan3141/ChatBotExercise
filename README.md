# ChatBot Interview Exercise

Author: Tyler Egan

## Overview

A Chatbot that can store and retrieve users and messages.

## Manifest

Chatbot.java: The main program that runs everything.
ChatBotTests.java: Unit tests for Chatbot.java.
Message.java: Middleware between Chatbot and FakeDatabase for message handling.
MessageTests.java: Unit tests for Message.java.
User.java: Middleware between Chatbot and User for profile handling.
UserTests.java: Unit tests for User.java.
FakeDatabase.java: A fake database to ensure other code is handled in a realistic way.
FakeDatabaseTests.java: Unit tests for FakeDatabase.java.
README.md: This file.

## Building the project

To compile:
Once all files are in one folder, compile with "javac Chatbot.java"

To run:
"java Chatbot"


## Features and usage

Chatbot.java allows users to create and modify accounts with a very simple interaction phase.

## Assumptions

	Chatbot: I assumed this was the main program that ran everything.
	
analyzeMessage: I assumed it would be best to have this code separated out into it's own method.

createUser: I assumed this would call User's createUser method. Since testing this method would actually
 test a method in User, I am assuming it is not meaningful to test it beyond null input.
 
parseAccountInfo: Because this is a private method, I assumed it is best to leave it private and consider
 it tested by the methods that call it.
 
receiveMessage: I assumed this method would receive a message from the user. Becuase it is just a scanner
 of System.in, I also assumed it was meaningless to test.
	
sendMessage: I assumed this method was intended to respond to the user as the primary interaction of this
 entire program. However, because I needed a way to test this method, I decided to have it return the 
 response and let the main method print it. I was unable to run 2 of my tests for it though due to a
 private global variable. I ask for help with how to properly handle this in the test class.

updateUserProfile: I assumed this would call the modifyClient method in database. As such, I only tested
 null inputs. 


	Message: I assumed this was an object file that controls messages and interacts with the database.
	
constructor: I assumed that the storeMessage method would be equivalent to calling the constructor. As
 such, I decided to make the constructor protected and assumed it did not need tested.
 
storeMessage: I assumed this would be used to store messages for responses to the user.
 
retrieveMessage: I assumed this would contact the database directly and return the object. As such, I only
 test null input.


	User: I assumed this was on object file that controls users.
	
constructor: I assumed that the createUser method would be equivalent to calling the constructor. As
 such, I decided to make the constructor protected and assumed it did not need tested.
	
createUser: I assumed a database would not check for valid input. Because of this I coded createUser
 to return a string that describes what happened. I.E. returns "Duplicate" if the user already exists.

retrieveUser: I assumed this would contact the database directly and return the object. As such, I only
 tested null inputs. 

 
	FakeDatabase: I assumed it was okay to create a fake database to have reasonable code.

constructor: Nothing to say.

addMessage: Due to testing, I may have added null checks on a few too many things.

addUser: Due to testing, I may have added null checks on a few too many things.

fakeHash: Seeing as this was not an intended part of the project and is private, I assumed it did not
 need tested.
 
getMessage: Due to this being a core method, I assumed there was very little that could be tested.

getUser: Due to this being a core method, I assumed there was very little that could be tested.

modifyClient: I assumed it was best to have this method in the database as well as verifyClient.

verifyClient: I assumed this should only be accessed by this class and made it private. As such, no
 testing was performed for this method.