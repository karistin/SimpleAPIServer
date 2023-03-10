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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
    void getList() throws Exception{
        mockMvc.perform(get("/v1/server/list").contentType("application/json"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.success").value(true))
            .andExpect(jsonPath("$.code").value(0))
            .andExpect(jsonPath("$.msg").exists())
            .andExpect(jsonPath("$.list[*].uid").exists())
            .andReturn();
    }

    @Test
    void getId() throws Exception{
        mockMvc.perform(get("/v1/server/{id}", "serverA").contentType("application/json"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.success").value(true))
            .andExpect(jsonPath("$.code").value(0))
            .andExpect(jsonPath("$.msg").exists())
            .andExpect(jsonPath("$.data").exists())
            .andReturn();
    }


    @Test
    void getServerCount() throws Exception{
        mockMvc.perform(get("/v1/server/list/type").contentType("application/json"))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$.success").value(true))
               .andExpect(jsonPath("$.code").value(0))
               .andExpect(jsonPath("$.msg").exists())
                .andExpect(jsonPath("$.data").exists())
               .andReturn();
    }

    @Test
    void getServerCore() throws Exception{
        mockMvc.perform(get("/v1/server/list/core").contentType("application/json"))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$.success").value(true))
               .andExpect(jsonPath("$.code").value(0))
               .andExpect(jsonPath("$.msg").exists())
               .andExpect(jsonPath("$.data").exists())
               .andReturn();
    }

    @Test
    void getCount() throws Exception{
        mockMvc.perform(get("/v1/server/list/count").contentType("application/json"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.success").value(true))
            .andExpect(jsonPath("$.code").value(0))
            .andExpect(jsonPath("$.msg").exists())
            .andExpect(jsonPath("$.data").exists())
            .andReturn();
    }
}