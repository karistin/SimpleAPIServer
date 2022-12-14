package com.lucas.osapi.service;

import com.lucas.osapi.entity.MemInfo;
import com.lucas.osapi.entity.MemUsage;
import java.util.List;
import java.util.Optional;
import org.influxdb.impl.InfluxDBResultMapper;

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
public interface MemUsageService {
    InfluxDBResultMapper resultMapper = new InfluxDBResultMapper();

    List<MemInfo> List();
    List<MemUsage> ListUsage();

    MemInfo Id(String uid);

    List<MemInfo> IdRange(String key, Long time);
    List<MemUsage> IdRangeUsage(String key, Long time);


    Optional<Double> Average();

    Optional<Double> Max();

    Optional<Double> Min();

    List<MemUsage> Top();

}
