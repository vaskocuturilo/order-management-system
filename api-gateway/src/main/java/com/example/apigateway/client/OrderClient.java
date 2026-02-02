package com.example.apigateway.client;

import com.example.apigateway.dto.OrderDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@Component
@RequiredArgsConstructor
public class OrderClient {

    private final WebClient webClient;

    @Value("${order.service.url}")
    private String orderServiceUrl;

    public Mono<OrderDto> getOrderById(Long id) {
        return webClient
                .get()
                .uri(orderServiceUrl + "/api/v1/orders" + id)
                .retrieve().bodyToMono(OrderDto.class)
                .switchIfEmpty(Mono.error(new IllegalStateException("The order is not found")))
                .doOnNext(body -> log.info("IN getOrderById - order with id {} and body {}", id, body));
    }

    public Flux<OrderDto> getOrders() {
        return webClient
                .get()
                .uri(orderServiceUrl + "/api/v1/orders")
                .retrieve().bodyToFlux(OrderDto.class)
                .switchIfEmpty(Mono.error(new IllegalStateException("The result cannot be null")))
                .doOnNext(orders -> log.debug("IN getOrders - orders received {}", orders))
                .doOnComplete(() -> log.info("IN getOrders - orders fetched successfully"));
    }
}
