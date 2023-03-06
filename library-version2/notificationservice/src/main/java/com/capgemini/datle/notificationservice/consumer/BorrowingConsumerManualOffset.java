package com.capgemini.datle.notificationservice.consumer;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.listener.AcknowledgingConsumerAwareMessageListener;
import org.springframework.kafka.support.Acknowledgment;

@Slf4j
public class BorrowingConsumerManualOffset implements AcknowledgingConsumerAwareMessageListener<String, Object> {


    @Override
    @KafkaListener(topics = {"notificationTopic"})
    public void onMessage(ConsumerRecord<String, Object> consumerRecord, Acknowledgment acknowledgment, Consumer<?, ?> consumer) {
        log.info("ConsumerRecord: {}", consumerRecord);
        acknowledgment.acknowledge();
    }
}
