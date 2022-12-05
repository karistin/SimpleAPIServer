package com.lucas.osapi.repo.influxDB;

import com.lucas.osapi.entity.CpuUsage;
import com.lucas.osapi.entity.MemInfo;
import com.lucas.osapi.entity.MemUsage;
import org.influxdb.dto.QueryResult;

import java.util.List;

/**
 * packageName    : com.lucas.osapi.repo.influxDB
 * fileName       : MemRepo
 * author         : lucas
 * date           : 2022-11-21
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-11-21        lucas       최초 생성
 * TODO : method naming
 */
public interface MemRepo extends InfluxDBRepo<MemInfo> {
    List<MemUsage> findListUsage();

    List<MemInfo> findByIdRange(String key, long time);
}
