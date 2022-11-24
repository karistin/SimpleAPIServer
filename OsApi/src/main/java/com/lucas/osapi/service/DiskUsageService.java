package com.lucas.osapi.service;

import com.lucas.osapi.entity.DiskInfo;
import com.lucas.osapi.entity.DiskUsage;
import com.lucas.osapi.model.response.SingleResult;

import java.util.Map;
import java.util.Optional;

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
    Optional<Map<String, String>> findByIdIops(String uid);
}
