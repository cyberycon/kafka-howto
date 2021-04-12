package com.example.cyberycon.consumption.calculations;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;

public class TestRollingAverage {

    @Test
    public void testBasicAverage() { 
        RollingAverage rollingAverage = new RollingAverageImpl() ;
        rollingAverage.setWindow(10000); 
        rollingAverage.addValue(10);
        rollingAverage.addValue(20);
        rollingAverage.addValue(30);
        assertEquals(20, rollingAverage.latestAverage()); 
        rollingAverage.addValue(40) ; 
        assertEquals(25, rollingAverage.latestAverage()) ; 
    }

    @Test
    public void testRollingAverage() throws Exception{ 
        RollingAverage rollingAverage = new RollingAverageImpl() ;
        rollingAverage.setWindow(100); 
        rollingAverage.addValue(10);
        Thread.sleep(50) ; 
        rollingAverage.addValue(20);
        rollingAverage.addValue(30);
        assertEquals(20, rollingAverage.latestAverage()); 
        Thread.sleep(50) ; 
        rollingAverage.addValue(40) ; 
        assertEquals(30, rollingAverage.latestAverage()) ; 
    }


}



