package com.example.cyberycon.consumption.event;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class ReadingListener {
    private static Logger logger = LoggerFactory.getLogger(ReadingListener.class) ;


    @KafkaListener(topics = "${meter.topic}")
    public void onEvent(ConsumerRecord<String,String> record) {
        logger.info(record.value());
    }

}
