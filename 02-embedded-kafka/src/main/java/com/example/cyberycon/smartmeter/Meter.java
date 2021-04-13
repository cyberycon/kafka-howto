package com.example.cyberycon.smartmeter;

import com.example.cyberycon.smartmeter.config.MeterConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import com.example.cyberycon.smartmeter.event.MeterReadingSender;

@Component
public class Meter implements Runnable {
	
	private int lastReading ; 
	
	private boolean running ;

	private MeterConfiguration config;

	private int readingInterval ;

	private String meterId;

	private MeterReadingSender sender;

	private static Logger logger = LoggerFactory.getLogger(Meter.class);


	public Meter(MeterReadingSender sender, MeterConfiguration config) {
		this.sender = sender;
		this.config = config;
	}

	public void start() {
		readingInterval = config.getInterval();
		meterId = config.getId();
		logger.info("Config interval is " + readingInterval ) ;
		Thread t = new Thread(this);
		t.start(); 
	}

	@Override
	public void run() {
		running = true ;
		while (running) { 
			try {
				sender.sendReading(meterId, System.currentTimeMillis(), nextReading()) ;
				Thread.sleep(readingInterval * 1000) ;
			} catch (InterruptedException e) {
				e.printStackTrace();
			} 
		}
	}
	
	public int nextReading() { 
		lastReading = lastReading + (int) Math.rint (Math.random() * 5 + 1)  ; 
		return lastReading; 
	}
	
	public void stop() {
		running = false; 
	}
}
