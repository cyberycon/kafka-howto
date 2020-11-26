package com.example.cyberycon.smartmeter;

import static org.assertj.core.api.Assertions.fail;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.example.cyberycon.smartmeter.event.MeterReadingSender;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@ActiveProfiles("test")
public class SmartMeterTests {
	
	@Autowired 
	private Meter meter ;
	
	@Autowired
	private MeterReadingSender sender;  
		
	@Test
	public void loadContext()  {
		assertNotNull(meter); 
		assertNotNull(sender);
	}
	
	@Test
	public void testSendAndReceive() throws Exception {
		CountDownLatch latch = new CountDownLatch(3); 
		((MockSender) sender).setCountdownLatch(latch);
		meter.start(); 
		assertTrue(latch.await(10, TimeUnit.SECONDS)) ;
	}

	public static class MockSender implements MeterReadingSender {

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
	
	@TestConfiguration
	public static class Config { 
		@Bean 
		public MeterReadingSender getSender () {
			return new MockSender() ;  
		}
	}


}
 

 