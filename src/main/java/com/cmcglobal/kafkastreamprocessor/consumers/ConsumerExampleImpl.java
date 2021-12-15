package com.cmcglobal.kafkastreamprocessor.consumers;

import com.cmcglobal.avro.ExampleAvro;
import lms.courses.course.Value;
import lombok.extern.log4j.Log4j2;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Component;
import org.springframework.kafka.annotation.KafkaListener;

@Component
@Log4j2
public class ConsumerExampleImpl implements BaseConsumerInterface {

    @KafkaListener(topics = "lms.schema.table", groupId = "lms.schema.table")
    @Retryable(
            value = {Exception.class},
            maxAttempts = 10, // default 3
            backoff = @Backoff(delay = 10))
    public void consumer(@Payload(required = false) ExampleAvro value) {
        log.info("consumer value {}", value);
    }


    @KafkaListener(topics = "lms.courses.course", groupId = "lms.courses-1")
    @Retryable(
            value = {Exception.class},
            maxAttempts = 10, // default 3
            backoff = @Backoff(delay = 10))
    public void consumerGroup1(@Payload(required = false) Value value) {
        log.info("value group 1: {}", value);
    }

//    @KafkaListener(topics = "lms.courses.course", groupId = "lms.courses-2")
//    @Retryable(
//            value = {Exception.class},
//            maxAttempts = 10, // default 3
//            backoff = @Backoff(delay = 10))
//    public void consumerGroup2(@Payload(required = false) Value value) {
//        log.info("value group 2: {}", value);
//    }
}
