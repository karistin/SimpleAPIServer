package com.lucas.osapi.repo.influxDB;

import com.lucas.osapi.repo.TimeRepository;
import org.influxdb.dto.QueryResult;
import org.springframework.stereotype.Repository;

/**
 * packageName    : com.lucas.osapi.repo.influxDB
 * fileName       : InfluxDBRepo
 * author         : lucas
 * date           : 2022-11-21
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-11-21        lucas       최초 생성
 */

public interface InfluxDBRepo extends TimeRepository<QueryResult, String> {

    QueryResult query(String query);
}
