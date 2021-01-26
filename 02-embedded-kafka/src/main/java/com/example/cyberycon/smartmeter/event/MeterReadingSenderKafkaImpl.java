package com.example.cyberycon.smartmeter.event;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;

import org.springframework.stereotype.Service;

@Service
public class MeterReadingSenderKafkaImpl implements MeterReadingSender {

	private static Logger logger = LoggerFactory.getLogger(MeterReadingSender.class);

	private KafkaTemplate<String,String> kafkaTemplate;

	@Value("${meter.topic}")
	private String topic ;

	@Value ("${meter.area}")
	private String area ;

	public MeterReadingSenderKafkaImpl(KafkaTemplate<String,String> kafkaTemplate) {
		this.kafkaTemplate = kafkaTemplate;
	}
	
	@Override
	public void sendReading(String meterId, long timestamp, int reading) {
		String meterReading = String.format("%s:%d:%d", meterId, timestamp, reading);
		logger.info(String.format("Sending %s", meterReading));
		kafkaTemplate.send(topic, area, meterReading);
	}

}
