BookMyShow Ticket Booking System
------------------------------------------------------------------------------------------
Overview
------------------------------------------------------------------------------------------
This project is an implementation of an online movie ticket booking system using Spring Boot. It allows users to browse shows, book tickets, and apply VIP booking enhancements.

**Features**
-----------------------------------------------------------------------------------------
a) Browse Shows (read-scenerio): Users can search for movie shows by city, date, and movie name.
b) Book Tickets: Users can book tickets for available shows, with the option to apply VIP booking enhancements.
c) Calculate Discounts: Discounts can be calculated based on the show and ticket types.

**Project Structure**
-----------------------------------------------------------------------------------------

**Database Schema**
-----------------------------------------------------------------------------------------
**Tables**:
* **movie**: Contains movie details (name, language, genre).
* **theatre**: Contains theatre details (name, city).
* **show**: Contains show details (linked to movie and theatre), including time, date, price, and seat availability.
* **seat**: Contains details about individual seats for each show, including availability and price.
* **booking**: Stores information about each booking, including the customer, show, and seats booked.


**How to Run the Project**
-------------------------------------------------------------------------------------------
**Prerequisites**
* Java 17+
* Maven 3+
* IntelliJ IDEA (or any other Java IDE)
* H2 Database (for in-memory usage)

**Steps to Run**
-------------------------------------------------------------------------------------------
1. Clone the repository:

git clone https://github.com/ssehgal91/bookmyshow.git

2. Navigate to the project directory:

cd bookmyshow

3. Run the application: You can run the Spring Boot application using the following command:
mvn spring-boot:run

4. Access H2 Console: Once the application is running, access the H2 database at:
http://localhost:8080/h2-console

* JDBC URL: jdbc:h2:mem:testdb
* Username: sakshi
* Password: (leave empty)

**API Endpoints**
-------------------------------------------------------------------------------------------
1. Browse Shows
   URL: GET /api/bookings/browse-theatres

**Parameters**:

movieName: Name of the movie.
city: City where the theatres are located.
date: Date of the show (format: YYYY-MM-DD).

Sample Request:

`curl -X GET "http://localhost:8080/api/bookings/browse-theatres?movieName=Inception&city=Delhi&date=2024-09-18"
`

Sample Response:

`[
{
"showId": 1,
"movieName": "Inception",
"theatreName": "The Grand Theatre",
"showDate": "2024-09-18",
"showTime": "14:00:00"
},
{
"showId": 2,
"movieName": "Inception",
"theatreName": "Cineplex",
"showDate": "2024-09-18",
"showTime": "16:30:00"
}
]`

2. Book Tickets
   URL: POST /api/bookings

Request Body:
{
"showId": 1,
"seatIds": [2,3],
"customerName": "Sakshi Sehgal"
}

Sample Request:
`curl -X POST http://localhost:8080/api/bookings \
-H "Content-Type: application/json" \
-d '{"showId": 1, "seatIds": [1, 2], "customerName": "Sakshi Sehgal"}'`

Sample Response :
`{
"id": 1,
"customerName": "Sakshi Sehgal",
"status": "Booking confirmed"
}`

3.Calculate Discounts

URL: GET /api/bookings/calculate-discounts

Parameters:

showId: The ID of the show.
seatIds: List of seat IDs

Sample Request:

`curl -X GET "http://localhost:8080/api/bookings/calculate-discounts?showId=1&seatIds=101,102"
`
Sample Response:
`{
"totalPrice": 200.0,
"discountDetails": "50% discount on every third seat applied and 20% discount for afternoon shows applied."
}`


**Key Classes and Design Patterns**
---------------------------------------------------------------------------------------------------
**Booking Service**: Handles the core booking logic and the integration with repositories.
**Decorator Pattern**: Used to decorate for extra VIP Discount
**Strategy Pattern**: Used to apply different discount strategies i.e 50% on 3rd ticket , afternoon show get a 20% discount
**Repository Pattern**: Manages data persistence for entities like Show, Seat, and Booking.
