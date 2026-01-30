# The order management system. 

## Design a simplified order management system for an online store:

- Users create orders.
- orders contain products and status (NEW, PAID, SHIPPED, CANCELLED)
- Payments are processed asynchronously through an external service.
- The system must be fault-tolerant and scalable

- ## Requirements:

- Use any *object-oriented language*.
- Use the latest stable Spring Boot version.
- Use the latest stable PostgreSQL database version.
- Use the latest stable MongoDB database version.
- Use the latest OpenAPI.

### 
- Create a REST API service.
- Create the other REST service with Kafka consumer support.
- Create an API Gateway with an async approach.
- Add Apache Kafka.
- Add Kafka UI.
- Add Cash (Redis). 
- Add observability (Prometheus, Grafana, Loki).
- Add Docker and Docker Compose. 
- Add Kubernetes.
- Add unit(repository, service and rest) and integration tests with testcontainers.

### 
- Provide code and clear instructions on how to run it.

You will need the following technologies available to try it out:

* Git
* Spring Boot 3+
* PostgresSQL 17+
* MongoDB 7.0+
* Gradle 9+
* JDK 24+
* Apache Kafka
* Kafbat UI (for Apache Kafka)
* Docker
* Docker compose
* Kubernetes
* Redis
* Prometheus
* Grafana
* Loki
* Watchtower
* IDE of your choice


### REST API:
- transactions/consistency
- async / Kafka or other messaging
- idempotency


POST /api/v1/orders — create an order

POST /api/v1/orders/{id}/pay — trigger asynchronous payment

GET /api/v1/orders/{id} — retrieve an order

GET /api/v1/orders — retrieve all orders (filtered by status, userId, dates)

PUT /api/v1/orders/{id}/status — change status only

DELETE /api/v1/orders/{id} — delete order (soft delete)
DELETE /api/v1/orders/{id} — delete order (hard delete)
