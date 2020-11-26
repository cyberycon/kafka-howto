package com.example.cyberycon.smartmeter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import com.example.cyberycon.smartmeter.config.MeterConfiguration;
import com.example.cyberycon.smartmeter.event.MeterReadingSender;

@Service
public class Meter implements Runnable {
	
	private int lastReading ; 
	
	private boolean running ; 
	
	private MeterConfiguration config;

	private MeterReadingSender sender;

	private static Logger logger = LoggerFactory.getLogger(Meter.class);


	public Meter(MeterConfiguration config, MeterReadingSender sender) {
		this.config = config; 
		this.sender = sender; 
	}

	public void start() {
		logger.info("Config interval is " + config.getInterval() ) ;
		Thread t = new Thread(this); 
		t.start(); 
	}

	@Override
	public void run() {
		running = true ;
		while (running) { 
			try {
				Thread.sleep(config.getInterval() * 1000) ;
				sender.sendReading(System.currentTimeMillis(), nextReading()) ; 
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
