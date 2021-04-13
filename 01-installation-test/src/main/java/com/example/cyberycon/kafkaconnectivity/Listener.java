package com.example.cyberycon.kafkaconnectivity;

import java.util.concurrent.CountDownLatch;

import org.springframework.kafka.annotation.KafkaListener;

public class Listener {
    private final CountDownLatch latch1 = new CountDownLatch(1);

    @KafkaListener(id = "xxx", topics = "meter.reading.1")
    public void listen1(String foo) {
        this.latch1.countDown();
    }
}
