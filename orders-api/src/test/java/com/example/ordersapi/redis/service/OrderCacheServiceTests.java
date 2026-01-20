package com.example.ordersapi.redis.service;

import com.example.ordersapi.entity.OrderEntity;
import com.example.ordersapi.entity.Status;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OrderCacheServiceTests {
    @Mock
    private RedisTemplate<String, OrderEntity> redisTemplate;

    @Mock
    private ListOperations<String, OrderEntity> listOps;

    @Mock
    private ValueOperations<String, OrderEntity> valueOps;

    private OrderCacheService cacheService;

    @BeforeEach
    void setUp() {
        Mockito.lenient().when(redisTemplate.opsForList()).thenReturn(listOps);
        Mockito.lenient().when(redisTemplate.opsForValue()).thenReturn(valueOps);
        cacheService = new OrderCacheService(redisTemplate);
    }

    @Test
    void shouldCacheOrderCorrectly() {
        // Arrange
        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setId(new Random().nextLong());
        orderEntity.setStatus(Status.NEW);

        // Act
        cacheService.cacheCountry(orderEntity);

        // Assert
        String listKey = "orderId:";
        String itemKey = "Status:" + orderEntity.getStatus();

        verify(listOps).rightPush(listKey, orderEntity);
        verify(valueOps).set(itemKey, orderEntity);
    }

    @Test
    void shouldReturnOrderFromCache() {
        // Arrange
        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setId(new Random().nextLong());
        orderEntity.setStatus(Status.NEW);

        List<OrderEntity> mockList = List.of(orderEntity);

        when(listOps.range("orderId:", 0, -1)).thenReturn(mockList);

        // Act
        List<OrderEntity> result = cacheService.getOrderById(1L);

        // Assert
        assertEquals(1, result.size());
        assertEquals(orderEntity.getId(), result.getFirst().getId());
    }

    @Test
    void shouldReturnEmptyListWhenCacheIsEmpty() {
        // Arrange
        when(listOps.range("orderId:2L", 0, -1)).thenReturn(null);

        // Act
        List<OrderEntity> result = cacheService.getOrderById(2L);

        // Assert
        assertTrue(result.isEmpty());
    }
}