package com.lucas.osapi.controller;

import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.influxdb.dto.Point;
import org.junit.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.influxdb.InfluxDBTemplate;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 * packageName    : com.lucas.osapi.controller
 * fileName       : CpuControllerTest
 * author         : lucas
 * date           : 2022-11-17
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-11-17        lucas       최초 생성
 *  TODO : https://stackoverflow.com/questions/35380387/unit-test-for-programs-that-uses-influxdb
 *  influxdb dependcy how to ?
 */

@AutoConfigureMockMvc
@NoArgsConstructor
@RunWith(SpringJUnit4ClassRunner.class)
@Slf4j
@SpringBootTest
class CpuControllerTest {

    /*
    * TODO : Using Constructor
    *  https://minkukjo.github.io/framework/2020/06/28/JUnit-23/
    *
    *  TODO : API Value Test
    *   how to ?
    * */
    @Autowired
    private MockMvc mockMvc;


    @Test
    void findList() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/v1/cpuinfo/list")
                .contentType(MediaType.ALL)
                .accept(MediaType.APPLICATION_JSON)
                .characterEncoding(StandardCharsets.UTF_8.displayName());
        MockHttpServletResponse response = mockMvc.perform(requestBuilder)
               .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                .andReturn()
                .getResponse();
        log.info(response.getContentAsString());
    }

    @Test
    void findTop() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/v1/cpuinfo/top")
                                                              .contentType(MediaType.ALL)
                                                              .accept(MediaType.APPLICATION_JSON)
                                                              .characterEncoding(StandardCharsets.UTF_8.displayName());
        MockHttpServletResponse response = mockMvc.perform(requestBuilder)
                                                  .andExpect(MockMvcResultMatchers.status().isOk())
                                                  .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                                                  .andReturn()
                                                  .getResponse();
        log.info(response.getContentAsString());
    }

    @Test
    void findAverage() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/v1/cpuinfo/average")
                                                              .contentType(MediaType.ALL)
                                                              .accept(MediaType.APPLICATION_JSON)
                                                              .characterEncoding(StandardCharsets.UTF_8.displayName());
        MockHttpServletResponse response = mockMvc.perform(requestBuilder)
                                                  .andExpect(MockMvcResultMatchers.status().isOk())
                                                  .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                                                  .andReturn()
                                                  .getResponse();
        log.info(response.getContentAsString());
    }

    @Test
    void findMax() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/v1/cpuinfo/max")
                                                              .contentType(MediaType.ALL)
                                                              .accept(MediaType.APPLICATION_JSON)
                                                              .characterEncoding(StandardCharsets.UTF_8.displayName());
        MockHttpServletResponse response = mockMvc.perform(requestBuilder)
                                                  .andExpect(MockMvcResultMatchers.status().isOk())
                                                  .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                                                  .andReturn()
                                                  .getResponse();
        log.info(response.getContentAsString());
    }

    @Test
    void findMin() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/v1/cpuinfo/min")
                                                              .contentType(MediaType.ALL)
                                                              .accept(MediaType.APPLICATION_JSON)
                                                              .characterEncoding(StandardCharsets.UTF_8.displayName());
        MockHttpServletResponse response = mockMvc.perform(requestBuilder)
                                                  .andExpect(MockMvcResultMatchers.status().isOk())
                                                  .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                                                  .andReturn()
                                                  .getResponse();
        log.info(response.getContentAsString());
    }

    @Test
    void findByIdUsage() throws Exception{
        RequestBuilder failRequest = MockMvcRequestBuilders.get("/v1/cpuinfo/foo/usage")
                                                           .contentType(MediaType.ALL)
                                                           .accept(MediaType.APPLICATION_JSON)
                                                           .characterEncoding(StandardCharsets.UTF_8.displayName());
        MockHttpServletResponse response = mockMvc.perform(failRequest)
                                                  .andExpect(MockMvcResultMatchers.status().is5xxServerError())
                                                  .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                                                  .andReturn()
                                                  .getResponse();
        RequestBuilder successRequset = MockMvcRequestBuilders.get("/v1/cpuinfo/serverA")
                                                              .contentType(MediaType.ALL)
                                                              .accept(MediaType.APPLICATION_JSON)
                                                              .characterEncoding(StandardCharsets.UTF_8.displayName());
        response = mockMvc.perform(successRequset)
                          .andExpect(MockMvcResultMatchers.status().isOk())
                          .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                          .andReturn()
                          .getResponse();
    }
    @Test
    void findById() throws Exception {
        RequestBuilder failRequest = MockMvcRequestBuilders.get("/v1/cpuinfo/foo")
                                                              .contentType(MediaType.ALL)
                                                              .accept(MediaType.APPLICATION_JSON)
                                                              .characterEncoding(StandardCharsets.UTF_8.displayName());
        MockHttpServletResponse response = mockMvc.perform(failRequest)
                                                  .andExpect(MockMvcResultMatchers.status().is5xxServerError())
                                                  .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                                                  .andReturn()
                                                  .getResponse();
        RequestBuilder successRequset = MockMvcRequestBuilders.get("/v1/cpuinfo/serverA")
                                                              .contentType(MediaType.ALL)
                                                              .accept(MediaType.APPLICATION_JSON)
                                                              .characterEncoding(StandardCharsets.UTF_8.displayName());
        response = mockMvc.perform(successRequset)
                          .andExpect(MockMvcResultMatchers.status().isOk())
                          .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                          .andReturn()
                          .getResponse();
    }


}