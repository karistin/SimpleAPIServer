package com.lucas.osapi.service;

import lombok.NoArgsConstructor;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

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
    private CpuUsageService cpuUsageService;

    @Mock
    private CpuUsageService cpuUsageServiceMock;


    @Test
    void findTop() {
        cpuUsageService.findTop();
        /*
        * 컬럼 수 만 비교
        * 2개인지
        *
        * */
    }

    @Test
    void findTopMock() {
//        when(cpuUsageServiceMock.findTop()).;
        /*
        * when get that
        * */
    }
}