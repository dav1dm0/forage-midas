package com.jpmc.midascore.component;

import com.jpmc.midascore.foundation.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class MidasKafkaListener {
    private static final Logger logger = LoggerFactory.getLogger(MidasKafkaListener.class);

    @KafkaListener(topics = "${general.kafka-topic}", groupId = "midas-group")
    public void listen(Transaction transaction) {
        // This log helps you see the transactions in the console
        logger.info("Received Transaction: {}", transaction);
    }
}