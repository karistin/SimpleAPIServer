package com.lucas.osapi.repo.influxDB;

import com.lucas.osapi.advice.exception.RepoException;
import com.lucas.osapi.entity.CpuInfo;
import com.lucas.osapi.entity.CpuUsage;
import lombok.extern.slf4j.Slf4j;
import org.influxdb.dto.Point;
import org.influxdb.dto.Query;
import org.influxdb.dto.QueryResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.influxdb.InfluxDBTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;


import static org.influxdb.querybuilder.BuiltQuery.QueryBuilder.desc;
import static org.influxdb.querybuilder.BuiltQuery.QueryBuilder.eq;
import static org.influxdb.querybuilder.BuiltQuery.QueryBuilder.gt;
import static org.influxdb.querybuilder.BuiltQuery.QueryBuilder.ne;
import static org.influxdb.querybuilder.BuiltQuery.QueryBuilder.select;
import static org.influxdb.querybuilder.BuiltQuery.QueryBuilder.subTime;
import static org.influxdb.querybuilder.BuiltQuery.QueryBuilder.time;
import static org.influxdb.querybuilder.time.DurationLiteral.DAY;
import static org.influxdb.querybuilder.time.DurationLiteral.MINUTE;
import static org.influxdb.querybuilder.time.DurationLiteral.SECOND;

/**
 * packageName    : com.lucas.osapi.repo.influxDB
 * fileName       : CpuRepo
 * author         : lucas
 * date           : 2022-11-21
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-11-21        lucas       최초 생성
 */
@Repository
@Slf4j
public class CpuRepoImpl implements CpuRepo {
    @Autowired
    private InfluxDBTemplate<Point> influxDBTemplate;

    @Value("${spring.influxdbRepo.cpu-table.name}")
    private String tableName;

    @Value("${spring.influxdbRepo.cpu-table.tag}")
    private String tagKey;

    @Value("${spring.influxdbRepo.cpu-table.usage}")
    private String mainCol;



    @Override
    public List<CpuUsage> findListUsage() {
        Query query = select("cpuUsage","userUsage","sysUsage")
                .from(influxDBTemplate.getDatabase(),tableName)
                .groupBy(tagKey)
                .orderBy(desc())
                .limit(1);

        log.info(query.getCommand());
        QueryResult queryResult = influxDBTemplate.query(query);
        List<CpuUsage> cpuUsage = resultMapper.toPOJO(queryResult, CpuUsage.class);
        return cpuUsage;
    }

    //    Dot data
    @Override
    public List<CpuInfo> findList() {
        Query query = select()
                .from(influxDBTemplate.getDatabase(),tableName)
                .groupBy(tagKey)
                .orderBy(desc())
                .limit(1);
        log.info(query.getCommand());
        QueryResult queryResult = influxDBTemplate.query(query);
        List<CpuInfo> cpuInfo = resultMapper.toPOJO(queryResult, CpuInfo.class);
        if (cpuInfo == null) {
            throw new RepoException();
        }
        return cpuInfo;
    }



    //    Dot data
    @Override
    public CpuInfo findById(String key) {
        Query query = select()
                .from(influxDBTemplate.getDatabase(),tableName)
                .where(eq(tagKey,key))
                .orderBy(desc())
                .limit(1);
        log.info(query.getCommand());
        QueryResult queryResult = influxDBTemplate.getConnection().query(query);
        List<CpuInfo> cpuInfoList = resultMapper.toPOJO(queryResult, CpuInfo.class);
        if (cpuInfoList == null || cpuInfoList.isEmpty()) {
            throw new RepoException();
        }
        return resultMapper.toPOJO(queryResult, CpuInfo.class).get(0);
    }


//    Range Data

    @Override
    public List<CpuInfo> findByIdRange(String key, Long time) {
        Query query = select()
            .from(influxDBTemplate.getDatabase(), tableName)
            .where(eq(tagKey, key)).and(gt("time",subTime(time, MINUTE)));

        log.info(query.getCommand());
        QueryResult queryResult = influxDBTemplate.getConnection().query(query);
        List<CpuInfo> queryResultList = resultMapper.toPOJO(queryResult, CpuInfo.class);
        if (queryResultList == null) {
            throw new RepoException();
        }
        return queryResultList;
    }

    @Override
    public List<CpuUsage> findbyIdRangeUsage(String key, Long time) {
        Query query = select("uid, cpuUsage","userUsage","sysUsage")
            .from(influxDBTemplate.getDatabase(), tableName)
            .where(eq(tagKey,key))
            .and(gt("time",subTime(
            time, MINUTE)));
        log.info(query.getCommand());
        QueryResult queryResult = influxDBTemplate.getConnection().query(query);
        List<CpuUsage> queryResultList = resultMapper.toPOJO(queryResult, CpuUsage.class);
        if (queryResultList == null) {
            throw new RepoException();
        }
        return queryResultList;
    }

    @Override
    public QueryResult query(String query) {
        return influxDBTemplate.getConnection().query(new Query(query, influxDBTemplate.getDatabase()));
    }


}
