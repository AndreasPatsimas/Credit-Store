# Foreword
Here at PwC we are interested to see your skills as a software developer and your ability to code.

Your finished exercise should be as close as possible to what you consider a production-ready application.

## Instructions
In this assignment you are tasked with creating a web application which exposes a credit store system.
The credit store should consist of a webservice serving a REST API that allows
- reading user information with no access restrictions 
- requesting increments or decrements for a user's balance given the right operator credentials are provided
- adding new or removing admins/operators given the right admin credentials are provided

and a User Interface in HTML where anyone can see all users and their balance.

All users start with 100 Euro balance, and no balance can go below 0.

We should be able to retrospectively tell which operator requested which increment or decrement and when.

We should be able to retrospectively tell which admin added or removed an admin or operator and when.

## Notes
In this repository you will find the [user_balance_init.csv](src/main/resources/user_balance_init.csv) file, which represents a dummy user-base and their balance information.

You will also find the [operators_admins_init.csv](src/main/resources/operators_admins_init.csv) file, which represents the credentials and the role level of a particular user. 

You can use these to initialise your storage.

This repository is created specially for you, so you can push anything you like. 

If you want to use a different java version, feel free to change it.

Please don't be discouraged to submit your work even if its not done. 

Please update this README to provide instructions, notes and/or comments for us.
In case of making any assumptions due to the requirements not being clear, please document them. 


## Disclaimer
The candidate's work will only be reviewed for the purposes of assessing the candidate's technical ability and will not be used in any other form. After the interview process is concluded, this repository will be deleted.
