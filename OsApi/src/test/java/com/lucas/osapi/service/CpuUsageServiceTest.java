package com.lucas.osapi.service;

import com.lucas.osapi.entity.CpuUsage;
import lombok.NoArgsConstructor;
import org.influxdb.dto.Point;
import org.influxdb.dto.Pong;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.influxdb.InfluxDBTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

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
@NoArgsConstructor
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
class CpuUsageServiceTest {

    @Autowired
    private InfluxDBTemplate<Point> influxDBTemplate;

    @Autowired
    private CpuUsageServiceimpl cpuUsageService;

    @Mock
    private CpuUsageServiceimpl cpuUsageServiceMock;


    @Test
    void pingDB(){
        Pong pong = influxDBTemplate.ping();
        assertTrue(pong.isGood());
    }
    @Test
    void findTop() {
        Optional<List<CpuUsage>> cpuUsageList = cpuUsageService.findTop();
        assertTrue(cpuUsageList.isPresent());
        assertEquals(cpuUsageList.get().size(), 5);
        cpuUsageList.get().forEach(cpuUsage -> assertNotNull(cpuUsage.getMean()));

    }


}