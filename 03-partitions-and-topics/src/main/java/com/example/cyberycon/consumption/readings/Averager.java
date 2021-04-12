package com.example.cyberycon.consumption.readings;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component 
public class Averager implements ReadingConsumer {

	private final Logger logger = LoggerFactory.getLogger(Averager.class);
	
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
			throw new RuntimeException("Invalid reading") ; 
		}

		int latestValue = Integer.parseInt(readingParts[2]) ; 
		total += latestValue; 
		float average = total * 1000 / (startTime - currentTime); 
		logger.info("Total consumption {}", total ) ; 
		logger.info("Average consumption/s {}", average ) ;
		}
}
