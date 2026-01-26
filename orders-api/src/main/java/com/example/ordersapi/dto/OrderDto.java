package com.example.ordersapi.dto;

import com.example.ordersapi.entity.OrderEntity;
import com.example.ordersapi.entity.Status;

import java.math.BigDecimal;

public class OrderDto {

    private Long id;

    private Long userId;

    private BigDecimal totalAmount;

    private Status status;

    public OrderEntity toEntity() {
        //add builder
        return new OrderEntity();
    }

    public static OrderDto fromEntity(final OrderEntity orderEntity) {
        //add builder
        return new OrderDto();
    }

    public Long getId() {
        return id;
    }
}
