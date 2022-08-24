# Twitter CLI Application

## Introduction
Twitter CLI is an application to manage tweets in other word users can post, show and delete tweets using this application. I used `MVC` (Model - View - Controller) as design pattern to relate my front end with the back end. On the other hand, I used `DAO` (Data Access Object) pattern to isolate the business layer from the persistence layer. As a consumer, I used `HTTPClient` to send and receive requests/responses from the Twitter APIs providers (servers) and `Oauth 1.0` as an authorization method using `Twitter REST APIs`. 
To convert `JSON` responses to a `Tweet` object from Twitter APIs I used a `Java` library called `jackson-databind`. I chose `Apache Maven` as a project management tool which help me to handle all dependencies and the automation of the project's life cycle.`JUnit4` and `Mockito` frameworks used for unit and integration testing. And finally, `Docker` for deployment.

### Technologies
* Java 8
* Git
* Docker
* Twitter APIs
* JUnit4
* Mockito
* Spring

## Quick start
* Packaging Twitter app:
 ```bash
      mvn clean compile package
 ```
* Running Twitter app with Docker:
    <details><summary>Post a Tweet</summary>
        <p>
      Arguments:
      
   |   | Argument           | Description |
   |---|--------------------| -------------------------- |
       | 1 | tweet_text         | tweet_text cannot exceed 140 UTF-8 encoded characters |
       | 2 | latitude:longitude | Geo location |
    
        ```bash
            docker run --rm \
            -e consumerKey=YOUR_VALUE \
            -e consumerSecret=YOUR_VALUE \
            -e accessToken=YOUR_VALUE \
            -e tokenSecret=YOUR_VALUE \
            georgeshomsy/twitter_app post "arg1" "arg2"
        ```
    </p>
    </details>
    
    <details><summary>Show a Tweet</summary>
    <p>
      Arguments:
    
    |   | Argument           | Description | 
  |---|--------------------| -------------------------- |
    | 1 | tweet_id      | Tweet ID. Same as id_str in the tweet object |
    | 2 | [field1,fields2] | A comma-separated list of top-level fields from the tweet object (similar to SELECT clause in SQL) |
    
        ```bash
            docker run --rm \
            -e consumerKey=YOUR_VALUE \
            -e consumerSecret=YOUR_VALUE \
            -e accessToken=YOUR_VALUE \
            -e tokenSecret=YOUR_VALUE \
            georgeshomsy/twitter_app show "arg1" "arg2"
        ```
    </p>
    </details>

    <details><summary>Delete a Tweet</summary>
    <p>
      Arguments:

  |   | Argument  | Description |
  |-----------|--------------------| -------------------------- |
  | 1 | tweet_ids | A comma-separated list of tweets |

        ```bash
            docker run --rm \
            -e consumerKey=YOUR_VALUE \
            -e consumerSecret=YOUR_VALUE \
            -e accessToken=YOUR_VALUE \
            -e tokenSecret=YOUR_VALUE \
            georgeshomsy/twitter_app delete "arg1"
        ```
    </p>
    </details>
  
## Design
### UML diagram
![This is an image](assests/TwitterUML.jpg)

### Components
#### TwitterHTttpHelper
TwitterHttpHelper is the layer responsible to communicate with Twitter REST APIs using HTTP with a given URI. It's also responsible to give authorization to the HTTP request using `Oauth 1.0`. For the authorization, we need 4 variables: consumer key,consumer secret, access token, token secret.

#### TwitterDAO
The TwitterDao is responsible to manage the Tweet object which is implemented with plain old Java object (POJO). So, when we need to post a tweet through TwitterHttpHelper, TwitterDAO create a Tweet object, transform this object to a JSON object and pass it to the TwitterHttpHelper.
In the case of show or delete TwitterDAO layer take the returned JSON object and transform it to a Tweet object.

#### TwitterService
This layer is the business logic of the application. It's the most important part of the application. In this layer, I validate the user input before passing it to the TwitterDAO layer.
I create multiple rules to valida inputs:
  1. First rule that a tweet can exceed 140 words
  2. Second rule I should validate the coordinates
  3. Third rule if the tweet ID is valid

#### TwitterController
TwitterController is the bridge between the view (TwitterCLIApp) and the business logic (TwitterService). So, it's retrieve the user input and call the corresponding service, and then it returns the result.

#### TwitterCLIApp
This layer is the view of my application.It will help to set up the components and their relationships.

## Modals
The modals are implemented with POJO. And basically these modals represents a simplified Tweet object.
Below a simplified tweet object:
```bash
{
   "created_at":"Mon Feb 18 21:24:39 +0000 2019",
   "id":1097607853932564480,
   "id_str":"1097607853932564480",
   "text":"test with loc223",
   "entities":{
      "hashtags":[],      
      "user_mentions":[]  
   },
   "coordinates":null,   
   "retweet_count":0,
   "favorite_count":0,
   "favorited":false,
   "retweeted":false
}
```
In this app, I create 5 modals:
  1. Tweet
  2. Entities
     1. Hashtags
     2. UserMention
  3. Coordinates

And all these modals above will represent a tweet object.

## Spring
As we mentioned before, that TwitterCLIApp will initiate all 4 classes (TwitterController,TwitterService,TwitterDAO,TwitterHttpHelper) and set up all the main dependencies relationships manually. Spring framework help us to manage all these dependencies without any manual intervention.
With Spring, I should only define the dependency relationship and IoC (Inversion of Control) will automatically create all dependencies and set up everything for us. There are 2 approaches to define dependency relationships: `@Beans` or `@ComponentScan`. In the first approach (`@Beans`), we should define dependency relationship using `@Bean` and create an IoC container to automatically instantiate all the relationship we specified before. But the problem in this approach is that we still have a lot of manual work. For the second approach `@ComponantScan`, 
we should only indicate the dependency relationship by only adding to the class `@components` annotation and `@Autowired` to the class constructor to tell IoC container to inject dependency through the constructor. For this application, I implement the second approach by using the `@Controller` for the TwitterController class, the `@Service` for the TwitterService, `@Repository` for the TwitterDAO and finally `@component` for the TwitterHttpHelper. All these previous annotations are stereotypes of `@component`.

## Test
I applied 2 types of testing in this project:Unit testing and Integration testing. For the integration testing I use the `JUnit4` to test that all components are working correctly. And for the unit testing, I used `Mockito` to test the class without testing dependencies. So, I only create a mock object of the dependency class and pass it to the testing class.

## Deployment
For the deployment I used `Docker`. I create a docker image of the app, and then I pushed it to Dockerhub for easier distribution.

1. Create the `DockerFile`:
```bash
    FROM openjdk:8-alpine
    ENV CONSUMER_KEY ""
    ENV CONSUMER_SECRET ""
    ENV ACCESS_TOKEN ""
    ENV TOKEN_SECRET ""
    COPY target/java_apps*.jar /usr/local/app/twitter/lib/twitter.jar
    ENTRYPOINT ["java","-jar","/usr/local/app/twitter/lib/twitter.jar"]
```
2. Build the image using `docker build` command::
```bash
   docker build -t {dockerhub_username}/{image name} {path for creation}
```

3. Push your image to `DockerHub` using `docker push`:
```bash
   docker push dockerhub_username}/{image name}
```
And now you can go to hub.docker.com and verify your image.

## Improvements
1. Connect this application to a database, so we can save all the tweets for analyzing purposes.
2. Try to tag people when we post a tweet.
3. Upgrade Twitter Rest APIs to version 2.