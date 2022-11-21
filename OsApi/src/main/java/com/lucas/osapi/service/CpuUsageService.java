package com.lucas.osapi.service;

import com.lucas.osapi.entity.CpuInfo;
import com.lucas.osapi.entity.CpuUsage;

/**
 * packageName    : com.lucas.osapi.service
 * fileName       : CpuUsageService
 * author         : lucas
 * date           : 2022-11-21
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-11-21        lucas       최초 생성
 */
public interface CpuUsageService extends MetricService<CpuUsage, CpuInfo, String> {
}
