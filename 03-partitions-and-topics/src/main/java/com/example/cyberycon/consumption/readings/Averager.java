package com.example.cyberycon.consumption.readings;

import java.util.Date;

import com.example.cyberycon.consumption.calculations.RollingAverage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component 
public class Averager implements ReadingConsumer {

	private final Logger logger = LoggerFactory.getLogger(Averager.class);

	private RollingAverage rollingAverage; 
	
	public Averager (RollingAverage rollingAverage) {
		this.rollingAverage = rollingAverage; 
	}

	@Override
	public void nextReading(String reading) {
		String[] readingParts = reading.split(":");
		if (readingParts.length != 3) {
			throw new RuntimeException("Invalid reading") ; 
		}
		try {
			int readingValue = Integer.parseInt(readingParts[2]); 
			rollingAverage.addValue(readingValue);
			logger.debug("Latest average" ); 
		}
		catch (NumberFormatException e) {
			throw new RuntimeException (e) ; 
		}
		
	}
}
