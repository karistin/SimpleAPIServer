package com.lucas.osapi.repo.influxDB;

import com.lucas.osapi.entity.DiskInfo;
import com.lucas.osapi.entity.DiskUsage;

import java.util.List;

/**
 * packageName    : com.lucas.osapi.repo.influxDB
 * fileName       : DiskRepo
 * author         : lucas
 * date           : 2022-11-21
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-11-21        lucas       최초 생성
 */
public interface DiskRepo extends InfluxDBRepo<DiskInfo>{

    List<DiskUsage> findListUsage();
    List<DiskUsage> findByIdRangeUsage(String key, Long time);

}
