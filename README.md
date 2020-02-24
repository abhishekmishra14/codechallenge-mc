# codechallenge-mc boot 
Code Chanllenge MC

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


