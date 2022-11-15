package com.cmcglobal.kafkastreamprocessor.consumers;

import com.cmcglobal.dashboard.avro.DashboardEmailValue;
import lombok.extern.log4j.Log4j2;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Component;
import org.springframework.kafka.annotation.KafkaListener;

@Component
@Log4j2
public class ConsumerExampleImpl implements BaseConsumerInterface {

    @KafkaListener(topics = "dashboard.email.topic", groupId = "dashboard.email")
    @Retryable(
            value = {Exception.class},
            maxAttempts = 10, // default 3
            backoff = @Backoff(delay = 10))
    public void consumer(@Payload(required = false) DashboardEmailValue value) {
        log.info("dashboard email value 1 {}", value);
    }
}
