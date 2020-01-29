# Technical test

- Each search is implemented in a concrete class (**CitySearch**/**IdSearch**) implementing the base abstract class **FileSearch**.  This base class implements a factory pattern to get the concrete instance.

- Unit testing implemented (coverage 94,9%).

- Performance test implemented on an auto-generated source data file (1,4GB).  About 20seg running a city search.

## Sonar

You can run static code analysis with sonar or see the image in root folder.

```shell script
  mvn clean verify sonar:sonar
```
## Unit testing

Unit testing (TestNg) uses **file.test.xml** (with duplicated records) to cover more source.
PerformanceTest generates a 1,4GB file (**file.test.performance.txt**)

    mvn test

## Running the app

Compile 

    mvn clean install

Search CITY:
    
    java -jar target/application.jar file.txt CITY BARCELONA
    
Search by ID:

     java -jar target/application.jar file.txt ID 09877359D