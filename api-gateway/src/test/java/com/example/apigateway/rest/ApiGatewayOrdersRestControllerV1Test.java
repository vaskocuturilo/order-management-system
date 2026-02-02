package com.example.apigateway.rest;

import com.example.apigateway.client.OrderClient;
import com.example.apigateway.dto.OrderDto;
import com.example.apigateway.dto.Status;
import com.example.apigateway.rest.utils.DataUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Description;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;

import static org.mockito.ArgumentMatchers.anyLong;

@ComponentScan({"com.example.reactiveproject.errorhandling"})
@ExtendWith(SpringExtension.class)
@WebFluxTest(controllers = {ApiGatewayOrdersRestControllerV1Test.class})
class ApiGatewayOrdersRestControllerV1Test {

    @Autowired
    private final OrderClient orderClient;

    @Autowired
    private WebTestClient webTestClient;

    private static final String ENDPOINT_PATH = "/api/v1/orders";


    ApiGatewayOrdersRestControllerV1Test(OrderClient orderClient) {
        this.orderClient = orderClient;
    }

    @Test
    @Description("Test get all orders functionality")
    void givenOrder_whenGetOrders_thenSuccessResponse() {
        //given
        final OrderDto orderDto = DataUtils.getOrderDtoPersisted();

        BDDMockito.given(orderClient.getOrders()).willReturn((Flux.just(orderDto)));

        //when
        final WebTestClient.ResponseSpec result = webTestClient.get()
                .uri(ENDPOINT_PATH)
                .exchange();

        //then
        result.expectStatus().isOk()
                .expectBody().consumeWith(System.out::println)
                .jsonPath("$[*].id").isNotEmpty()
                .jsonPath("$[*].totalAmount").isNotEmpty()
                .jsonPath("$[*].userId").isNotEmpty()
                .jsonPath("$[*].status").isNotEmpty()
                .jsonPath("$.size()").isEqualTo(1);
    }

    @Test
    @Description("Test get order by id functionality")
    void givenId_whenGetOrderById_thenSuccessResponse() {
        //given
        final OrderDto orderDto = DataUtils.getOrderDtoTransient();

        BDDMockito.given(orderClient.getOrderById(anyLong())).willReturn((Mono.just(orderDto)));

        //when
        final WebTestClient.ResponseSpec result = webTestClient
                .get()
                .uri(ENDPOINT_PATH + "/" + orderDto.getId())
                .exchange();

        //then
        result.expectStatus().isOk()
                .expectBody().consumeWith(System.out::println)
                .jsonPath("$.id").isEqualTo(1L)
                .jsonPath("$.totalAmount").isEqualTo(new BigDecimal(100))
                .jsonPath("$.userId").isEqualTo(1L)
                .jsonPath("$.status").isEqualTo(Status.NEW);
    }

    @Test
    @Description("Test get order by id with incorrect id functionality")
    void givenIncorrectAlphaCode_whenGetCountryByAlphaCode_thenErrorResponse() {
        //given
        BDDMockito.given(orderClient.getOrderById(anyLong()))
                .willReturn(Mono.error(new IllegalStateException("The order is not found")));

        //when
        final WebTestClient.ResponseSpec result = webTestClient.get().uri(ENDPOINT_PATH + "/100").exchange();

        //then
        result.expectStatus().isNotFound()
                .expectBody().consumeWith(System.out::println)
                .jsonPath("$.errors.[0].message").isEqualTo("The order is not found");
    }
}