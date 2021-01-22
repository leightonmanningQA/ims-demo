Coverage: 72%
# Inventory Management System

This is an application that a user can interact with via a Command-Line Interface. It is an inventory management system that can add customers and items to be used to create orders. All the objects are stored into a database. You're able to update customers,items and orders. The application will also calculate a cost for your order.

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes. See deployment for notes on how to deploy the project on a live system.

### Prerequisites

What things you need to install the software and how to install them


You will have to have java installed first. Download open JDK here
https://adoptopenjdk.net/index.html/

You will have to have maven installed as well. Download here
https://maven.apache.org/index.html

I am using Eclipse to open and edit my files, I would recommend downloading it here
https://www.eclipse.org/downloads/

I am using mySQL workbench 8.0 to work on my database locally, I would recommend downloading it here
https://dev.mysql.com/downloads/workbench/

Finally I'm using git for my version control and branches. I installed it from here
https://gitforwindows.org/


### Installing

A step by step series of examples that tell you how to get a development env running

1. Fork this repository so that you have your own copy of the files

```
Go to link https://github.com/leightonmanningQA/ims-demo 
and click the button in the top right that reads "Fork".

```
2. Head over to your forked repository now (It should be https://github.com/yourname/ims-demo) you want to press the green button and copy the HTTPS link.

3. Open up git bash in the folder you want the project by right clicking and clicking "Git bash here"(If you dont see it git isn't installed correctly)

4.Once open you want to enter commands as follows

```
git clone your_link

```
5. Once the repository has been cloned you will have all the files needed to run and develop this application. Now we need to open this project in Eclipse. Open Eclipse and go to
```
File, Import, Existing Maven Project
Browse root directory and select the folder the project is in.
Check the box and press finish.

```

6. Now you can run or develop this program. Before that though I would recommend changing the JDBC URL so you can use your own database.
```
This can be found in the Ims.java, and the DaoMysql for Customer, Items and Orders.
Set to jdbc:mysql://localhost:3306/ims if you want to use a local database.

```
7. Now you can run the application by right clicking the Runner.java file and clicking 
```
Run as, Java Application

```
8. When the program is first ran you will be greeted with the below example. My username and password are setup as root and root. 

```
INFO - What is your username
root
INFO - What is your password
root
```
9. Next you are given options to which object you would like to use. We are going to use customer for this example. After customer is selected you're asked what you would
like to do with customer. We are going to go with create for the example.
```
INFO - Which entity would you like to use?
INFO - CUSTOMER: Information about customers
INFO - ITEM: Individual Items
INFO - ORDER: Purchases of items
INFO - STOP: To close the application
customer
INFO - What would you like to do with customer:
INFO - CREATE: To save a new item into the database
INFO - READ: To read an item from the database
INFO - UPDATE: To change an item already in the database
INFO - DELETE: To remove an item from the database
INFO - RETURN: To return to domain selection
create
```
10. Once create has been selected the program will ask for a first name and surname. Once entered, the application will then add a customer to your database.
It will also print a customer ID.
```
INFO - Please enter a first name
Leighton
INFO - Please enter a surname
Manning
INFO - Customer created, your customer ID is: 1
```
11. You are then presented with the main screen again 
```
INFO - Customer created, your customer ID is: 4 Please make note of it.
INFO - Which entity would you like to use?
INFO - CUSTOMER: Information about customers
INFO - ITEM: Individual Items
INFO - ORDER: Purchases of items
INFO - STOP: To close the application
```

## Running the tests

Explain how to run the automated tests for this system. Break down into which tests and what they do

### Unit Tests 

Unit testing is where individual units / components of a software are tested. The purpose is to validate that each unit of the software performs as designed.

```
Right click on the testing portion of the project serc/test/java and click run as Junit Test. 
To check coverage right click on the entire project and click coverage as Junit Test.
```


## Deployment

Add additional notes about how to deploy this on a live system
1. Open command line in the project folder
2. Type **mvn clean** , we do this so that we can remove old builds of our code before rebuilding it again.
3. Once the target folder has been removed you want to type **mvn package**, This will package all the source code in our Java project into the .jar file
4. After this the target file will return. Navigate to that folder with the command **cd target**
5. Run the program with **java -jar name-ims-0.0.1-jar-with-dependencies.jar**

## Built With

* [Maven](https://maven.apache.org/) - Dependency Management

## Versioning

We use [SemVer](http://semver.org/) for versioning.

## Authors

* **Leighton Manning**  - [leightonmanning](https://github.com/leightonmanningQA)


* **Chris Perrins** - *Initial work* - [christophperrins](https://github.com/christophperrins)

## License

This project is licensed under the MIT license - see the [LICENSE.md](LICENSE.md) file for details 

*For help in [Choosing a license](https://choosealicense.com/)*

## Acknowledgments

* Hat tip to anyone whose code was used
* Inspiration
* etc
