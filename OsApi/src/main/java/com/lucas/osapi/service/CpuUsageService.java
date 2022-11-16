package com.lucas.osapi.service;


import com.lucas.osapi.entity.CpuUsage;
import com.lucas.osapi.entity.Cpuinfo;
import lombok.extern.slf4j.Slf4j;
import org.influxdb.dto.Point;
import org.influxdb.dto.Query;
import org.influxdb.dto.QueryResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.influxdb.InfluxDBTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.influxdb.querybuilder.BuiltQuery.QueryBuilder.select;

/**
 * packageName    : com.lucas.osapi.service
 * fileName       : CpuService
 * author         : lucas
 * date           : 2022-11-09
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-11-09        lucas       최초 생성
 */

@Slf4j
@Service
public class CpuUsageService implements MetricService<CpuUsage, String> {

    @Autowired
    private InfluxDBTemplate<Point> influxDBTemplate;

    /*
     * Time now()
     * uid가 가지고 있는 Cpu data 출력
     *  */


    /*
    * 최신 CPU 사용량 TOP 5 가져오기
    * TODO : queryBuilder를 사용한 쿼리문 제작하기
    *  https://github.com/influxdata/influxdb-java/blob/master/QUERY_BUILDER.md

    *  TODO : cpuTodoList 정렬해서 보낼것인가???
    *   https://stackoverflow.com/questions/12184378/sorting-linkedhashmap
    * */

    @Override
    public List<CpuUsage> findAll() {
        String query =  "select MEAN(cpuUsage) from CpuInfo group by uid limit 2 tz('Asia/Seoul')";
        QueryResult queryResult = influxDBTemplate.getConnection().query(new Query(query, influxDBTemplate.getDatabase()));
        List<CpuUsage> cpuinfoList = resultMapper.toPOJO(queryResult, CpuUsage.class);
        List<CpuUsage> cpuUsageList = new ArrayList<>();
//        for (CpuUsage cpuinfo : cpuinfoList) {
//            CpuUsage cpuUsage = new CpuUsage();
//            cpuUsage.setMean();
//            cpuUsage.add(CpuUsage.builder()
//                            .time(cpuinfo.getTime())
//                            .cpuUsage(cpuinfo.getCpuUsage())
//                            .uid(cpuinfo.getUid()).build());
//        }
        return cpuinfoList;
    }

    /*
    * 정렬하고 5개만 나오기
    * TODO : query 수정
    * */
    @Override
    public List<CpuUsage> findTop() {
        String query =  "select cpuUsage,hostname from CpuInfo group by uid limit 1 tz('Asia/Seoul')";
        QueryResult queryResult = influxDBTemplate.getConnection().query(new Query(query, influxDBTemplate.getDatabase()));
        List<CpuUsage> cpuUsage = new ArrayList<>();
        resultMapper.toPOJO(queryResult, Cpuinfo.class);
        return cpuUsage;
    }

    @Override
    public Optional<Double> findAverage() {
        return Optional.empty();
    }

    @Override
    public Optional<Double> findMax() {
        return Optional.empty();
    }

    @Override
    public Optional<Double> findMin() {
        return Optional.empty();
    }

    @Override
    public Optional<CpuUsage> findById(String id) {
        String query =  "select cpuUsage, uid,hostname from CpuInfo where uid='"+id+"' limit 1 tz('Asia/Seoul')";
        QueryResult queryResult = influxDBTemplate.getConnection().query(new Query(query, influxDBTemplate.getDatabase()));
//        return Optional.ofNullable(resultMapper.toPOJO(queryResult, Cpuinfo.class).get(0));
        return Optional.empty();
    }
}
