package com.example.cyberycon.smartmeter;

import com.example.cyberycon.smartmeter.event.MeterReadingSender;

import java.util.concurrent.CountDownLatch;

import static org.assertj.core.api.Assertions.fail;

public class MockSender implements MeterReadingSender {

    private CountDownLatch latch;

    private int lastReading = 0 ;

    @Override
    public void sendReading(long timestamp, int reading) {
        if (reading <= lastReading )
            fail("Reading values are not increasing") ;
        latch.countDown();
    }

    public void setCountdownLatch(CountDownLatch latch ) {
        this.latch = latch;
    }

}