package com.lucas.osapi.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.lucas.osapi.entity.CpuUsage;
import com.lucas.osapi.model.response.ListResult;
import com.lucas.osapi.service.CpuUsageService;
import com.lucas.osapi.service.ResponseService;
import java.util.Arrays;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * packageName    : com.lucas.osapi.controller fileName       : CpuControllerTest author         :
 * lucas date           : 2022-12-01 description    :
 * =========================================================== DATE              AUTHOR NOTE
 * ----------------------------------------------------------- 2022-12-01        lucas       최초 생성
 */
@WebMvcTest(CpuController.class)
public class CpuControllerTest {

    @Autowired
    private MockMvc mockMvc;


    private CpuUsageService cpuUsageService;

    private ResponseService responseService;

    @DisplayName("CPUList")
    @Test
    public void list() throws Exception {
//        given
        cpuUsageService = mock(CpuUsageService.class);
        responseService = mock(ResponseService.class);
        when(cpuUsageService.findList()).then(Arrays.asList(
            new CpuUsage[]{new CpuUsage(null, 20, 15, 5, "serverA")}));
        given(responseService.getListResult(
            any()
        )).willReturn((ListResult<Object>) cpuUsageService.findList());

        mockMvc.perform(MockMvcRequestBuilders.get("/v1/cpuinfo/list").contentType("application/json"))
            .andExpect(status().isOk())
            .andReturn();
//            .andExpect(jsonPath("$.success").value(true))
//            .andExpect(jsonPath("$.code").value(0))
//            .andExpect(jsonPath("$.msg").exists())
//            .andExpect(jsonPath("$.list").exists())
//            .andReturn().getResponse();





    }

}
