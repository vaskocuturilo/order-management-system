package com.example.ordersapi.rest;

import com.example.ordersapi.dto.OrderDto;
import com.example.ordersapi.entity.OrderEntity;
import com.example.ordersapi.service.IOrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/orders")
public class RestOrderApiControllerV1 {

    private final IOrderService orderService;

    @PostMapping
    public ResponseEntity<OrderDto> createOrder(@RequestBody final OrderDto order) {
        final OrderEntity orderEntity = order.toEntity();
        final OrderEntity saveOrder = orderService.createOrder(orderEntity);
        final OrderDto orderDto = OrderDto.fromEntity(saveOrder);

        return ResponseEntity.status(HttpStatus.CREATED)
                .contentType(MediaType.APPLICATION_JSON)
                .body(orderDto);
    }

    @PostMapping("/pay")
    public ResponseEntity<String> triggerAsynchronousPayment(@RequestBody final OrderDto order) {
        orderService.triggerAsynchronousPayment(order);

        log.info("The message {} has been send to the pay system", order);

        return ResponseEntity
                .status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body("The message to pay system has been send successfully");
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
