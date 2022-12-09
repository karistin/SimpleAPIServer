package com.lucas.osapi.dev.service;

import com.lucas.osapi.entity.CpuUsage;
import com.lucas.osapi.entity.DiskUsage;
import com.lucas.osapi.repo.influxDB.CpuRepo;
import com.lucas.osapi.repo.influxDB.CpuRepoImpl;
import com.lucas.osapi.service.CpuUsageService;
import com.lucas.osapi.service.CpuUsageServiceimpl;
import java.util.ArrayList;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.influxdb.dto.Point;
import org.influxdb.dto.Pong;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.influxdb.InfluxDBTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

/**
 * packageName    : com.lucas.osapi.service
 * fileName       : CpuUsageServiceTest
 * author         : lucas
 * date           : 2022-11-18
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-11-18        lucas       최초 생성
 */
@RunWith(MockitoJUnitRunner.class)
@Slf4j
class CpuUsageServiceTest {
    private final CpuRepo cpuRepo = mock(CpuRepoImpl.class);

    private final CpuUsageService cpuUsageService = new CpuUsageServiceimpl(cpuRepo);

    private final List<CpuUsage> cpuUsageList = new ArrayList<>();

    @Before
    public void mocking() {

    }



}