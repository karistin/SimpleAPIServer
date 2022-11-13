package com.lucas.osapi.influxdb;

import lombok.extern.slf4j.Slf4j;
import org.influxdb.dto.Point;
import org.influxdb.dto.Pong;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.influxdb.InfluxDBTemplate;

import static org.junit.Assert.assertTrue;

@Slf4j
@SpringBootTest
public class influxDBQueryTest {

    @Autowired
    private InfluxDBTemplate<Point> influxDBTemplate;

    @Test
    public void connetingTest(){
        assertTrue(pingTest());
    }

    private boolean pingTest(){
        try {
            Pong response = influxDBTemplate.ping();
            if (response.getVersion().equalsIgnoreCase("unknown")) {
                log.error("Error ping");
                return false;
            } else {
                log.info("Databases : {} ", response.getVersion());
                return true;
            }
        } catch (Exception e) {
            log.error("Exception while pinging database : ",e);
            return false;
        }
    }
}
