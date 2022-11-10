package com.lucas.osapi.config;

import lombok.extern.slf4j.Slf4j;
import org.influxdb.InfluxDB;
import org.influxdb.dto.Point;
import org.junit.jupiter.api.Test;
import org.springframework.data.influxdb.InfluxDBConnectionFactory;
import org.springframework.data.influxdb.InfluxDBProperties;
import org.springframework.data.influxdb.InfluxDBTemplate;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

/**
 * packageName    : com.lucas.osapi.config
 * fileName       : InfluxDBConfigureTest
 * author         : lucas
 * date           : 2022-11-10
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-11-10        lucas       최초 생성
 */
@Slf4j
public class InfluxDBConfigureTest {

    @Test
    void configurationTest(){
        InfluxDBProperties properties = new InfluxDBProperties();
        properties.setUrl("http://localhost:8086");
        properties.setUsername("admin");
        properties.setPassword("admin");
        properties.setDatabase("Cpuinfo");
        properties.setRetentionPolicy("autoget");
        InfluxDBConfiguration influxDBConfiguration = new InfluxDBConfiguration();
        InfluxDBConnectionFactory factory =  influxDBConfiguration.connectionFactory(properties);

        InfluxDB influxDB = factory.getConnection();
        InfluxDBTemplate<Point> influxDBTemplate = influxDBConfiguration.influxDBTemplate(factory);

        influxDBTemplate.
        assertEquals(influxDB.ping().getVersion(), influxDBTemplate.ping().getVersion());
    }
}
