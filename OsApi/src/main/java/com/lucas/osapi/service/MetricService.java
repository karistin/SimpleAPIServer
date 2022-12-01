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

    /*
     * 모든 CPU 데이터 표현
     * */
    List<T1> findList();

    /*
     * 그 시간에 있는 CPU의 모든 데이터
     * get All data Query about T
     * select * from T(Measurement) group by {{tag}} limit 5;의 각각의 평균
     *
     * */



    Optional<List<T1>> findTop();


    /*
     * 평균에 대한 데이터 가져오기
     * */
    Optional<Double> findAverage();

    Optional<Double> findMax();

    Optional<Double> findMin();


    /*
     * 특정 ID에 대한 평균 사용량
     *
     *  get monent data about T
     *  CPU => Usage
     *  Mem => Usage
     *  Disk => I/O(%)
     * */

    Optional<T2> findById(ID uid);
}
