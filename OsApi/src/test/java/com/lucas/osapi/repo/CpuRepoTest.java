package com.lucas.osapi.repo;

import com.lucas.osapi.entity.CpuInfo;
import com.lucas.osapi.repo.influxDB.CpuRepo;
import com.lucas.osapi.repo.influxDB.DiskRepo;
import com.lucas.osapi.repo.influxDB.MemRepo;
import lombok.extern.slf4j.Slf4j;
import org.influxdb.InfluxDB;
import org.influxdb.dto.Point;
import org.influxdb.dto.QueryResult;
import org.influxdb.impl.InfluxDBResultMapper;
import org.junit.After;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.influxdb.InfluxDBTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

/**
 * packageName    : com.lucas.osapi.repo
 * fileName       : CpuRepoTest
 * author         : lucas
 * date           : 2022-11-21
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-11-21        lucas       최초 생성
 */

// {"results":[{"series":[{"name":"cpu","columns":["time","value"],
//           "values":[["2015-06-06T14:55:27.195Z",90],["2015-06-06T14:56:24.556Z",90]]}]}]}
// {"results":[{"series":[{"name":"databases","columns":["name"],"values":[["mydb"]]}]}]}
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@Slf4j
public class CpuRepoTest {
    @Autowired
    private CpuRepo cpuRepo;

    private String key;
    private String time = "30";

    private InfluxDBResultMapper resultMapper = new InfluxDBResultMapper();
    @Test
    @DisplayName("findList")
    public void findList(){
        QueryResult result = cpuRepo.findList();
        List<CpuInfo> cpuInfos = resultMapper.toPOJO(result, CpuInfo.class);

        assertNotNull(cpuInfos);

    }

    @Test
    @DisplayName("findById")
    public void findById(){
        QueryResult result = cpuRepo.findById(key);
        List<CpuInfo> cpuInfos = resultMapper.toPOJO(result, CpuInfo.class);

        assertNotNull(cpuInfos);
    }


    @Test
    @DisplayName("findByIdRange")
    public void findByIdRange(){
        QueryResult result = cpuRepo.findByIdRange(key, time);
        List<CpuInfo> cpuInfos = resultMapper.toPOJO(result, CpuInfo.class);

        assertNotNull(cpuInfos);
    }

}
