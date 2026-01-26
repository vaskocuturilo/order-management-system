package com.example.ordersapi.kafka.producer;

import com.example.ordersapi.dto.OrderDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@Slf4j
public class KafkaProducerService {

    private final String topicName;

    private final KafkaTemplate<String, OrderDto> kafkaTemplate;

    public KafkaProducerService(@Value("${topic.name}") String topicName, KafkaTemplate<String, OrderDto> kafkaTemplate) {
        this.topicName = topicName;
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(final OrderDto order) {
        log.info("Sending order: {} to topic: {}", order, topicName);

        final Long id = order.getId();

        log.info("Attempting to send order: {} with id: {}", order.getId(), id);

        kafkaTemplate.send(topicName, String.valueOf(id), order).whenComplete((result, exception) -> {
            if (Objects.isNull(exception)) {
                log.info("Successfully sent message to topic {} [partition: {}, offset: {}]",
                        result.getRecordMetadata().topic(),
                        result.getRecordMetadata().partition(),
                        result.getRecordMetadata().offset());
            } else {
                log.error("Unable to send message: {} due to : {}", order, exception.getMessage());
            }
        });

        log.info("The message {} has been send to the topic {}", order, topicName);
    }
}
