package com.example.cyberycon.smartmeter;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.concurrent.CountDownLatch;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class TestListener {

    private CountDownLatch latch ;

    @KafkaListener(topics = "meter.reading")
    public void listen(ConsumerRecord<String,String> cr) {
        String[] values = cr.value().split(":");
        assertEquals(3, values.length);
        if (latch != null) {
            latch.countDown();
        }
    }

    public void setLatch(CountDownLatch latch) {
        this.latch = latch;
    }
}
