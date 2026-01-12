package com.example.ordersapi.service;

import com.example.ordersapi.dto.OrderDto;
import com.example.ordersapi.entity.OrderEntity;

import java.util.List;

public interface IOrderService {

    OrderEntity createOrder(OrderEntity order);

    void triggerAsynchronousPayment(Long id);

    OrderEntity retrieveOrder(Long id);

    List<OrderEntity> retrieveAllOrders();

    OrderEntity changeStatus(Long id);

    void softDeleteOrder(Long id);

    void hardDeleteOrder(Long id);
}
