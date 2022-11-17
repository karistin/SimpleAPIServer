package com.lucas.osapi.service;

import com.lucas.osapi.entity.CpuInfo;
import com.lucas.osapi.entity.CpuUsage;
import com.lucas.osapi.entity.MemInfo;
import com.lucas.osapi.entity.MemUsage;
import lombok.extern.slf4j.Slf4j;
import org.influxdb.dto.Point;
import org.influxdb.dto.Query;
import org.influxdb.dto.QueryResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.influxdb.InfluxDBTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

/**
 * packageName    : com.lucas.osapi.service
 * fileName       : MemUsageService
 * author         : lucas
 * date           : 2022-11-17
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-11-17        lucas       최초 생성
 */
@Slf4j
@Service
public class MemUsageService implements MetricService<MemUsage, MemInfo, String> {
    @Autowired
    private InfluxDBTemplate<Point> influxDBTemplate;

    @Override
    public List<MemUsage> findList() {
        String query =  "select MEAN(memUsage) from MemInfo group by uid limit 2";
        QueryResult queryResult = influxDBTemplate.getConnection().query(new Query(query, influxDBTemplate.getDatabase()));
        return resultMapper.toPOJO(queryResult, MemUsage.class);
    }

    @Override
    public Optional<List<MemUsage>> findTop() {
        String query =  "select MEAN(memUsage) from MemInfo group by uid limit 2";
        QueryResult queryResult = influxDBTemplate.getConnection().query(new Query(query, influxDBTemplate.getDatabase()));
        List<MemUsage> memUsage = resultMapper.toPOJO(queryResult, MemUsage.class);
        if (memUsage.isEmpty()) {
            return Optional.empty();
        }

        List<MemUsage> topValue = new ArrayList<>();

        if(memUsage.size() > 5){
            memUsage.stream().sorted(Comparator.comparing(MemUsage::getMean).reversed()).limit(5)
                    .forEach(topValue::add);
        }else {
            memUsage.stream().sorted(Comparator.comparing(MemUsage::getMean).reversed())
                    .forEach(topValue::add);
        }
        return Optional.of(topValue);
    }

    @Override
    public Optional<Double> findAverage() {
        String query =  "select MEAN(memUsage) from MemInfo group by uid limit 2";
        QueryResult queryResult = influxDBTemplate.getConnection().query(new Query(query, influxDBTemplate.getDatabase()));
        List<MemUsage> memUsage = resultMapper.toPOJO(queryResult, MemUsage.class);
        return Optional.of(memUsage.stream().mapToDouble(MemUsage::getMean).average().orElseThrow());
    }

    @Override
    public Optional<Double> findMax() {
        String query =  "select MEAN(memUsage) from MemInfo group by uid limit 2";
        QueryResult queryResult = influxDBTemplate.getConnection().query(new Query(query, influxDBTemplate.getDatabase()));
        List<MemUsage> memUsage = resultMapper.toPOJO(queryResult, MemUsage.class);
        return Optional.of(memUsage.stream().mapToDouble(MemUsage::getMean).max().orElseThrow());
    }

    @Override
    public Optional<Double> findMin() {
        String query =  "select MEAN(memUsage) from MemInfo group by uid limit 2";
        QueryResult queryResult = influxDBTemplate.getConnection().query(new Query(query, influxDBTemplate.getDatabase()));
        List<MemUsage> memUsage = resultMapper.toPOJO(queryResult, MemUsage.class);
        return Optional.of(memUsage.stream().mapToDouble(MemUsage::getMean).min().orElseThrow());
    }

    @Override
    public Optional<MemUsage> findByIdUsage(String uid) {
        String query = "select uid, mean from (select MEAN(memUsage) from MemInfo group by uid limit 2) where uid='"+uid+"'";
        QueryResult queryResult = influxDBTemplate.getConnection().query(new Query(query, influxDBTemplate.getDatabase()));
        return Optional.ofNullable(resultMapper.toPOJO(queryResult, MemUsage.class).get(0));
    }

    @Override
    public Optional<MemInfo> findById(String uid) {
        String query = "select * from MemInfo where uid='"+uid+"' limit 1";
        QueryResult queryResult = influxDBTemplate.getConnection().query(new Query(query, influxDBTemplate.getDatabase()));
        return Optional.ofNullable(resultMapper.toPOJO(queryResult, MemInfo.class).get(0));
    }
}
