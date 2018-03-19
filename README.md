# target-casestudy
## Target Technical Assessment Case Study - MyRetail RESTful service:

myRetail is a rapidly growing company with HQ in Richmond, VA and over 200 stores across the east coast. myRetail wants to make its internal data available to any number of client devices, from myRetail.com to native mobile apps. 
The goal for this exercise is to create an end-to-end Proof-of-Concept for a products API, which will aggregate product data from multiple sources and return it as JSON to the caller. 
Your goal is to create a RESTful service that can retrieve product and price details by ID. The URL structure is up to you to define, but try to follow some sort of logical convention.

Build an application that performs the following actions: 
•	Responds to an HTTP GET request at /products/{id} and delivers product data as JSON (where {id} will be a number.

•	Example product IDs: 15117729, 16483589, 16696652, 16752456, 15643793) 

•	Example response: {"id":13860428,"name":"The Big Lebowski (Blu-ray) (Widescreen)","current_price":{"value": 13.49,"currency_code":"USD"}}

•	Performs an HTTP GET to retrieve the product name from an external API. (For this exercise the data will come from redsky.target.com, but let’s just pretend this is an internal resource hosted by myRetail) 

•	Example: http://redsky.target.com/v2/pdp/tcin/13860428?excludes=taxonomy,price,promotion,bulk_ship,rating_and_review_reviews,rating_and_review_statistics,question_answer_statistics

•	Reads pricing information from a NoSQL data store and combines it with the product id and name from the HTTP request into a single response. 

BONUS: Accepts an HTTP PUT request at the same path (/products/{id}), containing a JSON request body similar to the GET response, and updates the product’s price in the data store. 

## Solution:

### Project Requirements:

* Java 8
* Spring Boot 2.0.0.RELEASE
* Maven version 3.3.9
* MongoDB version 3.4.3
* Mokito/Junit

### How to run this project?

1. Install mongoDb on your local machine and start the mongo server.
2. Create a new database myretaildb in mongo.
3. Download the project as zip file or clone it using eclipse.
4. Goto project home directory where pom.xml is present and run the below maven command to build the project.

```
mvn clean install
```
5. After successfull maven build a jar file(target-myretail-services-0.0.1-SNAPSHOT.jar) is generated in target folder. Now execute the below command to run this application using springboot.
```
java -jar target/target-myretail-services-0.0.1-SNAPSHOT.jar
```
6. Server will start on port 9000 with context path '/api'

### API Documentation

```
Method     Request          Body                Description
 GET     /products/{id}     N/A            To get product details
 PUT     /products/{id}    Product         To update product price
 ```
 
 #### GET ProductDetails
 ```
 GET localhost:9000/api/products/1234
 
 Response: 200OK
 
 {
    "id": "1234",
    "name": "The Big Lebowski (Blu-ray)",
    "current_price": {
        "value": "99.49",
        "currency_code": "USD"
    }
}
```
#### Update ProductPrice
```
PUT localhost:9000/api/products/1234

RequestBody:
{
    "id": "1234",
    "name": "The Big Lebowski (Blu-ray)",
    "current_price": {
        "value": "199.49",
        "currency_code": "USD"
    }
}

ResponseStatus: 200OK
ResponseBody:
{
    "id": "1234",
    "current_price": {
        "value": "199.49",
        "currency_code": "USD"
    }
}
```
### Exception Handling

* Throws ProductNotFoundException if you are trying to fetch/update invalid product.
* Throws EmptyProductPriceException if you are updating product without specifying price. 
* Throws ProductIdMisMatchException if product id in the path does not match with id in the body of update price request.
* Throws ExternalAPIException if failed to fetch product name from external API.



