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


# CI/CD Pipeline

This project uses GitHub Actions to automate the CI/CD process.

The pipeline runs automatically when:

* A pull request is opened against the `main` branch.
* Code is pushed to the repository.

## Pipeline Flow

```text
Code Push / Pull Request
        |
        v
GitHub Actions starts the workflow
        |
        v
Checkout project files
        |
        v
Run Lint
        |
        v
Run Unit Tests
        |
        v
Run Integration Tests
        |
        v
Run E2E Tests
        |
        v
Build Spring Boot JAR file
        |
        v
Upload JAR as an artifact
        |
        v
Download JAR artifact
        |
        v
Build Docker Image
```

## Reusable Composite Action

The project contains a reusable composite action located inside:

```text
.github/actions/setup-java-project/action.yml
```

This action is responsible for preparing the project environment and running different tasks depending on the input values.

It supports both Java and React projects.

For Java, it can:

* Set up Java using Temurin JDK.
* Cache Maven dependencies.
* Install Maven dependencies.
* Run Checkstyle linting.
* Run unit tests.
* Run integration tests.
* Run E2E tests.
* Build the Spring Boot application.

For React, it can:

* Set up Node.js.
* Cache npm dependencies.
* Install dependencies using `npm ci`.
* Run linting.
* Run tests.
* Build the React application.

## Main Workflow

The main workflow is responsible for controlling the full pipeline order.

The jobs run in this order:

```text
lint
  |
  v
unit-test
  |
  v
integration-test
  |
  v
e2e-test
  |
  v
build
  |
  v
container-image
```

Each job depends on the previous one using `needs`.

This means the build job will not run unless all previous checks pass successfully.

## Reusable Test Workflow

The project also contains a reusable workflow:

```text
.github/workflows/test-reusable.yml
```

This workflow is called multiple times from the main workflow.

Instead of repeating the same test job logic, the main workflow passes different task names such as:

```text
unit-test
integration-test
e2e-test
```

The reusable workflow receives these inputs and runs the correct task using the composite action.

## Build and Artifact

After all checks pass, the build job runs:

```bash
mvn clean package
```

This creates the Spring Boot JAR file inside the `target` directory.

Then GitHub Actions uploads the generated JAR as an artifact:

```text
app-jar
```

This allows the next job to reuse the same build output without rebuilding the application again.

## Docker Image Build

The final job downloads the JAR artifact and builds the Docker image:

```bash
docker build -t aleasofttrainner:latest .
```

This creates a Docker image for the Spring Boot backend application.

## Why This Pipeline Is Useful

This CI/CD pipeline makes the development process safer because every code change is checked automatically before being considered ready.

It helps ensure that:

* The code style is valid.
* Unit tests pass.
* Integration tests pass.
* E2E tests pass.
* The application can be built successfully.
* A Docker image can be created from the final build.


