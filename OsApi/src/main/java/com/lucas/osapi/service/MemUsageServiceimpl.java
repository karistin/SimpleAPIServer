package com.lucas.osapi.service;

import com.lucas.osapi.entity.MemInfo;
import com.lucas.osapi.entity.MemUsage;
import com.lucas.osapi.repo.influxDB.MemRepo;
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
 * fileName       : MemUsageServiceimpl
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
public class MemUsageServiceimpl implements MemUsageService {
//    @Autowired
//    private InfluxDBTemplate<Point> influxDBTemplate;

    @Autowired
    private MemRepo memRepo;

    @Override
    public List<MemUsage> findList() {
//        String query =  "select MEAN(memUsage) from MemInfo group by uid limit 2";
//        QueryResult queryResult = influxDBTemplate.getConnection().query(new Query(query, influxDBTemplate.getDatabase()));
        return memRepo.findListUsage();
    }

    @Override
    public Optional<List<MemUsage>> findTop() {
//        String query =  "select MEAN(memUsage) from MemInfo group by uid limit 2";
//        QueryResult queryResult = influxDBTemplate.getConnection().query(new Query(query, influxDBTemplate.getDatabase()));
        List<MemUsage> memUsage = memRepo.findListUsage();
        if (memUsage.isEmpty()) {
            return Optional.empty();
        }

        List<MemUsage> topValue = new ArrayList<>();

        if (memUsage.size() <= 5) {
            memUsage.stream().sorted(Comparator.comparing(MemUsage::getMemUsage).reversed())
                    .forEach(topValue::add);
        } else {
            memUsage.stream().sorted(Comparator.comparing(MemUsage::getMemUsage).reversed()).limit(5)
                    .forEach(topValue::add);
        }
        return Optional.of(topValue);
    }

    @Override
    public Optional<Double> findAverage() {
//        String query =  "select MEAN(memUsage) from MemInfo group by uid limit 2";
//        QueryResult queryResult = influxDBTemplate.getConnection().query(new Query(query, influxDBTemplate.getDatabase()));
        List<MemUsage> memUsage = memRepo.findListUsage();
        return Optional.of(memUsage.stream().mapToDouble(MemUsage::getMemUsage).average().orElseThrow());
    }

    @Override
    public Optional<Double> findMax() {
//        String query =  "select MEAN(memUsage) from MemInfo group by uid limit 2";
//        QueryResult queryResult = influxDBTemplate.getConnection().query(new Query(query, influxDBTemplate.getDatabase()));
        List<MemUsage> memUsage = memRepo.findListUsage();
        return Optional.of(memUsage.stream().mapToDouble(MemUsage::getMemUsage).max().orElseThrow());
    }

    @Override
    public Optional<Double> findMin() {
//        String query =  "select MEAN(memUsage) from MemInfo group by uid limit 2";
//        QueryResult queryResult = influxDBTemplate.getConnection().query(new Query(query, influxDBTemplate.getDatabase()));
        List<MemUsage> memUsage = memRepo.findListUsage();
        return Optional.of(memUsage.stream().mapToDouble(MemUsage::getMemUsage).min().orElseThrow());
    }

//    @Override
//    public Optional<MemUsage> findByIdUsage(String uid) {
////        String query = "select uid, mean from (select MEAN(memUsage) from MemInfo group by uid limit 2) where uid='"+uid+"'";
////        QueryResult queryResult = influxDBTemplate.getConnection().query(new Query(query, influxDBTemplate.getDatabase()));
//        return Optional.ofNullable(resultMapper.toPOJO(memRepo.findById(uid), MemUsage.class).get(0));
//    }

    @Override
    public Optional<MemInfo> findById(String uid) {
//        String query = "select * from MemInfo where uid='"+uid+"' limit 1";
//        QueryResult queryResult = influxDBTemplate.getConnection().query(new Query(query, influxDBTemplate.getDatabase()));
        return Optional.ofNullable(memRepo.findById(uid));
    }
}
