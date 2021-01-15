package com.example.cyberycon.smartmeter.config;

import com.example.cyberycon.smartmeter.Meter;
import com.example.cyberycon.smartmeter.event.MeterReadingSender;
import com.example.cyberycon.smartmeter.event.MeterReadingSenderKafkaImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.core.KafkaTemplate;

@Configuration
@ConfigurationProperties(prefix = "meter")
public class MeterConfiguration {
	private int interval;
	private String area ; 
	private String topic; 


	@Autowired
	private KafkaTemplate<String, String> template;

	public int getInterval() {
		return interval;
	}

	public void setInterval(int interval) {
		this.interval = interval;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getTopic() {
		return topic;
	}

	public void setTopic(String topic) {
		this.topic = topic;
	}
}
