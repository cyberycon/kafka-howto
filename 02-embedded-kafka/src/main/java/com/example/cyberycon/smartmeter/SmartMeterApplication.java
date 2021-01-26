package com.example.cyberycon.smartmeter;

import com.example.cyberycon.smartmeter.config.MeterConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SmartMeterApplication implements CommandLineRunner {

	private static Logger logger = LoggerFactory.getLogger(SmartMeterApplication.class);

	@Autowired
	private MeterConfiguration meterConfiguration;

	@Autowired
	private Meter meter;

	public static void main(String[] args) {
		SpringApplication.run(SmartMeterApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Runtime runtime = Runtime.getRuntime();
		runtime.addShutdownHook(new Thread() {
			public void run() {
				logger.info("Stopping");
				meter.stop();
			}
		});
		processArguments(args, meterConfiguration);
		meter.start();
	}

	static void processArguments(String[] args, MeterConfiguration config) {
		// optional arguments id, area, interval - in that order
		if (args.length > 0) {
			config.setId(args[0]);
			logger.info("id = " + args[0]);
			if (args.length >= 2) {
				logger.info("area = " + args[1]);
				config.setArea(args[1]);
			}
			if (args.length == 3) {
				logger.info("interval = " + args[2]);
				config.setInterval(Integer.parseInt(args[2]));
			}
		}
	}
}

