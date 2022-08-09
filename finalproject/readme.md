# Credit Management System

## Introduction

This is a credit management system. It is a simple system that allows users to create accounts, and then add credit to their accounts.

### Features

* View all users
* View a user
* Create a user
* Update a user
* Delete a user
* Apply for a credit
* View credit status of an user

In this system there are three classes. User class has a relation with the others as a composition.
Since a Credit class has no meaning without a User, they shouldn't exist without it.
So when a user is deleted, other related classes will be deleted as well. Credit class must be constructed when a User is applied for a credit.

In this scenario, to specify the credit limit of a user, we need a variable called credit score.
Since the credit score calculated from the identity number of a User, the system construct a CreditScore class to store the credit score of each user.
This credit score number shouldn't be an attribute of the User class, but a attribute of the CreditScore class.
If this credit score is stored in the User class, it can violate some principles of OOP like Single Responsibility Principle.
System creates a CreditScore class as soon as a User is created because it is already needed to calculate the credit score of a user when an application is done.

Since the other classes are composed of the User class, we don't need controllers to handle the requests.
All requests to Services of 'Credit' and 'CreditScore' will be sent from User service. We shouldn't reach them directly, these 2 classes and their attributes are calculated by system.
This scenario is safer than a scenario that we can update data and send requests these 2 classes without calculations.

Not found error for each method of controller is being sent to the user by backend itself to avoid exposing sensitive information to the user.
Jpa repositories are extended by repositories of system to manipulate the data.
