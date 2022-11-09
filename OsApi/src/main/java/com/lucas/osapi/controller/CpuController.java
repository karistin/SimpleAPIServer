package com.lucas.osapi.controller;

import com.lucas.osapi.entity.Cpuinfo;
import com.lucas.osapi.model.response.ListResult;
import com.lucas.osapi.service.CpuServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
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
@RequestMapping(value = "/v1")
public class CpuController {

    private final CpuServiceImpl cpuService;

    @GetMapping(value = "/cpuinfo/sysuage")
    public ListResult<Cpuinfo> findAllSysUsage() {
        return cpuService.findAllSysUsage();
    }

}
