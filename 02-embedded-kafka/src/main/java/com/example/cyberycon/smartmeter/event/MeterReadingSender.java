package com.example.cyberycon.smartmeter.event;

public interface MeterReadingSender {
	
	void sendReading(String meterId, long timestamp, int reading) ;

}
