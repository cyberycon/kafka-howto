package com.example.cyberycon.smartmeter;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;


@ExtendWith(SpringExtension.class)
@SpringBootTest
@EmbeddedKafka(partitions = 2, brokerProperties = { "listeners=PLAINTEXT://localhost:19092", "port=19092" })
@ActiveProfiles("test")
public class SmartMeterIntegrationTests {

	private CountDownLatch latch ;

	@Autowired
	private Meter meter;

	@Test
	public void loadContext() {
		assertNotNull(meter);
	}

	@Test
	public void shouldSendReadings() throws InterruptedException {
		latch = new CountDownLatch(3) ;
		meter.start();
		assertTrue(latch.await(10, TimeUnit.SECONDS));
		meter.stop();
	}

	@KafkaListener(topics = "meter.reading")
	public void listen(ConsumerRecord<String,String> cr) {
		String[] values = cr.value().split(":");
		assertEquals(2, values.length);
	}

}
