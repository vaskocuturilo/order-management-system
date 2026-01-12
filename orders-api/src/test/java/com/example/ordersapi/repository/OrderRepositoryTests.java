package com.example.ordersapi.repository;

import com.example.ordersapi.entity.OrderEntity;
import com.example.ordersapi.util.DataUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@DataJpaTest(properties = {
        "spring.jpa.properties.javax.persistence.validation.mode=none"
})
class OrderRepositoryTests {

    @Autowired
    private OrderRepository orderRepository;

    @BeforeEach
    void setUp() {
        orderRepository.deleteAll();
    }

    @Test
    @DisplayName("Test create order functionality")
    void givenOrderObject_whenSave_thenOrderIsCreated() {
        //given
        final OrderEntity orderEntity = DataUtils.getOrderTransient();

        //when
        final OrderEntity savedOrder = orderRepository.save(orderEntity);

        //then
        assertThat(savedOrder).isNotNull();
        assertThat(savedOrder.getId()).isNotNull();
    }
}