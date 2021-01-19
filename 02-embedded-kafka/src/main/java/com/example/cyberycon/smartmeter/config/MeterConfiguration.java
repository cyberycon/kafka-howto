package com.example.cyberycon.smartmeter.config;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * This Configuration class is primarily metadata to enable IDEs like
 * IntelliJ to show application specific properties.
 */
@Configuration
@ConfigurationProperties(prefix = "meter")
public class MeterConfiguration {
    private int interval;
    private String area ;
    private String topic;
    private String id ;


    public int getInterval() {
        return interval;
    }

    public void setInterval(int interval) {
        this.interval = interval;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
