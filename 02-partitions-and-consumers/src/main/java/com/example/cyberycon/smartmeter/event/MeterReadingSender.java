package com.example.cyberycon.smartmeter.event;

public interface MeterReadingSender {
	
	void sendReading(long timestamp, int reading) ;

}
