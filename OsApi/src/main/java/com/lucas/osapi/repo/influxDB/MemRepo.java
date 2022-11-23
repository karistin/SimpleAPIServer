package com.lucas.osapi.repo.influxDB;

import org.influxdb.dto.QueryResult;

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
 */
public interface MemRepo extends InfluxDBRepo {
    QueryResult findbyIdIops(String key);
    QueryResult findbyIdInode(String key);
    QueryResult findbyIdUsage(String key);
}
