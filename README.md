Application Flow
Startup Flow
When the application is started using Docker Compose:

Docker Compose creates the MySQL container first.

The MySQL container initializes the database and starts listening on port 3306.

Docker Compose starts the Spring Boot backend container.

The backend connects to the MySQL database using the Docker network.

Spring Boot initializes all beans, loads configurations, and exposes REST APIs.

Docker Compose starts the React frontend container.

React serves the application through Nginx (or the configured web server).

The user accesses the frontend from the browser.

React sends HTTP requests to the Spring Boot backend.

Spring Boot processes the request and interacts with MySQL when needed.

The response is returned to React and displayed to the user.

Request Flow
User Browser
      │
      ▼
React Frontend Container
      │
      ▼
Spring Boot Backend Container
      │
      ▼
MySQL Container
      │
      ▼
Spring Boot Backend Container
      │
      ▼
React Frontend Container
      │
      ▼
User Browser
Docker Architecture
+---------------------+
|   User Browser      |
+----------+----------+
           |
           ▼
+---------------------+
| React Container     |
| (Frontend)          |
+----------+----------+
           |
           ▼
+---------------------+
| Spring Boot         |
| Backend Container   |
+----------+----------+
           |
           ▼
+---------------------+
| MySQL Container     |
| Database            |
+---------------------+
Running the Application
docker compose up --build
This command:

Builds the frontend image.

Builds the backend image.

Creates the MySQL container.

Creates a dedicated Docker network.

Connects all containers together.

Starts the complete application stack.

After startup:

Frontend → http://localhost:3000

Backend → http://localhost:8080

MySQL → localhost:3306 (or the mapped port configured in docker-compose.yml)

The backend service depends on the MySQL service, and the frontend service depends on the backend service. Docker Compose ensures the containers are started in the correct order


