package com.lucas.osapi.controller;

import com.lucas.osapi.entity.DiskInfo;
import com.lucas.osapi.entity.DiskUsage;
import com.lucas.osapi.model.response.ListResult;
import com.lucas.osapi.model.response.SingleResult;
import com.lucas.osapi.service.DiskUsageService;
import com.lucas.osapi.service.ResponseService;
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
 * fileName       : DiskController
 * author         : lucas
 * date           : 2022-11-17
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-11-17        lucas       최초 생성
 * 엔티티 => repo
 * 서비스 => model
 */
@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/v1/diskinfo")
@Api(tags = {"DiskInfo"})
public class DiskController {
    private final ResponseService responseService;

    private final DiskUsageService diskUsageService;

    @ApiOperation(value = "List", notes = "최근 disk 정보를 가져온다.")
    @GetMapping(value = "/list")
    public ListResult<DiskInfo> List() {
        return responseService.getListResult(diskUsageService.List());
    }


    @ApiOperation(value = "UsageList", notes = "최근 diskUsage 정보를 가져온다.")
    @GetMapping(value = "/list/usage")
    public ListResult<DiskUsage> ListUsage() {
        return responseService.getListResult(diskUsageService.ListUsage());
    }

    @ApiOperation(value = "Id",notes = "Id disk 정보를 가져온다.")
    @GetMapping(value = "/list/{uid}")
    public SingleResult<DiskInfo> Id(
        @ApiParam(value = "uid",required = true) @PathVariable String uid)
        throws Exception {
        return responseService.getSingleResult(diskUsageService.Id(uid));
    }

    @ApiOperation(value = "Id", notes = "특정 호스트의 Series Data Time : minute")
    @GetMapping(value = "/range")
    public ListResult<DiskInfo> IdRange(
        @ApiParam(value = "uid", required = true) @RequestParam(value = "uid") String uid,
        @ApiParam(value = "time", required = true)@RequestParam(value = "time") Long time) {
        return responseService.getListResult(diskUsageService.IdRange(uid, time));
    }

    @ApiOperation(value = "Idrange", notes = "특정 호스트의 Series Data Time : minute")
    @GetMapping(value = "/range/usage")
    public ListResult<DiskUsage> IdRangeUsage(
        @ApiParam(value = "uid", required = true) @RequestParam(value = "uid") String uid,
        @ApiParam(value = "time", required = true)@RequestParam(value = "time") Long time) {
        return responseService.getListResult(diskUsageService.IdRangeUsage(uid, time));
    }

    @ApiOperation(value = "Top", notes = "Usage TOP diskUsage/ diskInodeUsed/ diskIOPS")
    @GetMapping(value = "/list/{type}/top")
    public ListResult<DiskUsage> Top(
        @ApiParam(value = "type", required = true) @PathVariable String type) {
        return responseService.getListResult(diskUsageService.Top(type));
    }

    @ApiOperation(value = "Aver", notes = "Disk 사용량 평균")
    @GetMapping(value = "/list/usage/average")
    public SingleResult<Double> Average() throws Exception {
        return responseService.getSingleResult(diskUsageService.Average().orElseThrow(Exception::new));
    }

    @ApiOperation(value = "findMax",notes = "Disk 사용량 최대값")
    @GetMapping(value = "/list/usage/max")
    public SingleResult<Double>  Max() throws Exception {
        return responseService.getSingleResult(diskUsageService.Max().orElseThrow(Exception::new));
    }

    @ApiOperation(value = "findMin",notes = "Disk 사용량 최소값")
    @GetMapping(value = "/list/usage/min")
    public SingleResult<Double>  Min() throws Exception {
        return responseService.getSingleResult(diskUsageService.Min().orElseThrow(Exception::new));
    }



}
