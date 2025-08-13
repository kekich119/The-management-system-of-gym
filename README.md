# The-management-system-of-gym

A user management system for a gym, built with Spring Boot.

## Features

- Add, delete, update, and search users
- Extend subscription by email or special code
- Automatic daily check and deactivation of expired subscriptions
- Web interface for user management

## Technologies

- Java 17+
- Spring Boot
- Maven
- SQL (JPA/Hibernate)
- HTML, CSS, JavaScript (Vanilla)

## Quick Start

1. Clone the repository:
   git clone https://github.com/kekich119/The-management-system-of-gym.git
2. Go to the project directory:
3. Build and run the application:
4. Open the web interface: http://localhost:8080


   
## API Endpoints

- `POST /api/add/user` — add a user
- `GET /api/get/users` — get all users
- `GET /api/get/user/by-name?name=...` — find by name
- `GET /api/get/user/by-lastName?lastName=...` — find by last name
- `GET /api/get/user/by-special-code?specialCode=...` — find by special code
- `DELETE /api/delete/user/by-email/?email=...` — delete by email
- `PUT /api/update/user/?email=...&new-name=...` — update name by email
- `PUT /api/extension/by-email/?email=...` — extend subscription by email
- `PUT /api/extension/by-special-code/?special-code=...` — extend subscription by special code

## Project Structure

- `src/main/java/com/kekich/gymsystem/` — application source code
- `src/main/resources/static/` — frontend (HTML, CSS, JS)
- `src/main/resources/application.properties` — application settings



For questions and suggestions, please create an issue or pull request!
