package com.example.ordersapi.rest;


import com.example.ordersapi.dto.OrderDto;
import com.example.ordersapi.entity.OrderEntity;
import com.example.ordersapi.service.IOrderService;
import com.example.ordersapi.util.DataUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@WebMvcTest
class RestOrderApiControllerV1Tests {

    @MockitoBean
    private IOrderService orderService;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    private static final String ENDPOINT_PATH = "/api/v1/orders";

    @Test
    @DisplayName("Test create a new order functionality")
    void givenOrderDto_whenCreateOrder_thenSuccessResponse() throws Exception {

        //given
        final OrderDto orderDto = DataUtils.getOrderDtoTransient();

        final OrderEntity orderEntity = DataUtils.getOrderPersisted();

        BDDMockito.given(orderService.createOrder(any(OrderEntity.class))).willReturn(orderEntity);

        //when
        final ResultActions result = mockMvc.perform(post(ENDPOINT_PATH)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsBytes(orderDto)));

        //then
        result.andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(jsonPath("$.id").isNotEmpty())
                .andExpect(jsonPath("$.id", CoreMatchers.notNullValue()))
                .andExpect(jsonPath("$.status", CoreMatchers.is("NEW")));
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