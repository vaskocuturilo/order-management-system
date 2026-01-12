package com.example.ordersapi.rest;

import com.example.ordersapi.dto.OrderDto;
import com.example.ordersapi.entity.OrderEntity;
import com.example.ordersapi.service.IOrderService;
import com.example.ordersapi.service.OrderServiceImplementation;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/orders")
public class RestOrderApiControllerV1 {

    private final IOrderService orderService;

    public RestOrderApiControllerV1(IOrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public ResponseEntity<OrderDto> createOrder(@RequestBody final OrderDto order) {
        final OrderEntity orderEntity = order.toEntity();
        final OrderEntity saveOrder = orderService.createOrder(orderEntity);
        final OrderDto orderDto = OrderDto.fromEntity(saveOrder);

        return ResponseEntity.status(HttpStatus.CREATED)
                .contentType(MediaType.APPLICATION_JSON)
                .body(orderDto);
    }

    @PostMapping("/{id}/pay")
    public ResponseEntity<Void> triggerAsynchronousPayment(@PathVariable("id") final Long id) {
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderDto> retrieveOrder(@PathVariable("id") final Long id) {
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<List<OrderDto>> retrieveAllOrders() {
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<OrderDto> changeStatus(@PathVariable("id") final Long id) {
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> softDeleteOrder(@PathVariable("id") final Long id) {
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> hardDeleteOrder(@PathVariable("id") final Long id) {
        return ResponseEntity.ok().build();
    }
}
