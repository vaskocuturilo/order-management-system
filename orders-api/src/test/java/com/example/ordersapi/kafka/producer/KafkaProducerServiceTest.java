package com.example.ordersapi.kafka.producer;

import com.example.ordersapi.dto.OrderDto;
import com.example.ordersapi.util.DataUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;

import java.util.concurrent.CompletableFuture;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class KafkaProducerServiceTest {

    @Mock
    private KafkaTemplate<String, OrderDto> kafkaTemplate;

    private KafkaProducerService kafkaProducerService;

    private final String topicName = "test-orders";

    @BeforeEach
    void setUp() {
        kafkaProducerService = new KafkaProducerService(topicName, kafkaTemplate);
    }

    @Test
    void shouldSendMessageSuccessfully() {
        // Arrange
        final OrderDto country = DataUtils.getOrderDtoPersisted();


        CompletableFuture<SendResult<String, OrderDto>> future = new CompletableFuture<>();
        when(kafkaTemplate.send(anyString(), anyString(), any(OrderDto.class))).thenReturn(future);


        kafkaProducerService.sendMessage(country);


        verify(kafkaTemplate, times(1)).send(topicName, "", country);
    }
}