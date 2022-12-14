package com.lucas.osapi.controller;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.lucas.osapi.config.InfluxDBConfiguration;
import com.lucas.osapi.repo.influxDB.CpuRepoImpl;
import lombok.NoArgsConstructor;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;


/**
 * packageName    : com.lucas.osapi.controller fileName       : CpuControllerTest author         :
 * lucas date           : 2022-12-01 description    :
 * =========================================================== DATE              AUTHOR NOTE
 * ----------------------------------------------------------- 2022-12-01        lucas       최초 생성
 */
@NoArgsConstructor
@RunWith(SpringJUnit4ClassRunner.class)
@AutoConfigureMockMvc
//@SpringBootTest(classes = {CpuRepoImpl.class, InfluxDBConfiguration.class})
@SpringBootTest
public class DiskControllerTest {

    @Autowired
    private MockMvc mockMvc;

    private final String apiVersion = "v1";
    private final String apiUrl = "diskinfo";
    @Test
    @DisplayName("/v1/diskinfo/list")
    public void list() throws Exception {
        mockMvc.perform(get("/"+apiVersion+"/"+apiUrl+"/list").contentType("application/json"))
            .andExpect(status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$.success").value(true))
            .andExpect(MockMvcResultMatchers.jsonPath("$.code").value(0))
            .andExpect(MockMvcResultMatchers.jsonPath("$.msg").exists())
            .andExpect(MockMvcResultMatchers.jsonPath("$.list").exists())
            .andExpect(MockMvcResultMatchers.jsonPath("$.list[*]").exists())
            .andReturn().getResponse();
    }



    @Test
    @DisplayName("/v1/diskinfo/list/usage")
    public void listUsage() throws Exception {
        mockMvc.perform(get("/"+apiVersion+"/"+apiUrl+"/list/usage").contentType("application/json"))
            .andExpect(status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$.success").value(true))
            .andExpect(MockMvcResultMatchers.jsonPath("$.code").value(0))
            .andExpect(MockMvcResultMatchers.jsonPath("$.msg").exists())
            .andExpect(MockMvcResultMatchers.jsonPath("$.list").exists())
            .andExpect(MockMvcResultMatchers.jsonPath("$.list[*].diskUsage").exists())
            .andReturn().getResponse();
    }


    @Test
    @DisplayName("/v1/diskinfo/list/{uid}")
    public void id() throws Exception {
        mockMvc.perform(get("/"+apiVersion+"/"+apiUrl+"/list/{uid}","foo")
                .contentType("application/json"))
            .andExpect(status().is5xxServerError())
            .andExpect(MockMvcResultMatchers.jsonPath("$.success").value(false))
            .andExpect(MockMvcResultMatchers.jsonPath("$.code").value(-1))
            .andReturn();

        mockMvc.perform(get("/"+apiVersion+"/"+apiUrl+"/list/{uid}","serverA")
                .contentType("application/json"))
            .andExpect(status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$.success").value(true))
            .andExpect(MockMvcResultMatchers.jsonPath("$.code").value(0))
            .andReturn();
    }

    @Test
    @DisplayName("/v1/diskinfo/range")
    public void idRange() throws Exception {
        mockMvc.perform(get("/"+apiVersion+"/"+apiUrl+"/range?uid=serverA&time=30").contentType("application/json"))
            .andExpect(status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$.success").value(true))
            .andExpect(MockMvcResultMatchers.jsonPath("$.code").value(0))
            .andExpect(MockMvcResultMatchers.jsonPath("$.msg").exists())
            .andExpect(MockMvcResultMatchers.jsonPath("$.list").exists())
            .andExpect(MockMvcResultMatchers.jsonPath("$.list[*]").exists())
            .andReturn().getResponse();
    }

    @Test
    @DisplayName("/v1/diskinfo/range/usage")
    public void idRangeUsage() throws Exception {
        mockMvc.perform(get("/"+apiVersion+"/"+apiUrl+"/range/usage?uid=serverA&time=30").contentType("application/json"))
            .andExpect(status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$.success").value(true))
            .andExpect(MockMvcResultMatchers.jsonPath("$.code").value(0))
            .andExpect(MockMvcResultMatchers.jsonPath("$.msg").exists())
            .andExpect(MockMvcResultMatchers.jsonPath("$.list").exists())
            .andExpect(MockMvcResultMatchers.jsonPath("$.list[*]").exists())
            .andReturn().getResponse();
    }


    @Test
    @DisplayName("/v1/diskinfo/list/(diskUsage, diskInodeUsed, diskIOPS)/top")
    public void top() throws Exception {
        mockMvc.perform(get("/"+apiVersion+"/"+apiUrl+"/list/diskUsage/top").contentType("application/json"))
            .andExpect(status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$.success").value(true))
            .andExpect(MockMvcResultMatchers.jsonPath("$.code").value(0))
            .andExpect(MockMvcResultMatchers.jsonPath("$.msg").exists())
            .andExpect(MockMvcResultMatchers.jsonPath("$.list.size()").value(5))
            .andExpect(MockMvcResultMatchers.jsonPath("$.list[*].diskUsage").exists())
            .andExpect(MockMvcResultMatchers.jsonPath("$.list[*].uid").exists())
            .andReturn();

        mockMvc.perform(get("/"+apiVersion+"/"+apiUrl+"/list/diskInodeUsed/top").contentType("application/json"))
            .andExpect(status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$.success").value(true))
            .andExpect(MockMvcResultMatchers.jsonPath("$.code").value(0))
            .andExpect(MockMvcResultMatchers.jsonPath("$.msg").exists())
            .andExpect(MockMvcResultMatchers.jsonPath("$.list.size()").value(5))
            .andExpect(MockMvcResultMatchers.jsonPath("$.list[*].diskInodeUsed").exists())
            .andExpect(MockMvcResultMatchers.jsonPath("$.list[*].uid").exists())
            .andReturn();

        mockMvc.perform(get("/"+apiVersion+"/"+apiUrl+"/list/diskInodeUsed/top").contentType("application/json"))
            .andExpect(status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$.success").value(true))
            .andExpect(MockMvcResultMatchers.jsonPath("$.code").value(0))
            .andExpect(MockMvcResultMatchers.jsonPath("$.msg").exists())
            .andExpect(MockMvcResultMatchers.jsonPath("$.list.size()").value(5))
            .andExpect(MockMvcResultMatchers.jsonPath("$.list[*].diskIOPS").exists())
            .andExpect(MockMvcResultMatchers.jsonPath("$.list[*].uid").exists())
            .andReturn();
    }


    @Test
    @DisplayName("/v1/diskinfo/list/usage/average")
    public void average() throws Exception {
        mockMvc.perform(get("/"+apiVersion+"/"+apiUrl+"/list/usage/average").contentType("application/json"))
            .andExpect(status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$.success").value(true))
            .andExpect(MockMvcResultMatchers.jsonPath("$.code").value(0))
            .andExpect(MockMvcResultMatchers.jsonPath("$.msg").exists())
            .andExpect(MockMvcResultMatchers.jsonPath("$.data").exists())
            .andExpect(MockMvcResultMatchers.jsonPath("$.data").isNumber())
            .andReturn().getResponse();
    }


}
