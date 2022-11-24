package com.lucas.osapi.controller;

import com.lucas.osapi.entity.DiskInfo;
import com.lucas.osapi.entity.DiskUsage;
import com.lucas.osapi.model.response.ListResult;
import com.lucas.osapi.model.response.SingleResult;
import com.lucas.osapi.service.DiskUsageService;
import com.lucas.osapi.service.DiskUsageServiceimpl;
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
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

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

    @ApiOperation(value = "findList", notes = "모든 호스트들의 Disk의 사용량을 가져온다.")
    @GetMapping(value = "/list")
    public ListResult<DiskUsage> findList() {
        return responseService.getListResult(diskUsageService.findList());
    }


    @ApiOperation(value = "findUsageList", notes = "모든 호스트들의 정보를 가져온다.")
    @GetMapping(value = "/list/usage")
    public ListResult<DiskUsage> findUsageList() {
        return responseService.getListResult(diskUsageService.findList());
    }



    @ApiOperation(value = "findTop", notes = "모든 호스트들중 Disk사용량 상위 5개를 가져온다.")
    @GetMapping(value = "/top")
    public ListResult<DiskUsage> findTop() throws Exception {
        return responseService.getListResult(diskUsageService.findTop().orElseThrow(Exception::new));
    }

    @ApiOperation(value = "findAver", notes = "모든 호스트들의 Disk 사용량 평균")
    @GetMapping(value = "/average")
    public SingleResult<Double> findAverage() throws Exception {
        return responseService.getSingleResult(diskUsageService.findAverage().orElseThrow(Exception::new));
    }

    @ApiOperation(value = "findMax",notes = "모든 호스트들중 최대값")
    @GetMapping(value = "/max")
    public SingleResult<Double>  findMax() throws Exception {
        return responseService.getSingleResult(diskUsageService.findMax().orElseThrow(Exception::new));
    }

    @ApiOperation(value = "findMin",notes = "모든 호스트들중 최소값")
    @GetMapping(value = "/min")
    public SingleResult<Double>  findMin() throws Exception {
        return responseService.getSingleResult(diskUsageService.findMin().orElseThrow(Exception::new));
    }

    @ApiOperation(value = "findById",notes = "특정 호스트 사용량")
    @GetMapping(value = "/{uid}/usage")
    public SingleResult<DiskUsage> findByIdUsage(@ApiParam(value = "uid",required = true) @PathVariable String uid) throws Exception {
        return responseService.getSingleResult(diskUsageService.findByIdUsage(uid).orElseThrow(Exception::new));
    }

    @ApiOperation(value = "findById", notes = "특정 호스트의 IOPS Usage")
    @GetMapping(value = "/{uid}/iops")
    public SingleResult<Map<String, String>> findByIdIops(@ApiParam(value = "uid", required = true)@PathVariable String uid) throws Exception{
        return responseService.getSingleResult(diskUsageService.findByIdIops(uid).orElseThrow(Exception::new));
    }

    @ApiOperation(value = "findById",notes = "특정 호스트 모든 데이터")
    @GetMapping(value = "/{uid}")
    public SingleResult<DiskInfo> findById(@ApiParam(value = "uid",required = true) @PathVariable String uid) throws Exception {
        return responseService.getSingleResult(diskUsageService.findById(uid).orElseThrow(Exception::new));
    }
}
