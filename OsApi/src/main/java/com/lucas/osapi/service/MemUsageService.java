package com.lucas.osapi.service;

import com.lucas.osapi.entity.MemInfo;
import com.lucas.osapi.entity.MemUsage;

/**
 * packageName    : com.lucas.osapi.service
 * fileName       : MemUsageService
 * author         : lucas
 * date           : 2022-11-21
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-11-21        lucas       최초 생성
 */
public interface MemUsageService extends MetricService<MemUsage, MemInfo, String> {
}
