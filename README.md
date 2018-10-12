# ChatBot Interview Exercise

Author: Tyler Egan

## Overview

A Chatbot that can store and retrieve users and messages.

## Manifest

Chatbot.java: The main program that runs everything.
Message.java: Middleware between Chatbot and FakeDatabase for message handling.
User.java: Middleware between Chatbot and User for profile handling.
FakeDatabase.java: A fake database to ensure other code is handled in a realistic way.
README.md: This file.

## Building the project

To compile:
Once all files are in one folder, compile with "javac Chatbot.java"

To run:
"java Chatbot"


## Features and usage

TODO


## Assumptions

	Chatbot: I assumed this was the main program that ran everything.
	
sendMessage: 
receiveMessage:
createUser: 
updateUserProfile: 


	Message: I assumed this was an object file that controls messages and interacts with the database.
	
constructor: I assumed that the storeMessage method would be equivalent to calling the constructor. As
 such, I decided the constructor should only be accessible by this class and made it private.
 
storeMessage: I assumed a database would not check for valid input. Because of this I coded storeMessage
 to return a string that describes what happened. I.E. returns "Null" if a null argument was passed.
 
retrieveMessage: I assumed this would contact the database directly and return the object. As such, it is
 a 1 line piece of code that contacts the database. Since testing this method would actually test a method
 in the database, I am assuming it is not meaningful to test it.


	User: I assumed this was on object file that controls users.
	
constructor: I assumed that the createUser method would be equivalent to calling the constructor. As
 such, I decided the constructor should only be accessible by this class and made it private.
	
createUser: I assumed a database would not check for valid input. Because of this I coded createUser
 to return a string that describes what happened. I.E. returns "Duplicate" if the user already exists.

fakeHash: I assumed this was outside the purpose of this exercise. As such, I created a fake hash that
 won't be tested.

retrieveUser: I assumed this would contact the database directly and return the object. As such, it is
 a 1 line piece of code that contacts the database. Since testing this method would actually test a method
 in the database, I am assuming it is not meaningful to test it.
