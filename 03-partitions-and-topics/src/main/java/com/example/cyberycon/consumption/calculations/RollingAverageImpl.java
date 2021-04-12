package com.example.cyberycon.consumption.calculations;

import java.util.ArrayDeque;
import java.util.Date;
import java.util.Iterator;
import java.util.Queue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RollingAverageImpl implements RollingAverage {

    private Logger logger = LoggerFactory.getLogger(RollingAverageImpl.class); 

    private int window  = 0 ;
    private final Queue<TimedValue> values = new ArrayDeque<>() ;
    @Override
    public void addValue(int value) {
        values.add(new TimedValue(value)) ; 
    }

    @Override
    public double latestAverage() {

        long now = new Date().getTime();
        logger.info("START LatestAverage timestamp = {}", now); 

        Iterator<TimedValue>  iterator = values.iterator() ; 
        while (iterator.hasNext()) {
            TimedValue tv = iterator.next(); 
            logger.info (tv.toString()); 
            if ( (now - tv.timestamp) > window) {
                logger.info("Removing: {}", tv) ; 
                values.remove();
            }
        }
        logger.info("END   LatestAverage() "); 
        return values.stream().mapToInt(TimedValue::valueOf).average().getAsDouble() ;

    }

    @Override
    public void setWindow(int milliseconds) {
       window = milliseconds; 
        
    }

    class TimedValue { 
        int value ; 
        long timestamp; 

        TimedValue(int value ) {
            this.value = value ; 
            this.timestamp = new Date().getTime(); 
        }

        public int valueOf() {
            return value; 
        }

        public long getTimestamp()  { 
            return timestamp;
        }

        @Override
        public String toString() {
            return String.format("%d : %d", value, timestamp) ;
        }
    

    }
    
}
