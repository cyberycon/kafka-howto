package com.example.cyberycon.consumption.calculations;

public interface RollingAverage {

    void setWindow(int milliseconds) ; 

    void addValue(int value) ; 

    double latestAverage() ;
    
}
