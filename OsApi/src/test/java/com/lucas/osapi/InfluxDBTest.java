package com.lucas.osapi;

import org.influxdb.dto.Point;
import org.junit.Test;
import org.junit.platform.commons.logging.Logger;
import org.junit.platform.commons.logging.LoggerFactory;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.influxdb.InfluxDBTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.concurrent.TimeUnit;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class InfluxDBTest {

    private final static Logger logger = LoggerFactory.getLogger(InfluxDBTest.class);

    @Autowired
    private InfluxDBTemplate<Point> influxDBTemplate;

    @Test
    public void writeData() {

        for (int index = 1; index <= 10; index++) {

            Point point = Point.measurement("test_measurement")
                    .time(System.currentTimeMillis(), TimeUnit.MILLISECONDS)
                    .addField("field1", index)
                    .addField("field2", 100 + index)
                    .addField("tag2", "test" + index)
                    .addField("test_tag", "test" + 100 + index)
                    .build();

            influxDBTemplate.write(point);
        }
    }
}