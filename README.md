# DVios - LEI

An interactive shopping assistant. Introducing Lei the AI for supermarkets

## Problem we are addressing

* People have to spend a lot of time in supermarkets reaching out goods and looking for the best fit
* Sales assistants are not always reachable for the customers
* There are a plenty of unsatisfied customers because customers might not reach the good they are looking for

###Our solution

* Youtube URL: https://youtu.be/hMhY5aU0kYQ
* An interactive AI name LEI to assist customers, which in Afrikans means the guider/ assistant.

## Technologies being used

* Natural Language Processing provided by Stanford University under GNU General Public License (Open Source)
   Manning, Christopher D., Mihai Surdeanu, John Bauer, Jenny Finkel, Steven J. Bethard, and David McClosky. 2014. The Stanford CoreNLP Natural Language Processing Toolkit In Proceedings of the 52nd Annual Meeting of the Association for Computational Linguistics: System Demonstrations, pp. 55-60. [pdf] [bib]
* MongoDB - For the natural language knowledge base
* PHP - For the main web service
* JAVA - For the heavy task of processing natural language

## Our technologies

* LEI socket for connecting PHP and JAVA for better performance and scalabiltity
* MongoDB knowledge base for storing the knowledge base

## Setting up the project

* Setting up the database
 * Create a database name googleIO using mongoDB in the localhost at port no 27017
 * Initiate a collectioned name ai in the database googleIO
 * add the data in the following format
   {
      "type" : Type of the product,
      "name" : Name of the product,
      "brand" : Brand of the product,
      "price" : Price of the product,
      "location" : Location of the product in the supermarket
   }
