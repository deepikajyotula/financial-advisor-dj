To build the application do:
mvn clean install

To execute the application do:

cd target
java -jar financial-advisor-dj-0.0.1-SNAPSHOT.jar


Test with Postman
- REST client is not included in the application
- Test with Postman


Assumptions:
- Supports only investment categories - Bonds,Large Cap, Mid Cap, Foreign, Small Cap. 
	- Application does not allow for changes or adding new investment categories.
- Database is not used for any persistence only application memory is used
- Not secure, no authentication and authorization are supported. 
- Only HTTP is supported and not HTTPS
- It is a jar application, it does not have any web facing pages yet. May be improved later to make it WAR
- Runs on Port 8080 and port is not configurable