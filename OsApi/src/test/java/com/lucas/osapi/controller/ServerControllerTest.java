package com.lucas.osapi.controller;

import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.*;

/**
 * packageName    : com.lucas.osapi.controller
 * fileName       : ServerControllerTest
 * author         : lucas
 * date           : 2022-11-17
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-11-17        lucas       최초 생성
 */
@AutoConfigureMockMvc
@NoArgsConstructor
@RunWith(SpringJUnit4ClassRunner.class)
@Slf4j
@SpringBootTest
class ServerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void getServerCount() throws Exception{
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/v1/server/count")
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
    void getServerCore() throws Exception{
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/v1/server/core")
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
}