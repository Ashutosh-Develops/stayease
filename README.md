# stayease
Stayease is a RESTful api service that streamlines the room booking process for a hotel management aggregator application.

## Application features
  * Enables a customer to register and login to access the services.
  * A customer is aassigned a CUSTOMER role which allows them to book available rooms in hotels. A customer cancel once booking is done.
  * Hotel admin and hotel manager are assigned roles of ADMIN and HOTEL_MANAGER,respectively. An admin can create,delete and update hotel,rooms. Hotel manager can cancel bookings ,update hotel details but cannot create,delete hotel. 
  * All customers,admins,and hotel managers are required to authenticate before using the stay ease services, except for brwosing available hotels and rooms.
     
## Tech stack and tools
  * Java, Spring boot, Spring Security , JWT Token , Spring data JPA , MySQL , Docker, lombok , Mockito , Junit.

## Getting Started
  * Download the repository on local machine.
  * Import the downloaded project in your editor, ex- Intellij, Eclipse, or VSCode.
  * The project uses MySQL as the database, so make sure to have a running instance on MySQL on your localhost or have access to cloud instance(For ex- Aiven MySQL).
  * Make changes to the application.properties file to add your database credentials.
  * The project is setup using embedded tomcat container, so project can be run directly through editor without setting up external application server.
  * Additionally, project could also be executed by invoking the project jar file. Follow the below steps
    * Run command
      * `./gradlew clean build`.
    * Make sure you are in the root directory of project and execute
      * `java -jar ./build/libs/stayease-0.0.1-SNAPSHOT.jar`

## API Endpoints
  * User Registration - `POST /api/stayease/v1/auth/register`. Use the below request body for post request. Role can be either of CUSTOMER,ADMIN,HOTEL_MANAGER.
    * ```
          firstName:"",
          lastName:"",
          email:"",
          password:"",
          role:""
      ```
  * User Login - `POST /api/stayease/v1/auth/login`. Use the below request body for post request. The response of this request fetches a JWT token which is required for using majority of API endpoints.
    * ```
          email:"",
          password:""
      ```
  * Add hotel - `POST api/stayease/v1/hotels` . This request requires user to send below request body and JWT token in bearer token header . Also, the role of this user must be ADMIN. User with other roles are not authorized for this operation.
    *  ```
         hotelName:"",
         location:"",
         hotelDescription:""
       ```
  * Add Room in a hotel - `POST api/stayease/v1/hotels/{hotelId}/room` . This request requires user to send below request body and JWT token in bearer token header . Also, the role of this user must be ADMIN. User with other roles are not authorized for this operation.
    *  ```
         hotelId:"",
         status:""
       ```
  * Book an available room in a hotel - `POST api/stayease/v1/hotels/{hotelId}/room ` . This request requires user to send below request body and JWT token in bearer token header . Also, the role of this user can CUSTOMER,ADMIN or HOTEL_MANAGER.
    *  ```
         roomId:""
       ```
  * Cancel a booking - `POST api/v1/bookings/{bookingId}` . This request requires a user to add JWT token in bearer token header . Only user with role HOTEL_MANAGER can perform this operation. Other roles are not authorized for this operation.
  
