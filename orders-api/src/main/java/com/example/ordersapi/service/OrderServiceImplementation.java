package com.example.ordersapi.service;

import com.example.ordersapi.dto.OrderDto;
import com.example.ordersapi.entity.OrderEntity;
import com.example.ordersapi.entity.Status;
import com.example.ordersapi.exception.OrderFoundException;
import com.example.ordersapi.kafka.producer.KafkaProducerService;
import com.example.ordersapi.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderServiceImplementation implements IOrderService {

    private final OrderRepository orderRepository;

    private final KafkaProducerService kafkaProducerService;

    @Override
    public OrderEntity createOrder(OrderEntity order) {
        final Optional<OrderEntity> existOrder = orderRepository.findById(order.getId());

        if (existOrder.isPresent()) {
            throw new OrderFoundException("The order already exists");
        }

        order.setStatus(Status.NEW);

        return orderRepository.save(order);
    }

    @Override
    public void triggerAsynchronousPayment(final OrderDto order) {
        kafkaProducerService.sendMessage(order);
        log.info("The message {} has been send to the pay system", order);
    }

    @Override
    public OrderEntity retrieveOrder(Long id) {
        return null;
    }

    @Override
    public List<OrderEntity> retrieveAllOrders() {
        return List.of();
    }

    @Override
    public OrderEntity changeStatus(Long id) {
        return null;
    }

    @Override
    public void softDeleteOrder(Long id) {

    }

    @Override
    public void hardDeleteOrder(Long id) {

    }
}
