package com.lucas.osapi.service;

import com.lucas.osapi.entity.CpuUsage;
import com.lucas.osapi.entity.DiskInfo;
import com.lucas.osapi.entity.DiskUsage;
import com.lucas.osapi.model.response.SingleResult;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.influxdb.impl.InfluxDBResultMapper;

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
public interface DiskUsageService {

    InfluxDBResultMapper resultMapper = new InfluxDBResultMapper();

    List<DiskInfo> List();

    List<DiskUsage> ListUsage();

    DiskInfo Id(String id);
    List<DiskInfo> IdRange(String key, Long time);

    List<DiskUsage> IdRangeUsage(String key, Long time);
    Optional<Double> Average();

    Optional<Double> Max();

    Optional<Double> Min();

    List<DiskUsage> Top(String colType);
}
