package com.lucas.osapi.service;


import com.lucas.osapi.entity.CpuInfo;
import com.lucas.osapi.entity.CpuUsage;
import com.lucas.osapi.repo.influxDB.CpuRepo;
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
 *  커리문 repo
 */

@Slf4j
@Service
public class CpuUsageServiceimpl implements CpuUsageService {

    @Autowired
    private CpuRepo cpuRepo;

    @Override
    public List<CpuInfo> List() {
        return cpuRepo.findList();
    }

    public List<CpuUsage> ListUsage(){
        return cpuRepo.findListUsage();
    }

    @Override
    public CpuInfo Id(String uid) {
        return cpuRepo.findById(uid);
    }


    @Override
    public List<CpuInfo> IdRange(String key, Long time){
        return cpuRepo.findByIdRange(key, time);
    }

    @Override
    public List<CpuUsage> IdRangeUsage(String key, Long time){
        return cpuRepo.findbyIdRangeUsage(key, time);
    }

    @Override
    public Optional<Double> Average() {
        List<CpuUsage> cpuUsage = cpuRepo.findListUsage();
        return Optional.of(cpuUsage.stream().mapToDouble(CpuUsage::getCpuUsage).average().orElse(Double.NaN));
    }

    @Override
    public Optional<Double> Max() {
        List<CpuUsage> cpuUsage = cpuRepo.findListUsage();
        return Optional.of(cpuUsage.stream().mapToDouble(CpuUsage::getCpuUsage).max().orElse(Double.NaN));
    }

    @Override
    public Optional<Double> Min() {
        List<CpuUsage> cpuUsage = cpuRepo.findListUsage();
//        TODO : Double -> Json
//         CpuUsage minCpuUsage = cpuUsage.stream().mapToDouble(CpuUsage::getCpuUsage).min().getClass();
        return Optional.of(cpuUsage.stream().mapToDouble(CpuUsage::getCpuUsage).min().orElse(Double.NaN));
    }

    @Override
    public List<CpuUsage> Top() {
        List<CpuUsage> cpuUsage = cpuRepo.findListUsage();

        List<CpuUsage> topValue = new ArrayList<>();

        if(cpuUsage.size() > 5){
            cpuUsage.stream().sorted(Comparator.comparing(CpuUsage::getCpuUsage).reversed()).limit(5)
                .forEach(topValue::add);
        }else {
            cpuUsage.stream().sorted(Comparator.comparing(CpuUsage::getCpuUsage).reversed())
                .forEach(topValue::add);
        }
        return topValue;
    }
}
