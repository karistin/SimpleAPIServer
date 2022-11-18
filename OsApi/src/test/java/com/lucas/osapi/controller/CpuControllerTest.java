package com.lucas.osapi.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lucas.osapi.advice.ExceptionAdvice;
import com.lucas.osapi.entity.CpuInfo;
import com.lucas.osapi.entity.CpuUsage;
import com.lucas.osapi.model.response.ListResult;
import com.lucas.osapi.service.CpuUsageService;
import com.lucas.osapi.service.ResponseServiceImpl;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.influxdb.dto.Point;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.shadow.com.univocity.parsers.annotations.Validate;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.influxdb.InfluxDBTemplate;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
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
@NoArgsConstructor
@RunWith(SpringJUnit4ClassRunner.class)
@AutoConfigureMockMvc
@SpringBootTest
@Slf4j
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

    @Autowired
    private ObjectMapper objectMapper;




    @Autowired
    private CpuController cpuController;


    @Test
    @DisplayName("findList api test")
    void findListApi() throws Exception {

        MockHttpServletResponse response = mockMvc.perform(get("/v1/cpuinfo/list")
                                                          .contentType("application/json"))
                                                  .andExpect(status().isOk()).andReturn().getResponse();
//        assertThat(response.getContentAsString()).
//        );


    }

    @Test
    @DisplayName("findList type check")
    void findListType() throws Exception {
//        ListResult<CpuUsage> listResult = cpuController.findList();
//        MockHttpServletResponse response = mockMvc.perform(get("/v1/cpuinfo/list")
//                                                          .contentType("application/json"))
//                                                  .andExpect(status().isOk()).andReturn().getResponse();
//        assertThat(response.getContentAsString()).ig(
//                objectMapper.writeValueAsString(listResult).
//        );
    }

    @Test
    @DisplayName("find by Id")
    void findByIdUsage() throws Exception {

        MockHttpServletResponse response = mockMvc.perform(get("/v1/cpuinfo/{uid}","foo")
                                                          .contentType("application/json"))
                                                  .andExpect(status().is5xxServerError()).andReturn().getResponse();
    }

}