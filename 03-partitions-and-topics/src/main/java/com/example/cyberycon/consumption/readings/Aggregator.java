package com.example.cyberycon.consumption.readings;

import java.util.Date;

import org.springframework.stereotype.Component;

@Component 
public class Aggregator implements ReadingConsumer {

	private int total ; 
	
	private long startTime ; 
	
	public void start() {
		startTime = new Date().getTime() ; 
	}
	
	@Override
	public void nextReading(String reading) {
				long currentTime = new Date().getTime() ;
		String[] readingParts = reading.split(":");
		if (readingParts.length != 3) {
			// TODO - Create custom exception type
			throw new RuntimeException("Invalid reading") ; 
		}
		// TODO - Wrap in try catch and throw custom exception
		int latestValue = Integer.parseInt(readingParts[2]) ; 
		total += latestValue; 
		float average = total * 1000 / (startTime - currentTime); 
		System.out.println(String.format("Total consumption %d", total )) ; 
		System.out.println(String.format("Average consumption/s %d", average )) ;
		
	}

}
