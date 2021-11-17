# MultiValueDictionary
This project mainly declares a Dictionary that holds <Key,Value> pairs and supports the following functionalities.
* getKeys
* getValues
* add
* remove
* removeAll
* clear
* size
* isKeyExists
* isValueExists
* getAllValues
* getItems

##### Requirements:
* Java 8
* Maven 3.6.3

##### Execution:
Clone the repo and import as Maven project. The MultiValueDictionary functionalities can be exexuted in two ways.
1) Run the __MultiValueDictionaryTest.java__ test class which thoroughly tests every requirement/scenario and edge case.
2) This project also has the main method implemented and could be run through command line using following commands
* ADD
* REMOVE
* REMOVEALL
* KEYS
* MEMBERS
* CLEAR
* KEYEXISTS
* MEMBEREXISTS
* ALLMEMBERS
* ITEMS

Follow this steps for command line execution
 1) Build the project using `mvn clean install`. 
 2) Navigate to generated `target\` folder in command line and run `java -jar MultiValueDictionary-1.0-SNAPSHOT.jar`.
 3) Enter arguments as input prompts in. For Example:
 ```Enter command:
      ADD
      Enter key:
      foo
      Enter value:
      bar
      Added
      Enter command:`
 ```
  Exit the terminal using `EXIT` command.
 
 


