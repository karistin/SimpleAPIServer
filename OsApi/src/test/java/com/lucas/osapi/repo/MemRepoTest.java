package com.lucas.osapi.repo;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;

import com.lucas.osapi.config.InfluxDBConfiguration;
import com.lucas.osapi.entity.CpuInfo;
import com.lucas.osapi.entity.CpuUsage;
import com.lucas.osapi.entity.MemInfo;
import com.lucas.osapi.entity.MemUsage;
import com.lucas.osapi.repo.influxDB.CpuRepo;
import com.lucas.osapi.repo.influxDB.DiskRepoImpl;
import com.lucas.osapi.repo.influxDB.MemRepo;
import com.lucas.osapi.repo.influxDB.MemRepoImpl;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

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
@SpringBootTest(classes = {MemRepoImpl.class, InfluxDBConfiguration.class})
@Slf4j
public class MemRepoTest {

    @Autowired
    private MemRepo memRepo;

    private final String  key = "serverA";
    private final long time = 30;



    @Test
    @DisplayName("findList")
    public void findList(){
        List<MemInfo> cpuInfoList  = memRepo.findList();
        assertNotNull(cpuInfoList);
        cpuInfoList.forEach(cpuInfo -> assertThat(cpuInfo).hasNoNullFieldsOrProperties());

    }

    @Test
    @DisplayName("findById")
    public void findById(){
        MemInfo cpuInfo = memRepo.findById(key);
        assertNotNull(cpuInfo);
        assertThat(cpuInfo).hasNoNullFieldsOrProperties();

    }


    @Test
    @DisplayName("findByIdRange")
    public void findByIdRange(){
        List<MemInfo> cpuInfos = memRepo.findByIdRange(key, time);
        cpuInfos.forEach(cpuInfo -> assertThat(cpuInfo).hasNoNullFieldsOrProperties());
    }


    @Test
    @DisplayName("findByIdRangeUsage")
    public void findByIdRangeUsage(){
        List<MemUsage> cpuInfos = memRepo.findbyIdRangeUsage(key, time);
        cpuInfos.forEach(cpuInfo -> assertThat(cpuInfo).hasNoNullFieldsOrProperties());
    }



}
