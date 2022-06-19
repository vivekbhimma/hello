### 
application.properties needs to be updated to use the API Key
- propertydata.apikey=<<API_KEY>>

TODO:
- Cache time is 60 seconds for testing. Can look at daily schedule, or monthly
- Can improve testing. focussed on integration happy case and a service unit test

How to run?
- mvn clean install
- mvn spring-boot:run

OR
- mvn clean install
- java -jar ./target/hello-neighbour-0.0.1-SNAPSHOT.jar (relative to application.properties file)

Endpoint 
- Example:
  GET
  - http://localhost:8080/rental-demand-rating-and-capital-growth/W11
  
  Response
  - {"rental_demand_rating":"Landlord's market","capital_growth":"12.6%"}

  
