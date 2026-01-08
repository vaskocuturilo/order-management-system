package com.example.ordersapi.dto;

import com.example.ordersapi.entity.OrderEntity;
import com.example.ordersapi.entity.OrderItemsEntity;

import java.math.BigDecimal;

public class OrderItemsDto {

    private Long id;

    private Long order_id;

    private Long product_id;

    private int quantity;

    private BigDecimal price;

    public OrderItemsEntity toEntity() {
        //add builder
        return new OrderItemsEntity();
    }

    public static OrderItemsDto fromEntity(final OrderEntity orderEntity) {
        //add builder
        return new OrderItemsDto();
    }
}
