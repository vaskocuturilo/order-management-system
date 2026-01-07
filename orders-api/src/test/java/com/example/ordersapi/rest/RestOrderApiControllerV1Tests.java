package com.example.ordersapi.rest;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

@WebMvcTest
class RestOrderApiControllerV1Tests {

    @Test
    @DisplayName("Test create a new order functionality")
    void givenOrderDto_whenCreateOrder_thenSuccessResponse() throws Exception {
        //given

        //when

        //then
    }

    @Test
    @DisplayName("Test create a new order with duplicate data functionality")
    void givenOrderDtoWithDuplicateData_whenCreateOrder_thenErrorResponse() throws Exception {
        //given

        //when

        //then
    }

    @Test
    @DisplayName("Test retrieve order by id functionality")
    void givenOrderId_whenRetrieveOrderById_thenSuccessResponse() throws Exception {
        //given

        //when

        //then
    }

    @Test
    @DisplayName("Test retrieve order order by id with incorrect id functionality")
    void givenIncorrectOrderId_whenRetrieveOrderById_thenErrorResponse() throws Exception {
        //given

        //when

        //then
    }

    @Test
    @DisplayName("Test retrieve all orders functionality")
    void givenOrders_whenRetrieveAllOrders_thenSuccessResponse() throws Exception {
        //given

        //when

        //then
    }

    @Test
    @DisplayName("Test change order status functionality")
    void givenOrderId_whenChangeOrderStatus_thenSuccessResponse() throws Exception {
        //given

        //when

        //then
    }

    @Test
    @DisplayName("Test change order status with incorrect order id functionality")
    void givenIncorrectOrderId_whenChangeOrderStatus_thenErrorResponse() throws Exception {
        //given

        //when

        //then
    }

    @Test
    @DisplayName("Test soft delete functionality")
    void givenOrderId_whenSoftDeleteOrderById_thenSuccessResponse() throws Exception {
        //given

        //when

        //then
    }

    @Test
    @DisplayName("Test soft delete with incorrect id functionality")
    void givenIncorrectOrderId_whenSoftDeleteOrderById_thenErrorResponse() throws Exception {
        //given

        //when

        //then
    }

    @Test
    @DisplayName("Test hard delete functionality")
    void givenOrderId_whenHardDeleteOrderById_thenSuccessResponse() throws Exception {
        //given

        //when

        //then
    }

    @Test
    @DisplayName("Test hard delete with incorrect id functionality")
    void givenIncorrectOrderId_whenHardDeleteOrderById_thenErrorResponse() throws Exception {
        //given

        //when

        //then
    }
}