package com.example.ordersapi.kafka.consumer;

import com.example.ordersapi.dto.OrderDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class KafkaConsumerService {
    @KafkaListener(topics = "${topic.name}", groupId = "${spring.kafka.consumer.group-id}", containerFactory = "kafkaListenerContainerFactory")
    public void consumeMessage(@Payload OrderDto orderDto, @Header(KafkaHeaders.RECEIVED_PARTITION) int partition, @Header(KafkaHeaders.OFFSET) long offset) {
        log.info("Successfully consumed from Topic: {}, Partition: {}, Offset: {}",
                "${topic.name}", partition, offset);

        log.info("Processing Country Data: {} ({})", orderDto.getId(), orderDto.getId());
    }
}
