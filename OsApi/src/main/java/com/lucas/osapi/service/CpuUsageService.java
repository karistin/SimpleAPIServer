package com.lucas.osapi.service;

import com.lucas.osapi.entity.CpuInfo;
import com.lucas.osapi.entity.CpuUsage;
import java.util.List;
import java.util.Optional;
import org.influxdb.impl.InfluxDBResultMapper;

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
public interface CpuUsageService{
    InfluxDBResultMapper resultMapper = new InfluxDBResultMapper();

    List<CpuInfo> List();

    List<CpuUsage> ListUsage();

    CpuInfo Id(String uid);

    List<CpuInfo> IdRange(String key, Long time);

    List<CpuUsage> IdRangeUsage(String key, Long time);

    Optional<Double> Average();

    Optional<Double> Max();

    Optional<Double> Min();

    List<CpuUsage> Top();

}
