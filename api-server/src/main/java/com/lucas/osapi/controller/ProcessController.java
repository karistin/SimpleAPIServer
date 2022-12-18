package com.lucas.osapi.controller;

import com.lucas.osapi.entity.MemInfo;
import com.lucas.osapi.entity.ProcessInfo;
import com.lucas.osapi.model.response.ListResult;
import com.lucas.osapi.repo.influxDB.ProcessRepo;
import com.lucas.osapi.service.MemUsageService;
import com.lucas.osapi.service.ProcessService;
import com.lucas.osapi.service.ResponseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/v1/process")
@Api(tags = {"Process"})
public class ProcessController {

    private final ResponseService responseService;

    private final ProcessRepo processRepo;

    @ApiOperation(value = "List", notes = "최근 10개의 모든 프로세스 정보를 cpu사용량으로 정렬해서 가져온다.")
    @GetMapping(value = "/list")
    public ListResult<ProcessInfo> List() {
        return responseService.getListResult(processRepo.findList());
    }

    @ApiOperation(value = "List", notes = "최근 5개의 cpuUsage process를 가져온다.")
    @GetMapping(value = "/list/cpu")
    public ListResult<ProcessInfo> ListCpu() {
        return responseService.getListResult(processRepo.findCpuList());
    }

    @ApiOperation(value = "List", notes = "최근 5개의 memUsage process를 가져온다.")
    @GetMapping(value = "/list/mem")
    public ListResult<ProcessInfo> ListMem() {
        return responseService.getListResult(processRepo.findMemList());
    }
}
