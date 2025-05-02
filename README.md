
# 🧾 Assignment Application

This is a Spring Boot-based application that supports account creation and money transfers between accounts. The application uses PostgreSQL as the database and is containerized using Docker. Everything is managed via a `docker-compose.yml` file.

---

## 🛠️ Technologies Used

- Java 17
- Spring Boot
- PostgreSQL
- Docker & Docker Compose
- Lombok
- Swagger (for API documentation)
- Postman (for API testing)

---

## 🚀 How to Run the Application

### ✅ Prerequisites

Make sure you have the following installed:

- [Docker](https://docs.docker.com/get-docker/)
- [Docker Compose](https://docs.docker.com/compose/install/)

### 🏁 Steps to Run

1. **Clone the Repository**
   ```bash
   git clone https://github.com/Shyam2210/assignment
   cd assignment
   ```

2. **Run the Application**
   ```bash
   docker-compose up
   ```

   This will:
   - Build and run the Spring Boot application
   - Start a PostgreSQL database
   - Set up the required network and volumes automatically

3. **Access the Application**

   - Swagger UI: [http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)

---

## 📬 API Testing with Postman

Import the following collection into Postman:

- `assignment.postman_collection.json`

This collection contains all the API endpoints for testing.

---

## 🛑 Stopping the Application

To stop and remove the running containers:

```bash
docker-compose down
```

This command will stop the containers, but it will **not** delete the volumes. To also delete volumes:

```bash
docker-compose down -v
```

---

## 📂 Logs

Logs are printed to the console by default. To view logs:

```bash
docker-compose logs
```

Or view logs for a specific service:

```bash
docker-compose logs app  # or postgres
```

---

## 📝 Notes

- All configurations (like DB credentials, ports) are defined in the `docker-compose.yml`.
- Make sure ports like `8080` and `5432` are not already in use on your system.

---
