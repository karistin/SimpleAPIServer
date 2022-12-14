package com.lucas.osapi.controller;

import com.lucas.osapi.entity.ServerInfo;
import com.lucas.osapi.model.response.ListResult;
import com.lucas.osapi.model.response.SingleResult;
import com.lucas.osapi.service.ResponseService;
import com.lucas.osapi.service.ServerService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
    private final ServerService serverInfoService;

    @ApiOperation(value = "getList", notes = "모든 호스트들의 count")
    @GetMapping(value = "/list")
    public ListResult<ServerInfo> getList() {
        return responseService.getListResult(serverInfoService.getList());
    }

    @ApiOperation(value = "getId", notes = "모든 호스트들의 count")
    @GetMapping(value = "/{id}")
    public SingleResult<ServerInfo> getId(
        @ApiParam(value = "id", required = true) @PathVariable String id) {
        return responseService.getSingleResult(serverInfoService.getServerInfoById(id));
    }


    @ApiOperation(value = "getTypeCount", notes = "모든 호스트들의 count")
    @GetMapping(value = "/list/type")
    public SingleResult<Map<String, Integer>> getTypeCount() {
        return responseService.getSingleResult(serverInfoService.getServerType());
    }

    @ApiOperation(value = "getCore", notes = "모든 호스트들의 core의 총합")
    @GetMapping(value = "/list/core")
    public SingleResult<Integer> getCore() {
        return responseService.getSingleResult(serverInfoService.getServerCore().orElseThrow());
    }

    @ApiOperation(value = "getCount", notes = "모든 호스트들의 core의 총합")
    @GetMapping(value = "/list/count")
    public SingleResult<Entry<String, Integer>> getCount() {
        return responseService.getSingleResult(serverInfoService.count());
    }
}
