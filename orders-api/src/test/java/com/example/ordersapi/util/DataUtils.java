package com.example.ordersapi.util;

import com.example.ordersapi.dto.OrderDto;
import com.example.ordersapi.entity.OrderEntity;

public class DataUtils {

    public static OrderEntity getOrderTransient() {
        return new OrderEntity();
    }

    public static OrderEntity getOrderPersisted() {
        return new OrderEntity();
    }

    public static OrderDto getOrderDtoTransient() {
        return new OrderDto();
    }

    public static OrderDto getOrderDtoPersisted() {
        return new OrderDto();
    }
}
