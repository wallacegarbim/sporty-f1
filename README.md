# Sporty F1 Betting API

A reactive betting API for Formula 1, built with **Java 21**, **Micronaut**, and **RxJava 3**, with **MongoDB** as the database.  
The application imports data from a third-party F1 API during startup to seed driver and session information.

**Curl sample requests** are also included for easy testing of the available endpoints.

---

## ⚡ Tech Stack
- **Java 21**
- **Micronaut Framework**
- **Reactive programming with RxJava 3**
- **MongoDB** for persistence
- **Docker & Docker Compose** for containerized setup

---

## 📦 Prerequisites
Before running the application, make sure you have installed:
- [Docker](https://docs.docker.com/get-docker/)
- [Docker Compose](https://docs.docker.com/compose/install/)

---

## ▶️ Running the Application

1. **Clone the repository**

   ```bash
   git clone https://github.com/wallacegarbim/sporty-f1.git
   cd sporty-f1
   ```
    
Build the application
The Dockerfile will build the Micronaut application into a runnable JAR.
```bash
  ./gradlew clean build
   ```
Start the app and MongoDB with Docker Compose
```bash
  docker-compose up --build
   ```
This will:
Start a MongoDB instance
Build and run the sporty-f1 service
Import F1 driver/session data into MongoDB on startup

## ▶️ Access the API

Once running, the API will be available at:


http://localhost:8080


---

## 🧪 Testing with Curl

### GET /events → Fetch available events

```bash
curl --location 'http://localhost:8080/events?year=2025&session_type=Race&country_name=China'



POST /bets → Place a bet

For the "user_id", any string can be used.

curl --location 'http://localhost:8080/bets' \
--header 'Content-Type: application/json' \
--data '{
  "user_id": "1",
  "session_id": 9693,
  "driver_id": 18,
  "amount": "30"
}'


POST /events/{sessionId}/outcome → Apply a race outcome

curl --location 'http://localhost:8080/events/9693/outcome' \
--header 'Content-Type: application/json' \
--data '{
  "driver_id": 18
}'

It's possible to check the outcome result usiong a Mongo client and checking the users collection for the balance, and 
the bets collection for the bets results.

Useful Commands

Stop containers: docker-compose down

Stop containers and remove MongoDB volume (reset data): docker-compose down -v
