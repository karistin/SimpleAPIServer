package com.lucas.osapi.controller;

import com.lucas.osapi.entity.CpuInfo;
import com.lucas.osapi.entity.CpuUsage;
import com.lucas.osapi.model.response.ListResult;
import com.lucas.osapi.model.response.SingleResult;
import com.lucas.osapi.service.CpuUsageService;
import com.lucas.osapi.service.CpuUsageServiceimpl;
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
import org.springframework.web.servlet.ModelAndView;

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

    private final ResponseService responseService;
    private final CpuUsageService cpuUsageService;

    @ApiOperation(value = "List", notes = "최근 CPU 정보를 가져온다.")
    @GetMapping(value = "/list")
    public ListResult<CpuInfo> List() {
        return responseService.getListResult(cpuUsageService.List());
    }

    @ApiOperation(value = "ListUsage", notes = "최근 CPU 사용량만 가져온다.")
    @GetMapping(value = "/list/usage")
    public ListResult<CpuUsage> ListUsage() {
        return responseService.getListResult(cpuUsageService.ListUsage());
    }

    @ApiOperation(value = "Id",notes = "ID CPU 정보만 가져온다.")
    @GetMapping(value = "/list/{uid}")
    public SingleResult<CpuInfo> Id(@ApiParam(value = "hostName",required = true) @PathVariable String uid){
        return responseService.getSingleResult(cpuUsageService.Id(uid));
    }

    @ApiOperation(value = "IdRange",notes = "특정 호스트의 Series Data Time : minute")
    @GetMapping(value = "/range")
    public ListResult<CpuInfo>  IdRange(
        @ApiParam(value = "uid", required = true) @RequestParam(value = "uid") String uid,
        @ApiParam(value = "time", required = true)@RequestParam(value = "time") Long time) {
        return responseService.getListResult(cpuUsageService.IdRange(uid, time));
    }

    @ApiOperation(value = "IdRangeUsage",notes = "특정 호스트의 Usage Series Data Time : minute")
    @GetMapping(value = "/range/usage")
    public ListResult<CpuUsage>  IdRangeUsage(
        @ApiParam(value = "uid", required = true) @RequestParam(value = "uid")String uid,
        @ApiParam(value = "time", required = true)@RequestParam(value = "time") Long time){
        return responseService.getListResult(cpuUsageService.IdRangeUsage(uid, time));
    }

    @ApiOperation(value = "Top", notes = "Usage TOP 기능")
    @GetMapping(value = "/list/usage/top")
    public ListResult<CpuUsage> Top(){
        return responseService.getListResult(cpuUsageService.Top());
    }

    @ApiOperation(value = "Aver", notes = "모든 호스트들의 평균")
    @GetMapping(value = "/list/usage/average")
    public SingleResult<Double> Average() throws Exception {
        return responseService.getSingleResult(cpuUsageService.Average().orElseThrow(Exception::new));
    }

    @ApiOperation(value = "Max",notes = "모든 호스트들중 최대값")
    @GetMapping(value = "/list/usage/max")
    public SingleResult<Double>  Max() throws Exception {
        return responseService.getSingleResult(cpuUsageService.Max().orElseThrow(Exception::new));
    }

    @ApiOperation(value = "Min",notes = "모든 호스트들중 최소값")
    @GetMapping(value = "/list/usage/min")
    public SingleResult<Double>  Min() throws Exception {
        return responseService.getSingleResult(cpuUsageService.Min().orElseThrow(Exception::new));
    }

}
