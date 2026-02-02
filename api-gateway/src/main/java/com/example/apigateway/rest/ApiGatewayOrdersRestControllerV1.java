package com.example.apigateway.rest;


import com.example.apigateway.client.OrderClient;
import com.example.apigateway.dto.OrderDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/orders")
public class ApiGatewayOrdersRestControllerV1 {

    private final OrderClient orderClient;

    @GetMapping("/{id}")
    public Mono<ResponseEntity<OrderDto>> getCountryByName(@PathVariable("id") Long id) {
        return orderClient.getOrderById(id).map(ResponseEntity::ok);
    }

    @GetMapping
    public Flux<OrderDto> getOrders() {
        return orderClient.getOrders();
    }
}
