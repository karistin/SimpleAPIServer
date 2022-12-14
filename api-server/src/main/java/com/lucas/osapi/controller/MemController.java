package com.lucas.osapi.controller;

import com.lucas.osapi.entity.CpuInfo;
import com.lucas.osapi.entity.CpuUsage;
import com.lucas.osapi.entity.MemInfo;
import com.lucas.osapi.entity.MemUsage;
import com.lucas.osapi.model.response.ListResult;
import com.lucas.osapi.model.response.SingleResult;
import com.lucas.osapi.service.MemUsageService;
import com.lucas.osapi.service.MemUsageServiceimpl;
import com.lucas.osapi.service.ResponseService;
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
 * fileName       : MemController
 * author         : lucas
 * date           : 2022-11-17
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-11-17        lucas       최초 생성
 */
@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/v1/meminfo")
@Api(tags = {"meminfo"})
public class MemController {
    private final ResponseService responseService;

    private final MemUsageService memUsageService;


    @ApiOperation(value = "List", notes = "최근 Mem 정보를 가져온다.")
    @GetMapping(value = "/list")
    public ListResult<MemInfo> List() {
        return responseService.getListResult(memUsageService.List());
    }


    @ApiOperation(value = "ListUsage", notes = "최근 Mem 사용량만 가져온다.")
    @GetMapping(value = "/list/usage")
    public ListResult<MemUsage> ListUsage() {
        return responseService.getListResult(memUsageService.ListUsage());
    }

    @ApiOperation(value = "Id",notes = "ID Mem 정보만 가져온다.")
    @GetMapping(value = "/list/{uid}")
    public SingleResult<MemInfo> Id(@ApiParam(value = "uid",required = true) @PathVariable String uid) {
        return responseService.getSingleResult(memUsageService.Id(uid));
    }

    @ApiOperation(value = "IdRange",notes = "특정 호스트의 Series Data Time : minute")
    @GetMapping(value = "/range")
    public ListResult<MemInfo>  IdRange(
        @ApiParam(value = "uid", required = true) @RequestParam(value = "uid") String uid,
        @ApiParam(value = "time", required = true)@RequestParam(value = "time") Long time) {
        return responseService.getListResult(memUsageService.IdRange(uid, time));
    }

    @ApiOperation(value = "IdRangeUsage",notes = "특정 호스트의 Usage Series Data Time : minute")
    @GetMapping(value = "/range/usage")
    public ListResult<MemUsage>  IdRangeUsage(
        @ApiParam(value = "uid", required = true) @RequestParam(value = "uid")String uid,
        @ApiParam(value = "time", required = true)@RequestParam(value = "time") Long time){
        return responseService.getListResult(memUsageService.IdRangeUsage(uid, time));
    }

    @ApiOperation(value = "Top", notes = "Usage TOP 기능")
    @GetMapping(value = "/list/usage/top")
    public ListResult<MemUsage> Top() {
        return responseService.getListResult(memUsageService.Top());
    }

    @ApiOperation(value = "Aver", notes = "모든 호스트들의 평균")
    @GetMapping(value = "/list/usage/average")
    public SingleResult<Double> Average() throws Exception {
        return responseService.getSingleResult(memUsageService.Average().orElseThrow(Exception::new));
    }

    @ApiOperation(value = "Max",notes = "모든 호스트들중 최대값")
    @GetMapping(value = "/list/usage/max")
    public SingleResult<Double>  Max() throws Exception {
        return responseService.getSingleResult(memUsageService.Max().orElseThrow(Exception::new));
    }

    @ApiOperation(value = "Min",notes = "모든 호스트들중 최소값")
    @GetMapping(value = "/list/usage/min")
    public SingleResult<Double>  Min() throws Exception {
        return responseService.getSingleResult(memUsageService.Min().orElseThrow(Exception::new));
    }

}
