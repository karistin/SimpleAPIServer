package com.lucas.osapi.controller;

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

    @ApiOperation(value = "findList", notes = "모든 호스트들의 Mem의 사용량을 가져온다.")
    @GetMapping(value = "/list")
    public ListResult<MemUsage> findList() {
        return responseService.getListResult(memUsageService.findList());
    }

    @ApiOperation(value = "findTop", notes = "모든 호스트들중 Mem사용량 상위 5개를 가져온다.")
    @GetMapping(value = "/top")
    public ListResult<MemUsage> findTop() throws Exception {
        return responseService.getListResult(memUsageService.findTop().orElseThrow(Exception::new));
    }

    @ApiOperation(value = "findAver", notes = "모든 호스트들의 평균")
    @GetMapping(value = "/average")
    public SingleResult<Double> findAverage() throws Exception {
        return responseService.getSingleResult(memUsageService.findAverage().orElseThrow(Exception::new));
    }

    @ApiOperation(value = "findMax",notes = "모든 호스트들중 최대값")
    @GetMapping(value = "/max")
    public SingleResult<Double>  findMax() throws Exception {
        return responseService.getSingleResult(memUsageService.findMax().orElseThrow(Exception::new));
    }

    @ApiOperation(value = "findMin",notes = "모든 호스트들중 최소값")
    @GetMapping(value = "/min")
    public SingleResult<Double>  findMin() throws Exception {
        return responseService.getSingleResult(memUsageService.findMin().orElseThrow(Exception::new));
    }

//    @ApiOperation(value = "findById",notes = "특정 호스트 사용량")
//    @GetMapping(value = "/{uid}/usage")
//    public SingleResult<MemUsage> findByIdUsage(@ApiParam(value = "uid",required = true) @PathVariable String uid) throws Exception {
//        return responseService.getSingleResult(memUsageService.findByIdUsage(uid).orElseThrow(Exception::new));
//    }

    @ApiOperation(value = "findById",notes = "특정 호스트 모든 데이터")
    @GetMapping(value = "/{uid}")
    public SingleResult<MemInfo> findById(@ApiParam(value = "uid",required = true) @PathVariable String uid) throws Exception {
        return responseService.getSingleResult(memUsageService.findById(uid).orElseThrow(Exception::new));
    }
}
