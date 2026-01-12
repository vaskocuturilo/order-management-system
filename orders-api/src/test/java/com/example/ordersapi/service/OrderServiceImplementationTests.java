package com.example.ordersapi.service;

import com.example.ordersapi.entity.OrderEntity;
import com.example.ordersapi.repository.OrderRepository;
import com.example.ordersapi.util.DataUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;

@ExtendWith(MockitoExtension.class)
class OrderServiceImplementationTests {

    @InjectMocks
    private OrderServiceImplementation serviceUnderTest;

    @Mock
    private OrderRepository orderRepository;

    @Test
    @DisplayName("Test create order functionality")
    void givenOrderToCreate_whenCreateOrder_thenRepositoryCalled() {
        //given
        final OrderEntity orderToSave = DataUtils.getOrderTransient();

        BDDMockito.given(orderRepository.findById(anyLong())).willReturn(null);

        BDDMockito.given(orderRepository.save(any(OrderEntity.class))).willReturn(DataUtils.getOrderPersisted());
        //when
        final OrderEntity createOrder = serviceUnderTest.createOrder(orderToSave);

        //then
        assertThat(createOrder).isNotNull();
    }
}