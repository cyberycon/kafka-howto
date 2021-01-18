package com.example.cyberycon.smartmeter;

import com.example.cyberycon.smartmeter.event.MeterReadingSender;

import java.util.concurrent.CountDownLatch;

import static org.assertj.core.api.Assertions.fail;

public class MockSender implements MeterReadingSender {

    private CountDownLatch latch;

    private int lastReading = 0 ;

    private String meterId ;

    @Override
    public void sendReading(String meterId, long timestamp, int reading) {
        if (reading <= lastReading )
            fail("Reading values are not increasing") ;
        this.meterId = meterId;
        latch.countDown();

    }

    public void setCountdownLatch(CountDownLatch latch ) {
        this.latch = latch;
    }

    public String getMeterId() {
        return meterId;
    }
}