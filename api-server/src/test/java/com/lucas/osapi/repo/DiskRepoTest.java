package com.lucas.osapi.repo;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;

import com.lucas.osapi.config.InfluxDBConfiguration;
import com.lucas.osapi.entity.DiskInfo;
import com.lucas.osapi.entity.DiskUsage;
import com.lucas.osapi.entity.DiskInfo;
import com.lucas.osapi.repo.influxDB.CpuRepo;
import com.lucas.osapi.repo.influxDB.DiskRepo;
import com.lucas.osapi.repo.influxDB.DiskRepoImpl;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * packageName    : com.lucas.osapi.repo fileName       : DiskRepoTest author         : lucas date
 *         : 2022-12-08 description    : ===========================================================
 * DATE              AUTHOR             NOTE
 * ----------------------------------------------------------- 2022-12-08        lucas       최초 생성
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {DiskRepoImpl.class, InfluxDBConfiguration.class})
@Slf4j
public class DiskRepoTest {
    @Autowired
    private DiskRepo diskRepo;

    private final String  key = "serverA";
    private final long time = 30;



    @Test
    @DisplayName("findList")
    public void findList(){
        List<DiskInfo> diskInfoList  = diskRepo.findList();
        assertNotNull(diskInfoList);
        diskInfoList.forEach(diskInfo -> assertThat(diskInfo).hasNoNullFieldsOrProperties());
    }

    @Test
    @DisplayName("findListUsage")
    public void findListUsage() {
        List<DiskUsage> diskUsage = diskRepo.findListUsage();
        assertNotNull(diskUsage);
        for (DiskUsage usage : diskUsage) {
            assertThat(usage).hasNoNullFieldsOrProperties();
        }
    }

    @Test
    @DisplayName("findById")
    public void findById(){
        DiskInfo diskInfo = diskRepo.findById(key);
        assertNotNull(diskInfo);
        assertThat(diskInfo).hasNoNullFieldsOrProperties();

    }


    @Test
    @DisplayName("findByIdRange")
    public void findByIdRange(){
        List<DiskInfo> diskInfos = diskRepo.findByIdRange(key, time);
        diskInfos.forEach(diskInfo -> assertThat(diskInfo).hasNoNullFieldsOrProperties());
    }


    @Test
    @DisplayName("findByIdRangeUsage")
    public void findByIdRangeUsage(){
        List<DiskUsage> diskInfos = diskRepo.findByIdRangeUsage(key, time);
        diskInfos.forEach(diskInfo -> assertThat(diskInfo).hasNoNullFieldsOrProperties());
    }



}