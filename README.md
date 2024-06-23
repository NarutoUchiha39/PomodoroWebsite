### Pomodoro Website

*💭 Bro what is the website about?*
Have you ever while studying for any subject felt sleepy? (*Sigh*) Well this pomodoro website can make your studying fun. 

*💭 Bro thats cool can you describe a bit more in detail? I could really use some help to focus*
Well we all know humans cant concentrate for extended periods of time. Optimal concentration criterion concentration is 25 mins study and 5 mins break (now i know many times the numbers get reversed ).
This timer might not work for you. You might have a longer capacity to concentrate and want to set your own timers. <br>
The pomodoro website allows you to set custom timers for study sessions as well as for breaks so that you can concentrate according to your focus duration. At the start and end of each
session an **alarm** sounds to indicate the start and end of the session

*💭 Bro thats all cool but i like to have some music with calming beats in the background while i study. Could you help mw with that?*

Look no further! Our website allows you to select song from our list so that you can drown out all the noises while studying and give your 100% focus to the task at hand

*Oh did i mention the background of the timer has a cute animated cat and the UI looks cozy? Cool i know all thanks to our UI designer Uditi Sinha*

*Now let us pull out our coffee mugs, use the JWT tokens and dive straight through the API Gateway into the details of website* <br>
( Disclaimer: Above sentence was a terrible attempt to mimic Actman. If you dont know him check out https://www.youtube.com/@TheActMan )

### <p align="center"><strong>made with ❤️ using</strong> </p>
<p align="center">
  <img width=100px height=100px style="margin-right:100px" src="https://github.com/NarutoUchiha39/PomodoroWebsite/assets/104666748/b5eaecdc-833a-481a-8173-37e6a1327f29">
  <img style="width:100px;height:100px;margin-right:100px" src="https://github.com/NarutoUchiha39/PomodoroWebsite/assets/104666748/9829648f-6b1b-4ff4-be28-5b9e078dbc62">
  <img style="width:100px;height:100px;margin-right:100px" src="https://github.com/NarutoUchiha39/PomodoroWebsite/assets/104666748/f73d9e1c-a7b6-46e3-8fa5-5f7ceb0898a4">
  <img style="width:100px;height:100px;margin-right:100px" src="https://github.com/NarutoUchiha39/PomodoroWebsite/assets/104666748/5a2a3aff-a3e8-455f-8e4c-b012c8294822">

</p>

*Features of the website :*
1. All the microservices are an ```Eureka Client``` with the ```Eureka``` microservice being the server. Eureka helps is keeping track and monitoring all the microservices. Eureka also gives quick access to which microservice runs on which port and which microservice is up and which is down 
2. The website consits of API Gateway to direct incoming HTTP request to the authentication microservice. The API gateway is asynchronous in nature and makes use of the ```netty``` webserver instead of tradiional tomcat webserver in oredr to support asynchronous processing of request
3. * The Website uses JWT based authentication to manage users. The ```Authorization``` header of each incoming request is checked to see if they contain the ```Bearer``` token. If they do so, further checks takes places to make sure the JWT hasn't expired and the user is authenticated. If the JWT token is not found (as in case of user registering) then the user details are used to create a JWT token and sent t the frontend. All the pages except the login and register are protected to make sure only authorized users can have access to pages. ```Hibernate's``` powerful ORM has been used to communicate with the ```postgresql``` database hosted at ```Supabase```
   * On the frontend part every route except the login and register pages are checked for JWT tokens. If not present user is redirected back to login page.
4. The website makes use of ```Firebase storage``` to store the songs. We make use of ```Firestore databse``` to store information related to the song. Firebase SDK for java is used to upload songs to storage and retrieve song information
5. The website also tracks the amount of time studied by a user. We have leveraged the power of ```Hibernate``` ORM to make database queries with ease
6. The website also features a separate admin and user login pages. Admin has the ability to upload new songs to the storage as well as edit information related to the song. The user can set timer and listen to music from the list of songs available.
7. For the front end we have made use of ```react``` and ```antd``` UI library to provide easy to use and intuitive UI

***Diagram representation***
<br>
![diagram-export-6-22-2024-12_19_36-PM](https://github.com/NarutoUchiha39/PomodoroWebsite/assets/104666748/9451df35-d19a-4b23-9046-82aca1ebfe6c)


*List of microservices and their dependencies:*<br><br>
*Note: Lombok has been used in all the micro services in order to make to  reduce boiler plate code*
1. ***Eureka*** micro service provides the Eureka Server that all the other microservices connect to. This microservice helps in monitoring other microservices
   * Dependencies:
     1. spring-cloud-starter-netflix-eureka-server
2. ***Gateway*** micro service provides the API gateway that is used to check and redirect all the incoming requests to the authentication microservice
    * Dependencies:
      1. spring-cloud-starter
      2. spring-cloud-starter-gateway
      3. spring-boot-starter-webflux
      4. spring-cloud-starter-netflix-eureka-client
3. ***Auth*** micro service enables the use of JWT based authentication. This micro service also protectes routes
    * Dependencies:
      1. spring-boot-starter-web
      2. spring-cloud-starter-netflix-eureka-client
      3. spring-boot-starter-data-jpa
      4. postgresql
      5. spring-boot-starter-securit
      6. jjwt-api
      7. jjwt-jackson
      8. jjwt-impl
4. ***Music*** micro service helps in CRUD for songs. Makes use of firebase API to connect to firebase
    * Dependencies:
      1. firebase-admin
      2. spring-boot-starter-web
      3. spring-cloud-starter-netflix-eureka-client
      4. spring-boot-devtools (helps in enabling hot reload)
5. ***TrackUser*** micro service helps to track the amount of time a user has studied
    * Dependencies:
      1. postgresql
      2. spring-boot-starter-data-jpa
      3. spring-boot-starter-web
      4. spring-cloud-starter-netflix-eureka-client

 ### *<p align="center">If you have made it this far far thank you for your time and attention</p>*
  
