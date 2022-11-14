package com.lucas.osapi.influxdb;


import com.lucas.osapi.entity.Cpuinfo;
import com.lucas.osapi.entity.MemoryInfo;
import com.lucas.osapi.entity.NetworkInfo;
import lombok.extern.slf4j.Slf4j;
import org.influxdb.InfluxDB;
import org.influxdb.dto.Point;
import org.influxdb.dto.Query;
import org.influxdb.dto.QueryResult;
import org.influxdb.impl.InfluxDBResultMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.influxdb.InfluxDBTemplate;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import static java.lang.Thread.sleep;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

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
@SpringBootApplication
public class Application implements CommandLineRunner {

    List<String> hostnameList = new ArrayList<>(
            Arrays.asList("serverA","serverB","serverC", "serverD", "serverE", "serverF", "serverI")
    );
    @Autowired
    private InfluxDBTemplate<Point> influxDBTemplate;

    @Override
    public void run(String... args) throws Exception {
        influxDBTemplate.createDatabase();
//        assertEquals(influxDBTemplate.getDatabase(), "OsData");
//        insertData();
//        InfluxDB influxDB = influxDBTemplate.getConnection();
        outputData();
    }



    private void outputData() throws InterruptedException {
        while(true) {
            Query query = new Query("select cpuUsage from CpuInfo group by uid limit 1 tz('Asia/Seoul')", influxDBTemplate.getDatabase());
            QueryResult queryResult = influxDBTemplate.getConnection().query(query);
//            log.info(queryResult.toString());
//        List<QueryResult.Result> results = queryResult.getResults();
            InfluxDBResultMapper resultMapper = new InfluxDBResultMapper();
            List<Cpuinfo> cpuinfoList = resultMapper.toPOJO(queryResult, Cpuinfo.class );
            for (Cpuinfo cpuinfo : cpuinfoList) {
                log.info(cpuinfo.getUid() + " : "+cpuinfo.getCpuUsage());
            }
            log.info("========================================================");
            sleep(3000);
        }
    }
    private void insertData() throws InterruptedException {

//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("YYYY-MM-DD HH:mm:ss.SSS");
//        int testDataCount = 20;
        Random rand = new Random();
        long seed = System.currentTimeMillis();
        rand.setSeed(seed);


        while(true) {

            List<Cpuinfo> cpuinfoList = new ArrayList<>();

            for (String hostname : hostnameList) {
                Cpuinfo cpuinfo = new Cpuinfo();
                cpuinfo.setUid(hostname);
                cpuinfo.setHostname(hostname);
                cpuinfo.setCpuUsage(Math.floor(rand.nextDouble()*10000)/100);
                influxDBTemplate.write(Point.measurementByPOJO(Cpuinfo.class).addFieldsFromPOJO(cpuinfo).time(System.currentTimeMillis(),TimeUnit.MILLISECONDS).build());
            }

//            influxDBTemplate.write(Point.measurementByPOJO(MemoryInfo.class).addFieldsFromPOJO(memoryInfo)
//                                        .build());
            sleep(3000);
        }

    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
