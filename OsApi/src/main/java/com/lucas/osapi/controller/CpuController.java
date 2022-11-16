package com.lucas.osapi.controller;

import com.lucas.osapi.entity.CpuUsage;
import com.lucas.osapi.entity.Cpuinfo;
import com.lucas.osapi.model.response.ListResult;
import com.lucas.osapi.model.response.SingleResult;
import com.lucas.osapi.service.CpuUsageService;
import com.lucas.osapi.service.ResponseServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * packageName    : com.lucas.osapi.controller
 * fileName       : CpuController
 * author         : lucas
 * date           : 2022-11-09
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-11-09        lucas       최초 생성
 * agent가 5초 주기로 전송
 * 10s가 기준
 */

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/v1/cpuinfo")
@Api(tags = {"Cpu"})
public class CpuController {

    private final ResponseServiceImpl responseService;
    private final CpuUsageService cpuUsageService;

    @ApiOperation(value = "findAll", notes = "select cpuUsage from CpuInfo group by uid limit 5 tz('Asia/Seoul')")
    @GetMapping(value = "/all")
    public ListResult<CpuUsage> findAll() {
        return responseService.getListResult(cpuUsageService.findAll());
    }

    @ApiOperation(value = "findTop", notes = "select MEAN(cpuUsage) from CpuInfo group by uid limit 2 tz('Asia/Seoul')")
    @GetMapping(value = "/top")
    public ListResult<CpuUsage> findTop() {
        return responseService.getListResult(cpuUsageService.findTop());
    }

    @ApiOperation(value = "findAver", notes = "Not dev")
    @GetMapping(value = "/average")
    public SingleResult<Double> findAverage() throws Exception {
        return responseService.getSingleResult(cpuUsageService.findAverage().orElseThrow(Exception::new));
    }

    @ApiOperation(value = "findMax",notes = "Not dev")
    @GetMapping(value = "/max")
    public SingleResult<Double>  findMax() throws Exception {
        return responseService.getSingleResult(cpuUsageService.findMax().orElseThrow(Exception::new));
    }

    @ApiOperation(value = "findMin",notes = "Not dev")
    @GetMapping(value = "/min")
    public SingleResult<Double>  findMin() throws Exception {
        return responseService.getSingleResult(cpuUsageService.findMin().orElseThrow(Exception::new));
    }

    @ApiOperation(value = "findMin",notes = "Not dev")
    @GetMapping(value = "/{{id}}")
    public SingleResult<CpuUsage> findById(@ApiParam(value = "id",required = true) @RequestParam String id) {
        return responseService.getSingleResult(cpuUsageService.findById(id).orElseThrow());
    }


}
