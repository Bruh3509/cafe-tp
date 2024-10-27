# Hotel Room Booking System

## Project Description

The Hotel Room Booking System is a web application that allows users to book hotel rooms. It provides functionality for managing users, rooms, bookings, and guests. The system enables CRUD operations for each of these entities and supports features such as room capacity, types, and additional features.

## Technology Stack

- **Backend**: Spring Boot
- **Build Tool**: Apache Maven
- **Containerization**: Docker
- **Database Migration**: Flyway
- **Microservices**: Spring Cloud
- **Database**: MySQL

## User Roles and Use Cases

### Roles

1. **Admin**
   - Manage rooms (CRUD operations)
   - Manage bookings (CRUD operations)
   - Manage guests (CRUD operations)
   - Manage users (CRUD operations)

2. **User**
   - View available rooms
   - Make a booking
   - View personal bookings

### Use Cases

![Use Case Diagram]!![image](https://github.com/user-attachments/assets/13c0c425-476b-4b58-b0c5-1d93a5657988)



- **Admin Use Cases:**
  - Add, update, delete, and view rooms
  - Add, update, delete, and view bookings
  - Add, update, delete, and view guests
  - Add, update, delete, and view users

- **User Use Cases:**
  - View available rooms
  - Book a room
  - View own bookings

## Database Schema

Below is the ER diagram for the database schema used in the Hotel Room Booking System:

![ER Diagram]![image_2024-09-27_00-11-23](https://github.com/user-attachments/assets/ec245a40-18e7-446c-92ac-6ff7521047c4)


- **Users**: Contains user information with a unique passport ID.
- **Rooms**: Contains details about rooms, such as capacity, type, cost per night, description, and additional features.
- **Bookings**: Contains booking information, linked to users and rooms.
- **Guests**: Contains guest information.
- **Bookings_Guests**: A junction table linking bookings and guests.

## API Endpoints

### Users
- **GET** `/api/users` - Retrieve all users
- **POST** `/api/users` - Create a new user
- **GET** `/api/users/{id}` - Retrieve a user by ID
- **PUT** `/api/users/{id}` - Update a user by ID
- **DELETE** `/api/users/{id}` - Delete a user by ID

### Rooms
- **GET** `/api/rooms` - Retrieve all rooms
- **POST** `/api/rooms` - Create a new room
- **GET** `/api/rooms/{id}` - Retrieve a room by ID
- **PUT** `/api/rooms/{id}` - Update a room by ID
- **DELETE** `/api/rooms/{id}` - Delete a room by ID

### Bookings
- **GET** `/api/bookings` - Retrieve all bookings
- **POST** `/api/bookings` - Create a new booking
- **GET** `/api/bookings/{id}` - Retrieve a booking by ID
- **PUT** `/api/bookings/{id}` - Update a booking by ID
- **DELETE** `/api/bookings/{id}` - Delete a booking by ID

### Guests
- **GET** `/api/guests` - Retrieve all guests
- **POST** `/api/guests` - Create a new guest
- **GET** `/api/guests/{id}` - Retrieve a guest by ID
- **PUT** `/api/guests/{id}` - Update a guest by ID
- **DELETE** `/api/guests/{id}` - Delete a guest by ID

## Swagger and OpenAPI Specification
Provides OpenAPI Spec, you can easilly read it and understand app's API and moreover you can generate controller stubs by it in different programming languages.
