package com.example.ordersapi.redis.service;

import com.example.ordersapi.entity.OrderEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
@Slf4j
public class OrderCacheService {
    private final RedisTemplate<String, OrderEntity> redisTemplate;

    public OrderCacheService(RedisTemplate<String, OrderEntity> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public void cacheCountry(OrderEntity order) {
        final String id = "orderId:" + order.getId();
        final String status = "Statis:" + order.getStatus();


        redisTemplate.opsForList().rightPush(id, order);
        redisTemplate.opsForValue().set(status, order);

        log.info("Cached order [{}] to Redis under list [{}]", order.getId(), status);

    }

    public List<OrderEntity> getOrderById(String orderId) {
        final String listKey = "orderId::" + orderId;
        final var cachedList = redisTemplate.opsForList().range(listKey, 0, -1);

        if (cachedList == null || cachedList.isEmpty()) {
            log.info("Redis cache miss for level: {}", orderId);
            return Collections.emptyList();
        }

        log.info("Fetched {} orders from Redis list for level: {}", cachedList.size(), orderId);

        return cachedList;
    }
}
