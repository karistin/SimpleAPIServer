package com.lucas.osapi.repo;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lucas.osapi.config.InfluxDBConfiguration;
import com.lucas.osapi.entity.CpuInfo;
import com.lucas.osapi.entity.DiskInfo;
import com.lucas.osapi.entity.MemInfo;
import lombok.extern.slf4j.Slf4j;

import org.influxdb.dto.Point;

import org.influxdb.dto.Pong;
import org.influxdb.dto.Query;
import org.influxdb.dto.QueryResult;
import org.influxdb.impl.InfluxDBResultMapper;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.influxdb.InfluxDBTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

/**
 * packageName    : com.lucas.osapi.db
 * fileName       : DbConfigTest
 * author         : lucas
 * date           : 2022-11-21
 * description    :
 * db ping , db table cloum
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-11-21        lucas       최초 생성
 * dbCol Testing
 */


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {InfluxDBConfiguration.class})
@Slf4j
public class DbConfigTest {
    @Autowired
    private InfluxDBTemplate<Point> influxDBTemplate;

    @Value("${spring.influxdb.database}")
    private String dbName;

    @Value("${spring.influxdb.retention-policy}")
    private String retentionPolicy;

    private final InfluxDBResultMapper resultMapper = new InfluxDBResultMapper();


    @Test
    @DisplayName("Db properties")
    public void DbPropertiesTest(){
        assertEquals(influxDBTemplate.getDatabase(), dbName);
        assertEquals(influxDBTemplate.getRetentionPolicy(), retentionPolicy);
    }

    @Test
    @DisplayName("Db ping Test")
    public void DbPingTest(){
        Pong pong = influxDBTemplate.ping();
        assertTrue(pong.isGood());
    }


    // {"results":[{"series":[{"name":"cpu","columns":["time","value"],
//           "values":[["2015-06-06T14:55:27.195Z",90],["2015-06-06T14:56:24.556Z",90]]}]}]}
// {"results":[{"series":[{"name":"databases","columns":["name"],"values":[["mydb"]]}]}]}

    @Test
    @DisplayName("CpuTable")
    public void CpuTable(){
        QueryResult result = influxDBTemplate.getConnection().query(new Query("select * from CpuInfo limit 1", dbName));
        List<String> colums = result.getResults().get(0).getSeries().get(0).getColumns();
        List<String> cpuInfoCol = Arrays.asList("time", "cpuUsage", "uid", "hostname","idleUsage","irqUsage","niceUsage","softIrqUsage","stealUsage","sysUsage",
                "cpuLoad15min","cpuLoad1min","cpuLoad5min",
                "userUsage","waitIoUsage");

        assertEquals(colums.size(), cpuInfoCol.size());

        for (String colum : colums) {
            assertTrue(cpuInfoCol.contains(colum));
        }


        List<CpuInfo> cpuInfos = resultMapper.toPOJO(result, CpuInfo.class);
        assertNotNull(cpuInfos.get(0));
    }

    @Test
    @DisplayName("DiskTable")
    public void DiskTable(){
        QueryResult result = influxDBTemplate.getConnection().query(new Query("select * from DiskInfo limit 1", dbName));
        List<String> colums = result.getResults().get(0).getSeries().get(0).getColumns();
        List<String> diskInfoCol = Arrays.asList("time", "diskDeviceId","diskIOPS","uid", "diskFileSystem", "diskMountPoint","diskMountOptions"
            ,"diskBlockSize","hostname", "diskUsage","diskIOPSWrite","diskIOPSRead","diskBpsWrite","diskBpsRead",
                "diskUsedSpace","diskUsedSpaceByte","diskQueuelength", "diskInodeUsed", "diskFreeSpacePercentage", "diskFreeSpaceByte");


        for (String colum : colums) {
            assertTrue(diskInfoCol.contains(colum));
        }

        assertEquals(colums.size(), diskInfoCol.size());
        List<DiskInfo> diskInfos = resultMapper.toPOJO(result, DiskInfo.class);
        assertNotNull(diskInfos.get(0));

    }

    @Test
    @DisplayName("MemTable")
    public void MemTable(){
        QueryResult result = influxDBTemplate.getConnection().query(new Query("select * from MemInfo limit 1", dbName));
        List<String> colums = result.getResults().get(0).getSeries().get(0).getColumns();
        List<String> memInfoCol = Arrays.asList("time", "memUsage", "uid", "hostname","memUsageByteAll","memUsageByteFree","memUsageByteCached","memUsageByteBuffers",
                "memUsageByteUsed","memAvilable","memSReclaimable", "memSUnreclaim", "memSlab", "memSwapUsed", "memSwapUsedByte", "memPageFault");


        assertEquals(colums.size(), memInfoCol.size());

        for (String colum : colums) {
            assertTrue(memInfoCol.contains(colum));
        }

        List<MemInfo> memInfos = resultMapper.toPOJO(result, MemInfo.class);
        assertNotNull(memInfos.get(0));


    }
}

