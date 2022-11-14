package com.lucas.osapi.controller;

import com.lucas.osapi.entity.Cpuinfo;
import com.lucas.osapi.model.response.ListResult;
import com.lucas.osapi.model.response.SingleResult;
import com.lucas.osapi.service.CpuServiceImpl;
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
 */

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/v1/cpuinfo")
@Api(tags = {"Cpu"})
public class CpuController {

    private final ResponseServiceImpl responseService;
    private final CpuServiceImpl cpuService;

    @ApiOperation(value = "cpu 데이터 조회",notes = "특정 호스트의 모든 CPU 데이터 확인")
    @GetMapping(value = "/cpuinfo/{uid}")
    public SingleResult<Cpuinfo> getCpuAllByUid(@ApiParam(value = "UserId",required = true) @PathVariable long uid) {
        return responseService.getSingleResult(cpuService.getCpuAllByUid(uid));
    }

    @ApiOperation(value = "cpu Usage 조회",notes = "특정 호스트의 cpu usage 확인")
    @GetMapping(value = "/cpuinfo/{uid}/usage")
    public SingleResult<Cpuinfo> getCpuUsageByuid(@ApiParam(value = "UserId",required = true) @PathVariable long uid) {
        return responseService.getSingleResult(cpuService.getCpuUsageByuid(uid));
    }

    @ApiOperation(value = "상위 5개 CPU Usage 조회", notes = "최상단 5개의 CPU Usage 반환")
    @GetMapping(value = "/cpuinfo/top")
    public ListResult<Cpuinfo> findTopCpuUsage() {
        return responseService.getListResult(cpuService.findTopCpuUsage());
    }

    @ApiOperation(value = "모든 CPU Usage 조회", notes = "모든 CPU Usage 반환")
    @GetMapping(value = "/cpuinfo/All")
    public ListResult<Cpuinfo> findAllCpuUsage() {
        return cpuService.findAllCpuUsage();
    }


}
