package com.lucas.osapi.service;

import com.lucas.osapi.entity.DiskInfo;
import com.lucas.osapi.entity.DiskUsage;
import com.lucas.osapi.repo.influxDB.DiskRepo;
import lombok.extern.slf4j.Slf4j;
import org.influxdb.dto.Point;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.influxdb.InfluxDBTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
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

    @Autowired
    private DiskRepo diskRepo;

    @Override
    public List<DiskUsage> findList() {
//        String query =  "select MEAN(diskUsage) from DiskInfo group by uid limit 2";
//        QueryResult queryResult = influxDBTemplate.getConnection().query(new Query(query, influxDBTemplate.getDatabase()));
        return diskRepo.findListUsage();
    }

    @Override
    public Optional<List<DiskUsage>> findTop() {
//        String query =  "select MEAN(diskUsage) from DiskInfo group by uid limit 2";
//        QueryResult queryResult = influxDBTemplate.getConnection().query(new Query(query, influxDBTemplate.getDatabase()));
        List<DiskUsage> diskUsage = diskRepo.findListUsage();
        if (diskUsage.isEmpty()) {
            return Optional.empty();
        }

        List<DiskUsage> topValue = new ArrayList<>();

        if(diskUsage.size() > 5){
            diskUsage.stream().sorted(Comparator.comparing(DiskUsage::getDiskUsage).reversed()).limit(5)
                    .forEach(topValue::add);
        }else {
            diskUsage.stream().sorted(Comparator.comparing(DiskUsage::getDiskUsage).reversed())
                    .forEach(topValue::add);
        }
        return Optional.of(topValue);
    }

    @Override
    public Optional<Double> findAverage() {
//        String query =  "select MEAN(diskUsage) from DiskInfo group by uid limit 2";
//        QueryResult queryResult = influxDBTemplate.getConnection().query(new Query(diskRepo., influxDBTemplate.getDatabase()));
        List<DiskUsage> memUsage = diskRepo.findListUsage();
        return Optional.of(memUsage.stream().mapToDouble(DiskUsage::getDiskUsage).average().orElseThrow());
    }

    @Override
    public Optional<Double> findMax() {
//        String query =  "select MEAN(diskUsage) from DiskInfo group by uid limit 2";
//        QueryResult queryResult = influxDBTemplate.getConnection().query(new Query(query, influxDBTemplate.getDatabase()));
        List<DiskUsage> memUsage = diskRepo.findListUsage();
        return Optional.of(memUsage.stream().mapToDouble(DiskUsage::getDiskUsage).max().orElseThrow());
    }

    @Override
    public Optional<Double> findMin() {
//        String query =  "select MEAN(diskUsage) from DiskInfo group by uid limit 2";
//        QueryResult queryResult = influxDBTemplate.getConnection().query(new Query(query, influxDBTemplate.getDatabase()));
        List<DiskUsage> memUsage = diskRepo.findListUsage();
        return Optional.of(memUsage.stream().mapToDouble(DiskUsage::getDiskUsage).min().orElseThrow());
    }

    @Override
    public Optional<Map<String, String>> findByIdIops(String uid) {
//        resultMapper.toPOJO(diskRepo.)

        return null;
    }

//    @Override
//    public Optional<DiskUsage> findByIdUsage(String uid) {
////        String query = "select uid, mean from (select MEAN(diskUsage) from DiskInfo group by uid limit 2) where uid='"+uid+"'";
////        QueryResult queryResult = influxDBTemplate.getConnection().query(new Query(query, influxDBTemplate.getDatabase()));
//        return Optional.ofNullable(resultMapper.toPOJO(diskRepo.findById(uid), DiskUsage.class).get(0));
//    }

    @Override
    public Optional<DiskInfo> findById(String uid) {
//        String query = "select * from DiskInfo where uid='"+uid+"' limit 1";
//        QueryResult queryResult = influxDBTemplate.getConnection().query(new Query(query, influxDBTemplate.getDatabase()));
        return Optional.ofNullable(diskRepo.findById(uid));
    }
}
