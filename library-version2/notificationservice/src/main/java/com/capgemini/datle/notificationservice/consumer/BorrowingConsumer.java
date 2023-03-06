package com.capgemini.datle.notificationservice.consumer;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class BorrowingConsumer {
    @KafkaListener(topics = {"notificationTopic"})
    public void onMessage(ConsumerRecord<String, Object> consumerRecord){
        log.info("ConsumerRecord: {}", consumerRecord);
    }
}
