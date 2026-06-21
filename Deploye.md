# Deployment Guide

## Backend Deployment (Render)

### 1. Create PostgreSQL Database

1. Create a new PostgreSQL instance on Render.
2. Choose the same region as your backend service (e.g. Ohio).
3. Create the database and save the following values:

   * Host
   * Port
   * Database Name
   * Username
   * Password

### Notes

* Use the same region for both PostgreSQL and the backend service to reduce latency.
* Use the Internal Database URL provided by Render when connecting from services inside Render.
* Verify all database environment variables before deploying.

---

## 2. Deploy Backend Service

Create a new Web Service on Render:

* Environment: Docker
* Branch: main
* Region: Same region as PostgreSQL
* Instance Type: Free (or higher for production)

### Docker Requirements

Make sure:

* Dockerfile exists in the project root.
* Dockerfile contains all required build and run commands.
* Source code is located in the repository root.

### Environment Variables

Configure all required environment variables:

```env
SPRING_DATASOURCE_URL=
SPRING_DATASOURCE_USERNAME=
SPRING_DATASOURCE_PASSWORD=
JWT_SECRET=
```

Verify that all values are correct before deployment.

### Health Check

Health checks were disabled for this project because application startup may take longer than Render's timeout period, causing false deployment failures.

If your application starts quickly, you may enable health checks again.

### Auto Deploy

Disable Render Auto Deploy.

Deployments are triggered through the CI/CD pipeline only after all tests pass successfully.

---

## 3. CI/CD Flow

Deployment order:

1. Lint
2. Unit Tests
3. Integration Tests
4. End-to-End Tests
5. Build
6. Docker Image Build
7. Deploy to Render

Deployment occurs only when all previous stages succeed.

---

# Frontend Deployment (Netlify)

## 1. Import Repository

Import the React repository from GitHub into Netlify.

## 2. Build Configuration

Example configuration:

* Branch: main
* Build Command:

```bash
npm run build
```

* Publish Directory:

```bash
dist
```

Adjust these values according to your project structure.

## 3. Environment Variables

Configure all required frontend environment variables.

Example:

```env
VITE_API_BASE_URL=https://your-backend-url.onrender.com
```

Verify that the frontend is pointing to the correct backend endpoint.

## Deployment Verification

After deployment:

1. Verify backend accessibility.
2. Verify database connectivity.
3. Verify frontend API requests.
4. Verify authentication and protected endpoints.
5. Verify application functionality end-to-end.


# Troubleshooting

## 1. Database Connection Failed

### Symptoms

* Application fails to start.
* Spring Boot cannot connect to PostgreSQL.
* HikariCP connection errors.

### Solution

Verify:

* Database host is correct.
* Database username and password are correct.
* Database name is correct.
* Environment variables are configured properly.
* Backend and database are deployed in the same Render region.

---

## 2. Backend Cannot Reach PostgreSQL

### Symptoms

* Connection timeout errors.
* Database appears online but requests fail.

### Solution

Use the Internal Database Host provided by Render when the backend service is hosted on Render.

Also verify that:

* Backend and PostgreSQL are in the same region.
* Internal networking is enabled by default within Render services.

---

## 3. Deployment Fails During Startup

### Symptoms

* Deployment starts successfully.
* Service becomes unhealthy.
* Render reports startup timeout.

### Solution

Some Spring Boot applications require additional startup time.

If Health Checks are configured incorrectly, Render may mark the deployment as failed before the application finishes starting.

Options:

* Configure a valid health endpoint.
* Increase startup efficiency.
* Temporarily disable health checks during development.

---

## 4. Frontend Cannot Call Backend APIs

### Symptoms

* Login requests fail.
* Network errors appear in browser console.
* Axios requests return connection errors.

### Solution

Verify:

```env
VITE_API_BASE_URL=https://your-backend-url.onrender.com
```

Make sure the frontend is pointing to the correct backend URL.

After changing environment variables, redeploy the frontend.

---

## 5. Environment Variables Not Applied

### Symptoms

* Application works locally but fails after deployment.
* Secrets appear as null.

### Solution

Verify all required environment variables exist in:

* Render Environment Variables
* Netlify Environment Variables

Redeploy the service after updating any variable.

---

## 6. CI/CD Passed But Deployment Did Not Occur

### Symptoms

* GitHub Actions completed successfully.
* Render did not deploy.

### Solution

Verify:

* Auto Deploy configuration.
* Deploy Hook URL configuration.
* Deploy job executed successfully in GitHub Actions.
* Deployment is triggered only from the main branch.

---

## 7. Docker Build Failed

### Symptoms

* Render fails during image build.
* GitHub Actions cannot build Docker image.

### Solution

Verify:

* Dockerfile exists in the repository root.
* Dockerfile path is correct.
* Build commands execute successfully locally.

Test locally:

```bash
docker build -t my-app .
```

before pushing changes.

---

## 8. Wrong Region Configuration

### Symptoms

* Increased latency.
* Slow database communication.

### Solution

Deploy:

* PostgreSQL
* Backend Service

in the same Render region.

Using different regions can increase response times and connection delays.

