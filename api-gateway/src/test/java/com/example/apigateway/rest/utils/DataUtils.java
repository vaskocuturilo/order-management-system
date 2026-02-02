package com.example.apigateway.rest.utils;

import com.example.apigateway.dto.OrderDto;
import com.example.apigateway.dto.Status;

import java.math.BigDecimal;


public class DataUtils {

    public static OrderDto getOrderDtoTransient() {
        return OrderDto
                .builder()
                .totalAmount(new BigDecimal(100))
                .userId(1L)
                .status(Status.NEW)
                .build();
    }

    public static OrderDto getOrderDtoPersisted() {
        return OrderDto
                .builder()
                .id(1L)
                .totalAmount(new BigDecimal(100))
                .userId(1L)
                .status(Status.NEW)
                .build();
    }
}
