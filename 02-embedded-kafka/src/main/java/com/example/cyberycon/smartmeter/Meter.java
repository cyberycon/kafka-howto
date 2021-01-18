package com.example.cyberycon.smartmeter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import com.example.cyberycon.smartmeter.event.MeterReadingSender;

@Component
public class Meter implements Runnable {
	
	private int lastReading ; 
	
	private boolean running ;

	@Value("${meter.interval}")
	private int readingInterval ;

	@Value("${meter.id}")
	private String meterId;

	private MeterReadingSender sender;

	private static Logger logger = LoggerFactory.getLogger(Meter.class);


	public Meter(MeterReadingSender sender) {
		this.sender = sender;
	}

	public void start() {
		logger.info("Config interval is " + readingInterval ) ;
		Thread t = new Thread(this); 
		t.start(); 
	}

	@Override
	public void run() {
		running = true ;
		while (running) { 
			try {
				Thread.sleep(readingInterval * 1000) ;
				sender.sendReading(meterId, System.currentTimeMillis(), nextReading()) ;
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
