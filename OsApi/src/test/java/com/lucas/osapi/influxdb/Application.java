package com.lucas.osapi.influxdb;


import com.lucas.osapi.entity.Cpuinfo;
import com.lucas.osapi.entity.MemoryInfo;
import com.lucas.osapi.entity.NetworkInfo;
import lombok.extern.slf4j.Slf4j;
import org.influxdb.dto.Point;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.influxdb.InfluxDBTemplate;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import static java.lang.Thread.sleep;

/**
 * packageName    : com.lucas.osapi.influxdb
 * fileName       : Application
 * author         : lucas
 * date           : 2022-11-11
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-11-11        lucas       최초 생성
 */


@Slf4j
//@SpringBootApplication
public class Application implements CommandLineRunner {

    @Autowired
    private InfluxDBTemplate<Point> influxDBTemplate;

    @Override
    public void run(String... args) throws Exception {

        influxDBTemplate.createDatabase();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("YYYY-MM-DD HH:mm:ss.SSS");
//        int testDataCount = 20;
        Random rand = new Random();
        long seed = System.currentTimeMillis();
        rand.setSeed(seed);


        List<Cpuinfo> cpuinfoList = new ArrayList<>();
        while(true){
            Cpuinfo cpuinfo = Cpuinfo.builder()
                                     .uid("serverA")
                                      .hostname("serverA")
                                      .cpuUsage(rand.nextFloat()*100)
                                      .userUsage(rand.nextFloat()*100)
                                      .sysUsage(rand.nextFloat()*100)
                                      .niceUsage(rand.nextFloat()*100)
                                      .idleUsage(rand.nextFloat()*100)
                                      .waitIoUsage(rand.nextFloat()*100)
                                      .stealUage(rand.nextFloat()*100)
                                      .irqUsage(rand.nextFloat()*100)
                                      .softIrqUsage(rand.nextFloat()*100)
                                      .build();
            MemoryInfo memoryInfo = MemoryInfo.builder()
                                              .uid("serverA").hostname("serverA")
                                              .memoryTotal(rand.nextLong())
                                              .memoryUsed(rand.nextLong())
                                              .memoryFree(rand.nextLong())
                                              .build();

            NetworkInfo networkInfo;
            log.info(cpuinfo.toString());
            log.info(memoryInfo.toString());
            Point.measurement("test").time(System.currentTimeMillis(),TimeUnit.MILLISECONDS);
            influxDBTemplate.write(Point.measurementByPOJO(cpuinfo.getClass()).addFieldsFromPOJO(cpuinfo)
                    .build());
            influxDBTemplate.write(Point.measurementByPOJO(memoryInfo.getClass()).addFieldsFromPOJO(memoryInfo)
                                        .build());
            sleep(5000);

        }


    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
