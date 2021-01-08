package com.example.cyberycon.smartmeter.event;

import org.springframework.kafka.core.KafkaTemplate;

import com.example.cyberycon.smartmeter.config.MeterConfiguration;
import org.springframework.stereotype.Service;

@Service
public class MeterReadingSenderKafkaImpl implements MeterReadingSender {

	private KafkaTemplate<String,String> kafkaTemplate;
	private MeterConfiguration config;

	public MeterReadingSenderKafkaImpl(KafkaTemplate<String,String> kafkaTemplate, MeterConfiguration config) {
		this.config = config; 
		this.kafkaTemplate = kafkaTemplate; 
	}
	
	@Override
	public void sendReading(long timestamp, int reading) {
		String meterReading = String.format("%d:%d", timestamp, reading); 
		kafkaTemplate.send(config.getTopic(), config.getArea(), meterReading); 
	}

}
