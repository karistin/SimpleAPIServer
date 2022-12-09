package com.lucas.osapi.controller;

import com.lucas.osapi.model.response.SingleResult;
import com.lucas.osapi.service.ResponseService;
import com.lucas.osapi.service.ResponseServiceImpl;
import com.lucas.osapi.service.ServerListService;
import com.lucas.osapi.service.ServerListServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * packageName    : com.lucas.osapi.controller
 * fileName       : ServiceController
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
@RequestMapping(value = "/v1/server")
@Api(tags = {"Server"})
public class ServerController {
    private final ResponseService responseService;
    private final ServerListService serverInfoService;

    @ApiOperation(value = "serverCount", notes = "모든 호스트들의 count")
    @GetMapping(value = "/count")
    public SingleResult<Integer> getServerCount() {
        return responseService.getSingleResult(serverInfoService.getServerCount().orElseThrow());
    }

    @ApiOperation(value = "serverCoreCount", notes = "모든 호스트들의 core의 총합")
    @GetMapping(value = "/core")
    public SingleResult<Integer> getServerCore() {
        return responseService.getSingleResult(serverInfoService.getServerCore().orElseThrow());
    }

}
