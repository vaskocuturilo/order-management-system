package com.example.ordersapi.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/orders")
public class RestOrdersApiControllerV1 {

    @PostMapping
    public ResponseEntity<?> createOrder() {
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{id}/pay")
    public ResponseEntity<?> triggerAsynchronousPayment(@PathVariable("id") final int id) {
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> retrieveOrder(@PathVariable("id") final int id) {
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<?> retrieveAllOrders() {
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<?> changeStatus(@PathVariable("id") final int id) {
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> softDeleteOrder(@PathVariable("id") final int id) {
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> hardDeleteOrder(@PathVariable("id") final int id) {
        return ResponseEntity.ok().build();
    }
}
