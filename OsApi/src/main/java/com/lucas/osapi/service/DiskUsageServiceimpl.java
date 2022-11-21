package com.lucas.osapi.service;

import com.lucas.osapi.entity.DiskInfo;
import com.lucas.osapi.entity.DiskUsage;
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
 * fileName       : DiskUsageServiceimpl
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
public class DiskUsageServiceimpl implements DiskUsageService {
    @Autowired
    private InfluxDBTemplate<Point> influxDBTemplate;

    @Override
    public List<DiskUsage> findList() {
        String query =  "select MEAN(diskUsage) from DiskInfo group by uid limit 2";
        QueryResult queryResult = influxDBTemplate.getConnection().query(new Query(query, influxDBTemplate.getDatabase()));
        return resultMapper.toPOJO(queryResult, DiskUsage.class);
    }

    @Override
    public Optional<List<DiskUsage>> findTop() {
        String query =  "select MEAN(diskUsage) from DiskInfo group by uid limit 2";
        QueryResult queryResult = influxDBTemplate.getConnection().query(new Query(query, influxDBTemplate.getDatabase()));
        List<DiskUsage> diskUsage = resultMapper.toPOJO(queryResult, DiskUsage.class);
        if (diskUsage.isEmpty()) {
            return Optional.empty();
        }

        List<DiskUsage> topValue = new ArrayList<>();

        if(diskUsage.size() > 5){
            diskUsage.stream().sorted(Comparator.comparing(DiskUsage::getMean).reversed()).limit(5)
                    .forEach(topValue::add);
        }else {
            diskUsage.stream().sorted(Comparator.comparing(DiskUsage::getMean).reversed())
                    .forEach(topValue::add);
        }
        return Optional.of(topValue);
    }

    @Override
    public Optional<Double> findAverage() {
        String query =  "select MEAN(diskUsage) from DiskInfo group by uid limit 2";
        QueryResult queryResult = influxDBTemplate.getConnection().query(new Query(query, influxDBTemplate.getDatabase()));
        List<DiskUsage> memUsage = resultMapper.toPOJO(queryResult, DiskUsage.class);
        return Optional.of(memUsage.stream().mapToDouble(DiskUsage::getMean).average().orElseThrow());
    }

    @Override
    public Optional<Double> findMax() {
        String query =  "select MEAN(diskUsage) from DiskInfo group by uid limit 2";
        QueryResult queryResult = influxDBTemplate.getConnection().query(new Query(query, influxDBTemplate.getDatabase()));
        List<DiskUsage> memUsage = resultMapper.toPOJO(queryResult, DiskUsage.class);
        return Optional.of(memUsage.stream().mapToDouble(DiskUsage::getMean).max().orElseThrow());
    }

    @Override
    public Optional<Double> findMin() {
        String query =  "select MEAN(diskUsage) from DiskInfo group by uid limit 2";
        QueryResult queryResult = influxDBTemplate.getConnection().query(new Query(query, influxDBTemplate.getDatabase()));
        List<DiskUsage> memUsage = resultMapper.toPOJO(queryResult, DiskUsage.class);
        return Optional.of(memUsage.stream().mapToDouble(DiskUsage::getMean).min().orElseThrow());
    }

    @Override
    public Optional<DiskUsage> findByIdUsage(String uid) {
        String query = "select uid, mean from (select MEAN(diskUsage) from DiskInfo group by uid limit 2) where uid='"+uid+"'";
        QueryResult queryResult = influxDBTemplate.getConnection().query(new Query(query, influxDBTemplate.getDatabase()));
        return Optional.ofNullable(resultMapper.toPOJO(queryResult, DiskUsage.class).get(0));
    }

    @Override
    public Optional<DiskInfo> findById(String uid) {
        String query = "select * from DiskInfo where uid='"+uid+"' limit 1";
        QueryResult queryResult = influxDBTemplate.getConnection().query(new Query(query, influxDBTemplate.getDatabase()));
        return Optional.ofNullable(resultMapper.toPOJO(queryResult, DiskInfo.class).get(0));
    }
}
