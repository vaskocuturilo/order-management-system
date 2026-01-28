# The order management system. 

## Design a simplified order management system for an online store:

- Users create orders.
- orders contain products and status (NEW, PAID, SHIPPED, CANCELLED)
- Payments are processed asynchronously through an external service.
- The system must be fault-tolerant and scalable


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
