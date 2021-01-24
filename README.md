# BlogService

Microservice build with Java and SpringBoot, Gradle build tools, MongoDB database.

Built for Cloud Programming Course in Afeka College of Management.

## Description

Microservice for saving and querying blog posts.

## Installation

* Running MongoDB local database from the following command: `docker run --name mongotest -p 27017:27017 -d mongo`
* `git clone https://github.com/danielsason112/reactive-blog`
* Open project in IntelliJ (Open project menu or import from git menu)
* Open project in Eclipse (File -> Import -> Import existing Gradle Project menu)
* after opening let gradle built and make the project.
* Run (Eclipse -> Run as SpringBoot App, IntelliJ -> via Play button)
* Open http://localhost:8087/swagger-ui.html in your browser.

## Usage

- POST /shopping/categories
 Get category details, saves to system.
 Returns 500 error code if exists.
- POST /shopping/products
 Get new product details with catalog number. If exists returns 500 error code.
 Name is not unique.
 If category of the product doesn't exist returns 500 error code.
 Category details are not being updated when creating new product.

- POST /blog

- GET /blog/byUser/{email}?sortBy={sortArrt}&sortOrder={order}&page={page}&size={size}
  
- GET /blog/byUser/{email}?filterType=byLanguage&filterValue={language}&sortBy={sortArrt}&sortOrder={order}&page={page}&size={size}

- GET /blog/byUser/{email}?filterType=byCreation&filterValue={timeEnum}&sortBy={sortArrt}&sortOrder={order}&page={page}&size={size}
  
- GET /blog/byUser/{email}?filterType=byProduct&filterValue={productId}&sortBy={sortArrt}&sortOrder={order}&page={page}&size={size}

- GET /blog/byProduct/{productId}?sortBy={sortArrt}&sortOrder={order}&page={page}&size={size}

- GET /blog/byProduct/{productId}?filterType=byLanguage&filterValue={language}&sortBy={sortArrt}&sortOrder={order}&page={page}&size={size}

- GET /blog/byProduct/{productId}?filterType=byCreation&filterValue={timeEnum}&sortBy={sortArrt}&sortOrder={order}&page={page}&size={size}
  
- GET /blog?filterType=byCreation&filterValue={timeEnum}&sortBy={sortArrt}&sortOrder={order}&page={page}&size={size}

- GET /blog?filterType=byCount&filterValue={postsCount}&page={page}&size={size}
  
- DELETE /blog

## Blog JSON Examples
    {
      "user":{
        "email":"customer11@shop.ping"
      }, 
      "product":{
        "id":"p12x"
      },  
      "postingTimestamp":"2020-12-10T04:12:39.053+0000", 
      "language":"en", 
      "postContent":{
        "text":"I really like this product"
      }
    }
    
    {
      "user":{
        "email":"customer98@shop.ping"
      }, 
      "product":{
        "id":"a32f"
      }, 
      "postingTimestamp":"2020-12-10T04:27:01.312+0000", 
      "language":"en", 
      "postContent":{
        "details":"the product installation is difficult", 
        "reference":"https://www.amazonas.corp/pid=a32f"
      }
    }
    
    {
      "user":{
        "email":"customerNumber1@shop.ping"
      }, 
      "product":{
        "id":"38996"
      },  
      "postingTimestamp":"2020-12-10T04:31:44.739+0000", 
      "language":"en", 
      "postContent":{
        "image":"http://image.im/product38996.jpg", 
        "message":"This product changed my life!", 
        "details":{
          "line1":"The fire consumed my apartment building",
          "line2":"I had to move to a shelter"
        }, 
        "references":[
          "https://newscase.org/firebrokeduetomulfunctioninproduct", 
          "http://www.cnn.com", 
          "http://demoservice.de.mo/story?stodyId=985645211596037"
        ]
      }
    }
