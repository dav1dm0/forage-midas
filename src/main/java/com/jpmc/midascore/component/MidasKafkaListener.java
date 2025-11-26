package com.jpmc.midascore.component;

import com.jpmc.midascore.foundation.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class MidasKafkaListener {
    private static final Logger logger = LoggerFactory.getLogger(MidasKafkaListener.class);
    private final DatabaseConduit databaseConduit;

    public MidasKafkaListener(DatabaseConduit databaseConduit) {
        this.databaseConduit = databaseConduit;
    }
    @KafkaListener(topics = "${general.kafka-topic}", groupId = "midas-group")
    public void listen(Transaction transaction) {
        logger.info("Received Transaction: {}", transaction);
        databaseConduit.save(transaction);
    }
}