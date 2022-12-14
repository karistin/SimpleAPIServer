package com.lucas.osapi.service;

import org.influxdb.impl.InfluxDBResultMapper;

import java.util.List;
import java.util.Optional;

/**
 * packageName    : com.lucas.osapi.service
 * fileName       : MetricService
 * author         : lucas
 * date           : 2022-11-16
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-11-16        lucas       최초 생성
 * 서비스 분리
 */
public interface MetricService<T1, T2, ID> {
    InfluxDBResultMapper resultMapper = new InfluxDBResultMapper();

    List<T1> findList();

    Optional<List<T1>> findTop();

    Optional<Double> findAverage();

    Optional<Double> findMax();

    Optional<Double> findMin();

    Optional<T2> findById(ID uid);
}
