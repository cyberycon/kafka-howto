package com.example.cyberycon.smartmeter;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest
@EmbeddedKafka(partitions = 2, brokerProperties = { "listeners=PLAINTEXT://localhost:19092", "port=19092" })
public class SmartMeterIntegrationTests {

	@Test
	public void loadContext() { 
	}
	
}
