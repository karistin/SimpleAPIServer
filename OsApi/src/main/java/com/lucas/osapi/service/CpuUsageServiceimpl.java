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

//    @Autowired
//    private InfluxDBTemplate<Point> influxDBTemplate;

    @Autowired
    private CpuRepo cpuRepo;
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
    public List<CpuUsage> findList() {
//        Query query = BuiltQuery.QueryBuilder.
//        String query =  "select MEAN(cpuUsage) from CpuInfo group by uid limit 2";
//        QueryResult queryResult = influxDBTemplate.getConnection().query(new Query(query, influxDBTemplate.getDatabase()));

        return cpuRepo.findListUsage();
    }

    /*
    * 정렬하고 5개만 나오기
    * TODO : query 수정
    * */
    @Override
    public Optional<List<CpuUsage>> findTop() {
//        String query =  "select MEAN(cpuUsage) from CpuInfo group by uid limit 2";
//        QueryResult queryResult = influxDBTemplate.getConnection().query(new Query(query, influxDBTemplate.getDatabase()));
        List<CpuUsage> cpuUsage = cpuRepo.findListUsage();
        if (cpuUsage.isEmpty()) {
            return Optional.empty();
        }

        List<CpuUsage> topValue = new ArrayList<>();

        if(cpuUsage.size() > 5){
            cpuUsage.stream().sorted(Comparator.comparing(CpuUsage::getCpuUsage).reversed()).limit(5)
                    .forEach(topValue::add);
        }else {
            cpuUsage.stream().sorted(Comparator.comparing(CpuUsage::getCpuUsage).reversed())
                    .forEach(topValue::add);
        }
        return Optional.of(topValue);
    }

    @Override
    public Optional<Double> findAverage() {
//        String query =  "select MEAN(cpuUsage) from CpuInfo group by uid limit 2";
//        QueryResult queryResult = influxDBTemplate.getConnection().query(new Query(query, influxDBTemplate.getDatabase()));
        List<CpuUsage> cpuUsage = cpuRepo.findListUsage();
        return Optional.of(cpuUsage.stream().mapToDouble(CpuUsage::getCpuUsage).average().orElse(Double.NaN));
    }

    @Override
    public Optional<Double> findMax() {
//        String query =  "select MEAN(cpuUsage) from CpuInfo group by uid limit 2";
//        QueryResult queryResult = influxDBTemplate.getConnection().query(new Query(query, influxDBTemplate.getDatabase()));
        List<CpuUsage> cpuUsage = cpuRepo.findListUsage();
        return Optional.of(cpuUsage.stream().mapToDouble(CpuUsage::getCpuUsage).max().orElse(Double.NaN));
    }

    @Override
    public Optional<Double> findMin() {
//        String query =  "select MEAN(cpuUsage) from CpuInfo group by uid limit 2";
//        QueryResult queryResult = influxDBTemplate.getConnection().query(new Query(query, influxDBTemplate.getDatabase()));
        List<CpuUsage> cpuUsage = cpuRepo.findListUsage();
        return Optional.of(cpuUsage.stream().mapToDouble(CpuUsage::getCpuUsage).min().orElse(Double.NaN));
    }

//    @Override
//    public Optional<CpuUsage> findByIdUsage(String uid) {
////        String query = "select uid, mean from (select MEAN(cpuUsage) from CpuInfo group by uid limit 2) where uid='"+uid+"'";
////        QueryResult queryResult = influxDBTemplate.getConnection().query(new Query(query, influxDBTemplate.getDatabase()));
//        return Optional.ofNullable(resultMapper.toPOJO(cpuRepo.findByIdUsage(uid), CpuUsage.class).get(0));
//    }

    @Override
    public Optional<CpuInfo> findById(String uid) {
//        String query = "select * from CpuInfo where uid='"+uid+"' limit 1";
//        QueryResult queryResult = influxDBTemplate.getConnection().query(new Query(query, influxDBTemplate.getDatabase()));
        return Optional.ofNullable(cpuRepo.findById(uid));
    }
}
