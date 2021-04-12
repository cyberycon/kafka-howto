package com.example.cyberycon.consumption.event;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.example.cyberycon.consumption.readings.ReadingConsumer;

@Service
public class ReadingListener {
    private static Logger logger = LoggerFactory.getLogger(ReadingListener.class) ;
    private ReadingConsumer readingConsumer; 
    
    public ReadingListener(ReadingConsumer readingConsumer) { 
    	this.readingConsumer = readingConsumer;
    }

    @KafkaListener(topics = "${meter.topic}")
    public void onEvent(ConsumerRecord<String,String> record) {
        logger.info(record.value());
        readingConsumer.nextReading(record.value());
    }

}
