package com.lucas.osapi.repo.influxDB;

import com.lucas.osapi.repo.TimeRepository;
import org.influxdb.dto.QueryResult;
import org.influxdb.impl.InfluxDBResultMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

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
 *  T => Data Entitiy
 */

public interface InfluxDBRepo<T> extends TimeRepository<T> {

    InfluxDBResultMapper resultMapper = new InfluxDBResultMapper();

    QueryResult query(String query);

}
