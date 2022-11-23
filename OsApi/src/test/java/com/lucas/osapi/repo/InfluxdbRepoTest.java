package com.lucas.osapi.repo;

import com.lucas.osapi.entity.CpuInfo;
import com.lucas.osapi.entity.DiskInfo;
import com.lucas.osapi.entity.MemInfo;
import com.lucas.osapi.repo.influxDB.CpuRepo;
import com.lucas.osapi.repo.influxDB.DiskRepo;
import com.lucas.osapi.repo.influxDB.MemRepo;
import lombok.extern.slf4j.Slf4j;
import org.influxdb.InfluxDB;
import org.influxdb.InfluxDBFactory;
import org.influxdb.dto.Point;
import org.influxdb.dto.Pong;
import org.influxdb.dto.Query;
import org.influxdb.dto.QueryResult;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.influxdb.InfluxDBTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.when;

/**
 * packageName    : com.lucas.osapi.repo
 * fileName       : InfluxdbRepoTest
 * author         : lucas
 * date           : 2022-11-21
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-11-21        lucas       최초 생성
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@Slf4j
public class InfluxdbRepoTest {
    @Autowired
    private InfluxDBTemplate<Point> influxDBTemplate;

    @Mock
    private InfluxDB influxDB;
    @Autowired
    private CpuRepo cpuRepo;

    @Autowired
    private DiskRepo diskRepo;

    @Autowired
    private MemRepo memRepo;

    private String uid = "serverA";
//    @Autowired
//    private CpuRepo CpuRepo;

    @Before
    public void insertData(){

        String influxHost = "http://localhost:8086";
        String credentials = "admin:admin";
        String influxDatabaseName = "OsData";
//
//        influxDB = InfluxDBFactory.connect(influxHost, credentials.split(":")[0], credentials.split(":")[1]);
//
//        when(influxDB.ping()).thenReturn(new Pong());

//        Random rand = new Random();
//        rand.setSeed(System.currentTimeMillis());
//
//        CpuInfo cpuinfo = new CpuInfo();
//        cpuinfo.setUid(uid);
//        cpuinfo.setHostname(uid);
//        cpuinfo.setCpuUsage(Math.floor(rand.nextDouble()*10000)/100);
//
//        MemInfo memInfo = new MemInfo();
//        memInfo.setUid(uid);
//        memInfo.setHostname(uid);
//        memInfo.setMemUsage(Math.floor(rand.nextDouble()*10000)/100);
//
//
//        DiskInfo diskInfo = new DiskInfo();
//        diskInfo.setUid(uid);
//        diskInfo.setHostname(uid);
//        diskInfo.setDiskUsage(Math.floor(rand.nextDouble()*10000)/100);
//
//        influxDBTemplate.getConnection().setDatabase(influxDBTemplate.getDatabase()).write(Point.measurementByPOJO(CpuInfo.class).addFieldsFromPOJO(cpuinfo).time(System.currentTimeMillis(), TimeUnit.MILLISECONDS).build());
//        influxDBTemplate.getConnection().setDatabase(influxDBTemplate.getDatabase()).write(Point.measurementByPOJO(MemInfo.class).addFieldsFromPOJO(memInfo).time(System.currentTimeMillis(), TimeUnit.MILLISECONDS).build());
//        influxDBTemplate.getConnection().setDatabase(influxDBTemplate.getDatabase()).write(Point.measurementByPOJO(DiskInfo.class).addFieldsFromPOJO(diskInfo).time(System.currentTimeMillis(), TimeUnit.MILLISECONDS).build());
    }

    @Test
    @DisplayName("cpuRepo testing")
    public void cpuRepo(){
//        QueryResult result = influxDBTemplate.getConnection().query(new Query("select MEAN(cpuUsage) from CpuInfo group by uid limit 2", influxDBTemplate.getDatabase()));
//
////        asserEqual /r /r/n 구분 x
//        assertThat(cpuRepo.findList().toString()).isEqualToNormalizingNewlines(result.toString());
//
//        result = influxDBTemplate.getConnection().query(new Query("select * from CpuInfo where uid='"+uid+"' limit 1", influxDBTemplate.getDatabase()));
//        assertThat(cpuRepo.findById(uid).toString()).isEqualToNormalizingNewlines(result.toString());

    }

    @Test
    @DisplayName("memRepo testing")
    public void memRepo(){
//        QueryResult result = influxDBTemplate.getConnection().query(new Query("select MEAN(memUsage) from MemInfo group by uid limit 2", influxDBTemplate.getDatabase()));
//        assertEquals(memRepo.findById(uid), influxDBTemplate.getConnection().query(new Query("select MEAN(memUsage) from MemInfo group by uid limit 2", influxDBTemplate.getDatabase())));
    }

    @Test
    @DisplayName("diskRepo testing")
    public void diskRepo(){
//        assertEquals(diskRepo.findList(), influxDBTemplate.getConnection().query(new Query("select MEAN(diskUsage) from diskInfo group by uid limit 2", influxDBTemplate.getDatabase())));
//        assertEquals(diskRepo.findById(uid), influxDBTemplate.getConnection().query(new Query("select MEAN(diskUsage) from diskInfo group by uid limit 2", influxDBTemplate.getDatabase())));
    }


    @After
    public void dropData(){
// https://docs.influxdata.com/influxdb/v1.8/query_language/manage-database/#drop-series-from-the-index-with-drop-series
    }
}
