package com.lucas.osapi.controller;

import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * packageName    : com.lucas.osapi.controller
 * fileName       : MetricControllerTest
 * author         : lucas
 * date           : 2022-11-17
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-11-17        lucas       최초 생성
 *  TODO : https://stackoverflow.com/questions/35380387/unit-test-for-programs-that-uses-influxdb
 *  influxdb dependcy how to ?
 *  쪼개서 테스트 하자
 */
@NoArgsConstructor
@RunWith(SpringJUnit4ClassRunner.class)
@AutoConfigureMockMvc
@SpringBootTest
@Slf4j
class MetricControllerTest {

    /*
    * TODO : Using Constructor
    *  https://minkukjo.github.io/framework/2020/06/28/JUnit-23/
    *
    *  TODO : API Value Test
    *   how to ?
    * */
    @Autowired
    private MockMvc mockMvc;

    private List<String> apiUrlList = new ArrayList<>(
            Arrays.asList("cpuinfo","meminfo","diskinfo","meminfo")
    );

    private String apiVersion = "v1";
    /*
    *
    {
        "code": 0,
        "list": [
        {
            "mean": 0,
            "uid": "string"
        }
        ],
        "msg": "Success",
        "success": true,
        "time": 1668994223183
    }
    * */


    @Test
    @DisplayName("Controller : /v1/-/list")
    void findListApi() throws Exception {
        for(String apiUrl: apiUrlList)
        {
            mockMvc.perform(get("/"+apiVersion+"/"+apiUrl+"/list").contentType("application/json"))
                   .andExpect(status().isOk())
                   .andExpect(jsonPath("$.success").value(true))
                   .andExpect(jsonPath("$.code").value(0))
                   .andExpect(jsonPath("$.msg").exists())
                   .andExpect(jsonPath("$.list").exists())
                   .andReturn().getResponse();
        }

    }

    @Test
    @DisplayName("Controller : /v1/-/top")
    void findTop() throws Exception {
        for(String apiUrl: apiUrlList)
        {
            mockMvc.perform(get("/"+apiVersion+"/"+apiUrl+"/top").contentType("application/json"))
                   .andExpect(status().isOk())
                   .andExpect(jsonPath("$.success").value(true))
                   .andExpect(jsonPath("$.code").value(0))
                   .andExpect(jsonPath("$.msg").exists())
                   .andExpect(jsonPath("$.list.size()").value(5))
                   .andExpect(jsonPath("$.list[*].mean").exists())
                   .andExpect(jsonPath("$.list[*].uid").exists())
                   .andReturn();
        }

    }

    @Test
    @DisplayName("find by Id")
    void findByIdUsage() throws Exception {
        for(String apiUrl: apiUrlList)
        {
//            longId
            mockMvc.perform(get("/"+apiVersion+"/"+apiUrl+"/{uid}","foo")
                           .contentType("application/json"))
                    .andExpect(status().is5xxServerError())
                    .andExpect(jsonPath("$.success").value(false))
                    .andExpect(jsonPath("$.code").value(-1))
                   .andReturn();
//              Demo data
            mockMvc.perform(get("/"+apiVersion+"/"+apiUrl+"/{uid}","serverA")
                           .contentType("application/json"))
                   .andExpect(status().isOk())
                   .andExpect(jsonPath("$.success").value(true))
                   .andExpect(jsonPath("$.code").value(0))
                   .andReturn();
        }
    }

}