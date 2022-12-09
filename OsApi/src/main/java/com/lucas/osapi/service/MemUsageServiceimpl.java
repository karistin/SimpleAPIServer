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
    public List<MemInfo> List() {
        return memRepo.findList();
    }

    @Override
    public List<MemUsage> ListUsage() {
        return memRepo.findListUsage();
    }

    @Override
    public MemInfo Id(String uid) {
        return memRepo.findById(uid);
    }

    @Override
    public List<MemInfo> IdRange(String key, Long time) {
        return memRepo.findByIdRange(key, time);
    }

    @Override
    public List<MemUsage> IdRangeUsage(String key, Long time) {
        return memRepo.findbyIdRangeUsage(key, time);
    }



    @Override
    public List<MemUsage> Top() {

        List<MemUsage> memUsage = memRepo.findListUsage();
        List<MemUsage> topValue = new ArrayList<>();

        if (memUsage.size() <= 5) {
            memUsage.stream().sorted(Comparator.comparing(MemUsage::getMemUsage)
                    .reversed())
                    .forEach(topValue::add);
        } else {
            memUsage.stream().sorted(Comparator.comparing(MemUsage::getMemUsage).reversed())
                .limit(5)
                .forEach(topValue::add);
        }
        return topValue;
    }

    @Override
    public Optional<Double> Average() {
        List<MemUsage> memUsage = memRepo.findListUsage();
        return Optional.of(memUsage.stream().mapToDouble(MemUsage::getMemUsage).average().orElseThrow());
    }

    @Override
    public Optional<Double> Max() {
        List<MemUsage> memUsage = memRepo.findListUsage();
        return Optional.of(memUsage.stream().mapToDouble(MemUsage::getMemUsage).max().orElseThrow());
    }

    @Override
    public Optional<Double> Min() {
        List<MemUsage> memUsage = memRepo.findListUsage();
        return Optional.of(memUsage.stream().mapToDouble(MemUsage::getMemUsage).min().orElseThrow());
    }


}
