package com.lucas.osapi.controller;

import com.lucas.osapi.entity.CpuInfo;
import com.lucas.osapi.entity.CpuUsage;
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
@Api(tags = {"CpuInfo"})
public class CpuController {

    private final ResponseServiceImpl responseService;
    private final CpuUsageService cpuUsageService;

    @ApiOperation(value = "findList", notes = "모든 호스트들의 CPU의 사용량을 가져온다.")
    @GetMapping(value = "/list")
    public ListResult<CpuUsage> findList() {
        return responseService.getListResult(cpuUsageService.findList());
    }

    @ApiOperation(value = "findTop", notes = "모든 호스트들중 Cpu사용량 상위 5개를 가져온다.")
    @GetMapping(value = "/top")
    public ListResult<CpuUsage> findTop() throws Exception {
        return responseService.getListResult(cpuUsageService.findTop().orElseThrow(Exception::new));
    }

    @ApiOperation(value = "findAver", notes = "모든 호스트들의 평균")
    @GetMapping(value = "/average")
    public SingleResult<Double> findAverage() throws Exception {
        return responseService.getSingleResult(cpuUsageService.findAverage().orElseThrow(Exception::new));
    }

    @ApiOperation(value = "findMax",notes = "모든 호스트들중 최대값")
    @GetMapping(value = "/max")
    public SingleResult<Double>  findMax() throws Exception {
        return responseService.getSingleResult(cpuUsageService.findMax().orElseThrow(Exception::new));
    }

    @ApiOperation(value = "findMin",notes = "모든 호스트들중 최소값")
    @GetMapping(value = "/min")
    public SingleResult<Double>  findMin() throws Exception {
        return responseService.getSingleResult(cpuUsageService.findMin().orElseThrow(Exception::new));
    }

    @ApiOperation(value = "findById",notes = "특정 호스트 사용량")
    @GetMapping(value = "/{uid}/usage")
    public SingleResult<CpuUsage> findByIdUsage(@ApiParam(value = "uid",required = true) @PathVariable String uid) throws Exception {
        return responseService.getSingleResult(cpuUsageService.findByIdUsage(uid).orElseThrow(Exception::new));
    }

    @ApiOperation(value = "findById",notes = "특정 호스트 모든 데이터")
    @GetMapping(value = "/{uid}")
    public SingleResult<CpuInfo> findById(@ApiParam(value = "uid",required = true) @PathVariable String uid) throws Exception {
        return responseService.getSingleResult(cpuUsageService.findById(uid).orElseThrow(Exception::new));
    }
}
