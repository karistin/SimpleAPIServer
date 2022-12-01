package com.lucas.osapi.repo.influxDB;

import com.lucas.osapi.entity.CpuInfo;
import com.lucas.osapi.entity.CpuUsage;
import org.influxdb.dto.QueryResult;

import java.util.List;

/**
 * packageName    : com.lucas.osapi.repo.influxDB
 * fileName       : CpuRepo
 * author         : lucas
 * date           : 2022-11-21
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-11-21        lucas       최초 생성
 */
public interface CpuRepo extends InfluxDBRepo<CpuInfo> {
    List<CpuUsage> findbyIdRangeUsage(String key, String time);
    List<CpuUsage> findListUsage();
}
