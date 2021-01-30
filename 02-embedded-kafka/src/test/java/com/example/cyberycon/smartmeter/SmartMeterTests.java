package com.example.cyberycon.smartmeter;

import static org.assertj.core.api.Assertions.fail;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

import com.example.cyberycon.smartmeter.event.MeterReadingSender;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@Import(TestConfig.class)
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


}
 

 