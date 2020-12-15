# connected cities 

Story - 
Two cities are considered to be connected, if Source and destination cities are connected. 
Application exposes endpoint which serve get request with origin/destination cities names as request param.
a. If cities are connected as per the given input csv file, endpoint return response as 'YES'.
b. If cities are not connected as per the given input csv file, endpoint return response as 'No'.
c. In case of any abnormal input for cities, endpoint return response as 'No'.

Assumptions - 
1. List of cities connected provided as input csv file.
2. Cities names are case in sensitive.
3. In case of any abnormal input for cities exception is not thrown.

Given - 
Input CSV file with comma seperated cities name as origin/destination. Input csv file can be loaded from classpath during application startup.
Example - 
Boston,New York
Philadelphia,Newark
Newark,Boston
Trenton,Albany

When - 
If origin/destination city passed as request param as null string.
Then - 
Response return as "NO"

When - 
If origin/destination city passed as request param as empty string.
Then - 
Response return as "NO"

When - 
If two cities are passed as request param are not null and origin/destination matches are per the input CSV file.
Then - 
Response return as "YES"

When - 
If two cities are passed as request param are not null and origin/destination not matches are per the input CSV file.
Then - 
Response return as "NO"
 
Implementation details -  

Spring boot app to find connection between two cities as per the given data in the form of input csm file.
Endpoint exposed as a REST api, please refer details documentation.

http://localhost:8080/swagger-ui.html#/

Endpoint exposed two api 
1. Request param as origin/destination city name. 
Example-
http://localhost:8080/connected?origin=NJ&destination=NYC

2. Path param as origin/destination city name. 
Example-
http://localhost:8080/connected/NJ/NYC

API respond with ‘yes’ if origin is connected to destination, ’no’ if origin is not connected to destination.
Any unexpected input should result in a ’no’ response.

Unit Testing - 
Performed unit testing using spring boot test - SpringBootTest. 

Instructions - 
1. Clone git project and import project as maven project.
2. Build project running command - mvn install. Spring profile is used when project is build. 
Default spring is currently set as local.
3. ConnectMainApplication - Run as spring boot app.

TODO -
1. Use custom cache to store input file data during application start.
2. Expose endpoint to provide flexibility to update/clear/add cache during application running.


