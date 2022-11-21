package com.lucas.osapi.service;

import com.lucas.osapi.entity.DiskInfo;
import com.lucas.osapi.entity.DiskUsage;

/**
 * packageName    : com.lucas.osapi.service
 * fileName       : DiskUsageService
 * author         : lucas
 * date           : 2022-11-21
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-11-21        lucas       최초 생성
 */
public interface DiskUsageService extends MetricService<DiskUsage, DiskInfo, String> {
}
