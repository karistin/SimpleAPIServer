package com.lucas.osapi.repo;

import com.lucas.osapi.config.InfluxDBConfiguration;
import com.lucas.osapi.entity.CpuInfo;
import com.lucas.osapi.entity.ProcessInfo;
import com.lucas.osapi.repo.influxDB.MemRepo;
import com.lucas.osapi.repo.influxDB.MemRepoImpl;
import com.lucas.osapi.repo.influxDB.ProcessRepo;
import com.lucas.osapi.repo.influxDB.ProcessRepoImpl;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {ProcessRepoImpl.class, InfluxDBConfiguration.class})
@Slf4j
public class ProcessRepoTetst {

    @Autowired
    private ProcessRepo processRepo;

    private final String  key = "serverA";
    private final long time = 30;

    @Test
    @DisplayName("findList")
    public void findList(){
        List<ProcessInfo> processInfoList  = processRepo.findList();
        assertNotNull(processInfoList);
        processInfoList.forEach(processInfo -> assertThat(processInfo).hasNoNullFieldsOrProperties());
    }

    @Test
    @DisplayName("findList")
    public void findCpuList(){
        List<ProcessInfo> processInfoList  = processRepo.findCpuList();
        assertNotNull(processInfoList);
        processInfoList.forEach(processInfo -> assertThat(processInfo.getCpuUsage()).isNotNull());
    }

    @Test
    @DisplayName("findList")
    public void findMemList(){
        List<ProcessInfo> processInfoList  = processRepo.findMemList();
        assertNotNull(processInfoList);
        processInfoList.forEach(processInfo -> assertThat(processInfo.getMemUsage()).isNotNull());
//        processInfoList.forEach(processInfo -> assertThat(processInfo).hasNoNullFieldsOrProperties());
    }

}
