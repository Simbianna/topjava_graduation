# TopJava graduation project.
Design and implement a REST API using Hibernate/Spring/SpringMVC (or Spring-Boot) without frontend.
## The task is:


Build a voting system for deciding where to have lunch.

2 types of users: admin and regular users
Admin can input a restaurant and it's lunch menu of the day (2-5 items usually, just a dish name and price)
Menu changes each day (admins do the updates)
Users can vote on which restaurant they want to have lunch at
Only one vote counted per user
If user votes again the same day:
If it is before 11:00 we asume that he changed his mind.
If it is after 11:00 then it is too late, vote can't be changed
Each restaurant provides new menu each day.

As a result, provide a link to github repository. It should contain the code, README.md with API documentation and couple curl commands to test it.


## Technologies/Tools/Frameworks:

- Maven
- Spring (MVC, Security, DATA JPA)
- HSQLDB


## Getting started.

+ Clone or download this repository:
```https://github.com/Simbianna/topjava_graduation.git```
  + run ```mvn package``` and deploy graduation.war with Tomcat at ```http://localhost:8080/graduation```

       **or**
  + run ```mvn clean verify org.codehaus.cargo:cargo-maven2-plugin:run``` and open ```http://localhost:8080/graduation```


### REST API.
#### Commands for ROLE:Admin


Entity |  Method |         UrL            |Description
-------| ------- | -----------------      | -------------
User   | GET     |/rest/admin/users       |Get all users
User   | POST    |/rest/admin/users       |Create new User 
User   | GET     |/rest/admin/users/{id}  |Get user by id 
User   | GET     |/rest/admin/users/by?email={email}  |Get user by email
User   | DELETE  |/rest/admin/users/{id}  |Delete user by id
User   | PUT     |/rest/admin/users/{id}  |Update user by id 
User   | PATCH   |/rest/admin/users/{id}included={boolean}  |Enable/disable user by id 
Restaurant| GET |/rest/admin/restaurants/| Get all restaurants (dishes are not shown)
Restaurant| POST |/rest/admin/restaurants/| Create new restaurant 
Restaurant| GET |/rest/admin/restaurants/{restaurantId}| Get restaurant by id (dishes are not shown)
Restaurant| DELETE |/rest/admin/restaurants/{restaurantId}| Delete restaurant by id
Restaurant| UPDATE |/rest/admin/restaurants/{restaurantId}| Update restaurant by id
Dish| GET |/rest/admin/restaurants/{restaurantId}/dishes| Get all dishes for restaurant by it`s id (even those that are not included in actual lunchmenu)
Dish| GET |/rest/admin/restaurants/{restaurantId}/dishes/lunchMenu| Get all dishes for restaurant by it`s id (only those that are included in actual lunch menu)
Dish| POST |/rest/admin/restaurants/{restaurantId}/dishes| Create new dish for restaurant
Dish| GET |/rest/admin/restaurants/{restaurantId}/dishes/{id}| Get dish by restaurantId and dishId
Dish| DELETE |/rest/admin/restaurants/{restaurantId}/dishes/{id}| Delete dish by restaurantId and dishId
Dish| PUT|/rest/admin/restaurants/{restaurantId}/dishes/{id}| Update dish by restaurantId and dishId
Dish| PATCH|/rest/admin/restaurants/{restaurantId}/dishes/{id}?included={boolean}| Include/Exclude dish in/from actual linch menu

#### Commands for ROLE:User

Entity |  Method |         UrL            |Description
-------| ------- | -----------------      | -------------
User   | GET     |/rest/profile      |Return logged user profile
User   | GET     |/rest/profile      |Delete logged user  profile
User   | GET     |/rest/profile/register      |Create (register) new user
Restaurant| GET |/rest/restaurants/| Get all restaurants with actual lunch menu, restaurants with blank lunch menu are not shown
Restaurant| GET |/rest/restaurants/{restaurantId}| Get restaurant by id with actual lunch menu
Dish| GET |/rest/restaurants/{restaurantId}/lunchMenu| Get all dishes for restaurant by it`s id (only those that are included in actual lunchmenu)
Dish| GET |/rest/restaurants/{restaurantId}/dishes/{id}| Get dish by restaurantId and dishId
Vote   | GET     |/rest/profile/votes      |Return all votes by logged user
Vote   | POST     |/rest/profile/votes      |Create new vote by logged user (the operation will be performed only if the voting time is less than 11 AM or if the vote is the first on the current date)
Vote   | GET     |/rest/profile/votes/{id}     |Return vote by id for logged user
Vote   | DELETE     |/rest/profile/votes/{id}     |Delete vote by id for logged user
Vote   | PUT     |/rest/profile/votes/{id}     |Updates vote by id for logged user (the operation will be performed only if the voting time is less than 11 AM today)
Vote   | GET     |/rest/profile/votes      |Return all votes by logged 
Vote   | GET     |/rest/votes      |Return all votes
Vote   | GET     |/rest/votes/{id}      |Return vote by id
Vote   | POST     |/rest/votes?restaurantId={restaurantId}|Create or upadate existing vote. The method checks in the database whether the logged user voted today. If not, a new voice will be added. If so, today's vote will either be overwritten in the database (if the voice is changed before 11 am) or nothing will happen.