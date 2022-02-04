exemple de réalisation de 2 contrôleurs REST

Create 2 REST services: one that allows to register a user and the other one that displays the details of a registered user.

 Requirements:

- use Java 8 or higher

- define a user (what are the fields needed). We should have mandatory and optional fields

- validate the input and return proper error messages/http status

- log the input and output of each call and the processing time.

- have a request parameter which is not mandatory and which provides a default value in case is not set

- have a path variable

- unit tests

- only adults ( age > 18 years)  and that live in France can create an account

 Bonuses:

- user a non-relational DB in order to save the users

- use AOP

